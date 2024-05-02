package com.ruoyi.flow.oa.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.flow.vo.PageVO;
import com.ruoyi.flow.vo.R;
import com.ruoyi.flow.oa.entity.OaActEntity;
import com.ruoyi.flow.oa.mapper.OaActMapper;
import com.ruoyi.flow.oa.service.IOaActService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 节点信息 服务实现类富士达范德萨都是
 * </p>
 *
 * @author 刘亚平
 * @since 2023-01-15
 */
@Service
public class OaActServiceImpl extends ServiceImpl<OaActMapper, OaActEntity> implements IOaActService {
    /**
    * 通过主键获取数据
    *
    * @param fid 主键
    * @return 是否成功
    */
    @Override
    public  OaActEntity getById(String id){
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
    public R<OaActEntity> saveOaActEntity(OaActEntity oaactentity){
        int row = 0;
        try {
          if( saveOrUpdate(oaactentity)){
                return R.newOk(oaactentity);
          }else{
               return R.newError("保存失败！");
          }
        } catch (Exception e) {
            e.printStackTrace();
            return R.newError("[{oaactentity}] 未知异常");
        }

    }

    /**
    * 通过id集合批量删除
    *
    * @param fid 主键
    * @return 是否成功
    */
    @Override
    public boolean deleteOaActEntityList(List<Integer> ids) {
            int row = 0;
            try {
            row = this.baseMapper.deleteBatchIds(ids);
            } catch (Exception e) {
            e.printStackTrace();
            log.error("[{oaactentity}] 未知异常");
            }
            return row>0;
    }

    @Override
    public R<List<OaActEntity>> getOaActEntityList(PageVO<OaActEntity> req){
            Page<OaActEntity> page = new Page<>(req.getPage(),req.getPagesize());
            Page oaactentityPage = this.baseMapper.selectPage(page, new QueryWrapper<OaActEntity>());
           return R.newOk(oaactentityPage.getRecords(),oaactentityPage.getTotal()) ;
    }

    /**
     * 通过流程模板ID 查找对应节点信息
     * @param oaAct
     */
    public List<OaActEntity> queryByProcessId(String ProcessId){
        QueryWrapper<OaActEntity> queryWrapper=new QueryWrapper();
        queryWrapper.eq("processid",ProcessId);
        return this.baseMapper.selectList(queryWrapper);
    }

    public OaActEntity  queryByProcessIdActchartId(String ProcessId, String chartActId){
        QueryWrapper<OaActEntity> queryWrapper=new QueryWrapper();
        queryWrapper.eq("processid",ProcessId).eq("actchart_id",chartActId);
        return this.getOne(queryWrapper,true);

    }
    public List<OaActEntity> queryByNodeIdAndProcid(String actchartid, String procid) {
        QueryWrapper<OaActEntity> queryWrapper=new QueryWrapper();
        queryWrapper.eq("processid",procid).eq("actchart_id",actchartid);
        return this.baseMapper.selectList(queryWrapper);
    }
}

