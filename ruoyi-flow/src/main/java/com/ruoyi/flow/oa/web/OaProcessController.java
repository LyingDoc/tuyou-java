package com.ruoyi.flow.oa.web;


import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.flow.form.entity.FormFormEntity;
import com.ruoyi.flow.form.req.FromSaveInfoReq;
import com.ruoyi.flow.form.service.impl.FormFormServiceImpl;
import com.ruoyi.flow.oa.vo.ExportFlowConfigVO;
import com.ruoyi.flow.oa.vo.ProcessChartVO;
import com.ruoyi.flow.vo.PageVO;
import com.ruoyi.flow.vo.R;
import com.ruoyi.flow.oa.entity.OaProcessEntity;
import com.ruoyi.flow.oa.req.ProcessInfoReq;
import com.ruoyi.flow.oa.req.SaveAsFlowReq;
import com.ruoyi.flow.oa.req.SaveFlowDataReq;
import com.ruoyi.flow.oa.service.impl.OaProcessServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * oa_processController
 * @author 刘亚平
 * @version 2022-12-01
 */
@Controller
@RequestMapping(value = "/flow/oa/Process")
public class OaProcessController extends BaseController {

	@Autowired
	private OaProcessServiceImpl oaProcessService;

	@Autowired
	private FormFormServiceImpl formFormService;
	/**
	 * 删除数据
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public R<Object> delete(@RequestBody OaProcessEntity oaProcess) {
		oaProcessService.declineVersion(oaProcess.getOaProcessId());
		return R.newOk();
	}

	/**
	 * 删除数据
	 */
	@Log(title = "删除流程模板", businessType = BusinessType.DELETE)
	@RequestMapping(value = "alldelete")
	@ResponseBody
	public R<Object> alldelete(@RequestBody OaProcessEntity oaProcess) {
		oaProcessService.allDelete(oaProcess);
		return R.newOk();
	}
	/**
	 * 将对应历史流程版本 设置为当前最新版本编辑
	 */
	@RequestMapping(value = "currentDraftProcess")
	@ResponseBody
	public R<String> currentDraftProcess(@RequestBody OaProcessEntity oaProcess) {
	  return 	oaProcessService.currentDraftProcess(oaProcess.getOaProcessId());
	}

	/**
	 * 保存流程模板数据
	 *
	 * @param param
	 * @return
	 * @throws IOException
	 */
	@Log(title = "保存流程模板数据", businessType = BusinessType.SAVE)
	@PostMapping("SaveFlowData")
	@ResponseBody
	public R<OaProcessEntity> SaveFlowData(@RequestBody SaveFlowDataReq param) throws IOException {
		ProcessInfoReq processInfoReq=  JSONObject.parseObject(param.getFlowjson(),ProcessInfoReq.class);
		processInfoReq.setFlowjson(param.getFlowjson());
		if(StringUtils.isBlank(processInfoReq.getFromID())){
			return R.newError("传入的表单ID不能为空！");
		}
		FormFormEntity formFormEntity= formFormService.getById(processInfoReq.getFromID());
		if(formFormEntity==null){
			return R.newError("找不到流程表单信息！");
		}
		return oaProcessService.SaveFlowData(processInfoReq, null);

	}
	/**
	 * 设计时 启动流程
	 *
	 * @param param
	 * @return
	 * @throws IOException
	 */
	@PostMapping("DesignStartFlow")
	@ResponseBody
	public R<OaProcessEntity> DesignStartFlow(@RequestBody SaveFlowDataReq param) throws IOException {
		ProcessInfoReq processInfoReq=  JSONObject.parseObject(param.getFlowjson(),ProcessInfoReq.class);
		processInfoReq.setFlowjson(param.getFlowjson());

		return oaProcessService.SaveFlowData(processInfoReq, 2);
	}

