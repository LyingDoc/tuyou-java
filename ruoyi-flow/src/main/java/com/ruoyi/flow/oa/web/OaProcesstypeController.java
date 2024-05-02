package com.ruoyi.flow.oa.web;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.flow.form.entity.FormCtrlEntity;
import com.ruoyi.flow.oa.entity.OaProcessEntity;
import com.ruoyi.flow.oa.entity.OaProcesstypeEntity;
import com.ruoyi.flow.oa.entity.OaSendNoticeEntity;
import com.ruoyi.flow.oa.entity.OaTempNoticeEntity;
import com.ruoyi.flow.oa.service.impl.OaProcessServiceImpl;
import com.ruoyi.flow.oa.service.impl.OaProcesstypeServiceImpl;
import com.ruoyi.flow.vo.PageVO;
import com.ruoyi.flow.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

/**
 * oa_processtypeController
 *
 * @author 刘亚平
 * @version 2022-12-14
 */
@Controller
@RequestMapping(value = "/flow/oa/oaProcesstype")
public class OaProcesstypeController extends BaseController {

    @Autowired
    private OaProcesstypeServiceImpl oaProcesstypeService;
    @Autowired
    private OaProcessServiceImpl oaProcessService;

    /**
     * 保存表单信息
     *
     * @param
     * @return
     * @throws IOException
     */
    @PostMapping("listData")
    @ResponseBody
    public R<List<OaProcesstypeEntity>> listData(@RequestBody PageVO<OaProcesstypeEntity> req) {
        return oaProcesstypeService.getOaProcesstypeEntityList(req);

    }

    /**
     * 保存数据
     */
    @Log(title = "流程类型", businessType = BusinessType.SAVE)
    @PostMapping(value = "save")
    @ResponseBody
    public R<Object> save(@RequestBody OaProcesstypeEntity oaSendNotice) {
        oaProcesstypeService.saveOrUpdate(oaSendNotice);
        return R.newOk();
    }

    /**
     * 删除数据
     */
    @Log(title = "流程类型删除", businessType = BusinessType.DELETE)
    @RequestMapping(value = "delete")
    @ResponseBody
    public R<Object> delete(@RequestBody OaProcesstypeEntity oaSendNotice) {
        OaProcesstypeEntity oaProcesstypeEntity = oaProcesstypeService.getById(oaSendNotice.getOaProcesstypeId());
        QueryWrapper<OaProcessEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("processtypecode", oaProcesstypeEntity.getProcesstypecode());
        if (oaProcessService.getBaseMapper().selectCount(queryWrapper) > 0) {
            return R.newError("已有流程关联流程类型，不能进行删除！");
        }
        oaProcesstypeService.deleteById(oaSendNotice.getOaProcesstypeId());
        return R.newOk();
    }

    /**
     * 删除数据
     */
    @PostMapping(value = "getProcessType")
    @ResponseBody
    public R<List<OaProcesstypeEntity>> getProcessType(@RequestBody PageVO<OaProcesstypeEntity> oaSendNotice) {
        return oaProcesstypeService.getOaProcesstypeEntityList(oaSendNotice);
    }
}