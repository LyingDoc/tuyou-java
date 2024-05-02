package com.ruoyi.flow.form.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.flow.vo.PageVO;
import com.ruoyi.flow.vo.R;
import com.ruoyi.flow.form.entity.FormApiconfigBackEntity;

import java.util.List;

/**
 * <p>
 * api接口备份表信息 服务类
 * </p>
 *
 * @author 刘亚平
 * @since 2023-01-15
 */
public interface IFormApiconfigBackService extends IService<FormApiconfigBackEntity> {
        /**
        * 通过主键获取数据
        *
        * @param fid 主键
        * @return 是否成功
        */
       FormApiconfigBackEntity getById(String id);
        /**
       * 通过主键删除数据
       *
       * @param fid 主键
       * @return 是否成功
       */
        boolean deleteById(String fid);


        /**
         * 修改
         * @param formapiconfigbackentity
         * @return  true代表更新成功  false代表更新失败
         */
        R<FormApiconfigBackEntity> saveFormApiconfigBackEntity(FormApiconfigBackEntity formapiconfigbackentity);
        /**
        * 批量删除
        * @param ids 要删除的集合列表
        * @return true代表删除成功  false代表删除失败
        */
        boolean deleteFormApiconfigBackEntityList(List<Integer> ids);

        /**
         * 分页查询
         * @param pageNo  页数
         * @param size    一页最大的条数
         * @return  Page<FormApiconfigBackEntity>
         */
        R<List<FormApiconfigBackEntity>> getFormApiconfigBackEntityList(PageVO<FormApiconfigBackEntity> req);

}

