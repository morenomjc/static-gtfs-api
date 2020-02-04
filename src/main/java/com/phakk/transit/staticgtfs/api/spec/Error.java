package com.phakk.transit.staticgtfs.api.spec;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Error {
    private String id;
    private Integer status;
    private String code;
    private String title;
    private String detail;
}
