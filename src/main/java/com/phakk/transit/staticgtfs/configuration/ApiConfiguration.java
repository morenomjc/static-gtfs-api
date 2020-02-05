package com.phakk.transit.staticgtfs.configuration;

import com.phakk.transit.staticgtfs.api.rest.logging.RequestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@Order(1)
public class ApiConfiguration extends WebMvcConfigurationSupport {

    private RequestInterceptor requestInterceptor;

    public ApiConfiguration(RequestInterceptor requestInterceptor) {
        this.requestInterceptor = requestInterceptor;
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestInterceptor);
    }
}
