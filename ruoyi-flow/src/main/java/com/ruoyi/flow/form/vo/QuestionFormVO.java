package com.ruoyi.flow.form.vo;

import com.ruoyi.flow.vo.OnLineFormVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QuestionFormVO {
    /** 是否填写密码 */
    @ApiModelProperty("是否密码")
    private Integer isPassword;

    /** 填写密码 */
    @ApiModelProperty("填写密码")
    private String password;

    /** 是否修改 */
    @ApiModelProperty("是否修改")
    private Integer isUpdate;
    /** 是否修改 */
    @ApiModelProperty("在线表单对象")
    private  OnLineFormVO onLineFormVO;
}
