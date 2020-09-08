package com.morssscoding.transit.staticgtfs.api.spec;

import lombok.Data;

@Data
public class ApiDocument {
    private ApiMeta meta;

    public ApiDocument() {
        meta = new ApiMeta();
    }
}
