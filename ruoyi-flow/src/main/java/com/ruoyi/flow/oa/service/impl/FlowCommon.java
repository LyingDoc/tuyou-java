package com.ruoyi.flow.oa.service.impl;

import com.alibaba.fastjson2.JSON;
import com.ruoyi.flow.oa.vo.ProcInstHandleEntity;
import net.sf.json.JSONObject;


public class FlowCommon {
    private ProcInstHandleEntity ProcInst;
    public FlowCommon(ProcInstHandleEntity procinst) {
        ProcInst = procinst;
    }
    String controlVlaue(String approvalname){
        switch (approvalname)
        {
            case "@主题":return ProcInst.getFromdata().getString("Header_formSubTitle");
            case "@编号":return ProcInst.getFromdata().getString("Header_formNumber");
            case "@创建人":return ProcInst.getFromdata().getString("Header_CreateUserName");
            case "@创建部门":return ProcInst.getFromdata().getString("Header_CreateDepName");
            case "@流程状态":return ProcInst.getFromdata().getString("Header_Document_Status");
            case "@申请人":return ProcInst.getFromdata().getString("Header_applicantUserShow");
            case "@申请人部门":return ProcInst.getFromdata().getString("Header_applicantUser_DepName");
            case "@提交时间":return ProcInst.getFromdata().getString("Header_CreateDate");
            case "@上一节点名称":return ProcInst.getFromdata().getString("hidPrevActName");
            case "@审批动作":return ProcInst.getActionname();
            case "@Action": return ProcInst.getActionname();
            default:
                if(ProcInst.getFromdata().containsKey(approvalname)) {
                    JSONObject jsonvalue = ProcInst.getFromdata().optJSONObject(approvalname);
                    System.out.println(approvalname);
                    if(jsonvalue!=null){
                        if(jsonvalue.containsKey("value")) {
                            System.out.println("解析下一审批人为:"+jsonvalue.optString("value"));
                            String jsonvaluestr= jsonvalue.optString("value");
                            if(jsonvaluestr.contains("[")&&jsonvaluestr.contains("]")){
                                return String.join(",", JSON.parseArray(jsonvaluestr, String.class));
                            }
                        }
                        return null;
                    }else{
                            return ProcInst.getFromdata().optString(approvalname);
                    }
                }
                return null;
        }
    }
}
