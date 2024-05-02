package com.ruoyi.flow.form.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import java.time.LocalDateTime;
/**
 * <p>
 * 部门表
 * </p>
 *
 * @author 刘亚平
 * @since 2023-01-29
 */
@Data
@ApiModel("部门表")
@TableName("sys_dept")
public class SysDeptEntity extends Model<SysDeptEntity> {
    private static final long serialVersionUID = 1L;
    /**
     * 部门id
     */
@ApiModelProperty("部门id")
    @TableId(value = "dept_id")
    private Long deptId;
    /**
     * 父部门id
     */
@ApiModelProperty("父部门id")
    private Long parentId;
    /**
     * 祖级列表
     */
@ApiModelProperty("祖级列表")
    private String ancestors;
    /**
     * 部门名称
     */
@ApiModelProperty("部门名称")
    private String deptName;
    /**
     * 显示顺序
     */
@ApiModelProperty("显示顺序")
    private Integer orderNum;
    /**
     * 负责人
     */
@ApiModelProperty("负责人")
    private String leader;
    /**
     * 联系电话
     */
@ApiModelProperty("联系电话")
    private String phone;
    /**
     * 邮箱
     */
@ApiModelProperty("邮箱")
    private String email;
    /**
     * 部门状态（0正常 1停用）
     */
@ApiModelProperty("部门状态（0正常 1停用）")
    private String status;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
@ApiModelProperty("删除标志（0代表存在 2代表删除）")
    private String delFlag;
    /**
     * 创建者
     */
@ApiModelProperty("创建者")
    private String createBy;
    /**
     * 创建时间
     */
@ApiModelProperty("创建时间")
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 更新者
     */
@ApiModelProperty("更新者")
    private String updateBy;
    /**
     * 更新时间
     */
@ApiModelProperty("更新时间")
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    /**
     * 父级部门名称
     */
    @ApiModelProperty("父级部门名称")
    private String parentName;
}
