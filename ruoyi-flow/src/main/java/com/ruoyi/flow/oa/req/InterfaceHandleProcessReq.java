package com.ruoyi.flow.oa.req;

import lombok.Data;
import net.sf.json.JSONObject;

@Data
public class InterfaceHandleProcessReq {
    private String procCode;
    private String procBID;
    private JSONObject fromdata;
    private String procInsID;
    private OperationInfoReq operationInfo;
}
