package com.morenomjc.transit.staticgtfs.api.rest.logging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class RequestInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestPath = buildRequestPath(request);
        log.info("Request for [{}] started", requestPath);
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("Request for [{}] completed", buildRequestPath(request));
        super.afterCompletion(request, response, handler, ex);
    }

    private String buildRequestPath(HttpServletRequest request){
        return request.getMethod() + " " + request.getRequestURI();
    }
}
