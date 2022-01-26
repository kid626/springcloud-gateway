package com.bruce.demo.gateway.route;

import com.bruce.demo.gateway.model.dto.CustomRouteDefinitionDTO;
import com.bruce.demo.gateway.model.enums.RouteTypeEnum;
import com.bruce.demo.gateway.model.po.GatewayRouteParam;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;

import java.net.URI;
import java.util.List;
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
        return dto;
    }

    public static RouteDefinition convertToRouteDefinition(CustomRouteDefinitionDTO dto) {
        RouteDefinition routeDefinition = new RouteDefinition();
        routeDefinition.setId(dto.getRouteId());
        routeDefinition.setOrder(dto.getRouteOrder());
        routeDefinition.setUri(URI.create(dto.getUri()));
        List<GatewayRouteParam> routeParams = dto.getList();
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

    public static PredicateDefinition convertToPredicateDefinition(GatewayRouteParam param) {
        PredicateDefinition predicateDefinition = new PredicateDefinition();
        // TODO name 和 key/value 对 一对多的情况
        predicateDefinition.setName(param.getParamName());
        predicateDefinition.addArg(param.getParamKey(), param.getParamValue());
        return predicateDefinition;
    }

    public static FilterDefinition convertToFilterDefinition(GatewayRouteParam param) {
        FilterDefinition filterDefinition = new FilterDefinition();
        // TODO name 和 key/value 对 一对多的情况
        filterDefinition.setName(param.getParamName());
        filterDefinition.addArg(param.getParamKey(), param.getParamValue());
        return filterDefinition;
    }

}
