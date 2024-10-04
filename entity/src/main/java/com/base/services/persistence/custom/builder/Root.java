package com.base.services.persistence.custom.builder;

import com.base.services.persistence.custom.constants.JoinType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.relational.core.mapping.Table;

import java.lang.reflect.Field;

@Getter
@Setter
@ToString
public class Root<T> {

    private String alias;

    private Column sourceColumn;

    private Column targetColumn;

    private JoinType joinType;

    private final Class<T> clazz;

    public Root(Class<T> clazz) {
        this.clazz = clazz;
    }

    public String getTable() {
        if (clazz.isAnnotationPresent(Table.class)) {
            Table tableAnnotation = clazz.getAnnotation(Table.class);
            return tableAnnotation.value();
        } else return toSnakeCase(clazz.getSimpleName());
    }

    private String toSnakeCase(String attributeName) {
        return attributeName
                .replaceAll("([a-z])([A-Z]+)", "$1_$2")
                .toLowerCase();
    }

    public Column get(String attributeName) {
        Field field = null;
        try {
            field = this.clazz.getDeclaredField(attributeName);
        } catch (NoSuchFieldException e) {
            throw new IllegalStateException(e);
        }
        String columnName = field.isAnnotationPresent(org.springframework.data.relational.core.mapping.Column.class)
                ? field.getAnnotation(org.springframework.data.relational.core.mapping.Column.class).value()
                : toSnakeCase(attributeName);
        return Column.builder().columnName(getAlias() + "." + columnName).build();
    }

    public Column get() {
        return Column.builder().columnName(getAlias() + "." + "*").build();
    }

}
