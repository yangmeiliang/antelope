package com.lmy.antelope.util;

import com.lmy.antelope.support.SpringContextHolder;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author yangmeiliang
 * @date 2018/8/15
 */
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext = SpringContextHolder.getApplicationContext();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }
}
