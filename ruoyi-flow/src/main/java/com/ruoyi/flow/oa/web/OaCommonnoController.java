package com.ruoyi.flow.oa.web;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.flow.oa.entity.OaCommonnoEntity;
import com.ruoyi.flow.oa.entity.OaProcesstypeEntity;
import com.ruoyi.flow.oa.entity.OaTempNoticeEntity;
import com.ruoyi.flow.oa.service.impl.OaCommonnoServiceImpl;
import com.ruoyi.flow.vo.PageVO;
import com.ruoyi.flow.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * oa_commonnoController
 * @author 刘亚平
 * @version 2022-12-14
 */
@Controller
@RequestMapping(value = "/flow/oa/oaCommonno")
public class 	OaCommonnoController extends BaseController {

	@Autowired
	private OaCommonnoServiceImpl oaCommonnoService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public OaCommonnoEntity get(String oaCommonnoId) {
		return oaCommonnoService.getById(oaCommonnoId);
	}

	/**
	 * 保存表单信息
	 *
	 * @param
	 * @return
	 * @throws IOException
	 */
	@PostMapping("listData")
	@ResponseBody
	public R<List<OaCommonnoEntity>> listData(@RequestBody PageVO<OaCommonnoEntity> req)  {
		return oaCommonnoService.getOaCommonnoEntityList(req);

	}

	/**
	 * 保存数据
	 */
	@PostMapping(value = "save")
	@ResponseBody
	@Log(title = "自动编码保存", businessType = BusinessType.UPDATE)
	public R<Object> save(@RequestBody OaCommonnoEntity oaTempNotice) {
		if(!StringUtils.isNotEmpty(oaTempNotice.getOaCommonnoId()) ) {
			QueryWrapper<OaCommonnoEntity> oaCommonnoEntityQueryWrapper = new QueryWrapper<>();
			oaCommonnoEntityQueryWrapper.eq("moduleid", oaTempNotice.getModuleid());
		    Integer count=	oaCommonnoService.getBaseMapper().selectCount(oaCommonnoEntityQueryWrapper);
		    if(count>0){
		    	return R.newError("编码模块ID已经存在！");
			}
		}
		oaCommonnoService.saveOrUpdate(oaTempNotice);
		return R.newOk();
	}
	/**
	 * 删除数据
	 */
	@RequestMapping(value = "delete")
	@Log(title = "自动编码删除", businessType = BusinessType.DELETE)
	@ResponseBody
	public R<Object> delete(@RequestBody OaCommonnoEntity oaSendNotice) {
		oaCommonnoService.deleteById(oaSendNotice.getOaCommonnoId());
		return R.newOk();
	}
}