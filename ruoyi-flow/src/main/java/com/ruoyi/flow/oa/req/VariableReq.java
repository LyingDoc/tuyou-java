package com.ruoyi.flow.oa.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("流程变量信息")
@Data
public class VariableReq {
    @ApiModelProperty("变量字段名称")
    private String name;
    @ApiModelProperty("变量名称")
    private String title;
    @ApiModelProperty("子变量 ")
    private List<VariableReq> Children;
    @ApiModelProperty("是否明晰")
    private String IsDetail;
}
