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
 * 流程实例表
 * </p>
 *
 * @author 刘亚平
 * @since 2023-01-15
 */
@Data
@ApiModel("流程实例表")
@TableName("oa_porcessins_log")
public class OaPorcessinsLogEntity extends Model<OaPorcessinsLogEntity> {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
@ApiModelProperty("主键")
    @TableId(value = "oa_porcessins_log_id")
    private Long oaPorcessinsLogId;
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
     * 流程模板Id
     */
@ApiModelProperty("流程模板Id")
    private String oaProcessId;
    /**
     * 表单Id
     */
@ApiModelProperty("表单Id")
    private String oaFromdataId;
    /**
     * 流程名称
     */
@ApiModelProperty("流程名称")
    private String procinsname;
    /**
     * 流程编码
     */
@ApiModelProperty("流程编码")
    private String procinsno;
    /**
     * 节点实例表
     */
@ApiModelProperty("节点实例表")
    private String actins;
    /**
     * 运行日志
     */
@ApiModelProperty("运行日志")
    private String logmsg;
    /**
     * 提交流程Data
     */
@ApiModelProperty("提交流程Data")
    private String fromdata;
    /**
     * 最后修改人Id
     */
@ApiModelProperty("最后修改人Id")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String flastupdateid;
    /**
     * 创建人id
     */
@ApiModelProperty("创建人id")
    private String createid;
    /**
     * 节点名称
     */
@ApiModelProperty("节点名称")
    private String actinsname;
    /**
     * 动作名称
     */
@ApiModelProperty("动作名称")
    private String actionname;
    /**
     * 状态 0 未处理 1已处理 8回写失败 9回调报错
     */
@ApiModelProperty("状态 0 未处理 1已处理 8回写失败 9回调报错")
    private String porcessinsLogStatus;
    /**
     * 流程实例id
     */
@ApiModelProperty("流程实例id")
    private String oaPorcessinsId;
    /**
     * 节点ID
     */
@ApiModelProperty("节点ID")
    private String oaActId;
    /**
     * 当时提交的数据
     */
@ApiModelProperty("当时提交的数据")
    private String handleProcessParam;
}
