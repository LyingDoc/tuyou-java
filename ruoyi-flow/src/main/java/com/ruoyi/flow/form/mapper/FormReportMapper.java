package com.ruoyi.flow.form.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.flow.form.entity.FormReport;

/**
 * 报设计Mapper接口
 * 
 * @author Tellsea
 * @date 2023-07-15
 */
public interface FormReportMapper extends BaseMapper<FormReport>
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
    public List<FormReport> queryList(FormReport formReport);

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
     * 删除报设计
     * 
     * @param formReportId 报设计主键
     * @return 结果
     */
    public int deleteFormReportByFormReportId(String formReportId);

    /**
     * 批量删除报设计
     * 
     * @param formReportIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFormReportByFormReportIds(String[] formReportIds);
}
