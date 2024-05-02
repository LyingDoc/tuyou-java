package com.ruoyi.flow.oa.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.flow.vo.PageVO;
import com.ruoyi.flow.vo.R;
import com.ruoyi.flow.oa.entity.OaActinsEntity;
import com.ruoyi.flow.oa.mapper.OaActinsMapper;
import com.ruoyi.flow.oa.service.IOaActinsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 节点实例表 服务实现类富士达范德萨都是
 * </p>
 *
 * @author 刘亚平
 * @since 2023-01-15
 */
@Service
public class OaActinsServiceImpl extends ServiceImpl<OaActinsMapper, OaActinsEntity> implements IOaActinsService {
    /**
     * 通过主键获取数据
     *
     * @param fid 主键
     * @return 是否成功
     */
    @Override
    public OaActinsEntity getById(String id) {
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
    public R<OaActinsEntity> saveOaActinsEntity(OaActinsEntity oaactinsentity) {
        int row = 0;
        try {
            if (saveOrUpdate(oaactinsentity)) {
                return R.newOk(oaactinsentity);
            } else {
                return R.newError("保存失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.newError("[{oaactinsentity}] 未知异常");
        }

    }

    /**
     * 通过id集合批量删除
     *
     * @param fid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteOaActinsEntityList(List<Integer> ids) {
        int row = 0;
        try {
            row = this.baseMapper.deleteBatchIds(ids);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[{oaactinsentity}] 未知异常");
        }
        return row > 0;
    }

    @Override
    public R<List<OaActinsEntity>> getOaActinsEntityList(PageVO<OaActinsEntity> req) {
        Page<OaActinsEntity> page = new Page<>(req.getPage(), req.getPagesize());
        Page oaactinsentityPage = this.baseMapper.selectPage(page, new QueryWrapper<OaActinsEntity>());
        return R.newOk(oaactinsentityPage.getRecords(), oaactinsentityPage.getTotal());
    }

    /**
     * 根据流程实例ID 获取当前走过的节点
     *
     * @param fprocessinsid
     * @return
     */
    public List<OaActinsEntity> getCompletedNodeList(String fprocessinsid) {
        List<Integer> statuslist = new ArrayList<>();
        statuslist.add(2);
        statuslist.add(1);
        OaActinsEntity oaActins = new OaActinsEntity();
        if (StringUtils.isEmpty(fprocessinsid)) {
            fprocessinsid = "-1";
        }
        QueryWrapper<OaActinsEntity> queryWrapper = new QueryWrapper();
        queryWrapper.eq("oa_processins_id", fprocessinsid);
        queryWrapper.in("actins_status", statuslist);
        return this.baseMapper.selectList(queryWrapper);
    }

    public List<OaActinsEntity> queryTodoActins(String processinsid) {
        return this.baseMapper.queryTodoActins(processinsid);
    }

    /**
     * 获取当前未处理 会签的节点实例集合
     *
     * @param arrivalDate
     * @param procinsid
     * @param factid
     * @return
     */
    public List<OaActinsEntity> GetNotHandleCountersignActIns(Date arrivalDate, String procinsid, String factid) {
        QueryWrapper<OaActinsEntity> queryWrapper = new QueryWrapper();
        queryWrapper.eq("oa_processins_id", procinsid).eq("actins_status", -1).le("arrivaldate", arrivalDate).orderByAsc("create_date");
        return this.baseMapper.selectList(queryWrapper);
    }

    public List<OaActinsEntity> GetNotHandleActIns(String procinsid, String factid) {
        List<String> actinsStatus = new ArrayList<>();
        actinsStatus.add("1");
        actinsStatus.add("-1");
        QueryWrapper<OaActinsEntity> queryWrapper = new QueryWrapper();
        queryWrapper.eq("oa_Act_Id", factid).in("actins_status", actinsStatus).eq("oa_Processins_Id", procinsid);
        return this.baseMapper.selectList(queryWrapper);
    }

    public List<OaActinsEntity> GetAllCurrentActIns(Date arrivalDate, String procinsid, String factid) {
        QueryWrapper<OaActinsEntity> queryWrapper = new QueryWrapper();
        queryWrapper.eq("oa_Processins_Id", procinsid).eq("oa_Act_Id",factid).le("arrivaldate",arrivalDate);
        return this.baseMapper.selectList(queryWrapper);
    }
}

