package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.system.domain.SysNews;
import com.ruoyi.system.service.ISysNewsService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 新闻Controller
 * 
 * @author Tellsea
 * @date 2023-07-09
 */
@RestController
@RequestMapping("/system/news")
public class SysNewsController extends BaseController
{
    @Autowired
    private ISysNewsService sysNewsService;

    /**
     * 查询新闻列表
     */
    @PreAuthorize("@ss.hasPermi('system:news:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysNews sysNews)
    {
        startPage();
        List<SysNews> list = sysNewsService.selectSysNewsList(sysNews);
        return getDataTable(list);
    }

    /**
     * 导出新闻列表
     */
    @PreAuthorize("@ss.hasPermi('system:news:export')")
    @Log(title = "新闻", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysNews sysNews)
    {
        List<SysNews> list = sysNewsService.selectSysNewsList(sysNews);
        ExcelUtil<SysNews> util = new ExcelUtil<SysNews>(SysNews.class);
        util.exportExcel(response, list, "新闻数据");
    }

    /**
     * 获取新闻详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:news:query')")
    @GetMapping(value = "/{newsId}")
    public AjaxResult getInfo(@PathVariable("newsId") String newsId)
    {
        return AjaxResult.success(sysNewsService.selectSysNewsByNewsId(newsId));
    }

    /**
     * 新增新闻
     */
    @PreAuthorize("@ss.hasPermi('system:news:add')")
    @Log(title = "新闻", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysNews sysNews)
    {
        return toAjax(sysNewsService.save(sysNews));
    }

    /**
     * 修改新闻
     */
    @PreAuthorize("@ss.hasPermi('system:news:edit')")
    @Log(title = "新闻", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysNews sysNews)
    {
        return toAjax(sysNewsService.updateById(sysNews));
    }

    /**
     * 删除新闻
     */
    @PreAuthorize("@ss.hasPermi('system:news:remove')")
    @Log(title = "新闻", businessType = BusinessType.DELETE)
	@DeleteMapping("/{newsIds}")
    public AjaxResult remove(@PathVariable String[] newsIds)
    {
        return toAjax(sysNewsService.deleteSysNewsByNewsIds(newsIds));
    }
    @PreAuthorize("@ss.hasPermi('system:news:publishNews')")
    @Log(title = "发布新闻", businessType = BusinessType.UPDATE)
    @GetMapping("/publishNews/{newsId}")
    public AjaxResult publishNews(@PathVariable("newsId") String newsId){
       SysNews sysNews= sysNewsService.getById(newsId);
        sysNews.setNewsStatus("1");
        sysNewsService.updateSysNews(sysNews);
        return AjaxResult.success();
    }

    @PreAuthorize("@ss.hasPermi('system:news:offlineNews')")
    @Log(title = "下线新闻", businessType = BusinessType.UPDATE)
    @GetMapping("/offlineNews/{newsId}")
    public AjaxResult offlineNews(@PathVariable("newsId") String newsId){
        SysNews sysNews= sysNewsService.getById(newsId);
        sysNews.setNewsStatus("2");
        sysNewsService.updateSysNews(sysNews);
        return AjaxResult.success();
    }


    @GetMapping("/getPublishNewList")
    public TableDataInfo getPublishNewList(@RequestParam(value="newsTitle", required=false, defaultValue="") String newsTitle){
        startPage();
        return getDataTable(sysNewsService.getPublishNewList(newsTitle));
    }
}
