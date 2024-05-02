package com.ruoyi.flow.oa.vo;

import com.ruoyi.flow.form.req.FromSaveInfoReq;
import com.ruoyi.flow.oa.req.ProcessInfoReq;
import lombok.Data;

@Data
public class ExportFlowConfigVO {
    private String flowjson;
    private FromSaveInfoReq fromSaveInfoReq;
    private Integer status;
}
