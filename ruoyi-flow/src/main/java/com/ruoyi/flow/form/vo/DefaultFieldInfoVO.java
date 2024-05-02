package com.ruoyi.flow.form.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/***
 * 默认字段信息
 */
@Data
public class DefaultFieldInfoVO {
    @ApiModelProperty("默认字段名称")
    private  String fieldName;
    @ApiModelProperty("默认字段类型")
    private  String fieldType;
    @ApiModelProperty("是否默认修改")
    private Boolean isUpdate;
    @ApiModelProperty("是否主键")
    private  Boolean isPrimaryKey;
    @ApiModelProperty("搜索条件")
    private String whereSql;
    @ApiModelProperty("是否列表展示")
    private  Boolean IsGridShow;
    @ApiModelProperty("描述")
    private String describe;
    @ApiModelProperty("是否公共字段")
    private Boolean isPublic;
    @ApiModelProperty("字段赋值")
    private String fieldAssignment;
}
