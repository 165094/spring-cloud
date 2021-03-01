package com.test.aop.develop.config;

public class ConfigService {

    private ConfigProperties configProperties;

    public ConfigService(ConfigProperties configProperties) {
        this.configProperties = configProperties;
    }

    public void demo(){
        System.out.println(configProperties.getPrefix() + "-" + configProperties.getSuffix());
        System.out.println("starter配置成功！");
    }
}
