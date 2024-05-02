package com.ruoyi.flow.form.vo;

import lombok.Data;

/**
 * 根据关联的表单 拼接sql
 */
@Data
public class SelectSqlVO {
    ///返回字段
    private String returnFild;
    //拼接sql
    private String innerSql;
}
