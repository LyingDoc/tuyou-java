package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.ruoyi.common.annotation.Excel;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 【请填写功能名称】对象 sys_reading_like
 * 
 * @author Tellsea
 * @date 2023-07-09
 */
@Data
@ApiModel("【请填写功能名称】")
@TableName("sys_reading_like")
public class SysReadingLike extends Model<SysReadingLike>
{
    private static final long serialVersionUID = 1L;

    /** ${column.columnComment} */
    @ApiModelProperty("${column.columnComment}")
    @TableId
    private String readingLikeId;

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

    /** 已阅类别（1.通知公告，2.公司新闻 3.我的消息 4.公告点赞 5.公司新闻点赞） */
    @ApiModelProperty("已阅类别（1.通知公告，2.公司新闻 3.我的消息 4.公告点赞 5.公司新闻点赞）")
    @Excel(name = "已阅类别", readConverterExp = "1=.通知公告，2.公司新闻,3=.我的消息,4=.公告点赞,5=.公司新闻点赞")
    private String readingLikeType;

    /** 阅读、点赞人 */
    @ApiModelProperty("阅读、点赞人")
    @Excel(name = "阅读、点赞人")
    private String readingLikeUserId;

    /** 阅读关联id */
    @ApiModelProperty("阅读关联id")
    @Excel(name = "阅读关联id")
    private String readingLinkId;


}
