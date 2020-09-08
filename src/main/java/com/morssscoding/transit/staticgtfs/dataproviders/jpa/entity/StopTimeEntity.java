package com.morssscoding.transit.staticgtfs.dataproviders.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalTime;

@Entity
@Table(schema = "STATICGTFS", name = "STOPTIMES")
@Data
public class StopTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TRIP_ID")
    private String tripId;

    @Column(name = "ARRIVAL_TIME")
    private LocalTime arrivalTime;

    @Column(name = "DEPARTURE_TIME")
    private LocalTime departureTime;

    @Column(name = "STOP_ID")
    private String stopId;

    @Column(name = "SEQUENCE")
    private Integer stopSequence;

    @Column(name = "HEADSIGN")
    private String stopHeadsign;

    @Column(name = "PICKUP_TYPE")
    private String pickupType;

    @Column(name = "DROP_OFF_TYPE")
    private String dropOffType;

    @Column(name = "SHAPE_DISTANCE_TRAVELED")
    private Double distanceTraveled;

    @Column(name = "TIMEPOINT")
    private String timepoint;
}
