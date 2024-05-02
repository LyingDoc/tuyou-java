package com.ruoyi.flow.oa.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.flow.vo.PageVO;
import com.ruoyi.flow.vo.R;
import com.ruoyi.flow.oa.entity.OaProcesstypeEntity;
import com.ruoyi.flow.oa.mapper.OaProcesstypeMapper;
import com.ruoyi.flow.oa.service.IOaProcesstypeService;
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
public class OaProcesstypeServiceImpl extends ServiceImpl<OaProcesstypeMapper, OaProcesstypeEntity> implements IOaProcesstypeService {
    /**
    * 通过主键获取数据
    *
    * @param fid 主键
    * @return 是否成功
    */
    @Override
    public  OaProcesstypeEntity getById(Long id){
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
    public R<OaProcesstypeEntity> saveOaProcesstypeEntity(OaProcesstypeEntity oaprocesstypeentity){
        int row = 0;
        try {
          if( saveOrUpdate(oaprocesstypeentity)){
                return R.newOk(oaprocesstypeentity);
          }else{
               return R.newError("保存失败！");
          }
        } catch (Exception e) {
            e.printStackTrace();
            return R.newError("[{oaprocesstypeentity}] 未知异常");
        }

    }

    /**
    * 通过id集合批量删除
    *
    * @param fid 主键
    * @return 是否成功
    */
    @Override
    public boolean deleteOaProcesstypeEntityList(List<Integer> ids) {
            int row = 0;
            try {
            row = this.baseMapper.deleteBatchIds(ids);
            } catch (Exception e) {
            e.printStackTrace();
            log.error("[{oaprocesstypeentity}] 未知异常");
            }
            return row>0;
    }


    @Override
    public R<List<OaProcesstypeEntity>> getOaProcesstypeEntityList(PageVO<OaProcesstypeEntity> req){

             QueryWrapper<OaProcesstypeEntity> oaProcesstypeEntityQueryWrapper=  new QueryWrapper<OaProcesstypeEntity>();
             if(req.getParam()!=null) {
                 if (StringUtils.isNotEmpty(req.getParam().getProcesstypename())) {
                     oaProcesstypeEntityQueryWrapper.like("processtypename", req.getParam().getProcesstypename());
                 }
                 if (StringUtils.isNotEmpty(req.getParam().getRemarks())) {
                     oaProcesstypeEntityQueryWrapper.like("remarks", req.getParam().getRemarks());
                 }
             }
            oaProcesstypeEntityQueryWrapper.orderByAsc("update_Date");
            Page<OaProcesstypeEntity> page = new Page<>(req.getPage(),req.getPagesize());
            Page oaprocesstypeentityPage = this.baseMapper.selectPage(page,oaProcesstypeEntityQueryWrapper );
           return R.newOk(oaprocesstypeentityPage.getRecords(),oaprocesstypeentityPage.getTotal()) ;
    }

}