	/**
	 * 停止流程模板
	 *
	 * @param
	 * @return
	 * @throws IOException
	 */
	@Log(title = "停止流程模板", businessType = BusinessType.UPDATE)
	@PostMapping("StopFlow/{processid}")
	@ResponseBody
	public R<String> StopFlow(@PathVariable(name = "processid") String processid) throws Exception {
		return R.newOk( oaProcessService.StopFlow(processid));

	}
	/**
	 * 启动流程模板
	 *
	 * @param
	 * @return
	 * @throws IOException
	 */
	@Log(title = "启动流程模板", businessType = BusinessType.OTHER)
	@PostMapping("StartFlow/{processid}")
	@ResponseBody
	public R<String> GridStartFlow(@PathVariable(name = "processid") String processid) throws Exception {
		return R.newOk(oaProcessService.StartFlow(processid));
	}
	/**
	 *  详细界面启动流程模板
	 *
	 * @param
	 * @return
	 * @throws IOException
	 */
	@Log(title = "启动流程模板", businessType = BusinessType.OTHER)
	@PostMapping("StartFlow")
	@ResponseBody
	public R<OaProcessEntity> StartFlow(@RequestBody SaveFlowDataReq param) throws IOException {
		ProcessInfoReq processInfoReq=  JSONObject.parseObject(param.getFlowjson(),ProcessInfoReq.class);
		processInfoReq.setFlowjson(param.getFlowjson());
		return  oaProcessService.SaveFlowData(processInfoReq, 2);
	}
	/**
	 *  流程模板另存为
	 *
	 * @param
	 * @return
	 * @throws IOException
	 */
	@Log(title = "流程模板另存为", businessType = BusinessType.OTHER)
	@PostMapping("SaveAsFlow")
	@ResponseBody
	public R<String> SaveAsFlow(@RequestBody SaveAsFlowReq param) throws Exception {
		if (!StringUtils.isNotBlank(param.getProcessId())) {
			return R.newError("传入流程模板ID不能为空！");
		}
		if (!StringUtils.isNotBlank(param.getProcessname()) ) {
			return R.newError("传入流程名称不能为空！");
		}
		if (!StringUtils.isNotBlank(param.getProcesscode())) {
			return R.newError("传入传入流程编码不能为空！");
		}
		if(!oaProcessService.validateProcesscode(param.getProcesscode())){
			return R.newError("流程编码已存在,请重新输入流程编码！");
		}
		return R.newOk(oaProcessService.SaveAsFlow(param.getProcessId(), param.getProcessname(), param.getProcesscode()));
	}
	/**
	 *  详细界面流程模板另存为
	 *
	 * @param
	 * @return
	 * @throws IOException
	 */
	@Log(title = "详细界面流程模板另存为", businessType = BusinessType.OTHER)
	@PostMapping("optSaveAsFlow")
	@ResponseBody
	public R<OaProcessEntity> optSaveAsFlow(@RequestBody SaveAsFlowReq param) throws Exception {
		if (!StringUtils.isNotBlank(param.getProcessname()) ) {
			return R.newError("传入流程名称不能为空！");
		}
		if (!StringUtils.isNotBlank(param.getProcesscode())) {
			return R.newError("传入传入流程编码不能为空！");
		}
		return  oaProcessService.optSaveAsFlow(param);
	}
	/**
	 * 检查流程编码 是否唯一
	 *
	 * @param
	 * @return
	 * @throws IOException
	 */

	@PostMapping("CheackProcesscode/{Code}")
	@ResponseBody
	public R<String> CheackProcesscode(@PathVariable(name = "Code") String Code) throws IOException {
		if (!oaProcessService.CheackProcesscode(Code)) {
			return R.newError("流程编码已存在！");
		}
		return R.newOk("输入验证码正确！");
	}

	@GetMapping("getStartProcess")
	@ResponseBody
	public R<List<OaProcessEntity>>  getStartProcess(){
      return R.newOk(oaProcessService.getStartProcess());
	}

	@PostMapping("queryProcess")
	@ResponseBody
	public R<List<OaProcessEntity>> queryProcess(@RequestBody PageVO<OaProcessEntity> oaProcessEntity){
		return oaProcessService.queryProcess(oaProcessEntity);
	}

	@GetMapping("getProcessChart")
	@ResponseBody
	public R<ProcessChartVO>  getProcessChart(@RequestParam("processid") String processid){
		return R.newOk(oaProcessService.getProcessChart(processid));
	}

	///将流程与表单导出同步使用
	@PostMapping("exportFlowConfig")
	@Log(title = "流程与表单导出", businessType = BusinessType.OTHER)
	@ResponseBody
	public R< List<ExportFlowConfigVO>> exportFlowConfig(@RequestBody List<String> flowIds){
		if(flowIds==null||flowIds.size()==0){
			return R.newError("导出失败！传入参数错误");
		}
		return R.newOk(	oaProcessService.exportFlowConfig(flowIds));
	}

	///将表单导出同步使用
	@PostMapping("importFlowConfig")
	@Log(title = "流程与表单导入", businessType = BusinessType.OTHER)
	@ResponseBody
	public R importFlowConfig(@RequestBody List<ExportFlowConfigVO> flowConfigVOList){
		if(flowConfigVOList==null||flowConfigVOList.size()==0){
			return R.newError("导入失败！传入参数错误");
		}
		return 	oaProcessService.importFlowConfig(flowConfigVOList);
	}





}