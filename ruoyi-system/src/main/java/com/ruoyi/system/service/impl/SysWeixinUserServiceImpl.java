package com.ruoyi.system.service.impl;


import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.SysWeixinUser;
import com.ruoyi.system.mapper.SysWeixinUserMapper;
import com.ruoyi.system.service.ISysWeixinUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
/**
 * 微信用户表Service业务层处理
 *
 * @author Tellsea
 * @date 2023-07-20
 */
@Service
public class SysWeixinUserServiceImpl extends ServiceImpl<SysWeixinUserMapper, SysWeixinUser> implements ISysWeixinUserService
{


    /**
     * 查询微信用户表
     *
     * @param wexinId 微信用户表主键
     * @return 微信用户表
     */
    @Override
    public SysWeixinUser selectSysWeixinUserByWexinId(String wexinId)
    {
        return baseMapper.selectSysWeixinUserByWexinId(wexinId);
    }

    /**
     * 查询微信用户表列表
     *
     * @param sysWeixinUser 微信用户表
     * @return 微信用户表
     */
    @Override
    public List<SysWeixinUser> selectSysWeixinUserList(SysWeixinUser sysWeixinUser)
    {
        return baseMapper.queryList(sysWeixinUser);
    }

    /**
     * 新增微信用户表
     *
     * @param sysWeixinUser 微信用户表
     * @return 结果
     */
    @Override
    public void insertSysWeixinUser(SysWeixinUser sysWeixinUser)
    {
        sysWeixinUser.setCreateTime(DateUtils.getNowDate());
         baseMapper.insertSysWeixinUser(sysWeixinUser);
    }

    /**
     * 修改微信用户表
     *
     * @param sysWeixinUser 微信用户表
     * @return 结果
     */
    @Override
    public void updateSysWeixinUser(SysWeixinUser sysWeixinUser)
    {
        sysWeixinUser.setUpdateTime(DateUtils.getNowDate());
         baseMapper.updateSysWeixinUser(sysWeixinUser);
    }

    /**
     * 批量删除微信用户表
     *
     * @param wexinIds 需要删除的微信用户表主键
     * @return 结果
     */
    @Override
    public int deleteSysWeixinUserByWexinIds(String[] wexinIds)
    {
        return baseMapper.deleteSysWeixinUserByWexinIds(wexinIds);
    }

    /**
     * 删除微信用户表信息
     *
     * @param wexinId 微信用户表主键
     * @return 结果
     */
    @Override
    public int deleteSysWeixinUserByWexinId(String wexinId)
    {
        return baseMapper.deleteSysWeixinUserByWexinId(wexinId);
    }
}
