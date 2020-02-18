package com.phakk.transit.staticgtfs.core.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PickupType {
    PT_0_REGULAR("0", "Regular Pickup"),
    PT_1_NONE("1", "No Pickup"),
    PT_2_CONTACT_AGENCY("2", "Contact Agency"),
    PT_3_CONTACT_DRIVER("3", "Contact Driver")
    ;

    private String code;
    private String description;
}
