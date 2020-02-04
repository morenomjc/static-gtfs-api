package com.phakk.transit.staticgtfs.api.spec;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiMeta {
    private final String apiVersion = "v1";
    private final String staticGtfsVersion = "v1.0";
}
