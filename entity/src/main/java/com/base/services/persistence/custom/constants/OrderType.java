package com.base.services.persistence.custom.constants;

public enum OrderType {

    ASC("asc"), DESC("desc");

    private final String value;

    OrderType(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }

}
