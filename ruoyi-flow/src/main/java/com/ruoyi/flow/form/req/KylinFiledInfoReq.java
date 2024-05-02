package com.ruoyi.flow.form.req;

import lombok.Data;

@Data
public class KylinFiledInfoReq {
    private  String tableName;
    private  String columnName;
    private  String columnComment;
}
