package com.bruce.demo.gateway.route;

import com.bruce.demo.gateway.service.GatewayRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Copyright Copyright © 2022 fanzh . All rights reserved.
 * @Desc
 * @ProjectName demo-server
 * @Date 2022/1/26 15:48
 * @Author fzh
 */
@Component
public class DynamicRouteDefinitionRepository implements RouteDefinitionRepository, ApplicationEventPublisherAware {

    @Autowired
    private GatewayRouteService gatewayRouteService;

    private ApplicationEventPublisher eventPublisher;

    private Map<String, RouteDefinition> cache = new ConcurrentHashMap<>();

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        return Flux.fromIterable(cache.values());
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        // TODO 调用 api 接口来新增
        return null;
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return null;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    /**
     * 将指定路由加入到缓存中。
     */
    public void addCache(RouteDefinition route) {
        this.cache.putIfAbsent(route.getId(), route);
        this.publishEvent();
    }

    /**
     * 将指定路由从缓存中删除。
     */
    public void removeCache(String routeId) {
        if (this.cache.remove(routeId) != null) {
            this.publishEvent();
        }
    }

    public void publishEvent() {
        eventPublisher.publishEvent(new RefreshRoutesEvent(this));
    }

    public Map<String, RouteDefinition> getCache() {
        return cache;
    }

    public void setCache(Map<String, RouteDefinition> cache) {
        this.cache = cache;
    }

    public void clearCache() {
        this.cache.clear();
    }


}
