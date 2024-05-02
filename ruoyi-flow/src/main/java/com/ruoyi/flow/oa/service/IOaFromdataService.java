package com.ruoyi.flow.oa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.flow.vo.PageVO;
import com.ruoyi.flow.vo.R;
import com.ruoyi.flow.oa.entity.OaFromdataEntity;

import java.util.List;

/**
 * <p>
 * 流程表单数据ID 服务类
 * </p>
 *
 * @author 刘亚平
 * @since 2023-01-15
 */
public interface IOaFromdataService extends IService<OaFromdataEntity> {
        /**
        * 通过主键获取数据
        *
        * @param fid 主键
        * @return 是否成功
        */
       OaFromdataEntity getById(Long id);
        /**
       * 通过主键删除数据
       *
       * @param fid 主键
       * @return 是否成功
       */
        boolean deleteById(Long fid);


        /**
         * 修改
         * @param oafromdataentity
         * @return  true代表更新成功  false代表更新失败
         */
        R<OaFromdataEntity> saveOaFromdataEntity(OaFromdataEntity oafromdataentity);
        /**
        * 批量删除
        * @param ids 要删除的集合列表
        * @return true代表删除成功  false代表删除失败
        */
        boolean deleteOaFromdataEntityList(List<Integer> ids);

        /**
         * 分页查询
         * @param pageNo  页数
         * @param size    一页最大的条数
         * @return  Page<OaFromdataEntity>
         */
        R<List<OaFromdataEntity>> getOaFromdataEntityList(PageVO<OaFromdataEntity> req);

}

