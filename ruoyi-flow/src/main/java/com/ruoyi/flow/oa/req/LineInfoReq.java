package com.ruoyi.flow.oa.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("连线信息")
@Data
public class LineInfoReq {
    @ApiModelProperty("开始节点")
    private String from;
    @ApiModelProperty("到节点")
    private String to;
    @ApiModelProperty("连线ID")
    private String lineid;
    @ApiModelProperty("连线名称")
    private String label;
    @ApiModelProperty("连线类型")
    private String type;
    @ApiModelProperty("连线回调处理类型")
    private String handletype;
    @ApiModelProperty("连线回调url地址")
    private String apiurl;
    @ApiModelProperty("连线类型")
    private String ajaxtype;
    @ApiModelProperty("连线执行sql")
    private String sqlcontent;
}
