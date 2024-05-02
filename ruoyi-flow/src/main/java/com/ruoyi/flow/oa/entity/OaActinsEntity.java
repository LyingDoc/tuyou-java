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
 * 节点实例表
 * </p>
 *
 * @author 刘亚平
 * @since 2023-01-15
 */
@Data
@ApiModel("节点实例表")
@TableName("oa_actins")
public class OaActinsEntity extends Model<OaActinsEntity> {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
@ApiModelProperty("主键")
    @TableId(value = "oa_actins_id")
    private String oaActinsId;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("修改时间")
    private Date updateDate;
    /**
     * 修改人
     */
    @ApiModelProperty("修改人")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;
    /**
     * 节点ID
     */
@ApiModelProperty("节点ID")
    private String oaActId;
    /**
     * 流程模板ID
     */
@ApiModelProperty("流程模板ID")
    private String oaProcessId;
    /**
     * 审批人
     */
@ApiModelProperty("审批人")
    private String approversuser;
    /**
     * 有效时间
     */
@ApiModelProperty("有效时间")
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date arrivaldate;
    /**
     * 完成时间
     */
@ApiModelProperty("完成时间")
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date completeddate;
    /**
     * 待办打开时间
     */
@ApiModelProperty("待办打开时间")
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date opendate;
    /**
     * -1 准待办 在待办列表中不显示 1待办 2已处理完成，7系统异常
     */
@ApiModelProperty("-1 准待办 在待办列表中不显示 1待办 2已处理完成，7系统异常")
    private String actinsStatus;
    /**
     * 完成时间
     */
@ApiModelProperty("完成时间")
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date plannedcompletiontime;
    /**
     * 流程实例ID
     */
@ApiModelProperty("流程实例ID")
    private String oaProcessinsId;
    /**
     * 流程图节点ID
     */
@ApiModelProperty("流程图节点ID")
    private String actchartId;
}
