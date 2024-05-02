package com.ruoyi.flow.oa.vo;

import lombok.Data;

import java.util.Date;

@Data
public class QuerySendNoticeVO {
    private String processinsName;
    private String sendUserName;
    private Integer isRead;
    private Date readTime;
    private String tempName;
    private String tempType;
    private Date createDate;
    private Date updateDate;
    private String updateBy;
    private Long noticeId;
    private String tempNoticeId;
    private String sendUserId;
    private String processinsId;
    private String sendContent;
}
