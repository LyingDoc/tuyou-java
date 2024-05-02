package com.ruoyi.flow.form.mapper;

import com.ruoyi.flow.form.entity.FormFormEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.flow.form.req.QueryFromReq;
import com.ruoyi.flow.form.vo.QueryFromVO;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 刘亚平
 * @since 2023-01-15
 */
public interface FormFormMapper extends BaseMapper<FormFormEntity> {
    List<QueryFromVO>  queryFromList(QueryFromReq req);
    String getMainDatabaseName();

    List<QueryFromVO> queryFromDesignList(QueryFromReq req);
}

