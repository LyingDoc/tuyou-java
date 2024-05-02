package com.ruoyi.flow.form.vo;

import lombok.Data;

import java.util.Date;

@Data
public class QueryFromVO {

    private String formFormId;
    private Date createDate;
    private String createBy;
    private Date updateDate;
    private String updateBy;
    private String fromName;
    private String fromCode;
    private String fremarks;
    private String fromTableName;
    private Boolean isBuildapi;
    private String fromdesignjson;
}
