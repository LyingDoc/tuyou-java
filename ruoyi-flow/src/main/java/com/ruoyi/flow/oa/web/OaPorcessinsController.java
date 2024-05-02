package com.ruoyi.flow.oa.web;


import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.flow.comm.SpringBeanUtils;
import com.ruoyi.flow.oa.vo.ProcessCurrentUserVO;
import com.ruoyi.flow.oa.vo.ProcessInsInfoVO;
import com.ruoyi.flow.vo.CurrentUserInfoVO;
import com.ruoyi.flow.vo.R;
import com.ruoyi.flow.oa.entity.OaPorcessinsEntity;
import com.ruoyi.flow.oa.entity.OaPorcessinsLogEntity;
import com.ruoyi.flow.oa.entity.OaProcauditEntity;
import com.ruoyi.flow.oa.req.LoadProcessFromInfoReq;
import com.ruoyi.flow.oa.req.QueryPorcessInsListReq;
import com.ruoyi.flow.oa.req.QueryProcessInsLogReq;
import com.ruoyi.flow.oa.service.impl.*;
import com.ruoyi.flow.oa.vo.HandleErrorProcessVO;
import com.ruoyi.flow.oa.vo.HandleProcessVO;
import com.ruoyi.flow.form.service.impl.ApiCommService;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * OaActController
 * @author 刘亚平
 * @version 2022-12-01
 */
@Controller
@RequestMapping(value = "/flow/oa/processins")
public class OaPorcessinsController extends BaseController {


    @Autowired
    private ApiCommService apiCommService;
    @Autowired
    private OaPorcessinsServiceImpl oaPorcessinsService;
    @Autowired
    private OaProcauditServiceImpl oaProcauditService;
    @Autowired
    private OaCommonnoServiceImpl oaCommonnoService;
    @Autowired
    private SpringBeanUtils springBeanUtils;
    @Autowired
    private OaPorcessinsLogServiceImpl oaPorcessinsLogService;

    @ApiOperation("加载流程表单 流程基本信息")
    @PostMapping("/loadProcessFromInfo")
    @ResponseBody
    public R<JSONObject> loadProcessFromInfo(@RequestBody LoadProcessFromInfoReq param){
        try {
            CurrentUserInfoVO currentUserInfo = apiCommService.getCurrentUserInfo();
            JSONObject result = new JSONObject();
            ProcessCurrentUserVO model = new ProcessCurrentUserVO();
            model.setUserfid( currentUserInfo.getId());// AuthIdentity.USERFID;
            model.setUserno( currentUserInfo.getUserCode());// AuthIdentity.UserName;
            model.setUsername( currentUserInfo.getUserName()); // ViewBag.USERNAME = "ds";// AuthIdentity.RealaName;
            model.setDeptid( currentUserInfo.getOfficeCode());     //ViewBag.DEPTID = "ds";//AuthIdentity.DEPTID;
            model.setDeptname(currentUserInfo.getOfficeName());  /// ViewBag.DEPTNAME = "ds";//AuthIdentity.DEPTNAME;
            model.setOrganid( currentUserInfo.getOfficeCode());     //ViewBag.DEPTID = "ds";//AuthIdentity.DEPTID;
            model.setOrganname( currentUserInfo.getOfficeName());
            model.setOrgancode( currentUserInfo.getOfficeCode());     //ViewBag.DEPTID = "ds";//AuthIdentity.DEPTID;
            model.setDeptcode(currentUserInfo.getOfficeCode());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = formatter.format(new Date());
            model.setDatetime( dateString);   //ViewBag.DateTime = DateTime.Now;
            model.setEncryptstring( "ds");   //ViewBag.EncryptString = "ds";// DESEncrypt.DesEncrypt(AuthIdentity.UserName);。
            result.put("currentUserInfo", model);
            R<ProcessInsInfoVO> r = oaPorcessinsService.GetProcessFromInfo(param.getProcessid(), param.getProcessinsid(), param.getActinsid(), currentUserInfo.getUserCode(), param.getViewmodel(),param.getProcessBid(),param.getProcessCode());
            if (r.getSuccess()) {
                result.put("Process", r.getData());
                return r.newOk(result);
            } else {
                return R.newError(r.getMsg());
            }

        } catch (Exception ex) {
//            Log.error("获取流程基本信息失败，具体错误信息：" + ex.toString());
            System.out.println("获取流程基本信息失败，具体错误信息：" + ex.toString());
            return R.newError("获取流程基本信息失败，具体错误信息：" + ex.getMessage());
        }
    }

    @PostMapping("/GetProcAudit")
    @ResponseBody
    public R<List<OaProcauditEntity>> GetProcAudit(@RequestBody OaProcauditEntity oaProcaudit){
        List<OaProcauditEntity> tappProcauditList= oaProcauditService.GetProcAudit(oaProcaudit);
        return R.newOk( tappProcauditList);
    }

    @ApiOperation("生成单号")
    @GetMapping("/getprocinstnumber")
    @ResponseBody
    public R<String> GetProcInstNumber(@RequestParam(value = "bMoudleEN", defaultValue = "") String bMoudleEN, @RequestParam(value = "bModuleID", defaultValue = "") String bModuleID) throws IOException {
        return R.newOk(oaCommonnoService.GetProcInstNumber(bMoudleEN, bModuleID));
    }
    /**
     * 处理流程入口
     *
     * @return
     */
    @Log(title = "流程处理接口", businessType = BusinessType.OTHER)
    @PostMapping("HandleProcess")
    @ResponseBody
    public R<String> HandleProcess(@RequestBody HandleProcessVO param) throws Exception {
        return  springBeanUtils.getBean(ProcessHandleServiceImpl.class).HandleProc( param);

    }
    /**
     * 处理异常流程
     *
     * @return
     */
    @Log(title = "处理异常流程", businessType = BusinessType.OTHER)
    @PostMapping("HandleErrorProcess")
    @ResponseBody
    public R<String> HandleErrorProcess(@RequestBody HandleErrorProcessVO param) throws Exception {
        return  springBeanUtils.getBean(ProcessHandleServiceImpl.class).HandleErrorProcess( param);
    }
    @ApiOperation("获取当前用户所有相关的流程列表数据")
    @PostMapping("/queryporcessinsList")
    @ResponseBody
    public R<List<OaPorcessinsEntity>> queryporcessinsList(@RequestBody QueryPorcessInsListReq param) {
        return oaPorcessinsService.queryporcessinsList(param);
    }
    /**
     * 处理流程入口
     *
     * @return
     */
    @PostMapping("queryProcessInsLogList")
    @ResponseBody
    public R<List<OaPorcessinsLogEntity>> queryProcessInsLogList(@RequestBody QueryProcessInsLogReq porcessinsLogEntity) {
        return oaPorcessinsLogService.queryProcessInsLogList(porcessinsLogEntity);
    }
}
