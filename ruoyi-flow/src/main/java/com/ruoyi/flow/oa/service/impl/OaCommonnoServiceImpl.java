package com.ruoyi.flow.oa.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.flow.vo.PageVO;
import com.ruoyi.flow.vo.R;
import com.ruoyi.flow.oa.entity.OaCommonnoEntity;
import com.ruoyi.flow.oa.mapper.OaCommonnoMapper;
import com.ruoyi.flow.oa.service.IOaCommonnoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类富士达范德萨都是
 * </p>
 *
 * @author 刘亚平
 * @since 2023-01-15
 */
@Service
public class OaCommonnoServiceImpl extends ServiceImpl<OaCommonnoMapper, OaCommonnoEntity> implements com.ruoyi.flow.oa.service.IOaCommonnoService {
    /**
     * 通过主键获取数据
     *
     * @param fid 主键
     * @return 是否成功
     */
    @Override
    public OaCommonnoEntity getById(String id) {
        return baseMapper.selectById(id);
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
    public R<OaCommonnoEntity> saveOaCommonnoEntity(OaCommonnoEntity oacommonnoentity) {
        int row = 0;
        try {
            if (saveOrUpdate(oacommonnoentity)) {
                return R.newOk(oacommonnoentity);
            } else {
                return R.newError("保存失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.newError("[{oacommonnoentity}] 未知异常");
        }

    }

    /**
     * 通过id集合批量删除
     *
     * @param fid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteOaCommonnoEntityList(List<Integer> ids) {
        int row = 0;
        try {
            row = this.baseMapper.deleteBatchIds(ids);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[{oacommonnoentity}] 未知异常");
        }
        return row > 0;
    }

    @Override
    public R<List<OaCommonnoEntity>> getOaCommonnoEntityList(PageVO<OaCommonnoEntity> req) {
        Page<OaCommonnoEntity> page = new Page<>(req.getPage(), req.getPagesize());
        QueryWrapper<OaCommonnoEntity> queryWrapper=     new QueryWrapper<OaCommonnoEntity>();
        if(StringUtils.isNotBlank(req.getParam().getModulename())){
            queryWrapper.like("modulename",req.getParam().getModulename());
        }
        queryWrapper.orderByAsc("update_Date");
        Page oacommonnoentityPage = this.baseMapper.selectPage(page,queryWrapper );
        return R.newOk(oacommonnoentityPage.getRecords(), oacommonnoentityPage.getTotal());
    }

    public String GetProcInstNumber(String bMoudleEN, String bModuleID) {
        QueryWrapper<OaCommonnoEntity> queryWrapper = new QueryWrapper();
        if (StringUtils.isNotBlank(bMoudleEN) && StringUtils.isNotBlank(bModuleID)) {
            queryWrapper.eq("modulename", bMoudleEN).eq("moduleid", bModuleID);

        } else {
            bMoudleEN="wmp";
            bModuleID="M20110803110024";
            queryWrapper.eq("modulename", "wmp").eq("moduleid", "M20110803110024");
        }
        try {   //02 获取当天该模块最大流水号
            int procInstNo = 0;
            OaCommonnoEntity entity = this.getOne(queryWrapper,false);// db.TB_Common_NumberID.Where(x => x.FModuleName == entity.FModuleName && x.FCreationDate.Value.Day == DateTime.Now.Day && x.FCreationDate.Value.Year == DateTime.Now.Year && x.FCreationDate.Value.Month == DateTime.Now.Month).OrderByDescending(ee => ee.FOrderID).FirstOrDefault(); ///new Achievo.MMIP.CommonService.CommonNumberService().GetMaxOrderIDByMoudleName(entity.FModuleName);
            if (entity!=null) {
                procInstNo = entity.getOrderid() == null ? 0 : entity.getOrderid();
                String updatedaystr =entity.getUpdateDate()==null?"": new SimpleDateFormat("yyyyMMdd").format(entity.getUpdateDate());
                String nowdaystr = new SimpleDateFormat("yyyyMMdd").format(new Date());
                ///新的一天自动编码重新编码
                if (!updatedaystr.equals(nowdaystr)) {
                    procInstNo = 0;
                }
            }else{
                entity=new OaCommonnoEntity();
                entity.setModuleid(bModuleID);
                entity.setModulename(bMoudleEN);
                entity.setCreateBy(SecurityUtils.getUsername());
                entity.setCreateDate(new Date());
            }
            entity.setUpdateBy(SecurityUtils.getUsername());
            entity.setUpdateDate(new Date());
            entity.setOrderid(procInstNo + 1);
            String procInstNumber = new SimpleDateFormat("yyyyMMdd").format(new Date());
            ;
            ///db.InserByMoudleName(entity.FID, entity.FModuleID, entity.FModuleName, CurrentUserID, entity.FCreationDate, null, CurrentUserID, entity.FCreationDate, objectParams);

            String order = "000" + entity.getOrderid();
            if (order.length() > 4)
                order = order.substring(order.length() - 4, order.length());
            // 由于碰到没有预见性的人的意见，此处我们难受的去掉“-”
            //procInstNumber = entity.FModuleName.ToUpper() + procInstNumber + "-" + order;
            procInstNumber = entity.getModulename().toUpperCase() + procInstNumber + order;

            if (entity.getOaCommonnoId() != null)
                this.baseMapper.updateById(entity);
            else
                this.baseMapper.insert(entity);
            return procInstNumber;

        } catch (Exception ex) {
            throw ex;
        }
    }
}

