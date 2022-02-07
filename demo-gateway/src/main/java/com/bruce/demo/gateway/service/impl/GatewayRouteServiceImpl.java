package com.bruce.demo.gateway.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.bruce.demo.gateway.mapper.GatewayRouteMapper;
import com.bruce.demo.gateway.model.dto.CustomRouteDefinitionDTO;
import com.bruce.demo.gateway.model.dto.CustomRouteParamDTO;
import com.bruce.demo.gateway.model.enums.YesOrNoEnum;
import com.bruce.demo.gateway.model.po.GatewayRoute;
import com.bruce.demo.gateway.model.po.GatewayRouteParam;
import com.bruce.demo.gateway.route.RouteDefinitionConverter;
import com.bruce.demo.gateway.service.GatewayRouteParamService;
import com.bruce.demo.gateway.service.GatewayRouteService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @Transactional(rollbackFor = Exception.class)
    public long save(CustomRouteDefinitionDTO dto) {
        // 新增网关-路由
        Date now = new Date();
        GatewayRoute gatewayRoute = new GatewayRoute();
        BeanUtils.copyProperties(dto, gatewayRoute);
        gatewayRoute.setCreateTime(now);
        gatewayRoute.setCreateUser("admin");
        gatewayRoute.setUpdateTime(now);
        gatewayRoute.setUpdateUser("admin");
        gatewayRoute.setIsEnable(YesOrNoEnum.YES.getCode());
        gatewayRoute.setIsDelete(YesOrNoEnum.NO.getCode());
        mapper.insert(gatewayRoute);
        List<CustomRouteParamDTO> list = dto.getList();
        List<GatewayRouteParam> gatewayRouteParamList = RouteDefinitionConverter.convertToGatewayRouteParamList(list);
        gatewayRouteParamService.batchSave(gatewayRouteParamList);
        // 批量新增参数
        return gatewayRoute.getId();
    }

    @Override
    public List<CustomRouteDefinitionDTO> queryAll() {
        List<CustomRouteDefinitionDTO> all = mapper.queryAll();
        for (CustomRouteDefinitionDTO dto : all) {
            List<CustomRouteParamDTO> list = dto.getList();
            if (CollectionUtils.isNotEmpty(list)) {
                // 根据 name 合并
                Map<String, List<CustomRouteParamDTO>> map = list.stream().collect(Collectors.groupingBy(CustomRouteParamDTO::getParamName));
                List<CustomRouteParamDTO> paramDTOList = new ArrayList<>();
                for (String key : map.keySet()) {
                    List<CustomRouteParamDTO> valueList = map.get(key);
                    CustomRouteParamDTO customRouteParamDTO = RouteDefinitionConverter.convertToCustomRouteParamDTO(valueList);
                    paramDTOList.add(customRouteParamDTO);
                }
                dto.setList(paramDTOList);
            }
        }
        return all;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(String routeId) {
        // 先删除参数列表
        gatewayRouteParamService.removeByRouteId(routeId);
        // 再删网关-路由表
        UpdateWrapper<GatewayRoute> wrapper = new UpdateWrapper<>();
        wrapper.lambda().eq(GatewayRoute::getIsDelete, YesOrNoEnum.NO.getCode())
                .eq(GatewayRoute::getRouteId, routeId);
        GatewayRoute gatewayRoute = new GatewayRoute();
        gatewayRoute.setIsDelete(YesOrNoEnum.YES.getCode());
        gatewayRoute.setUpdateUser("admin");
        gatewayRoute.setUpdateTime(new Date());
        mapper.update(gatewayRoute, wrapper);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(CustomRouteDefinitionDTO dto) {
        // 更新网关-路由
        GatewayRoute gatewayRoute = new GatewayRoute();
        BeanUtils.copyProperties(dto, gatewayRoute);
        gatewayRoute.setUpdateTime(new Date());
        mapper.updateById(gatewayRoute);

        // 采取先删后新增的方式
        gatewayRouteParamService.removeByRouteId(dto.getRouteId());
        List<CustomRouteParamDTO> list = dto.getList();
        List<GatewayRouteParam> gatewayRouteParamList = RouteDefinitionConverter.convertToGatewayRouteParamList(list);

        gatewayRouteParamService.batchSave(gatewayRouteParamList);
    }
}
