package com.ruoyi.flow.form.vo;

import com.ruoyi.flow.vo.HeadEntityVO;
import lombok.Data;

import java.util.List;

@Data
public class ExportDetailSqlVO {
    /**
     * 明细拼接增删改的sql
     */
    private String exportDetailSql;
    private List<HeadEntityVO> headEntity;
    private  String title;
}
