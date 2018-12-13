package com.lmy.antelope.support.exception;

/**
 * @author yangmeiliang
 * @date 2018/6/11
 */
public class AntelopeRuntimeException extends RuntimeException {

    public AntelopeRuntimeException() {
    }

    public AntelopeRuntimeException(String message) {
        super(message);
    }

    public AntelopeRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
