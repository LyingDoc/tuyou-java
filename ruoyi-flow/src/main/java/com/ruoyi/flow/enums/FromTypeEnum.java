package com.ruoyi.flow.enums;

public enum FromTypeEnum implements IBaseEnum<String> {
    ELEMENT("1", "element表单"),
    ANTDESIGN("2", "AntDesign表单"),
    QUESTIONNAIRE("3", "问卷调查"),
    SCREENANALYSE("4", "大屏分析"),
    FLOWFROM("5", "流程表单"),
    DETAILFROM("6", "明细列表转换的表单"),
    ;


    private String value;
    private String description;

    FromTypeEnum(String value, String description) {
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
