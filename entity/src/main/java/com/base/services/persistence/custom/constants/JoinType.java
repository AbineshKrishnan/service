package com.base.services.persistence.custom.constants;

public enum JoinType {

    JOIN("JOIN"),
    CROSS_JOIN("CROSS JOIN"),
    LEFT_OUTER_JOIN("LEFT OUTER JOIN"),
    RIGHT_OUTER_JOIN("RIGHT OUTER JOIN"),
    FULL_OUTER_JOIN("FULL OUTER JOIN");

    private final String sql;

    JoinType(String sql) {
        this.sql = sql;
    }

    public String sql() {
        return this.sql;
    }
}
