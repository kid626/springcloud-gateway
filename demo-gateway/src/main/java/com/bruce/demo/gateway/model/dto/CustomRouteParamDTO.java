package com.bruce.demo.gateway.model.dto;

import lombok.Data;

import java.util.Map;

/**
 * @Copyright Copyright © 2022 fanzh . All rights reserved.
 * @Desc
 * @ProjectName demo-server
 * @Date 2022/1/26 16:10
 * @Author fzh
 */
@Data
public class CustomRouteParamDTO {

    private String routeId;

    private String paramName;

    private Integer type;

    private Map<String, String> args;

}
