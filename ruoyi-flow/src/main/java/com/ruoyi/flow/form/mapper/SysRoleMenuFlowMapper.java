package com.ruoyi.flow.form.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.flow.form.entity.SysRoleMenuEntity;

/**
 * 角色和菜单关联Mapper接口
 *
 * @author ruoyi
 * @date 2023-04-02
 */
public interface SysRoleMenuFlowMapper extends BaseMapper<SysRoleMenuEntity>
{
    /**
     * 查询角色和菜单关联
     *
     * @param roleId 角色和菜单关联主键
     * @return 角色和菜单关联
     */
    public SysRoleMenuEntity selectSysRoleMenuByRoleId(Long roleId);

    /**
     * 查询角色和菜单关联列表
     *
     * @param sysRoleMenuEntity 角色和菜单关联
     * @return 角色和菜单关联集合
     */
    public List<SysRoleMenuEntity> selectSysRoleMenuList(SysRoleMenuEntity sysRoleMenuEntity);

    /**
     * 新增角色和菜单关联
     *
     * @param sysRoleMenuEntity 角色和菜单关联
     * @return 结果
     */
    public int insertSysRoleMenu(SysRoleMenuEntity sysRoleMenuEntity);

    /**
     * 修改角色和菜单关联
     *
     * @param sysRoleMenuEntity 角色和菜单关联
     * @return 结果
     */
    public int updateSysRoleMenu(SysRoleMenuEntity sysRoleMenuEntity);

    /**
     * 删除角色和菜单关联
     *
     * @param roleId 角色和菜单关联主键
     * @return 结果
     */
    public int deleteSysRoleMenuByRoleId(Long roleId);

    /**
     * 批量删除角色和菜单关联
     *
     * @param roleIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysRoleMenuByRoleIds(Long[] roleIds);
}
