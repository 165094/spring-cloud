//package com.test.gateway.develop.config;
//
//import com.test.gateway.develop.Exception.WebResposeExceptionTranslator;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
//import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
//import org.springframework.security.web.AuthenticationEntryPoint;
//
//public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
//
//    private static final String DEMO_RESOURCE_ID = "demo";
//
////    @Autowired
////    RedisConnectionFactory redisConnectionFactory;
////
////    @Bean
////    public TokenStore redisTokenStore() {
////        return new RedisTokenStore(redisConnectionFactory);
////        //JWT token 签名加密
//////        return new JwtTokenStore(accessTokenConverter());
////    }
//
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        resources
//                .resourceId(DEMO_RESOURCE_ID)
//                .authenticationEntryPoint(authenticationEntryPoint())
//                .stateless(true);
//    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .requestMatchers().antMatchers("/**")
//                .and()
//                .authorizeRequests()
//                .antMatchers("/**/uaa/**").permitAll()
//                .anyRequest().authenticated();
//    }
//
//    @Bean
//    public WebResponseExceptionTranslator<?> exceptionTranslator() {
//        return new WebResposeExceptionTranslator();
//    }
//
//    @Bean
//    public AuthenticationEntryPoint authenticationEntryPoint() {
//        final OAuth2AuthenticationEntryPoint entryPoint = new OAuth2AuthenticationEntryPoint();
//        entryPoint.setExceptionTranslator(exceptionTranslator());
//        return entryPoint;
//    }
//}
