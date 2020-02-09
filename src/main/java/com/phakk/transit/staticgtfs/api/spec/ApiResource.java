package com.phakk.transit.staticgtfs.api.spec;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ApiResource<T> extends ApiDocument{
    private ApiData<T> data;

    public ApiResource(String type, T data) {
        this.data = new ApiData<>(type, data);
    }
}
