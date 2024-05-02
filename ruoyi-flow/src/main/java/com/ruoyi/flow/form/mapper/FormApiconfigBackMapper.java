package com.ruoyi.flow.form.mapper;

import com.ruoyi.flow.form.entity.FormApiconfigBackEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * <p>
 * api接口备份表信息 Mapper 接口
 * </p>
 *
 * @author 刘亚平
 * @since 2023-01-15
 */
public interface FormApiconfigBackMapper extends BaseMapper<FormApiconfigBackEntity> {
    /**
     * 根据callMethodCode 获取最大版本
     * @param callMethodCode
     * @return
     */
    Integer getMaxVersion(String callMethodCode);
}

