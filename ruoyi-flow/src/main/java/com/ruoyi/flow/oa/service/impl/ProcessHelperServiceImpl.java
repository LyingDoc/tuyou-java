package com.ruoyi.flow.oa.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.ruoyi.flow.comm.SpringBeanUtils;
import com.ruoyi.flow.comm.WebApiHelper;
import com.ruoyi.flow.form.service.impl.DBOperationHelperService;
import com.ruoyi.flow.oa.entity.*;
import com.ruoyi.flow.oa.vo.ApproveEntity;
import com.ruoyi.flow.oa.vo.CopyUserEntity;
import com.ruoyi.flow.oa.vo.ProcInstHandleEntity;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Scope("prototype")
public class ProcessHelperServiceImpl {
    ProcInstHandleEntity Instance;

    public void setInstance(ProcInstHandleEntity instance) {
        Instance = instance;
        dataList = new ArrayList<>();
        userList=new ArrayList<>();
    }

    List<ApproveEntity> dataList ;

    @Autowired
    private OaPorcessinsServiceImpl tappPorcessinsDao;

    @Autowired
    private OaFromdataServiceImpl tappFromdataDao;

    @Autowired
    private OaActinsServiceImpl tappActinsService;

    @Autowired
    private OaProcauditServiceImpl tappProcauditDao;

    @Autowired
    private OaActinscommunicateServiceImpl tappActinscommunicateDao;
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
    @Autowired
    private SpringBeanUtils springBeanUtils;
//    @Autowired
//    private DBOperationHelperService dbOperationHelperService;
    @Autowired
    private SysOaUserServiceImpl userService;


    Boolean isHandled = false;
    Boolean IsFlowEnd = false;///流程是否结束

    public Boolean SaveProcInfo() {
        Instance.addRunlog("开始解析会签，串行各种模式,判断当前节点是否已完成，进入下一个节点。");
        SaveProcInstData("2");
        if (!StringUtils.isNotBlank(Instance.getActinsid())) { //当节点实例为空时 就是发起节点
            isHandled = true;
        } else if ("驳回".equals(Instance.getActionname())) { ///不过多少个人审批 只要一个人驳回 流程就驳回
            isHandled = true;
            Instance.addRunlog("当前处理动作为：驳回，不管多少人是否已经审批或者未审批，只要一个人驳回，流程就驳回");
            OaActinsEntity actInsEnt = tappActinsService.getById(Instance.getActinsid());
            actInsEnt.setActinsStatus("2");
            actInsEnt.setCompleteddate(new Date());
            Add(actInsEnt, "edit", "TappActins");
            List<OaActinsEntity> tappActinsList = tappActinsService.GetNotHandleActIns(Instance.getProcinsid(), Instance.getCurrentactentity().getOaActId());
            for (int i = 0; i < tappActinsList.size(); i++) {
                OaActinsEntity itemactins = tappActinsList.get(i);
                if (!itemactins.getOaActinsId().equals(actInsEnt.getOaActinsId())) {
                    Add(itemactins, "del", "TappActins");
                }
            }
            Instance.addRunlog("驳回动作已完成。");
        } else {
            OaActinsEntity actInsEnt = tappActinsService.getById(Instance.getActinsid());
            actInsEnt.setActinsStatus("2");
            actInsEnt.setCompleteddate(new Date());
            Add(actInsEnt, "edit", "TappActins");
            if (Instance.getCurrentactentity().getHandletype() == null || Instance.getCurrentactentity().getHandletype().equals("1")) {  ///节点处理 一个人审批通过就通过
                Instance.addRunlog("进入普通审批模式，一人审批通过则全部审批通过！");
                isHandled = true;
                List<OaActinsEntity> tappActinsList = tappActinsService.GetNotHandleActIns(Instance.getProcinsid(), Instance.getCurrentactentity().getOaActId());
                for (int i = 0; i < tappActinsList.size(); i++) {
                    OaActinsEntity itemactins = tappActinsList.get(i);
                    if (!itemactins.getOaActinsId().equals(actInsEnt.getOaActinsId())) {
                        Add(itemactins, "del", "TappActins");
                    }
                }
                Instance.addRunlog("普通审批模式，一人审批通过则全部审批通过！处理已经完成。");
            } else { ////节点会签
                Instance.addRunlog("进入多人会签模式。");
                List<OaActinsEntity> tappActinsList = tappActinsService.GetAllCurrentActIns(actInsEnt.getArrivaldate(), Instance.getProcinsid(), Instance.getCurrentactentity().getOaActId());
                int wait = 0;
                for (int i = 0; i < tappActinsList.size(); i++) {
                    OaActinsEntity itemactins = tappActinsList.get(i);
                    //新增判断该待办是否有效，有效待办才计算；
                    //并行会签时,FInitStatus = 1;
                    //串行会签时,FInitStatus = -1;
                    if (("1".equals(itemactins.getActinsStatus()) || "-1".equals(itemactins.getActinsStatus())) && !itemactins.getOaActinsId().equals(actInsEnt.getOaActinsId())) {
                        wait = wait + 1;
                    }
                }
                switch (Instance.getCurrentactentity().getHandlecountersigntype()) {
                    case "1"://所有审批通过
                        if (wait > 0) {
                            //大于0表示还有需要审批的待办才能结束
                            isHandled = false;
                        } else {
                            isHandled = true;
                        }
                        break;
                    case "2"://多数审批通过
                        double iRs = (double) (tappActinsList.size()) / 2;
                        int LeastNum = (int) (iRs + 0.5);
                        if (wait < LeastNum) //待办数小于已办数
                        {
                            isHandled = true;

                        } else {
                            isHandled = false;
                        }
                        break;
                    case "3": ///任意一个审批通过
                        isHandled = true;

                        break;
                    default:
                        isHandled = true;

                        break;
                }
                if (isHandled) {
                    for (int i = 0; i < tappActinsList.size(); i++) {
                        OaActinsEntity itemactins = tappActinsList.get(i);
                        //新增判断该待办是否有效，有效待办才计算；
                        //并行会签时,FInitStatus = 1;
                        //串行会签时,FInitStatus = -1;
                        if (("1".equals(itemactins.getActinsStatus()) || "-1".equals(itemactins.getActinsStatus())) && !itemactins.getOaActinsId().equals(actInsEnt.getOaActinsId())) {
                            Add(itemactins, "del", "TappActins");
                        }
                    }
                }
                Instance.addRunlog("多人会签模式处理完成。");
            }
        }
        Instance.setHandled(isHandled);
        if (isHandled) {
            Instance.addRunlog("当前节点已经处理完成，开始保存数据。");
            if ("F_End".equals(Instance.getNextactchartid())) ///判断流程是否处理完成
            {
                IsFlowEnd = true;
                Instance.setFlowEnd(IsFlowEnd);
                Instance.AddProcessSendNotice(Instance.getFromdata().getString("Header_CreateUserID"), "2");
//                SaveProcInstCompletedData(procInstanceEntity);
            } else {
                Instance.addRunlog("当前节点处理完成,开始插入待办数据。\r\n");
                InsertActIns(); //保存下一节点实例信息 产生待办
            }
            systemCC();
        } else {
            if (Instance.getCurrentactentity().getCountersigntype().equals("2")) {
                Instance.addRunlog("当前节点如果为多人串行审批，需要将下一个人的审批的人待办显示出来。\r\n");
                OaActinsEntity actInsEnt = tappActinsService.getById(Instance.getActinsid());
                List<OaActinsEntity> tappActinsList = tappActinsService.GetNotHandleCountersignActIns(actInsEnt.getArrivaldate(), Instance.getProcinsid(), Instance.getCurrentactentity().getOaActId());
                if (tappActinsList.size() > 0) {
                    actInsEnt = tappActinsList.get(0);
                    actInsEnt.setActinsStatus("1");
                    Instance.AddProcessSendNotice(actInsEnt.getApproversuser(), "1");
                    Add(actInsEnt, "edit", "TappActins");//new ApproveEntity { Entity = actInsEnt, OperationType = "edit", TableName = "TB_WMP_ActIns" });
                } else {
                    Instance.addRunlog("没有找到下一个人的审批的人待办节点。当前节点:" + Instance.getActinsid() + "\r\n");
                }
//                    systemCC(procInstanceEntity, resolve);
                Instance.addRunlog("保存抄送。当前节点:" + Instance.getActinsid() + "\r\n");
//                    EmailHelper emial = new EmailHelper(procInstanceEntity, resolve, dataList);
//                    emial.SendEmial();
            }
        }
        Instance.addRunlog("开始将流程运行数据保存入库。");
        return SaveData();
    }

