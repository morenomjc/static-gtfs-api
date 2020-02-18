package com.phakk.transit.staticgtfs.core.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WheelchairAccessibility {
    WA_0_NO_INFO("0", "No Information"),
    WA_1_ACCESSIBLE("1", "Accessible"),
    WA_2_NOT_ACCESSIBLE("2", "Not Accessible"),
    ;

    private String code;
    private String description;
}
