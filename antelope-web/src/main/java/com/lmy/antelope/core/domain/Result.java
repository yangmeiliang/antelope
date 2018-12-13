package com.lmy.antelope.core.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yangmeiliang
 * @date 2018/10/23
 */
@Getter
public class Result {

    public static final int SUCCESS_CODE = 0;
    public static final int FAILURE_CODE = 500;

    private boolean success;
    private int code;
    private String message;
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private Map<String, Object> data;

    private Result(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public static Result success() {
        return new Result(true, SUCCESS_CODE, "");
    }

    public static Result failure() {
        return failure("系统内部错误");
    }

    public static Result failure(String message) {
        return new Result(false, FAILURE_CODE, message);
    }

    public Result setItem(Object item) {
        if (this.data == null) {
            this.data = new HashMap<>();
        }
        this.data.put("item", item);
        return this;
    }

    public Result setItems(List<?> items) {
        if (this.data == null) {
            this.data = new HashMap<>();
        }
        this.data.put("items", items);
        return this;
    }
}
