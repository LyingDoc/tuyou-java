package com.ruoyi.flow.oa.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.flow.vo.PageVO;
import com.ruoyi.flow.vo.R;
import com.ruoyi.flow.oa.entity.OaActoperatepermissionEntity;
import com.ruoyi.flow.oa.mapper.OaActoperatepermissionMapper;
import com.ruoyi.flow.oa.service.IOaActoperatepermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 节点按钮权限 服务实现类富士达范德萨都是
 * </p>
 *
 * @author 刘亚平
 * @since 2023-01-15
 */
@Service
public class OaActoperatepermissionServiceImpl extends ServiceImpl<OaActoperatepermissionMapper, OaActoperatepermissionEntity> implements com.ruoyi.flow.oa.service.IOaActoperatepermissionService {
    /**
    * 通过主键获取数据
    *
    * @param fid 主键
    * @return 是否成功
    */
    @Override
    public  OaActoperatepermissionEntity getById(Long id){
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
    public R<OaActoperatepermissionEntity> saveOaActoperatepermissionEntity(OaActoperatepermissionEntity oaactoperatepermissionentity){
        int row = 0;
        try {
          if( saveOrUpdate(oaactoperatepermissionentity)){
                return R.newOk(oaactoperatepermissionentity);
          }else{
               return R.newError("保存失败！");
          }
        } catch (Exception e) {
            e.printStackTrace();
            return R.newError("[{oaactoperatepermissionentity}] 未知异常");
        }

    }

    /**
    * 通过id集合批量删除
    *
    * @param fid 主键
    * @return 是否成功
    */
    @Override
    public boolean deleteOaActoperatepermissionEntityList(List<Integer> ids) {
            int row = 0;
            try {
            row = this.baseMapper.deleteBatchIds(ids);
            } catch (Exception e) {
            e.printStackTrace();
            log.error("[{oaactoperatepermissionentity}] 未知异常");
            }
            return row>0;
    }

    @Override
    public R<List<OaActoperatepermissionEntity>> getOaActoperatepermissionEntityList(PageVO<OaActoperatepermissionEntity> req){
            Page<OaActoperatepermissionEntity> page = new Page<>(req.getPage(),req.getPagesize());
            Page oaactoperatepermissionentityPage = this.baseMapper.selectPage(page, new QueryWrapper<OaActoperatepermissionEntity>());
           return R.newOk(oaactoperatepermissionentityPage.getRecords(),oaactoperatepermissionentityPage.getTotal()) ;
    }

    public List<OaActoperatepermissionEntity> queryByActId(String actId) {
        QueryWrapper<OaActoperatepermissionEntity> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("oa_Act_Id",actId);
        return this.baseMapper.selectList(queryWrapper);

    }
}

