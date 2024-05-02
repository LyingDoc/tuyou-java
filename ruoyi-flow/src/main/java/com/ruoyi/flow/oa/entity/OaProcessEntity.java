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
@TableName("oa_process")
public class OaProcessEntity extends Model<OaProcessEntity> {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
@ApiModelProperty("主键")
    @TableId(value = "oa_process_id")
    private String oaProcessId;
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
     * 流程名称
     */
@ApiModelProperty("流程名称")
    private String processname;
    /**
     * 流程类型编码
     */
@ApiModelProperty("流程类型编码")
    private String processtypecode;
    /**
     * 流程编码
     */
@ApiModelProperty("流程编码")
    private String processcode;
    /**
     * 是否催办提醒
     */
@ApiModelProperty("是否催办提醒")
    private Boolean isreminder;
    /**
     * 催办消息提醒方式
     */
@ApiModelProperty("催办消息提醒方式")
    private String fremindermode;
    /**
     * 发起人类型
     */
@ApiModelProperty("发起人类型")
    private String starttype;
    /**
     * 发起人名称
     */
@ApiModelProperty("发起人名称")
    private String startname;
    /**
     * 发起人ID
     */
@ApiModelProperty("发起人ID")
    private String startid;
    /**
     * 结束通知申请人
     */
@ApiModelProperty("结束通知申请人")
    private Boolean isendreminder;
    /**
     * 是否可复制草稿
     */
@ApiModelProperty("是否可复制草稿")
    private Boolean iscopy;
    /**
     * 流程说明
     */
@ApiModelProperty("流程说明")
    private String description;
    /**
     * 版本号
     */
@ApiModelProperty("版本号")
    private Long fVersion;
    /**
     * 表单Id
     */
@ApiModelProperty("表单Id")
    private String formFormId;
    /**
     * 流程状态 1 草稿 2 已启动 3停止 4历史
     */
@ApiModelProperty("流程状态 1 草稿 2 已启动 3停止 4历史 5 已删除")
    private Integer processState;
    /**
     * 流水号模块
     */
@ApiModelProperty("流水号模块")
    private String businessmoudle;
    /**
     * 流水号模块ID
     */
@ApiModelProperty("流水号模块ID")
    private String businessmoduleid;
    /**
     * 流程显示图标
     */
@ApiModelProperty("流程显示图标")
    private String flowicon;
    /**
     * 流程BId
     */
@ApiModelProperty("流程BId")
    private String processBid;
    /**
     * 默认回调地址
     */
    @ApiModelProperty("默认回调地址")
    private String callbackApprovalUrl;
    @TableField(exist=false)
    @ApiModelProperty("流程类型名称")
    private String  processtypename;
}
