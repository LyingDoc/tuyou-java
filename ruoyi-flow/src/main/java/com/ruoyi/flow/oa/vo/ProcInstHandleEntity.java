package com.ruoyi.flow.oa.vo;




import com.ruoyi.flow.oa.entity.OaActEntity;
import com.ruoyi.flow.oa.entity.OaActroutingEntity;
import com.ruoyi.flow.oa.entity.OaPorcessinsEntity;
import com.ruoyi.flow.oa.entity.OaProcauditEntity;
import com.ruoyi.flow.vo.CurrentUserInfoVO;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProcInstHandleEntity {

    public ProcInstHandleEntity(){
        tappProcauditList=new ArrayList<>();
        runlogList=new ArrayList<>();
        errorlogList=new ArrayList<>();
    }
    /// <summary>
    /// 需要发送通知的对象  tempNoticeType：1 待办通知 2结束通知 3抄送通知 4沟通通知 5回复通知，6驳回通知，7转办通知 8催办提醒通知 9过期通知
    /// </summary>
    public void  AddProcessSendNotice(String userCode, String tempNoticeType){
        if("发起,审批,驳回".equals(this.getActionname())){
            if(this.getNexact()!=null) {
                ///当前节点设置接收通知
                if(this.getNexact().getIsemail()) {
                     ProcessSendNoticeVO processSendNoticeVO = new ProcessSendNoticeVO();
                    processSendNoticeVO.setUserCode(userCode);
                    processSendNoticeVO.setTempNoticeType(tempNoticeType);
                    this.processSendNoticeVOList.add(processSendNoticeVO);
                }
            }
        }else{
            if(this.getCurrentactentity()!=null) {
                ///当前节点设置接收通知
                if(this.getCurrentactentity().getIsemail()) {
                    com.ruoyi.flow.oa.vo.ProcessSendNoticeVO processSendNoticeVO = new com.ruoyi.flow.oa.vo.ProcessSendNoticeVO();
                    processSendNoticeVO.setUserCode(userCode);
                    processSendNoticeVO.setTempNoticeType(tempNoticeType);
                    this.processSendNoticeVOList.add(processSendNoticeVO);
                }
            }
        }
    }
    /// <summary>
    /// 流程消息发送实体
    /// </summary>
    private List<com.ruoyi.flow.oa.vo.ProcessSendNoticeVO> processSendNoticeVOList=new ArrayList<>();

    /// <summary>
    /// 流程消息发送实体
    /// </summary>
    public List<com.ruoyi.flow.oa.vo.ProcessSendNoticeVO> GetProcessSendNoticeVOList(){
        return processSendNoticeVOList;
    };
    /// <summary>
    /// 流程实例ID
    /// </summary>
    private String procinsid;
    /// <summary>
    /// 流程模板ID
    /// </summary>
    private String procid;
    /// <summary>
    /// 流程名称
    /// </summary>
    private String procname;
    /// <summary>
    /// 流程主题
    /// </summary>
    private String procinsname;
    /// <summary>
    /// 流程编号
    /// </summary>
    private String procinsno;
    /// <summary>
    /// 当前节点ID
    /// </summary>
    private String actid;
    /// <summary>
    /// 审批人
    /// </summary>
    private String fapproversuser;

    /// <summary>
    /// 申请人名称
    /// </summary>
    private String applicantuser;
    /// <summary>
    /// 申请人
    /// </summary>
    private String applicantuserno ;
    /// <summary>
    /// 节点实例ID
    /// </summary>
    private String actinsid ;
    /// <summary>
    /// 表单模板ID
    /// </summary>
    private String fromid ;
    /// <summary>
    /// 动作类型
    /// </summary>
    private String actionType;

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getFromid() {
        return fromid;
    }

    public com.ruoyi.flow.oa.vo.ProcInstHandleEntity setFromid(String fromid) {
        this.fromid = fromid;
        return this;
    }

    /// <summary>
    /// 表单实例ID
    /// </summary>
    private String fforminsid ;
    /// <summary>
    /// 发起用户ID
    /// </summary>
    private String startuserid ;
    /// <summary>
    /// 发起用户ID
    /// </summary>
    private String startuserName ;

    public String getStartuserName() {
        return startuserName;
    }

    public void setStartuserName(String startuserName) {
        this.startuserName = startuserName;
    }

    /// <summary>
    /// 当前用户用户信息
    /// </summary>
    private CurrentUserInfoVO cureentuser;
    /// <summary>
    /// 动作名称
    /// </summary>
    private String actionname;
    /// <summary>
    /// 动作类型 1 发起，5审批
    /// </summary>
    private int action;
     /// <summary>
    /// 审批原因
    /// </summary>
    private String ApprovedDes;
    /// <summary>
    /// 上一个节点
    /// </summary>
    private String PrevActName;
    /// <summary>
    /// 运行时当前节点Act实体对象
    /// </summary>
    private OaActEntity currentactentity;
    ///下一节点
    private String nextactid;
    //下一节点对象
    private  OaActEntity nexact;
    //获取下一节点审批人对象
    private String approvalobject;
    //转办人
    private String redrituser;
    public List<com.ruoyi.flow.oa.vo.CopyUserEntity> copylist=null;
    public String getRedrituser() {
        return redrituser;
    }

    public void setRedrituser(String redrituser) {
        this.redrituser = redrituser;
    }

    private List<OaProcauditEntity> tappProcauditList;
    ///运行日志
    private List<String> runlogList;
    ///错误日志
    private List<String> errorlogList;
    //表单业务表名
    private String businessTable;

    private Boolean isHandled;

    public Boolean getHandled() {
        return isHandled;
    }

    public void setHandled(Boolean handled) {
        isHandled = handled;
    }
    private Boolean IsFlowEnd=false;

    public Boolean getFlowEnd() {
        return IsFlowEnd;
    }

    public void setFlowEnd(Boolean flowEnd) {
        IsFlowEnd = flowEnd;
    }

    public String getBusinessTable() {
        return businessTable;
    }

    public void setBusinessTable(String businessTable) {
        this.businessTable = businessTable;
    }

    public List<OaProcauditEntity> getTappProcauditList() {
        return tappProcauditList;
    }
    ///添加运行日志
    public void addRunlog(String logMsg) {
         runlogList.add(logMsg);
    }
    ///添加错误日志
    public void addErrorlog(String logMsg) {
        errorlogList.add(logMsg);
    }
    public List<String> getRunlogList(){
        return runlogList;
    }
    public List<String> getErrorlog(){
        return errorlogList;
    }
    public String getApprovalobject() {
        return approvalobject;
    }

    public com.ruoyi.flow.oa.vo.ProcInstHandleEntity setApprovalobject(String approvalobject) {
        this.approvalobject = approvalobject;
        return this;
    }

    public String getNextactid() {
        return nextactid;
    }

    public com.ruoyi.flow.oa.vo.ProcInstHandleEntity setNextactid(String nextactid) {
        this.nextactid = nextactid;
        return this;
    }

    public OaActEntity getNexact() {
        return nexact;
    }

    public String nextactchartid;

    public String getNextactchartid() {
        return nextactchartid;
    }

    public void setNextactchartid(String nextactchartid) {
        this.nextactchartid = nextactchartid;
    }

    public com.ruoyi.flow.oa.vo.ProcInstHandleEntity setNexact(OaActEntity nexact) {
        this.nexact = nexact;
        return this;
    }

    /// <summary>
    ///
    /// </summary>
    private List<OaActroutingEntity> tappActroutingList;
    private OaPorcessinsEntity tappPorcessins;

    public OaPorcessinsEntity getTappPorcessins() {
        return tappPorcessins;
    }

    public com.ruoyi.flow.oa.vo.ProcInstHandleEntity setTappPorcessins(OaPorcessinsEntity tappPorcessins) {
        this.tappPorcessins = tappPorcessins;
        return this;
    }

    private JSONObject fromdata;
    private String fromdataJson;

    public String getFromdataJson() {
        return fromdataJson;
    }

    public com.ruoyi.flow.oa.vo.ProcInstHandleEntity setFromdataJson(String fromdataJson) {
        this.fromdataJson = fromdataJson;
        return this;
    }

    public JSONObject getFromdata() {
        return fromdata;
    }

    public com.ruoyi.flow.oa.vo.ProcInstHandleEntity setFromdata(JSONObject fromdata) {
        this.fromdata = fromdata;
        return this;
    }
    public String getProcinsid() {
        return procinsid;
    }

    public com.ruoyi.flow.oa.vo.ProcInstHandleEntity setProcinsid(String procinsid) {
        this.procinsid = procinsid;
        return this;
    }

    public String getProcid() {
        return procid;
    }

    public com.ruoyi.flow.oa.vo.ProcInstHandleEntity setProcid(String procid) {
        this.procid = procid;
        return this;
    }

    public String getProcname() {
        return procname;
    }

    public com.ruoyi.flow.oa.vo.ProcInstHandleEntity setProcname(String procname) {
        this.procname = procname;
        return this;
    }

    public String getProcinsname() {
        return procinsname;
    }

    public com.ruoyi.flow.oa.vo.ProcInstHandleEntity setProcinsname(String procinsname) {
        this.procinsname = procinsname;
        return this;
    }

    public String getProcinsno() {
        return procinsno;
    }

    public com.ruoyi.flow.oa.vo.ProcInstHandleEntity setProcinsno(String procinsno) {
        this.procinsno = procinsno;
        return this;
    }

    public String getActid() {
        return actid;
    }

    public com.ruoyi.flow.oa.vo.ProcInstHandleEntity setActid(String actid) {
        this.actid = actid;
        return this;
    }

    public String getFapproversuser() {
        return fapproversuser;
    }

    public com.ruoyi.flow.oa.vo.ProcInstHandleEntity setFapproversuser(String fapproversuser) {
        this.fapproversuser = fapproversuser;
        return this;
    }

    public String getApplicantuser() {
        return applicantuser;
    }

    public com.ruoyi.flow.oa.vo.ProcInstHandleEntity setApplicantuser(String applicantuser) {
        this.applicantuser = applicantuser;
        return this;
    }

    public String getApplicantuserno() {
        return applicantuserno;
    }

    public com.ruoyi.flow.oa.vo.ProcInstHandleEntity setApplicantuserno(String applicantuserno) {
        this.applicantuserno = applicantuserno;
        return this;
    }

    public String getActinsid() {
        return actinsid;
    }

    public com.ruoyi.flow.oa.vo.ProcInstHandleEntity setActinsid(String actinsid) {
        this.actinsid = actinsid;
        return this;
    }

    public String getFforminsid() {
        return fforminsid;
    }

    public com.ruoyi.flow.oa.vo.ProcInstHandleEntity setFforminsid(String fforminsid) {
        this.fforminsid = fforminsid;
        return this;
    }

    public String getStartuserid() {
        return startuserid;
    }

    public com.ruoyi.flow.oa.vo.ProcInstHandleEntity setStartuserid(String startuserid) {
        this.startuserid = startuserid;
        return this;
    }

    public CurrentUserInfoVO getCureentuser() {
        return cureentuser;
    }

    public com.ruoyi.flow.oa.vo.ProcInstHandleEntity setCureentuser(CurrentUserInfoVO cureentuser) {
        this.cureentuser = cureentuser;
        return this;
    }

    public String getActionname() {
        return actionname;
    }

    public com.ruoyi.flow.oa.vo.ProcInstHandleEntity setActionname(String actionname) {
        this.actionname = actionname;
        return this;
    }

    public int getAction() {
        return action;
    }

    public com.ruoyi.flow.oa.vo.ProcInstHandleEntity setAction(int action) {
        this.action = action;
        return this;
    }



    public String getApprovedDes() {
        return ApprovedDes;
    }

    public com.ruoyi.flow.oa.vo.ProcInstHandleEntity setApprovedDes(String approvedDes) {
        ApprovedDes = approvedDes;
        return this;
    }

    public String getPrevActName() {
        return PrevActName;
    }

    public com.ruoyi.flow.oa.vo.ProcInstHandleEntity setPrevActName(String prevActName) {
        PrevActName = prevActName;
        return this;
    }

    public OaActEntity getCurrentactentity() {
        return currentactentity;
    }

    public com.ruoyi.flow.oa.vo.ProcInstHandleEntity setCurrentactentity(OaActEntity currentactentity) {
        this.currentactentity = currentactentity;
        return this;
    }

    public List<OaActroutingEntity> getTappActroutingList() {
        return tappActroutingList;
    }

    public com.ruoyi.flow.oa.vo.ProcInstHandleEntity setTappActroutingList(List<OaActroutingEntity> tappActroutingList) {
        this.tappActroutingList = tappActroutingList;
        return this;
    }


    private String handleType; //回调处理方式 1、ApiUrl回调 2.sql语句回调
    private String apiUrl; //回调url地址
    private String ajaxType; //请求方式 post  请求 get 请求
    private String sqlcontent; //回调执行sql内容


    public String getHandleType() {
        return handleType;
    }

    public void setHandleType(String handleType) {
        this.handleType = handleType;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getAjaxType() {
        return ajaxType;
    }

    public void setAjaxType(String ajaxType) {
        this.ajaxType = ajaxType;
    }

    public String getSqlcontent() {
        return sqlcontent;
    }

    public void setSqlcontent(String sqlcontent) {
        this.sqlcontent = sqlcontent;
    }

    private String processInsState;
    ////流程处理状态
    public String getProcessInsState() {
        return processInsState;
    }

    public void setProcessInsState(String processInsState) {
        this.processInsState = processInsState;
    }
    private String callbackApprovalUrl;    // 默认回调·

    public String getCallbackApprovalUrl() {
        return callbackApprovalUrl;
    }

    public void setCallbackApprovalUrl(String callbackApprovalUrl) {
        this.callbackApprovalUrl = callbackApprovalUrl;
    }
}
