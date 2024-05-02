package com.ruoyi.flow.form.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.flow.form.vo.SelectorMultiVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@ApiModel("保存表单信息")
@Data
public class FromSaveInfoReq {
    @ApiModelProperty("fromID")
    private  String id;
    @NotEmpty(message = "表单名称不能为空")
    @NotBlank(message = "表单名称不能为空")
    @ApiModelProperty("表单名称")
    private  String fromName;
    @NotEmpty(message = "表单名称不能为空")
    @NotBlank(message = "表单名称不能为空")
    @ApiModelProperty("表单表单编码")
    private  String fromCode;
    @ApiModelProperty("表单备注信息")
    private  String fremarks;
    @NotEmpty(message = "设计类型不能为空")
    @NotBlank(message = "设计类型不能为空")
    @ApiModelProperty("设计类型")
    private  String designType;
    @ApiModelProperty("表单内容")
    @NotEmpty(message = "表单内容不能为空")
    @NotBlank(message = "表单内容不能为空")
    private  String fromcontent;
    @NotEmpty(message = "表单设计内容不能为空")
    @NotBlank(message = "表单设计内容不能为空")
    @ApiModelProperty("表单设计内容")
    private  String fromdesignjson;
    @ApiModelProperty("表单类型")
    private  String fromtype;
    @ApiModelProperty("表单对应表名")
    private  String fromTableName;
    @ApiModelProperty("弹出层的宽度设置")
    private  String  dialogwidth;
    @ApiModelProperty("针对明细列表生成查看的弹出页面")
    private List<FromSaveInfoReq> detailfromInfolist;
    @ApiModelProperty("是否创建Api接口")
    private  Boolean  isBuildApi=true;
    @ApiModelProperty("查询列表接口控制")
    private  List<QueryWhereReq>  queryWhere;
//    @ApiModelProperty("角色按钮权限控制")
//    private  List<FromPowerReq> fromPowerReqList;
    @ApiModelProperty("保存后修改其它表的sql")
    private String fromsavelogic;

    @ApiModelProperty("上级菜单ID")
    private String parentMenuid;
    @ApiModelProperty("菜单类型")
    private  String menuType;
    @ApiModelProperty("菜单图标")
    private  String formIcon;
    @ApiModelProperty("图标类型")
    private  String ficontype;
    @ApiModelProperty("授权角色")
    private  List<String> rolelisted;
    @ApiModelProperty("开始采集时间")
    private String collectStartTime;
    @ApiModelProperty("结束采集时间")
    private String collectEndTime;
    @ApiModelProperty("是否填写密码")
    private Integer isPassword;
    @ApiModelProperty("填写密码")
    private String formPassword;
    @ApiModelProperty("填写权限")
    private Integer powerType;
    @ApiModelProperty("填写人员")
    private SelectorMultiVO formPerson;
    @ApiModelProperty("填写后是否修改")
    private Integer isUpdate;
    @ApiModelProperty("每人填写次数")
    private Long formNum;
    @ApiModelProperty("是否需要登录")
    private Integer isLogin;
}
