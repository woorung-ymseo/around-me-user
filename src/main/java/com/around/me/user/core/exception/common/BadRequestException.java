package com.around.me.user.core.exception.common;



import com.around.me.user.core.enums.response.ResponseCodeEnum;

import java.io.Serializable;

/**
 * 요청에러
 */
public class BadRequestException extends RuntimeException implements Serializable {

    private String code = ResponseCodeEnum.BAD_REQUEST.code();
    private String message = ResponseCodeEnum.BAD_REQUEST.message();

    public BadRequestException() {
        super();
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestException(Throwable cause) {
        super(cause);
    }

    public BadRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public BadRequestException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
