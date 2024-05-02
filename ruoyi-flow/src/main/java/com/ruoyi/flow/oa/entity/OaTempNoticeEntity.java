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
 * 通知模板表
 * </p>
 *
 * @author 刘亚平
 * @since 2023-01-15
 */
@Data
@ApiModel("通知模板表")
@TableName("oa_temp_notice")
public class OaTempNoticeEntity extends Model<OaTempNoticeEntity> {
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
    @TableId(value = "temp_id")
    private String tempId;
    /**
     * 邮件模板
     */
@ApiModelProperty("邮件模板")
    private String tempName;
    /**
     * 1 待办通知 2结束通知 3抄送通知 4沟通通知 5回复通知，6驳回通知，7转办通知 8催办提醒通知 9过期通知 
     */
@ApiModelProperty("1 待办通知 2结束通知 3抄送通知 4沟通通知 5回复通知，6驳回通知，7转办通知 8催办提醒通知 9过期通知 ")
    private String tempType;
    private String tempContent;
    /**
     * 模板状态
     */
@ApiModelProperty("模板状态")
    private String tempStatus;
}
