package com.ruoyi.flow.oa.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("逻辑运算")
@Data
public class ExpressionReq {
    @ApiModelProperty("逻辑符")
    private String operator;
    @ApiModelProperty("条件1")
    private String Where;
    @ApiModelProperty("逻辑符")
    private String operator2;
    @ApiModelProperty("条件2")
    private String toWhere;
}
