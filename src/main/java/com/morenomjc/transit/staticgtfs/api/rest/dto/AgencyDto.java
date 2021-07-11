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
public class AgencyDto implements Serializable {

    @JsonProperty("agency_id")
    private String id;

    @JsonProperty("agency_name")
    private String name;

    @JsonProperty("agency_url")
    private String url;

    @JsonProperty("agency_timezone")
    private String timezone;

    @JsonProperty("agency_lang")
    private String lang;

    @JsonProperty("agency_phone")
    private String phone;

    @JsonProperty("agency_fare_url")
    private String fareUrl;

    @JsonProperty("agency_email")
    private String email;
}
