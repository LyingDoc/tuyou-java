package com.ruoyi.flow.oa.req;

import com.ruoyi.flow.oa.vo.OrganSearchVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@ApiModel("流程查询实体")
@Data
public class QueryPorcessInsListReq {
    @ApiModelProperty("节点名称")
    private  String  actName;
    @ApiModelProperty("审批人")
    private OrganSearchVO approvingUser;
    @ApiModelProperty("发起人")
    private  OrganSearchVO  startUser;
    @ApiModelProperty("流程模板ID")
    private  String   procBID;
    @ApiModelProperty("流程模板")
    private  String   procName;
    @ApiModelProperty("流程状态 1 草稿 2 处理中 3 4已完成 5废弃 6撤销 7待系统处理 8审批失败 9审批异常 10流程异常")
    private  String procInsState;
    @ApiModelProperty("申请人")
    private  OrganSearchVO   applicantuser;
    @ApiModelProperty("流程视图")
    private  String  viewState;
    @ApiModelProperty("流程实例名称")
    private  String  procinsname;
    @ApiModelProperty("流程实例编号")
    private  String   procinsno;
    @ApiModelProperty("发起时间")
    private List<String> starttime;
    @ApiModelProperty("分页码")
    private  Integer page;
    @ApiModelProperty("每页行数")
    private  Integer rows;
}
