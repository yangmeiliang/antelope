package com.lmy.antelope.support;

import com.alibaba.fastjson.JSON;
import com.lmy.antelope.core.domain.Result;
import com.lmy.antelope.support.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @author yangmeiliang
 * @date 2018/10/23
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result handle(Exception ex) {
        return Result.failure(ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public Result handle(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        String params = JSON.toJSONString(request.getParameterMap());
        String parameterName = ex.getPropertyName();
        String type = Optional.ofNullable(ex.getRequiredType()).map(Class::getName).orElse("");
        log.error("请求参数类型不匹配, url: {}, 参数: {}, 异常类型参数: {}, 期望类型: {}", url, params, parameterName, type);
        return Result.failure(String.format("参数【%s】类型不匹配", parameterName));
    }

    @ResponseBody
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public Result handle(MissingServletRequestParameterException ex, HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        String params = JSON.toJSONString(request.getParameterMap());
        String parameterName = ex.getParameterName();
        log.error("请求参数缺失, url: {}, 参数: {}, 缺失参数: {}", url, params, parameterName);
        return Result.failure(String.format("参数【%s】缺失", parameterName));
    }

    @ResponseBody
    @ExceptionHandler(value = BusinessException.class)
    public Result handle(BusinessException ex, HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        String params = JSON.toJSONString(request.getParameterMap());
        log.error("业务异常, url: {}, 参数: {}, 异常信息: {}", url, params, ex.getMessage());
        return Result.failure(ex.getMessage());
    }
}
