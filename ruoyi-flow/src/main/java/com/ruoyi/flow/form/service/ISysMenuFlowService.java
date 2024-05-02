package com.ruoyi.flow.form.service;

import java.util.List;

import com.ruoyi.flow.form.entity.SysMenuEntity;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * 菜单权限Service接口
 *
 * @author ruoyi
 * @date 2023-04-02
 */
public interface ISysMenuFlowService extends IService<SysMenuEntity>
{
    /**
     * 查询菜单权限
     *
     * @param menuId 菜单权限主键
     * @return 菜单权限
     */
    public SysMenuEntity selectSysMenuByMenuId(Long menuId);

    /**
     * 查询菜单权限列表
     *
     * @param sysMenuEntity 菜单权限
     * @return 菜单权限集合
     */
    public List<SysMenuEntity> selectSysMenuList(SysMenuEntity sysMenuEntity);

    /**
     * 新增菜单权限
     *
     * @param sysMenuEntity 菜单权限
     * @return 结果
     */
    public Boolean insertSysMenu(SysMenuEntity sysMenuEntity);

    /**
     * 修改菜单权限
     *
     * @param sysMenuEntity 菜单权限
     * @return 结果
     */
    public Boolean updateSysMenu(SysMenuEntity sysMenuEntity);

    /**
     * 批量删除菜单权限
     *
     * @param menuIds 需要删除的菜单权限主键集合
     * @return 结果
     */
    public int deleteSysMenuByMenuIds(Long[] menuIds);

    /**
     * 删除菜单权限信息
     *
     * @param menuId 菜单权限主键
     * @return 结果
     */
    public int deleteSysMenuByMenuId(Long menuId);
}
