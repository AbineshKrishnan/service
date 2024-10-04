package com.base.services.persistence.custom.builder;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CustomQuery {

    private Select select;

    private CriteriaBuilder criteriaBuilder;

    private Predicate where;

    private Distinct distinctOn;

    private String orderBy;

    private int limit;

    private Long offset;

    private boolean distinct;

    private String groupBy;

    public CustomQuery select(Select selection) {
        select = selection;
        return this;
    }

    public CustomQuery criteriaBuilder(CriteriaBuilder builder) {
        criteriaBuilder = builder;
        return this;
    }

    public CustomQuery where(Predicate predicate) {
        where = predicate;
        return this;
    }

    public CustomQuery distinctOn(Distinct distinct) {
        distinctOn = distinct;
        return this;
    }

    public CustomQuery orderBy(Order... orders) {
        List<String> orderList = Arrays.stream(orders).map(this::joinOrder).toList();
        orderBy = String.join(",", orderList);
        return this;
    }

    public CustomQuery orderBy(List<Order> orders) {
        List<String> orderList = orders.stream().map(this::joinOrder).toList();
        orderBy = String.join(",", orderList);
        return this;
    }

    private String joinOrder(Order o) {
        return String.format(" %s %s ", o.getColumnName().columnName(), o.getOrderType());
    }

    public CustomQuery limit(int limitValue) {
        limit = limitValue;
        return this;
    }

    public CustomQuery offset(long offsetValue) {
        offset = offsetValue;
        return this;
    }

    public CustomQuery distinct(boolean isDistinct) {
        distinct = isDistinct;
        return this;
    }

    public CustomQuery groupBy(Column... column) {
        List<String> groups = Arrays.stream(column).map(Column::columnName).toList();
        groupBy = String.join(" ,", groups);
        return this;
    }

    public String getQuery() {
        return getSelectQuery() + getFromQuery() + getJoinQuery()
                + getWhereQuery() + getGroupByQuery() + getOrderQuery()
                + getLimitQuery() + getOffsetQuery();
    }

    private String getSelectQuery() {
        String data = distinctOn != null ? distinctOn.getData() : "";
        if (Boolean.TRUE.equals(distinct) && !data.isBlank())
            throw new IllegalArgumentException("either DISTINCT or DISTINCT ON can be used");
        else if (Boolean.FALSE.equals(distinct) && data.isBlank())
            select.setData(select.getData().replace("DISTINCT ", ""));
        else if (!data.isBlank())
            select.setData(select.getData().replace("DISTINCT", data));
        return select.getData();
    }

    private String getFromQuery() {
        nullCheck();
        return criteriaBuilder.getFromRoot().getTable() + " " + criteriaBuilder.getFromRoot().getAlias();
    }

    private String getJoinQuery() {
        nullCheck();
        if (criteriaBuilder.getJoinRoot() != null) {
            StringBuilder stringBuilder = new StringBuilder();
            for (Map.Entry<String, Root<?>> entry : criteriaBuilder.getJoinRoot().entrySet()) {
                Root<?> root = entry.getValue();
                stringBuilder.append(String.format(" %s %s %s ON %s = %s ", root.getJoinType().sql(), root.getTable(), root.getAlias()
                        , root.getSourceColumn().columnName(), root.getTargetColumn().columnName()));
            }
            return stringBuilder.toString();
        }
        return "";
    }

    private void nullCheck() {
        if (criteriaBuilder == null)
            throw new IllegalArgumentException("criteriaBuilder is null");
    }

    private String getWhereQuery() {
        String condition = where != null ? where.getCondition() : "";
        return condition.isBlank() ? "" : " WHERE " + condition;
    }

    private String getLimitQuery() {
        return limit > 0 ? " LIMIT " + limit : "";
    }

    private String getOrderQuery() {
        return orderBy != null && !orderBy.isBlank() ? " ORDER BY " + orderBy : "";
    }

    private String getGroupByQuery() {
        return groupBy != null && !groupBy.isBlank() ? " GROUP BY " + groupBy : "";
    }

    private String getOffsetQuery() {
        if (offset != null && limit < 1)
            throw new IllegalArgumentException("limit should not be less than 1 with offset value");
        return offset == null ? "" : " OFFSET " + offset;
    }

}
