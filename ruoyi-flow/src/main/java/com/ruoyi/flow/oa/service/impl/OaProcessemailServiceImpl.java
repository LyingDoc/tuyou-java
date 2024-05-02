package com.ruoyi.flow.oa.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.flow.vo.PageVO;
import com.ruoyi.flow.vo.R;
import com.ruoyi.flow.oa.entity.OaProcessemailEntity;
import com.ruoyi.flow.oa.mapper.OaProcessemailMapper;
import com.ruoyi.flow.oa.service.IOaProcessemailService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类富士达范德萨都是
 * </p>
 *
 * @author 刘亚平
 * @since 2023-01-15
 */
@Service
public class OaProcessemailServiceImpl extends ServiceImpl<OaProcessemailMapper, OaProcessemailEntity> implements IOaProcessemailService {
    /**
    * 通过主键获取数据
    *
    * @param fid 主键
    * @return 是否成功
    */
    @Override
    public  OaProcessemailEntity getById(Long id){
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
    public R<OaProcessemailEntity> saveOaProcessemailEntity(OaProcessemailEntity oaprocessemailentity){
        int row = 0;
        try {
          if( saveOrUpdate(oaprocessemailentity)){
                return R.newOk(oaprocessemailentity);
          }else{
               return R.newError("保存失败！");
          }
        } catch (Exception e) {
            e.printStackTrace();
            return R.newError("[{oaprocessemailentity}] 未知异常");
        }

    }

    /**
    * 通过id集合批量删除
    *
    * @param fid 主键
    * @return 是否成功
    */
    @Override
    public boolean deleteOaProcessemailEntityList(List<Integer> ids) {
            int row = 0;
            try {
            row = this.baseMapper.deleteBatchIds(ids);
            } catch (Exception e) {
            e.printStackTrace();
            log.error("[{oaprocessemailentity}] 未知异常");
            }
            return row>0;
    }

    @Override
    public R<List<OaProcessemailEntity>> getOaProcessemailEntityList(PageVO<OaProcessemailEntity> req){
            Page<OaProcessemailEntity> page = new Page<>(req.getPage(),req.getPagesize());
            Page oaprocessemailentityPage = this.baseMapper.selectPage(page, new QueryWrapper<OaProcessemailEntity>());
           return R.newOk(oaprocessemailentityPage.getRecords(),oaprocessemailentityPage.getTotal()) ;
    }
}

