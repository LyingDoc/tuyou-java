package com.ruoyi.flow.oa.service.impl;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.flow.form.entity.FormFormEntity;
import com.ruoyi.flow.form.req.FromSaveInfoReq;
import com.ruoyi.flow.form.service.impl.BuildFlowMybatisInterfaceService;
import com.ruoyi.flow.form.service.impl.BuildFormMybatisInterfaceService;
import com.ruoyi.flow.form.service.impl.FormFormServiceImpl;
import com.ruoyi.flow.oa.vo.ExportFlowConfigVO;
import com.ruoyi.flow.oa.vo.ProcessChartVO;
import com.ruoyi.flow.vo.PageVO;
import com.ruoyi.flow.vo.R;
import com.ruoyi.flow.oa.entity.*;
import com.ruoyi.flow.oa.mapper.OaProcessMapper;
import com.ruoyi.flow.oa.req.NodeDetailInfoReq;
import com.ruoyi.flow.oa.req.ProcessInfoReq;
import com.ruoyi.flow.oa.req.RoutingReq;
import com.ruoyi.flow.oa.req.SaveAsFlowReq;
import com.ruoyi.flow.oa.service.IOaProcessService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类富士达范德萨都是
 * </p>
 *
 * @author 刘亚平
 * @since 2023-01-15
 */
@Service
public class OaProcessServiceImpl extends ServiceImpl<OaProcessMapper, OaProcessEntity> implements IOaProcessService {
    @Resource
    private OaActServiceImpl actService;
    @Resource
    private OaActdatapermissionServiceImpl tappActdatapermissionDao;
    @Resource
    private OaActroutingServiceImpl tappActroutingDao;
    @Resource
    private OaActoperatepermissionServiceImpl tappActoperatepermissionDao;
    @Resource
    private OaProcesschartServiceImpl tappProcesschartDao;

    @Resource
    private FormFormServiceImpl formFormService;
    @Autowired
    private BuildFormMybatisInterfaceService buildFormMybatisInterfaceService;

    @Autowired
    private BuildFlowMybatisInterfaceService buildFlowMybatisInterfaceService;

    /**
     * 通过主键获取数据
     *
     * @param fid 主键
     * @return 是否成功
     */
    @Override
    public OaProcessEntity getById(Long id) {
        return baseMapper.selectById(id);
    }

