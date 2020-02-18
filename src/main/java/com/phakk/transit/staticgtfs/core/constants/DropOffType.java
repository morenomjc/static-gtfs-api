package com.phakk.transit.staticgtfs.core.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DropOffType {
    DOT_0_REGULAR("0", "Regular Drop Off"),
    DOT_1_NONE("1", "No Drop Off"),
    DOT_2_CONTACT_AGENCY("2", "Contact Agency"),
    DOT_3_CONTACT_DRIVER("3", "Contact Driver")
    ;

    private String code;
    private String description;
}
