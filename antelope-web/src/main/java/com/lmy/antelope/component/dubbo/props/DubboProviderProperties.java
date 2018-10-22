//package com.lmy.antelope.component.dubbo.props;
//
//import lombok.Data;
//import org.hibernate.validator.constraints.NotBlank;
//import org.hibernate.validator.constraints.NotEmpty;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.validation.annotation.Validated;
//
//import javax.validation.constraints.Min;
//import javax.validation.constraints.NotNull;
//import java.util.List;
//
///**
// * @author yangmeiliang
// * @date 2018/1/29
// */
//@Data
//@Validated
//@ConfigurationProperties(prefix = "dubbo.provider")
//public class DubboProviderProperties {
//
//    private boolean enable = false;
//
//    /**
//     * 服务版本
//     */
//    @NotBlank(message = "dubbo服务提供者的版本不能为空")
//    private String version;
//
//    /**
//     * 服务分组
//     */
//    @NotBlank(message = "dubbo服务提供者的组不能为空")
//    private String group;
//
//    /**
//     * 远程调用超时时间(毫秒)
//     */
//    @Min(value = 0, message = "dubbo服务提供者的超时时间不能小于0")
//    private int timeout = 2000;
//
//    /**
//     * 重试次数
//     */
//    @Min(value = 0, message = "dubbo服务提供者的重试次数不能小于0")
//    private int retries = 0;
//
//    /**
//     * 负载均衡
//     */
//    @NotBlank(message = "负载均衡策略不能为空")
//    private String loadbalance = "roundrobin";
//
//    /**
//     * 允许执行请求数
//     */
//    @Min(value = 0, message = "dubbo服务提供者的允许请求数不能小于0")
//    private Integer executes = 200;
//
//    @NotEmpty(message = "服务提供者集合不能为空")
//    private List<Service> services;
//
//    @Data
//    public static class Service {
//
//        @NotNull(message = "dubbo服务提供者的接口类不能为空")
//        private Class interfaceClass;
//
//        @NotBlank(message = "dubbo服务提供者的服务引用bean name不能为空")
//        private String ref;
//    }
//}
