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
 * 沟通，抄送表
 * </p>
 *
 * @author 刘亚平
 * @since 2023-01-15
 */
@Data
@ApiModel("沟通，抄送表")
@TableName("oa_actinscommunicate")
public class OaActinscommunicateEntity extends Model<OaActinscommunicateEntity> {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @TableId(value = "oa_actinscommunicate_id")
    private Long oaActinscommunicateId;
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
     * 节点实例ID
     */
    @ApiModelProperty("节点实例ID")
    private String oaActinsId;
    /**
     * 类别
     */
    @ApiModelProperty("类别")
    private String communicatetype;
    /**
     * 状态
     */
    @ApiModelProperty("状态")
    private String communicateStatus;
    /**
     * 审批人
     */
    @ApiModelProperty("审批人")
    private String approversuser;
    /**
     * 打开时间
     */
    @ApiModelProperty("打开时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date opendate;
    /**
     * 完成时间
     */
    @ApiModelProperty("完成时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date completeddate;
    /**
     * 节点名称
     */
    @ApiModelProperty("节点名称")
    private String actname;
    /**
     * 流程实例ID
     */
    @ApiModelProperty("流程实例ID")
    private String oaProcessinsId;
}
