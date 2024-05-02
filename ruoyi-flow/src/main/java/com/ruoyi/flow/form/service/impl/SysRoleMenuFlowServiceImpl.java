package com.ruoyi.flow.form.service.impl;
import java.util.List;

import com.ruoyi.flow.form.entity.SysRoleMenuEntity;
import com.ruoyi.flow.form.mapper.SysRoleMenuFlowMapper;
import com.ruoyi.flow.form.service.ISysRoleMenuService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
/**
 * 角色和菜单关联Service业务层处理
 *
 * @author ruoyi
 * @date 2023-04-02
 */
@Service
public class SysRoleMenuFlowServiceImpl extends ServiceImpl<SysRoleMenuFlowMapper, SysRoleMenuEntity> implements ISysRoleMenuService
{


    /**
     * 查询角色和菜单关联
     *
     * @param roleId 角色和菜单关联主键
     * @return 角色和菜单关联
     */
    @Override
    public SysRoleMenuEntity selectSysRoleMenuByRoleId(Long roleId)
    {
        return baseMapper.selectSysRoleMenuByRoleId(roleId);
    }

    /**
     * 查询角色和菜单关联列表
     *
     * @param sysRoleMenuEntity 角色和菜单关联
     * @return 角色和菜单关联
     */
    @Override
    public List<SysRoleMenuEntity> selectSysRoleMenuList(SysRoleMenuEntity sysRoleMenuEntity)
    {
        return baseMapper.selectSysRoleMenuList(sysRoleMenuEntity);
    }

    /**
     * 新增角色和菜单关联
     *
     * @param sysRoleMenuEntity 角色和菜单关联
     * @return 结果
     */
    @Override
    public Boolean insertSysRoleMenu(SysRoleMenuEntity sysRoleMenuEntity)
    {

            return this.save(sysRoleMenuEntity);
    }

    /**
     * 修改角色和菜单关联
     *
     * @param sysRoleMenuEntity 角色和菜单关联
     * @return 结果
     */
    @Override
    public Boolean updateSysRoleMenu(SysRoleMenuEntity sysRoleMenuEntity)
    {

        return this.saveOrUpdate(sysRoleMenuEntity);
    }

    /**
     * 批量删除角色和菜单关联
     *
     * @param roleIds 需要删除的角色和菜单关联主键
     * @return 结果
     */
    @Override
    public int deleteSysRoleMenuByRoleIds(Long[] roleIds)
    {
        return baseMapper.deleteSysRoleMenuByRoleIds(roleIds);
    }

    /**
     * 删除角色和菜单关联信息
     *
     * @param roleId 角色和菜单关联主键
     * @return 结果
     */
    @Override
    public int deleteSysRoleMenuByRoleId(Long roleId)
    {
        return baseMapper.deleteSysRoleMenuByRoleId(roleId);
    }
}
