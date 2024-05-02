package com.ruoyi.flow.oa.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.flow.vo.PageVO;
import com.ruoyi.flow.vo.R;
import com.ruoyi.flow.oa.entity.OaFromdataEntity;
import com.ruoyi.flow.oa.mapper.OaFromdataMapper;
import com.ruoyi.flow.oa.service.IOaFromdataService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 流程表单数据ID 服务实现类富士达范德萨都是
 * </p>
 *
 * @author 刘亚平
 * @since 2023-01-15
 */
@Service
public class OaFromdataServiceImpl extends ServiceImpl<OaFromdataMapper, OaFromdataEntity> implements com.ruoyi.flow.oa.service.IOaFromdataService {
    /**
    * 通过主键获取数据
    *
    * @param fid 主键
    * @return 是否成功
    */
    @Override
    public  OaFromdataEntity getById(Long id){
          return    baseMapper.selectById(id);
    }
    /**
     * 通过主键删除数据
     *
     * @param fid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long fid) {
            return this.baseMapper.deleteById(fid) > 0;
    }
    /**
    * 通过主键修改数据
    *
    * @param fid 主键
    * @return 是否成功
    */
    @Override
    public R<OaFromdataEntity> saveOaFromdataEntity(OaFromdataEntity oafromdataentity){
        int row = 0;
        try {
          if( saveOrUpdate(oafromdataentity)){
                return R.newOk(oafromdataentity);
          }else{
               return R.newError("保存失败！");
          }
        } catch (Exception e) {
            e.printStackTrace();
            return R.newError("[{oafromdataentity}] 未知异常");
        }

    }

    /**
    * 通过id集合批量删除
    *
    * @param fid 主键
    * @return 是否成功
    */
    @Override
    public boolean deleteOaFromdataEntityList(List<Integer> ids) {
            int row = 0;
            try {
            row = this.baseMapper.deleteBatchIds(ids);
            } catch (Exception e) {
            e.printStackTrace();
            log.error("[{oafromdataentity}] 未知异常");
            }
            return row>0;
    }

    @Override
    public R<List<OaFromdataEntity>> getOaFromdataEntityList(PageVO<OaFromdataEntity> req){
            Page<OaFromdataEntity> page = new Page<>(req.getPage(),req.getPagesize());
            Page oafromdataentityPage = this.baseMapper.selectPage(page, new QueryWrapper<OaFromdataEntity>());
           return R.newOk(oafromdataentityPage.getRecords(),oafromdataentityPage.getTotal()) ;
    }
}

