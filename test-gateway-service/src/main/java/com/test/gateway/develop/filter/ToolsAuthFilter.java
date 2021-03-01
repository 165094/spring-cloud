package com.test.gateway.develop.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Configuration
public class ToolsAuthFilter implements GlobalFilter , Ordered {

    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public int getOrder() {
        return -300;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String accessToken = extractToken(request);

        if(!pathMatcher.match("/**/uaa/**",exchange.getRequest().getPath().value())){
            if (accessToken == null) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }else{
                String key = "access_token:" + accessToken;
                String token =  (String) redisTemplate.opsForValue().get(key) ;
                if(token == null){
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                }
            }
        }
        return chain.filter(exchange);
    }

    protected String extractToken(ServerHttpRequest request) {
        List<String> strings = request.getHeaders().get("Authorization");
        String authToken = null;
        if (strings != null) {
            authToken = strings.get(0).substring("Bearer".length()).trim();
        }

        if (StringUtils.isBlank(authToken)) {
            strings = request.getQueryParams().get("access_token");
            if (strings != null) {
                authToken = strings.get(0);
            }
        }

        return authToken;


    }
}
