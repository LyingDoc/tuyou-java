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
@TableName("oa_porcessins")
public class OaPorcessinsEntity extends Model<OaPorcessinsEntity> {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @TableId(value = "oa_porcessins_id")
    private String oaPorcessinsId;
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
     * 流程模板ID
     */
    @ApiModelProperty("流程模板ID")
    private String oaProcessId;
    /**
     * 表单数据ID
     */
    @ApiModelProperty("表单数据ID")
    private String oaFromdataId;
    /**
     * 流程主题
     */
    @ApiModelProperty("流程主题")
    private String procinsname;
    /**
     * 流程编号
     */
    @ApiModelProperty("流程编号")
    private String procinsno;
    /**
     * 发起人名称
     */
    @ApiModelProperty("发起人名称")
    private String startername;
    /**
     * 发起人
     */
    @ApiModelProperty("发起人")
    private String starter;
    /**
     * 发起时间
     */
    @ApiModelProperty("发起时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date starttime;
    /**
     * 完成时间
     */
    @ApiModelProperty("完成时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date completetime;
    /**
     * 状态
     */
    @ApiModelProperty("状态")
    private String porcessinsStatus;
    /**
     * 复制草稿，撤销时上一个流程的ID
     */
    @ApiModelProperty("复制草稿，撤销时上一个流程的ID")
    private String historyprocessinsid;
    /**
     * 申请人
     */
    @ApiModelProperty("申请人")
    private String applicantuser;
    /**
     * 申请人名称
     */
    @ApiModelProperty("申请人名称")
    private String applicantusername;
    /**
     * 当前审批人
     */
    @ApiModelProperty("当前审批人")
    private String currentapproversuser;
    /**
     * 当前审批人名称
     */
    @ApiModelProperty("当前审批人名称")
    private String currentactname;
    /**
     * 表单ID
     */
    @ApiModelProperty("表单ID")
    private String formFormId;
//    /**
//     * 状态
//     */
//    @ApiModelProperty("状态")
//    @TableField(exist = false)
//    private String status;
    ////处理的id 用于sql查询使用
    @TableField(exist = false)
    @ApiModelProperty("节点实例ID")
    private String actinsid;
    @TableField(exist = false)
    @ApiModelProperty("进入状态")
    private String viewmodel;
    ///流程名称
    @ApiModelProperty("流程模板名称")
    @TableField(exist = false)
    private String procname;
    @TableField(exist = false)
    @ApiModelProperty("进入流程表单详情时使用")
    private String vactinsid;

}
