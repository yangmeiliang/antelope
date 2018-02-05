package com.lmy.antelope.component.dubbo.props;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @author yangmeiliang
 * @date 2018/1/26
 */
@Data
@Validated
@ConfigurationProperties(prefix = "dubbo")
public class DubboProperties {

    private boolean enable = false;

    @NotNull(message = "dubbo的应用配置不能为空")
    private DubboProperties.Application application;

    @NotNull(message = "dubbo的协议配置不能为空")
    private DubboProperties.Protocol protocol = new DubboProperties.Protocol();

    @NotNull(message = "dubbo的注册中心配置不能为空")
    private DubboProperties.Registry registry;

    @NotNull(message = "dubbo的监控中心配置不能为空")
    private DubboProperties.Monitor monitor;

    @Data
    public static class Application {
        private String name;
        private String owner;
        /**
         * thread stack存储路径
         */
        private String dumpDirectory = "/tmp";
    }

    @Data
    public static class Protocol {
        @NotNull(message = "dubbo协议名不能为空")
        private ProtocolName name = ProtocolName.DUBBO;

        /**
         * 服务IP地址(多网卡时使用)
         */
        private String host;

        @Min(value = 0, message = "dubbo协议端口不能小于0")
        private int port = 20885;

        /**
         * 线程池大小(固定大小)
         */
        @Min(value = 0, message = "dubbo协议线程数量不能小于0")
        private int threads = 200;

        /**
         * 序列化方式
         */
        private String serialization;

        /**
         * 访问日志
         */
        private Boolean accesslog = Boolean.TRUE;

        /**
         * 参数
         */
        private Map<String, String> parameters;

        /**
         * 服务器端实现
         */
        @NotNull(message = "服务器端实现方式不能为空")
        private String server = ServerName.NETTY.getName();
    }

    /**
     * dubbo注册中心配置类
     */
    @Data
    public static class Registry {

        @NotBlank(message = "dubbo注册中心地址不能为空")
        private String address;

        @NotBlank(message = "dubbo注册中心缓存文件路径不能为空")
        private String file;
    }

    /**
     * dubbo监控中心配置类
     */
    @Data
    public static class Monitor {

        @NotBlank(message = "dubbo监控中心地址不能为空")
        private String address;

        @NotBlank(message = "dubbo监控中心协议不能为空")
        private String protocol = "registry";
    }

    /**
     * 协议名称类型
     */
    @Getter
    @AllArgsConstructor
    public enum ProtocolName {
        /**
         * dubbo
         */
        DUBBO("dubbo"),

        /**
         * rmi
         */
        RMI("rmi"),

        /**
         * hessian
         */
        HESSIAN("hessian"),

        /**
         * http
         */
        HTTP("http"),

        /**
         * webservice
         */
        WEBSERVICE("webservice"),

        /**
         * thrift
         */
        THRIFT("thrift"),

        /**
         * memcached
         */
        MEMCACHED("memcached"),

        /**
         * redis
         */
        REDIS("redis");


        private String name;

    }

    @Getter
    @AllArgsConstructor
    public enum ServerName {
        /**
         * netty
         */
        NETTY("netty");

        private String name;
    }
}
