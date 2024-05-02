package com.ruoyi.flow.form.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MyQuestionReq {
    private String ancestorscode;
    private String userName;
    private List<Long> roleIds;
    /** 表单名称 */
    private String formName;
    /** 表单编码 */
    private String formCode;
    /** 日期时间 */
    private Date datetime;
    @ApiModelProperty("分页码")
    private  Integer page;
    @ApiModelProperty("每页行数")
    private  Integer rows;
}
