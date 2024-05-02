package com.ruoyi.flow.oa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.flow.oa.entity.OaProcessEntity;
import com.ruoyi.flow.oa.vo.ProcessChartVO;
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
public interface OaProcessMapper extends BaseMapper<OaProcessEntity> {
    OaProcessEntity  getMaxVersionProcess(@Param("processBid") String processBid);
    OaProcessEntity  getMaxVersionProcessCode(@Param("processCode") String processCode);
    OaProcessEntity  getStartMaxVersionProcess(@Param("processBid") String processBid);
    OaProcessEntity  getStartMaxVersionProcessCode(@Param("processCode") String processCode);
    List<OaProcessEntity> getStartProcess();

    ProcessChartVO getProcessChart(@Param("processid") String processid);

    List<OaProcessEntity> queryMaxList(OaProcessEntity oaProcessEntity);

    List<OaProcessEntity> queryAllList(OaProcessEntity oaProcessEntity);
}

