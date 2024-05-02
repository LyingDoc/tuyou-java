package com.ruoyi.system.service.impl;

import java.util.Base64;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.SysNews;
import com.ruoyi.system.mapper.SysNewsMapper;
import com.ruoyi.system.service.ISysNewsService;
import com.ruoyi.system.vo.HomeDataVO;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
/**
 * 新闻Service业务层处理
 * 
 * @author Tellsea
 * @date 2023-07-09
 */
@Service
public class SysNewsServiceImpl extends ServiceImpl<SysNewsMapper, SysNews> implements ISysNewsService
{


    /**
     * 查询新闻
     * 
     * @param newsId 新闻主键
     * @return 新闻
     */
    @Override
    public SysNews selectSysNewsByNewsId(String newsId)
    {
        return baseMapper.selectSysNewsByNewsId(newsId);
    }

    /**
     * 查询新闻列表
     * 
     * @param sysNews 新闻
     * @return 新闻
     */
    @Override
    public List<SysNews> selectSysNewsList(SysNews sysNews)
    {
        return baseMapper.queryList(sysNews);
    }

    /**
     * 新增新闻
     * 
     * @param sysNews 新闻
     * @return 结果
     */
    @Override
    public int insertSysNews(SysNews sysNews)
    {
        byte[] decodedBytes = Base64.getMimeDecoder().decode(sysNews.getNewsContent());
        String newContent = new String(decodedBytes);
        sysNews.setNewsContent(newContent);
        sysNews.setCreateTime(DateUtils.getNowDate());
        return baseMapper.insertSysNews(sysNews);
    }

    /**
     * 修改新闻
     * 
     * @param sysNews 新闻
     * @return 结果
     */
    @Override
    public int updateSysNews(SysNews sysNews)
    {
        byte[] decodedBytes = Base64.getMimeDecoder().decode(sysNews.getNewsContent());
        String newContent = new String(decodedBytes);
        sysNews.setNewsContent(newContent);
        sysNews.setUpdateTime(DateUtils.getNowDate());
        return baseMapper.updateSysNews(sysNews);
    }

    /**
     * 批量删除新闻
     * 
     * @param newsIds 需要删除的新闻主键
     * @return 结果
     */
    @Override
    public int deleteSysNewsByNewsIds(String[] newsIds)
    {
         baseMapper.deleteSysNewsByNewsIds(newsIds);
         return 1;
    }

    /**
     * 删除新闻信息
     * 
     * @param newsId 新闻主键
     * @return 结果
     */
    @Override
    public int deleteSysNewsByNewsId(String newsId)
    {

        baseMapper.deleteSysNewsByNewsId(newsId);
        return 1;
    }
    @Override
    public List<SysNews> getPublishNewList(String newsTitle){
       return  baseMapper.getPublishNewList(newsTitle);
    }
    @Override
    public   HomeDataVO getHomeData(){
        return  baseMapper.getHomeData();
    }
}
