package com.ruoyi.flow.oa.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.sf.json.JSONObject;

import java.util.List;

@ApiModel("节点详细信息")
@Data
public class NodeDetailInfoReq {
    @ApiModelProperty("节点图形ID")
    private String FActChartID;
    @ApiModelProperty("催办提醒(到达本节点后多少天进行提醒)")
    private String FAlertDays;
    @ApiModelProperty("催办频率  天/次")
    private String FAlertFrequency;
    @ApiModelProperty("审批对象")
    private String FApprovalObj;
    @ApiModelProperty("审批对象类型 1固定角色 2业务ID")
    private String FApprovalType;
    @ApiModelProperty("审批对象")
    private String FApprover;
    @ApiModelProperty("审批对象 显示名称 ")
    private String FApproverName;
    @ApiModelProperty("抄送人员")
    private String FCCPerson;
    @ApiModelProperty("会签方式 1并行会签 2 串行会签")
    private String FCounterSignType;
    @ApiModelProperty("会签规则 1 所有审批通过 2 多数审批通过 3任意一个审批通过")
    private String FHandleCountersignType;
    @ApiModelProperty("会签规则  显示名称")
    private String FHandleCountersignTypeName;
    @ApiModelProperty("处理方式 1节点审批处理 2节点会签处理")
    private String FHandleType;
    @ApiModelProperty("处理方式名称")
    private String FHandleTypeName;
    @ApiModelProperty("连续自动跳过")
    private Boolean FIsContinuousBy;
    @ApiModelProperty("是否启动邮件通知")
    private Boolean FIsEmail;
    @ApiModelProperty("非连续自动跳过")
    private Boolean FIsNotContinuousBy;
    @ApiModelProperty("驳回邮件通知")
    private Boolean FIsRejectEmail;
    @ApiModelProperty("是否可驳回历史")
    private Boolean FIsRejectHistory;
    @ApiModelProperty("当前节点名称")
    private String FNodeName;
    @ApiModelProperty("附件权限")
    private List<AttachmentPermissionReq> AttachmentPermission;
    @ApiModelProperty("节点权限")
    private JSONObject DataPermission;
    @ApiModelProperty("节点按钮权限")
    private List<OperatePermissionReq> OperatePermission;
    @ApiModelProperty("节点路由")
    private List<RoutingReq> Routing;
}
