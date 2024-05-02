package com.ruoyi.flow.oa.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.flow.vo.PageVO;
import com.ruoyi.flow.vo.R;
import com.ruoyi.flow.oa.entity.OaPorcessinsLogEntity;
import com.ruoyi.flow.oa.mapper.OaPorcessinsLogMapper;
import com.ruoyi.flow.oa.req.QueryProcessInsLogReq;
import com.ruoyi.flow.oa.service.IOaPorcessinsLogService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 流程实例表 服务实现类富士达范德萨都是
 * </p>
 *
 * @author 刘亚平
 * @since 2023-01-15
 */
@Service
public class OaPorcessinsLogServiceImpl extends ServiceImpl<OaPorcessinsLogMapper, OaPorcessinsLogEntity> implements com.ruoyi.flow.oa.service.IOaPorcessinsLogService {
    /**
    * 通过主键获取数据
    *
    * @param fid 主键
    * @return 是否成功
    */
    @Override
    public  OaPorcessinsLogEntity getById(Long id){
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
    public R<OaPorcessinsLogEntity> saveOaPorcessinsLogEntity(OaPorcessinsLogEntity oaporcessinslogentity){
        int row = 0;
        try {
          if( saveOrUpdate(oaporcessinslogentity)){
                return R.newOk(oaporcessinslogentity);
          }else{
               return R.newError("保存失败！");
          }
        } catch (Exception e) {
            e.printStackTrace();
            return R.newError("[{oaporcessinslogentity}] 未知异常");
        }

    }

    /**
    * 通过id集合批量删除
    *
    * @param fid 主键
    * @return 是否成功
    */
    @Override
    public boolean deleteOaPorcessinsLogEntityList(List<Integer> ids) {
            int row = 0;
            try {
            row = this.baseMapper.deleteBatchIds(ids);
            } catch (Exception e) {
            e.printStackTrace();
            log.error("[{oaporcessinslogentity}] 未知异常");
            }
            return row>0;
    }

    @Override
    public R<List<OaPorcessinsLogEntity>> getOaPorcessinsLogEntityList(PageVO<OaPorcessinsLogEntity> req){
            Page<OaPorcessinsLogEntity> page = new Page<>(req.getPage(),req.getPagesize());
            Page oaporcessinslogentityPage = this.baseMapper.selectPage(page, new QueryWrapper<OaPorcessinsLogEntity>());
           return R.newOk(oaporcessinslogentityPage.getRecords(),oaporcessinslogentityPage.getTotal()) ;
    }


    public R<List<OaPorcessinsLogEntity>> queryProcessInsLogList(QueryProcessInsLogReq porcessinsLogEntity){
        QueryWrapper<OaPorcessinsLogEntity>    porcessinsLog=new QueryWrapper();
        if(StringUtils.isNotBlank(porcessinsLogEntity.getProcinsno())){
            porcessinsLog.eq("procinsno",porcessinsLogEntity.getProcinsno());
        }
        if(StringUtils.isNotBlank(porcessinsLogEntity.getProcinsname())){
            porcessinsLog.eq("procinsname",porcessinsLogEntity.getProcinsname());
        }
        if(porcessinsLogEntity.getCreationdate().size()>0){
            porcessinsLog.le("create_date",porcessinsLogEntity.getCreationdate().get(0)).le("create_date",porcessinsLogEntity.getCreationdate().get(1));
        }
        porcessinsLog.orderByDesc("update_Date");
        Page<OaPorcessinsLogEntity> page = new Page<>(porcessinsLogEntity.getPage(),porcessinsLogEntity.getRows());
        Page<OaPorcessinsLogEntity> porcessinsLogPageentity=this.baseMapper.selectPage(page,porcessinsLog);
        return R.newOk(porcessinsLogPageentity.getRecords(),porcessinsLogPageentity.getTotal());
    }
}

