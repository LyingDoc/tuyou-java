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
@TableName("oa_processtype")
public class OaProcesstypeEntity extends Model<OaProcesstypeEntity> {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
@ApiModelProperty("主键")
    @TableId(value = "oa_processtype_id")
    private Long oaProcesstypeId;
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
     * 分类名称
     */
@ApiModelProperty("分类名称")
    private String processtypename;
    /**
     * 流程类型编码
     */
@ApiModelProperty("流程类型编码")
    private String processtypecode;
    /**
     * 备注
     */
@ApiModelProperty("备注")
    private String remarks;
    /**
     * 显示图标
     */
@ApiModelProperty("显示图标")
    private String iconclass;
}
