package com.morssscoding.transit.staticgtfs.api.spec;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ApiMeta {
    private Map<String, String> api = new HashMap<>();
    private Map<String, String> gtfs = new HashMap<>();

    public ApiMeta() {
        api.put("version", "v1");
        gtfs.put("static", "v1.0");
    }

}
