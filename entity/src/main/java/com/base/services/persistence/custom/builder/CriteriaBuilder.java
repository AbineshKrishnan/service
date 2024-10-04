package com.base.services.persistence.custom.builder;

import com.base.services.persistence.custom.constants.JoinType;
import com.base.services.persistence.custom.constants.LikeOperator;
import com.base.services.persistence.custom.constants.OrderType;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Slf4j
public class CriteriaBuilder {

    private Root<?> fromRoot;

    private Map<String, Root<?>> joinRoot;

    public CustomQuery customQueryBuilder() {
        CustomQuery customQuery = new CustomQuery();
        return customQuery.criteriaBuilder(this);
    }

    public <T> Root<T> from(Class<T> clazz) {
        Root<T> root = new Root<>(clazz);
        String alias = "m";
        if (fromRoot != null)
            throw new IllegalArgumentException("from class already defined");
        root.setAlias(alias);
        fromRoot = root;
        return root;
    }

    public <T> Root<T> join(Class<T> clazz, Column sourceColumn, String joinColumnName, JoinType joinType) {
        Root<T> root = new Root<>(clazz);
        if (fromRoot == null)
            throw new IllegalArgumentException("from not defined to join");
        joinRoot = joinRoot != null ? joinRoot : new LinkedHashMap<>();
        String alias = getUniqueKey(joinRoot, "j");
        root.setAlias(alias);
        root.setSourceColumn(sourceColumn);
        root.setTargetColumn(root.get(joinColumnName));
        root.setJoinType(joinType);
        joinRoot.put(alias, root);
        return root;
    }

    public Select select(Root<?> selection) {
        return Select.builder().data(String.format("SELECT DISTINCT %s.* FROM ", selection.getAlias())).build();
    }

    public Select count(Root<?> selection) {
        return Select.builder().data(String.format("SELECT COUNT(DISTINCT %s.*) FROM ", selection.getAlias())).build();
    }

    public Select count(Column... selections) {
        return Select.builder().data(String.format("SELECT COUNT(DISTINCT %s) FROM ", getAttributes(selections))).build();
    }

    public Select multiSelect(Column... selections) {
        return Select.builder().data(String.format("SELECT DISTINCT %s FROM ", getAttributes(selections))).build();
    }

    public Distinct selectDistinctOn(Column... distinctOn) {
        return Distinct.builder().data(String.format("DISTINCT ON (%s)", getAttributes(distinctOn))).build();
    }

    private String getAttributes(Column... columns) {
        return Arrays.stream(columns).map(Column::columnName).collect(Collectors.joining(", "));
    }

    public String getUniqueKey(Map<String, Root<?>> map, String baseKey) {
        String key = baseKey;
        int suffix = 0;
        while (map.containsKey(key))
            key = baseKey + (++suffix);
        return key;
    }

    public String extractAlias(String input) {
        input = input.toLowerCase();
        return (input.contains("_")
                ? extractFromSnakeCase(input)
                : extractFromCamelCase(input)) + ".";
    }

    public String extractFromCamelCase(String input) {
        StringBuilder firstLetters = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (Character.isUpperCase(ch) || i == 0)
                firstLetters.append(Character.toLowerCase(ch));
        }
        return firstLetters.toString().toLowerCase();
    }

    private String extractFromSnakeCase(String input) {
        StringBuilder abbreviation = new StringBuilder();
        String[] words = input.split("_");
        for (String word : words) {
            if (!word.isEmpty())
                abbreviation.append(word.charAt(0));
        }
        return abbreviation.toString().toLowerCase();
    }

    public Predicate equals(Column column, Object value) {
        return Predicate.builder().condition(String.format(" %s = '%s' ", column.columnName(), value)).build();
    }

    public Predicate in(Column column, List<?> values) {
        return Predicate.builder().condition(String.format(" %s IN ('%s') ", column.columnName(), inValues(values))).build();
    }

    public Predicate notIn(Column column, List<?> values) {
        return Predicate.builder().condition(String.format(" %s NOT IN ('%s') ", column.columnName(), inValues(values))).build();
    }

    private String inValues(List<?> values) {
        return String.join("','", values.stream().map(Object::toString).toList());
    }

    public Predicate like(Column column, Object value, LikeOperator likeOperator) {
        return Predicate.builder().condition(String.format(" %s LIKE '%s' ", column.columnName(), likeStart(likeOperator, value))).build();
    }

    private Object likeStart(LikeOperator likeOperator, Object value) {
        return switch (likeOperator) {
            case ALL -> "%" + value + "%";
            case START -> value + "%";
            case END -> "%" + value;
            case null -> value;
        };
    }

    public Predicate between(Column column, Object value1, Object value2) {
        return Predicate.builder().condition(String.format(" %s BETWEEN '%s' and '%s' ", column.columnName(), value1, value2)).build();
    }

    public Predicate greaterThan(Column column, Object value) {
        return Predicate.builder().condition(String.format(" %s < '%s' ", column.columnName(), value)).build();
    }

    public Predicate lessThan(Column column, Object value) {
        return Predicate.builder().condition(String.format(" %s > '%s' ", column.columnName(), value)).build();
    }

    public Predicate greaterThanEqual(Column column, Object value) {
        return Predicate.builder().condition(String.format(" %s <= '%s' ", column.columnName(), value)).build();
    }

    public Predicate lessThanEqual(Column column, Object value) {
        return Predicate.builder().condition(String.format(" %s >= '%s' ", column.columnName(), value)).build();
    }

    public Predicate and(Predicate... predicates) {
        return Predicate.builder()
                .condition(joinPredicate(" and ", predicates))
                .build();
    }

    public Predicate or(Predicate... predicates) {
        return Predicate.builder()
                .condition(joinPredicate(" or ", predicates))
                .build();
    }

    private String joinPredicate(String condition, Predicate... predicates) {
        return String.format(" (%s) ", String.join(condition, getPredicates(predicates)));
    }

    private List<String> getPredicates(Predicate... predicates) {
        return Arrays.stream(predicates).map(Predicate::getCondition).toList();
    }

    public Order asc(Column column) {
        return Order.builder().columnName(column).orderType(OrderType.ASC).build();
    }

    public Order desc(Column column) {
        return Order.builder().columnName(column).orderType(OrderType.DESC).build();
    }

}
