package com.ruoyi.flow.form.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("面板中具体控件集合")
@Data
public class LayoutModelColumnsReq {
    @ApiModelProperty("面板中具体控件集合")
    private List<FormControlReq> list;
}
