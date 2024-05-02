package com.ruoyi.flow.oa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.flow.oa.entity.OaSendNoticeEntity;
import com.ruoyi.flow.oa.entity.OaTempNoticeEntity;
import com.ruoyi.flow.oa.vo.ProcessChartVO;
import com.ruoyi.flow.oa.vo.QuerySendNoticeVO;
import com.ruoyi.flow.vo.HomeDataVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p>
 * 通知发送表 Mapper 接口
 * </p>
 *
 * @author 刘亚平
 * @since 2023-01-15
 */
public interface OaSendNoticeMapper extends BaseMapper<OaSendNoticeEntity> {
    List<QuerySendNoticeVO> querySendNotice(QuerySendNoticeVO oaTempNotice);

    HomeDataVO getHomeData(@Param("username") String username);
}

