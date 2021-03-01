//package com.test.gateway.develop.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//
//import java.util.Arrays;
//import java.util.List;
//
//@Configuration
//public class CorsConfig {
//    @Bean
//    public CorsFilter corsFilter() {
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", buildConfig());
//        return new CorsFilter(source);
//    }
//
//    private CorsConfiguration buildConfig() {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        // 1允许任何域名使用
//        corsConfiguration.addAllowedOrigin("*");
//        List<String> heander = Arrays.asList("access-control-allow-headers",
//                "access-control-allow-methods",
//                "access-control-allow-origin",
//                "access-control-max-age",
//                "X-Frame-Options",
//                "token", "Host",
//                "Content-Type", "Server",
//                "content-disposition",
//                "Cookie");
//        corsConfiguration.setExposedHeaders(heander);
//        // 2允许任何头
//        corsConfiguration.addAllowedHeader("*");
//        // 3允许任何方法（post、get等）
//        corsConfiguration.addAllowedMethod("*");
//        corsConfiguration.setMaxAge(3600L);
//        return corsConfiguration;
//    }
//}
