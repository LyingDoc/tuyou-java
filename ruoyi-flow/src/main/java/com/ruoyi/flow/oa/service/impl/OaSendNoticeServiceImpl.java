package com.ruoyi.flow.oa.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.flow.oa.entity.OaCommonnoEntity;
import com.ruoyi.flow.oa.vo.QuerySendNoticeVO;
import com.ruoyi.flow.vo.HomeDataVO;
import com.ruoyi.flow.vo.PageVO;
import com.ruoyi.flow.vo.R;
import com.ruoyi.flow.oa.entity.OaSendNoticeEntity;
import com.ruoyi.flow.oa.mapper.OaSendNoticeMapper;
import com.ruoyi.flow.oa.service.IOaSendNoticeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 通知发送表 服务实现类富士达范德萨都是
 * </p>
 *
 * @author 刘亚平
 * @since 2023-01-15
 */
@Service
public class OaSendNoticeServiceImpl extends ServiceImpl<OaSendNoticeMapper, OaSendNoticeEntity> implements IOaSendNoticeService {
    /**
    * 通过主键获取数据
    *
    * @param fid 主键
    * @return 是否成功
    */
    @Override
    public  OaSendNoticeEntity getById(Long id){
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
    public R<OaSendNoticeEntity> saveOaSendNoticeEntity(OaSendNoticeEntity oasendnoticeentity){
        int row = 0;
        try {
          if( saveOrUpdate(oasendnoticeentity)){
                return R.newOk(oasendnoticeentity);
          }else{
               return R.newError("保存失败！");
          }
        } catch (Exception e) {
            e.printStackTrace();
            return R.newError("[{oasendnoticeentity}] 未知异常");
        }

    }

    /**
    * 通过id集合批量删除
    *
    * @param fid 主键
    * @return 是否成功
    */
    @Override
    public boolean deleteOaSendNoticeEntityList(List<Integer> ids) {
            int row = 0;
            try {
            row = this.baseMapper.deleteBatchIds(ids);
            } catch (Exception e) {
            e.printStackTrace();
            log.error("[{oasendnoticeentity}] 未知异常");
            }
            return row>0;
    }

    @Override
    public R<List<OaSendNoticeEntity>> getOaSendNoticeEntityList(PageVO<OaSendNoticeEntity> req){
            Page<OaSendNoticeEntity> page = new Page<>(req.getPage(),req.getPagesize());
        QueryWrapper<OaSendNoticeEntity> queryWrapper=     new QueryWrapper<OaSendNoticeEntity>();
        if(StringUtils.isNotBlank(req.getParam().getProcessinsName())){
            queryWrapper.like("processins_name",req.getParam().getProcessinsName());
        }
        if(req.getParam().getIsRead()!=null){
            queryWrapper.eq("is_read",req.getParam().getIsRead());
        }
        if(StringUtils.isNotBlank(req.getParam().getSendUserId())){
            queryWrapper.eq("SendUser_Id",req.getParam().getSendUserId());
        }
        queryWrapper.orderByAsc("update_Date");
            Page oasendnoticeentityPage = this.baseMapper.selectPage(page, queryWrapper);
           return R.newOk(oasendnoticeentityPage.getRecords(),oasendnoticeentityPage.getTotal()) ;
    }

    @Override
    public  R<List<QuerySendNoticeVO>>  querySendNotice(PageVO<QuerySendNoticeVO>  oaTempNotice){
        int page =oaTempNotice.getPage();
        int rows = oaTempNotice.getPagesize();
        PageHelper.startPage(page, rows);
       if(oaTempNotice.getParam()==null){
           oaTempNotice.setParam(new QuerySendNoticeVO());
       }
        oaTempNotice.getParam().setSendUserId(SecurityUtils.getUsername());
        List<QuerySendNoticeVO>  querySendNoticeVOList = this.baseMapper.querySendNotice(oaTempNotice.getParam());
        PageInfo pageInfo = new PageInfo(querySendNoticeVOList);
        return R.newOk(querySendNoticeVOList,(int)pageInfo.getTotal());
    }
    @Override
    public HomeDataVO getHomeData(){
        String  username = SecurityUtils.getUsername();
        return this.baseMapper.getHomeData(username);
    }
}

