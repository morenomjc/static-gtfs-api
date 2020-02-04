package com.phakk.transit.staticgtfs.api.spec;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiTemplate {
    private ApiMeta meta;

    public ApiTemplate() {
        meta = new ApiMeta();
    }
}
