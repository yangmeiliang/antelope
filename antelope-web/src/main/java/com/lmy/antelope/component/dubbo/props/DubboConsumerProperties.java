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
//@ConfigurationProperties(prefix = "dubbo.consumer")
//public class DubboConsumerProperties {
//
//    private boolean enable = false;
//
//    /**
//     * 检查服务提供者是否存在
//     */
//    @NotNull(message = "dubbo服务消费者是否检查服务提供者不能为空")
//    private Boolean check = Boolean.FALSE;
//
//    /**
//     * 版本
//     */
//    @NotBlank(message = "dubbo服务消费者的版本不能为空")
//    private String version;
//
//    /**
//     * 服务分组
//     */
//    @NotBlank(message = "dubbo服务消费者的分组不能为空")
//    private String group;
//
//    /**
//     * 远程调用超时时间(毫秒)
//     */
//    @Min(value = 0, message = "dubbo服务消费者的超时时间不能小于0")
//    private int timeout = 20000;
//
//    /**
//     * 重试次数
//     */
//    @Min(value = 0, message = "dubbo服务消费者的重试次数不能小于0")
//    private int retries = 0;
//
//    /**
//     * 负载均衡
//     */
//    @NotBlank(message = "dubbo服务消费者的负载均衡策略不能为空")
//    private String loadbalance = "roundrobin";
//
//    /**
//     * 参数校验
//     */
//    @NotBlank(message = "dubbo服务消费者的参数校验不能为空")
//    private String validation = "dubboValidation";
//
//    @NotEmpty(message = "dubbo服务消费者引用的服务列表不能为空")
//    private List<Reference> references;
//
//    @Data
//    public static class Reference {
//
//        @NotNull(message = "dubbo消费者引用的服务接口类名称不能为空")
//        private Class interfaceClass;
//
//        @NotBlank(message = "dubbo消费者引用的服务bean id不能为空")
//        private String beanId;
//
//        // --- 这里的group和verison一般不用设置，继承consumer的配置 ---
//
//        private String group;
//
//        private String version;
//    }
//}
