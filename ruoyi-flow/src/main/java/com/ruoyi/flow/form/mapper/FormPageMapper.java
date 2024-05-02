package com.ruoyi.flow.form.mapper;

import com.ruoyi.flow.vo.SelectPageOrApiVO;
import com.ruoyi.flow.form.entity.FormPageEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 刘亚平
 * @since 2023-01-15
 */
public interface FormPageMapper extends BaseMapper<FormPageEntity> {
    /**
     * 接口和包管理
     * @return
     */
    List<SelectPageOrApiVO> selectPageOrApi();
}

