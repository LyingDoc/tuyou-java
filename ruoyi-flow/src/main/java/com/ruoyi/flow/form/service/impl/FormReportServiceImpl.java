package com.ruoyi.flow.form.service.impl;

import java.util.List;

import com.ruoyi.flow.form.entity.FormReport;
import com.ruoyi.flow.form.mapper.FormReportMapper;
import com.ruoyi.flow.form.service.IFormReportService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
/**
 * 报设计Service业务层处理
 * 
 * @author Tellsea
 * @date 2023-07-15
 */
@Service
public class FormReportServiceImpl extends ServiceImpl<FormReportMapper, FormReport> implements IFormReportService
{


    /**
     * 查询报设计
     * 
     * @param formReportId 报设计主键
     * @return 报设计
     */
    @Override
    public FormReport selectFormReportByFormReportId(String formReportId)
    {
        return baseMapper.selectById(formReportId);
    }

    /**
     * 查询报设计列表
     * 
     * @param formReport 报设计
     * @return 报设计
     */
    @Override
    public List<FormReport> selectFormReportList(FormReport formReport)
    {
        return baseMapper.queryList(formReport);
    }

    /**
     * 新增报设计
     * 
     * @param formReport 报设计
     * @return 结果
     */
    @Override
    public int insertFormReport(FormReport formReport)
    {
        return baseMapper.insertFormReport(formReport);
    }

    /**
     * 修改报设计
     * 
     * @param formReport 报设计
     * @return 结果
     */
    @Override
    public int updateFormReport(FormReport formReport)
    {
        return baseMapper.updateFormReport(formReport);
    }

    /**
     * 批量删除报设计
     * 
     * @param formReportIds 需要删除的报设计主键
     * @return 结果
     */
    @Override
    public int deleteFormReportByFormReportIds(String[] formReportIds)
    {
        return baseMapper.deleteFormReportByFormReportIds(formReportIds);
    }

    /**
     * 删除报设计信息
     * 
     * @param formReportId 报设计主键
     * @return 结果
     */
    @Override
    public int deleteFormReportByFormReportId(String formReportId)
    {
        return baseMapper.deleteFormReportByFormReportId(formReportId);
    }
}
