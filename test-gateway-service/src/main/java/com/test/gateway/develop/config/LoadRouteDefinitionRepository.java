package com.test.gateway.develop.config;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoadRouteDefinitionRepository implements RouteDefinitionRepository, ApplicationEventPublisherAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoadRouteDefinitionRepository.class);
    private ApplicationEventPublisher publisher;
    private List<RouteDefinition> routeDefinitionList = new ArrayList<>();

    @Value("${gateway.route.config.file}")
    private String file;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @PostConstruct
    public void init() {
        load();
    }

    /**
     * 监听事件刷新配置
     */
    @EventListener
    public void listenEvent() {
        load();
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
    }

    /**
     * 加载
     */
    private void load() {
        try {
            String jsonStr = Files.lines(Paths.get(file)).collect(Collectors.joining());
            routeDefinitionList = JSON.parseArray(jsonStr, RouteDefinition.class);
            LOGGER.info("路由配置已加载,加载条数:{}", routeDefinitionList.size());
        } catch (Exception e) {
            LOGGER.error("从文件加载路由配置异常", e);
        }
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return Mono.defer(() -> Mono.error(new NotFoundException("Unsupported operation")));
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return Mono.defer(() -> Mono.error(new NotFoundException("Unsupported operation")));
    }

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        return Flux.fromIterable(routeDefinitionList);
    }
}
