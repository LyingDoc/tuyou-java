package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import com.ruoyi.common.annotation.Excel;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 微信用户表对象 sys_weixin_user
 *
 * @author Tellsea
 * @date 2023-07-20
 */
@Data
@ApiModel("微信用户表")
@TableName("sys_weixin_user")
public class SysWeixinUser extends Model<SysWeixinUser>
{
    private static final long serialVersionUID = 1L;

    /**  */
    @ApiModelProperty("")
    @TableId
    private String wexinId;

    /** 创建者 */
    @ApiModelProperty("创建者")
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    /** 创建时间 */
    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /** 更新者 */
    @ApiModelProperty("更新者")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    /** 更新时间 */
    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /** 微信用户Opent_id */
    @ApiModelProperty("微信用户Opent_id")
    @Excel(name = "微信用户Opent_id")
    private String openId;

    /** 用户ID */
    @ApiModelProperty("用户ID")
    @Excel(name = "用户ID")
    private String userId;


}