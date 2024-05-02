package com.ruoyi.flow.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("返回分页")
public class FormPageVO<T> {
    @ApiModelProperty("总行数")
    private Integer records;
    @ApiModelProperty("当前页码")
    private Integer page;
    @ApiModelProperty("总页数")
    private Integer total;
    @ApiModelProperty("返回对象")
    private List<T> rows;
    @ApiModelProperty("执行是否成功")
    private Boolean success;
}
