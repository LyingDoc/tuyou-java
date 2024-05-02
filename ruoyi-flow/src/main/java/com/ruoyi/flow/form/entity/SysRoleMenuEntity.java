package com.ruoyi.flow.form.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 角色和菜单关联对象 sys_role_menu
 * 
 * @author ruoyi
 * @date 2023-04-02
 */
@TableName("sys_role_menu")
public class SysRoleMenuEntity extends Model<SysRoleMenuEntity>
{
    private static final long serialVersionUID = 1L;

    /** 角色ID */
    @TableId
    private Long roleId;

    /** 菜单ID */
    private Long menuId;

    public void setRoleId(Long roleId) 
    {
        this.roleId = roleId;
    }

    public Long getRoleId() 
    {
        return roleId;
    }
    public void setMenuId(Long menuId) 
    {
        this.menuId = menuId;
    }

    public Long getMenuId() 
    {
        return menuId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("roleId", getRoleId())
            .append("menuId", getMenuId())
            .toString();
    }
}
