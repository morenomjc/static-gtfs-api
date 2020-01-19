package com.phakk.transit.staticgtfs.core.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WheelchairBoardingEnum {
    WB_0("0", "No Information"),
    WB_1("1", "Accessible"),
    WB_2("2", "Not Accessible"),
    ;

    private String id;
    private String description;
}
