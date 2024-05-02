package com.ruoyi.flow.form.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("保存配置显示的列")
@Data
public class SaveConfigShowCelNamesReq {
    @ApiModelProperty("请求接口名称")
    public String callMethodCodeName;

    @ApiModelProperty("设置显示列")
    private  String showCelNames;
    @ApiModelProperty("设置列的顺序")
    private String headList;
}
