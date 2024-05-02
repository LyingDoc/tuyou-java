package com.ruoyi.flow.oa.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("沟通人员信息")
@Data
public class OperationUserInfoVO {
    @ApiModelProperty("用户编码")
    private String no;
    @ApiModelProperty("用户名称")
    private String name;
}
