package com.morssscoding.transit.staticgtfs.api.rest.logging;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@Data
@Getter
@Builder
public class RequestTraceHeaders implements Serializable {

    public static final String HEADER_REQUEST_ID = "x-request-id";
    public static final String HEADER_REQUEST_PATH = "x-request-path";

    private String requestId;
}
