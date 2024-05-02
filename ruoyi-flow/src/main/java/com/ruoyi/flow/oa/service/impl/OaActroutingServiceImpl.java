package com.ruoyi.flow.oa.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.flow.vo.PageVO;
import com.ruoyi.flow.vo.R;
import com.ruoyi.flow.oa.entity.OaActroutingEntity;
import com.ruoyi.flow.oa.mapper.OaActroutingMapper;
import com.ruoyi.flow.oa.service.IOaActroutingService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 路由信息表 服务实现类富士达范德萨都是
 * </p>
 *
 * @author 刘亚平
 * @since 2023-01-15
 */
@Service
public class OaActroutingServiceImpl extends ServiceImpl<OaActroutingMapper, OaActroutingEntity> implements IOaActroutingService {
    /**
    * 通过主键获取数据
    *
    * @param fid 主键
    * @return 是否成功
    */
    @Override
    public  OaActroutingEntity getById(String id){
          return    baseMapper.selectById(id);
    }
    /**
     * 通过主键删除数据
     *
     * @param fid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String fid) {
            return this.baseMapper.deleteById(fid) > 0;
    }
    /**
    * 通过主键修改数据
    *
    * @param fid 主键
    * @return 是否成功
    */
    @Override
    public R<OaActroutingEntity> saveOaActroutingEntity(OaActroutingEntity oaactroutingentity){
        int row = 0;
        try {
          if( saveOrUpdate(oaactroutingentity)){
                return R.newOk(oaactroutingentity);
          }else{
               return R.newError("保存失败！");
          }
        } catch (Exception e) {
            e.printStackTrace();
            return R.newError("[{oaactroutingentity}] 未知异常");
        }

    }

    /**
    * 通过id集合批量删除
    *
    * @param fid 主键
    * @return 是否成功
    */
    @Override
    public boolean deleteOaActroutingEntityList(List<Integer> ids) {
            int row = 0;
            try {
            row = this.baseMapper.deleteBatchIds(ids);
            } catch (Exception e) {
            e.printStackTrace();
            log.error("[{oaactroutingentity}] 未知异常");
            }
            return row>0;
    }

    @Override
    public R<List<OaActroutingEntity>> getOaActroutingEntityList(PageVO<OaActroutingEntity> req){
            Page<OaActroutingEntity> page = new Page<>(req.getPage(),req.getPagesize());
            Page oaactroutingentityPage = this.baseMapper.selectPage(page, new QueryWrapper<OaActroutingEntity>());
           return R.newOk(oaactroutingentityPage.getRecords(),oaactroutingentityPage.getTotal()) ;
    }


    public List<OaActroutingEntity> queryByActID(String actId){
        QueryWrapper<OaActroutingEntity> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("oa_act_id",actId);
        return this.baseMapper.selectList(queryWrapper);
    }
}

