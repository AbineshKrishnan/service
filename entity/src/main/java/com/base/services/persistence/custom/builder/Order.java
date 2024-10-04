package com.base.services.persistence.custom.builder;

import com.base.services.persistence.custom.constants.OrderType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Order {

    private Column columnName;

    private OrderType orderType;

}
