package com.ruoyi.flow.form.vo;

import lombok.Data;

import java.util.List;

/***
 * 选人控件多选实体
 */
@Data
public class SelectorMultiVO {
    private String text;
    private List<SelectorItemVO> value;
}
