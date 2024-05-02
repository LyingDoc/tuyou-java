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
 * 用户信息表
 * </p>
 *
 * @author 刘亚平
 * @since 2023-01-29
 */
@Data
@ApiModel("用户信息表")
@TableName("sys_user")
public class SysUserEntity extends Model<SysUserEntity> {
    private static final long serialVersionUID = 1L;
    /**
     * 用户ID
     */
@ApiModelProperty("用户ID")
    @TableId(value = "user_id")
    private Long userId;
    /**
     * 部门ID
     */
@ApiModelProperty("部门ID")
    private Long deptId;
    /**
     * 用户账号
     */
@ApiModelProperty("用户账号")
    private String userName;
    /**
     * 用户昵称
     */
@ApiModelProperty("用户昵称")
    private String nickName;
    /**
     * 用户类型（00系统用户）
     */
@ApiModelProperty("用户类型（00系统用户）")
    private String userType;
    /**
     * 用户邮箱
     */
@ApiModelProperty("用户邮箱")
    private String email;
    /**
     * 手机号码
     */
@ApiModelProperty("手机号码")
    private String phonenumber;
    /**
     * 用户性别（0男 1女 2未知）
     */
@ApiModelProperty("用户性别（0男 1女 2未知）")
    private String sex;
    /**
     * 头像地址
     */
@ApiModelProperty("头像地址")
    private String avatar;
    /**
     * 密码
     */
@ApiModelProperty("密码")
    private String password;
    /**
     * 帐号状态（0正常 1停用）
     */
@ApiModelProperty("帐号状态（0正常 1停用）")
    private String status;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
@ApiModelProperty("删除标志（0代表存在 2代表删除）")
    private String delFlag;
    /**
     * 最后登录IP
     */
@ApiModelProperty("最后登录IP")
    private String loginIp;
    /**
     * 最后登录时间
     */
@ApiModelProperty("最后登录时间")
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date loginDate;
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
     * 备注
     */
@ApiModelProperty("备注")
    private String remark;
}
