package com.bruce.demo.gateway.route;

import com.bruce.demo.gateway.model.dto.CustomRouteDefinitionDTO;
import com.bruce.demo.gateway.service.GatewayRouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Copyright Copyright © 2022 fanzh . All rights reserved.
 * @Desc
 * @ProjectName demo-server
 * @Date 2022/1/26 16:47
 * @Author fzh
 */
@Slf4j
@Component
@ConditionalOnProperty(value = "route.schedule.enabled", havingValue = "true", matchIfMissing = true)
public class RouteRefresher {


    @Autowired
    private DynamicRouteDefinitionRepository repository;

    @Autowired
    private GatewayRouteService gatewayRouteService;


    /**
     * 固定间隔重新加载一次缓存
     */
    @Scheduled(initialDelay = 10000, fixedDelay = 60 * 60 * 1001)
    private void refresh() {
        repository.clearCache();
        Map<String, RouteDefinition> cache = new ConcurrentHashMap<>();
        List<CustomRouteDefinitionDTO> dtoList = gatewayRouteService.queryAll();
        for (CustomRouteDefinitionDTO dto : dtoList) {
            cache.put(dto.getRouteId(),RouteDefinitionConverter.convertToRouteDefinition(dto));
        }
        repository.setCache(cache);
        log.info("新路由表加载完成，当前大小为：{}", cache.size());
        repository.publishEvent();
    }


}
