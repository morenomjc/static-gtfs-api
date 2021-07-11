package com.morenomjc.transit.staticgtfs.api.rest.logging;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component
public class RequestLoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        interceptTraceHeaders(request);
        filterChain.doFilter(request, response);
    }

    private void interceptTraceHeaders(HttpServletRequest request){
        String requestId = getRequestId(request);
        MDC.put(RequestTraceHeaders.HEADER_REQUEST_ID, requestId);

        RequestTraceHeaders traceHeaders = RequestTraceHeaders.builder()
                .requestId(requestId)
                .build();

        RequestContext.setTraceHeaders(traceHeaders);
    }

    private String getRequestId(HttpServletRequest request) {
        String correlationId = request.getHeader(RequestTraceHeaders.HEADER_REQUEST_ID);
        return StringUtils.isEmpty(correlationId) ? UUID.randomUUID().toString() : correlationId;
    }
}
