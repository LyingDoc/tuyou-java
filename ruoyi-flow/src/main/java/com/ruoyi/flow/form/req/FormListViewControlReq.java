package com.ruoyi.flow.form.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("明细列表每列的控件")
@Data
public class FormListViewControlReq extends FormControlReq {
//    @ApiModelProperty("控件主题")
//    public String title;
    @ApiModelProperty("数据保存字段名称")
    public String dataIndex;

}
