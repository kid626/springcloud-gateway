package com.bruce.demo.gateway.model.dto;

import com.bruce.demo.gateway.model.po.GatewayRoute;
import com.bruce.demo.gateway.model.po.GatewayRouteParam;
import lombok.Data;

import java.util.List;

/**
 * @Copyright Copyright © 2022 fanzh . All rights reserved.
 * @Desc
 * @ProjectName demo-server
 * @Date 2022/1/26 16:10
 * @Author fzh
 */
@Data
public class CustomRouteDefinitionDTO extends GatewayRoute {

    private List<GatewayRouteParam> list;

}
