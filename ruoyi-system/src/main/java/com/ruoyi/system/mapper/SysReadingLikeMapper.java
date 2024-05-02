package com.ruoyi.system.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.SysReadingLike;


/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author Tellsea
 * @date 2023-07-09
 */
public interface SysReadingLikeMapper extends BaseMapper<SysReadingLike>
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param readingLikeId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public SysReadingLike selectSysReadingLikeByReadingLikeId(String readingLikeId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param sysReadingLike 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<SysReadingLike> selectSysReadingLikeList(SysReadingLike sysReadingLike);

    /**
     * 新增【请填写功能名称】
     * 
     * @param sysReadingLike 【请填写功能名称】
     * @return 结果
     */
    public int insertSysReadingLike(SysReadingLike sysReadingLike);

    /**
     * 修改【请填写功能名称】
     * 
     * @param sysReadingLike 【请填写功能名称】
     * @return 结果
     */
    public int updateSysReadingLike(SysReadingLike sysReadingLike);

    /**
     * 删除【请填写功能名称】
     * 
     * @param readingLikeId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteSysReadingLikeByReadingLikeId(String readingLikeId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param readingLikeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysReadingLikeByReadingLikeIds(String[] readingLikeIds);
}
