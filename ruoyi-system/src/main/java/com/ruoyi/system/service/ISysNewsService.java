package com.ruoyi.system.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.system.domain.SysNews;
import com.ruoyi.system.vo.HomeDataVO;

/**
 * 新闻Service接口
 * 
 * @author Tellsea
 * @date 2023-07-09
 */
public interface ISysNewsService  extends IService<SysNews>
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
    public List<SysNews> selectSysNewsList(SysNews sysNews);

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
     * 批量删除新闻
     * 
     * @param newsIds 需要删除的新闻主键集合
     * @return 结果
     */
    public int deleteSysNewsByNewsIds(String[] newsIds);

    /**
     * 删除新闻信息
     * 
     * @param newsId 新闻主键
     * @return 结果
     */
    public int deleteSysNewsByNewsId(String newsId);

    public List<SysNews> getPublishNewList(String newsTitle);

    public HomeDataVO getHomeData();
}
