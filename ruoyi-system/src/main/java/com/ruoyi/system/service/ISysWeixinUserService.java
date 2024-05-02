package com.ruoyi.system.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.system.domain.SysWeixinUser;

/**
 * 微信用户表Service接口
 *
 * @author Tellsea
 * @date 2023-07-20
 */
public interface ISysWeixinUserService  extends IService<SysWeixinUser>
{
    /**
     * 查询微信用户表
     *
     * @param wexinId 微信用户表主键
     * @return 微信用户表
     */
    public SysWeixinUser selectSysWeixinUserByWexinId(String wexinId);

    /**
     * 查询微信用户表列表
     *
     * @param sysWeixinUser 微信用户表
     * @return 微信用户表集合
     */
    public List<SysWeixinUser> selectSysWeixinUserList(SysWeixinUser sysWeixinUser);

    /**
     * 新增微信用户表
     *
     * @param sysWeixinUser 微信用户表
     * @return 结果
     */
    public void insertSysWeixinUser(SysWeixinUser sysWeixinUser);

    /**
     * 修改微信用户表
     *
     * @param sysWeixinUser 微信用户表
     * @return 结果
     */
    public void updateSysWeixinUser(SysWeixinUser sysWeixinUser);

    /**
     * 批量删除微信用户表
     *
     * @param wexinIds 需要删除的微信用户表主键集合
     * @return 结果
     */
    public int deleteSysWeixinUserByWexinIds(String[] wexinIds);

    /**
     * 删除微信用户表信息
     *
     * @param wexinId 微信用户表主键
     * @return 结果
     */
    public int deleteSysWeixinUserByWexinId(String wexinId);
}