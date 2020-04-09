package com.phakk.transit.staticgtfs.dataproviders.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "STATICGTFS", name = "TRIPS")
@Data
public class TripEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ROUTE_ID")
    private String routeId;

    @Column(name = "SERVICE_ID")
    private String serviceId;

    @Column(name = "TRIP_ID")
    private String tripId;

    @Column(name = "HEADSIGN")
    private String headsign;

    @Column(name = "SHORT_NAME")
    private String shortName;

    @Column(name = "DIRECTION_ID")
    private String directionId;

    @Column(name = "BLOCK_ID")
    private String blockId;

    @Column(name = "SHAPE_ID")
    private String shapeId;

    @Column(name = "WHEELCHAIR_ACCESSIBLE")
    private String wheelchairAccessible;

    @Column(name = "BIKES_ALLOWED")
    private String bikesAllowed;
}
