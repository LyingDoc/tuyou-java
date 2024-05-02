package com.ruoyi.flow.enums;

public enum ListButPowerEnum implements IBaseEnum<String> {
    add("add", "新增"),
    export("export", "导出"),
    report("report", "报表"),
    edit("edit", "修改"),
    delete("delete", "删除")
    ;
    private String value;
    private String description;

    ListButPowerEnum(String value, String description) {
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

