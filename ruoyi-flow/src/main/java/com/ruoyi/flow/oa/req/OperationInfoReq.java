package com.ruoyi.flow.oa.req;


import com.ruoyi.flow.oa.vo.CopyUserEntity;
import lombok.Data;

import java.util.List;

@Data
public class OperationInfoReq {
    private String userCode;
    private String approvedDes;
    private String transferUser;
    private String nodeno;
    private String nodename;
    private List<CopyUserEntity> copyUserlist;
    private String actInsID;
}
