package com.ruoyi.flow.oa.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("节点按钮操作权限")
@Data
public class OperatePermissionReq {
    @ApiModelProperty("是否显示")
    private boolean  IsVisible;
    @ApiModelProperty("按钮默认名称")
    private String  OperateName;
    @ApiModelProperty("按钮类别")
    private String  OperateType;
    @ApiModelProperty("操作按钮显示名称")
    private String   ShowName;
}
