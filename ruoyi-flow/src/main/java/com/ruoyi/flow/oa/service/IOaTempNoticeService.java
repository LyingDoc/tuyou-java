package com.ruoyi.flow.oa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.flow.oa.vo.ProcessChartVO;
import com.ruoyi.flow.vo.PageVO;
import com.ruoyi.flow.vo.R;
import com.ruoyi.flow.oa.entity.OaTempNoticeEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * <p>
 * 通知模板表 服务类
 * </p>
 *
 * @author 刘亚平
 * @since 2023-01-15
 */
public interface IOaTempNoticeService extends IService<OaTempNoticeEntity> {
        /**
        * 通过主键获取数据
        *
        * @param fid 主键
        * @return 是否成功
        */
       OaTempNoticeEntity getById(String id);
        /**
       * 通过主键删除数据
       *
       * @param fid 主键
       * @return 是否成功
       */
        boolean deleteById(String fid);


        /**
         * 修改
         * @param oatempnoticeentity
         * @return  true代表更新成功  false代表更新失败
         */
        R<OaTempNoticeEntity> saveOaTempNoticeEntity(OaTempNoticeEntity oatempnoticeentity);
        /**
        * 批量删除
        * @param ids 要删除的集合列表
        * @return true代表删除成功  false代表删除失败
        */
        boolean deleteOaTempNoticeEntityList(List<Integer> ids);

        /**
         * 分页查询
         * @param pageNo  页数
         * @param size    一页最大的条数
         * @return  Page<OaTempNoticeEntity>
         */
        R<List<OaTempNoticeEntity>> getOaTempNoticeEntityList(PageVO<OaTempNoticeEntity> req);


        R<List<OaTempNoticeEntity>> queryTempNotice(PageVO<OaTempNoticeEntity>  oaTempNotice);




}

