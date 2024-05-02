package com.ruoyi.flow.form.service;

import com.ruoyi.flow.form.entity.SysRoleDeptEntity;

import java.util.List;

public interface ISysRoleFlowService {
    List<SysRoleDeptEntity> getDeptIDByRoleId(Long roleId);
}
