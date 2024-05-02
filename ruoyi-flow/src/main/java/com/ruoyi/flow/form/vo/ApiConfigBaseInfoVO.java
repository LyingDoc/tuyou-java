package com.ruoyi.flow.form.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

///获取Api基本信息
@Data
public class ApiConfigBaseInfoVO {
    @ApiModelProperty("apiID")
    private String fid;
    @ApiModelProperty("方法中文名称")
    private String methodName;
    @ApiModelProperty("方法说明")
    private String remarks;
    @ApiModelProperty("参数配置JSON")
    private String paramConfig;
    @ApiModelProperty("方法类型为1 设置前端列表头部信息的")
    private String headList;
    @ApiModelProperty("设置显示的列")
    private String showCelNames;
}
