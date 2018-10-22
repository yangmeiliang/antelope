//package com.lmy.antelope.component.dubbo;
//
//import com.alibaba.dubbo.config.ApplicationConfig;
//import com.alibaba.dubbo.config.ConsumerConfig;
//import com.alibaba.dubbo.config.MonitorConfig;
//import com.alibaba.dubbo.config.RegistryConfig;
//import com.alibaba.dubbo.config.spring.ReferenceBean;
//import com.lmy.antelope.component.dubbo.props.DubboConsumerProperties;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.config.BeanPostProcessor;
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.boot.autoconfigure.AutoConfigureAfter;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.concurrent.atomic.AtomicBoolean;
//
///**
// * @author yangmeiliang
// * @date 2018/1/30
// */
//@Slf4j
//@Configuration
//@AllArgsConstructor
//@AutoConfigureAfter(value = DubboAutoConfig.class)
//@EnableConfigurationProperties(value = DubboConsumerProperties.class)
//@ConditionalOnProperty(prefix = "dubbo.consumer", value = "enable", havingValue = "true")
//public class ConsumerAutoConfig {
//
//    private final DubboConsumerProperties dubboConsumerProperties;
//    private final ApplicationConfig applicationConfig;
//    private final RegistryConfig registryConfig;
//    private final MonitorConfig monitorConfig;
//
//    private final ApplicationContext applicationContext;
//    private final ConfigurableListableBeanFactory beanFactory;
//
//    private static final AtomicBoolean INITED = new AtomicBoolean(false);
//
//    @Bean
//    public ConsumerConfig consumerConfig() {
//        ConsumerConfig consumerConfig = new ConsumerConfig();
//        consumerConfig.setCheck(dubboConsumerProperties.getCheck());
//        consumerConfig.setTimeout(dubboConsumerProperties.getTimeout());
//        consumerConfig.setRetries(dubboConsumerProperties.getRetries());
//        consumerConfig.setLoadbalance(dubboConsumerProperties.getLoadbalance());
//        consumerConfig.setValidation(dubboConsumerProperties.getValidation());
//        consumerConfig.setGroup(dubboConsumerProperties.getGroup());
//        consumerConfig.setVersion(dubboConsumerProperties.getVersion());
//        return consumerConfig;
//    }
//
//    @Bean
//    public BeanPostProcessor beanPostProcessor() {
//        return new BeanPostProcessor() {
//            @Override
//            public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//                if (!INITED.get() && INITED.compareAndSet(false, true)) {
//                    log.info("begin init dubbo consumer bean...");
//                    List<DubboConsumerProperties.Reference> references = new ArrayList<>();
//                    List<DubboConsumerProperties.Reference> propertiesReferences = dubboConsumerProperties.getReferences();
//                    Optional.ofNullable(propertiesReferences).ifPresent(references::addAll);
//
//                    // set dubbo consumer bean
//                    for (DubboConsumerProperties.Reference reference : references) {
//                        // 如果已经存在，则跳过
//                        String beanId = reference.getBeanId();
//                        if (isContainInSpring(beanId)) {
//                            log.warn("consumer reference bean is exists, beanId=>" + beanId);
//                            continue;
//                        }
//                        // 注册到spring容器
//                        registry2Spring(beanId, referenceBean(reference));
//                    }
//                    log.info("init dubbo consumer bean success!");
//                }
//                return bean;
//            }
//
//            @Override
//            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//                return bean;
//            }
//        };
//    }
//
//    /**
//     * 指定beanName的bean是否已经注册到spring容器中
//     */
//    private boolean isContainInSpring(String beanName) {
//        return beanFactory.containsBean(beanName);
//    }
//
//    /**
//     * 注册远程服务到spring容器
//     */
//    private void registry2Spring(String beanId, Object o) {
//        beanFactory.registerSingleton(beanId, o);
//    }
//
//    /**
//     * 远程调用服务
//     */
//    private Object referenceBean(DubboConsumerProperties.Reference reference) {
//        ReferenceBean<Object> consumerBean = new ReferenceBean<>();
//        consumerBean.setInterface(reference.getInterfaceClass());
//        consumerBean.setId(reference.getBeanId());
//        consumerBean.setConsumer(consumerConfig());
//        consumerBean.setApplicationContext(applicationContext);
//        consumerBean.setApplication(applicationConfig);
//        consumerBean.setRegistry(registryConfig);
//        consumerBean.setMonitor(monitorConfig);
//
//        // 设置特殊属性
//        String group = reference.getGroup();
//        Optional.ofNullable(group).ifPresent(consumerBean::setGroup);
//        String version = reference.getVersion();
//        Optional.ofNullable(version).ifPresent(consumerBean::setVersion);
//
//        Object result;
//        try {
//            consumerBean.afterPropertiesSet();
//            result = consumerBean.getObject();
//        } catch (Exception e) {
//            log.error("远程引用服务失败.interface=>" +
//                    reference.getInterfaceClass().getName() + ",bean Id=>" + reference.getBeanId());
//            throw new IllegalStateException(e);
//        }
//        return result;
//    }
//
//}
