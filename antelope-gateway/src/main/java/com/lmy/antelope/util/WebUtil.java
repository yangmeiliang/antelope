package com.lmy.antelope.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * @author yangmeiliang
 */
public class WebUtil {

    /**
     * 判断请求是否来自移动终端 - 正则
     */
    public static Pattern PTN_UA_MOBILE_LOWERCASE = Pattern.compile(".*AppleWebKit.*Mobile", Pattern.CASE_INSENSITIVE);

    public static Mono<ServerResponse> redirect(String url) {
        return ServerResponse.temporaryRedirect(URI.create(url)).build();
    }

    public static Mono<Void> redirect(ServerHttpResponse response, String url) {
        response.setStatusCode(HttpStatus.SEE_OTHER);
        response.getHeaders().set("Location", url);
        return response.setComplete();
    }

    public static String getCookieValue(ServerHttpRequest request, String cookieKey) {
        return Optional.ofNullable(request.getCookies().getFirst(cookieKey)).map(HttpCookie::getValue).orElse("");
    }

    public static String getHeaderValue(ServerHttpRequest request, String headerKey) {
        return request.getHeaders().getFirst(headerKey);
    }

    public static boolean isMobile(ServerHttpRequest request) {
        String headerValue = getHeaderValue(request, "User-Agent");
        return PTN_UA_MOBILE_LOWERCASE.matcher(headerValue).find();
    }

    public static boolean isIos(ServerHttpRequest request) {
        String headerValue = getHeaderValue(request, "User-Agent");
        if (StringUtils.isBlank(headerValue)) {
            return false;
        }
        return StringUtils.containsAny(headerValue, "iphone", "ipod", "ipad", "ios");
    }

}
