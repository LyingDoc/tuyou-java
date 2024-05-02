package com.ruoyi.flow.form.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("tab选项面板属性")
@Data
public class LayoutModelPanelistReq {
    @ApiModelProperty("面板中具体控件集合")
    private List<FormControlReq> columns;
}
