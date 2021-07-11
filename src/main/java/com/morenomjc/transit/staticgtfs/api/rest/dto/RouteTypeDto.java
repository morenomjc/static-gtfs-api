package com.morenomjc.transit.staticgtfs.api.rest.dto;

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
public class RouteTypeDto implements Serializable {

    @JsonProperty("route_type")
    private DataTypeDto type;

    @JsonProperty("count")
    private Integer count;
}
