package com.ruoyi.flow.oa.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author 刘亚平
 * @since 2023-01-15
 */
@Data
@ApiModel("")
@TableName("oa_procaudit")
public class OaProcauditEntity extends Model<OaProcauditEntity> {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
@ApiModelProperty("主键")
    @TableId(value = "oa_procaudit_id")
    private Long oaProcauditId;
    /**
     * 创建日期
     */
    @ApiModelProperty("创建日期")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("创建人")
    private String createBy;
    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty("修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;
    /**
     * 修改人
     */
    @ApiModelProperty("修改人")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;
    /**
     * 流程实例ID
     */
@ApiModelProperty("流程实例ID")
    private String oaProcinsId;
    /**
     * 节点实例ID
     */
@ApiModelProperty("节点实例ID")
    private String oaActinsId;
    /**
     * 节点名称
     */
@ApiModelProperty("节点名称")
    private String actname;
    /**
     * 审批动作
     */
@ApiModelProperty("审批动作")
    private String approvelaction;
    /**
     * 备注信息
     */
@ApiModelProperty("备注信息")
    private String approveldescr;
    /**
     * 节点实例创建时间
     */
@ApiModelProperty("节点实例创建时间")
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date actcreatedate;
    /**
     * 第一次打开时间
     */
@ApiModelProperty("第一次打开时间")
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date actopendate;
    /**
     * 完成时间
     */
@ApiModelProperty("完成时间")
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date actfishdate;
    /**
     * 审批人
     */
@ApiModelProperty("审批人")
    private String approversuser;
    /**
     * 审批人名称
     */
@ApiModelProperty("审批人名称")
    private String approversname;
    /**
     * 是否当前
     */
@ApiModelProperty("是否当前")
    private String iscurrent;
}
