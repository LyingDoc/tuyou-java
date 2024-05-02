package com.ruoyi.flow.oa.service.impl;



import com.ruoyi.flow.form.entity.SysUserEntity;
import com.ruoyi.flow.oa.vo.ProcInstHandleEntity;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ApproverParsing {
    private ProcInstHandleEntity ProcInst;
    FlowCommon flowCommon;
    SysOaUserServiceImpl tappUserDao;
    public ApproverParsing(ProcInstHandleEntity procinst , SysOaUserServiceImpl _tappUserDao) {
        ProcInst = procinst;
        flowCommon=new  FlowCommon(procinst);
        tappUserDao=_tappUserDao;
    }

    public String GetNextActApprover() throws Exception {
        String ApprovalType = ProcInst.getNexact().getApprovaltype();
        String ApprovalName = ProcInst.getNexact().getApprovername();
        String approver = ProcInst.getNexact().getApprover();
        switch (ApprovalType) {
            //等于 需要判断是否是角色，人员，部门 获取
            case "1":
                return tappUserDao.GetSystemApprovaler(approver);
            case "2":
                if (!StringUtils.isNotBlank(approver)) {
                    throw new Exception("获取审批人失败!控件未解析正确！");
                }
                String Approvaler=flowCommon.controlVlaue(approver);
                if (!StringUtils.isNotBlank(Approvaler)) {
                    throw new Exception("未找到自定义【"+ApprovalName+"】控件审批人！");
                }
                Approvaler=   Approvaler.replace(';', ',');
                List<String> userList = new ArrayList<>();
                String[] strUser = Approvaler.split(",");
                List<SysUserEntity> tappUserEntityList=  tappUserDao.batchQueryUserList(strUser);
                if(tappUserEntityList==null||tappUserEntityList.size()==0){
                    throw new Exception("控件审批人:"+Approvaler+",对应用户在系统不存在！");
                }
                for (SysUserEntity user:tappUserEntityList) {
                    userList.add(user.getUserName());
                }
                return String.join(",",userList);
        }
      return null;
    }



}
