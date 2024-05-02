package com.ruoyi.flow.form.mapper;

import com.ruoyi.flow.form.entity.FormApiconfigEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.flow.form.req.FromApiConfigReq;
import com.ruoyi.flow.form.req.QueryApiListReq;
import com.ruoyi.flow.form.vo.ApiConfigVO;
import com.ruoyi.flow.form.vo.QueryApiListVO;
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
public interface FormApiconfigMapper extends BaseMapper<FormApiconfigEntity> {
    /**
     *根据方法code 获取Api接口
     * @return
     */
    ApiConfigVO getApiConfigByCallMethodCode(@Param("methodCode") String methodCode, @Param("fid") String fid);

    /**
     * 根据表单名称 查询关联的接口方法
     * @return
     */
    List<ApiConfigVO> queryFormApiconfigList(FromApiConfigReq fromApiConfigReq);

    List<QueryApiListVO> queryApiList(QueryApiListReq req);
}

