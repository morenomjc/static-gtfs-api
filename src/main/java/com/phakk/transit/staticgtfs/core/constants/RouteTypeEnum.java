package com.phakk.transit.staticgtfs.core.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RouteTypeEnum {
    ROUTE_100("100", "Railway Service"),
    ROUTE_200("200", "Coach Service"),
    ROUTE_400("400", "Urban Railway Service"),
    ROUTE_700("700", "Bus Service"),
    ;

    private String id;
    private String description;
}
