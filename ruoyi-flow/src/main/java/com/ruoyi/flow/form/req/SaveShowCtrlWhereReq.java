package com.ruoyi.flow.form.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@ApiModel("保存显示条件")
@Data
public class SaveShowCtrlWhereReq {
    @ApiModelProperty("请求接口名称")
    public String callMethodCodeName;

    @ApiModelProperty("设置显示列")
    private  String showCtrlWhere;
}
