package com.ruoyi.flow.oa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.flow.oa.entity.OaProcauditEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 刘亚平
 * @since 2023-01-15
 */
public interface OaProcauditMapper extends BaseMapper<OaProcauditEntity> {
    List<OaProcauditEntity> GetProcAudit(@Param("fprocinsid") String fprocinsid);
}

