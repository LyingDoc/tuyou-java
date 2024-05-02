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
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 新闻对象 sys_news
 * 
 * @author Tellsea
 * @date 2023-07-09
 */
@Data
@ApiModel("新闻")
@TableName("sys_news")
public class SysNews extends Model<SysNews>
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("主键")
    @TableId
    private String newsId;

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

    /** 新闻标题 */
    @ApiModelProperty("新闻标题")
    @Excel(name = "新闻标题")
    private String newsTitle;

    /** 新闻来源 */
    @ApiModelProperty("新闻来源")
    @Excel(name = "新闻来源")
    private String newsSource;

    /** 新闻内容 */
    @ApiModelProperty("新闻内容")
    @Excel(name = "新闻内容")
    private String newsContent;

    /** 新闻状态 */
    @ApiModelProperty("新闻状态")
    @Excel(name = "新闻状态")
    private String newsStatus;

    /** 发布时间 */
    @ApiModelProperty("发布时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date publishTime;

    /** 新闻类型 */
    @ApiModelProperty("新闻类型")
    @Excel(name = "新闻类型")
    private String newsType;

    /** 新闻主题 */
    @ApiModelProperty("新闻主题")
    @Excel(name = "新闻主题")
    private String themeImg;


}
