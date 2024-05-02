package com.ruoyi.flow.oa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.flow.form.req.FromSaveInfoReq;
import com.ruoyi.flow.oa.vo.ExportFlowConfigVO;
import com.ruoyi.flow.oa.vo.ProcessChartVO;
import com.ruoyi.flow.vo.PageVO;
import com.ruoyi.flow.vo.R;
import com.ruoyi.flow.oa.entity.OaProcessEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 刘亚平
 * @since 2023-01-15
 */
public interface IOaProcessService extends IService<OaProcessEntity> {
        /**
        * 通过主键获取数据
        *
        * @param fid 主键
        * @return 是否成功
        */
       OaProcessEntity getById(Long id);
        /**
       * 通过主键删除数据
       *
       * @param fid 主键
       * @return 是否成功
       */
        boolean deleteById(Long fid);


        /**
         * 修改
         * @param oaprocessentity
         * @return  true代表更新成功  false代表更新失败
         */
        R<OaProcessEntity> saveOaProcessEntity(OaProcessEntity oaprocessentity);
        /**
        * 批量删除
        * @param ids 要删除的集合列表
        * @return true代表删除成功  false代表删除失败
        */
        boolean deleteOaProcessEntityList(List<Integer> ids);

        /**
         * 分页查询
         * @param pageNo  页数
         * @param size    一页最大的条数
         * @return  Page<OaProcessEntity>
         */
        R<List<OaProcessEntity>> getOaProcessEntityList(PageVO<OaProcessEntity> req);
        boolean allDelete(OaProcessEntity processEntity);

        void  declineVersion(String oaProcessId);
        ///将对应历史流程版本 设置为当前最新版本编辑
        R<String>  currentDraftProcess(String oaProcessId);

        List<OaProcessEntity>  getStartProcess();

        ProcessChartVO  getProcessChart(String processid);

        R<List<OaProcessEntity>> queryProcess( PageVO<OaProcessEntity> oaProcessEntity);
        ///导出流程配置
        List<ExportFlowConfigVO> exportFlowConfig(List<String> flowIds);

        R    importFlowConfig( List<ExportFlowConfigVO> flowConfigVOList);
}

