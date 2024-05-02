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
 *
 * </p>
 *
 * @author 刘亚平
 * @since 2023-01-15
 */
@Data
@ApiModel("")
@TableName("form_form")
public class FormFormEntity extends Model<FormFormEntity> {
    private static final long serialVersionUID = 1L;
    @TableId(value = "form_form_id")
    private String formFormId;
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
     * 表单名称
     */
    @ApiModelProperty("表单名称")
    private String fromname;
    /**
     * 表单code
     */
    @ApiModelProperty("表单code")
    private String fromcode;
    /**
     * 表单vue源码
     */
    @ApiModelProperty("表单vue源码")
    private String fromcontent;
    /**
     * 表单设计JSON
     */
    @ApiModelProperty("表单设计JSON")
    private String fromdesignjson;
    /**
     * 默认值json
     */
    @ApiModelProperty("默认值json")
    private String defaultdata;
    /**
     * 页面控件JSon
     */
    @ApiModelProperty("页面控件JSon")
    private String pagecontroljson;
    /**
     * 备注信息
     */
    @ApiModelProperty("备注信息")
    private String fremarks;
    /**
     * 控件标题
     */
    @ApiModelProperty("控件标题")
    private String controltitle;
    /**
     * 关联主表单ID
     */
    @ApiModelProperty("关联主表单ID")
    private String relevanceFormId;
    /**
     * 1 列表表单，2编辑表单 3流程表单
     */
    @ApiModelProperty("1 列表表单，2编辑表单 3流程表单")
    private String fromtype;
    @ApiModelProperty("权限控制")
    private String nodepower;
    /**
     * 保存表名
     */
    @ApiModelProperty("保存表名")
    private String fromTableName;
    /**
     * 列表查询条件
     */
    @ApiModelProperty("列表查询条件")
    private String queryJson;
    /**
     * 弹出宽度设置
     */
    @ApiModelProperty("弹出宽度设置")
    private String dialogwidth;
    /**
     * 1需要权限才能访问
     */
    @ApiModelProperty("1需要权限才能访问")
    private String isAuthority;
    /**
     * 1生成表结构 2 不生成表结构
     */
    @ApiModelProperty(" 1生成表结构 2 不生成表结构")
    private Long isBuildapi;
    /**
     * 表单形象图
     */
    @ApiModelProperty("表单形象图")
    private String formimgurl;

    /**
     * 报表的部门id
     */
    @ApiModelProperty("报表部门id")
    private Long deptId;
}
