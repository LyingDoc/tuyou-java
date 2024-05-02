package com.ruoyi.flow.oa.web;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.flow.comm.SpringBeanUtils;
import com.ruoyi.flow.vo.R;
import com.ruoyi.flow.oa.entity.*;
import com.ruoyi.flow.oa.req.InterfaceHandleProcessReq;
import com.ruoyi.flow.oa.req.ProcessInfoReq;
import com.ruoyi.flow.oa.service.impl.*;
import com.ruoyi.flow.oa.vo.HandleProcessVO;
import com.ruoyi.flow.oa.vo.OperationInfoVO;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/flow/oa/processInsService")
public class ProcessInsServiceController extends BaseController {

    @Autowired
    private OaActinsServiceImpl oaActinsService;
    @Resource
    public OaProcessServiceImpl oaProcessService;

    @Resource
    public OaPorcessinsServiceImpl oaPorcessinsService;
    @Resource
    private OaActServiceImpl oaActService;
    @Autowired
    private SysOaUserServiceImpl userService;

    @Autowired
    private  OaFromdataServiceImpl oaFromdataService;
    @Autowired
    private  OaProcesschartServiceImpl oaProcesschartService;
    @Autowired
    private SpringBeanUtils springBeanUtils;
    @Autowired
    private OaCommonnoServiceImpl oaCommonnoService;
    @Log(title = "发起流程入口", businessType = BusinessType.FLOW)
    @PostMapping("startProcess")
    @ResponseBody
    public R<String> startProcess(@RequestBody InterfaceHandleProcessReq processInfoReq) {
        if(StringUtils.isEmpty(processInfoReq.getProcCode())&& StringUtils.isEmpty(processInfoReq.getProcBID())){
          return R.newError("流程编码和流程BID不能同时为空！");
        }
        if(processInfoReq.getOperationInfo()==null){
            return R.newError("当前处理人不能为空！");
        }
        if(processInfoReq.getFromdata()==null){
            return R.newError("表单内容不能为空！");
        }
        if(StringUtils.isEmpty(processInfoReq.getOperationInfo().getUserCode())){
            return R.newError("当前处理人不能为空！");
        }
        OaProcessEntity oaProcess=null;
        if(StringUtils.isNotBlank(processInfoReq.getProcCode())){
            oaProcess= oaProcessService.getMaxVersionProcessCode(processInfoReq.getProcCode());
        }else{
            oaProcess= oaProcessService.getMaxVersionProcess(processInfoReq.getProcBID());
        }
        if(oaProcess==null){
            return R.newError("未找到流程模板！");
        }
        OaActEntity oaAct= oaActService.queryByProcessIdActchartId(oaProcess.getOaProcessId(),"F_Start");
        HandleProcessVO param=new HandleProcessVO();
        param.setActID(oaAct.getOaActId());
        param.setActionName("发起");
        param.setActionType("Start");
        param.setProcname(oaProcess.getProcessname());
        param.setProcID(oaProcess.getOaProcessId());

        SysUser employee= userService.selectUserByUserName(processInfoReq.getOperationInfo().getUserCode());
        if(employee==null){
            return R.newError(processInfoReq.getOperationInfo().getUserCode()+"用户信息找不到,无法提交！");
        }
        if(processInfoReq.getFromdata()==null) {
            param.setFromdata(new JSONObject());
        }else{
            param.setFromdata(processInfoReq.getFromdata());
        }
        OaProcesschartEntity oaProcesschart=oaProcesschartService.queryByProcessId(oaProcess.getOaProcessId());
        ProcessInfoReq processChartInfoReq = JSON.parseObject(oaProcesschart.getFlowdata(),ProcessInfoReq.class);
        param.getFromdata().put("Header_formNumber",oaCommonnoService.GetProcInstNumber(processChartInfoReq.getBusinessMoudle(),processChartInfoReq.getBusinessModuleID()));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        param.getFromdata().put("Header_CreateDate",df.format(new Date()));
        param.getFromdata().put("Header_applicantUserName",employee.getNickName());
        param.getFromdata().put("Header_applicantUserID",processInfoReq.getOperationInfo().getUserCode());
        param.getFromdata().put("Header_CreateUserName",employee.getNickName());
        param.getFromdata().put("Header_CreateUserID",processInfoReq.getOperationInfo().getUserCode());
        param.getFromdata().put("Header_applicantUser_DepID",employee.getDept().getDeptId());
        param.getFromdata().put("Header_applicantUser_DepName",employee.getDept().getDeptName());
        Long newid= IdWorker.getId();
        param.getFromdata().put("$tableNewId",newid.toString());
        param.setOperationInfo(new OperationInfoVO());
        param.setCurrentUserCode(processInfoReq.getOperationInfo().getUserCode());
        return     springBeanUtils.getBean(ProcessHandleServiceImpl.class).HandleProc(param);
    }
    @Log(title = "驳回流程入口", businessType = BusinessType.FLOW)
    @PostMapping("rejectProcess")
    @ResponseBody
    public R<String> rejectProcess(@RequestBody InterfaceHandleProcessReq processInfoReq) {
        if(StringUtils.isEmpty(processInfoReq.getProcInsID())){
            return R.newError("流程实例ID不能为空！");
        }
        if(processInfoReq.getOperationInfo()==null){
            return R.newError("驳回人和驳回意见不能为空！");
        }
        if(StringUtils.isEmpty(processInfoReq.getOperationInfo().getUserCode())){
            return R.newError("驳回人不能为空！");
        }
        if(StringUtils.isEmpty(processInfoReq.getOperationInfo().getApprovedDes())){
            return R.newError("驳回意见不能为空!");
        }
        SysUser employee= userService.selectUserByUserName(processInfoReq.getOperationInfo().getUserCode());
        if(employee==null){
            return R.newError(processInfoReq.getOperationInfo().getUserCode()+"用户信息找不到,无法提交！");
        }
        OaPorcessinsEntity oaProcessins=  oaPorcessinsService.getById(processInfoReq.getProcInsID());
        if(oaProcessins==null){
            return R.newError("未找到流程实例数据!");
        }
       List<OaActinsEntity> oaActinsList= oaActinsService.queryTodoActins(processInfoReq.getProcInsID());
       List<OaActinsEntity> oaActins= oaActinsList.stream().filter(ee->ee.getApproversuser().equals(processInfoReq.getOperationInfo().getUserCode())).collect(Collectors.toList());
       if(oaActins.size()==0){
           return R.newError("驳回人没有权限驳回！");
       }
        OaActinsEntity actIns=oaActins.get(0);
        HandleProcessVO param=new HandleProcessVO();
        param.setActID(actIns.getOaActId());
        param.setActInsID(actIns.getOaActinsId());
        param.setActionName("驳回");
        param.setActionType("Sendback");
        param.setProcname(oaProcessins.getProcname());
        param.setProcID(oaProcessins.getOaProcessId());
        param.setProcInsID(oaProcessins.getOaPorcessinsId());
        if(processInfoReq.getFromdata()==null){
            OaFromdataEntity oaFromdata=  oaFromdataService.getById(oaProcessins.getOaFromdataId());
            param.setFromdata(JSONObject.fromObject(oaFromdata.getFromdata()));
        }else{
            OaFromdataEntity oaFromdata=  oaFromdataService.getById(oaProcessins.getOaFromdataId());
            param.setFromdata(JSONObject.fromObject(oaFromdata.getFromdata()));
            Iterator<String> it = processInfoReq.getFromdata().keys();
            while (it.hasNext()) {
                String key = it.next();  // 获得key
                Object value = processInfoReq.getFromdata().get(key);
                param.getFromdata().put(key, value);
            }
        }
        param.setOperationInfo(new OperationInfoVO());
        param.getOperationInfo().setApprovedDes(processInfoReq.getOperationInfo().getApprovedDes());
        param.setCurrentUserCode(processInfoReq.getOperationInfo().getUserCode());
        return  springBeanUtils.getBean(ProcessHandleServiceImpl.class).HandleProc(param);
    }
    @Log(title = "审批流程入口", businessType = BusinessType.FLOW)
    @PostMapping("approvalProcess")
    @ResponseBody
    public R<String> approvalProcess(@RequestBody InterfaceHandleProcessReq processInfoReq) {
        if(StringUtils.isEmpty(processInfoReq.getProcInsID())){
            return R.newError("流程实例ID不能为空！");
        }
        if(processInfoReq.getOperationInfo()==null){
            return R.newError("审批人和审批意见不能为空！");
        }
        if(StringUtils.isEmpty(processInfoReq.getOperationInfo().getUserCode())){
            return R.newError("审批人不能为空！");
        }
        SysUser employee= userService.selectUserByUserName(processInfoReq.getOperationInfo().getUserCode());
        if(employee==null){
            return R.newError(processInfoReq.getOperationInfo().getUserCode()+"用户信息找不到,无法提交！");
        }
        if(StringUtils.isEmpty(processInfoReq.getOperationInfo().getApprovedDes())){
            return R.newError("审批意见不能为空!");
        }
        OaPorcessinsEntity oaProcessins=  oaPorcessinsService.getById(processInfoReq.getProcInsID());
        if(oaProcessins==null){
            return R.newError("未找到流程实例数据!");
        }
        List<OaActinsEntity> oaActinsList= oaActinsService.queryTodoActins(processInfoReq.getProcInsID());
        List<OaActinsEntity> oaActins= oaActinsList.stream().filter(ee->ee.getApproversuser().equals(processInfoReq.getOperationInfo().getUserCode())).collect(Collectors.toList());
        if(oaActins.size()==0){
            return R.newError("审批人没有权限操作！");
        }
        OaActinsEntity actIns=oaActins.get(0);
        HandleProcessVO param=new HandleProcessVO();
        param.setActID(actIns.getOaActId());
        param.setActInsID(actIns.getOaActinsId());
        param.setActionName("审批");
        param.setActionType("Approval");
        param.setProcname(oaProcessins.getProcname());
        param.setProcID(oaProcessins.getOaProcessId());
        param.setProcInsID(oaProcessins.getOaPorcessinsId());
        if(param.getFromdata()==null){
            OaFromdataEntity oaFromdata=  oaFromdataService.getById(oaProcessins.getOaFromdataId());
            param.setFromdata(JSONObject.fromObject(oaFromdata.getFromdata()));
        }else{
            OaFromdataEntity oaFromdata=  oaFromdataService.getById(oaProcessins.getOaFromdataId());
            param.setFromdata(JSONObject.fromObject(oaFromdata.getFromdata()));
            Iterator<String> it = processInfoReq.getFromdata().keys();
            while (it.hasNext()) {
                String key = it.next();  // 获得key
                Object value = processInfoReq.getFromdata().get(key);
                param.getFromdata().put(key, value);
            }
        }
        param.setOperationInfo(new OperationInfoVO());
        param.getOperationInfo().setApprovedDes(processInfoReq.getOperationInfo().getApprovedDes());
        param.setCurrentUserCode(processInfoReq.getOperationInfo().getUserCode());
        return  springBeanUtils.getBean(ProcessHandleServiceImpl.class).HandleProc(param);
    }
    @Log(title = "废弃流程入口", businessType = BusinessType.FLOW)
    @PostMapping("abandonProcess")
    @ResponseBody
    public R<String> abandonProcess(@RequestBody InterfaceHandleProcessReq processInfoReq) {
        if(StringUtils.isEmpty(processInfoReq.getProcInsID())){
            return R.newError("流程实例ID不能为空！");
        }
        if(processInfoReq.getOperationInfo()==null){
            return R.newError("废弃人和废弃意见不能为空！");
        }
        if(StringUtils.isEmpty(processInfoReq.getOperationInfo().getUserCode())){
            return R.newError("废弃人不能为空！");
        }
        SysUser employee= userService.selectUserByUserName(processInfoReq.getOperationInfo().getUserCode());
        if(employee==null){
            return R.newError(processInfoReq.getOperationInfo().getUserCode()+"用户信息找不到,无法提交！");
        }
        if(StringUtils.isEmpty(processInfoReq.getOperationInfo().getApprovedDes())){
            return R.newError("废弃意见不能为空!");
        }
        OaPorcessinsEntity oaProcessins=  oaPorcessinsService.getById(processInfoReq.getProcInsID());
        if(oaProcessins==null){
            return R.newError("未找到流程实例数据!");
        }
        List<OaActinsEntity> oaActinsList= oaActinsService.queryTodoActins(processInfoReq.getProcInsID());
        List<OaActinsEntity> oaActins= oaActinsList.stream().filter(ee->ee.getApproversuser().equals(processInfoReq.getOperationInfo().getUserCode())).collect(Collectors.toList());
        if(oaActins.size()==0){
            return R.newError("废弃人没有权限操作！");
        }
        OaActinsEntity actIns=oaActins.get(0);
        HandleProcessVO param=new HandleProcessVO();
        param.setActID(actIns.getOaActId());
        param.setActInsID(actIns.getOaActinsId());
        param.setActionName("废弃");
        param.setActionType("Abandon");
        param.setProcname(oaProcessins.getProcname());
        param.setProcID(oaProcessins.getOaProcessId());
        param.setProcInsID(oaProcessins.getOaPorcessinsId());
        if(param.getFromdata()==null){
            OaFromdataEntity oaFromdata=  oaFromdataService.getById(oaProcessins.getOaFromdataId());
            param.setFromdata(JSONObject.fromObject(oaFromdata.getFromdata()));
        }else{
            OaFromdataEntity oaFromdata=  oaFromdataService.getById(oaProcessins.getOaFromdataId());
            param.setFromdata(JSONObject.fromObject(oaFromdata.getFromdata()));
            Iterator<String> it = processInfoReq.getFromdata().keys();
            while (it.hasNext()) {
                String key = it.next();  // 获得key
                Object value = processInfoReq.getFromdata().get(key);
                param.getFromdata().put(key, value);
            }
        }
        param.setOperationInfo(new OperationInfoVO());
        param.getOperationInfo().setApprovedDes(processInfoReq.getOperationInfo().getApprovedDes());
        param.setCurrentUserCode(processInfoReq.getOperationInfo().getUserCode());
        return  springBeanUtils.getBean(ProcessHandleServiceImpl.class).HandleProc(param);
    }
    @Log(title = "撤销流程入口", businessType = BusinessType.FLOW)
    @PostMapping("revokeProcess")
    @ResponseBody
    public R<String> revokeProcess(@RequestBody InterfaceHandleProcessReq processInfoReq) {
        if(StringUtils.isEmpty(processInfoReq.getProcInsID())){
            return R.newError("流程实例ID不能为空！");
        }
        if(processInfoReq.getOperationInfo()==null){
            return R.newError("撤销人和撤销意见不能为空！");
        }
        if(StringUtils.isEmpty(processInfoReq.getOperationInfo().getUserCode())){
            return R.newError("撤销人不能为空！");
        }
        SysUser employee= userService.selectUserByUserName(processInfoReq.getOperationInfo().getUserCode());
        if(employee==null){
            return R.newError(processInfoReq.getOperationInfo().getUserCode()+"用户信息找不到,无法提交！");
        }
        if(StringUtils.isEmpty(processInfoReq.getOperationInfo().getApprovedDes())){
            return R.newError("撤销意见不能为空!");
        }
        OaPorcessinsEntity oaProcessins=  oaPorcessinsService.getById(processInfoReq.getProcInsID());
        if(oaProcessins==null){
            return R.newError("未找到流程实例数据!");
        }
        List<OaActinsEntity> oaActinsList= oaActinsService.queryTodoActins(processInfoReq.getProcInsID());
        List<OaActinsEntity> oaActins= oaActinsList.stream().filter(ee->ee.getApproversuser().equals(processInfoReq.getOperationInfo().getUserCode())).collect(Collectors.toList());
        if(oaActins.size()==0){
            return R.newError("撤销人没有权限操作！");
        }
        OaActinsEntity actIns=oaActins.get(0);
        HandleProcessVO param=new HandleProcessVO();
        param.setActID(actIns.getOaActId());
        param.setActInsID(actIns.getOaActinsId());
        param.setActionName("撤销");
        param.setActionType("Repeal");
        param.setProcname(oaProcessins.getProcname());
        param.setProcID(oaProcessins.getOaProcessId());
        param.setProcInsID(oaProcessins.getOaPorcessinsId());
        if(param.getFromdata()==null){
            OaFromdataEntity oaFromdata=  oaFromdataService.getById(oaProcessins.getOaFromdataId());
            param.setFromdata(JSONObject.fromObject(oaFromdata.getFromdata()));
        }else{
            OaFromdataEntity oaFromdata=  oaFromdataService.getById(oaProcessins.getOaFromdataId());
            param.setFromdata(JSONObject.fromObject(oaFromdata.getFromdata()));
            Iterator<String> it = processInfoReq.getFromdata().keys();
            while (it.hasNext()) {
                String key = it.next();  // 获得key
                Object value = processInfoReq.getFromdata().get(key);
                param.getFromdata().put(key, value);
            }
        }
        param.setOperationInfo(new OperationInfoVO());
        param.getOperationInfo().setApprovedDes(processInfoReq.getOperationInfo().getApprovedDes());
        param.setCurrentUserCode(processInfoReq.getOperationInfo().getUserCode());
        return  springBeanUtils.getBean(ProcessHandleServiceImpl.class).HandleProc(param);
    }
    @Log(title = "转办流程入口", businessType = BusinessType.FLOW)
    @PostMapping("transferProcess")
    @ResponseBody
    public R<String> transferProcess(@RequestBody InterfaceHandleProcessReq processInfoReq) {
        if(StringUtils.isEmpty(processInfoReq.getProcInsID())){
            return R.newError("流程实例ID不能为空！");
        }
        if(processInfoReq.getOperationInfo()==null){
            return R.newError("转办对象不能为空！");
        }
        if(StringUtils.isEmpty(processInfoReq.getOperationInfo().getUserCode())){
            return R.newError("处理人不能为空！");
        }
        SysUser employee= userService.selectUserByUserName(processInfoReq.getOperationInfo().getUserCode());
        if(employee==null){
            return R.newError(processInfoReq.getOperationInfo().getUserCode()+"用户信息找不到,无法提交！");
        }
        if(StringUtils.isEmpty(processInfoReq.getOperationInfo().getTransferUser())){
            return R.newError("转办人不能为空！");
        }

        SysUser  employee2=userService.selectUserByUserName(processInfoReq.getOperationInfo().getTransferUser());
        if(employee2==null){
            return R.newError(processInfoReq.getOperationInfo().getTransferUser()+"转办人信息找不到,无法提交！");
        }
        if(StringUtils.isEmpty(processInfoReq.getOperationInfo().getApprovedDes())){
            return R.newError("转办意见不能为空!");
        }
        OaPorcessinsEntity oaProcessins=  oaPorcessinsService.getById(processInfoReq.getProcInsID());
        if(oaProcessins==null){
            return R.newError("未找到流程实例数据!");
        }
        List<OaActinsEntity> oaActinsList= oaActinsService.queryTodoActins(processInfoReq.getProcInsID());
        List<OaActinsEntity> oaActins= oaActinsList.stream().filter(ee->ee.getApproversuser().equals(processInfoReq.getOperationInfo().getUserCode())).collect(Collectors.toList());
        if(oaActins.size()==0){
            return R.newError("转办人没有权限操作！");
        }
        OaActinsEntity actIns=oaActins.get(0);
        HandleProcessVO param=new HandleProcessVO();
        param.setActID(actIns.getOaActId());
        param.setActInsID(actIns.getOaActinsId());
        param.setActionName("转办");
        param.setActionType("Tranfer");
        param.setProcname(oaProcessins.getProcname());
        param.setProcID(oaProcessins.getOaProcessId());
        param.setProcInsID(oaProcessins.getOaPorcessinsId());
        if(param.getFromdata()==null){
            OaFromdataEntity oaFromdata=  oaFromdataService.getById(oaProcessins.getOaFromdataId());
            param.setFromdata(JSONObject.fromObject(oaFromdata.getFromdata()));
        }else{
            OaFromdataEntity oaFromdata=  oaFromdataService.getById(oaProcessins.getOaFromdataId());
            param.setFromdata(JSONObject.fromObject(oaFromdata.getFromdata()));
            Iterator<String> it = processInfoReq.getFromdata().keys();
            while (it.hasNext()) {
                String key = it.next();  // 获得key
                Object value = processInfoReq.getFromdata().get(key);
                param.getFromdata().put(key, value);
            }
        }
        param.setOperationInfo(new OperationInfoVO());
        param.getOperationInfo().setApprovedDes(processInfoReq.getOperationInfo().getApprovedDes());
        param.getOperationInfo().setRedritUser(processInfoReq.getOperationInfo().getTransferUser());
        param.setCurrentUserCode(processInfoReq.getOperationInfo().getUserCode());
        return  springBeanUtils.getBean(ProcessHandleServiceImpl.class).HandleProc(param);
    }
    @Log(title = "跳转流程入口", businessType = BusinessType.FLOW)
    @PostMapping("skipProcess")
    @ResponseBody
    public R<String> skipProcess(@RequestBody InterfaceHandleProcessReq interfaceHandleProcessReq) {
        if(StringUtils.isEmpty(interfaceHandleProcessReq.getProcInsID())){
            return R.newError("流程实例ID不能为空！");
        }
        if(interfaceHandleProcessReq.getOperationInfo()==null){
            return R.newError("跳转对象不能为空！");
        }
        if(StringUtils.isEmpty(interfaceHandleProcessReq.getOperationInfo().getUserCode())){
            return R.newError("处理人不能为空！");
        }
        SysUser employee= userService.selectUserByUserName(interfaceHandleProcessReq.getOperationInfo().getUserCode());
        if(employee==null){
            return R.newError(interfaceHandleProcessReq.getOperationInfo().getUserCode()+"处理用户信息找不到,无法提交！");
        }
        if(StringUtils.isEmpty(interfaceHandleProcessReq.getOperationInfo().getApprovedDes())){
            return R.newError("跳转意见不能为空!");
        }
        if(StringUtils.isEmpty(interfaceHandleProcessReq.getOperationInfo().getNodeno())){
            return R.newError("跳转节点编码不能为空!");
        }
        if(StringUtils.isEmpty(interfaceHandleProcessReq.getOperationInfo().getNodename())){
            return R.newError("跳转节点名称不能为空!");
        }
        OaPorcessinsEntity oaProcessins=  oaPorcessinsService.getById(interfaceHandleProcessReq.getProcInsID());
        if(oaProcessins==null){
            return R.newError("未找到流程实例数据!");
        }
        OaProcesschartEntity oaProcesschart=oaProcesschartService.queryByProcessId(oaProcessins.getOaProcessId());
        if(oaProcesschart==null){
            return R.newError("找不到流程模板!");
        }
        ProcessInfoReq  processInfoReq = JSON.parseObject(oaProcesschart.getFlowdata(),ProcessInfoReq.class);
        Long nodecount=  processInfoReq.getNodeList().stream().filter(ee->interfaceHandleProcessReq.getOperationInfo().getNodeno().equals(ee.getId())).count();
        if(nodecount<=0){
            return R.newError("跳转的节点找不到!");
        }
        List<OaActinsEntity> oaActinsList= oaActinsService.queryTodoActins(interfaceHandleProcessReq.getProcInsID());
        List<OaActinsEntity> oaActins= oaActinsList.stream().filter(ee->ee.getApproversuser().equals(interfaceHandleProcessReq.getOperationInfo().getUserCode())).collect(Collectors.toList());
        if(oaActins.size()==0){
            return R.newError("处理人没有权限操作！");
        }
        OaActinsEntity actIns=oaActins.get(0);
        HandleProcessVO param=new HandleProcessVO();
        param.setActID(actIns.getOaActId());
        param.setActInsID(actIns.getOaActinsId());
        param.setActionName("跳转");
        param.setActionType("FlowSkip");
        param.setProcname(oaProcessins.getProcname());
        param.setProcID(oaProcessins.getOaProcessId());
        param.setProcInsID(oaProcessins.getOaPorcessinsId());
        if(param.getFromdata()==null){
            OaFromdataEntity oaFromdata=  oaFromdataService.getById(oaProcessins.getOaFromdataId());
            param.setFromdata(JSONObject.fromObject(oaFromdata.getFromdata()));
        }else{
            OaFromdataEntity oaFromdata=  oaFromdataService.getById(oaProcessins.getOaFromdataId());
            param.setFromdata(JSONObject.fromObject(oaFromdata.getFromdata()));
            Iterator<String> it = interfaceHandleProcessReq.getFromdata().keys();
            while (it.hasNext()) {
                String key = it.next();  // 获得key
                Object value = interfaceHandleProcessReq.getFromdata().get(key);
                param.getFromdata().put(key, value);
            }
        }
        param.setOperationInfo(new OperationInfoVO());
        param.getOperationInfo().setApprovedDes(interfaceHandleProcessReq.getOperationInfo().getApprovedDes());
        param.getOperationInfo().setRedritUser(interfaceHandleProcessReq.getOperationInfo().getTransferUser());
        param.setCurrentUserCode(interfaceHandleProcessReq.getOperationInfo().getUserCode());
        return  springBeanUtils.getBean(ProcessHandleServiceImpl.class).HandleProc(param);
    }
    @Log(title = "抄送流程入口", businessType = BusinessType.FLOW)
    @PostMapping("ccProcess")
    @ResponseBody
    public R<String> ccProcess(@RequestBody InterfaceHandleProcessReq processInfoReq) {
        if(StringUtils.isEmpty(processInfoReq.getProcInsID())){
            return R.newError("流程实例ID不能为空！");
        }
        if(processInfoReq.getOperationInfo()==null){
            return R.newError("抄送对象不能为空！");
        }
        if(StringUtils.isEmpty(processInfoReq.getOperationInfo().getUserCode())){
            return R.newError("处理人不能为空！");
        }
        SysUser employee= userService.selectUserByUserName(processInfoReq.getOperationInfo().getUserCode());
        if(employee==null){
            return R.newError(processInfoReq.getOperationInfo().getUserCode()+"处理用户信息找不到,无法提交！");
        }
        if(StringUtils.isEmpty(processInfoReq.getOperationInfo().getApprovedDes())){
            return R.newError("抄送意见不能为空!");
        }
        if(processInfoReq.getOperationInfo().getCopyUserlist()!=null&&processInfoReq.getOperationInfo().getCopyUserlist().size()>0){
            return R.newError("抄送人不能为空!");
        }
        OaPorcessinsEntity oaProcessins=  oaPorcessinsService.getById(processInfoReq.getProcInsID());
        if(oaProcessins==null){
            return R.newError("未找到流程实例数据!");
        }
        List<OaActinsEntity> oaActinsList= oaActinsService.queryTodoActins(processInfoReq.getProcInsID());
        List<OaActinsEntity> oaActins= oaActinsList.stream().filter(ee->ee.getApproversuser().equals(processInfoReq.getOperationInfo().getUserCode())).collect(Collectors.toList());
        if(oaActins.size()==0){
            return R.newError("处理人没有权限操作！");
        }
        OaActinsEntity actIns=oaActins.get(0);
        HandleProcessVO param=new HandleProcessVO();
        param.setActID(actIns.getOaActId());
        param.setActInsID(actIns.getOaActinsId());
        param.setActionName("抄送");
        param.setActionType("CopySend");
        param.setProcname(oaProcessins.getProcname());
        param.setProcID(oaProcessins.getOaProcessId());
        param.setProcInsID(oaProcessins.getOaPorcessinsId());
        if(param.getFromdata()==null){
            OaFromdataEntity oaFromdata=  oaFromdataService.getById(oaProcessins.getOaFromdataId());
            param.setFromdata(JSONObject.fromObject(oaFromdata.getFromdata()));
        }else{
            OaFromdataEntity oaFromdata=  oaFromdataService.getById(oaProcessins.getOaFromdataId());
            param.setFromdata(JSONObject.fromObject(oaFromdata.getFromdata()));
            Iterator<String> it = processInfoReq.getFromdata().keys();
            while (it.hasNext()) {
                String key = it.next();  // 获得key
                Object value = processInfoReq.getFromdata().get(key);
                param.getFromdata().put(key, value);
            }

        }
        param.setOperationInfo(new OperationInfoVO());
        param.getOperationInfo().setApprovedDes(processInfoReq.getOperationInfo().getApprovedDes());
        param.getOperationInfo().setCopyUserlist(processInfoReq.getOperationInfo().getCopyUserlist());
        param.setCurrentUserCode(processInfoReq.getOperationInfo().getUserCode());
        return  springBeanUtils.getBean(ProcessHandleServiceImpl.class).HandleProc(param);
    }



}
