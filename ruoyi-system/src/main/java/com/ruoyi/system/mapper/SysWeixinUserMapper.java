package com.ruoyi.system.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.domain.SysWeixinUser;


/**
 * 微信用户表Mapper接口
 *
 * @author Tellsea
 * @date 2023-07-20
 */
public interface SysWeixinUserMapper  extends BaseMapper<SysWeixinUser>
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
    public List<SysWeixinUser> queryList(SysWeixinUser sysWeixinUser);

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
     * 删除微信用户表
     *
     * @param wexinId 微信用户表主键
     * @return 结果
     */
    public int deleteSysWeixinUserByWexinId(String wexinId);

    /**
     * 批量删除微信用户表
     *
     * @param wexinIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysWeixinUserByWexinIds(String[] wexinIds);
}