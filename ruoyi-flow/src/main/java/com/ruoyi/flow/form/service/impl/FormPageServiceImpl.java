package com.ruoyi.flow.form.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.flow.vo.PackageInterfaceInfoVO;
import com.ruoyi.flow.vo.PageVO;
import com.ruoyi.flow.vo.R;
import com.ruoyi.flow.vo.SelectPageOrApiVO;
import com.ruoyi.flow.form.entity.FormPageEntity;
import com.ruoyi.flow.form.mapper.FormPageMapper;
import com.ruoyi.flow.form.service.IFormPageService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class FormPageServiceImpl extends ServiceImpl<FormPageMapper, FormPageEntity> implements com.ruoyi.flow.form.service.IFormPageService {
    /**
     * 通过主键获取数据
     *
     * @param fid 主键
     * @return 是否成功 
     */
    @Override
    public FormPageEntity getById(String id) {
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
    public R<FormPageEntity> saveFormPageEntity(FormPageEntity formpageentity) {
        int row = 0;
        try {
            if (saveOrUpdate(formpageentity)) {
                return R.newOk(formpageentity);
            } else {
                return R.newError("保存失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.newError("[{formpageentity}] 未知异常");
        }

    }

    /**
     * 通过id集合批量删除
     *
     * @param fid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteFormPageEntityList(List<Integer> ids) {
        int row = 0;
        try {
            row = this.baseMapper.deleteBatchIds(ids);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[{formpageentity}] 未知异常");
        }
        return row > 0;
    }

    @Override
    public R<List<FormPageEntity>> getFormPageEntityList(PageVO<FormPageEntity> req) {
        Page<FormPageEntity> page = new Page<>(req.getPage(), req.getPagesize());
        Page formpageentityPage = this.baseMapper.selectPage(page, new QueryWrapper<FormPageEntity>());
        return R.newOk(formpageentityPage.getRecords(), formpageentityPage.getTotal());
    }

    public R<String> addPage(String pageName, String title) {

        QueryWrapper<FormPageEntity> queryWrapper = new QueryWrapper();
        queryWrapper.eq("page_Name", pageName);
        FormPageEntity tappPageEntity = this.getOne(queryWrapper,false);
        if (tappPageEntity != null) {
            tappPageEntity.setPageName(pageName);
            tappPageEntity.setDescription(title);
            this.updateById(tappPageEntity);
            return R.newOk(tappPageEntity.getFormPageId());
        } else {
            tappPageEntity = new FormPageEntity();
            tappPageEntity.setDescription(title);
            tappPageEntity.setPageName(pageName);
            tappPageEntity.setDeleted(0);
            this.baseMapper.insert(tappPageEntity);
            return R.newOk(tappPageEntity.getFormPageId());
        }

    }


    /**
     * 获取包接口信息
     *
     * @return
     */
    public List<PackageInterfaceInfoVO> getPackageInterfaceInfo() {
        List<SelectPageOrApiVO> list = this.baseMapper.selectPageOrApi();
        return getChildResourceByPId(list, "0");
    }

    /**
     * 递归组装菜单树
     *
     * @param menulist
     * @param pId
     * @return
     */
    List<PackageInterfaceInfoVO> getChildResourceByPId(List<SelectPageOrApiVO> menulist, String pId) {
        List<PackageInterfaceInfoVO> child = new ArrayList<>();
        menulist.forEach(ee -> {
            if (ee.getParentId() != null && ee.getParentId().equals(pId)) {
                PackageInterfaceInfoVO childitem = new PackageInterfaceInfoVO();
                BeanUtils.copyProperties(ee, childitem);
                childitem.setChildren(getChildResourceByPId(menulist, ee.getFid()));
                child.add(childitem);
            }
        });
        return child;
    }
}

