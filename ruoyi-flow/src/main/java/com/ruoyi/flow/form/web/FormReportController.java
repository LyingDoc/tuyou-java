package com.ruoyi.flow.form.web;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.flow.form.entity.FormReport;
import com.ruoyi.flow.form.service.IFormApiconfigService;
import com.ruoyi.flow.form.service.IFormReportService;
import com.ruoyi.flow.form.service.impl.FormApiconfigServiceImpl;
import com.ruoyi.flow.form.vo.ApiConfigVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 报设计Controller
 * 
 * @author Tellsea
 * @date 2023-07-15
 */
@RestController
@RequestMapping("/flow/oa/report")
public class FormReportController extends BaseController
{
    @Autowired
    private IFormReportService formReportService;
    @Autowired
    private FormApiconfigServiceImpl formApiconfigService;
    /**
     * 查询报设计列表
     */
    @PreAuthorize("@ss.hasPermi('business:report:list')")
    @GetMapping("/list")
    public TableDataInfo list(FormReport formReport)
    {
        startPage();
        List<FormReport> list = formReportService.selectFormReportList(formReport);
        list.forEach(ee->{
            ApiConfigVO apiConfigVO= formApiconfigService.getApiConfigByCallMethodCode(ee.getApicode());
            if(apiConfigVO!=null) {
                ee.setApiname(apiConfigVO.getMethodName());
            }
        });
        return getDataTable(list);
    }

    /**
     * 导出报设计列表
     */
    @PreAuthorize("@ss.hasPermi('business:report:export')")
    @Log(title = "报设计", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FormReport formReport)
    {
        List<FormReport> list = formReportService.selectFormReportList(formReport);
        ExcelUtil<FormReport> util = new ExcelUtil<FormReport>(FormReport.class);
        util.exportExcel(response, list, "报设计数据");
    }

    /**
     * 获取报设计详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:report:query')")
    @GetMapping(value = "/{formReportId}")
    public AjaxResult getInfo(@PathVariable("formReportId") String formReportId)
    {
        FormReport formReport= formReportService.selectFormReportByFormReportId(formReportId);
        ApiConfigVO apiConfigVO= formApiconfigService.getApiConfigByCallMethodCode(formReport.getApicode());
        formReport.setApiname(apiConfigVO.getMethodName());
        return AjaxResult.success(formReport);
    }

    /**
     * 新增报设计
     */
    @PreAuthorize("@ss.hasPermi('business:report:add')")
    @Log(title = "报设计", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FormReport formReport)
    {
        QueryWrapper<FormReport> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("report_code",formReport.getReportCode());
        List<FormReport>  formReportList= formReportService.getBaseMapper().selectList(queryWrapper);
        if(formReportList.size()>0){
           return AjaxResult.error("报表编码已存在,请重新输入！");
        }
        return toAjax(formReportService.save(formReport));
    }

    /**
     * 修改报设计
     */
    @PreAuthorize("@ss.hasPermi('business:report:edit')")
    @Log(title = "报设计", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FormReport formReport)
    {
        QueryWrapper<FormReport> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("report_code",formReport.getReportCode());
        queryWrapper.ne("form_report_id",formReport.getFormReportId());
        List<FormReport>  formReportList= formReportService.getBaseMapper().selectList(queryWrapper);
        if(formReportList.size()>0){
            return AjaxResult.error("报表编码已存在,请重新输入！");
        }
        return toAjax(formReportService.updateById(formReport));
    }

    /**
     * 删除报设计
     */
    @PreAuthorize("@ss.hasPermi('business:report:remove')")
    @Log(title = "报设计", businessType = BusinessType.DELETE)
	@DeleteMapping("/{formReportIds}")
    public AjaxResult remove(@PathVariable String[] formReportIds)
    {
        return toAjax(formReportService.deleteFormReportByFormReportIds(formReportIds));
    }
}
