package com.ruoyi.flow.form.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QueryApiListReq {
    private String pagename;
    private String description;
    private String methodname;
    private String methodcode;
    private String methodtype;
    @ApiModelProperty("分页码")
    private  Integer page;
    @ApiModelProperty("每页行数")
    private  Integer rows;
}
