package com.phakk.transit.staticgtfs.core.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BikesAllowedEnum {
    BIKES_ALLOWED_0("0", "No Information"),
    BIKES_ALLOWED_1("1", "Bikes Allowed"),
    BIKES_ALLOWED_3("2", "Bikes Not Allowed"),
    ;

    private String id;
    private String description;
}
