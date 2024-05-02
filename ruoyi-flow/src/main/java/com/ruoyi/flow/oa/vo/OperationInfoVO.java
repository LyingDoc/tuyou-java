package com.ruoyi.flow.oa.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("操作信息")
@Data
public class OperationInfoVO {
    @ApiModelProperty("审批意见")
    private String approvedDes;
    @ApiModelProperty("转办用户")
    private String redritUser;
    @ApiModelProperty("节点")
    private String nodeno;
    @ApiModelProperty("节点名称")
    private String nodename;
    @ApiModelProperty("抄送沟通人员列表")
    private List<CopyUserEntity> copyUserlist;
}
