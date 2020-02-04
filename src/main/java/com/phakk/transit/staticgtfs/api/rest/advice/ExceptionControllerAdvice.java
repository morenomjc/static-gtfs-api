package com.phakk.transit.staticgtfs.api.rest.advice;

import com.phakk.transit.staticgtfs.api.spec.ApiError;
import com.phakk.transit.staticgtfs.api.spec.ApiTemplate;
import com.phakk.transit.staticgtfs.api.spec.Error;
import com.phakk.transit.staticgtfs.core.constants.ErrorCode;
import com.phakk.transit.staticgtfs.core.exception.ConstantsMappingException;
import com.phakk.transit.staticgtfs.core.exception.DataNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@Slf4j
@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ApiTemplate handleDataNotFoundException(final DataNotFoundException exception){
        logError(exception);
        return mapToApiErrorDto(exception, HttpStatus.NOT_FOUND.value(), ErrorCode.ERROR_400_NOT_FOUND);
    }

    @ExceptionHandler(ConstantsMappingException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiTemplate handleConstantsMappingException(final ConstantsMappingException exception){
        logError(exception);
        return mapToApiErrorDto(exception, HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorCode.ERROR_500_INTERNAL_SERVER_ERROR);
    }

    private void logError(Exception e){
        log.error("Exception encountered caused by: {}", e.getMessage());
    }

    private Error mapToErrorDto(Exception exception, int httpStatusCode, ErrorCode errorCode){
        return new Error(
                UUID.randomUUID().toString(),
                httpStatusCode,
                errorCode.getCode(),
                errorCode.getDetail(),
                exception.getMessage()
        );
    }

    private ApiError mapToApiErrorDto(Exception exception, int httpStatusCode, ErrorCode errorCode){
        return new ApiError(
                mapToErrorDto(exception, httpStatusCode, errorCode)
        );
    }
}
