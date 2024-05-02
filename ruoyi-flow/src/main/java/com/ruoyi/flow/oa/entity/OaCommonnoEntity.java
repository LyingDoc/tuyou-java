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
@TableName("oa_commonno")
public class OaCommonnoEntity extends Model<OaCommonnoEntity> {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @TableId(value = "oa_commonno_id")
    private String oaCommonnoId;
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
     * 模块ID
     */
    @ApiModelProperty("模块ID")
    private String moduleid;
    /**
     * 模块名称
     */
    @ApiModelProperty("模块名称")
    private String modulename;
    /**
     * 生成序号ID
     */
    @ApiModelProperty("生成序号ID")
    private Integer orderid;
    /**
     * 排序时间
     */
    @ApiModelProperty("排序时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderdate;
    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remarks;
}
