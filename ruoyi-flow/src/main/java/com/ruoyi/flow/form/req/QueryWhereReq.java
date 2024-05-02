package com.ruoyi.flow.form.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("查询条件设置")
@Data
public class QueryWhereReq {
    @ApiModelProperty("字段名称")
    private String filed;
    @ApiModelProperty("字段值")
    private  String value;
    @ApiModelProperty("1 表单变量 2自定义变量 3url变量 4当前环境变量")
    private Integer variabletype;
    @ApiModelProperty("查询类型")
    private  String opt;
}
