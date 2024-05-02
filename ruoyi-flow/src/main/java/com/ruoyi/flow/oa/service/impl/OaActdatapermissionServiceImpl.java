package com.ruoyi.flow.oa.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.flow.vo.PageVO;
import com.ruoyi.flow.vo.R;
import com.ruoyi.flow.oa.entity.OaActdatapermissionEntity;
import com.ruoyi.flow.oa.mapper.OaActdatapermissionMapper;
import com.ruoyi.flow.oa.service.IOaActdatapermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 节点权限表 服务实现类富士达范德萨都是
 * </p>
 *
 * @author 刘亚平
 * @since 2023-01-15
 */
@Service
public class OaActdatapermissionServiceImpl extends ServiceImpl<OaActdatapermissionMapper, OaActdatapermissionEntity> implements IOaActdatapermissionService {
    /**
     * 通过主键获取数据
     *
     * @param fid 主键
     * @return 是否成功
     */
    @Override
    public OaActdatapermissionEntity getById(Long id) {
        return baseMapper.selectById(id);
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
    public R<OaActdatapermissionEntity> saveOaActdatapermissionEntity(OaActdatapermissionEntity oaactdatapermissionentity) {
        int row = 0;
        try {
            if (saveOrUpdate(oaactdatapermissionentity)) {
                return R.newOk(oaactdatapermissionentity);
            } else {
                return R.newError("保存失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.newError("[{oaactdatapermissionentity}] 未知异常");
        }

    }

    /**
     * 通过id集合批量删除
     *
     * @param fid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteOaActdatapermissionEntityList(List<Integer> ids) {
        int row = 0;
        try {
            row = this.baseMapper.deleteBatchIds(ids);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[{oaactdatapermissionentity}] 未知异常");
        }
        return row > 0;
    }

    @Override
    public R<List<OaActdatapermissionEntity>> getOaActdatapermissionEntityList(PageVO<OaActdatapermissionEntity> req) {
        Page<OaActdatapermissionEntity> page = new Page<>(req.getPage(), req.getPagesize());
        Page oaactdatapermissionentityPage = this.baseMapper.selectPage(page, new QueryWrapper<OaActdatapermissionEntity>());
        return R.newOk(oaactdatapermissionentityPage.getRecords(), oaactdatapermissionentityPage.getTotal());
    }


    public OaActdatapermissionEntity queryByActId(String actId) {
        QueryWrapper<OaActdatapermissionEntity> queryWrapper = new QueryWrapper();
        queryWrapper.eq("oa_act_id", actId);
        return this.getOne(queryWrapper,false);

    }
}

