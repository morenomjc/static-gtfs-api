package com.phakk.transit.staticgtfs.core.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RouteType {
    ROUTE_100_RAIL("100", "Railway Service"),
    ROUTE_200_COACH("200", "Coach Service"),
    ROUTE_400_URBAN_RAIL("400", "Urban Railway Service"),
    ROUTE_700_BUS("700", "Bus Service"),
    ;

    private String code;
    private String description;
}
