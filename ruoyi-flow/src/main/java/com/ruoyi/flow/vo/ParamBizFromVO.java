package com.ruoyi.flow.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("参数验证实体")
@Data
public class ParamBizFromVO {
    @ApiModelProperty("参数属性名称")
    private String  property;
    @ApiModelProperty("是否必填")
    private Boolean required=false;
    @ApiModelProperty("验证类型")
    private String paramtype;
    @ApiModelProperty("验证提示说明字段")
    private String paramname;
    @ApiModelProperty("字段类型(object,Array,常量)")
    private String variabletype;
    @ApiModelProperty("子项")
    private List<ParamBizFromVO> children;
    @ApiModelProperty("是否第一级")
    private Boolean isfirst=false;
    private Boolean _expanded=false;
    private  int _level;
    private Boolean _show=false;
    @ApiModelProperty("表单Id")
    private  String formId;

}
