package com.ruoyi.flow.form.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.flow.form.entity.SysDeptEntity;
import com.ruoyi.flow.form.mapper.SysDeptFlowMapper;
import com.ruoyi.flow.form.service.ISysDeptFlowService;


import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 部门管理 服务实现
 * 
 * @author ruoyi
 */
@Service
public class SysDeptFlowServiceImpl extends ServiceImpl<SysDeptFlowMapper, SysDeptEntity> implements ISysDeptFlowService
{

    /**
     * 根据部门ID查询信息
     *
     * @param deptId 部门ID
     * @return 部门信息
     */
    public SysDeptEntity selectDeptById(Long deptId)
    {
        return baseMapper.selectDeptById(deptId);
    }

    /**
     * 根据部门ID查询信息
     *
     * @param deptId 部门ID
     * @return 部门信息
     */
    public List<Long> getChildDeptId(String  ancestors)
    {
        return baseMapper.getChildDeptId(ancestors);
    }
}
