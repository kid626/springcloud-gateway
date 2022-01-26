package com.bruce.demo.gateway.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bruce.demo.gateway.mapper.GatewayRouteMapper;
import com.bruce.demo.gateway.model.dto.CustomRouteDefinitionDTO;
import com.bruce.demo.gateway.model.enums.YesOrNoEnum;
import com.bruce.demo.gateway.model.po.GatewayRoute;
import com.bruce.demo.gateway.model.po.GatewayRouteParam;
import com.bruce.demo.gateway.service.GatewayRouteParamService;
import com.bruce.demo.gateway.service.GatewayRouteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Copyright Copyright © 2022 Bruce . All rights reserved.
 * @Desc 网关-路由service 实现类
 * @ProjectName gateway
 * @Date 2022-1-26 15:57:14
 * @Author Bruce
 */
@Service
public class GatewayRouteServiceImpl implements GatewayRouteService {

    @Autowired
    private GatewayRouteMapper mapper;

    @Autowired
    private GatewayRouteParamService gatewayRouteParamService;


    @Override
    public long save() {
        return 0;
    }

    @Override
    public List<CustomRouteDefinitionDTO> queryAll() {
        // TODO 一个 sql 完成
        QueryWrapper<GatewayRoute> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(GatewayRoute::getIsDelete, YesOrNoEnum.NO.getCode())
                .eq(GatewayRoute::getIsEnable, YesOrNoEnum.YES.getCode());
        List<GatewayRoute> list = mapper.selectList(wrapper);
        List<CustomRouteDefinitionDTO> result = new ArrayList<>();
        for (GatewayRoute gatewayRoute : list) {
            CustomRouteDefinitionDTO dto = new CustomRouteDefinitionDTO();
            BeanUtils.copyProperties(gatewayRoute, dto);
            List<GatewayRouteParam> gatewayRouteParamList = gatewayRouteParamService.getByRouteId(gatewayRoute.getRouteId());
            dto.setList(gatewayRouteParamList);
            result.add(dto);
        }
        return result;
    }
}
