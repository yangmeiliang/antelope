//package com.lmy.antelope.component.dubbo;
//
//import com.alibaba.dubbo.config.ApplicationConfig;
//import com.alibaba.dubbo.config.MonitorConfig;
//import com.alibaba.dubbo.config.ProtocolConfig;
//import com.alibaba.dubbo.config.RegistryConfig;
//import com.lmy.antelope.component.dubbo.props.DubboProperties;
//import lombok.AllArgsConstructor;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Optional;
//
///**
// * @author yangmeiliang
// * @date 2018/1/26
// */
//@Configuration
//@AllArgsConstructor
//@EnableConfigurationProperties(DubboProperties.class)
//@ConditionalOnProperty(prefix = "dubbo", value = "enable", havingValue = "true")
//public class DubboAutoConfig {
//
//    private DubboProperties dubboProperties;
//
//    @Bean
//    @ConditionalOnMissingBean
//    public ApplicationConfig applicationConfig() {
//        ApplicationConfig applicationConfig = new ApplicationConfig();
//        DubboProperties.Application application = dubboProperties.getApplication();
//        applicationConfig.setName(application.getName());
//        applicationConfig.setOwner(application.getOwner());
//        applicationConfig.setLogger("slf4j");
//
////        Optional.ofNullable(application.getDumpDirectory())
////                .ifPresent(applicationConfig::setDumpDirectory);
//        return applicationConfig;
//    }
//
//    @Bean
//    @ConditionalOnMissingBean
//    public ProtocolConfig dubboProtocol() {
//        ProtocolConfig protocolConfig = new ProtocolConfig();
//        DubboProperties.Protocol protocol = dubboProperties.getProtocol();
//        protocolConfig.setName(protocol.getName().getName());
//        protocolConfig.setPort(protocol.getPort());
//        protocolConfig.setThreads(protocol.getThreads());
//        protocolConfig.setAccesslog(protocol.getAccesslog().toString());
//        protocolConfig.setServer(protocol.getServer());
//        Optional.ofNullable(protocol.getSerialization()).ifPresent(protocolConfig::setSerialization);
//        Optional.ofNullable(protocol.getParameters()).ifPresent(protocolConfig::setParameters);
//        Optional.ofNullable(protocol.getHost()).ifPresent(protocolConfig::setHost);
//        return protocolConfig;
//    }
//
//    @Bean
//    @ConditionalOnMissingBean
//    public RegistryConfig registryConfig() {
//        RegistryConfig registryConfig = new RegistryConfig();
//        DubboProperties.Registry registry = dubboProperties.getRegistry();
//
//        registryConfig.setAddress(registry.getAddress());
//        registryConfig.setFile(registry.getFile());
//        return registryConfig;
//    }
//
//    @Bean
//    @ConditionalOnMissingBean
//    public MonitorConfig monitorConfig() {
//        MonitorConfig monitorConfig = new MonitorConfig();
//        DubboProperties.Monitor monitor = dubboProperties.getMonitor();
//        monitorConfig.setAddress(monitor.getAddress());
//        monitorConfig.setProtocol(monitor.getProtocol());
//        return monitorConfig;
//    }
//}
