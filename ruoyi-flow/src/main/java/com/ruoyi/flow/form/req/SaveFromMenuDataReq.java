package com.ruoyi.flow.form.req;

import com.ruoyi.flow.form.entity.SysMenuEntity;
import com.ruoyi.flow.form.vo.RoleVO;
import lombok.Data;

import java.util.List;

@Data
public class SaveFromMenuDataReq {
    public Long  parentId;
    public String  menuName;
    public String   menuLinkType;
    public String role;
    public String roleName;
    public String    icon;
    public String    menuurl;
    public String    fromId;
    public List<String> butPowerList;
}
