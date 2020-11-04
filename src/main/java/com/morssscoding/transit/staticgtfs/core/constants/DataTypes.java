package com.morssscoding.transit.staticgtfs.core.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DataTypes {
    AGENCY("agencies"),
    CALENDAR("calendars"),
    FREQUENCIES("frequencies"),
    ROUTE("routes"),
    SHAPE("shapes"),
    STOP("stops"),
    STOP_TIMES("stoptimes"),
    TRIP("trips"),
    ;

    private String value;
}

