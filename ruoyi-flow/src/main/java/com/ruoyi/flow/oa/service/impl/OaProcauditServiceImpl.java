package com.ruoyi.flow.oa.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.flow.vo.PageVO;
import com.ruoyi.flow.vo.R;
import com.ruoyi.flow.oa.entity.OaProcauditEntity;
import com.ruoyi.flow.oa.mapper.OaProcauditMapper;
import com.ruoyi.flow.oa.service.IOaProcauditService;
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
public class OaProcauditServiceImpl extends ServiceImpl<OaProcauditMapper, OaProcauditEntity> implements IOaProcauditService {
    /**
    * 通过主键获取数据
    *
    * @param fid 主键
    * @return 是否成功
    */
    @Override
    public  OaProcauditEntity getById(Long id){
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
    public R<OaProcauditEntity> saveOaProcauditEntity(OaProcauditEntity oaprocauditentity){
        int row = 0;
        try {
          if( saveOrUpdate(oaprocauditentity)){
                return R.newOk(oaprocauditentity);
          }else{
               return R.newError("保存失败！");
          }
        } catch (Exception e) {
            e.printStackTrace();
            return R.newError("[{oaprocauditentity}] 未知异常");
        }

    }

    /**
    * 通过id集合批量删除
    *
    * @param fid 主键
    * @return 是否成功
    */
    @Override
    public boolean deleteOaProcauditEntityList(List<Integer> ids) {
            int row = 0;
            try {
            row = this.baseMapper.deleteBatchIds(ids);
            } catch (Exception e) {
            e.printStackTrace();
            log.error("[{oaprocauditentity}] 未知异常");
            }
            return row>0;
    }

    @Override
    public R<List<OaProcauditEntity>> getOaProcauditEntityList(PageVO<OaProcauditEntity> req){
            Page<OaProcauditEntity> page = new Page<>(req.getPage(),req.getPagesize());
            Page oaprocauditentityPage = this.baseMapper.selectPage(page, new QueryWrapper<OaProcauditEntity>());
           return R.newOk(oaprocauditentityPage.getRecords(),oaprocauditentityPage.getTotal()) ;
    }

    /**
     * 查询多条数据
     * @menu 根据流程实例ID获取审批记录
     * @return 对象列表
     */

    public List<OaProcauditEntity> GetProcAudit(OaProcauditEntity entity){
        return this.baseMapper.GetProcAudit(entity.getOaProcinsId()) ;

    }
}

