package com.ruoyi.flow.form.web;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.flow.comm.SpringBeanUtils;
import com.ruoyi.flow.form.req.*;
import com.ruoyi.flow.form.vo.ApiConfigVO;
import com.ruoyi.flow.form.vo.QueryFromVO;
import com.ruoyi.flow.oa.entity.OaProcessEntity;
import com.ruoyi.flow.oa.service.impl.OaProcessServiceImpl;
import com.ruoyi.flow.vo.OnLineFormVO;
import com.ruoyi.flow.vo.R;
import com.ruoyi.flow.form.entity.FormFormEntity;
import com.ruoyi.flow.form.service.impl.BuildFlowMybatisInterfaceService;
import com.ruoyi.flow.form.service.impl.BuildFormMybatisInterfaceService;
import com.ruoyi.flow.form.service.impl.FormFormServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * form_formController
 *
 * @author 刘亚平
 * @version 2022-11-29
 */
@Controller
@RequestMapping(value = "/flow/oa/formForm")
public class FormFormController extends BaseController {

    @Autowired
    private FormFormServiceImpl formFormService;
    @Autowired
    private SpringBeanUtils springBeanUtils;

    @Autowired
    private OaProcessServiceImpl oaProcessService;
//	@Autowired
//	private BuildFormMybatisInterfaceService buildFormMybatisInterfaceService;
//
//	@Autowired
//	private BuildFlowMybatisInterfaceService buildFlowMybatisInterfaceService;

    /**
     * 通过主键查询单条数据
     *
     * @param
     * @return 单条数据
     */
    @PostMapping("getFromInfo")
    @ResponseBody
    public R<FormFormEntity> selectOne(@RequestParam("fromId") Long fromId) throws IOException {
        return R.newOk(this.formFormService.getById(fromId));
    }

    /**
     * 保存表单信息
     *
     * @param
     * @return
     * @throws IOException
     */
    @Log(title = "表单信息", businessType = BusinessType.UPDATE)
    @PostMapping("saveData")
    @ResponseBody
    public R<String> SaveData(@RequestBody FromSaveInfoReq param) throws IOException {
        try {
            LambdaQueryWrapper<FormFormEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(FormFormEntity::getFromcode, param.getFromCode());
            lambdaQueryWrapper.ne(FormFormEntity::getFormFormId, param.getId());
            Integer formcount = formFormService.getBaseMapper().selectCount(lambdaQueryWrapper);
            if (formcount > 0) {
                return R.newError("表单编码已存在，请重新输入！");
            }
            return formFormService.SaveFromData(param);
        } catch (Exception ex) {
            ex.printStackTrace();
            return R.newError("保存报错！具体错误信息为：" + ex);
        }
    }


    /**
     * @param
     * @return
     * @throws IOException
     */
    @Log(title = "表单信息", businessType = BusinessType.UPDATE)
    @PostMapping("saveFromMenuData")
    @ResponseBody
    public R<String> saveFromMenuData(@RequestBody SaveFromMenuDataReq fromMenuDataReq) {
        return formFormService.saveFromMenuData(fromMenuDataReq);
    }

    /**
     * 根据表单ID获取保存的菜单信息
     *
     * @param
     * @return
     * @throws IOException
     */

    @PostMapping("getFromMenuData")
    @ResponseBody
    public R<SaveFromMenuDataReq> getFromMenuData(@RequestParam("fromId") String fromId) {
        return formFormService.getFromMenuData(fromId);
    }

    /**
     * 根据表单ID获取保存的菜单信息
     *
     * @param
     * @return
     * @throws IOException
     */
    @PostMapping("getFromApiConfig")
    @ResponseBody
    public R<List<ApiConfigVO>> getFromApiConfig(@RequestBody FromApiConfigReq fromApiConfigReq) {
        return formFormService.getFromApiConfig(fromApiConfigReq);
    }

    @PostMapping("getRelevanceForm")
    @ResponseBody
    public R<List<FormFormEntity>> getRelevanceForm(@RequestBody FormFormEntity formFormEntity) {
        return formFormService.getRelevanceForm(formFormEntity);
    }

    @GetMapping("getFromContent")
    @ResponseBody
    public R<OnLineFormVO> getFromContent(@RequestParam("fid") String fid) {
        return R.newOk(formFormService.getFromContent(fid));
    }

    @GetMapping("getFromDesignJson")
    @ResponseBody
    public R<OnLineFormVO> getFromDesignJson(@RequestParam("fid") String fid) {
        return R.newOk(formFormService.getFromDesignJson(fid));
    }

    @GetMapping("getFromDesignJsonOrFromContent")
    @ResponseBody
    public R<OnLineFormVO> getFromDesignJsonOrFromContent(@RequestParam("fid") String fid) {
        return R.newOk(formFormService.getFromDesignJsonOrFromContent(fid));
    }

    @PostMapping("queryFromList")
    @ResponseBody
    public R<List<QueryFromVO>> queryFromList(@RequestBody QueryFromReq req) {
        // 超级管理员能查询全部表单数据，否则只能查询当前部门的表单...
        boolean admin = SecurityUtils.isAdmin(SecurityUtils.getUserId());
        if (!admin) {
            // todo 后面可能会修改
            req.setDeptId(SecurityUtils.getDeptId());
        }
        return formFormService.queryFromList(req);
    }
    @PostMapping("queryFromDesignList")
    @ResponseBody
    public R<List<QueryFromVO>> queryFromDesignList(@RequestBody QueryFromReq req) {
        // 超级管理员能查询全部表单数据，否则只能查询当前部门的表单...
        boolean admin = SecurityUtils.isAdmin(SecurityUtils.getUserId());
        if (!admin) {
            req.setDeptId(SecurityUtils.getDeptId());
        }
        return formFormService.queryFromDesignList(req);
    }
    @Log(title = "表单信息", businessType = BusinessType.DELETE)
    @PostMapping("delfrom")
    @ResponseBody
    public R delfrom(@RequestBody FormFormEntity req) {
        QueryWrapper<OaProcessEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("form_form_id", req.getFormFormId());
        if (oaProcessService.getBaseMapper().selectCount(queryWrapper) > 0) {
            return R.newError("已有流程关联，不能进行删除！");
        }
        formFormService.deleteById(req.getFormFormId());
        return R.newOk();
    }

    @GetMapping("clearFromCache")
    @ResponseBody
    public R clearFromCache() {
        formFormService.clearFromCache();
        return R.newOk("已清除所有表单缓存！");
    }
    @Log(title = "表单信息", businessType = BusinessType.IMPORT)
    ///将表单导出同步使用
    @PostMapping("exportFormConfig")
    @ResponseBody
    public R<List<FromSaveInfoReq>> exportFormConfig(@RequestBody List<String> formIds) {
        if (formIds == null || formIds.size() == 0) {
            return R.newError("导出失败！传入参数错误");
        }
        return R.newOk(formFormService.exportFormConfig(formIds));
    }

    ///将表单导出同步使用
    @Log(title = "表单信息", businessType = BusinessType.EXPORT)
    @PostMapping("importFormConfig")
    @ResponseBody
    public R importFormConfig(@RequestBody List<FromSaveInfoReq> formlist) {
        if (formlist == null || formlist.size() == 0) {
            return R.newError("导入失败！传入参数错误");
        }
        return formFormService.importFormConfig(formlist, springBeanUtils.getBean(BuildFormMybatisInterfaceService.class), springBeanUtils.getBean(BuildFlowMybatisInterfaceService.class));
    }
}