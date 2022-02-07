package com.bruce.demo.gateway.model.po;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @Copyright Copyright © 2022 Bruce . All rights reserved.
 * @Desc 网关-路由参数表实体类
 * @ProjectName gateway
 * @Date 2022-1-26 15:57:14
 * @Author Bruce
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("gateway_route_param")
@ApiModel(value = "GatewayRouteParam对象", description = "网关-路由参数表")
public class GatewayRouteParam implements Serializable {

    private static final long serialVersionUID = -1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "路由id")
    private String routeId;

    @ApiModelProperty(value = "参数名称")
    private String paramName;

    @ApiModelProperty(value = "参数key")
    private String paramKey;

    @ApiModelProperty(value = "参数value")
    private String paramValue;

    @ApiModelProperty(value = "1 为predicate，2为 filter")
    private Integer type;

    @ApiModelProperty(value = "是否生效")
    private String isEnable;

    @ApiModelProperty(value = "创建用户")
    private String createUser;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新用户")
    private String updateUser;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "是否删除")
    private String isDelete;


}