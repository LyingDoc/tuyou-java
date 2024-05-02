package com.ruoyi.flow.form.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("控件信息 主要为了问卷调查功能")
@Data
public class FormControlReq {
    /**
     * 控件类型
     */
    public String type;
    @ApiModelProperty("唯一ID")
    public String model;
    /**
     * 控件属性对象
     */
    public ControlOptionsReq options;


}
