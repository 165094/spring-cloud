package com.test.auth.config;

import com.test.auth.exception.WebResposeExceptionTranslator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private static final String DEMO_RESOURCE_ID = "demo";

    @Override
    public void configure(HttpSecurity http) throws Exception {
//        http
//            .csrf().disable()
//            .authorizeRequests()
//            .antMatchers("/login").permitAll()
//            .anyRequest()
//            .authenticated();

        http.csrf().disable()
            .requestMatchers().anyRequest()
            .and()
            .authorizeRequests()
            .antMatchers("/login").permitAll()
            .anyRequest().authenticated();
//            .antMatchers("/order/**").authenticated()     //配置order访问控制，必须认证过后才可以访问
//            .authorizeRequests()
//            .antMatchers("/order/**").hasAuthority("admin_role")
//            .antMatchers("/test").authenticated();//配置访问控制，必须具有admin_role权限才可以访问资源

    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
                .resourceId(DEMO_RESOURCE_ID)
                .authenticationEntryPoint(authenticationEntryPoint())
                .stateless(true);
    }

    @Bean
    public WebResponseExceptionTranslator<?> exceptionTranslator() {
        return new WebResposeExceptionTranslator();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        final OAuth2AuthenticationEntryPoint entryPoint = new OAuth2AuthenticationEntryPoint();
        entryPoint.setExceptionTranslator(exceptionTranslator());
        return entryPoint;
    }

}
