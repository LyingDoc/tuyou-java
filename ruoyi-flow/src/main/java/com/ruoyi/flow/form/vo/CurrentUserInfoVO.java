package com.ruoyi.flow.form.vo;

import lombok.Data;

import java.util.Date;

@Data
public class CurrentUserInfoVO {
    public String userName;
    public  String userCode;
    public String lastLoginIp;
    public String loginCode;
    public String status;
    public  String id;
    public Date lastLoginDate;
    /**
     * 所属机构编码
     */
    public String officeCode;
    /**
     * 所属机构名称
     */
    public String officeName;

    /**
     * 所属机构编码
     */
    public String deptCode;
    /**
     * 所属机构名称
     */
    public String deptName;
}