    /**
     * 通过主键删除数据
     *
     * @param fid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long fid) {
        return this.baseMapper.deleteById(fid) > 0;
    }

    /**
     * 通过主键修改数据
     *
     * @param fid 主键
     * @return 是否成功
     */
    @Override
    public R<OaProcessEntity> saveOaProcessEntity(OaProcessEntity oaprocessentity) {
        int row = 0;
        try {
            if (saveOrUpdate(oaprocessentity)) {
                return R.newOk(oaprocessentity);
            } else {
                return R.newError("保存失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.newError("[{oaprocessentity}] 未知异常");
        }

    }

    /**
     * 通过id集合批量删除
     *
     * @param fid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteOaProcessEntityList(List<Integer> ids) {
        int row = 0;
        try {
            row = this.baseMapper.deleteBatchIds(ids);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[{oaprocessentity}] 未知异常");
        }
        return row > 0;
    }

    @Override
    public R<List<OaProcessEntity>> getOaProcessEntityList(PageVO<OaProcessEntity> req) {
        Page<OaProcessEntity> page = new Page<>(req.getPage(), req.getPagesize());
        Page oaprocessentityPage = this.baseMapper.selectPage(page, new QueryWrapper<OaProcessEntity>());
        return R.newOk(oaprocessentityPage.getRecords(), oaprocessentityPage.getTotal());
    }


    public boolean validateProcesscode(String flowCode) {
        QueryWrapper<OaProcessEntity> queryWrapper = new QueryWrapper();
        queryWrapper.eq("processcode", flowCode);
        return !(this.baseMapper.selectCount(queryWrapper) > 0);

    }

    @Transactional
    public R<OaProcessEntity> SaveFlowData(ProcessInfoReq param, Integer state) {
//		String jsonString = param.get("flowJson");///流程表单数据
//		String flowdrawStr = param.get("flowdraw"); ///画的图形
//		String flowID = param.get("flowID");
//		JSONObject json = JSONObject.fromObject(jsonString);
        OaProcessEntity entity = this.getById(param.getFlowID());
        if (entity == null) {
            if (!validateProcesscode(param.getFlowCode())) {
                return R.newError("流程编码已存在,请重新输入流程编码！");
            }
            entity = new OaProcessEntity();
            if (StringUtils.isNotBlank(param.getFlowID())) {
                entity.setOaProcessId(param.getFlowID());
            }
            entity.setProcesscode(param.getFlowCode());
            entity.setProcessname(param.getFlowName());
            entity.setBusinessmoduleid(param.getBusinessModuleID());
            entity.setBusinessmoudle(param.getBusinessMoudle());
            entity.setProcesstypecode(param.getFlowType());
            entity.setIsreminder(param.getFIsEnabledAlert());
            entity.setIsendreminder(param.getFIsAlertStarter());
            entity.setIscopy(param.getFIsCopyStart());
            entity.setFremindermode(param.getFMessageType());
            entity.setDescription(param.getFRemark());
            entity.setStarttype(param.getFStartType());
            entity.setStartid(param.getFStartPerson());
            entity.setStartname(param.getFStartPersonName());
            entity.setFormFormId(param.getFromID());
            entity.setFlowicon(param.getFlowicon());
            entity.setCallbackApprovalUrl(param.getCallbackApprovalurl());
            Long newId = IdWorker.getId();
            entity.setProcessBid(newId.toString());
            entity.setFVersion(1L);
            if (state != null) {
                entity.setProcessState(state); /// 1草稿 2启动，3停用
            } else {
                entity.setProcessState(1);
            }
            this.baseMapper.insert(entity);
        } else {
            entity.setProcesscode(param.getFlowCode());
            entity.setProcessname(param.getFlowName());
            entity.setBusinessmoduleid(param.getBusinessModuleID());
            entity.setBusinessmoudle(param.getBusinessMoudle());
            entity.setProcesstypecode(param.getFlowType());
            entity.setIsreminder(param.getFIsEnabledAlert());
            entity.setIsendreminder(param.getFIsAlertStarter());
            entity.setIscopy(param.getFIsCopyStart());
            entity.setFremindermode(param.getFMessageType());
            entity.setDescription(param.getFRemark());
            entity.setStarttype(param.getFStartType());
            entity.setStartid(param.getFStartPerson());
            entity.setStartname(param.getFStartPersonName());
            entity.setFormFormId(param.getFromID());
            entity.setFlowicon(param.getFlowicon());
            entity.setCallbackApprovalUrl(param.getCallbackApprovalurl());
            if (state != null)
                entity.setProcessState(state); /// 1草稿 2启动，3停用
            this.baseMapper.updateById(entity);
        }
        //SaveProcessEmail(entity.getFid(), json.getJSONArray("EmailConfig"));
        SaveAct(entity.getOaProcessId(), param);
        SaveProcesschart(entity.getOaProcessId(), param);

        return R.newOk(entity);
    }

    /**
     * 保存节点信息
     *
     * @param proessID
     * @param
     * @return
     */
    Boolean SaveAct(String proessID, ProcessInfoReq param) {
        List<OaActEntity> actList = actService.queryByProcessId(proessID);
        if (param.getNodeList() != null && param.getNodeList().size() > 0) {
            param.getNodeList().forEach(ee -> {
                if (!"F_End".equals(ee.getId())) {
                    OaActEntity tappAct = new OaActEntity();
                    List<OaActEntity> findlist = actList.stream().filter(ff -> ff.getActchartId().equals(ee.getId())).collect(Collectors.toList());
                    if (findlist.size() > 0) {
                        tappAct = findlist.get(0);
                        tappAct.setActname(ee.getNodeinfo().getFNodeName());
                        tappAct.setApprovaltype(ee.getNodeinfo().getFApprovalType());
                        tappAct.setActchartId(ee.getNodeinfo().getFActChartID());
                        if ("2".equals(tappAct.getApprovaltype())) {
                            tappAct.setApprover(ee.getNodeinfo().getFApprover());
                            tappAct.setApprovername(ee.getNodeinfo().getFApproverName());
                        } else {
                            tappAct.setApprovername("");
                            tappAct.setApprover(ee.getNodeinfo().getFApprovalObj());
                        }
                        tappAct.setCcPerson(ee.getNodeinfo().getFCCPerson());
                        tappAct.setHandlecountersigntype(ee.getNodeinfo().getFHandleCountersignType());
                        tappAct.setIscontinuousby(ee.getNodeinfo().getFIsContinuousBy());
                        tappAct.setIsnotcontinuousby(ee.getNodeinfo().getFIsNotContinuousBy());
                        tappAct.setIsrejectemail(ee.getNodeinfo().getFIsRejectEmail());
                        tappAct.setIsrejecthistory(ee.getNodeinfo().getFIsRejectHistory());
                        tappAct.setIsemail(ee.getNodeinfo().getFIsEmail());
                        tappAct.setHandletype(ee.getNodeinfo().getFHandleType());
                        tappAct.setCountersigntype(ee.getNodeinfo().getFCounterSignType());
                        actService.getBaseMapper().updateById(tappAct);
                    } else {
                        tappAct.setActname(ee.getNodeinfo().getFNodeName());
                        tappAct.setApprovaltype(ee.getNodeinfo().getFApprovalType());
                        tappAct.setActchartId(ee.getNodeinfo().getFActChartID());
                        if ("2".equals(tappAct.getApprovaltype())) {
                            tappAct.setApprover(ee.getNodeinfo().getFApprover());
                            tappAct.setApprovername(ee.getNodeinfo().getFApproverName());
                        } else {
                            tappAct.setApprovername(ee.getNodeinfo().getFApproverName());
                            tappAct.setApprover(ee.getNodeinfo().getFApprovalObj());
                        }
                        tappAct.setCcPerson(ee.getNodeinfo().getFCCPerson());
                        tappAct.setHandlecountersigntype(ee.getNodeinfo().getFHandleCountersignType());
                        tappAct.setIscontinuousby(ee.getNodeinfo().getFIsContinuousBy());
                        tappAct.setIsnotcontinuousby(ee.getNodeinfo().getFIsNotContinuousBy());
                        tappAct.setIsrejectemail(ee.getNodeinfo().getFIsRejectEmail());
                        tappAct.setIsrejecthistory(ee.getNodeinfo().getFIsRejectHistory());
                        tappAct.setIsemail(ee.getNodeinfo().getFIsEmail());
                        tappAct.setHandletype(ee.getNodeinfo().getFHandleType());
                        tappAct.setCountersigntype(ee.getNodeinfo().getFCounterSignType());
                        tappAct.setProcessid(proessID);
                        actService.getBaseMapper().insert(tappAct);
                    }
                    SaveOperatePermission(tappAct.getOaActId(), ee.getNodeinfo());
                    // System.out.println("6");
                    SaveDataPermission(tappAct.getOaActId(), ee.getNodeinfo());
                    //  System.out.println("7");
                    SaveActrouting(tappAct.getOaActId(), ee.getNodeinfo(), param);
                }
            });
            List<String> nodeidList = param.getNodeList().stream().map(ee -> ee.getId()).collect(Collectors.toList());
            List<OaActEntity> delOaActList = actList.stream().filter(ee -> !nodeidList.contains(ee.getActchartId())).collect(Collectors.toList());
            delOaActList.forEach(ee -> {
                actService.deleteById(ee.getOaActId());
            });
        } else {
            actList.forEach(ee -> {
                actService.deleteById(ee.getOaActId());
            });
        }

        return true;
    }

    /**
     * 保存节点数据权限
     *
     * @param
     * @param
     * @return
     */
    Boolean SaveDataPermission(String actID, NodeDetailInfoReq nodeinfo) {
        String permissionStr = JSON.toJSONString(nodeinfo.getDataPermission());
        OaActdatapermissionEntity tappActdatapermission = this.tappActdatapermissionDao.queryByActId(actID);
        if (tappActdatapermission != null) {
            tappActdatapermission.setActdatapermission(permissionStr);
            this.tappActdatapermissionDao.getBaseMapper().updateById(tappActdatapermission);
        } else {
            OaActdatapermissionEntity entity = new OaActdatapermissionEntity();
            entity.setActdatapermission(permissionStr);
            entity.setOaActId(actID);
            this.tappActdatapermissionDao.getBaseMapper().insert(entity);
        }
        return true;
    }

    /**
     * 保存节点操作权限
     *
     * @param
     * @param
     * @return
     */
    Boolean SaveOperatePermission(String actID, NodeDetailInfoReq nodeinfo) {
        String permissionStr = JSON.toJSONString(nodeinfo.getOperatePermission());
        String attachmentPermissionJosn = JSON.toJSONString(nodeinfo.getAttachmentPermission());
        List<OaActoperatepermissionEntity> tappActoperatepermissionList = this.tappActoperatepermissionDao.queryByActId(actID);
        if (tappActoperatepermissionList.size() > 0) {
            for (Integer i = 0; i < tappActoperatepermissionList.size(); i++) {
                OaActoperatepermissionEntity entity = tappActoperatepermissionList.get(i);
                if (entity.getOperatetype().equals(1L)) {
                    entity.setActoperatepermission(permissionStr);
                    this.tappActoperatepermissionDao.getBaseMapper().updateById(entity);
                } else {
                    entity.setActoperatepermission(attachmentPermissionJosn);
                    this.tappActoperatepermissionDao.getBaseMapper().updateById(entity);
                }
            }
        } else {
            OaActoperatepermissionEntity entity = new OaActoperatepermissionEntity();
            entity.setActoperatepermission(permissionStr);
            entity.setOaActId(actID);
            entity.setOperatetype(1);
            this.tappActoperatepermissionDao.getBaseMapper().insert(entity);
            OaActoperatepermissionEntity entity2 = new OaActoperatepermissionEntity();
            entity2.setActoperatepermission(attachmentPermissionJosn);
            entity2.setOaActId(actID);
            entity2.setOperatetype(2);
            this.tappActoperatepermissionDao.getBaseMapper().insert(entity2);
        }
        return true;
    }

    /**
     * 保存路由信息
     *
     * @param
     * @param
     * @return
     */
    Boolean SaveActrouting(String actID, NodeDetailInfoReq nodeinfo, ProcessInfoReq param) {
        ///删除对应节点信息
        List<OaActroutingEntity> oaActroutingEntityList = this.tappActroutingDao.queryByActID(actID);
        for (int i = 0; i < nodeinfo.getRouting().size(); i++) {
            RoutingReq actitemjson = nodeinfo.getRouting().get(i);
            OaActroutingEntity entity = new OaActroutingEntity();
            entity.setRunActId(actitemjson.getNodeID());
            entity.setOaActId(actID);
            entity.setRunactname(actitemjson.getNodeName());
            entity.setRoutingjson(JSON.toJSONString(actitemjson.getRoutingList()));
            entity.setExpressionjson(JSON.toJSONString(actitemjson.getExpressionList()));
            entity.setOutputrules(actitemjson.getRuleName());
            param.getLineList().forEach(ee -> {
                if (ee.getLineid() != null && ee.getLineid().equals(actitemjson.getLineid())) {
                    entity.setApiUrl(ee.getApiurl());
                    entity.setAjaxType(ee.getAjaxtype());
                    entity.setSqlcontent(ee.getSqlcontent());
                    entity.setHandleType(ee.getHandletype());
                }
            });
            this.tappActroutingDao.getBaseMapper().insert(entity);
        }
        for (int i = 0; i < oaActroutingEntityList.size(); i++) {
            this.tappActroutingDao.deleteById(oaActroutingEntityList.get(i).getOaActroutingId());
        }
        return true;
    }

    Boolean SaveProcesschart(String proessID, ProcessInfoReq param) {
        QueryWrapper<OaProcesschartEntity> queryWrapper = new QueryWrapper();
        queryWrapper.eq("Oa_Process_Id", proessID);
        OaProcesschartEntity tappProcesschart = this.tappProcesschartDao.getOne(queryWrapper, false);
        if (tappProcesschart == null) {
            tappProcesschart = new OaProcesschartEntity();
            tappProcesschart.setOaProcessId(proessID);
            tappProcesschart.setFlowdata(param.getFlowjson());
            this.tappProcesschartDao.getBaseMapper().insert(tappProcesschart);
        } else {
            tappProcesschart.setFlowdata(param.getFlowjson());
            this.tappProcesschartDao.getBaseMapper().updateById(tappProcesschart);
        }
        return true;
    }

    public String SaveAsFlow(String processid, String processname, String processcode) throws Exception {
        return CopyFlow(processid, 1, processname, processcode);
    }

    public R<OaProcessEntity> optSaveAsFlow(@RequestBody SaveAsFlowReq param) {
        ProcessInfoReq processInfoReq = JSON.parseObject(param.getFlowjson(), ProcessInfoReq.class);
        processInfoReq.setFlowID("");
        processInfoReq.setFlowName(param.getProcessname());
        processInfoReq.setFlowCode(param.getProcesscode());
        JSONObject jsonparam = JSON.parseObject(param.getFlowjson());
        jsonparam.put("FlowCode", param.getProcesscode());
        jsonparam.put("FlowName", param.getProcessname());
        jsonparam.put("flowID", "");
        processInfoReq.setFlowjson(param.getFlowjson());
        return this.SaveFlowData(processInfoReq, null);
    }

    /**
     * 拷贝流程
     *
     * @param processid
     * @param state
     * @param processname
     * @param processcode
     * @return
     * @throws Exception
     */
    String CopyFlow(String processid, Integer state, String processname, String processcode) throws Exception {
        if (state == 1) {
            if (processname == null || processname.length() == 0) {
                throw new Exception("复制流程名称不能空！");
            }
            if (processcode == null || processcode.length() == 0) {
                throw new Exception("复制流程编码不能空！");
            }
            if (!CheackProcesscode(processcode)) {
                throw new Exception("流程编码已经存在！");
            }
        }
        OaProcessEntity oldentity = this.getById(processid);
        OaProcessEntity entity = new OaProcessEntity();

        if (state == 3) { ///等于3代表是停用操作  停用后将当前流程模板状态改为4
            entity.setProcesscode(oldentity.getProcesscode());
            entity.setProcessname(oldentity.getProcessname());
            entity.setProcessBid(oldentity.getProcessBid());
            oldentity.setProcessState(4);
            OaProcessEntity maxVersionProcess = getMaxVersionProcess(oldentity.getProcessBid());
            entity.setFVersion(maxVersionProcess.getFVersion() + 1);
            this.updateById(oldentity);
        } else {
            Long newid = IdWorker.getId();
            entity.setProcessBid(newid.toString());
            entity.setProcesscode(processcode);
            entity.setProcessname(processname);
            entity.setFVersion(1L);
        }
        entity.setProcesstypecode(oldentity.getProcesstypecode());
        entity.setIsreminder(oldentity.getIsreminder());
        entity.setIsendreminder(oldentity.getIsendreminder());
        entity.setIscopy(oldentity.getIscopy());
        entity.setFremindermode(oldentity.getFremindermode());
        entity.setDescription(oldentity.getDescription());
        entity.setStarttype(oldentity.getStarttype());
        entity.setStartid(oldentity.getStartid());
        entity.setStartname(oldentity.getStartname());
        entity.setFormFormId(oldentity.getFormFormId());
        entity.setFlowicon(oldentity.getFlowicon());
        entity.setProcessState(state); /// 1草稿 2启动，3停用
        entity.setCallbackApprovalUrl(oldentity.getCallbackApprovalUrl());
        this.baseMapper.insert(entity);
        OaProcesschartEntity oldprocesschart = this.tappProcesschartDao.queryByProcessId(processid);
        ProcessInfoReq param = JSON.parseObject(oldprocesschart.getFlowdata(), ProcessInfoReq.class);
        param.setFlowCode(processcode);
        param.setFlowName(processname);
        param.setFlowID(entity.getOaProcessId());
        SaveAct(entity.getOaProcessId(), param);
        OaProcesschartEntity tappProcesschart = new OaProcesschartEntity();
        JSONObject jsonparam = JSON.parseObject(oldprocesschart.getFlowdata());
        if (state != 3) {
            jsonparam.put("FlowCode", processcode);
            jsonparam.put("FlowName", processname);
            jsonparam.put("flowID", entity.getOaProcessId());
        }
//        jsonparam.put("FromID",entity.getOaProcessId());
        tappProcesschart.setOaProcessId(entity.getOaProcessId());
        tappProcesschart.setProcesschart(oldprocesschart.getProcesschart());
        tappProcesschart.setFlowdata(JSON.toJSONString(jsonparam));
        this.tappProcesschartDao.saveOrUpdate(tappProcesschart);
//        List<TappProcessEmailEntity> processemailList = tappProcessemailDao.queryByProcessID(processid);
//        for (int i = 0; i < processemailList.size(); i++) {
//            TappProcessEmailEntity oldemailitem = processemailList.get(i);
//            TappProcessEmailEntity emailitem = new TappProcessEmailEntity();
//            emailitem.setFcreateby(currentUserInfo.getUserName());
//            emailitem.setFlastupdateby(currentUserInfo.getUserName());
//            emailitem.setFcreationdate(new Date());
//            emailitem.setFlastupdatedate(new Date());
//            emailitem.setFemailid(oldemailitem.getFemailid());
//            emailitem.setFemailname(oldemailitem.getFemailname());
//            emailitem.setFemailtype(oldemailitem.getFemailtype());
//            emailitem.setFprocessid(entity.getFid());
//            this.tappProcessemailDao.insert(emailitem);
//        }
        return entity.getOaProcessId();

    }

    public Boolean CheackProcesscode(String processcode) {

        QueryWrapper<OaProcessEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("processcode", processcode);
        return !(this.baseMapper.selectCount(queryWrapper) > 0);

    }

    public String StopFlow(String processid) throws Exception {
        return CopyFlow(processid, 3, null, null);
    }

    /**
     * 启动流程模板
     *
     * @param processid
     * @return
     */
    public String StartFlow(String processid) {
        OaProcessEntity process = this.getById(processid);
        process.setProcessState(2);
        this.updateById(process);
        return processid;
    }

    public OaProcessEntity getMaxVersionProcess(String processBid) {
        return this.baseMapper.getMaxVersionProcess(processBid);
    }

    public OaProcessEntity getMaxVersionProcessCode(String processCode) {
        return this.baseMapper.getMaxVersionProcessCode(processCode);
    }
    public  OaProcessEntity  getStartMaxVersionProcess(String processBid){
        return this.baseMapper.getStartMaxVersionProcess(processBid);
    }
    public  OaProcessEntity  getStartMaxVersionProcessCode(String processCode){
        return this.baseMapper.getStartMaxVersionProcessCode(processCode);
    }
    @Transactional
    public boolean allDelete(OaProcessEntity processEntity) {
        OaProcessEntity queryOaProcess = this.getById(processEntity.getOaProcessId());
        QueryWrapper<OaProcessEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("process_Bid", queryOaProcess.getProcessBid());
        List<OaProcessEntity> processEntityList = this.getBaseMapper().selectList(queryWrapper);
        if (processEntityList != null) {
            processEntityList.forEach(ee -> {
                ee.setProcessState(5);
                this.updateById(ee);
            });
        }
        return true;
    }

    ///降版本
    @Transactional
    public void declineVersion(String oaProcessId) {
        OaProcessEntity queryOaProcess = this.getById(oaProcessId);
        OaProcessEntity maxVersionProcess = getMaxVersionProcess(queryOaProcess.getProcessBid());
        queryOaProcess.setProcessState(5);
        this.updateById(queryOaProcess);
        if (maxVersionProcess != null) {
            maxVersionProcess.setProcessState(3);
            this.updateById(maxVersionProcess);
        }
    }

    @Override
    ///将对应历史流程版本 设置为当前最新版本编辑
    public R<String> currentDraftProcess(String oaProcessId) {
        try {
            OaProcessEntity queryOaProcess = this.getById(oaProcessId);
            QueryWrapper<OaProcessEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("process_bid", queryOaProcess.getProcessBid());
            queryWrapper.eq("process_state", 3);
            Integer count = this.getBaseMapper().selectCount(queryWrapper);
            if (count > 0) {
                return R.newError("请先删除草稿的流程模型。");
            } else {
                return R.newOk(CopyFlow(oaProcessId, 3, null, null));
            }
        } catch (Exception ex) {
            return R.newError("操作失败！");
        }
    }

    @Override
    public List<OaProcessEntity> getStartProcess() {
        return baseMapper.getStartProcess();
    }

    @Override
    public ProcessChartVO getProcessChart(String processid) {
        return baseMapper.getProcessChart(processid);
    }

    @Override
    public R<List<OaProcessEntity>> queryProcess(PageVO<OaProcessEntity> oaProcessEntity) {
        int page = oaProcessEntity.getPage();
        int rows = oaProcessEntity.getPagesize();
        PageHelper.startPage(page, rows);
        List<OaProcessEntity> processChartVOList = new ArrayList<>();
        if (oaProcessEntity.getParam().getFVersion() != null) {
            processChartVOList = this.baseMapper.queryAllList(oaProcessEntity.getParam());
        } else {
            processChartVOList = this.baseMapper.queryMaxList(oaProcessEntity.getParam());
        }
        PageInfo pageInfo = new PageInfo(processChartVOList);
        return R.newOk(processChartVOList, (int) pageInfo.getTotal());
    }

    ///导出流程配置
    @Override
    public List<ExportFlowConfigVO> exportFlowConfig(List<String> flowIds) {
        QueryWrapper<OaProcesschartEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("oa_process_id", flowIds);
        List<OaProcesschartEntity> oaProcesschartEntityList = this.tappProcesschartDao.getBaseMapper().selectList(queryWrapper);
        List<ExportFlowConfigVO> returnlist = new ArrayList<>();
        QueryWrapper<OaProcessEntity> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.in("oa_process_id", flowIds);
        List<OaProcessEntity> oaProcessEntityList = this.getBaseMapper().selectList(queryWrapper1);
        oaProcesschartEntityList.forEach(ee -> {
            ExportFlowConfigVO exportFlowConfigVO = new ExportFlowConfigVO();
            exportFlowConfigVO.setFlowjson(ee.getFlowdata());
            ProcessInfoReq processInfoReq = JSONObject.parseObject(ee.getFlowdata(), ProcessInfoReq.class);
            FormFormEntity formFormEntity = formFormService.getById(processInfoReq.getFromID());
            exportFlowConfigVO.setFromSaveInfoReq(formFormService.getFromConfigInfo(formFormEntity));
            List<OaProcessEntity> findProcessList = oaProcessEntityList.stream().filter(ff -> ff.getOaProcessId().equals(ee.getOaProcessId())).collect(Collectors.toList());
            if (findProcessList.size() > 0) {
                exportFlowConfigVO.setStatus(findProcessList.get(0).getProcessState());
            }
            returnlist.add(exportFlowConfigVO);
        });
        return returnlist;
    }

    @Override
    @Transactional
    public R importFlowConfig(List<ExportFlowConfigVO> flowConfigVOList) {
        flowConfigVOList.forEach(ee -> {
            R<String> r = formFormService.SaveFromData(ee.getFromSaveInfoReq());
            String formFormId = r.getData();
            ProcessInfoReq processInfoReq = JSONObject.parseObject(ee.getFlowjson(), ProcessInfoReq.class);
            processInfoReq.setFlowjson(ee.getFlowjson().replaceAll(processInfoReq.getFromID(), formFormId));
            processInfoReq.setFromID(formFormId);
            this.SaveFlowData(processInfoReq, ee.getStatus());
        });
        return R.newOk("导入成功！");
    }
}

