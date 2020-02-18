package com.phakk.transit.staticgtfs.core.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Direction {
    OUTBOUND("0", "Outbound"),
    INBOUND("1", "Inbound")
    ;

    private String code;
    private String description;
}
