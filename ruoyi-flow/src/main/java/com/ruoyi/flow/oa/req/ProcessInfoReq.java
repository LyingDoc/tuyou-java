package com.ruoyi.flow.oa.req;

import com.ruoyi.flow.oa.vo.TempNoticeVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import net.sf.json.JSONObject;

import java.util.List;

@ApiModel("流程模板信息")
public class ProcessInfoReq {
    @ApiModelProperty("流水号模板iD")
    private String BusinessModuleID;
    @ApiModelProperty("流水号前缀")
    private String BusinessMoudle;
    @ApiModelProperty("是否结束通知申请人")
    private Boolean FIsAlertStarter;
    @ApiModelProperty("是否可复制草稿")
    private Boolean FIsCopyStart;
    @ApiModelProperty("是否开启催办提醒")
    private Boolean FIsEnabledAlert;
    @ApiModelProperty("消息提醒方式")
    private String FMessageType;
    @ApiModelProperty("流程可阅人")
    private String FReadPerson;
    @ApiModelProperty("流程可阅人名称")
    private String FReadPersonName;
    @ApiModelProperty("流程说明")
    private String FRemark;
    @ApiModelProperty("流程发起人")
    private String FStartPerson;
    @ApiModelProperty("流程发起人名称")
    private String FStartPersonName;
    @ApiModelProperty("发起人类型")
    private String FStartType;
    @ApiModelProperty("流程编码")
    private String FlowCode;
    @ApiModelProperty("流程名称")
    private String FlowName;
    @ApiModelProperty("流程ID")
    private String FlowID;
    @ApiModelProperty("流程类型")
    private String FlowType;
    @ApiModelProperty("流程类型名称")
    private String FlowTypeName;
    @ApiModelProperty("流程图标")
    private String flowicon;
    @ApiModelProperty("表单名称")
    private String fromname;
    @ApiModelProperty("表单ID")
    private String FromID;
    @ApiModelProperty("默认回调地址")
    private String  callbackApprovalurl;
    @ApiModelProperty("流程节点信息")
    private List<NodeInfoReq> NodeList;
    @ApiModelProperty("流程连线信息")
    private  List<LineInfoReq> LineList;
    @ApiModelProperty("流程变量信息")
    private  List<VariableReq> VariableList;
    @ApiModelProperty("流程控件信息")
    private  List<FromControlReq> FromControl;
    @ApiModelProperty("流程头部显示信息")
    private JSONObject Head;
    @ApiModelProperty("流程设计json")
    private  String flowjson;
    @ApiModelProperty("流程消息模板")
    private  List<TempNoticeVO>  tempNoticeList;

    public String getCallbackApprovalurl() {
        return callbackApprovalurl;
    }

    public void setCallbackApprovalurl(String callbackApprovalurl) {
        this.callbackApprovalurl = callbackApprovalurl;
    }

    public String getBusinessModuleID() {
        return BusinessModuleID;
    }

    public void setBusinessModuleID(String businessModuleID) {
        BusinessModuleID = businessModuleID;
    }

    public String getBusinessMoudle() {
        return BusinessMoudle;
    }

    public void setBusinessMoudle(String businessMoudle) {
        BusinessMoudle = businessMoudle;
    }

    public Boolean getFIsAlertStarter() {
        return FIsAlertStarter;
    }

    public void setFIsAlertStarter(Boolean FIsAlertStarter) {
        this.FIsAlertStarter = FIsAlertStarter;
    }

    public Boolean getFIsCopyStart() {
        return FIsCopyStart;
    }

    public void setFIsCopyStart(Boolean FIsCopyStart) {
        this.FIsCopyStart = FIsCopyStart;
    }

    public Boolean getFIsEnabledAlert() {
        return FIsEnabledAlert;
    }

    public void setFIsEnabledAlert(Boolean FIsEnabledAlert) {
        this.FIsEnabledAlert = FIsEnabledAlert;
    }

    public String getFMessageType() {
        return FMessageType;
    }

    public void setFMessageType(String FMessageType) {
        this.FMessageType = FMessageType;
    }

    public String getFReadPerson() {
        return FReadPerson;
    }

    public void setFReadPerson(String FReadPerson) {
        this.FReadPerson = FReadPerson;
    }

    public String getFReadPersonName() {
        return FReadPersonName;
    }

    public void setFReadPersonName(String FReadPersonName) {
        this.FReadPersonName = FReadPersonName;
    }

    public String getFRemark() {
        return FRemark;
    }

    public void setFRemark(String FRemark) {
        this.FRemark = FRemark;
    }

    public String getFStartPerson() {
        return FStartPerson;
    }

    public void setFStartPerson(String FStartPerson) {
        this.FStartPerson = FStartPerson;
    }

    public String getFStartPersonName() {
        return FStartPersonName;
    }

    public void setFStartPersonName(String FStartPersonName) {
        this.FStartPersonName = FStartPersonName;
    }

    public String getFStartType() {
        return FStartType;
    }

    public void setFStartType(String FStartType) {
        this.FStartType = FStartType;
    }

    public String getFlowCode() {
        return FlowCode;
    }

    public void setFlowCode(String flowCode) {
        FlowCode = flowCode;
    }

    public String getFlowName() {
        return FlowName;
    }

    public void setFlowName(String flowName) {
        FlowName = flowName;
    }

    public String getFlowID() {
        return FlowID;
    }

    public void setFlowID(String flowID) {
        FlowID = flowID;
    }

    public String getFlowType() {
        return FlowType;
    }

    public void setFlowType(String flowType) {
        FlowType = flowType;
    }

    public String getFlowTypeName() {
        return FlowTypeName;
    }

    public void setFlowTypeName(String flowTypeName) {
        FlowTypeName = flowTypeName;
    }

    public String getFlowicon() {
        return flowicon;
    }

    public void setFlowicon(String flowicon) {
        this.flowicon = flowicon;
    }

    public String getFromname() {
        return fromname;
    }

    public void setFromname(String fromname) {
        this.fromname = fromname;
    }

    public String getFromID() {
        return FromID;
    }

    public void setFromID(String fromID) {
        FromID = fromID;
    }

    public List<NodeInfoReq> getNodeList() {
        return NodeList;
    }

    public void setNodeList(List<NodeInfoReq> nodeList) {
        NodeList = nodeList;
    }

    public List<LineInfoReq> getLineList() {
        return LineList;
    }

    public void setLineList(List<LineInfoReq> lineList) {
        LineList = lineList;
    }

    public List<VariableReq> getVariableList() {
        return VariableList;
    }

    public void setVariableList(List<VariableReq> variableList) {
        VariableList = variableList;
    }

    public List<FromControlReq> getFromControl() {
        return FromControl;
    }

    public void setFromControl(List<FromControlReq> fromControl) {
        FromControl = fromControl;
    }

    public JSONObject getHead() {
        return Head;
    }

    public void setHead(JSONObject head) {
        Head = head;
    }

    public String getFlowjson() {
        return flowjson;
    }

    public void setFlowjson(String flowjson) {
        this.flowjson = flowjson;
    }

    public List<TempNoticeVO> getTempNoticeList() {
        return tempNoticeList;
    }

    public void setTempNoticeList(List<TempNoticeVO> tempNoticeList) {
        this.tempNoticeList = tempNoticeList;
    }
}
