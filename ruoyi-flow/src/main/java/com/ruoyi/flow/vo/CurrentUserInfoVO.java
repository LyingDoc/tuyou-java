package com.ruoyi.flow.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("当前用户信息")
public class CurrentUserInfoVO {
    public String userName;
    public String userCode;
    public String lastLoginIp;
    public String loginCode;
    public String status;
    public String id;
    private String email;
    private String phonenumber;
    private String sex;
    private String avatar;
//    private  String isAdmin;
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

