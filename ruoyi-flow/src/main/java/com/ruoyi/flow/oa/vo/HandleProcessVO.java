package com.ruoyi.flow.oa.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.sf.json.JSONObject;

@ApiModel("流程处理对象")
@Data
public class HandleProcessVO {
    @ApiModelProperty("节点ID")
    private String actID;
    @ApiModelProperty("节点实例ID")
    private String actInsID;
    @ApiModelProperty("上一节点名称")
    private String prevActName;
    @ApiModelProperty("流程模板ID")
    private String procID;
    @ApiModelProperty("流程实例ID")
    private String procInsID;
    @ApiModelProperty("动作名称")
    private String actionName;
    @ApiModelProperty("动作类型")
    private String actionType;
    @ApiModelProperty("表单信息")
    private JSONObject fromdata;
    @ApiModelProperty("流程名称")
    private String procname;
    @ApiModelProperty("操作信息")
    private OperationInfoVO operationInfo;
    @ApiModelProperty("当前处理人")
    private String currentUserCode;
    @ApiModelProperty("下一节点Id")
    private String nextactid;
    @ApiModelProperty("下一节点名称")
    private String nextactname;
    @ApiModelProperty("下一节点图形ID")
    private String nextactchartid;
    @ApiModelProperty("是否流程结束")
    private Boolean flowEnd;
    @ApiModelProperty("是否处理完成")
    private Boolean handled;
    @ApiModelProperty("回调url")
    private String apiUrl;
    @ApiModelProperty("回调更改sql")
    private String sqlcontent;
    @ApiModelProperty("处理方式")
    private String handleType;


}
