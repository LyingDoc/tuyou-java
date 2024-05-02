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
 * 路由信息表
 * </p>
 *
 * @author 刘亚平
 * @since 2023-01-15
 */
@Data
@ApiModel("路由信息表")
@TableName("oa_actrouting")
public class OaActroutingEntity extends Model<OaActroutingEntity> {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
@ApiModelProperty("主键")
    @TableId(value = "oa_actrouting_id")
    private String oaActroutingId;
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
     * 节点ID
     */
@ApiModelProperty("节点ID")
    private String oaActId;
    /**
     * 下一节点ID
     */
@ApiModelProperty("下一节点ID")
    private String runActId;
    /**
     * 下一节点名称
     */
@ApiModelProperty("下一节点名称")
    private String runactname;
    /**
     * 输出路由json
     */
@ApiModelProperty("输出路由json")
    private String outputrules;
    /**
     * 逻辑json
     */
@ApiModelProperty("逻辑json")
    private String routingjson;
    /**
     * 逻辑json
     */
@ApiModelProperty("逻辑json")
    private String expressionjson;
    /**
     * 回调处理方式 1、ApiUrl回调 2.sql语句回调
     */
@ApiModelProperty("回调处理方式 1、ApiUrl回调 2.sql语句回调")
    private String handleType;
    /**
     * 回调url地址
     */
@ApiModelProperty("回调url地址")
    private String apiUrl;
    /**
     * 请求方式 post  请求 get 请求
     */
@ApiModelProperty("请求方式 post  请求 get 请求")
    private String ajaxType;
    /**
     * 回调执行sql内容
     */
@ApiModelProperty("回调执行sql内容")
    private String sqlcontent;
}
