package com.bruce.demo.gateway.route;

import com.bruce.demo.gateway.model.dto.CustomRouteDefinitionDTO;
import com.bruce.demo.gateway.model.dto.CustomRouteParamDTO;
import com.bruce.demo.gateway.model.enums.RouteTypeEnum;
import com.bruce.demo.gateway.model.po.GatewayRouteParam;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Copyright Copyright © 2022 fanzh . All rights reserved.
 * @Desc
 * @ProjectName demo-server
 * @Date 2022/1/26 16:12
 * @Author fzh
 */
public class RouteDefinitionConverter {

    public static CustomRouteDefinitionDTO convertToDTO(RouteDefinition routeDefinition) {
        CustomRouteDefinitionDTO dto = new CustomRouteDefinitionDTO();
        dto.setRouteId(routeDefinition.getId());
        dto.setRouteOrder(routeDefinition.getOrder());
        dto.setUri(routeDefinition.getUri().toASCIIString());
        List<FilterDefinition> filters = routeDefinition.getFilters();
        List<PredicateDefinition> predicates = routeDefinition.getPredicates();
        List<CustomRouteParamDTO> list = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(filters)) {
            for (FilterDefinition filter : filters) {
                CustomRouteParamDTO customRouteParamDTO = new CustomRouteParamDTO();
                customRouteParamDTO.setRouteId(routeDefinition.getId());
                customRouteParamDTO.setParamName(filter.getName());
                customRouteParamDTO.setType(RouteTypeEnum.FILTER.getCode());
                customRouteParamDTO.setArgs(filter.getArgs());
                list.add(customRouteParamDTO);
            }
        }
        if (CollectionUtils.isNotEmpty(predicates)) {
            for (PredicateDefinition predicate : predicates) {
                CustomRouteParamDTO customRouteParamDTO = new CustomRouteParamDTO();
                customRouteParamDTO.setRouteId(routeDefinition.getId());
                customRouteParamDTO.setParamName(predicate.getName());
                customRouteParamDTO.setType(RouteTypeEnum.PREDICATE.getCode());
                customRouteParamDTO.setArgs(predicate.getArgs());
                list.add(customRouteParamDTO);
            }
        }
        dto.setList(list);
        return dto;
    }

    public static RouteDefinition convertToRouteDefinition(CustomRouteDefinitionDTO dto) {
        RouteDefinition routeDefinition = new RouteDefinition();
        routeDefinition.setId(dto.getRouteId());
        routeDefinition.setOrder(dto.getRouteOrder());
        routeDefinition.setUri(URI.create(dto.getUri()));
        List<CustomRouteParamDTO> routeParams = dto.getList();
        if (CollectionUtils.isNotEmpty(routeParams)) {
            List<PredicateDefinition> predicates = routeParams.stream()
                    .filter(params -> RouteTypeEnum.PREDICATE.getCode().equals(params.getType()))
                    .map(RouteDefinitionConverter::convertToPredicateDefinition)
                    .collect(Collectors.toList());
            List<FilterDefinition> filters = routeParams.stream().
                    filter(params -> RouteTypeEnum.FILTER.getCode().equals(params.getType()))
                    .map(RouteDefinitionConverter::convertToFilterDefinition)
                    .collect(Collectors.toList());
            routeDefinition.setPredicates(predicates);
            routeDefinition.setFilters(filters);
        }
        return routeDefinition;
    }

    public static PredicateDefinition convertToPredicateDefinition(CustomRouteParamDTO param) {
        PredicateDefinition predicateDefinition = new PredicateDefinition();
        predicateDefinition.setName(param.getParamName());
        Map<String, String> args = param.getArgs();
        if (!(args == null || args.isEmpty())) {
            predicateDefinition.setArgs(args);
        }
        return predicateDefinition;
    }

    public static FilterDefinition convertToFilterDefinition(CustomRouteParamDTO param) {
        FilterDefinition filterDefinition = new FilterDefinition();
        filterDefinition.setName(param.getParamName());
        Map<String, String> args = param.getArgs();
        if (!(args == null || args.isEmpty())) {
            filterDefinition.setArgs(args);
        }
        return filterDefinition;
    }


    public static CustomRouteParamDTO convertToCustomRouteParamDTO(List<CustomRouteParamDTO> list) {
        CustomRouteParamDTO baseParam = list.get(0);
        CustomRouteParamDTO dto = new CustomRouteParamDTO();
        BeanUtils.copyProperties(baseParam, dto);
        Map<String, String> args = new HashMap<>(2);
        for (CustomRouteParamDTO customRouteParamDTO : list) {
            args.put(customRouteParamDTO.getParamKey(), customRouteParamDTO.getParamValue());
        }
        dto.setArgs(args);
        return dto;
    }

    public static List<GatewayRouteParam> convertToGatewayRouteParamList(List<CustomRouteParamDTO> list) {
        List<GatewayRouteParam> result = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (CustomRouteParamDTO dto : list) {
                Map<String, String> args = dto.getArgs();
                if (!(args == null || args.isEmpty())) {
                    for (String key : args.keySet()) {
                        GatewayRouteParam gatewayRouteParam = new GatewayRouteParam();
                        gatewayRouteParam.setType(dto.getType());
                        gatewayRouteParam.setRouteId(dto.getRouteId());
                        gatewayRouteParam.setParamName(dto.getParamName());
                        gatewayRouteParam.setParamKey(key);
                        gatewayRouteParam.setParamValue(args.get(key));
                        result.add(gatewayRouteParam);
                    }
                }
            }
        }
        return result;
    }

}
