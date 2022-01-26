package com.bruce.demo.gateway.service;

import com.bruce.demo.gateway.model.po.GatewayRouteParam;

import java.util.List;

/**
 * @Copyright Copyright © 2022 Bruce . All rights reserved.
 * @Desc 网关-路由参数表service 层
 * @ProjectName gateway
 * @Date 2022-1-26 15:57:14
 * @Author Bruce
 */
public interface GatewayRouteParamService {

    /**
     * 根据路由id获取
     */
    List<GatewayRouteParam> getByRouteId(String routeId);

}
