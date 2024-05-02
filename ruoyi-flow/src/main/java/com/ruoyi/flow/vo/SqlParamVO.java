package com.ruoyi.flow.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
@Data
@ApiModel("执行sql对象")
public class SqlParamVO {
    @ApiModelProperty("执行sql对象")
    private  String sql;
    @ApiModelProperty("执行sql 传参数对象")
    private List<Object> param;
    @ApiModelProperty("传参数类型")
    private  List<Integer> types;
}
