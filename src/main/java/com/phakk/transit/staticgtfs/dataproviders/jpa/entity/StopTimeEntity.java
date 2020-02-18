package com.phakk.transit.staticgtfs.dataproviders.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalTime;

@Entity
@Table(name = "STOPTIMES")
@Data
public class StopTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "trip_id")
    private String tripId;

    @Column(name = "arrival_time")
    private LocalTime arrivalTime;

    @Column(name = "departure_time")
    private LocalTime departureTime;

    @Column(name = "stop_id")
    private String stopId;

    @Column(name = "stop_sequence")
    private Integer stopSequence;

    @Column(name = "stop_headsign")
    private String stopHeadsign;

    @Column(name = "pickup_type")
    private String pickupType;

    @Column(name = "drop_off_type")
    private String dropOffType;

    @Column(name = "shape_dist_traveled")
    private Double distanceTraveled;

    @Column(name = "timepoint")
    private String timepoint;
}
