package com.ruoyi.flow.form.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QueryFromReq {
    private String  fromname;
    private String fromtype;
    private String fromcode;
    private String fremarks;
    private Integer isBuildapi;
    @ApiModelProperty("分页码")
    private  Integer page;
    @ApiModelProperty("每页行数")
    private  Integer rows;
}
