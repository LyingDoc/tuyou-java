package com.ruoyi.flow.oa.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@ApiModel("流程日志列表查询类")
@Data
public class QueryProcessInsLogReq {
    @ApiModelProperty("流程实例编号")
    private  String procinsno;
    @ApiModelProperty("流程实例名称")
    private  String procinsname;
    @ApiModelProperty("创建时间")
    private List<Date> creationdate;
    @ApiModelProperty("分页码")
    private  int page;
    @ApiModelProperty("每页行数")
    private  int rows;
}
