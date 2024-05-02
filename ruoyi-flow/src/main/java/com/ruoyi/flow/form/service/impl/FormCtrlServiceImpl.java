package com.ruoyi.flow.form.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.flow.vo.PageVO;
import com.ruoyi.flow.vo.R;
import com.ruoyi.flow.form.entity.FormCtrlEntity;
import com.ruoyi.flow.form.mapper.FormCtrlMapper;
import com.ruoyi.flow.form.service.IFormCtrlService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 表单控件 服务实现类富士达范德萨都是
 * </p>
 *
 * @author 刘亚平
 * @since 2023-01-15
 */
@Service
public class FormCtrlServiceImpl extends ServiceImpl<FormCtrlMapper, FormCtrlEntity> implements IFormCtrlService {
    /**
     * 通过主键获取数据
     *
     * @param fid 主键
     * @return 是否成功
     */
    @Override
    public FormCtrlEntity getById(String id) {
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
    public R<FormCtrlEntity> saveFormCtrlEntity(FormCtrlEntity formctrlentity) {
        int row = 0;
        try {
            if (saveOrUpdate(formctrlentity)) {
                return R.newOk(formctrlentity);
            } else {
                return R.newError("保存失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.newError("[{formctrlentity}] 未知异常");
        }

    }

    /**
     * 通过id集合批量删除
     *
     * @param fid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteFormCtrlEntityList(List<Integer> ids) {
        int row = 0;
        try {
            row = this.baseMapper.deleteBatchIds(ids);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[{formctrlentity}] 未知异常");
        }
        return row > 0;
    }

    @Override
    public R<List<FormCtrlEntity>> getFormCtrlEntityList(PageVO<FormCtrlEntity> req) {
        Page<FormCtrlEntity> page = new Page<>(req.getPage(), req.getPagesize());
        QueryWrapper<FormCtrlEntity> queryWrapper = new QueryWrapper<FormCtrlEntity>();
        if(req.getParam()!=null) {
            if (StringUtils.isNotEmpty(req.getParam().getCtrlTitle())) {
                queryWrapper.like("ctrl_title", req.getParam().getCtrlTitle());
            }
            if (StringUtils.isNotEmpty(req.getParam().getCtrlType())) {
                queryWrapper.eq("ctrl_type", req.getParam().getCtrlType());
            }
            if (StringUtils.isNotEmpty(req.getParam().getCtrlRemarks())) {
                queryWrapper.like("ctrl_remarks", req.getParam().getCtrlRemarks());
            }
        }
        Page formctrlentityPage = this.baseMapper.selectPage(page, queryWrapper);
        return R.newOk(formctrlentityPage.getRecords(), formctrlentityPage.getTotal());
    }
}

