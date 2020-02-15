package com.phakk.transit.staticgtfs.dataproviders.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CALENDARS")
@Data
public class CalendarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service_id")
    private String serviceId;

    @Column(name = "monday")
    private Integer monday;

    @Column(name = "tuesday")
    private Integer tuesday;

    @Column(name = "wednesday")
    private Integer wednesday;

    @Column(name = "thursday")
    private Integer thursday;

    @Column(name = "friday")
    private Integer friday;

    @Column(name = "saturday")
    private Integer saturday;

    @Column(name = "sunday")
    private Integer sunday;
}
