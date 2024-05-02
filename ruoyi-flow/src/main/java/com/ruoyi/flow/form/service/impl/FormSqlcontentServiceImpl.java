package com.ruoyi.flow.form.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.flow.vo.PageVO;
import com.ruoyi.flow.vo.R;
import com.ruoyi.flow.form.entity.FormSqlcontentEntity;
import com.ruoyi.flow.form.mapper.FormSqlcontentMapper;
import com.ruoyi.flow.form.service.IFormSqlcontentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 执行sql 服务实现类富士达范德萨都是
 * </p>
 *
 * @author 刘亚平
 * @since 2023-01-15
 */
@Service
public class FormSqlcontentServiceImpl extends ServiceImpl<FormSqlcontentMapper, FormSqlcontentEntity> implements com.ruoyi.flow.form.service.IFormSqlcontentService {
    /**
    * 通过主键获取数据
    *
    * @param fid 主键
    * @return 是否成功
    */
    @Override
    public  FormSqlcontentEntity getById(String id){
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
    public R<FormSqlcontentEntity> saveFormSqlcontentEntity(FormSqlcontentEntity formsqlcontententity){
        int row = 0;
        try {
          if( saveOrUpdate(formsqlcontententity)){
                return R.newOk(formsqlcontententity);
          }else{
               return R.newError("保存失败！");
          }
        } catch (Exception e) {
            e.printStackTrace();
            return R.newError("[{formsqlcontententity}] 未知异常");
        }

    }

    /**
    * 通过id集合批量删除
    *
    * @param fid 主键
    * @return 是否成功
    */
    @Override
    public boolean deleteFormSqlcontentEntityList(List<Integer> ids) {
            int row = 0;
            try {
            row = this.baseMapper.deleteBatchIds(ids);
            } catch (Exception e) {
            e.printStackTrace();
            log.error("[{formsqlcontententity}] 未知异常");
            }
            return row>0;
    }

    @Override
    public R<List<FormSqlcontentEntity>> getFormSqlcontentEntityList(PageVO<FormSqlcontentEntity> req){
            Page<FormSqlcontentEntity> page = new Page<>(req.getPage(),req.getPagesize());
            Page formsqlcontententityPage = this.baseMapper.selectPage(page, new QueryWrapper<FormSqlcontentEntity>());
           return R.newOk(formsqlcontententityPage.getRecords(),formsqlcontententityPage.getTotal()) ;
    }

    public void saveSqlContent(String sqlcontent, Long id) {
        FormSqlcontentEntity entity = new FormSqlcontentEntity();
        entity.setSqlcontent(sqlcontent);
        entity.setFormSqlcontentId(id.toString());
        this.baseMapper.insert(entity);
    }
}

