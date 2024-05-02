package com.ruoyi.flow.form.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("布局模块信息")
@Data
public class LayoutModelReq {
    private String type;
    private  String name;
    private  String model;

    private  LayoutModelOptionsReq options;
}
