package com.ruoyi.flow.form.vo;

import com.ruoyi.flow.form.req.QueryWhereReq;
import lombok.Data;

import java.util.List;

@Data
public class FormInfoVO {
    private String formName;
    private String formCode;
    private String formDesc;
    private String designType;
    private String deviceDesignType;
    private String formid;
    private List<QueryWhereReq> queryWhere;
    private List<String> frompower;

    private String fromsavelogic;
    private String headurl;
    private String backgroundurl;
    private boolean isadapt;
    private int fwidth;
    private int fheight;
    private String logicList;
    private String dialogwidth;
    private String fromTableName;
    private String fromTableFieldRule;
    private String subTableNameRule;
    private String subTableNameFieldRule;
    private int defaulcelnum;
    private int defaultwidth;
    private  String menuType;
    private String parentMenuName;
    private String parentMenuid;
    private List<String> rolelisted;
    private String formIcon;
    private String ficontype;
    private boolean isBuildApi;
    private String version;
    private String isDataScope;
}
