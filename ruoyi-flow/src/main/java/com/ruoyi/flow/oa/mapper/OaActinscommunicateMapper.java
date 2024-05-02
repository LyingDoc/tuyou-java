package com.ruoyi.flow.oa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.flow.oa.entity.OaActinscommunicateEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p>
 * 沟通，抄送表 Mapper 接口
 * </p>
 *
 * @author 刘亚平
 * @since 2023-01-15
 */
public interface OaActinscommunicateMapper extends BaseMapper<OaActinscommunicateEntity> {
    /**
     *
     * @param fapproversuser 审批人
     * @param factinsid 流程节点实例
     * @param fprocessinsid 流程实例
     * @return
     */
    List<OaActinscommunicateEntity> queryTodoCommunicate(@Param("fapproversuser") String fapproversuser, @Param("factinsid") String factinsid, @Param("fprocessinsid") String fprocessinsid);

}

