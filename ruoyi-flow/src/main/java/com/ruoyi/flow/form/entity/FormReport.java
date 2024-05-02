package com.ruoyi.flow.form.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 报设计对象 form_report
 * 
 * @author Tellsea
 * @date 2023-07-15
 */
@Data
@ApiModel("报设计")
@TableName("form_report")
public class FormReport extends Model<FormReport>
{
    private static final long serialVersionUID = 1L;

    /** 报表设计器ID */
    @ApiModelProperty("报表设计器ID")
    @TableId
    private String formReportId;

    /** 创建时间 */
    @ApiModelProperty("创建时间")
      @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createDate;

    /** 创建人 */
    @ApiModelProperty("创建人")
      @TableField(fill = FieldFill.INSERT)
    private String createBy;

    /** 修改时间 */
    @ApiModelProperty("修改时间")
        @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updateDate;

    /** 修改人 */
    @ApiModelProperty("修改人")
        @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    /** Apicode 查询接口 */
    @ApiModelProperty("Apicode 查询接口")
    @Excel(name = "Apicode 查询接口")
    private String apicode;

    /** 报表设计字段 */
    @ApiModelProperty("报表设计字段")
    @Excel(name = "报表设计字段")
    private String reportJson;

    /** 报表名称 */
    @ApiModelProperty("报表名称")
    @Excel(name = "报表名称")
    private String reportName;

    /** 报表说明 */
    @ApiModelProperty("报表说明")
    @Excel(name = "报表说明")
    private String reportDescription;

    /** 报表编码 */
    @ApiModelProperty("报表编码")
    @Excel(name = "报表编码")
    private String reportCode;

    /** Apicode 查询接口 */
    @ApiModelProperty("apiName 查询接口名称")
    @TableField(exist = false)
    private String apiname;

}
