package com.ruoyi.flow.oa.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("处理异常流程日志")
@Data
public class HandleErrorProcessVO {
    @ApiModelProperty("流程日志Id")
    private String porcessinsLogId;
    @ApiModelProperty("提交表单内容")
    private String fromdata;
    @ApiModelProperty("操作信息")
    private String operationInfo;
    @ApiModelProperty("动作名称")
    private String actionName;
    @ApiModelProperty("动作类别")
    private String actionType;

}
