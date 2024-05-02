package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.SysReadingLike;
import com.ruoyi.system.mapper.SysReadingLikeMapper;
import com.ruoyi.system.service.ISysReadingLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author Tellsea
 * @date 2023-07-09
 */
@Service
public class SysReadingLikeServiceImpl extends ServiceImpl<SysReadingLikeMapper, SysReadingLike> implements ISysReadingLikeService
{


    /**
     * 查询【请填写功能名称】
     * 
     * @param readingLikeId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public SysReadingLike selectSysReadingLikeByReadingLikeId(String readingLikeId)
    {
        return baseMapper.selectSysReadingLikeByReadingLikeId(readingLikeId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param sysReadingLike 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<SysReadingLike> selectSysReadingLikeList(SysReadingLike sysReadingLike)
    {
        return baseMapper.selectSysReadingLikeList(sysReadingLike);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param sysReadingLike 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertSysReadingLike(SysReadingLike sysReadingLike)
    {
        return baseMapper.insertSysReadingLike(sysReadingLike);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param sysReadingLike 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateSysReadingLike(SysReadingLike sysReadingLike)
    {
        return baseMapper.updateSysReadingLike(sysReadingLike);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param readingLikeIds 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteSysReadingLikeByReadingLikeIds(String[] readingLikeIds)
    {
        return baseMapper.deleteSysReadingLikeByReadingLikeIds(readingLikeIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param readingLikeId 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteSysReadingLikeByReadingLikeId(String readingLikeId)
    {
        return baseMapper.deleteSysReadingLikeByReadingLikeId(readingLikeId);
    }
}
