package com.ruoyi.flow.oa.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("加载流程表单 请求参数")
@Data
public class LoadProcessFromInfoReq {
    ///流程模板ID
    @ApiModelProperty("流程模板ID")
    private String processid;
    ///流程实例ID
    @ApiModelProperty("流程实例ID")
    private String processinsid;
    ///节点实例ID
    @ApiModelProperty("节点实例ID")
    private String actinsid;
    ///查看设备
    @ApiModelProperty("查看设备")
    private String viewmodel;
    ///流程BId
    @ApiModelProperty("流程BId")
    private String processBid;

    ///流程BId
    @ApiModelProperty("流程编码")
    private String processCode;
}
