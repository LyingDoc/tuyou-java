package com.ruoyi.flow.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("包接口管理输出实体")
public class PackageInterfaceInfoVO {
    @ApiModelProperty("id")
    private  String fid;
    @ApiModelProperty("包名称")
    private String pageName;
    @ApiModelProperty("备注描述")
    private String description;
    @ApiModelProperty("父级ID")
    private String parentId;
    @ApiModelProperty("孩子")
    private List<PackageInterfaceInfoVO> children;

    @ApiModelProperty("类型")
    private  String typeName;

    @ApiModelProperty("回调方法")
    private String callMethodCode;
}
