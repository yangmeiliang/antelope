package com.lmy.antelope.support.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yangmeiliang
 * @date 2018/6/11
 */
@Getter
@Setter
public class BusinessException extends AntelopeRuntimeException {

    public static final int DEFAULT_FAULT_CODE = 500;
    private int code;
    private String message;

    public BusinessException(String message) {
        this(DEFAULT_FAULT_CODE, message);
    }

    public BusinessException(int code, String message) {
        this(code, message, new Throwable());
    }

    public BusinessException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }
}
