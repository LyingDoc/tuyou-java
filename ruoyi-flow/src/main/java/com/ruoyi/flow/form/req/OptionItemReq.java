package com.ruoyi.flow.form.req;

import lombok.Data;

@Data
public class OptionItemReq {
    /**
     * 选项名称
     */
    private  String label;
    /**
     * 选项值
     */
    private String value;
}
