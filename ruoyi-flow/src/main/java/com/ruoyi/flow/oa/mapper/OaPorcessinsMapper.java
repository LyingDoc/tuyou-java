package com.ruoyi.flow.oa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.flow.oa.entity.OaPorcessinsEntity;
import com.ruoyi.flow.oa.vo.QueryPorcessinsVO;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 刘亚平
 * @since 2023-01-15
 */
public interface OaPorcessinsMapper extends BaseMapper<OaPorcessinsEntity> {
    /*
     所有视图
   */
    List<OaPorcessinsEntity> MyAllPorcessinsList(QueryPorcessinsVO tappPorcessins);
    //我的待办待阅
    List<OaPorcessinsEntity> MyToDoPorcessinsList(QueryPorcessinsVO tappPorcessins);
    //获取流程待办数量
    Integer  MyToDoPorcessinsCount(QueryPorcessinsVO tappPorcessins);
    //我的已办已阅
    List<OaPorcessinsEntity> MyReadporcessinsList(QueryPorcessinsVO tappPorcessins);
    //我的草稿
    List<OaPorcessinsEntity> MyDraftPorcessinsList(QueryPorcessinsVO tappPorcessins);
    ///我的发起
    List<OaPorcessinsEntity> MyStartPorcessinsList(QueryPorcessinsVO tappPorcessins);
}

