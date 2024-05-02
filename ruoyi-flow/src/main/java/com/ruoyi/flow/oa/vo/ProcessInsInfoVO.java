package com.ruoyi.flow.oa.vo;

import lombok.Data;

import java.util.List;

@Data
public class ProcessInsInfoVO {
    private  String processid;
    private String head;
    private  String flowname;
    private String businessModuleID;
    private String businessMoudle;
    private String flowTempContext;
    private List<String> actchartids;
    private  String operateType;
    private String operatePermission;
    private  String attachmentPermission;
    private  String dataPermission;
    private Boolean isRead;
    private  String actid;
    private  String fromData;
    private  String currentChartId;
    private  String flowstatus;
    private String flowstatusName;


}
