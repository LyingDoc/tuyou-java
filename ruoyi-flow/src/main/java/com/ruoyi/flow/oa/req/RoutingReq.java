package com.ruoyi.flow.oa.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("路由信息")
@Data
public class RoutingReq {
    @ApiModelProperty("路由名称")
    private String ruleName;
    @ApiModelProperty("流向节点名称")
    private String nodeName;
    @ApiModelProperty("流向节点")
    private String nodeID;
    @ApiModelProperty("连线ID")
    private String lineid;
    @ApiModelProperty("路由条件")
    private List<RoutingWhereReq> routingList;
    @ApiModelProperty("运算集合")
    private List<ExpressionReq> expressionList;
}
