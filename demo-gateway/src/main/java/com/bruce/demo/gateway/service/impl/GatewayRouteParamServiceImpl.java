package com.bruce.demo.gateway.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.bruce.demo.gateway.mapper.GatewayRouteParamMapper;
import com.bruce.demo.gateway.model.enums.YesOrNoEnum;
import com.bruce.demo.gateway.model.po.GatewayRouteParam;
import com.bruce.demo.gateway.service.GatewayRouteParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Copyright Copyright © 2022 Bruce . All rights reserved.
 * @Desc 网关-路由参数表service 实现类
 * @ProjectName gateway
 * @Date 2022-1-26 15:57:14
 * @Author Bruce
 */
@Service
public class GatewayRouteParamServiceImpl implements GatewayRouteParamService {

    @Autowired
    private GatewayRouteParamMapper mapper;

    @Override
    public List<GatewayRouteParam> getByRouteId(String routeId) {
        QueryWrapper<GatewayRouteParam> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(GatewayRouteParam::getIsDelete, YesOrNoEnum.NO.getCode())
                .eq(GatewayRouteParam::getIsEnable, YesOrNoEnum.YES.getCode())
                .eq(GatewayRouteParam::getRouteId, routeId);
        return mapper.selectList(wrapper);
    }

    @Override
    public void removeByRouteId(String routeId) {
        UpdateWrapper<GatewayRouteParam> wrapper = new UpdateWrapper<>();
        wrapper.lambda().eq(GatewayRouteParam::getIsDelete, YesOrNoEnum.NO.getCode())
                .eq(GatewayRouteParam::getRouteId, routeId);
        GatewayRouteParam gatewayRouteParam = new GatewayRouteParam();
        gatewayRouteParam.setIsDelete(YesOrNoEnum.YES.getCode());
        gatewayRouteParam.setUpdateUser("admin");
        gatewayRouteParam.setUpdateTime(new Date());
        mapper.update(gatewayRouteParam, wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchSave(List<GatewayRouteParam> list) {
        mapper.insertBatch(list);
    }
}
