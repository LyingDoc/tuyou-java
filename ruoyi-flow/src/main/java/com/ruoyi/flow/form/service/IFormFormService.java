package com.ruoyi.flow.form.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.flow.form.req.FromApiConfigReq;
import com.ruoyi.flow.form.req.FromSaveInfoReq;
import com.ruoyi.flow.form.req.QueryFromReq;
import com.ruoyi.flow.form.req.SaveFromMenuDataReq;
import com.ruoyi.flow.form.service.impl.BuildFlowMybatisInterfaceService;
import com.ruoyi.flow.form.service.impl.BuildFormMybatisInterfaceService;
import com.ruoyi.flow.form.vo.ApiConfigVO;
import com.ruoyi.flow.form.vo.QueryFromVO;
import com.ruoyi.flow.vo.OnLineFormVO;
import com.ruoyi.flow.vo.PageVO;
import com.ruoyi.flow.vo.R;
import com.ruoyi.flow.form.entity.FormFormEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 刘亚平
 * @since 2023-01-15
 */
public interface IFormFormService extends IService<FormFormEntity> {
    /**
     * 通过主键获取数据
     *
     * @param fid 主键
     * @return 是否成功
     */
    FormFormEntity getById(String id);

    /**
     * 通过主键删除数据
     *
     * @param fid 主键
     * @return 是否成功
     */
    boolean deleteById(String fid);


    /**
     * 修改
     *
     * @param formformentity
     * @return true代表更新成功  false代表更新失败
     */
    R<FormFormEntity> saveFormFormEntity(FormFormEntity formformentity);

    /**
     * 批量删除
     *
     * @param ids 要删除的集合列表
     * @return true代表删除成功  false代表删除失败
     */
    boolean deleteFormFormEntityList(List<Integer> ids);

    /**
     * 分页查询
     *
     * @param pageNo 页数
     * @param size   一页最大的条数
     * @return Page<FormFormEntity>
     */
    R<List<FormFormEntity>> getFormFormEntityList(PageVO<FormFormEntity> req);

    /**
     * 保存表单菜单数据
     */
    R<String> saveFromMenuData(@RequestBody SaveFromMenuDataReq fromMenuDataReq);

    /**
     * 获取表单菜单数据
     */
    R<SaveFromMenuDataReq> getFromMenuData(String fromId);

    R<List<ApiConfigVO>> getFromApiConfig(FromApiConfigReq fromApiConfigReq);

    /**
     * 获取表单菜单数据
     */
    R<List<FormFormEntity>> getRelevanceForm(FormFormEntity formFormEntity);

    /**
     * 保存表单数据
     */
    R<String> SaveFromData(FromSaveInfoReq param);

    OnLineFormVO getFromContent(String fid);

    OnLineFormVO getFromDesignJson(String fid);

    R<List<QueryFromVO>> queryFromList(QueryFromReq req);

    R<List<QueryFromVO>> queryFromDesignList(QueryFromReq req);
    void clearFromCache();

    ///将表单导出同步使用
    List<FromSaveInfoReq> exportFormConfig(List<String> formIds);

    ///将表单信息导入
    R importFormConfig(List<FromSaveInfoReq> formlist, BuildFormMybatisInterfaceService buildFormMybatisInterfaceService, BuildFlowMybatisInterfaceService buildFlowMybatisInterfaceService);

    FromSaveInfoReq getFromConfigInfo(FormFormEntity ee);

    OnLineFormVO getFromDesignJsonOrFromContent(String fid);
}

