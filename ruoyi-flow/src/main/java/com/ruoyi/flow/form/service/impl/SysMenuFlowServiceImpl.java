package com.ruoyi.flow.form.service.impl;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.flow.form.entity.SysMenuEntity;
import com.ruoyi.flow.form.mapper.SysMenuFlowMapper;
import com.ruoyi.flow.form.service.ISysMenuFlowService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
/**
 * 菜单权限Service业务层处理
 *
 * @author ruoyi
 * @date 2023-04-02
 */
@Service
public class SysMenuFlowServiceImpl extends ServiceImpl<SysMenuFlowMapper, SysMenuEntity> implements ISysMenuFlowService
{


    /**
     * 查询菜单权限
     *
     * @param menuId 菜单权限主键
     * @return 菜单权限
     */
    @Override
    public SysMenuEntity selectSysMenuByMenuId(Long menuId)
    {
        return baseMapper.selectSysMenuByMenuId(menuId);
    }

    /**
     * 查询菜单权限列表
     *
     * @param sysMenuEntity 菜单权限
     * @return 菜单权限
     */
    @Override
    public List<SysMenuEntity> selectSysMenuList(SysMenuEntity sysMenuEntity)
    {
        return baseMapper.selectSysMenuList(sysMenuEntity);
    }

    /**
     * 新增菜单权限
     *
     * @param sysMenuEntity 菜单权限
     * @return 结果
     */
    @Override
    public Boolean insertSysMenu(SysMenuEntity sysMenuEntity)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        sysMenuEntity.setCreateTime(new Date());
        sysMenuEntity.setUpdateTime(new Date());
        sysMenuEntity.setCreateBy(loginUser.getUsername());
        sysMenuEntity.setUpdateBy(loginUser.getUsername());
            return this.save(sysMenuEntity);
    }

    /**
     * 修改菜单权限
     *
     * @param sysMenuEntity 菜单权限
     * @return 结果
     */
    @Override
    public Boolean updateSysMenu(SysMenuEntity sysMenuEntity)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        sysMenuEntity.setUpdateTime(new Date());
        sysMenuEntity.setUpdateBy(loginUser.getUsername());
        return this.saveOrUpdate(sysMenuEntity);
    }

    /**
     * 批量删除菜单权限
     *
     * @param menuIds 需要删除的菜单权限主键
     * @return 结果
     */
    @Override
    public int deleteSysMenuByMenuIds(Long[] menuIds)
    {
        return baseMapper.deleteSysMenuByMenuIds(menuIds);
    }

    /**
     * 删除菜单权限信息
     *
     * @param menuId 菜单权限主键
     * @return 结果
     */
    @Override
    public int deleteSysMenuByMenuId(Long menuId)
    {
        return baseMapper.deleteSysMenuByMenuId(menuId);
    }
}
