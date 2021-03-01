package com.test.aop.develop.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "info")
@Data
public class ConfigProperties {

    /**
     * 前缀
     */
    private String prefix;

    /**
     * 后缀
     */
    private String suffix;

}
