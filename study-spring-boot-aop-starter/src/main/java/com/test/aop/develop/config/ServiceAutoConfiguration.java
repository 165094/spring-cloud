package com.test.aop.develop.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ConfigProperties.class)
@ConditionalOnProperty(prefix = "info", value = "enabled", matchIfMissing = true)
public class ServiceAutoConfiguration {

    @Autowired
    private ConfigProperties configProperties;

    public ConfigService getConfigService(){
        ConfigService configService = new ConfigService(configProperties);
        return configService;
    }
}
