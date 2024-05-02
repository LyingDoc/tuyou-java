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
 * 通知发送表
 * </p>
 *
 * @author 刘亚平
 * @since 2023-01-15
 */
@Data
@ApiModel("通知发送表")
@TableName("oa_send_notice")
public class OaSendNoticeEntity extends Model<OaSendNoticeEntity> {
    private static final long serialVersionUID = 1L;
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
     * 主键
     */
@ApiModelProperty("主键")
    @TableId(value = "notice_id")
    private Long noticeId;
    /**
     * 通知模板id
     */
@ApiModelProperty("通知模板id")
    private String tempNoticeId;
    /**
     * 发送通知人员ID
     */
@ApiModelProperty("发送通知人员ID")
    private String sendUserId;
    /**
     * 发送通知人员名称
     */
@ApiModelProperty("发送通知人员名称")
    private String sendUserName;
    /**
     * 是否已阅
     */
@ApiModelProperty("是否已阅")
    private Integer isRead;
    /**
     * 阅读时间
     */
@ApiModelProperty("阅读时间")
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date readTime;
    /**
     * 发送通知内容
     */
@ApiModelProperty("发送通知内容")
    private String sendContent;
    /**
     * 流程ID
     */
@ApiModelProperty("流程ID")
    private String processinsId;
    /**
     * 消息主题
     */
@ApiModelProperty("消息主题")
    private String processinsName;
}
