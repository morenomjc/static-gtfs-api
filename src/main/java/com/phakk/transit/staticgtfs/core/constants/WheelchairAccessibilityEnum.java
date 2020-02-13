package com.phakk.transit.staticgtfs.core.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WheelchairAccessibilityEnum {
    WA_0("0", "No Information"),
    WA_1("1", "Accessible"),
    WA_2("2", "Not Accessible"),
    ;

    private String id;
    private String description;
}
