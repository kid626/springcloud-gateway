package com.bruce.demo.gateway.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bruce.demo.gateway.model.dto.CustomRouteDefinitionDTO;
import com.bruce.demo.gateway.model.po.GatewayRoute;

import java.util.List;

/**
 * @Copyright Copyright © 2022 Bruce . All rights reserved.
 * @Desc 网关-路由mapper 层
 * @ProjectName gateway
 * @Date 2022-1-26 15:57:14
 * @Author Bruce
 */
public interface GatewayRouteMapper extends BaseMapper<GatewayRoute> {

    /**
     * 查询全部
     */
    List<CustomRouteDefinitionDTO> queryAll();

}
