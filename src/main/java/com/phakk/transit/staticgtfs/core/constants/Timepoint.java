package com.phakk.transit.staticgtfs.core.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Timepoint {
    TP_0_APPROXIMATE("0", "Approximate Time"),
    TP_1_EXACT("1", "Exact Time"),
    ;

    private String code;
    private String description;
}
