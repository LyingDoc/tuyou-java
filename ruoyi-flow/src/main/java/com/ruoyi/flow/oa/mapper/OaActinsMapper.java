package com.ruoyi.flow.oa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.flow.oa.entity.OaActinsEntity;

import java.util.List;


/**
 * <p>
 * 节点实例表 Mapper 接口
 * </p>
 *
 * @author 刘亚平
 * @since 2023-01-15
 */
public interface OaActinsMapper extends BaseMapper<OaActinsEntity> {
    List< OaActinsEntity> queryTodoActins(String processinsid);
}

