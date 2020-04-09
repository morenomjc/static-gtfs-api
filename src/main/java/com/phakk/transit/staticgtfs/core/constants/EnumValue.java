package com.phakk.transit.staticgtfs.core.constants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnumValue {

    private String file;
    private String field;
    private String code;
    private String name;
    private String description;
}
