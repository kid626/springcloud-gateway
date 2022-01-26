package com.bruce.demo.gateway.model.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * @Copyright Copyright © 2021 fanzh . All rights reserved.
 * @Desc
 * @ProjectName security
 * @Date 2021/12/27 14:10
 * @Author fzh
 */
public enum YesOrNoEnum {
    /**
     * 代表是否的枚举类
     */
    YES("Y", "是"),
    NO("N", "否");

    YesOrNoEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    private String code;
    private String name;

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public static final Map<String, String> LOOKUP = new HashMap<String, String>();

    static {
        for (YesOrNoEnum s : EnumSet.allOf(YesOrNoEnum.class)) {
            LOOKUP.put(s.getCode(), s.getName());
        }
    }

    @Override
    public String toString() {
        return this.getCode();
    }
}
