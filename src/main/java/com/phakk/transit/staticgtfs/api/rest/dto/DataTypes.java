package com.phakk.transit.staticgtfs.api.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum  DataTypes {
    AGENCY("agencies"),
    STOP("stops"),
    ROUTE("routes"),
    TRIP("trips"),
    CALENDAR("calendars"),
    STOP_TIMES("stoptimes"),

    ;

    private String value;
}

