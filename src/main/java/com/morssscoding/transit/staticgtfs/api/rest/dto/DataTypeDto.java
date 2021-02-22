package com.morssscoding.transit.staticgtfs.api.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataTypeDto implements Serializable {

    @JsonProperty("code")
    private String code;
    @JsonProperty("desc")
    private String desc;
}
