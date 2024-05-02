package com.ruoyi.flow.form.req;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("保存Api接口参数实体")
public class SaveApiConfigReq {
    @NotNull(message = "Api名称不能为空")
    @ApiModelProperty("方法中文名称")
    private String methodName;
    @ApiModelProperty("方法说明")
    private String remarks;
    @NotNull(message = "Api类型不能为空")
    @ApiModelProperty("方法类型(1列表方法2查询方法，3保存方法)")
    private Integer methodType;
    @NotNull(message = "ApiCode不能为空")
    @ApiModelProperty("方法名称")
    private String methodCode;
    @ApiModelProperty("参数配置JSON")
    private String paramConfig;
    @ApiModelProperty("方法类型为1 设置前端列表头部信息的")
    private String headList;
    @NotNull(message = "包Id不能为空")
    @ApiModelProperty("包Id")
    private String pageId;
    @NotNull(message = "执行sql不能为空")
    @ApiModelProperty("执行sql")
    private String sqlContent;
    @NotNull(message = "数据源不能为空")
    @ApiModelProperty("数据源")
    private String dbId;
    @ApiModelProperty("是否系统接口")
    private String isSystem;
}
