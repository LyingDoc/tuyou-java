package com.ruoyi.flow.oa.req;

import lombok.Data;

/**
 * 另存为流程
 */

@Data
public class SaveAsFlowReq {
    //流程模板
    private  String processId;
    //流程名称
    private  String processname;
    //流程编码
    private  String processcode;

    private  String flowjson;
}
