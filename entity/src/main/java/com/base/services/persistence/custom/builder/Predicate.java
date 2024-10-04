package com.base.services.persistence.custom.builder;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Predicate {

    private String condition;

}
