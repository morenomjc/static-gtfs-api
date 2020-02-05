package com.phakk.transit.staticgtfs.api.rest.logging;

import java.io.Serializable;

public class RequestContext implements Serializable {

    private static final ThreadLocal<RequestTraceHeaders> headersThreadLocal = new ThreadLocal<>();

    public static RequestTraceHeaders getRequestTraceHeaders(){
        return headersThreadLocal.get();
    }

    public static void setTraceHeaders(RequestTraceHeaders requestTraceHeaders){
        RequestContext.headersThreadLocal.set(requestTraceHeaders);
    }
}
