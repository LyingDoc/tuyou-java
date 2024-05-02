package com.ruoyi.flow.oa.vo;


import lombok.Data;

import java.io.Serializable;

@Data
public class RoleUserVO implements Serializable {
    private static final long serialVersionUID = -29819230068719315L;
    private String name;
    private String typename;
    private String code;
    private String fpowertype;
    private Long froleid;
    private Long id;

    public Long fid;
}
