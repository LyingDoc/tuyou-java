package com.ruoyi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.SysNews;
import com.ruoyi.system.vo.HomeDataVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;



/**
 * 新闻Mapper接口
 * 
 * @author Tellsea
 * @date 2023-07-09
 */
public interface SysNewsMapper extends BaseMapper<SysNews>
{
    /**
     * 查询新闻
     * 
     * @param newsId 新闻主键
     * @return 新闻
     */
    public SysNews selectSysNewsByNewsId(String newsId);

    /**
     * 查询新闻列表
     * 
     * @param sysNews 新闻
     * @return 新闻集合
     */
    public List<SysNews> queryList(SysNews sysNews);

    /**
     * 新增新闻
     * 
     * @param sysNews 新闻
     * @return 结果
     */
    public int insertSysNews(SysNews sysNews);

    /**
     * 修改新闻
     * 
     * @param sysNews 新闻
     * @return 结果
     */
    public int updateSysNews(SysNews sysNews);

    /**
     * 删除新闻
     * 
     * @param newsId 新闻主键
     * @return 结果
     */
    public int deleteSysNewsByNewsId(String newsId);

    /**
     * 批量删除新闻
     * 
     * @param newsIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysNewsByNewsIds(String[] newsIds);

    /**
     * 获取发布新闻信息
     * @return
     */
    public List<SysNews> getPublishNewList(@Param("newsTitle") String newsTitle);

    /**
     * 获取发布新闻信息
     * @return
     */
    public HomeDataVO getHomeData();
}
