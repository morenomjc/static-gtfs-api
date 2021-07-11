package com.morenomjc.transit.staticgtfs.dataproviders.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalTime;

@Entity
@Table(schema = "STATICGTFS", name = "FREQUENCIES")
@Data
public class FrequencyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TRIP_ID")
    private String tripId;

    @Column(name = "START_TIME")
    private LocalTime startTime;

    @Column(name = "END_TIME")
    private LocalTime endTime;

    @Column(name = "HEADWAY_SECS")
    private Integer headwaySecs;

    @Column(name = "EXACT_TIMES")
    private String exactTimes;
}
