package com.morssscoding.transit.staticgtfs.api.rest.dto;

import com.morssscoding.transit.staticgtfs.api.spec.ApiData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FullTripDto implements Serializable {
    private ApiData<TripDto> trip;
    private ApiData<CalendarDto> schedule;
    private List<ApiData<?>> stops;
}
