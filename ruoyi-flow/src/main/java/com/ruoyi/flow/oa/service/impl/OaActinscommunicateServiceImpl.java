package com.ruoyi.flow.oa.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.flow.vo.PageVO;
import com.ruoyi.flow.vo.R;
import com.ruoyi.flow.oa.entity.OaActinscommunicateEntity;
import com.ruoyi.flow.oa.mapper.OaActinscommunicateMapper;
import com.ruoyi.flow.oa.service.IOaActinscommunicateService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 沟通，抄送表 服务实现类富士达范德萨都是
 * </p>
 *
 * @author 刘亚平
 * @since 2023-01-15
 */
@Service
public class OaActinscommunicateServiceImpl extends ServiceImpl<OaActinscommunicateMapper, OaActinscommunicateEntity> implements com.ruoyi.flow.oa.service.IOaActinscommunicateService {
    /**
    * 通过主键获取数据
    *
    * @param fid 主键
    * @return 是否成功
    */
    @Override
    public  OaActinscommunicateEntity getById(Long id){
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
    public R<OaActinscommunicateEntity> saveOaActinscommunicateEntity(OaActinscommunicateEntity oaactinscommunicateentity){
        int row = 0;
        try {
          if( saveOrUpdate(oaactinscommunicateentity)){
                return R.newOk(oaactinscommunicateentity);
          }else{
               return R.newError("保存失败！");
          }
        } catch (Exception e) {
            e.printStackTrace();
            return R.newError("[{oaactinscommunicateentity}] 未知异常");
        }

    }

    /**
    * 通过id集合批量删除
    *
    * @param fid 主键
    * @return 是否成功
    */
    @Override
    public boolean deleteOaActinscommunicateEntityList(List<Integer> ids) {
            int row = 0;
            try {
            row = this.baseMapper.deleteBatchIds(ids);
            } catch (Exception e) {
            e.printStackTrace();
            log.error("[{oaactinscommunicateentity}] 未知异常");
            }
            return row>0;
    }

    @Override
    public R<List<OaActinscommunicateEntity>> getOaActinscommunicateEntityList(PageVO<OaActinscommunicateEntity> req){
            Page<OaActinscommunicateEntity> page = new Page<>(req.getPage(),req.getPagesize());
            Page oaactinscommunicateentityPage = this.baseMapper.selectPage(page, new QueryWrapper<OaActinscommunicateEntity>());
           return R.newOk(oaactinscommunicateentityPage.getRecords(),oaactinscommunicateentityPage.getTotal()) ;
    }

    public List<OaActinscommunicateEntity> queryTodoCommunicate(String fapproversuser, String factinsid, String fprocessinsid){
        return this.baseMapper.queryTodoCommunicate(fapproversuser,factinsid,fprocessinsid);
    }
}

