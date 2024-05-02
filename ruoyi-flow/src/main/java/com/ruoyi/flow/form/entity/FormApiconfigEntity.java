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
@TableName("form_apiconfig")
public class FormApiconfigEntity extends Model<FormApiconfigEntity> {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
@ApiModelProperty("主键")
    @TableId(value = "form_apiconfig_id")
    private String formApiconfigId;
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
     * 方法名称说明
     */
@ApiModelProperty("方法名称说明")
    private String methodName;
    /**
     * 备注信息
     */
@ApiModelProperty("备注信息")
    private String remarks;
    /**
     * 方法类型(1列表查询，2查询结果，3.保存操作类,4.导入,5.对象)
     */
@ApiModelProperty("方法类型(1列表查询，2查询结果，3.保存操作类,4.导入,5.对象)")
    private Integer methodType;
    /**
     * 方法code
     */
@ApiModelProperty("方法code")
    private String methodCode;
    /**
     * 参数必填项控制
     */
@ApiModelProperty("参数必填项控制")
    private String paramConfig;
    /**
     * 返回列表list 返回字段说明
     */
@ApiModelProperty("返回列表list 返回字段说明")
    private String headList;
    /**
     * 实体名称
     */
@ApiModelProperty("实体名称")
    private String pageId;
    /**
     * 版本
     */
@ApiModelProperty("版本")
    private Integer fVersions;
    /**
     * 执行sqlId
     */
@ApiModelProperty("执行sqlId")
    private String executeSqlId;
    /**
     * 调用方法保证方法唯一
     */
@ApiModelProperty("调用方法保证方法唯一")
    private String callMethodCode;
    /**
     * 显示列
     */
@ApiModelProperty("显示列")
    private String showCelNames;
    /**
     * 用于列表显示条件使用
     */
    @ApiModelProperty("用于列表显示条件使用")
    private String showCtrlWhere;
    /**
     * 链接数据类型(等于system，请求默认数据库链接)
     */
    @ApiModelProperty("链接数据类型(等于system，请求默认数据库链接)")
    private String dbId;
    @ApiModelProperty("是否系统接口")
    private  String  isSystem;
    @ApiModelProperty("xls导入模板地址")
    private  String  xlsTemplateUrl;
    @ApiModelProperty("xls导入开始行")
    private  String  xlsTemplateRowNum;
    @ApiModelProperty("参数格式")
    private  String  jsonparam;
}
