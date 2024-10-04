package com.base.services.persistence.custom.builder;

import com.base.services.persistence.custom.constants.OrderType;
import lombok.Builder;

@Builder
public class Column {

    private String columnName;

    private OrderType orderType;

    public String columnName() {
        return this.columnName;
    }

}
