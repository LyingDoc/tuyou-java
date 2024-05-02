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
 * 节点信息
 * </p>
 *
 * @author 刘亚平
 * @since 2023-01-15
 */
@Data
@ApiModel("节点信息")
@TableName("oa_act")
public class OaActEntity extends Model<OaActEntity> {
    private static final long serialVersionUID = 1L;
    /**
     * 主键ID
     */
@ApiModelProperty("主键ID")
    @TableId(value = "oa_act_id")
    private String oaActId;
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
     * 节点名称
     */
@ApiModelProperty("节点名称")
    private String actname;
    /**
     * 画图节点ID
     */
@ApiModelProperty("画图节点ID")
    private String actchartId;
    /**
     * 是否发邮件
     */
@ApiModelProperty("是否发邮件")
    private Boolean isemail;
    /**
     * 是否驳回历史
     */
@ApiModelProperty("是否驳回历史")
    private Boolean isrejecthistory;
    /**
     * 驳回是否发邮件
     */
@ApiModelProperty("驳回是否发邮件")
    private Boolean isrejectemail;
    /**
     * 是否连续自动跳过
     */
@ApiModelProperty("是否连续自动跳过")
    private Boolean iscontinuousby;
    /**
     * 非连续自动跳过
     */
@ApiModelProperty("非连续自动跳过")
    private Boolean isnotcontinuousby;
    /**
     * 处理方式
     */
    @ApiModelProperty("处理方式")
    private String handletype;
    /**
     * 会签规则类型
     */
@ApiModelProperty("会签规则类型")
    private String handlecountersigntype;
    /**
     * 审批类型
     */
@ApiModelProperty("审批类型")
    private String approvaltype;
    /**
     * 审批对象显示名称
     */
@ApiModelProperty("审批对象显示名称")
    private String approvername;
    /**
     * 审批对象
     */
@ApiModelProperty("审批对象")
    private String approver;
    /**
     * 流程模板ID
     */
@ApiModelProperty("流程模板ID")
    private String processid;
    /**
     * 会签方式
     */
@ApiModelProperty("会签方式")
    private String countersigntype;
    /**
     * 是否自动追溯
     */
@ApiModelProperty("是否自动追溯")
    private String traceup;
    /**
     * 抄送人员
     */
@ApiModelProperty("抄送人员")
    private String ccPerson;
}
