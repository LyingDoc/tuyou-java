package com.ruoyi.flow.oa.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@ApiModel("流程提交实体")
@Data
public class SaveFlowDataReq {
    @ApiModelProperty("流程设计json")
    private  String flowjson;
}
