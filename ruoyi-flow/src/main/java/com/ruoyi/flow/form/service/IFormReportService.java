package com.ruoyi.flow.form.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.flow.form.entity.FormReport;

/**
 * 报设计Service接口
 * 
 * @author Tellsea
 * @date 2023-07-15
 */
public interface IFormReportService  extends IService<FormReport>
{
    /**
     * 查询报设计
     * 
     * @param formReportId 报设计主键
     * @return 报设计
     */
    public FormReport selectFormReportByFormReportId(String formReportId);

    /**
     * 查询报设计列表
     * 
     * @param formReport 报设计
     * @return 报设计集合
     */
    public List<FormReport> selectFormReportList(FormReport formReport);

    /**
     * 新增报设计
     * 
     * @param formReport 报设计
     * @return 结果
     */
    public int insertFormReport(FormReport formReport);

    /**
     * 修改报设计
     * 
     * @param formReport 报设计
     * @return 结果
     */
    public int updateFormReport(FormReport formReport);

    /**
     * 批量删除报设计
     * 
     * @param formReportIds 需要删除的报设计主键集合
     * @return 结果
     */
    public int deleteFormReportByFormReportIds(String[] formReportIds);

    /**
     * 删除报设计信息
     * 
     * @param formReportId 报设计主键
     * @return 结果
     */
    public int deleteFormReportByFormReportId(String formReportId);
}
