package com.phakk.transit.staticgtfs.core.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StopTypeEnum {
    STOP_0("0", "Stop"),
    STOP_1("1", "Station"),
    STOP_2("2", "Entrance/Exit"),
    STOP_3("3", "Generic Node"),
    STOP_4("4", "Boarding Area")
    ;

    private String id;
    private String description;
}
