package com.test.gateway.develop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DynamicRouteService implements ApplicationEventPublisherAware {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RouteDefinitionWriter routeDefinitionWriter;

    private ApplicationEventPublisher publisher;


    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    public String add(RouteDefinition definition){
        try {
            routeDefinitionWriter.save(Mono.just(definition)).subscribe();
            this.publisher.publishEvent(new RefreshRoutesEvent(this));
            logger.info("添加路由成功");
            return "success";
        } catch (Exception e) {
            logger.error("添加路由失败：", e);
            return "update route  fail";
        }
    }

    //更新/新增路由
    public String update(RouteDefinition definition) {
        try {
            delete(definition.getId());
            logger.info("删除成功：{}", definition.getId());
        } catch (Exception e) {
            logger.warn("errorMsg:", e);
            logger.info("删除失败，路由信息不存在：{}", definition.getId());
        }
        return add(definition);
    }

    //删除路由
    public Mono<ResponseEntity<Object>> delete(String id) {
        return this.routeDefinitionWriter.delete(Mono.just(id)).then(Mono.defer(() -> {
            return Mono.just(ResponseEntity.ok().build());
        })).onErrorResume((t) -> {
            return t instanceof NotFoundException;
        }, (t) -> {
            return Mono.just(ResponseEntity.notFound().build());
        });
    }
}
