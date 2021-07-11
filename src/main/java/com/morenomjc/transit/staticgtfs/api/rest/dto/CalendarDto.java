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
public class CalendarDto implements Serializable {

    @JsonProperty("service_id")
    private String serviceId;

    @JsonProperty("monday")
    private Boolean monday;

    @JsonProperty("tuesday")
    private Boolean tuesday;

    @JsonProperty("wednesday")
    private Boolean wednesday;

    @JsonProperty("thursday")
    private Boolean thursday;

    @JsonProperty("friday")
    private Boolean friday;

    @JsonProperty("saturday")
    private Boolean saturday;

    @JsonProperty("sunday")
    private Boolean sunday;
}
