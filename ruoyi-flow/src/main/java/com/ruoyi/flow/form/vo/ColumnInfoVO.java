package com.ruoyi.flow.form.vo;

import lombok.Data;

@Data
public class ColumnInfoVO {
    /**
     * 字段名称
     */
    public  String columnname;
    /**
     * 字段类型
     */
    public  String columntype;
    /**
     * 字段备注信息
     */
    public String columnComment;
}
