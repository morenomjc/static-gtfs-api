package com.morenomjc.transit.staticgtfs.core.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    ERROR_404_NOT_FOUND("404.0", "Resource Not Found"),

    ERROR_500_INTERNAL_SERVER_ERROR("500.0", "Internal Server Error"),
    ;

    private String code;
    private String detail;
}
