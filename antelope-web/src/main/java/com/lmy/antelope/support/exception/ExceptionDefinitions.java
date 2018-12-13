package com.lmy.antelope.support.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Properties;

/**
 * @author yangmeiliang
 * @date 2018/6/25
 */
@Getter
@Setter
@Configuration
@AllArgsConstructor
public class ExceptionDefinitions {

    private ResourcePatternResolver resourcePatternResolver;

    private static Properties exceptionDefinitionProps;

    public String valueOf(String errorCode) {
        Object msgObj = exceptionDefinitionProps.get(errorCode);
        if (msgObj == null) {
            return errorCode;
        } else {
            return (String) msgObj;
        }
    }

    @PostConstruct
    public void init() throws IOException {
        if (exceptionDefinitionProps == null) {
            Resource[] resources = resourcePatternResolver.getResources("classpath*:/props/error.properties");
            exceptionDefinitionProps = new Properties();
            Arrays.stream(resources).forEach(resource -> {
                InputStreamReader reader = null;
                try {
                    reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);
                    exceptionDefinitionProps.load(reader);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    IOUtils.closeQuietly(reader);
                }

            });
        }


    }
}
