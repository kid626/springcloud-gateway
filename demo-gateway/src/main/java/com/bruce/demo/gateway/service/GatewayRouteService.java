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

    long save();

    /**
     * 获取所有
     */
    List<CustomRouteDefinitionDTO> queryAll();

}
