package com.ruoyi.flow.form.enums;

import com.ruoyi.flow.enums.IBaseEnum;

public enum ApiTypeEnum implements IBaseEnum<String> {
    LIST("1", "列表"),
    QUERY("2", "查询"),
    OPERATE("3", "操作"),
    IMPORT("4", "导入"),
    FOROBJECT("5", "对象"),
    ;


    private String value;
    private String description;

    ApiTypeEnum(String value, String description) {
        this.value = value;
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getValue() {
        return value;
    }
}