    void systemCC() {
        if (Instance.getNexact() != null && StringUtils.isNotBlank(Instance.getNexact().getCcPerson())) {
            String approvalers = userService.GetSystemApprovaler(Instance.getNexact().getCcPerson());
            if (StringUtils.isNotBlank(approvalers) && approvalers.indexOf(",") >= 0) {
                String[] strUser = approvalers.split(",");
                List<String> ccUserList = new ArrayList<>();
                for (Integer i = 0; i < strUser.length; i++) {
                    if (strUser[i].length() > 0 && !ccUserList.contains(strUser[i].trim())) {
                        ccUserList.add(strUser[i].trim());
                    }
                }
                ccUserList.forEach(ee -> {
                    Instance.AddProcessSendNotice(ee, "3");
                });
            }
        }
    }

    void Add(Object obj, String operationtype, String tablename) {
        ApproveEntity approveEntity = new ApproveEntity();
        approveEntity.setEntity(obj);
        approveEntity.setOperationtype(operationtype);
        approveEntity.setTablename(tablename);
        dataList.add(approveEntity);
    }

    /// <summary>
    /// 插入待办数据
    /// </summary>
    /// <param name="procInstanceEntity">处理流程数据对象 包含当前节点信息，当前流程实例信息，表单信息等等</param>
    /// <param name="resolve">审批对象</param>
    /// <returns></returns>
    List<String> userList ;

    public boolean InsertActIns() {
        String users = Instance.getApprovalobject().replace(';', ',');
        String[] strUser = users.split(",");
        //added by zhong 去掉重复用户
        for (Integer i = 0; i < strUser.length; i++) {
            if (strUser[i].length() > 0 && !userList.contains(strUser[i].trim())) {
                userList.add(strUser[i].trim());
            }
        }
        Date arrivaldate = new Date();
        int iCount = 0;
        for (int i = 0; i < userList.size(); i++) {
            if ("2".equals(Instance.getNexact().getCountersigntype()))  ///串行会签
            {
                if (iCount == 0) {
                    Instance.AddProcessSendNotice(userList.get(i), "1");
                    InsertActIns(userList.get(i), "1", i, arrivaldate);
                    iCount++;
                } else {
                    InsertActIns(userList.get(i), "-1", i, arrivaldate);
                }
            } else {
                InsertActIns(userList.get(i), "1", i, arrivaldate);
                if ("驳回".equals(Instance.getActionname())) {
                    Instance.AddProcessSendNotice(userList.get(i), "6");
                } else {
                    Instance.AddProcessSendNotice(userList.get(i), "1");
                }
            }
        }
        return true;
    }

