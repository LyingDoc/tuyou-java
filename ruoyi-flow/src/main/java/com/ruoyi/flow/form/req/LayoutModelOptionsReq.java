package com.ruoyi.flow.form.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("模块操作属性类")
@Data
public class LayoutModelOptionsReq {
 @ApiModelProperty("tab选项面板列属性")
 private List<LayoutModelPanelistReq>  planelist;
 @ApiModelProperty("面板中具体控件集合")
 private List<FormControlReq> columns;
}
