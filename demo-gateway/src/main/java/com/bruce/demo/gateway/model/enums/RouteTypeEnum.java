package com.bruce.demo.gateway.model.enums;

/**
 * @Copyright Copyright © 2022 fanzh . All rights reserved.
 * @Desc
 * @ProjectName demo-server
 * @Date 2022/1/26 16:20
 * @Author fzh
 */
public enum RouteTypeEnum {

    PREDICATE(1,"Predicate"),
    FILTER(2,"Filter");

    private Integer code;

    private String name;

    RouteTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