    void InsertActIns(String user, String FStatus, int index, Date arrivaldate) {
        Long newid = IdWorker.getId();
        OaActinsEntity actIns = new OaActinsEntity();
        actIns.setOaActId(newid.toString());
        //actIns.FProcInsID = procInstanceEntity.ProcInsID.Value;
        actIns.setOaActId(Instance.getNexact().getOaActId());//= actEnt.FID;
        //actIns.setFActName = actEnt.FActName;
        // actIns.FActType = (int)type;
        actIns.setApproversuser(user);/// = GetAppLicense(user, procInstanceEntity, actIns.FID);  代理功能后续再实现
        actIns.setUpdateBy(Instance.getCureentuser().getUserCode());
        actIns.setUpdateDate(new Date());
        actIns.setOaProcessId(Instance.getProcid());
        actIns.setActchartId(Instance.getNexact().getActchartId());
        actIns.setArrivaldate(new Date());// = DateTime.Now;
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, index);
        actIns.setCreateDate(nowTime.getTime()); ///= DateTime.Now.AddSeconds(index);//给创建时间加上多少秒 方便多人串行会签时，按照配置人的顺序审批
        actIns.setCreateBy(Instance.getCureentuser().getUserCode()); //= procInstanceEntity.CureentUserID;
        ///  actIns.FActDisplayName = actEnt.FActDisplayName;
//        if (!string.IsNullOrEmpty(procInstanceEntity.ApprovedCallBack))
//            actIns.FAfterCallBackUrl = procInstanceEntity.ApprovedCallBack;
//        if (!string.IsNullOrEmpty(procInstanceEntity.OldApprovedCallBack))
//            actIns.FAfterCallBackUrl = procInstanceEntity.OldApprovedCallBack;
//        if (!string.IsNullOrEmpty(procInstanceEntity.MobileApprovedCallBack))
//            actIns.FAfterCallBackUrl = procInstanceEntity.MobileApprovedCallBack;
//        if (FInitStatus != 0)
//            actIns.FInitStatus = FInitStatus;
//        else
//            actIns.FInitStatus = 0;
        actIns.setActinsStatus(FStatus); //
        Add(actIns, "add", "TappActins");
    }

    void NewProcessInst(String status) {
        OaFromdataEntity formDataEntity = new OaFromdataEntity();
        formDataEntity.setFromid(Instance.getFromid());
        formDataEntity.setFromdata(Instance.getFromdataJson());
        Long newId = IdWorker.getId();
        formDataEntity.setOaFromdataId(newId.toString());
        Instance.setFforminsid(newId.toString());
        formDataEntity.setCreateBy(Instance.getCureentuser().getUserCode());// = procInstanceEntity.CureentUserID;
        formDataEntity.setCreateDate(new Date()); ///= DateTime.Now;
        formDataEntity.setUpdateBy(Instance.getCureentuser().getUserCode());
        formDataEntity.setUpdateDate(new Date()); //= DateTime.Now;
        Add(formDataEntity, "add", "TappFromdata");
        OaPorcessinsEntity procInst = new OaPorcessinsEntity();
        Long newId2 = IdWorker.getId();
        procInst.setOaPorcessinsId(newId2.toString());
        Instance.setProcinsid(newId2.toString());
        procInst.setOaProcessId(Instance.getProcid());
        procInst.setCreateBy(Instance.getCureentuser().getUserCode());
        procInst.setCreateDate(new Date());
        procInst.setOaFromdataId(formDataEntity.getOaFromdataId());
        procInst.setFormFormId(Instance.getFromid());
        procInst.setStarttime(new Date()); ///= DateTime.Now;
        procInst.setPorcessinsStatus(status);
        procInst.setStarter(Instance.getCureentuser().getUserCode());// = procInstanceEntity.ApplicantUserNo;
        procInst.setStartername(Instance.getCureentuser().getUserName());
        procInst.setApplicantuser(Instance.getApplicantuserno());
        procInst.setApplicantusername(Instance.getApplicantuser());
        procInst.setUpdateDate(new Date()); //= DateTime.Now;
        procInst.setUpdateBy(Instance.getCureentuser().getUserCode()); //= procInstanceEntity.CureentUserID;
        procInst.setProcinsname(Instance.getProcinsname());// = procInstanceEntity.GetFormDataXmlValue("Header_formSubTitle");
        procInst.setProcinsno(Instance.getProcinsno());// = procInstanceEntity.GetFormDataXmlValue("Header_formNumber");
        Add(procInst, "add", "TappPorcessins");
    }

    /// <summary>
    /// 保存流程实例数据
    /// </summary>
    /// <param name="procInstanceEntity">处理流程数据对象 包含当前节点信息，当前流程实例信息，表单信息等等</param>
    /// <param name="status">流程状态</param>
    /// <returns></returns>
    public Boolean SaveProcInstData(String status) {

        // Guid gProcInsID = procInstanceEntity.ProcInsID == null ? Guid.Empty : procInstanceEntity.ProcInsID.Value;
        if (!StringUtils.isNotBlank(Instance.getProcinsid())) {
            NewProcessInst(status);
//                procInstanceEntity.EmailVariable["ProcInsID"] = procInst.FID.ToString();
//                procInstanceEntity.EmailVariable["FFormInsID"] = formDataEntity.FID.ToString();
        } else {
            OaPorcessinsEntity procInst = tappPorcessinsDao.getById(Instance.getProcinsid());// db.TB_WMP_ProcInstance.FirstOrDefault(ee => ee.FID == procInstanceEntity.ProcInsID && ee.FInactivateDate == null);
            if (procInst == null) {
                OaFromdataEntity formDataEntity = new OaFromdataEntity();
                Long newid = IdWorker.getId();
                formDataEntity.setOaFromdataId(newid.toString());
                Instance.setFforminsid(formDataEntity.getOaFromdataId());
                formDataEntity.setCreateBy(Instance.getCureentuser().getUserCode());// = procInstanceEntity.CureentUserID;
                formDataEntity.setCreateDate(new Date()); ///= DateTime.Now;
                formDataEntity.setFromid(Instance.getFromid());
                formDataEntity.setFromdata(Instance.getFromdataJson());
                formDataEntity.setUpdateBy(Instance.getCureentuser().getUserCode());
                formDataEntity.setUpdateDate(new Date()); //= DateTime.Now;
                Add(formDataEntity, "add", "TappFromdata");
                procInst = new OaPorcessinsEntity();
                Long newid2 = IdWorker.getId();
                Instance.setProcinsid(newid2.toString());
                procInst.setOaPorcessinsId(Instance.getProcinsid());
                procInst.setUpdateBy(Instance.getCureentuser().getUserCode());
                procInst.setUpdateDate(new Date());
                procInst.setOaFromdataId(formDataEntity.getOaFromdataId());
                procInst.setOaProcessId(Instance.getProcid());
                procInst.setFormFormId(Instance.getFromid());
                procInst.setStarttime(new Date()); ///= DateTime.Now;
                procInst.setPorcessinsStatus(status);
                procInst.setStarter(Instance.getCureentuser().getUserCode());// = procInstanceEntity.ApplicantUserNo;
                procInst.setStartername(Instance.getCureentuser().getUserName());
                procInst.setApplicantuser(Instance.getApplicantuserno());
                procInst.setApplicantusername(Instance.getApplicantuser());
                procInst.setUpdateDate(new Date()); //= DateTime.Now;
                procInst.setUpdateBy(Instance.getCureentuser().getUserCode()); //= procInstanceEntity.CureentUserID;
                procInst.setProcinsname(Instance.getProcinsname());// = procInstanceEntity.GetFormDataXmlValue("Header_formSubTitle");
                procInst.setProcinsno(Instance.getProcinsno());// = procInstanceEntity.GetFormDataXmlValue("Header_formNumber");
                Add(procInst, "add", "TappPorcessins");
            } else {
                //  procInst.FK2ProcInsID = -1;
                OaFromdataEntity formDataEntity = tappFromdataDao.getById(procInst.getOaFromdataId());  ///db.TB_WMP_FormData.FirstOrDefault(ee => ee.FID == procInst.FFormInsID && ee.FInactivateDate == null);
                if (formDataEntity == null) {
                    formDataEntity = new OaFromdataEntity();
                    Long formnewid = IdWorker.getId();
                    formDataEntity.setOaFromdataId(formnewid.toString());
                    Instance.setFforminsid(formDataEntity.getOaFromdataId());
                    formDataEntity.setCreateBy(Instance.getCureentuser().getUserCode());// = procInstanceEntity.CureentUserID;
                    formDataEntity.setCreateDate(new Date()); ///= DateTime.Now;
                    Add(formDataEntity, "add", "TappFromdata");
                    formDataEntity.setFromid(Instance.getFromid());
                } else {
                    Add(formDataEntity, "edit", "TappFromdata");
                }
                formDataEntity.setFromdata(Instance.getFromdataJson());
                formDataEntity.setUpdateBy(Instance.getCureentuser().getUserCode()); //= procInstanceEntity.CureentUserID;
                formDataEntity.setUpdateDate(new Date());
                procInst.setProcinsname(Instance.getProcinsname());
                procInst.setProcinsno(Instance.getProcinsno());///FProcInsNO = procInstanceEntity.GetFormDataXmlValue("Header_formNumber");
                procInst.setUpdateDate(new Date()); //= DateTime.Now;
                procInst.setUpdateBy(Instance.getCureentuser().getUserCode());// = procInstanceEntity.CureentUserID;
                ///暂存不更改流程状态
                if (!"1".equals(status)) {
                    procInst.setPorcessinsStatus(status); //= status;
                }
                Add(procInst, "edit", "TappPorcessins");
                /// dataList.Add(new ApproveEntity { Entity = formDataEntity, OperationType = "edit", TableName = "TB_WMP_FormData" });
            }
        }
        Instance.addRunlog("保存流程实例，表单实例，审批记录，节点实例的状态更新成功！\r\n");
        return true;
    }

    /// <summary>
    /// 删除草稿
    /// </summary>
    /// <param name="procInstanceEntity">处理流程数据对象 包含当前节点信息，当前流程实例信息，表单信息等等</param>
    /// <returns></returns>
    public Boolean DelProcInst() {
        OaPorcessinsEntity porcessins = tappPorcessinsDao.getById(Instance.getProcinsid());
        if ("0".equals(porcessins.getPorcessinsStatus()) && (porcessins.getStarter().equals(Instance.getCureentuser()) || porcessins.getApplicantuser().equals(Instance.getCureentuser()))) {
            Add(porcessins, "del", "TappPorcessins");
            return SaveData();
        } else {
            Instance.addErrorlog("该流程已经不是草稿状态，或者您没有权限删除！");
            return false;
        }

    }

    /// <summary>
    /// 转办
    /// </summary>
    /// <param name="procInstanceEntity">处理流程数据对象 包含当前节点信息，当前流程实例信息，表单信息等等</param>
    /// <returns></returns>
    public Boolean TransferProcInst(OaActinsEntity tappActins) {
        SaveProcInstData("2");
        tappActins.setOaProcessinsId(Instance.getProcinsid());
        tappActins.setActinsStatus("1");
        tappActins.setApproversuser(Instance.getRedrituser());
        tappActins.setUpdateBy(Instance.getCureentuser().getUserCode());
        tappActins.setUpdateDate(new Date());
        Add(tappActins, "edit", "TappActins");
//        EmailHelper emial = new EmailHelper(procInstanceEntity, null, dataList);
//        emial.SendDoReadEmial();
        Instance.AddProcessSendNotice(tappActins.getApproversuser(), "7");
        return SaveData();
    }

    /// <summary>
    /// 暂存使用
    /// </summary>
    /// <param name="procInstanceEntity">处理流程数据对象 包含当前节点信息，当前流程实例信息，表单信息等等</param>
    /// <returns></returns>
    public Boolean TempSave() {
        SaveProcInstData("1");
        return SaveData();
    }

    /// <summary>
    /// 撤销,废弃，终止入口
    /// </summary>
    /// <param name="procInstanceEntity">处理流程数据对象 包含当前节点信息，当前流程实例信息，表单信息等等</param>
    ///  <param name="Action">5 废弃 6 撤销 4终止</param>
    /// <returns></returns>
    public Boolean OperationProcInst(int Action) {
        SaveProcInstData(Action + "");
        List<OaActinsEntity> tappActinsList = tappActinsService.GetNotHandleActIns(Instance.getProcinsid(), Instance.getCurrentactentity().getOaActId());
        tappActinsList.forEach(ee -> {
            Add(ee, "del", "TappActins");
        });
        QueryWrapper<OaActinscommunicateEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("Oa_Actins_Id", Instance.getActinsid()).eq("communicatetype", "5");
        List<OaActinscommunicateEntity> tappActinscommunicateList = tappActinscommunicateDao.getBaseMapper().selectList(queryWrapper);
        if (!tappActinscommunicateList.isEmpty()) {
            for (OaActinscommunicateEntity item : tappActinscommunicateList) {
                Add(item, "del", "TappActinscommunicate");
            }
        }
        if (Action == 6) {
            NewProcessInst("1");
        }
        if (SaveData()) {
            HandleCallback();
            return true;
        }
        return false;
    }

    void HandleCallback() {
        if (!StringUtils.isNotBlank(Instance.getApiUrl()) && !StringUtils.isNotBlank(Instance.getSqlcontent())) {
            if (StringUtils.isNotBlank(Instance.getCallbackApprovalUrl())) {
                Instance.setApiUrl(Instance.getCallbackApprovalUrl());
            }
        }
        if (StringUtils.isNotBlank(Instance.getApiUrl()) || StringUtils.isNotBlank(Instance.getSqlcontent())) {
            JSONObject processData = new JSONObject();
            processData.put("actinsid", Instance.getActinsid());
            processData.put("procinsid", Instance.getProcinsid());
            processData.put("procid", Instance.getProcid());
            processData.put("actionname", Instance.getActionname());
            processData.put("action", Instance.getAction());
            processData.put("actionType", Instance.getActionType());
            processData.put("fromid", Instance.getFromid());
            processData.put("formdataid", Instance.getFforminsid());
            processData.put("actid", Instance.getActid());
            processData.put("actchartid", Instance.getCurrentactentity().getActchartId());
            if (Instance.getNexact() != null) {
                processData.put("nextactid", Instance.getNexact().getOaActId());
                processData.put("nextactname", Instance.getNexact().getActname());
                processData.put("nextactchartid", Instance.getNexact().getActchartId());
            }
            if (Instance.getFlowEnd() == true) {
                processData.put("nextactid", "");
                processData.put("nextactname", "流程结束");
                processData.put("nextactchartid", "F_End");
            }
            processData.put("cureentUserCode", Instance.getCureentuser().getUserCode());
            processData.put("fromdata", Instance.getFromdata());
            if ("1".equals(Instance.getHandleType()) && StringUtils.isNotBlank(Instance.getApiUrl())) {
                String headurl = "";
                try {
                    WebApiHelper webApiHelper = new WebApiHelper();
                    JSONObject headMap = new JSONObject();
                    webApiHelper.AjaxPost(Instance.getApiUrl().replace("${http}", headurl), headMap, JSON.toJSONString(processData));
                } catch (Exception ex) {
                    ///当流程回调失败时，将状态改为9 锁住流程
                    OaPorcessinsEntity oaPorcessins = tappPorcessinsDao.getById(Instance.getProcinsid());
                    oaPorcessins.setPorcessinsStatus("9");
                    Instance.setProcessInsState("9");
                    tappPorcessinsDao.updateById(oaPorcessins);
                    Instance.getErrorlog().add("回调失败！回调url：" + Instance.getApiUrl().replace("${http}", headurl) + ", 具体错误：" + ex);
                }
            } else if (StringUtils.isNotBlank(Instance.getSqlcontent())) {
                try {
                    DBOperationHelperService   dbOperationHelperService =   springBeanUtils.getBean(DBOperationHelperService.class);
                    dbOperationHelperService.setContext(processData);
//                    DBOperationHelper dbOperationHelper = new DBOperationHelper(jdbcTemplate, processData);
                    dbOperationHelperService.ExecuteOptSql(Instance.getSqlcontent());
                } catch (Exception ex) {
                    ///当流程回调失败时，将状态改为9 锁住流程
                    OaPorcessinsEntity oaPorcessins = tappPorcessinsDao.getById(Instance.getProcinsid());
                    oaPorcessins.setPorcessinsStatus("9");
                    Instance.setProcessInsState("9");
                    tappPorcessinsDao.updateById(oaPorcessins);
                    Instance.getErrorlog().add("回调失败！具体错误：" + ex);
                }
            }
        }
    }

    @Transactional
    public Boolean SaveData() {
        int procInsStatus = 0;
        for (int i = 0; i < dataList.size(); i++) {
            ApproveEntity approveEntity = dataList.get(i);
            switch (approveEntity.getTablename()) {
                case "TappFromdata":
                    OaFromdataEntity tappFromdata = null;
                    if (approveEntity.getOperationtype().equals("add")) {
                        tappFromdata = (OaFromdataEntity) approveEntity.getEntity();
                        tappFromdataDao.getBaseMapper().insert(tappFromdata);
                    } else if (approveEntity.getOperationtype().equals("edit")) {
                        tappFromdata = (OaFromdataEntity) approveEntity.getEntity();
                        tappFromdataDao.getBaseMapper().updateById(tappFromdata);
                    } else {
                        tappFromdata = (OaFromdataEntity) approveEntity.getEntity();
                        tappFromdataDao.getBaseMapper().deleteById(tappFromdata.getOaFromdataId());
                    }
                    break;
                case "TappPorcessins":
                    OaPorcessinsEntity tappPorcessins = null;
                    if (approveEntity.getOperationtype().equals("add")) {
                        tappPorcessins = (OaPorcessinsEntity) approveEntity.getEntity();
                        if (isHandled) {
                            if (IsFlowEnd) {
                                tappPorcessins.setCurrentactname("");
                                tappPorcessins.setCurrentapproversuser("");
                                tappPorcessins.setCompletetime(new Date());
                                tappPorcessins.setPorcessinsStatus("4");
                            } else {
                                tappPorcessins.setCurrentactname(Instance.getNexact().getActname());
                                tappPorcessins.setCurrentapproversuser(String.join(",", userList));
                            }
                        }
                        tappPorcessinsDao.getBaseMapper().insert(tappPorcessins);
                    } else if (approveEntity.getOperationtype().equals("edit")) {
                        tappPorcessins = (OaPorcessinsEntity) approveEntity.getEntity();
                        if (isHandled) {
                            if (IsFlowEnd) {
                                tappPorcessins.setCurrentactname("");
                                tappPorcessins.setCurrentapproversuser("");
                                tappPorcessins.setCompletetime(new Date());
                                //  tappPorcessins.setFromdataid(tappFromdata.getFid());
                                tappPorcessins.setPorcessinsStatus("4");
                            } else {
                                tappPorcessins.setCurrentactname(Instance.getNexact().getActname());
                                tappPorcessins.setCurrentapproversuser(String.join(",", userList));
                            }
                        }
                        tappPorcessinsDao.getBaseMapper().updateById(tappPorcessins);
                    } else {
                        tappPorcessinsDao.getBaseMapper().deleteById(((OaPorcessinsEntity) approveEntity.getEntity()).getOaPorcessinsId());
                    }
                    break;
                case "TappActinscommunicate":
                    if (approveEntity.getOperationtype().equals("add")) {
                        OaActinscommunicateEntity tappActinscommunicate = (OaActinscommunicateEntity) approveEntity.getEntity();
                        tappActinscommunicateDao.getBaseMapper().insert(tappActinscommunicate);
                    } else if (approveEntity.getOperationtype().equals("edit")) {
                        tappActinscommunicateDao.getBaseMapper().updateById((OaActinscommunicateEntity) approveEntity.getEntity());
                    } else {
                        tappActinscommunicateDao.getBaseMapper().deleteById(((OaActinscommunicateEntity) approveEntity.getEntity()).getOaActinscommunicateId());
                    }
                    break;
                case "TappActins":
                    if (approveEntity.getOperationtype().equals("add")) {
                        OaActinsEntity tappActins = (OaActinsEntity) approveEntity.getEntity();
                        tappActins.setOaProcessinsId(Instance.getProcinsid());
                        tappActinsService.getBaseMapper().insert(tappActins);
                    } else if (approveEntity.getOperationtype().equals("edit")) {
                        tappActinsService.getBaseMapper().updateById((OaActinsEntity) approveEntity.getEntity());
                    } else {
                        tappActinsService.deleteById(((OaActinsEntity) approveEntity.getEntity()).getOaActinsId());
                    }
                    break;
                case "TappProcaudit":
                    if ("add".equals(approveEntity.getOperationtype())) {
                        tappProcauditDao.getBaseMapper().insert((OaProcauditEntity) approveEntity.getEntity());
                    } else if ("edit".equals(approveEntity.getOperationtype())) {
                        tappProcauditDao.getBaseMapper().updateById((OaProcauditEntity) approveEntity.getEntity());
                    } else {
                        tappProcauditDao.getBaseMapper().deleteById(((OaProcauditEntity) approveEntity.getEntity()).getOaProcauditId());
                    }
                    break;
            }
        }
        for (int i = 0; i < Instance.getTappProcauditList().size(); i++) {
            OaProcauditEntity tappProcaudit = Instance.getTappProcauditList().get(i);
            tappProcaudit.setOaProcinsId(Instance.getProcinsid());
            tappProcauditDao.getBaseMapper().insert(tappProcaudit);
        }
        return true;
    }

    /// <summary>
    ///跳转
    /// </summary>
    public Boolean SkipProcInst() {

        return true;
    }

    /// <summary>
    /// 抄送入口
    /// </summary>
    /// <param name="procInstanceEntity">处理流程数据对象 包含当前节点信息，当前流程实例信息，表单信息等等</param>
    ///  <returns></returns>
    public Boolean CCProcInst(OaActinsEntity tappActins) {
        List<String> ccuserList = new ArrayList<>();
        Map<String, String> mapParam = new HashMap<>();
        if (Instance.copylist != null && Instance.copylist.size() > 0) {
            //added by zhong 去掉重复用户
            for (CopyUserEntity copyuseritem : Instance.copylist
            ) {
                if (!ccuserList.contains(copyuseritem.getNo())) {
                    ccuserList.add(copyuseritem.getNo());
                    mapParam.put(copyuseritem.getNo(), copyuseritem.getName());
                }
            }
        }
        List<String> copySendUser = new ArrayList<>();
        List<String> copySendUserName = new ArrayList<>();
        String msg = "";
        for (String item : ccuserList) {
            String UserName = mapParam.get(item);// CacheHelper.Instance.GetUserNameAll(item);
            QueryWrapper<OaActinscommunicateEntity> tappActinscommunicate = new QueryWrapper();
            tappActinscommunicate.eq("oa_Actins_Id", Instance.getActinsid()).eq("approversuser", item).eq("communicatetype", "6").eq("communicate_status", "1");
            List<OaActinscommunicateEntity> tappActinscommunicateList = tappActinscommunicateDao.getBaseMapper().selectList(tappActinscommunicate);
            if (!(tappActinscommunicateList.isEmpty() || tappActinscommunicateList.size() == 0)) {
                msg += UserName + "[" + item + "]、";
            } else {
                copySendUser.add(item);
                copySendUserName.add(UserName);
            }
        }
        if (!copySendUser.isEmpty()) {
            for (int i = 0; i < copySendUser.size(); i++) {
                OaActinscommunicateEntity aice = new OaActinscommunicateEntity();
                aice.setOaActinsId(Instance.getActinsid()); //= procInstanceEntity.ActInsID;
                aice.setOaProcessinsId(Instance.getProcinsid());
                // aice.FActID = ActInsEntity.FActID;
                aice.setActname(Instance.getCurrentactentity().getActname());//FActName = ActInsEntity.FActName;
                // aice.FActDisplayName = ActInsEntity.FActDisplayName;
                aice.setCommunicatetype("6"); //= 6;
                aice.setApproversuser(copySendUser.get(i));/// copySendUser[i].Trim().Replace("\n", "").Replace("\t", "").Replace("\r", "");

                aice.setCommunicateStatus("1");
                aice.setCreateBy(Instance.getCureentuser().getUserCode());
                aice.setCreateDate(new Date()); //= DateTime.Now;
                aice.setUpdateBy(Instance.getCureentuser().getUserCode()); //= procInstanceEntity.CureentUserID;
                aice.setUpdateDate(new Date());
                Add(aice, "add", "TappActinscommunicate");
                Instance.AddProcessSendNotice(copySendUser.get(i), "3");
            }
            ///创建待办待阅信息 抄送邮件，待办待阅邮件，流程结束邮件
            String bark = "【" + Instance.getCureentuser().getUserName() + " 抄送给：";
            bark = bark + String.join("、", copySendUserName);
            bark += "】" + "备注:" + Instance.getApprovedDes();
            OaProcauditEntity procAudi = new OaProcauditEntity();
            procAudi.setOaProcinsId(Instance.getProcinsid()); //procInstanceEntity.ProcInsID.Value;
            procAudi.setOaActinsId(Instance.getActinsid());
            procAudi.setActname(Instance.getCurrentactentity().getActname());
            procAudi.setApprovelaction(Instance.getActionname());
            procAudi.setApproveldescr(bark);
            //发起沟通或者抄送时，开始时间为当前时间；回复沟通时，开始时间为到达日期；
            procAudi.setActcreatedate(new Date());
            procAudi.setActopendate(new Date());
            procAudi.setActfishdate(new Date());
            procAudi.setApproversname(Instance.getCureentuser().getUserName());
            procAudi.setCreateBy(Instance.getCureentuser().getUserCode());
            procAudi.setCreateDate(new Date());
            procAudi.setUpdateDate(new Date());

            Add(procAudi, "add", "TappProcaudit");
            ///发送邮件 和审批记录
            //        EmailHelper emial = new EmailHelper(procInstanceEntity, null, dataList);
//        emial.SendCCEmial(procInstanceEntity.RedritUser);
            return SaveData();
        } else {
            if (!msg.equals("")) {
                Instance.addErrorlog("重复抄送人员：" + msg);
                return false;
            }
            return true;
        }

    }

    /// <summary>
    /// 取消沟通
    /// </summary>
    /// <param name="procInstanceEntity">处理流程数据对象 包含当前节点信息，当前流程实例信息，表单信息等等</param>
    /// <returns></returns>
    public Boolean CancerCommunication() {
        QueryWrapper<OaActinscommunicateEntity> queryWrapper = new QueryWrapper();
        queryWrapper.eq("oa_Actins_Id", Instance.getActinsid()).eq("communicatetype", "5");
        List<OaActinscommunicateEntity> tappActinscommunicateList = tappActinscommunicateDao.getBaseMapper().selectList(queryWrapper);
        if (!tappActinscommunicateList.isEmpty()) {
            for (OaActinscommunicateEntity item : tappActinscommunicateList) {
                Add(item, "del", "TappActinscommunicate");
            }

        }
        OaActinsEntity tappActins = tappActinsService.getById(Instance.getActinsid());
        tappActins.setActinsStatus("1");
        Add(tappActins, "edit", "TappActins");
        Instance.getTappPorcessins().setPorcessinsStatus("2");
        Add(Instance.getTappPorcessins(), "edit", "TappPorcessins");
        return SaveData();
    }

    /// <summary>
    /// 发起沟通
    /// </summary>
    /// <param name="procInstanceEntity">处理流程数据对象 包含当前节点信息，当前流程实例信息，表单信息等等</param>
    /// <returns></returns>
    public Boolean StartCommunication() {
//        String users = Instance.getApprovalobject().replace(';', ',');
//        String[] strUser = users.split(",");
        List<String> ccuserList = new ArrayList<>();
        if (Instance.copylist != null && Instance.copylist.size() > 0) {
            //added by zhong 去掉重复用户
            for (CopyUserEntity copyuseritem : Instance.copylist
            ) {
                if (!userList.contains(copyuseritem.getNo())) {
                    ccuserList.add(copyuseritem.getNo());
                }
            }
        }
        List<String> copySendUser = new ArrayList<>();
        List<String> copySendUserName = new ArrayList<>();
        String msg = "";
        for (String item : ccuserList) {
            String UserName = "";// CacheHelper.Instance.GetUserNameAll(item);
            QueryWrapper<OaActinscommunicateEntity> queryWrapper = new QueryWrapper();
            queryWrapper.eq("Oa_Actins_Id", Instance.getActinsid()).eq("approversuser", item).eq("communicatetype", "6")
                    .eq("Communicate_Status", "1");
            List<OaActinscommunicateEntity> tappActinscommunicateList = tappActinscommunicateDao.getBaseMapper().selectList(queryWrapper);
            if (!(tappActinscommunicateList.isEmpty() || tappActinscommunicateList.size() == 0)) {
                msg += UserName + "[" + item + "]、";
            } else {
                copySendUser.add(item);
                copySendUserName.add(UserName);
            }
        }
        QueryWrapper<OaActinscommunicateEntity> queryWrapper = new QueryWrapper();
        queryWrapper.eq("Oa_Actins_Id", Instance.getActinsid()).eq("communicatetype", "5")
                .eq("Communicate_Status", "1");//等于1 未回复 等于2 已回复
        List<OaActinscommunicateEntity> tappActinscommunicateList = tappActinscommunicateDao.getBaseMapper().selectList(queryWrapper);
        if (tappActinscommunicateList.isEmpty()) {
            for (int i = 0; i < copySendUser.size(); i++) {
                OaActinscommunicateEntity aice = new OaActinscommunicateEntity();
                aice.setOaActinsId(Instance.getActinsid());
                aice.setOaProcessinsId(Instance.getProcinsid());
                aice.setActname(Instance.getCurrentactentity().getActname());
                aice.setCommunicatetype("5");
                aice.setApproversuser(copySendUser.get(i));
                aice.setCommunicateStatus("1");
                aice.setCreateBy(Instance.getCureentuser().getUserCode());
                aice.setCreateDate(new Date());
                aice.setUpdateBy(Instance.getCureentuser().getUserCode());
                aice.setUpdateDate(new Date());
                Add(aice, "add", "TappActinscommunicate");
                Instance.AddProcessSendNotice(aice.getApproversuser(), "4");
            }
            OaActinsEntity actInsEntity = tappActinsService.getById(Instance.getActinsid());
            if (actInsEntity != null) {
                actInsEntity.setActinsStatus("0");
                actInsEntity.setUpdateBy(Instance.getCureentuser().getUserCode());
                actInsEntity.setUpdateDate(new Date());
                Add(actInsEntity, "edit", "TappActins");
            }
            Instance.getTappPorcessins().setPorcessinsStatus("2");
            Add(Instance.getTappPorcessins(), "edit", "TappPorcessins");
//            ///发送沟通邮件
//            EmailHelper emial = new EmailHelper(procInstanceEntity, null, dataList);
//            emial.LaunchCommunicationEmail(procInstanceEntity, CacheHelper.Instance.GetUserName(procInstanceEntity.CureentUserID));
            return SaveData();
        } else {
            Instance.addErrorlog("请勿重复发起沟通！");
            return false;

        }
    }

    /// <summary>
    /// 回复沟通
    /// </summary>
    /// <param name="procInstanceEntity">处理流程数据对象 包含当前节点信息，当前流程实例信息，表单信息等等</param>
    /// <returns></returns>
    public Boolean AnswerCommunication() {
        QueryWrapper<OaActinscommunicateEntity> queryWrapper = new QueryWrapper();
        queryWrapper.eq("Oa_Actins_Id", Instance.getActinsid()).eq("communicatetype", "5")
                .eq("Communicate_Status", "1");//等于1 未回复 等于2 已回复
        List<OaActinscommunicateEntity> tappActinscommunicateList = tappActinscommunicateDao.getBaseMapper().selectList(queryWrapper);
        if (!tappActinscommunicateList.isEmpty()) {
            Integer idex = 0;

            for (OaActinscommunicateEntity item : tappActinscommunicateList) {
                if (item.getApproversuser().equals(Instance.getCureentuser().getUserCode())) {
                    item.setCommunicateStatus("2");
                    item.setCompleteddate(new Date());
                    item.setUpdateDate(new Date());
                    item.setUpdateBy(Instance.getCureentuser().getUserCode());
                    Add(item, "edit", "TappActinscommunicate");
                } else {
                    idex++;
                }
            }
            if (idex == 0) {
                OaActinsEntity tappActins = tappActinsService.getById(Instance.getActinsid());
                tappActins.setActinsStatus("1");
                tappActins.setUpdateDate(new Date());
                tappActins.setUpdateBy(Instance.getCureentuser().getUserCode());
                Add(tappActins, "edit", "TappActins");
            }
            Instance.getTappPorcessins().setPorcessinsStatus("2");
            Add(Instance.getTappPorcessins(), "edit", "TappPorcessins");
            return SaveData();
        } else {
            Instance.addErrorlog("可能已经沟通过了！");
            return false;
        }

    }
}
