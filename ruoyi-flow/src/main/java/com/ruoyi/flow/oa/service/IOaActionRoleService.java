package com.ruoyi.flow.oa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.flow.vo.PageVO;
import com.ruoyi.flow.vo.R;
import com.ruoyi.flow.oa.entity.OaActionRoleEntity;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 刘亚平
 * @since 2023-01-15
 */
public interface IOaActionRoleService extends IService<OaActionRoleEntity> {
        /**
        * 通过主键获取数据
        *
        * @param fid 主键
        * @return 是否成功
        */
       OaActionRoleEntity getById(Long id);
        /**
       * 通过主键删除数据
       *
       * @param fid 主键
       * @return 是否成功
       */
        boolean deleteById(Long fid);


        /**
         * 修改
         * @param oaactionroleentity
         * @return  true代表更新成功  false代表更新失败
         */
        R<OaActionRoleEntity> saveOaActionRoleEntity(OaActionRoleEntity oaactionroleentity);
        /**
        * 批量删除
        * @param ids 要删除的集合列表
        * @return true代表删除成功  false代表删除失败
        */
        boolean deleteOaActionRoleEntityList(List<Integer> ids);

        /**
         * 分页查询
         * @param pageNo  页数
         * @param size    一页最大的条数
         * @return  Page<OaActionRoleEntity>
         */
        R<List<OaActionRoleEntity>> getOaActionRoleEntityList(PageVO<OaActionRoleEntity> req);

}

