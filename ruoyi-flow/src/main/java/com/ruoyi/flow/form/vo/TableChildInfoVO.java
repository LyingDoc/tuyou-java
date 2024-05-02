package com.ruoyi.flow.form.vo;


import com.ruoyi.flow.vo.HeadEntityVO;
import lombok.Data;

import java.util.List;

@Data
public class TableChildInfoVO {
    /**
     * 明细拼接增删改的sql
     */
    private StringBuffer saveInfoSql;
    private List<HeadEntityVO> headEntity;
}
