package com.ruoyi.flow.oa.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.flow.oa.entity.OaPorcessinsEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel("流程实例查询")
@Data
public class QueryPorcessinsVO extends OaPorcessinsEntity {
    @ApiModelProperty("当前节点名称")
    private String actName;
    @ApiModelProperty("审批人")
    private String approvingUser;
    @ApiModelProperty("发起人")
    private String startUser;
    @ApiModelProperty("流程模板ID")
    private String procBID;
    @ApiModelProperty("流程模板")
    private  String procName;
    @ApiModelProperty("流程状态 1 草稿 2 处理中 3 4已完成 5废弃 6撤销 7待系统处理 8审批失败 9审批异常 10流程异常")
    private String procInsState;
    @ApiModelProperty("申请人")
    private String applicantuser;
    @ApiModelProperty("流程实例名称")
    private String procinsname;
    @ApiModelProperty("流程实例编号")
    private String procinsno;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("发起时间开始时间")
    private String starttimeStart;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("发起时间结束时间")
    private String starttimeEnd;
    @ApiModelProperty("当前用户")
    private String username;
}
