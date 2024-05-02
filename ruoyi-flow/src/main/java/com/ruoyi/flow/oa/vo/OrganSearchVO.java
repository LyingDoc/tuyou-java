package com.ruoyi.flow.oa.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("组织架构")
public class OrganSearchVO {
    /// <summary>
    /// 编号【部门编号或工号】
    /// </summary>
    @ApiModelProperty("编号【部门编号或工号】")
    public String No;

    /// <summary>
    /// 名称
    /// </summary>
    @ApiModelProperty("名称")
    public String Name;


    /// <summary>
    /// 性别：男），女
    /// </summary>
    @ApiModelProperty("性别：男），女")
    public String SexName;

    /// <summary>
    /// 是否部门
    /// </summary>
    @ApiModelProperty("model 1 机构  2 角色 3 人员")
    public int model;

    /// <summary>
    /// 头像
    /// </summary>
    @ApiModelProperty("头像")
    public String HeadImgUrl ;


    /// <summary>
    /// 上级部门名称
    /// </summary>
    @ApiModelProperty("上级部门名称")
    public String DeptPName ;

    /// <summary>
    /// 上级部门编码
    /// </summary>
    @ApiModelProperty("上级部门编码")
    public String DeptPNo ;
    /// <summary>
    /// 真实名称
    /// </summary>
    @ApiModelProperty("真实名称")
    public String RealName ;
    @ApiModelProperty("层级code")
    public String parentCodes;
}
