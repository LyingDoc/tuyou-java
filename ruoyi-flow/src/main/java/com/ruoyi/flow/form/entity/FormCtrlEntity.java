package com.ruoyi.flow.form.entity;


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
 * 表单控件
 * </p>
 *
 * @author 刘亚平
 * @since 2023-01-15
 */
@Data
@ApiModel("表单控件")
@TableName("form_ctrl")
public class FormCtrlEntity extends Model<FormCtrlEntity> {
    private static final long serialVersionUID = 1L;
    @TableId(value = "ctrl_id")
    private String ctrlId;
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
     * 控件名称
     */
@ApiModelProperty("控件名称")
    private String ctrlTitle;
    /**
     * 控件内容
     */
@ApiModelProperty("控件内容")
    private String ctrlContent;
    /**
     * 控件截图
     */
@ApiModelProperty("控件截图")
    private String ctrlImg;
    /**
     * 控件说明
     */
@ApiModelProperty("控件说明")
    private String ctrlRemarks;
    /**
     * 控件类型
     */
@ApiModelProperty("控件类型")
    private String ctrlType;
}
