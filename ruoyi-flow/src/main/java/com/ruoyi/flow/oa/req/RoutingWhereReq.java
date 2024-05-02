package com.ruoyi.flow.oa.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("路由条件信息")
@Data
public class RoutingWhereReq {
    @ApiModelProperty("变量名称")
    private String variableName;
    @ApiModelProperty("变量")
    private String variable;
    @ApiModelProperty("逻辑符名称")
    private String operatorName;
    @ApiModelProperty("逻辑符")
    private String operator;
    @ApiModelProperty("值")
    private String value;
    @ApiModelProperty("备注信息")
    private String description;
}
