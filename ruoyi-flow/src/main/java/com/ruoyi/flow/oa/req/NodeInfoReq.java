package com.ruoyi.flow.oa.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("节点信息")
@Data
public class NodeInfoReq {
    @ApiModelProperty("节点ID")
    private String id;
    @ApiModelProperty("节点名称")
    private String name;
    @ApiModelProperty("节点类型 startnode 发起节点 endnode结束节点 flownode节点信息")
    private String type;
    @ApiModelProperty("坐标left")
    private String left;
    @ApiModelProperty("坐标top")
    private String top;
    @ApiModelProperty("节点图标")
    private String icon;
    @ApiModelProperty("节点状态")
    private String state;
    @ApiModelProperty("节点详细信息")
    private NodeDetailInfoReq nodeinfo;
}
