package com.ruoyi.flow.form.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class ControlOptionsReq {
    /**
     * 控件标题
     */
    @ApiModelProperty("控件标题")
    public String title;
    @ApiModelProperty("数据库保存字段")
    public String filed;
    @ApiModelProperty("数据保存长度")
    public  Integer dataLength=50;
    @ApiModelProperty("控件ID")
    public String controlId;
    @ApiModelProperty("控件类型")
    public String modeltype;
    @ApiModelProperty("如果是列表控件 对应显示列的集合")
    private List<FormListViewControlReq> columns;
    @ApiModelProperty("下拉列表是否多选")
    public Boolean isMulty;
    @ApiModelProperty("下拉列表，多选，单选的选项")
    public  List<String> options;
    @ApiModelProperty("日期格式")
    public String format;
    @ApiModelProperty("数据源ID")
    public String sourceApi;
    @ApiModelProperty("是否远程Api接口请求")
    public String isRemote;
    @ApiModelProperty("是否保存入库创建字段")
    public Boolean issavedata=true;
    @ApiModelProperty("绑定Text")
    public  String labelText;
    @ApiModelProperty("绑定Value")
    public  String labelValue;
    @ApiModelProperty("数字控件保留几位小数")
    public int precision;
}
