package com.ruoyi.flow.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("接口管理展示")
public class SelectPageOrApiVO {
    @ApiModelProperty("id")
    private  String fid;
    @ApiModelProperty("包名称")
    private String pageName;
    @ApiModelProperty("备注描述")
    private String description;
    @ApiModelProperty("父级ID")
    private String parentId;
    @ApiModelProperty("类型(page包,api接口)")
    private String typeName;
    @ApiModelProperty("回调方法")
    private String callMethodCode;
}
