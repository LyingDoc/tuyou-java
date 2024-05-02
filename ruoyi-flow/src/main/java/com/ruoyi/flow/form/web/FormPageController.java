package com.ruoyi.flow.form.web;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.flow.vo.PackageInterfaceInfoVO;
import com.ruoyi.flow.vo.R;
import com.ruoyi.flow.form.entity.FormPageEntity;
import com.ruoyi.flow.form.service.impl.FormPageServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/flow/oa/formpage")
public class FormPageController extends BaseController {
    @Autowired
    private FormPageServiceImpl pageService;

    @ApiOperation("获取包信息")
    @PostMapping("selectOne/{id}")
    @ResponseBody
    public R<Object> selectOne(@PathVariable(name = "id") String id) {
        return R.newOk(this.pageService.getById(id));
    }
    /**
     * 修改数据
     *
     * @param tappPageEntity 实例对象
     * @return 实例对象
     */
    @Log(title = "包信息", businessType = BusinessType.UPDATE)
    @PostMapping("edit")
    @ResponseBody
    public R<Object> edit(FormPageEntity tappPageEntity) {
        pageService.updateById(tappPageEntity);
        return R.newOk();
    }


    /**
     * 修改数据
     *
     * @param tappPageEntity 实例对象
     * @return 实例对象
     */
    @PostMapping("getByPageName")
    @ResponseBody
    public R<FormPageEntity> getByPageName(@RequestBody FormPageEntity tappPageEntity) {
        if(!StringUtils.isNotEmpty(tappPageEntity.getPageName())){
            return R.newError("传入参数有误！");//传入参数为空！
        }
        QueryWrapper<FormPageEntity> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("page_Name",tappPageEntity.getPageName());
        return R.newOk( pageService.getBaseMapper().selectOne(queryWrapper));
    }

    /**
     * 新增数据
     *
     * @param tappPageEntity 实例对象
     * @return 实例对象
     */
    @Log(title = "包信息", businessType = BusinessType.INSERT)
    @PostMapping("add")
    @ResponseBody
    public R<String> add(@RequestBody FormPageEntity tappPageEntity) {
        pageService.saveOrUpdate(tappPageEntity);
        return R.newOk("保存成功！");

    }
    /**
     * 删除包
     * @param id
     */
    @Log(title = "包信息", businessType = BusinessType.DELETE)
    @PostMapping("del")
    @ResponseBody
    public R<Object> deleteMapping(@RequestParam("id") FormPageEntity tappPageEntity){
        pageService.deleteById(tappPageEntity.getFormPageId());
        return R.newOk();
    }

    /**
     * 获取包信息
     * @return
     */
    @PostMapping("getPackageInterfaceInfo")
    @ResponseBody
    public R<List<PackageInterfaceInfoVO>> getPackageInterfaceInfo(){
        return R.newOk(pageService.getPackageInterfaceInfo());
    }
}
