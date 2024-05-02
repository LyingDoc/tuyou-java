package com.ruoyi.flow.oa.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.flow.form.entity.SysDeptEntity;
import com.ruoyi.flow.form.entity.SysRoleEntity;
import com.ruoyi.flow.form.entity.SysUserEntity;
import com.ruoyi.flow.form.mapper.SysUserFlowMapper;
import com.ruoyi.flow.form.service.impl.SysDeptFlowServiceImpl;
import com.ruoyi.flow.form.service.impl.SysRoleFlowServiceImpl;
import com.ruoyi.flow.oa.vo.OrganSearchVO;
import com.ruoyi.flow.vo.R;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SysOaUserServiceImpl extends ServiceImpl<SysUserFlowMapper, SysUserEntity> {

    @Autowired
    private SysRoleFlowServiceImpl sysRoleService;
    @Autowired
    private SysDeptFlowServiceImpl sysDeptService;

    public List<SysUserEntity> batchQueryUserList(String[] strUserlist) {
        QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper();
        queryWrapper.in("user_Name" , strUserlist);
        queryWrapper.eq("status" , "0");
        queryWrapper.eq("del_flag" , "0");
        return this.getBaseMapper().selectList(queryWrapper);
    }

    public R<List<OrganSearchVO>> SelectUser(@PathVariable(name = "Search") String Search) {
        R<List<OrganSearchVO>> result = new R<>();
        List<SysDeptEntity> Organlist = new ArrayList<>();
        List<SysUserEntity> employeeList = new ArrayList<>();
        if (!StringUtils.isNotBlank(Search)) {
            QueryWrapper<SysDeptEntity> queryWrapper = new QueryWrapper();
            queryWrapper.eq("parent_id" , "0");
            queryWrapper.eq("status" , "0");
            queryWrapper.eq("del_flag" , "0");
            Organlist = sysDeptService.getBaseMapper().selectList(queryWrapper);
            QueryWrapper<SysUserEntity> userQueryWrapper = new QueryWrapper();
            userQueryWrapper.in("dept_id" , "0");
            userQueryWrapper.eq("status" , "0");
            userQueryWrapper.eq("del_flag" , "0");
            employeeList = this.getBaseMapper().selectList(userQueryWrapper);

        } else {
            QueryWrapper<SysDeptEntity> queryWrapper = new QueryWrapper();
            queryWrapper.eq("ancestors" , Search);
            queryWrapper.eq("status" , "0");
            queryWrapper.eq("del_flag" , "0");
            Organlist = sysDeptService.getBaseMapper().selectList(queryWrapper);
            String[] arrystr = Search.split(",");
            QueryWrapper<SysUserEntity> userqueryWrapper = new QueryWrapper();
            userqueryWrapper.in("dept_id" , arrystr[arrystr.length - 1]);
            userqueryWrapper.eq("status" , "0");
            userqueryWrapper.eq("del_flag" , "0");
            employeeList = this.getBaseMapper().selectList(userqueryWrapper);
        }
        List<OrganSearchVO> searchList = new ArrayList<>();
        for (int i = 0; i < Organlist.size(); i++) {
            SysDeptEntity ee = Organlist.get(i);
            OrganSearchVO entity = new OrganSearchVO();
            entity.setNo(ee.getDeptId().toString());
            entity.setName(ee.getDeptId().toString());
            entity.setRealName(ee.getDeptName());
            entity.setModel(1);
            entity.setParentCodes(ee.getAncestors());
            entity.setDeptPNo(ee.getParentId().toString());
            entity.setDeptPName(ee.getParentName());// = organEntity.FOrganName;
            searchList.add(entity);
        }
        for (int i = 0; i < employeeList.size(); i++) {
            SysUserEntity ee = employeeList.get(i);
            OrganSearchVO entity = new OrganSearchVO();
            entity.setNo(ee.getUserName());
            entity.setName(ee.getUserName());
            entity.setRealName(ee.getNickName());
            SysDeptEntity dept = sysDeptService.getById(ee.getDeptId());
            entity.setDeptPNo(dept.getDeptId().toString());
            entity.setDeptPName(dept.getDeptName());
            entity.setModel(3);
            searchList.add(entity);
        }
        return R.newOk(searchList);

    }

    /**
     * 搜索使用
     *
     * @param
     * @return
     * @throws IOException
     */
    public R<List<OrganSearchVO>> SearchUser(@PathVariable(name = "Search") String Search) {
        QueryWrapper<SysUserEntity> employee = new QueryWrapper();
        employee.like("user_Name" , Search).or().like("nick_Name" , Search);
        employee.eq("status" , "0");
        employee.eq("del_flag" , "0");
        List<SysUserEntity> employeeList = this.getBaseMapper().selectList(employee);
        List<OrganSearchVO> searchList = new ArrayList<>();
        for (int i = 0; i < employeeList.size(); i++) {
            SysUserEntity user = employeeList.get(i);
            OrganSearchVO entity = new OrganSearchVO();
            entity.setNo(user.getUserName());
            entity.setName(user.getUserName());
            entity.setRealName(user.getNickName());
            SysDeptEntity dept = sysDeptService.getById(user.getDeptId());
            entity.setDeptPNo(dept.getDeptId().toString());
            entity.setDeptPName(dept.getDeptName());
            entity.setModel(3);
            searchList.add(entity);
        }
        return R.newOk(searchList);
    }

    public R<List<OrganSearchVO>> SelectOrgan(@PathVariable(name = "Search") String Search) {
        R<List<OrganSearchVO>> result = new R<>();
        List<SysDeptEntity> Organlist = new ArrayList<>();
        if (!StringUtils.isNotBlank(Search)) {
            QueryWrapper<SysDeptEntity> queryWrapper = new QueryWrapper();
            queryWrapper.eq("parent_id" , "0");
            queryWrapper.eq("status" , "0");
            queryWrapper.eq("del_flag" , "0");
            Organlist = sysDeptService.getBaseMapper().selectList(queryWrapper);
        } else {
            QueryWrapper<SysDeptEntity> queryWrapper = new QueryWrapper();
            queryWrapper.eq("ancestors" , Search);
            queryWrapper.eq("status" , "0");
            queryWrapper.eq("del_flag" , "0");
            Organlist = sysDeptService.getBaseMapper().selectList(queryWrapper);
        }
        List<OrganSearchVO> searchList = new ArrayList<>();
        for (int i = 0; i < Organlist.size(); i++) {
            SysDeptEntity ee = Organlist.get(i);
            OrganSearchVO entity = new OrganSearchVO();
            entity.setNo(ee.getDeptId().toString());
            entity.setName(ee.getDeptId().toString());
            entity.setRealName(ee.getDeptName());
            entity.setParentCodes(ee.getAncestors());
            entity.setModel(1);
//            if (ee.getParent() != null) {
            entity.setDeptPNo(ee.getParentId().toString());
            entity.setDeptPName(ee.getParentName());// = organEntity.FOrganName;
//            }
            searchList.add(entity);
        }
        return R.newOk(searchList);

    }

    /**
     * 搜索使用
     *
     * @param
     * @return
     * @throws IOException
     */
    public R<List<OrganSearchVO>> SearchOrgan(@PathVariable(name = "Search") String Search) {
        QueryWrapper<SysDeptEntity> queryWrapper = new QueryWrapper();
        queryWrapper.like("dept_Name" , Search).or().like("dept_Id" , Search);
        queryWrapper.eq("status" , "0");
        queryWrapper.eq("del_flag" , "0");
        List<SysDeptEntity> employeeList = sysDeptService.getBaseMapper().selectList(queryWrapper);
        List<OrganSearchVO> searchList = new ArrayList<>();
        for (int i = 0; i < employeeList.size(); i++) {
            SysDeptEntity user = employeeList.get(i);
            OrganSearchVO entity = new OrganSearchVO();
            entity.setNo(user.getDeptId().toString());
            entity.setName(user.getDeptId().toString());
            entity.setRealName(user.getDeptName());
            entity.setDeptPNo(user.getParentId().toString());
            entity.setDeptPName(user.getParentName());
            ;
            entity.setModel(1);
            searchList.add(entity);
        }
        return R.newOk(searchList);
    }


    public R<List<OrganSearchVO>> SelectRole(@PathVariable(name = "Search") String Search) {
        R<List<OrganSearchVO>> result = new R<>();
        List<SysRole> rolelist = sysRoleService.getBaseMapper().selectRoleAll();
        List<OrganSearchVO> searchList = new ArrayList<>();
        for (int i = 0; i < rolelist.size(); i++) {
            SysRole ee = rolelist.get(i);
            if ("0".equals(ee.getStatus())) {
                OrganSearchVO entity = new OrganSearchVO();
                entity.setNo(ee.getRoleId().toString());
                entity.setName(ee.getRoleName());
                entity.setRealName(ee.getRoleName());
                entity.setModel(2);
                searchList.add(entity);
            }
        }
        return R.newOk(searchList);
    }

    public R<List<OrganSearchVO>> SearchRole(@PathVariable(name = "Search") String Search) {
        QueryWrapper<SysRoleEntity> queryWrapper = new QueryWrapper();
        queryWrapper.like("role_name" , Search).or().like("role_code" , Search);
        queryWrapper.eq("status" , "0");
        List<SysRoleEntity> rolelist = sysRoleService.getBaseMapper().selectList(queryWrapper);

        List<OrganSearchVO> searchList = new ArrayList<>();
        for (int i = 0; i < rolelist.size(); i++) {
            SysRoleEntity ee = rolelist.get(i);
            OrganSearchVO entity = new OrganSearchVO();
            entity.setNo(ee.getRoleId().toString());
            entity.setName(ee.getRoleName());
            entity.setRealName(ee.getRoleName());
            entity.setModel(2);
            searchList.add(entity);
        }
        return R.newOk(searchList);
    }

    public String GetSystemApprovaler(String approver) {
        String approvalerStr = "";
        List<OrganSearchVO> approvalParamList = JSON.parseArray(approver, OrganSearchVO.class);
        for (OrganSearchVO item : approvalParamList) {
            switch (item.getModel()) {
                case 1://机构
                    String deptUserStr = GetDeptCodeUser(item.getNo());
                    if (StringUtils.isNotBlank(deptUserStr)) {
                        approvalerStr = StringUtils.isEmpty(approvalerStr) ? deptUserStr : approvalerStr + "," + deptUserStr;
                    }
                    break;
                case 2://角色
                    String roleUserStr = GetRoleUser(item.getNo());
                    if (StringUtils.isNotBlank(roleUserStr)) {
                        approvalerStr = StringUtils.isEmpty(approvalerStr) ? roleUserStr : approvalerStr + "," + roleUserStr;
                    }
                    break;
                case 3: //人员
                    approvalerStr = StringUtils.isEmpty(approvalerStr) ? item.getName() : approvalerStr + "," + item.getName();
                    break;
            }
        }
        return approvalerStr;
    }

    ///获取角色成员
    public String GetRoleUser(String roleid) {
        List<SysUser> roleUserList = this.queryUserByRoleId(Long.parseLong(roleid));
        String userstr = "";
        for (SysUser useritem : roleUserList) {
            userstr = StringUtils.isEmpty(userstr) ? useritem.getUserName() : userstr + "," + useritem.getUserName();
        }
        return userstr;
    }

    ///根据部门ID 获取所有部门下的用户
    public String GetDeptCodeUser(String deptcode) {
        List<SysUserEntity> tappUserList = this.queryUserByDeptCode(deptcode);
        String userstr = "";
        for (SysUserEntity useritem : tappUserList) {
            userstr = StringUtils.isEmpty(userstr) ? useritem.getUserName() : userstr + "," + useritem.getUserName();
        }
        return userstr;
    }


    public List<SysUserEntity> queryUserByDeptCode(String deptcode) {
        SysDeptEntity sysDeptEntity = sysDeptService.getById(deptcode);
        QueryWrapper<SysDeptEntity> queryWrapper = new QueryWrapper();
        queryWrapper.likeRight("ancestors" , sysDeptEntity.getAncestors());
        queryWrapper.eq("status" , "0");
        queryWrapper.eq("del_flag" , "0");
        List<SysDeptEntity> deptEntityList = sysDeptService.getBaseMapper().selectList(queryWrapper);
        List<Long> deptIdlist = deptEntityList.stream().map(ee -> ee.getDeptId()).collect(Collectors.toList());
        QueryWrapper<SysUserEntity> queryuserWrapper = new QueryWrapper<>();
        queryuserWrapper.in("dept_id" , deptIdlist);
        queryuserWrapper.eq("status" , "0");
        queryuserWrapper.eq("del_flag" , "0");
        return this.getBaseMapper().selectList(queryuserWrapper);
    }

    public List<SysUser> queryUserByRoleId(Long roleId) {
        return this.baseMapper.queryUserByRoleId(roleId);
    }

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    public SysUser selectUserByUserName(String userName) {
        return baseMapper.selectUserByUserName(userName);
    }

    /**
     * 通过用户ID查询用户
     *
     * @param userId 用户ID
     * @return 用户对象信息
     */
    public SysUser selectUserById(Long userId) {
        return baseMapper.selectUserById(userId);
    }
}
