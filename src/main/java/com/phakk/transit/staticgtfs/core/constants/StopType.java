package com.phakk.transit.staticgtfs.core.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StopType {
    STOP_0_STOP("0", "Stop"),
    STOP_1_STATION("1", "Station"),
    STOP_2_ENTRANCE_EXIT("2", "Entrance/Exit"),
    STOP_3_GENERIC_NODE("3", "Generic Node"),
    STOP_4_BOARDING_AREA("4", "Boarding Area")
    ;

    private String code;
    private String description;
}
