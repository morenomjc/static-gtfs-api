package com.morssscoding.transit.staticgtfs.dataproviders.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "STATICGTFS", name = "CALENDARS")
@Data
public class CalendarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SERVICE_ID")
    private String serviceId;

    @Column(name = "MONDAY")
    private Integer monday;

    @Column(name = "TUESDAY")
    private Integer tuesday;

    @Column(name = "WEDNESDAY")
    private Integer wednesday;

    @Column(name = "THURSDAY")
    private Integer thursday;

    @Column(name = "FRIDAY")
    private Integer friday;

    @Column(name = "SATURDAY")
    private Integer saturday;

    @Column(name = "SUNDAY")
    private Integer sunday;

    @Column(name = "START_DATE")
    private String startDate;

    @Column(name = "END_DATE")
    private String endDate;
}
