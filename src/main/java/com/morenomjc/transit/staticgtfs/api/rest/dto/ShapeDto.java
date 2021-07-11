package com.morenomjc.transit.staticgtfs.api.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class ShapeDto implements Serializable {

    @JsonProperty("shape_id")
    private String id;
    @JsonProperty("points")
    private List<PointDto> points;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PointDto{

        @JsonProperty("shape_pt_lat")
        private Double lat;

        @JsonProperty("shape_pt_lon")
        private Double lon;

        @JsonProperty("shape_pt_sequence")
        private Integer sequence;

        @JsonProperty("shape_dist_traveled")
        private Double distanceTraveled;
    }
}
