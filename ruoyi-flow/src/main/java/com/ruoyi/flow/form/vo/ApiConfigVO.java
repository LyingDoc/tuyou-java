package com.ruoyi.flow.form.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("api信息获取")
public class ApiConfigVO {
    @ApiModelProperty("apiID")
    private String formApiconfigId;
    @ApiModelProperty("方法中文名称")
    private String methodName;
    @ApiModelProperty("方法说明")
    private String remarks;
    @ApiModelProperty("方法类型(1列表方法2查询方法，3保存方法)")
    private String methodType;
    @ApiModelProperty("方法类型名称")
    private String methodTypeName;
    @ApiModelProperty("方法名称")
    private String methodCode;
    @ApiModelProperty("参数配置JSON")
    private String paramConfig;
    @ApiModelProperty("方法类型为1 设置前端列表头部信息的")
    private String headList;
    @ApiModelProperty("包Id")
    private Long pageId;
    @ApiModelProperty("执行sql")
    private String sqlContent;
    @ApiModelProperty("调用Api地址")
    private String apiUrl;
    @ApiModelProperty("调用方法code")
    private String callMethodCode;
    @ApiModelProperty("设置显示的列")
    private String showCelNames;
    @ApiModelProperty("设置显示的查询列")
    private  String showCtrlWhere;
    @ApiModelProperty("链接DBId")
    private  String dbId;
    @ApiModelProperty("是否系统接口")
    private  String  isSystem;
    @ApiModelProperty("xls导入开始行")
    private  String xlsTemplateRowNum;
}
