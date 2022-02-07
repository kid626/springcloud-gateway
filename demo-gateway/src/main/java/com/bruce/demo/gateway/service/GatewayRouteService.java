package com.bruce.demo.gateway.service;

import com.bruce.demo.gateway.model.dto.CustomRouteDefinitionDTO;

import java.util.List;

/**
 * @Copyright Copyright © 2022 Bruce . All rights reserved.
 * @Desc 网关-路由service 层
 * @ProjectName gateway
 * @Date 2022-1-26 15:57:14
 * @Author Bruce
 */
public interface GatewayRouteService {

    /**
     * 新增一条路由规则
     */
    long save(CustomRouteDefinitionDTO dto);

    /**
     * 获取所有
     */
    List<CustomRouteDefinitionDTO> queryAll();


    /**
     * 删除路由规则
     *
     * @param routeId 路由规则id
     */
    void remove(String routeId);

    /**
     * 更新
     */
    void update(CustomRouteDefinitionDTO dto);

}
