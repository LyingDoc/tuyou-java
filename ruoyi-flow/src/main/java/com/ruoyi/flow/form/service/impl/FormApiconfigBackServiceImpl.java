package com.ruoyi.flow.form.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.flow.vo.PageVO;
import com.ruoyi.flow.vo.R;
import com.ruoyi.flow.form.entity.FormApiconfigBackEntity;
import com.ruoyi.flow.form.mapper.FormApiconfigBackMapper;
import com.ruoyi.flow.form.service.IFormApiconfigBackService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * api接口备份表信息 服务实现类富士达范德萨都是
 * </p>
 *
 * @author 刘亚平
 * @since 2023-01-15
 */
@Service
public class FormApiconfigBackServiceImpl extends ServiceImpl<FormApiconfigBackMapper, FormApiconfigBackEntity> implements IFormApiconfigBackService {
    /**
    * 通过主键获取数据
    *
    * @param fid 主键
    * @return 是否成功
    */
    @Override
    public  FormApiconfigBackEntity getById(String id){
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
    public R<FormApiconfigBackEntity> saveFormApiconfigBackEntity(FormApiconfigBackEntity formapiconfigbackentity){
        int row = 0;
        try {
          if( saveOrUpdate(formapiconfigbackentity)){
                return R.newOk(formapiconfigbackentity);
          }else{
               return R.newError("保存失败！");
          }
        } catch (Exception e) {
            e.printStackTrace();
            return R.newError("[{formapiconfigbackentity}] 未知异常");
        }

    }

    /**
    * 通过id集合批量删除
    *
    * @param fid 主键
    * @return 是否成功
    */
    @Override
    public boolean deleteFormApiconfigBackEntityList(List<Integer> ids) {
            int row = 0;
            try {
            row = this.baseMapper.deleteBatchIds(ids);
            } catch (Exception e) {
            e.printStackTrace();
            log.error("[{formapiconfigbackentity}] 未知异常");
            }
            return row>0;
    }

    @Override
    public R<List<FormApiconfigBackEntity>> getFormApiconfigBackEntityList(PageVO<FormApiconfigBackEntity> req){
            Page<FormApiconfigBackEntity> page = new Page<>(req.getPage(),req.getPagesize());
            Page formapiconfigbackentityPage = this.baseMapper.selectPage(page, new QueryWrapper<FormApiconfigBackEntity>());
           return R.newOk(formapiconfigbackentityPage.getRecords(),formapiconfigbackentityPage.getTotal()) ;
    }
    /**
     * 备份接口
     */
    public void backApiConfig(FormApiconfigBackEntity entity) {
        Integer version=  this.baseMapper.getMaxVersion(entity.getCallMethodCode());
        if(version==null){
            version=0;
        }
        entity.setFVersions(version+1);
        this.baseMapper.insert(entity);
    }

    /**
     * 获取指定版本的接口方法
     *
     * @param callMethodCode
     * @param versions
     * @return
     */
    public FormApiconfigBackEntity getApiConfigVersion(String callMethodCode, Integer versions) {
        QueryWrapper<FormApiconfigBackEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("call_method_code", callMethodCode).eq("versions", versions);
        return this.getOne(queryWrapper,false);
    }
}

