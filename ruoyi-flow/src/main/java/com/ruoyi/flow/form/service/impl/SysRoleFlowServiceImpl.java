package com.ruoyi.flow.form.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.flow.form.entity.SysRoleDeptEntity;
import com.ruoyi.flow.form.entity.SysRoleEntity;
import com.ruoyi.flow.form.mapper.SysRoleDeptFlowMapper;
import com.ruoyi.flow.form.mapper.SysRoleFlowMapper;
import com.ruoyi.flow.form.service.ISysRoleFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色 业务层处理
 * 
 * @author ruoyi
 */
@Service
public class SysRoleFlowServiceImpl extends ServiceImpl<SysRoleFlowMapper, SysRoleEntity> implements ISysRoleFlowService
{
      @Autowired
     private SysRoleDeptFlowMapper sysRoleDeptFlowMapper;
      //根据角色获取拥有多少部门数据
      @Override
      public List<SysRoleDeptEntity> getDeptIDByRoleId(Long roleId){
          QueryWrapper<SysRoleDeptEntity> queryWrapper=new QueryWrapper<>();
          queryWrapper.eq("role_id",roleId);
         return  sysRoleDeptFlowMapper.selectList(queryWrapper);
      }
}
