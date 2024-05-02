package com.ruoyi.flow.oa.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("附件权限信息")
@Data
public class AttachmentPermissionReq {
    @ApiModelProperty("是否显示")
    private boolean IsVisible;
    @ApiModelProperty("操作名称")
    private String OperateName;
    @ApiModelProperty("操作类别")
    private String OperateType;
}
