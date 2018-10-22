//package com.lmy.antelope.component.dubbo;
//
//import com.alibaba.dubbo.config.ApplicationConfig;
//import com.alibaba.dubbo.config.MonitorConfig;
//import com.alibaba.dubbo.config.ProtocolConfig;
//import com.alibaba.dubbo.config.ProviderConfig;
//import com.alibaba.dubbo.config.RegistryConfig;
//import com.alibaba.dubbo.config.spring.ServiceBean;
//import com.lmy.antelope.component.dubbo.props.DubboProviderProperties;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.autoconfigure.AutoConfigureAfter;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.annotation.PostConstruct;
//
///**
// * @author yangmeiliang
// * @date 2018/1/30
// */
//@Slf4j
//@Configuration
//@AllArgsConstructor
//@AutoConfigureAfter(value = DubboAutoConfig.class)
//@EnableConfigurationProperties(value = DubboProviderProperties.class)
//@ConditionalOnProperty(prefix = "dubbo.provider", value = "enable", havingValue = "true")
//public class ProviderAutoConfig {
//
//    private final ApplicationContext applicationContext;
//
//    private final DubboProviderProperties dubboProviderProperties;
//    private final ApplicationConfig applicationConfig;
//    private final ProtocolConfig protocolConfig;
//    private final RegistryConfig registryConfig;
//    private final MonitorConfig monitorConfig;
//
//    @Bean
//    public ProviderConfig providerConfig() {
//        ProviderConfig providerConfig = new ProviderConfig();
//        providerConfig.setVersion(dubboProviderProperties.getVersion());
//        providerConfig.setGroup(dubboProviderProperties.getGroup());
//        providerConfig.setTimeout(dubboProviderProperties.getTimeout());
//        providerConfig.setExecutes(dubboProviderProperties.getExecutes());
//        providerConfig.setLoadbalance(dubboProviderProperties.getLoadbalance());
//        providerConfig.setRetries(dubboProviderProperties.getRetries());
//        return providerConfig;
//    }
//
//
//    @PostConstruct
//    public void init() {
//        log.info("开始dubbo服务提供者自动配置...");
//        dubboProviderProperties.getServices().forEach(this::initProviderBean);
//        log.info("dubbo服务提供者自动配置完成.");
//    }
//
//    /**
//     * dubbo服务提供者启动
//     */
//    private void initProviderBean(DubboProviderProperties.Service service) {
//        ServiceBean<Object> serviceBean = new ServiceBean<>();
//        serviceBean.setInterface(service.getInterfaceClass());
//        serviceBean.setRef(applicationContext.getBean(service.getRef()));
//        serviceBean.setProvider(providerConfig());
//        serviceBean.setApplicationContext(applicationContext);
//        serviceBean.setApplication(applicationConfig);
//        serviceBean.setProtocol(protocolConfig);
//        serviceBean.setRegistry(registryConfig);
//        serviceBean.setMonitor(monitorConfig);
//        try {
//            serviceBean.afterPropertiesSet();
//        } catch (Exception e) {
//            log.error("dubbo服务提供者启动失败.interface=>" +
//                    service.getInterfaceClass().getName() + ",ref=>" + service.getRef());
//            throw new IllegalStateException(e);
//        }
//        serviceBean.export();
//    }
//
//}
