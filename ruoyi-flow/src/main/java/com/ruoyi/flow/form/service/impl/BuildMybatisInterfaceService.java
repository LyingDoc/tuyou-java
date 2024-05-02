package com.ruoyi.flow.form.service.impl;

import com.alibaba.fastjson2.JSON;
import com.ruoyi.flow.form.mapper.FormFormMapper;
import com.ruoyi.flow.vo.HeadEntityVO;
import com.ruoyi.flow.vo.ParamBizFromVO;
import com.ruoyi.flow.vo.R;
import com.ruoyi.flow.form.entity.FormFormEntity;
import com.ruoyi.flow.form.req.*;
import com.ruoyi.flow.form.vo.*;
import net.sf.json.JSONObject;
import ognl.Ognl;
import ognl.OgnlContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 生成Mybatis接口基类
 */
public class BuildMybatisInterfaceService {
    @Autowired
    @Qualifier("secondJdbcTemplate")
    private JdbcTemplate jdbcTemplate;
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 服务对象
     */
    @Resource
    private FormApiconfigServiceImpl formApiconfigService;
    @Resource
    private FormFormServiceImpl tappFromService;
    /**
     * 服务对象
     */
    @Resource
    private FormPageServiceImpl pageService;
    JSONObject param = null;
    OgnlContext context;
    String fromname;
    List<QueryWhereReq> QueryWherelist;
    String fromsavelogic;
    Integer buildType;
    String saveFormInfoSql="";
    /***
     * 默认字段信息
     */
    public List<DefaultFieldInfoVO> defaultFieldlist = new ArrayList<>();

    String getCreateTableDefaultField(Boolean isPublic) {
        List<String> tableDefaultFieldSqlList = new ArrayList<>();
        defaultFieldlist.forEach(ee -> {
            if (isPublic) {
                if (ee.getIsPublic() != null && ee.getIsPublic()) {
                    tableDefaultFieldSqlList.add("`" + ee.getFieldName() + "` " + ee.getFieldType() + " COMMENT '\"+ee.getDescribe()+\"'");
                }
            } else {
                tableDefaultFieldSqlList.add("`" + ee.getFieldName() + "` " + ee.getFieldType() + "  COMMENT '"+ee.getDescribe()+"'");
            }
        });
        if (tableDefaultFieldSqlList.size() > 0) {
            return String.join(",", tableDefaultFieldSqlList) + ",";
        }
        return "";
    }

    List<String> getAllCreateField(Boolean isPublic) {
        if (isPublic) {
            return defaultFieldlist.stream().filter(ee -> ee.getIsPublic() == true).map(DefaultFieldInfoVO::getFieldName).collect(Collectors.toList());
        } else {
            return defaultFieldlist.stream().map(DefaultFieldInfoVO::getFieldName).collect(Collectors.toList());
        }
    }

    String getUpdateDefaultFieldAssignment(Boolean isPublic) {
        List<String> updateDefaultFieldAssignmentSqlList = new ArrayList<>();
        defaultFieldlist.forEach(ee -> {
            if (ee.getIsUpdate() != null && ee.getIsUpdate()) {
                if (isPublic) {
                    if (ee.getIsPublic() != null & ee.getIsPublic()) {
                        if (ee.getIsPrimaryKey() != null && ee.getIsPrimaryKey()) {
                            updateDefaultFieldAssignmentSqlList.add(ee.getFieldName() + "=#{$newid}");
                        } else {
                            updateDefaultFieldAssignmentSqlList.add(ee.getFieldName() + "=" + ee.getFieldAssignment() + "");
                        }
                    }
                } else {
                    updateDefaultFieldAssignmentSqlList.add(ee.getFieldName() + "=" + ee.getFieldAssignment() + "");
                }
            }
        });
        if (updateDefaultFieldAssignmentSqlList.size() > 0) {
            return String.join(",", updateDefaultFieldAssignmentSqlList);
        } else {
            return "";
        }
    }
    /// 生成修改Api
    String getNewUpdateDefaultFieldAssignment(Boolean isPublic) {
        List<String> updateDefaultFieldAssignmentSqlList = new ArrayList<>();
        defaultFieldlist.forEach(ee -> {
            if (ee.getIsUpdate() != null && ee.getIsUpdate()) {
                if (isPublic) {
                    if (ee.getIsPublic() != null & ee.getIsPublic()) {
                        if (!(ee.getIsPrimaryKey() != null && ee.getIsPrimaryKey())) {
                            updateDefaultFieldAssignmentSqlList.add( ee.getFieldName() + "=" + ee.getFieldAssignment() );
                        }
                    }
                } else {
                    updateDefaultFieldAssignmentSqlList.add( ee.getFieldName() + "=" + ee.getFieldAssignment() );
                }
            }
        });
        if (updateDefaultFieldAssignmentSqlList.size() > 0) {
            return "<if test=\"true\">" +String.join(",", updateDefaultFieldAssignmentSqlList)+",</if>";
        } else {
            return "";
        }
    }
    List<String> getInsertDefaultFieldAssignment(Boolean isPublic, Boolean isChild) {
        List<String> insertDefaultFieldSqlList = new ArrayList<>();
        List<String> insertDefaultFieldSqlAssignmentList = new ArrayList<>();
        defaultFieldlist.forEach(ee -> {
            if (isPublic) {
                if (ee.getIsPublic() != null && ee.getIsPublic()) {
                    if (isChild && ee.getIsPrimaryKey() != null && ee.getIsPrimaryKey()) {
                        insertDefaultFieldSqlList.add(ee.getFieldName());
                        insertDefaultFieldSqlAssignmentList.add("#{$newid}");
                    } else {
                        insertDefaultFieldSqlList.add(ee.getFieldName());
                        insertDefaultFieldSqlAssignmentList.add(ee.getFieldAssignment());
                    }
                }
            } else {
                if (isChild && ee.getIsPrimaryKey() != null && ee.getIsPrimaryKey()) {
                    insertDefaultFieldSqlList.add(ee.getFieldName());
                    insertDefaultFieldSqlAssignmentList.add("#{$newid}");
                } else {
                    insertDefaultFieldSqlList.add(ee.getFieldName());
                    insertDefaultFieldSqlAssignmentList.add(ee.getFieldAssignment());
                }
            }
        });
        List<String> resultsqllist = new ArrayList<>();
        if (insertDefaultFieldSqlList.size() > 0) {
            resultsqllist.add(String.join(",", insertDefaultFieldSqlList));
            resultsqllist.add(String.join(",", insertDefaultFieldSqlAssignmentList));
        } else {
            resultsqllist.add("");
            resultsqllist.add("");
        }
        return resultsqllist;
    }

    String getWhereSql() {
        List<String> whereSqlList = new ArrayList<>();
        defaultFieldlist.forEach(ee -> {
                if (StringUtils.isNotBlank(ee.getWhereSql())) {
                    whereSqlList.add(ee.getWhereSql());
                }
        });
        return String.join(" ", whereSqlList);
    }

    /***
     * 更新表结构
     * @param fromdesignjson 设计表单json
     * @param tableName
     */
    public void TableEidt(String fromdesignjson, String tableName, List<QueryWhereReq> queryWhere, String fromsavelogicsql) {
        param = null;
        context = null;
        fromname = "";
        QueryWherelist = null;
        QueryWherelist = queryWhere;
        fromsavelogic = fromsavelogicsql;
        FormInfoVO formInfoVO = JSON.parseObject(fromdesignjson, FormInfoVO.class);
        List<LayoutModelReq> layoutModelReqList = GetFormControl(fromdesignjson, "ctrls", LayoutModelReq.class);
        fromname = param.getString("formName");
        if (layoutModelReqList == null)
            return;
        final int rowCount = jdbcTemplate.queryForObject("SELECT count(*) totalcount FROM information_schema.TABLES WHERE table_schema =(SELECT DATABASE ()) and table_name='" + tableName + "'", Integer.class);
        if (rowCount == 0) {
            CreateTable(layoutModelReqList, tableName,formInfoVO);
        } else {
            AlterTable(layoutModelReqList, tableName,formInfoVO);
        }
    }

    /**
     * 配置的扩展sql 添加的查询条件函数
     *
     * @return
     */
    String getQueryWhereSql() {
        String queryWheresql = "";
        if (QueryWherelist != null) {
            for (QueryWhereReq item : QueryWherelist) {
                String filevalue = getfilevalue(item);
                if (StringUtils.isNotBlank(filevalue)) {
                    queryWheresql = StringUtils.isNotBlank(queryWheresql) ? queryWheresql + (item.getVariabletype() == 3 ? "<if test=\"$query." + item.getValue() + "!=null and $query." + item.getValue() + "!=''\" >" : "") + " and " : "";
                    String endsql = item.getVariabletype() == 3 ? "</if>" : "";
                    if (StringUtils.isNotBlank(filevalue)) {
                        switch (item.getOpt()) {
                            case "=":
                                queryWheresql = queryWheresql + item.getFiled() + "=" + filevalue + endsql;
                                break;
                            case ">":
                                queryWheresql = queryWheresql + item.getFiled() + ">" + filevalue + endsql;
                                break;
                            case ">=":
                                queryWheresql = queryWheresql + item.getFiled() + ">=" + filevalue + endsql;
                                break;
                            case "<":
                                queryWheresql = queryWheresql + item.getFiled() + "<" + filevalue + endsql;
                                break;
                            case "<=":
                                queryWheresql = queryWheresql + item.getFiled() + "<=" + filevalue + endsql;
                                break;
                            case "!=":
                                queryWheresql = queryWheresql + item.getFiled() + "!=" + filevalue + endsql;
                                break;
                            case "like":
                                queryWheresql = queryWheresql + item.getFiled() + " like CONCAT('%'," + filevalue + ",'%') " + endsql;
                                break;
                            case "like%":
                                queryWheresql = queryWheresql + item.getFiled() + " like CONCAT(" + filevalue + ",'%') " + endsql;
                                break;
                            case "%like":
                                queryWheresql = queryWheresql + item.getFiled() + " like CONCAT('%', " + filevalue + ") " + endsql;
                                break;
                            case "in":
                                queryWheresql = queryWheresql + item.getFiled() + " in (" + filevalue + ") " + endsql;
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        }
        return queryWheresql;
    }

    /**
     * 获取条件配置的值
     *
     * @return
     */
    String getfilevalue(QueryWhereReq item) {
        switch (item.getVariabletype()) {
            case 2:
                return "'" + item.getValue().replaceAll("'", "''") + "' ";
            case 3:
                return "#{$query." + item.getValue() + "} ";
            case 4:
                return "#{" + item.getValue() + "} ";
            default:
                return "";
        }
    }

    ///根据提交设计json  获取设计的json
    List<FormControlReq> GetFormControl(String fromdesignjson, String jsonPath) {
        return GetFormControl(fromdesignjson, jsonPath, FormControlReq.class);
    }

    <T> List<T> GetFormControl(String jsonPath, Class<T> clazz) {
        return GetFormControl("", jsonPath, clazz);
    }

    <T> List<T> GetFormControl(String fromdesignjson, String jsonPath, Class<T> clazz) {
        try {
            if (param == null) {
                param = JSONObject.fromObject(fromdesignjson);
                context = getOgnlContext(param);
            }
            Object expression = Ognl.parseExpression(jsonPath);
            Object value = Ognl.getValue(expression, context, context);
            return JSON.parseArray(JSON.toJSONString(value), clazz);
            ///   return  JSONObject.jsonToObjList(FormControlReq.class,JSONUtil.objToJson(value));
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

    private OgnlContext getOgnlContext(JSONObject param) {
        OgnlContext context = new OgnlContext();
        Iterator<String> it = param.keys();
        while (it.hasNext()) {
            String key = it.next();  // 获得key
            Object value = param.get(key);
            context.put(key, value);
        }
        return context;
    }

    ///新增 创建表
    public void CreateTable(List<LayoutModelReq> layoutModelReqList, String tableName,FormInfoVO formInfoVO) {
        String createTableSqlstr = "CREATE TABLE `" + tableName + "`  (\n" + getCreateTableDefaultField(false);
        StringBuffer createTableSql = new StringBuffer();
        StringBuffer selectListSql = new StringBuffer();
        StringBuffer updatesql = new StringBuffer();
        StringBuffer newupdatesql = new StringBuffer();
        StringBuffer batchupdatesql = new StringBuffer();
        StringBuffer saveInfoSql = new StringBuffer();
        updatesql.append("update `" + tableName + "` set  " + getUpdateDefaultFieldAssignment(false));
        newupdatesql.append("update `" + tableName + "` <set>  " + getNewUpdateDefaultFieldAssignment(false));
        StringBuffer insertfiled = new StringBuffer();
        StringBuffer insertfiledValue = new StringBuffer();
        StringBuffer insertChildTableSql = new StringBuffer();
        String queryWhereSql = getQueryWhereSql();
        List<SelectSqlVO> selectSqlVOList=new ArrayList<SelectSqlVO>();
        if (StringUtils.isNotBlank(queryWhereSql)) {
            selectListSql.append("select maintable.* ◐◐◐returnFild☼☼☼ from `" + tableName + "` as maintable ◐◐◐innerSql☼☼☼  where "+ queryWhereSql+"<if test=\"fid!=null and fid!=''\"> fid=#{fid}</if>" );
        } else {
            selectListSql.append("select maintable.* ◐◐◐returnFild☼☼☼ from `" + tableName + "` as maintable ◐◐◐innerSql☼☼☼ <where> <if test=\"fid!=null and fid!=''\"> fid=#{fid}</if>");
        }
        if("1".equals(formInfoVO.getIsDataScope())){
            selectListSql.append(" <if test=\"$dataScope!=null and $dataScope!=''\">and  ${$dataScope}</if>");
        }
        List<HeadEntityVO> headEntityVOS = new ArrayList<>();
        Map<String, ExportDetailSqlVO> exportDetailSql = new HashMap<>();
        StringBuilder whereSqlBuilder = new StringBuilder();
        StringBuilder chartQuerySql = new StringBuilder();
        List<ParamBizFromVO> paramlist = new ArrayList<>();
        for (LayoutModelReq layoutModelReq : layoutModelReqList) {
            switch (layoutModelReq.getType()) {
                case "gridPanel":
                    for (FormControlReq req : layoutModelReq.getOptions().getColumns()) {
                        if (req.getOptions().getIssavedata()) {
                            List<QuestionnaireSqlContentVO> questionnaireSqlContentVOlist = getQuestionnaireSqlContent(req, paramlist, headEntityVOS,selectSqlVOList);
                            if (questionnaireSqlContentVOlist != null) {
                                for (QuestionnaireSqlContentVO questionnaireSqlContentVO : questionnaireSqlContentVOlist) {
                                    if (questionnaireSqlContentVO != null) {
                                        if (!req.type.equals("listview")) {
                                            insertfiled.append("," + questionnaireSqlContentVO.getFiled());
                                            createTableSql.append(questionnaireSqlContentVO.getTableEidtSql() + ",");
                                            whereSqlBuilder.append(questionnaireSqlContentVO.getSelectListSql());
                                            updatesql.append(questionnaireSqlContentVO.getUpdateFiledSql());
                                            newupdatesql.append(questionnaireSqlContentVO.getNewupdateFiledSql());
                                            insertfiledValue.append(questionnaireSqlContentVO.getInsertFiledSql());
                                            if (!"".equals(questionnaireSqlContentVO.getChartQuerySql().toString())) {
                                                if ("".equals(chartQuerySql.toString())) {
                                                    chartQuerySql.append(questionnaireSqlContentVO.getChartQuerySql().toString());
                                                } else {
                                                    chartQuerySql.append("," + questionnaireSqlContentVO.getChartQuerySql().toString());
                                                }
                                            }
                                        } else {
                                            saveInfoSql.append(questionnaireSqlContentVO.getSaveInfoSql());
                                            insertChildTableSql.append(questionnaireSqlContentVO.getInsertChildTableSql());
                                            exportDetailSql.put(req.options.controlId, new ExportDetailSqlVO());
                                            exportDetailSql.get(req.options.controlId).setExportDetailSql("select detailtable.* from " + req.options.controlId + " as detailtable inner join `" + tableName + "` as maintable on detailtable.fparentid=maintable.fid  <where> <if test=\"fcreateby!=null and fcreateby!=''\">and maintable.fcreateby=#{fcreateby} </if>");
                                            exportDetailSql.get(req.options.controlId).setHeadEntity(questionnaireSqlContentVO.getHeadEntity());
                                            exportDetailSql.get(req.options.controlId).setTitle(req.options.title);
                                            if (!"".equals(questionnaireSqlContentVO.getChartQuerySql().toString())) {
                                                if ("".equals(chartQuerySql.toString())) {
                                                    chartQuerySql.append(questionnaireSqlContentVO.getChartQuerySql().toString());
                                                } else {
                                                    chartQuerySql.append("," + questionnaireSqlContentVO.getChartQuerySql().toString());
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;
                case "collapse":
                case "layouttable":
                case "tabs":
                    for (LayoutModelPanelistReq layoutModelPanelistReq : layoutModelReq.getOptions().getPlanelist()) {
                        for (FormControlReq req : layoutModelPanelistReq.getColumns()) {
                            if (req.getOptions().getIssavedata()) {
                                List<QuestionnaireSqlContentVO> questionnaireSqlContentVOlist = getQuestionnaireSqlContent(req, paramlist, headEntityVOS,selectSqlVOList);
                                if (questionnaireSqlContentVOlist != null) {
                                    for (QuestionnaireSqlContentVO questionnaireSqlContentVO : questionnaireSqlContentVOlist) {
                                        if (questionnaireSqlContentVO != null) {
                                            if (!req.type.equals("listview")) {
                                                insertfiled.append("," + questionnaireSqlContentVO.getFiled());
                                                createTableSql.append(questionnaireSqlContentVO.getTableEidtSql() + ",");
                                                whereSqlBuilder.append(questionnaireSqlContentVO.getSelectListSql());
                                                updatesql.append(questionnaireSqlContentVO.getUpdateFiledSql());
                                                newupdatesql.append(questionnaireSqlContentVO.getNewupdateFiledSql());
                                                insertfiledValue.append(questionnaireSqlContentVO.getInsertFiledSql());
                                                if (!"".equals(questionnaireSqlContentVO.getChartQuerySql().toString())) {
                                                    if ("".equals(chartQuerySql.toString())) {
                                                        chartQuerySql.append(questionnaireSqlContentVO.getChartQuerySql().toString());
                                                    } else {
                                                        chartQuerySql.append("," + questionnaireSqlContentVO.getChartQuerySql().toString());
                                                    }
                                                }
                                            } else {
                                                saveInfoSql.append(questionnaireSqlContentVO.getSaveInfoSql());
                                                insertChildTableSql.append(questionnaireSqlContentVO.getInsertChildTableSql());
                                                exportDetailSql.put(req.options.controlId, new ExportDetailSqlVO());
                                                exportDetailSql.get(req.options.controlId).setExportDetailSql("select detailtable.* from " + req.options.controlId + " as detailtable inner join `" + tableName + "` as maintable on detailtable.fparentid=maintable.fid  <where> <if test=\"fcreateby!=null and fcreateby!=''\">and maintable.fcreateby=#{fcreateby} </if>");
                                                exportDetailSql.get(req.options.controlId).setHeadEntity(questionnaireSqlContentVO.getHeadEntity());
                                                exportDetailSql.get(req.options.controlId).setTitle(req.options.title);
                                                if (!"".equals(questionnaireSqlContentVO.getChartQuerySql().toString())) {
                                                    if ("".equals(chartQuerySql.toString())) {
                                                        chartQuerySql.append(questionnaireSqlContentVO.getChartQuerySql().toString());
                                                    } else {
                                                        chartQuerySql.append("," + questionnaireSqlContentVO.getChartQuerySql().toString());
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        GetDefaultHeadEntity(headEntityVOS, paramlist);
        whereSqlBuilder.append(getWhereSql());
        selectListSql.append(whereSqlBuilder.toString());
        if (!StringUtils.isNotBlank(queryWhereSql)) {
            selectListSql.append("</where>");
        }
        selectListSql.append("<if test=\" sorter==null or sorter==''\"> order by flastupdatedate desc</if>");
        selectListSql.append("<if test=\" sorter!=null and sorter!=''\"> order by ${sorter}</if>");
        createTableSqlstr = createTableSqlstr + createTableSql.toString() + "   PRIMARY KEY (`fid`)) comment='" + fromname + "';";
        String getEntitySql = "select *from " + tableName + " where fid=#{fid}";
        updatesql.append(" where fid=#{$tableNewId}");
        batchupdatesql.append(newupdatesql.toString());
        newupdatesql.append("</set> where fid=#{fid}");
        List<String> insertDefaultFieldAssignmentSql = getInsertDefaultFieldAssignment(false, false);
        String insertSql = "insert into " + tableName + "(" + insertDefaultFieldAssignmentSql.get(0) + insertfiled.toString() + ") values(" + insertDefaultFieldAssignmentSql.get(1) + insertfiledValue.toString() + ")";
        jdbcTemplate.execute(createTableSqlstr);
        String selectSql=getNewSql(selectSqlVOList,selectListSql);
        R<String> r = pageService.addPage(tableName, fromname);
        SaveApiConfigReq apiParam = new SaveApiConfigReq();
        if (r.getCode() == 0) {
            apiParam.setMethodType(1);
            apiParam.setMethodName(fromname + "_查询列表接口");
            apiParam.setMethodCode("querylist");

            apiParam.setParamConfig(getParamBizFromVO(paramlist));
            apiParam.setPageId(r.getData());
            apiParam.setHeadList(JSON.toJSONString(headEntityVOS));
            apiParam.setRemarks(fromname + "_查询列表");
            apiParam.setSqlContent(selectSql);
            apiParam.setDbId("business");
            apiParam.setIsSystem("1");
            formApiconfigService.saveApiConfigInfo(apiParam,headEntityVOS);
            if (!"".equals(chartQuerySql.toString())) {
                apiParam.setMethodType(2);
                apiParam.setMethodName(fromname + "_统计功能接口");
                apiParam.setMethodCode("queryChartList");
                apiParam.setPageId(r.getData());
                apiParam.setRemarks(fromname + "_默认查询统计功能");
                apiParam.setSqlContent("select " + chartQuerySql.toString() + ",count(*) as " + tableName + "total from " + tableName);
                apiParam.setDbId("business");
                apiParam.setIsSystem("1");
                formApiconfigService.saveApiConfigInfo(apiParam);
            }
            for (Map.Entry<String, ExportDetailSqlVO> entry : exportDetailSql.entrySet()) {
                R<String> rDetailPage = pageService.addPage(entry.getKey(), entry.getValue().getTitle());
                entry.getValue().setExportDetailSql(entry.getValue().getExportDetailSql() + whereSqlBuilder.toString() + "</where>");
                apiParam.setMethodType(1);
                apiParam.setMethodName(fromname + "_导出接口");
                apiParam.setMethodCode("export");
                apiParam.setPageId(rDetailPage.getData());
                apiParam.setHeadList(JSON.toJSONString(entry.getValue().getHeadEntity()));
                apiParam.setRemarks(fromname + "_根据主表查询条件进行导出");
                apiParam.setSqlContent(entry.getValue().getExportDetailSql());
                apiParam.setDbId("business");
                apiParam.setIsSystem("1");
                formApiconfigService.saveApiConfigInfo(apiParam);
            }
            apiParam.setMethodType(3);
            apiParam.setMethodName(fromname + "_数据保存接口");
            apiParam.setMethodCode("SaveDataInfo");
            apiParam.setPageId(r.getData());
            apiParam.setRemarks(fromname + "_数据保存接口");
            saveInfoSql.append(this.saveFormInfoSql);
            saveInfoSql.append("<sql param=\"$totalcount\"> select count(*) rowcount from " + tableName + " where fid=#{$tableNewId} </sql>");
            saveInfoSql.append("<sql test=\"$totalcount!=null and $totalcount[0].rowcount>0 \"> " + updatesql + " </sql>");
            saveInfoSql.append("<sql test=\"$totalcount==null or $totalcount[0].rowcount==0 \"> " + insertSql + " </sql>");
            if (StringUtils.isNotBlank(fromsavelogic) && StringUtils.isNotBlank(fromsavelogic.trim())) {
                saveInfoSql.append(fromsavelogic);
            }
            apiParam.setSqlContent(saveInfoSql.toString() + insertChildTableSql);
            apiParam.setDbId("business");
            apiParam.setIsSystem("1");
            formApiconfigService.saveApiConfigInfo(apiParam);

            apiParam.setParamConfig(getParamBizFromVO(paramlist));
            apiParam.setMethodType(3);
            apiParam.setMethodName(fromname+"_批量修改接口");
            apiParam.setMethodCode("batchupdateInfo");
            apiParam.setPageId(r.getData());
            apiParam.setRemarks(fromname + "_批量修改接口");
            apiParam.setSqlContent("<for forname=\"ids\"><sql>"+batchupdatesql.toString()+"</set>  where fid=#{ids[$index0]}</sql></for>");
            apiParam.setDbId("business");
            apiParam.setIsSystem("1");
            formApiconfigService.saveApiConfigInfo(apiParam);

            ParamBizFromVO item = new ParamBizFromVO();
            item.setRequired(true);
            item.setVariabletype("常量");
            item.setProperty("fid");
            item.setParamname("主键ID");
            paramlist.add(item);
            apiParam.setParamConfig(getParamBizFromVO(paramlist));
            apiParam.setMethodType(3);
            apiParam.setMethodName(fromname+"_根据Id修改接口");
            apiParam.setMethodCode("updateInfo");
            apiParam.setPageId(r.getData());
            apiParam.setRemarks(fromname + "_根据Id修改接口");
            apiParam.setSqlContent(newupdatesql.toString());
            apiParam.setDbId("business");
            apiParam.setIsSystem("1");
            formApiconfigService.saveApiConfigInfo(apiParam);

            List<ParamBizFromVO> paramlist1=new ArrayList<>();
            paramlist1.add(item);
            apiParam.setParamConfig(getParamBizFromVO(paramlist1));
            apiParam.setMethodType(5);
            apiParam.setMethodName(fromname + "_根据Id获取接口");
            apiParam.setMethodCode("getDataInfo");
            apiParam.setPageId(r.getData());
            apiParam.setRemarks(fromname + "_查询列表");
            apiParam.setSqlContent(getEntitySql);
            apiParam.setDbId("business");
            apiParam.setIsSystem("1");
            formApiconfigService.saveApiConfigInfo(apiParam);
            apiParam.setMethodType(3);
            apiParam.setMethodName(fromname + "_根据Id删除接口");
            apiParam.setMethodCode("deleteInfo");
            apiParam.setPageId(r.getData());
            apiParam.setRemarks(fromname + "_根据Id删除");
            apiParam.setSqlContent("delete from " + tableName + " where fid=#{fid}");
            apiParam.setDbId("business");
            apiParam.setIsSystem("1");
            formApiconfigService.saveApiConfigInfo(apiParam);

            List<ParamBizFromVO> paramlist2 =  new ArrayList<>();
            ParamBizFromVO item2 = new ParamBizFromVO();
            item2.setRequired(true);
            item2.setVariabletype("Array");
            item2.setProperty("ids");
            item2.setParamname("id");
            paramlist2.add(item2);
            apiParam.setParamConfig(getParamBizFromVO(paramlist2));
            apiParam.setMethodType(3);
            apiParam.setMethodName(fromname+"_批量删除接口");
            apiParam.setMethodCode("batchDeleteInfo");
            apiParam.setPageId(r.getData());
            apiParam.setRemarks(fromname + "_批量删除");
            apiParam.setSqlContent("<for forname=\"ids\"><sql>delete from " + tableName + " where fid=#{ids[$index0]}</sql></for>");
            apiParam.setDbId("business");
            apiParam.setIsSystem("1");
            formApiconfigService.saveApiConfigInfo(apiParam);
        }
    }

    ///新增修改表结构
    public void AlterTable(List<LayoutModelReq> layoutModelReqList, String tableName,FormInfoVO formInfoVO) {
        List<ColumnInfoVO> columnNameList = jdbcTemplate.query("select column_name as columnname ,column_type as columntype  from information_schema.columns where table_schema =(SELECT DATABASE ()) and table_name='" + tableName + "'", new Object[]{}, new BeanPropertyRowMapper<>(ColumnInfoVO.class));
        List<String> newColummNameList = getAllCreateField(false);
        StringBuffer selectListSql = new StringBuffer();
        StringBuffer updatesql = new StringBuffer();
        StringBuffer newupdatesql = new StringBuffer();
        StringBuffer batchupdatesql = new StringBuffer();
        StringBuffer saveInfoSql = new StringBuffer();
        updatesql.append("update `" + tableName + "` set  " + getUpdateDefaultFieldAssignment(false));
        newupdatesql.append("update `" + tableName + "` <set>  " + getNewUpdateDefaultFieldAssignment(false));
        StringBuffer insertfiled = new StringBuffer();
        StringBuffer insertfiledValue = new StringBuffer();
        StringBuffer insertChildTableSql = new StringBuffer();
        String queryWhereSql = getQueryWhereSql();
        List<SelectSqlVO> selectSqlVOList=new ArrayList<SelectSqlVO>();
        if (StringUtils.isNotBlank(queryWhereSql)) {
            selectListSql.append("select maintable.* ◐◐◐returnFild☼☼☼ from `" + tableName + "` as maintable ◐◐◐innerSql☼☼☼ where "+ queryWhereSql+" <if test=\"fid!=null and fid!=''\"> maintable.fid=#{fid}</if>" );
        } else {
            selectListSql.append("select maintable.*  ◐◐◐returnFild☼☼☼ from `" + tableName + "` as maintable ◐◐◐innerSql☼☼☼ <where> <if test=\"fid!=null and fid!=''\"> maintable.fid=#{fid}</if>");
        }
        if("1".equals(formInfoVO.getIsDataScope())){
            selectListSql.append(" <if test=\"$dataScope!=null and $dataScope!=''\">and  ${$dataScope}</if>");
        }
        Map<String, ExportDetailSqlVO> exportDetailSql = new HashMap<>();
        StringBuilder whereSqlBuilder = new StringBuilder();
        ///对表结构进行修改
        List<HeadEntityVO> headEntityVOS = new ArrayList<>();
        StringBuilder chartQuerySql = new StringBuilder();
        List<ParamBizFromVO> paramlist = new ArrayList<>();
        for (LayoutModelReq layoutModelReq : layoutModelReqList) {
            switch (layoutModelReq.getType()) {
                case "gridPanel":
                    for (FormControlReq req : layoutModelReq.getOptions().getColumns()) {
                        if (req.getOptions().getIssavedata()) {
                            List<QuestionnaireSqlContentVO> questionnaireSqlContentVOlist = getQuestionnaireSqlContent(req, paramlist, headEntityVOS,selectSqlVOList);
                            if (questionnaireSqlContentVOlist != null) {
                                for (QuestionnaireSqlContentVO questionnaireSqlContentVO : questionnaireSqlContentVOlist) {
                                    StringBuilder alterTableSql = new StringBuilder();
                                    if (questionnaireSqlContentVO != null) {
                                        if (!newColummNameList.contains(questionnaireSqlContentVO.getFiled())) {
                                            newColummNameList.add(questionnaireSqlContentVO.getFiled());
                                        }
                                        alterTableSql.append("ALTER TABLE " + tableName);
                                        Optional<ColumnInfoVO> columnInfo = columnNameList.stream().filter(ee -> ee.getColumnname().equals(questionnaireSqlContentVO.getFiled())).findFirst();
                                        if (!columnInfo.equals(Optional.empty())) {
                                            alterTableSql.append("  MODIFY  ");
                                        } else {
                                            alterTableSql.append("  ADD  ");
                                        }
                                        whereSqlBuilder.append(questionnaireSqlContentVO.getSelectListSql());
                                        updatesql.append(questionnaireSqlContentVO.getUpdateFiledSql());
                                        newupdatesql.append(questionnaireSqlContentVO.getNewupdateFiledSql());
                                        insertfiledValue.append(questionnaireSqlContentVO.getInsertFiledSql());
                                        if (!req.type.equals("listview")) {
                                            insertfiled.append("," + questionnaireSqlContentVO.getFiled());
                                            alterTableSql.append(questionnaireSqlContentVO.getTableEidtSql());
                                            if (!"".equals(questionnaireSqlContentVO.getChartQuerySql().toString())) {
                                                if ("".equals(chartQuerySql.toString())) {
                                                    chartQuerySql.append(questionnaireSqlContentVO.getChartQuerySql().toString());
                                                } else {
                                                    chartQuerySql.append("," + questionnaireSqlContentVO.getChartQuerySql().toString());
                                                }
                                            }
                                            if (columnInfo.equals(Optional.empty()) || (!columnInfo.equals(Optional.empty()) && !columnInfo.get().getColumntype().equals(questionnaireSqlContentVO.getColumnType()))) {
                                                jdbcTemplate.execute(alterTableSql.toString());
                                            }
                                        } else {
                                            insertChildTableSql.append(questionnaireSqlContentVO.getInsertChildTableSql());
                                            saveInfoSql.append(questionnaireSqlContentVO.getSaveInfoSql());
                                            exportDetailSql.put(req.options.controlId, new ExportDetailSqlVO());
                                            exportDetailSql.get(req.options.controlId).setExportDetailSql("select detailtable.* from " + req.options.controlId + " as detailtable inner join `" + tableName + "` as maintable on detailtable.fparentid=maintable.fid  <where> <if test=\"fcreateby!=null and fcreateby!=''\">and maintable.fcreateby=#{fcreateby} </if>");
                                            exportDetailSql.get(req.options.controlId).setHeadEntity(questionnaireSqlContentVO.getHeadEntity());
                                            if (!"".equals(questionnaireSqlContentVO.getChartQuerySql().toString())) {
                                                if ("".equals(chartQuerySql.toString())) {
                                                    chartQuerySql.append(questionnaireSqlContentVO.getChartQuerySql().toString());
                                                } else {
                                                    chartQuerySql.append("," + questionnaireSqlContentVO.getChartQuerySql().toString());
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;
                case "collapse":
                case "layouttable":
                case "tabs":
                    for (LayoutModelPanelistReq layoutModelPanelistReq : layoutModelReq.getOptions().getPlanelist()) {
                        for (FormControlReq req : layoutModelPanelistReq.getColumns()) {
                            if (req.getOptions().getIssavedata()) {
                                List<QuestionnaireSqlContentVO> questionnaireSqlContentVOlist1 = getQuestionnaireSqlContent(req, paramlist, headEntityVOS,selectSqlVOList);
                                if (questionnaireSqlContentVOlist1 != null) {
                                    for (QuestionnaireSqlContentVO questionnaireSqlContentVO : questionnaireSqlContentVOlist1) {
                                        StringBuilder alterTableSql = new StringBuilder();
                                        if (questionnaireSqlContentVO != null) {
                                            if (!newColummNameList.contains(questionnaireSqlContentVO.getFiled())) {
                                                newColummNameList.add(questionnaireSqlContentVO.getFiled());
                                            }
                                            alterTableSql.append("ALTER TABLE " + tableName);
                                            Optional<ColumnInfoVO> columnInfo = columnNameList.stream().filter(ee -> ee.getColumnname().equals(questionnaireSqlContentVO.getFiled())).findFirst();
                                            if (!columnInfo.equals(Optional.empty())) {
                                                alterTableSql.append("  MODIFY  ");
                                            } else {
                                                alterTableSql.append("  ADD  ");
                                            }
                                            whereSqlBuilder.append(questionnaireSqlContentVO.getSelectListSql());
                                            updatesql.append(questionnaireSqlContentVO.getUpdateFiledSql());
                                            newupdatesql.append(questionnaireSqlContentVO.getNewupdateFiledSql());
                                            insertfiledValue.append(questionnaireSqlContentVO.getInsertFiledSql());
                                            if (!req.type.equals("listview")) {
                                                insertfiled.append("," + questionnaireSqlContentVO.getFiled());
                                                alterTableSql.append(questionnaireSqlContentVO.getTableEidtSql());
                                                if (!"".equals(questionnaireSqlContentVO.getChartQuerySql().toString())) {
                                                    if ("".equals(chartQuerySql.toString())) {
                                                        chartQuerySql.append(questionnaireSqlContentVO.getChartQuerySql().toString());
                                                    } else {
                                                        chartQuerySql.append("," + questionnaireSqlContentVO.getChartQuerySql().toString());
                                                    }
                                                }
                                                if (columnInfo.equals(Optional.empty()) || (!columnInfo.equals(Optional.empty()) && !columnInfo.get().getColumntype().equals(questionnaireSqlContentVO.getColumnType()))) {
                                                    jdbcTemplate.execute(alterTableSql.toString());
                                                }
                                            } else {
                                                insertChildTableSql.append(questionnaireSqlContentVO.getInsertChildTableSql());
                                                saveInfoSql.append(questionnaireSqlContentVO.getSaveInfoSql());
                                                exportDetailSql.put(req.options.controlId, new ExportDetailSqlVO());
                                                exportDetailSql.get(req.options.controlId).setExportDetailSql("select detailtable.* from " + req.options.controlId + " as detailtable inner join `" + tableName + "` as maintable on detailtable.fparentid=maintable.fid  <where> <if test=\"fcreateby!=null and fcreateby!=''\">and maintable.fcreateby=#{fcreateby} </if>");
                                                exportDetailSql.get(req.options.controlId).setHeadEntity(questionnaireSqlContentVO.getHeadEntity());
                                                if (!"".equals(questionnaireSqlContentVO.getChartQuerySql().toString())) {
                                                    if ("".equals(chartQuerySql.toString())) {
                                                        chartQuerySql.append(questionnaireSqlContentVO.getChartQuerySql().toString());
                                                    } else {
                                                        chartQuerySql.append("," + questionnaireSqlContentVO.getChartQuerySql().toString());
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        ///获取删除表列字段集合
        List<ColumnInfoVO> delColumnList = columnNameList.stream().filter(ee -> !newColummNameList.contains(ee.getColumnname())).collect(Collectors.toList());
        delColumnList.forEach(ee -> {
            jdbcTemplate.execute("ALTER TABLE " + tableName + " DROP COLUMN " + ee.getColumnname() + "; ");
        });
        List<String> oldcolumnlist = columnNameList.stream().map(ee -> ee.columnname).collect(Collectors.toList());
        List<DefaultFieldInfoVO> addfieldList = defaultFieldlist.stream().filter(ee -> !oldcolumnlist.contains(ee.getFieldName())).collect(Collectors.toList());
        addfieldList.forEach(ee -> {
            jdbcTemplate.execute("ALTER TABLE " + tableName + " add COLUMN " + ee.getFieldName() + "  " + ee.getFieldType() + "    COMMENT '" + ee.getDescribe() + "'; ");

        });
        jdbcTemplate.execute("ALTER TABLE " + tableName + " comment '" + fromname + "'; ");
        whereSqlBuilder.append(getWhereSql());
        selectListSql.append(whereSqlBuilder.toString());
        GetDefaultHeadEntity(headEntityVOS, paramlist);
        if (!StringUtils.isNotBlank(queryWhereSql)) {
            selectListSql.append("</where>");
        }
        selectListSql.append("<if test=\" sorter==null or sorter==''\"> order by maintable.flastupdatedate desc</if>");
        selectListSql.append("<if test=\" sorter!=null and sorter!=''\"> order by maintable.${sorter}</if>");
        StringBuilder returnFildBuilder=new StringBuilder();
        StringBuilder innerSqlBuilder=new StringBuilder();
        selectSqlVOList.forEach(ff->{
            if(returnFildBuilder.length()==0) {
                returnFildBuilder.append(ff.getReturnFild());
                innerSqlBuilder.append(ff.getInnerSql());
            }else{
                returnFildBuilder.append(","+ff.getReturnFild());
                innerSqlBuilder.append(ff.getInnerSql());
            }

        });
        updatesql.append(" where fid=#{$tableNewId}");
        batchupdatesql.append(newupdatesql.toString());
        newupdatesql.append("</set> where fid=#{fid}");
        String getEntitySql = "select *from " + tableName + " where fid=#{fid}";
        String selectSql =getNewSql(selectSqlVOList,selectListSql);
        List<String> insertDefaultFieldAssignmentSql = getInsertDefaultFieldAssignment(false, false);
        String insertSql = "insert into " + tableName + "(" + insertDefaultFieldAssignmentSql.get(0) + " " + insertfiled.toString() + ") values(" + insertDefaultFieldAssignmentSql.get(1) + " " + insertfiledValue.toString() + ")";
        R<String> r = pageService.addPage(tableName, fromname);
        SaveApiConfigReq apiParam = new SaveApiConfigReq();
        if (r.getCode() == 0) {
            apiParam.setMethodType(1);
            apiParam.setMethodName(fromname+"_查询列表接口");
            apiParam.setMethodCode("querylist");
            apiParam.setPageId(r.getData());
            apiParam.setHeadList(JSON.toJSONString(headEntityVOS));
            apiParam.setRemarks(fromname + "_查询列表");
            apiParam.setIsSystem("1");

            apiParam.setParamConfig(getParamBizFromVO(paramlist));
            apiParam.setSqlContent(selectSql);
            apiParam.setDbId("business");
            formApiconfigService.saveApiConfigInfo(apiParam,headEntityVOS);
            if (!"".equals(chartQuerySql.toString())) {
                apiParam.setMethodType(2);
                apiParam.setMethodName(fromname+"_统计接口");
                apiParam.setMethodCode("queryChartList");
                apiParam.setPageId(r.getData());
                apiParam.setRemarks(fromname + "_默认查询统计功能");
                apiParam.setSqlContent("select " + chartQuerySql.toString() + ",count(*) as " + tableName + "total from " + tableName);
                apiParam.setDbId("business");
                apiParam.setIsSystem("1");
                formApiconfigService.saveApiConfigInfo(apiParam);
            }
            for (Map.Entry<String, ExportDetailSqlVO> entry : exportDetailSql.entrySet()) {
                R<String> rDetailPage = pageService.addPage(entry.getKey(), entry.getValue().getTitle());
                entry.getValue().setExportDetailSql(entry.getValue().getExportDetailSql() + whereSqlBuilder.toString() + "</where>");
                apiParam.setMethodType(1);
                apiParam.setMethodName(fromname+"_导出接口");
                apiParam.setMethodCode("export");
                apiParam.setPageId(rDetailPage.getData());
                apiParam.setHeadList(JSON.toJSONString(entry.getValue().getHeadEntity()));
                apiParam.setRemarks(fromname + "_根据主表查询条件进行导出");
                apiParam.setSqlContent(entry.getValue().getExportDetailSql());
                apiParam.setDbId("business");
                apiParam.setIsSystem("1");
                formApiconfigService.saveApiConfigInfo(apiParam);
            }
            apiParam.setMethodType(3);
            apiParam.setMethodName(fromname+"_数据保存接口");
            apiParam.setMethodCode("SaveDataInfo");
            apiParam.setPageId(r.getData());
            apiParam.setRemarks(fromname + "_数据保存接口");
            saveInfoSql.append(this.saveFormInfoSql);
            saveInfoSql.append("<sql param=\"$totalcount\"> select count(*) rowcount from " + tableName + " where fid=#{$tableNewId} </sql>");
            saveInfoSql.append("<sql test=\"$totalcount!=null and $totalcount[0].rowcount>0 \"> " + updatesql + " </sql>");
            saveInfoSql.append("<sql test=\"$totalcount==null or $totalcount[0].rowcount==0 \"> " + insertSql + " </sql>");
            if (StringUtils.isNotBlank(fromsavelogic) && StringUtils.isNotBlank(fromsavelogic.trim())) {
                saveInfoSql.append(fromsavelogic);
            }
            apiParam.setSqlContent(saveInfoSql.toString() + insertChildTableSql);
            apiParam.setDbId("business");
            apiParam.setIsSystem("1");
            formApiconfigService.saveApiConfigInfo(apiParam);
            apiParam.setParamConfig(getParamBizFromVO(paramlist));
            apiParam.setMethodType(3);
            apiParam.setMethodName(fromname+"_批量修改接口");
            apiParam.setMethodCode("batchupdateInfo");
            apiParam.setPageId(r.getData());
            apiParam.setRemarks(fromname + "_批量修改接口");
            apiParam.setSqlContent("<for forname=\"ids\"><sql>"+batchupdatesql.toString()+"</set>  where fid=#{ids[$index0]}</sql></for>");
            apiParam.setDbId("business");
            apiParam.setIsSystem("1");
            formApiconfigService.saveApiConfigInfo(apiParam);

            ParamBizFromVO item = new ParamBizFromVO();
            item.setRequired(true);
            item.setVariabletype("常量");
            item.setProperty("fid");
            item.setParamname("主键ID");
            paramlist.add(item);
            apiParam.setParamConfig(getParamBizFromVO(paramlist));
            apiParam.setMethodType(3);
            apiParam.setMethodName(fromname+"_根据Id修改接口");
            apiParam.setMethodCode("updateInfo");
            apiParam.setPageId(r.getData());
            apiParam.setRemarks(fromname + "_根据Id修改接口");
            apiParam.setSqlContent(newupdatesql.toString());
            apiParam.setDbId("business");
            apiParam.setIsSystem("1");
            formApiconfigService.saveApiConfigInfo(apiParam);
            List<ParamBizFromVO> paramlist1 = new ArrayList<>();
            paramlist1.add(item);
            apiParam.setParamConfig(getParamBizFromVO(paramlist1));
            apiParam.setMethodType(5);
            apiParam.setMethodName(fromname+"_根据Id获取接口");
            apiParam.setMethodCode("getDataInfo");
            apiParam.setPageId(r.getData());
            apiParam.setRemarks(fromname + "_根据Id获取接口");
            apiParam.setSqlContent(getEntitySql);
            apiParam.setDbId("business");
            apiParam.setIsSystem("1");
            formApiconfigService.saveApiConfigInfo(apiParam);
            apiParam.setMethodType(3);
            apiParam.setMethodName(fromname+"_根据Id删除接口");
            apiParam.setMethodCode("deleteInfo");
            apiParam.setPageId(r.getData());
            apiParam.setRemarks(fromname + "_根据Id删除");
            apiParam.setSqlContent("delete from " + tableName + " where fid=#{fid}");
            apiParam.setDbId("business");
            apiParam.setIsSystem("1");
            formApiconfigService.saveApiConfigInfo(apiParam);
            List<ParamBizFromVO> paramlist2 =  new ArrayList<>();
            ParamBizFromVO item2 = new ParamBizFromVO();
            item2.setRequired(true);
            item2.setVariabletype("Array");
            item2.setProperty("ids");
            item2.setParamname("id");
            paramlist2.add(item2);
            apiParam.setParamConfig(getParamBizFromVO(paramlist2));
            apiParam.setMethodType(3);
            apiParam.setMethodName(fromname+"_批量删除接口");
            apiParam.setMethodCode("batchDeleteInfo");
            apiParam.setPageId(r.getData());
            apiParam.setRemarks(fromname + "_批量删除");
            apiParam.setSqlContent("<for forname=\"ids\"><sql>delete from " + tableName + " where fid=#{ids[$index0]}</sql></for>");
            apiParam.setDbId("business");
            apiParam.setIsSystem("1");
            formApiconfigService.saveApiConfigInfo(apiParam);
        }
    }

    void GetDefaultHeadEntity(List<HeadEntityVO> headEntityVOS, List<ParamBizFromVO> paramlist) {
        defaultFieldlist.forEach(ee -> {
            if (ee.getIsGridShow() != null && ee.getIsGridShow()) {
                HeadEntityVO headEntityVO2 = new HeadEntityVO();
                headEntityVO2.setBindname(ee.getFieldName());
                headEntityVO2.setHeadname(ee.getDescribe());
                if("procinsname,procinsno".indexOf(ee.getFieldName())>=0){
                    headEntityVOS.add(0,headEntityVO2);
                }else {
                    headEntityVOS.add(headEntityVO2);
                }
            }
            if (StringUtils.isNotBlank(ee.getWhereSql())) {
                ParamBizFromVO item = new ParamBizFromVO();
                item.setRequired(false);
                if("fcreationdate;flastupdatedate".contains( ee.getFieldName())){
                    item.setVariabletype("Array");
                }else {
                    item.setVariabletype("常量");
                }
                item.setProperty(ee.getFieldName());
                item.setParamname(ee.getDescribe());
                paramlist.add(item);
            }
        });
    }

    List<QuestionnaireSqlContentVO> getQuestionnaireSqlContent(FormControlReq req, List<ParamBizFromVO> paramlist, List<HeadEntityVO> headEntityVOList, List<SelectSqlVO> selectSqlVOList) {
        List<QuestionnaireSqlContentVO> returnQuestionnairelist = new ArrayList<>();
        QuestionnaireSqlContentVO questionnaireSqlContentVO = getNewQuestionnaireContent();
        questionnaireSqlContentVO.setFiled(req.options.filed);
        HeadEntityVO headEntityVO = new HeadEntityVO();
        headEntityVO.setBindname(req.options.filed);
        headEntityVO.setHeadname(req.options.title);
        ParamBizFromVO item = new ParamBizFromVO();
        switch (req.type) {
            case "input":
                questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` varchar(" + req.options.dataLength + ")   DEFAULT NULL ");
                questionnaireSqlContentVO.getSelectListSql().append("<if test=\"" + req.options.filed + "!=null and " + req.options.filed + "!=''\">and maintable." + req.options.filed + " like CONCAT('%',#{" + req.options.filed + "},'%') </if>");
                questionnaireSqlContentVO.getInsertFiledSql().append(",#{" + req.options.filed + "}");
                questionnaireSqlContentVO.getUpdateFiledSql().append(" ," + req.options.filed + "=#{" + req.options.filed + "}");
                questionnaireSqlContentVO.getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "!=null\">" + req.options.filed + "=#{" + req.options.filed + "},</if>");

                item.setRequired(false);
                item.setVariabletype("常量");
                item.setProperty(req.options.filed);
                item.setParamname(req.options.title);
                paramlist.add(item);
                headEntityVOList.add(headEntityVO);
                break;
            case "textarea":
                questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "`   varchar(" + req.options.dataLength + ")  DEFAULT NULL ");
                questionnaireSqlContentVO.getInsertFiledSql().append(",#{" + req.options.filed + "}");
                questionnaireSqlContentVO.getUpdateFiledSql().append(" ," + req.options.filed + "=#{" + req.options.filed + "}");
                questionnaireSqlContentVO.getSelectListSql().append("<if test=\"" + req.options.filed + "!=null and " + req.options.filed + "!=''\">and maintable." + req.options.filed + " like CONCAT('%',#{" + req.options.filed + "},'%') </if>");
                questionnaireSqlContentVO.getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "!=null\">" + req.options.filed + "=#{" + req.options.filed + "},</if>");
                item.setVariabletype("常量");
                item.setRequired(false);
                item.setProperty(req.options.filed);
                item.setParamname(req.options.title);
                paramlist.add(item);
                headEntityVOList.add(headEntityVO);
                break;
            case "select":
                if (req.options.getIsMulty() != null && req.options.getIsMulty().equals(true)) {
                    questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` varchar(500)   DEFAULT NULL ");
                    questionnaireSqlContentVO.setColumnType("varchar(1000)");
                    questionnaireSqlContentVO.getSelectListSql().append("<if test=\"" + req.options.filed + "!=null and " + req.options.filed + "!=''\">and maintable." + req.options.filed + " like CONCAT('%', #{" + req.options.filed + "},'%') </if>");
                } else {
                    questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` varchar(" + req.options.dataLength + ")   DEFAULT NULL ");
                    questionnaireSqlContentVO.setColumnType("varchar(50)");
                    questionnaireSqlContentVO.getSelectListSql().append("<if test=\"" + req.options.filed + "!=null and " + req.options.filed + "!=''\">and maintable." + req.options.filed + " =  #{" + req.options.filed + "} </if>");
                }
                questionnaireSqlContentVO.getChartQuerySql().append(chartFiledSql(req.options.getIsMulty(), req.options.filed, req.options.getOptions()));
                questionnaireSqlContentVO.getInsertFiledSql().append(",#{" + req.options.filed + "}");
                questionnaireSqlContentVO.getUpdateFiledSql().append(" ," + req.options.filed + "=#{" + req.options.filed + "}");
                questionnaireSqlContentVO.getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "!=null\">" + req.options.filed + "=#{" + req.options.filed + "},</if>");
                item.setVariabletype("常量");
                item.setRequired(false);
                item.setProperty(req.options.filed);
                item.setParamname(req.options.title);
                if ("1".equals(req.options.isRemote)) {
                    headEntityVO.setBindname(req.options.labelText);
                    item.setFormId(querybyFormId(req.options.sourceApi));
                    SelectSqlVO selectSqlVO=new SelectSqlVO();
                    selectSqlVO.setReturnFild(" , "+req.model+"."+req.options.labelText+" ");
                    selectSqlVO.setInnerSql(getLetSql(req));
                    selectSqlVOList.add(selectSqlVO);
                }
                paramlist.add(item);
                headEntityVOList.add(headEntityVO);
                break;
            case "date":
                switch (req.options.modeltype) {
                    case "date":
                        if("yyyy-MM-DD HH:mm:ss".equals(req.options.format)) {
                            questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` datetime(0)   DEFAULT NULL ");
                        }else if("yyyy-MM-DD".equals(req.options.format)){
                            questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` date   DEFAULT NULL ");
                        }else if("yyyy".equals(req.options.format)){
                            questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` varchar(50)   DEFAULT NULL ");
                        }else if("yyyy-MM".equals(req.options.format)){
                            questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` varchar(50)   DEFAULT NULL ");
                        }else{
                            questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` datetime(0)   DEFAULT NULL ");
                        }
                        questionnaireSqlContentVO.getSelectListSql().append("<if test=\"" + req.options.filed + "!=null and " + req.options.filed + ".size()>0\">and maintable." + req.options.filed + " between #{" + req.options.filed + "[0]} and #{" + req.options.filed + "[1]}  </if>");
                        break;
                    case "year":
                        questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` varchar(50)   DEFAULT NULL ");
                        questionnaireSqlContentVO.getSelectListSql().append("<if test=\"" + req.options.filed + "!=null and " + req.options.filed + "!=''\">and date_format(maintable." + req.options.filed + ",'%Y') = #{" + req.options.filed + "}  </if>");
                        break;
                    case "month":
                        questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` varchar(50)   DEFAULT NULL ");
                        questionnaireSqlContentVO.getSelectListSql().append("<if test=\"" + req.options.filed + "!=null and " + req.options.filed + ".size()>0\">and date_format(maintable." + req.options.filed + ",'%Y%m') between REPLACE(#{" + req.options.filed + "[0]},'-','') and REPLACE(#{" + req.options.filed + "[1]},'-','')  </if>");
                        break;
                    case "week":
                        questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "`  varchar(50)   DEFAULT NULL ");
                        questionnaireSqlContentVO.getSelectListSql().append("<if test=\"" + req.options.filed + "!=null and " + req.options.filed + ".size()>0\">and maintable." + req.options.filed + " between #{" + req.options.filed + "[0]} and #{" + req.options.filed + "[1]}  </if>");
                        break;
                    case "daterange":
                    case "monthrange":
                    case "datetimerange":
                        questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "`  varchar(100)   DEFAULT NULL ");
                        returnQuestionnairelist.add(getNewQuestionnaireContent());
                        returnQuestionnairelist.get(0).getTableEidtSql().append("`" + req.options.filed + "_start` datetime(0)    DEFAULT NULL ");
                        returnQuestionnairelist.get(0).getInsertFiledSql().append(",#{" +  req.options.filed + "==null&&req.options.filed.size()>=0?\"\":" +    req.options.filed + "[0]}");
                        returnQuestionnairelist.get(0).getUpdateFiledSql().append(" ," + req.options.filed + "_start=#{" +  req.options.filed + "==null&&req.options.filed.size()>=0?\"\":" +  req.options.filed + "[0]}");
                        returnQuestionnairelist.get(0).getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "_start!=null\">" + req.options.filed + "_start=#{" + req.options.filed + "_start},</if>");
                        returnQuestionnairelist.get(0).setFiled(req.options.filed + "_start");
                        returnQuestionnairelist.add(getNewQuestionnaireContent());
                        returnQuestionnairelist.get(1).getTableEidtSql().append("`" + req.options.filed + "_end` datetime(0)    DEFAULT NULL ");
                        returnQuestionnairelist.get(1).getInsertFiledSql().append(",#{" +  req.options.filed + "==null&&req.options.filed.size()>=1?\"\":" +    req.options.filed + "[1]}");
                        returnQuestionnairelist.get(1).getUpdateFiledSql().append(" ," + req.options.filed + "_end=#{" +  req.options.filed + "==null&&req.options.filed.size()>=0?\"\":" +  req.options.filed + "[1]}");
                        returnQuestionnairelist.get(1).getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "_end!=null\">" + req.options.filed + "_end=#{" + req.options.filed + "_end},</if>");
                        returnQuestionnairelist.get(1).setFiled(req.options.filed + "_end");
                        break;
                    default:
                        questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` varchar(50)   DEFAULT NULL ");
                        questionnaireSqlContentVO.getSelectListSql().append("<if test=\"" + req.options.filed + "!=null and " + req.options.filed + "!=''\">and maintable." + req.options.filed + " = #{" + req.options.filed + "} </if>");
                        break;

                }
                questionnaireSqlContentVO.getInsertFiledSql().append(",#{" + req.options.filed + "}");
                questionnaireSqlContentVO.getUpdateFiledSql().append(" ," + req.options.filed + "=#{" + req.options.filed + "}");
                questionnaireSqlContentVO.getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "!=null\">" + req.options.filed + "=#{" + req.options.filed + "},</if>");
                headEntityVOList.add(headEntityVO);
                break;
            case "radio":
                questionnaireSqlContentVO.getChartQuerySql().append(chartFiledSql(false, req.options.filed, req.options.getOptions()));
                questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` varchar(" + req.options.dataLength + ")   DEFAULT NULL ");
                questionnaireSqlContentVO.getSelectListSql().append("<if test=\"" + req.options.filed + "!=null and " + req.options.filed + "!=''\">and maintable." + req.options.filed + " = #{" + req.options.filed + "} </if>");
                questionnaireSqlContentVO.getInsertFiledSql().append(",#{" + req.options.filed + "}");
                questionnaireSqlContentVO.getUpdateFiledSql().append(" ," + req.options.filed + "=#{" + req.options.filed + "}");
                questionnaireSqlContentVO.getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "!=null\">" + req.options.filed + "=#{" + req.options.filed + "},</if>");
                item.setVariabletype("常量");
                item.setRequired(false);
                item.setProperty(req.options.filed);
                item.setParamname(req.options.title);
                if ("1".equals(req.options.isRemote)) {
                    headEntityVO.setBindname(req.options.labelText);
                    item.setFormId(querybyFormId(req.options.sourceApi));
                    SelectSqlVO selectSqlVO=new SelectSqlVO();
                    selectSqlVO.setReturnFild(" , "+req.model+"."+req.options.labelText+" ");
                    selectSqlVO.setInnerSql(getLetSql(req));
                    selectSqlVOList.add(selectSqlVO);
                }
                paramlist.add(item);
                headEntityVOList.add(headEntityVO);
                break;
            case "checkbox":
                questionnaireSqlContentVO.getChartQuerySql().append(chartFiledSql(true, req.options.filed, req.options.getOptions()));
                questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` varchar(500)   DEFAULT NULL ");
                questionnaireSqlContentVO.getSelectListSql().append("<if test=\"" + req.options.filed + "!=null and " + req.options.filed + "!=''\">and maintable." + req.options.filed + " like CONCAT('%', #{" + req.options.filed + "},'%') </if>");
                questionnaireSqlContentVO.getInsertFiledSql().append(",#{" + req.options.filed + "}");
                questionnaireSqlContentVO.getUpdateFiledSql().append(" ," + req.options.filed + "=#{" + req.options.filed + "}");
                questionnaireSqlContentVO.getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "!=null\">" + req.options.filed + "=#{" + req.options.filed + "},</if>");
                item.setVariabletype("常量");
                item.setRequired(false);
                item.setProperty(req.options.filed);
                item.setParamname(req.options.title);
                if ("1".equals(req.options.isRemote)) {
                    headEntityVO.setBindname(req.options.labelText);
                    item.setFormId(querybyFormId(req.options.sourceApi));
                    SelectSqlVO selectSqlVO=new SelectSqlVO();
                    selectSqlVO.setReturnFild(" , "+req.model+"."+req.options.labelText+" ");
                    selectSqlVO.setInnerSql(getLetSql(req));
                    selectSqlVOList.add(selectSqlVO);
                }
                paramlist.add(item);
                headEntityVOList.add(headEntityVO);
                break;
            case "switch":
                questionnaireSqlContentVO.getChartQuerySql().append(chartswitchFiledSql(req.options.filed));
                questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` tinyint(1)   DEFAULT NULL ");
                questionnaireSqlContentVO.getSelectListSql().append("<if test=\"" + req.options.filed + "!=null and " + req.options.filed + "!='' and " + req.options.filed + "!=''\">and maintable." + req.options.filed + " = #{" + req.options.filed + "} </if>");
                questionnaireSqlContentVO.getInsertFiledSql().append(",#{" + req.options.filed + "==true?1:0}");
                questionnaireSqlContentVO.getUpdateFiledSql().append(" ," + req.options.filed + "=#{" + req.options.filed + "==true?1:0}");
                questionnaireSqlContentVO.getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "!=null\">" + req.options.filed + "=#{" + req.options.filed + "},</if>");
                item.setVariabletype("常量");
                item.setRequired(false);
                item.setProperty(req.options.filed);
                item.setParamname(req.options.title);
                paramlist.add(item);
                headEntityVOList.add(headEntityVO);
                break;
            case "slider":
                questionnaireSqlContentVO.getChartQuerySql().append(chartNumberFiledSql(req.options.filed));
                questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` int(16)   DEFAULT NULL ");
                questionnaireSqlContentVO.getSelectListSql().append("<if test=\"" + req.options.filed + "!=null and " + req.options.filed + ".size()>0\">and maintable." + req.options.filed + " between  #{" + req.options.filed + "[0]} and #{" + req.options.filed + "[1]} </if>");
                questionnaireSqlContentVO.getInsertFiledSql().append(",#{" + req.options.filed + "}");
                questionnaireSqlContentVO.getUpdateFiledSql().append(" ," + req.options.filed + "=#{" + req.options.filed + "}");
                questionnaireSqlContentVO.getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "!=null\">" + req.options.filed + "=#{" + req.options.filed + "},</if>");
                item.setVariabletype("常量");
                item.setRequired(false);
                item.setProperty(req.options.filed);
                item.setParamname(req.options.title);
                paramlist.add(item);
                headEntityVOList.add(headEntityVO);
                break;
            case "colorpicker":
                questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` varchar(50)   DEFAULT NULL ");
                questionnaireSqlContentVO.getSelectListSql().append("<if test=\"" + req.options.filed + "!=null and " + req.options.filed + "!=''\">and maintable." + req.options.filed + " = #{" + req.options.filed + "} </if>");
                questionnaireSqlContentVO.getInsertFiledSql().append(",#{" + req.options.filed + "}");
                questionnaireSqlContentVO.getUpdateFiledSql().append(" ," + req.options.filed + "=#{" + req.options.filed + "}");
                questionnaireSqlContentVO.getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "!=null\">" + req.options.filed + "=#{" + req.options.filed + "},</if>");
                item.setVariabletype("常量");
                item.setRequired(false);
                item.setProperty(req.options.filed);
                item.setParamname(req.options.title);
                paramlist.add(item);
                headEntityVOList.add(headEntityVO);
                break;
            case "editor":
                questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` text   DEFAULT NULL ");
                questionnaireSqlContentVO.getInsertFiledSql().append(",#{" + req.options.filed + "}");
                questionnaireSqlContentVO.getUpdateFiledSql().append(" ," + req.options.filed + "=#{" + req.options.filed + "}");
                questionnaireSqlContentVO.getSelectListSql().append("<if test=\"" + req.options.filed + "!=null and " + req.options.filed + "!=''\">and maintable." + req.options.filed + " like CONCAT('%', #{" + req.options.filed + "},'%') </if>");
                questionnaireSqlContentVO.getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "!=null\">" + req.options.filed + "=#{" + req.options.filed + "},</if>");
                item.setVariabletype("常量");
                item.setRequired(false);
                item.setProperty(req.options.filed);
                item.setParamname(req.options.title);
                paramlist.add(item);
                headEntityVOList.add(headEntityVO);
                break;
            case "rate":
                questionnaireSqlContentVO.getChartQuerySql().append(chartNumberFiledSql(req.options.filed));
                questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` int(16)   DEFAULT NULL ");
                questionnaireSqlContentVO.getSelectListSql().append("<if test=\"" + req.options.filed + "!=null and " + req.options.filed + "!=''\">and maintable." + req.options.filed + " = #{" + req.options.filed + "} </if>");
                questionnaireSqlContentVO.getInsertFiledSql().append(",#{" + req.options.filed + "}");
                questionnaireSqlContentVO.getUpdateFiledSql().append(" ," + req.options.filed + "=#{" + req.options.filed + "}");
                questionnaireSqlContentVO.getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "!=null\">" + req.options.filed + "=#{" + req.options.filed + "},</if>");
                item.setVariabletype("常量");
                item.setRequired(false);
                item.setProperty(req.options.filed);
                item.setParamname(req.options.title);
                paramlist.add(item);
                headEntityVOList.add(headEntityVO);
                break;
            case "inputnumber":
                questionnaireSqlContentVO.getChartQuerySql().append(chartNumberFiledSql(req.options.filed));
                if(req.options.precision>0) {
                    questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "`  decimal(18, "+req.options.precision+")   DEFAULT NULL ");
                }else{
                    questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` int(16)   DEFAULT NULL ");
                }
                questionnaireSqlContentVO.getSelectListSql().append("<if test=\"" + req.options.filed + "!=null and " + req.options.filed + "!=''\">and maintable." + req.options.filed + " = #{" + req.options.filed + "} </if>");
                questionnaireSqlContentVO.getInsertFiledSql().append(",#{" + req.options.filed + "}");
                questionnaireSqlContentVO.getUpdateFiledSql().append(" ," + req.options.filed + "=#{" + req.options.filed + "}");
                questionnaireSqlContentVO.getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "!=null\">" + req.options.filed + "=#{" + req.options.filed + "},</if>");
                item.setVariabletype("常量");
                item.setRequired(false);
                item.setProperty(req.options.filed);
                item.setParamname(req.options.title);
                paramlist.add(item);
                headEntityVOList.add(headEntityVO);
                break;
            case "timepicker":
                questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` varchar(50)   DEFAULT NULL ");
                questionnaireSqlContentVO.getSelectListSql().append("<if test=\"" + req.options.filed + "!=null and " + req.options.filed + ".size()>0\">and maintable." + req.options.filed + " between #{" + req.options.filed + "[0]}  and #{" + req.options.filed + "[1]} </if>");
                questionnaireSqlContentVO.getInsertFiledSql().append(",#{" + req.options.filed + "}");
                questionnaireSqlContentVO.getUpdateFiledSql().append(" ," + req.options.filed + "=#{" + req.options.filed + "}");
                questionnaireSqlContentVO.getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "!=null\">" + req.options.filed + "=#{" + req.options.filed + "},</if>");
                item.setVariabletype("常量");
                item.setRequired(false);
                item.setProperty(req.options.filed);
                item.setParamname(req.options.title);
                paramlist.add(item);
                headEntityVOList.add(headEntityVO);
                break;
            case "upFilesComp":
            case "FileUpload":
            case "ImageUpload":
                questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` varchar(500)   DEFAULT NULL ");
                questionnaireSqlContentVO.getInsertFiledSql().append(",#{" + req.options.filed + "}");
                questionnaireSqlContentVO.getUpdateFiledSql().append(" ," + req.options.filed + "=#{" + req.options.filed + "}");
                questionnaireSqlContentVO.getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "!=null\">" + req.options.filed + "=#{" + req.options.filed + "},</if>");
                headEntityVOList.add(headEntityVO);
                break;
            case "user":
            case "department":
            case "organ":
            case "role":
                questionnaireSqlContentVO.getSelectListSql().append("<if test=\"" + req.options.filed + "!=null and " + req.options.filed + "!='' \">and maintable." + req.options.filed + " = #{" + req.options.filed + "}  </if>");
                if (req.options.getIsMulty() == true) {
                    questionnaireSqlContentVO.setColumnType("varchar(2000)");
                    questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` varchar(2000)   DEFAULT NULL ");
                    returnQuestionnairelist.add(getNewQuestionnaireContent());
                    returnQuestionnairelist.get(0).getSelectListSql().append("<if test=\"" + req.options.filed + "!=null and " + req.options.filed + ".text!=''\" >and maintable." + req.options.filed + "_text = #{" + req.options.filed + ".text}  </if>");
                    returnQuestionnairelist.get(0).getTableEidtSql().append("`" + req.options.filed + "_text` varchar(500)   DEFAULT NULL ");
                    returnQuestionnairelist.get(0).getInsertFiledSql().append(",#{" +  req.options.filed + "==null?\"\":" +    req.options.filed + ".text}");
                    returnQuestionnairelist.get(0).getUpdateFiledSql().append(" ," + req.options.filed + "_text=#{" +  req.options.filed + "==null?\"\":" +  req.options.filed + ".text}");
                    returnQuestionnairelist.get(0).setFiled(req.options.filed + "_text");
                    returnQuestionnairelist.get(0).getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "_text!=null\">" + req.options.filed + "_text=#{" + req.options.filed + "_text},</if>");
                    headEntityVO.setBindname(req.options.filed + "_text");
                    headEntityVO.setHeadname(req.options.title);
                    headEntityVO.setFromId(item.getFormId());
                    ParamBizFromVO item2 = new ParamBizFromVO();
                    item2.setVariabletype("常量");
                    item2.setRequired(false);
                    item2.setProperty(req.options.filed + "_text");
                    item2.setParamname(req.options.title + "text");
                    paramlist.add(item2);
                } else {
                    questionnaireSqlContentVO.setColumnType("varchar(200)");
                    questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` varchar(200)   DEFAULT NULL ");
                    returnQuestionnairelist.add(getNewQuestionnaireContent());
                    returnQuestionnairelist.get(0).getSelectListSql().append("<if test=\"" + req.options.filed + "!=null and " + req.options.filed + ".text!=''\" >and maintable." + req.options.filed + "_text = #{" + req.options.filed + ".text}  </if>");
                    returnQuestionnairelist.get(0).getTableEidtSql().append("`" + req.options.filed + "_text` varchar(50)   DEFAULT NULL ");
                    returnQuestionnairelist.get(0).getInsertFiledSql().append(",#{" +  req.options.filed + "==null?\"\":" +    req.options.filed + ".text}");
                    returnQuestionnairelist.get(0).getUpdateFiledSql().append(" ," + req.options.filed + "_text=#{" +  req.options.filed + "==null?\"\":" +  req.options.filed + ".text}");
                    returnQuestionnairelist.get(0).setFiled(req.options.filed + "_text");
                    returnQuestionnairelist.get(0).getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "_text!=null\">" + req.options.filed + "_text=#{" + req.options.filed + "_text},</if>");
                    headEntityVO.setBindname(req.options.filed + "_text");
                    headEntityVO.setHeadname(req.options.title);
                    headEntityVO.setFromId(item.getFormId());
                    ParamBizFromVO item2 = new ParamBizFromVO();
                    item2.setVariabletype("常量");
                    item2.setRequired(false);
                    item2.setProperty(req.options.filed + "_text");
                    item2.setParamname(req.options.title + "text");
                    paramlist.add(item2);
                }
                questionnaireSqlContentVO.getInsertFiledSql().append(",#{" + req.options.filed + "}");
                questionnaireSqlContentVO.getUpdateFiledSql().append(" ," + req.options.filed + "=#{" + req.options.filed + "}");
                questionnaireSqlContentVO.getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "!=null\">" + req.options.filed + "=#{" + req.options.filed + "},</if>");
                item.setVariabletype("常量");
                item.setRequired(false);
                item.setProperty(req.options.filed);
                item.setParamname(req.options.title);
                paramlist.add(item);
                headEntityVOList.add(headEntityVO);
                break;
            case "imgage":
                questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` varchar(500)   DEFAULT NULL ");
                questionnaireSqlContentVO.getInsertFiledSql().append(",#{" + req.options.filed + "}");
                questionnaireSqlContentVO.getUpdateFiledSql().append(" ," + req.options.filed + "=#{" + req.options.filed + "}");
                questionnaireSqlContentVO.getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "!=null\">" + req.options.filed + "=#{" + req.options.filed + "},</if>");
                headEntityVOList.add(headEntityVO);
                break;
            case "cityselector":
                questionnaireSqlContentVO.getSelectListSql().append("<if test=\"" + req.options.filed + "!=null and " + req.options.filed + "!='' \" >and maintable." + req.options.filed + " = #{" + req.options.filed + "}  </if>");
                questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` varchar(150)   DEFAULT NULL ");
                questionnaireSqlContentVO.getInsertFiledSql().append(",#{" + req.options.filed + "}");
                questionnaireSqlContentVO.getUpdateFiledSql().append(" ," + req.options.filed + "=#{" + req.options.filed + "}");
                questionnaireSqlContentVO.getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "!=null\">" + req.options.filed + "=#{" + req.options.filed + "},</if>");
                item.setVariabletype("常量");
                item.setRequired(false);
                item.setProperty(req.options.filed);
                item.setParamname(req.options.title);
                paramlist.add(item);
                headEntityVOList.add(headEntityVO);
                break;
            case "datarelevance":
                questionnaireSqlContentVO.getSelectListSql().append("<if test=\"" + req.options.filed + "!=null and " + req.options.filed + "!=''\" >and maintable." + req.options.filed + " = #{" + req.options.filed + "}  </if>");
                questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` varchar(50)   DEFAULT NULL ");
                questionnaireSqlContentVO.getInsertFiledSql().append(",#{" + req.options.filed + "}");
                questionnaireSqlContentVO.getUpdateFiledSql().append(" ," + req.options.filed + "=#{" + req.options.filed + "}");
                questionnaireSqlContentVO.getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "!=null\">" + req.options.filed + "=#{" + req.options.filed + "},</if>");
                item.setVariabletype("常量");
                item.setRequired(false);
                item.setProperty(req.options.filed);
                item.setParamname(req.options.title + "Id");
                item.setFormId(querybyFormId(req.options.sourceApi));
                paramlist.add(item);
                returnQuestionnairelist.add(getNewQuestionnaireContent());
                returnQuestionnairelist.get(0).getSelectListSql().append("<if test=\"" + req.options.filed + "_text!=null and " + req.options.filed + "_text!=''\" >and " + req.options.filed + "_text = #{" + req.options.filed + "_text}  </if>");
                returnQuestionnairelist.get(0).getTableEidtSql().append("`" + req.options.filed + "_text` varchar(250)   DEFAULT NULL ");
                returnQuestionnairelist.get(0).getInsertFiledSql().append(",#{" + req.options.filed + "_text}");
                returnQuestionnairelist.get(0).getUpdateFiledSql().append(" ," + req.options.filed + "_text=#{" + req.options.filed + "_text}");
                returnQuestionnairelist.get(0).setFiled(req.options.filed + "_text");
                returnQuestionnairelist.get(0).getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "_text!=null\">" + req.options.filed + "_text=#{" + req.options.filed + "_text},</if>");
                ParamBizFromVO item2 = new ParamBizFromVO();
                item2.setVariabletype("常量");
                item2.setRequired(false);
                item2.setProperty(req.options.filed + "_text");
                item2.setParamname(req.options.title + "text");
                paramlist.add(item2);
                headEntityVO.setBindname(req.options.filed + "_text");
                headEntityVO.setHeadname(req.options.title);
                headEntityVO.setFromId(item.getFormId());
                headEntityVOList.add(headEntityVO);
                break;
            case "fromlable":
                questionnaireSqlContentVO.getSelectListSql().append("<if test=\"" + req.options.filed + "!=null and " + req.options.filed + "!=''\" >and maintable." + req.options.filed + " like CONCAT('%',#{" + req.options.filed + "},'%')  </if>");
                questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` varchar(" + req.options.dataLength + ")   DEFAULT NULL ");
                questionnaireSqlContentVO.getInsertFiledSql().append(",#{" + req.options.filed + "}");
                questionnaireSqlContentVO.getUpdateFiledSql().append(" ," + req.options.filed + "=#{" + req.options.filed + "}");
                questionnaireSqlContentVO.getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "!=null\">" + req.options.filed + "=#{" + req.options.filed + "},</if>");
                headEntityVOList.add(headEntityVO);
                break;
            case "hidden":
                questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` varchar(" + req.options.dataLength + ")   DEFAULT NULL ");
                questionnaireSqlContentVO.getInsertFiledSql().append(",#{" + req.options.filed + "}");
                questionnaireSqlContentVO.getUpdateFiledSql().append(" ," + req.options.filed + "=#{" + req.options.filed + "}");
                questionnaireSqlContentVO.getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "!=null\">" + req.options.filed + "=#{" + req.options.filed + "},</if>");
                headEntityVOList.add(headEntityVO);
                break;
            case "autonumber":
                questionnaireSqlContentVO.getSelectListSql().append("<if test=\"" + req.options.filed + "!=null and " + req.options.filed + "!=''\" >and maintable." + req.options.filed + " like CONCAT('%', #{" + req.options.filed + "},'%')  </if>");
                questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` varchar(100)   DEFAULT NULL ");
                questionnaireSqlContentVO.getInsertFiledSql().append(",#{" + req.options.filed + "}");
                questionnaireSqlContentVO.getUpdateFiledSql().append(" ," + req.options.filed + "=#{" + req.options.filed + "}");
                questionnaireSqlContentVO.getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "!=null\">" + req.options.filed + "=#{" + req.options.filed + "},</if>");
                item.setVariabletype("常量");
                item.setRequired(false);
                item.setProperty(req.options.filed);
                item.setParamname(req.options.title);
                paramlist.add(item);
                headEntityVOList.add(headEntityVO);
                break;
            case "signname":
                questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` varchar(200)   DEFAULT NULL ");
                questionnaireSqlContentVO.getInsertFiledSql().append(",#{" + req.options.filed + "}");
                questionnaireSqlContentVO.getUpdateFiledSql().append(" ," + req.options.filed + "=#{" + req.options.filed + "}");
                questionnaireSqlContentVO.getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "!=null\">" + req.options.filed + "=#{" + req.options.filed + "},</if>");
                headEntityVOList.add(headEntityVO);
                break;
            case "locationmap":
                questionnaireSqlContentVO.getSelectListSql().append("<if test=\"" + req.options.filed + "!=null and " + req.options.filed + "!=''\" >and maintable." + req.options.filed + " like CONCAT('%', #{" + req.options.filed + "},'%')  </if>");
                questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` varchar(500)   DEFAULT NULL ");
                questionnaireSqlContentVO.getInsertFiledSql().append(",#{" + req.options.filed + "}");
                questionnaireSqlContentVO.getUpdateFiledSql().append(" ," + req.options.filed + "=#{" + req.options.filed + "}");
                questionnaireSqlContentVO.getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "!=null\">" + req.options.filed + "=#{" + req.options.filed + "},</if>");
                item.setVariabletype("常量");
                item.setRequired(false);
                item.setProperty(req.options.filed);
                item.setParamname(req.options.title);
                paramlist.add(item);
                headEntityVOList.add(headEntityVO);
                break;
            case "listview":
                TableChildInfoVO tableChildInfoVO = TableChildEidt(req.getOptions().getColumns(), req.options.controlId, req.options.title);
                questionnaireSqlContentVO.setHeadEntity(tableChildInfoVO.getHeadEntity());
                questionnaireSqlContentVO.getInsertChildTableSql().append("<for  forname=\"" + req.options.controlId + "\">" + tableChildInfoVO.getSaveInfoSql().toString() + "</for>");
                questionnaireSqlContentVO.getSaveInfoSql().append("<sql test=\"del" + req.options.controlId + "!=null and  del" + req.options.controlId + ".size()>0 \"> delete from  " + req.options.controlId + " where #{del" + req.options.controlId + "} like CONCAT('%',fid,'%')</sql>");
                break;
            default:
                questionnaireSqlContentVO = null;
                break;
        }
        if (!req.type.equals("listview") && questionnaireSqlContentVO != null) {
            questionnaireSqlContentVO.getTableEidtSql().append("COMMENT '" + req.options.title.replace("'", "''") + "' ");

        }
        returnQuestionnairelist.add(questionnaireSqlContentVO);
        return returnQuestionnairelist;
    }

    QuestionnaireSqlContentVO getNewQuestionnaireContent() {
        QuestionnaireSqlContentVO questionnaireSqlContentVO = new QuestionnaireSqlContentVO();
        questionnaireSqlContentVO.setInsertChildTableSql(new StringBuilder());
        questionnaireSqlContentVO.setInsertFiledSql(new StringBuilder());
        questionnaireSqlContentVO.setSaveInfoSql(new StringBuilder());
        questionnaireSqlContentVO.setSelectListSql(new StringBuilder());
        questionnaireSqlContentVO.setTableEidtSql(new StringBuilder());
        questionnaireSqlContentVO.setUpdateFiledSql(new StringBuilder());
        questionnaireSqlContentVO.setChartQuerySql(new StringBuilder());
        questionnaireSqlContentVO.setNewupdateFiledSql(new StringBuilder());
        return questionnaireSqlContentVO;
    }

    String chartFiledSql(boolean IsMulty, String filed, List<String> optionItemReqList) {
        StringBuilder sqlBuilder = new StringBuilder();
        if (optionItemReqList != null) {
            if (IsMulty) {
                for (String req : optionItemReqList) {
                    sqlBuilder.append(" sum(case when " + filed + " like '%" + req + "%' then 1 else 0 end) as '" + filed + req + "',");
                }
            } else {
                for (String req : optionItemReqList) {
                    sqlBuilder.append(" sum(case when " + filed + "='" + req + "'then 1 else 0 end) as '" + filed + req + "',");
                }
            }
        }
        if (sqlBuilder.length() > 0) {
            return sqlBuilder.toString().substring(0, sqlBuilder.toString().lastIndexOf(","));
        }
        return sqlBuilder.toString();
    }

    String chartswitchFiledSql(String filed) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(" sum(case when " + filed + " = 1 then 1 else 0 end) as '" + filed + "yes',");
        sqlBuilder.append(" sum(case when " + filed + " != 1 then 1 else 0 end) as '" + filed + "no'");
        return sqlBuilder.toString();
    }

    String chartNumberFiledSql(String filed) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(" sum(" + filed + ") as '" + filed + "sum',");
        sqlBuilder.append(" min(" + filed + ") as '" + filed + "min',");
        sqlBuilder.append(" max(" + filed + ") as '" + filed + "max',");
        sqlBuilder.append(" avg(" + filed + ") as '" + filed + "svg'");
        return sqlBuilder.toString();
    }

    /**
     * 子表控件数据保存
     *
     * @param formControlReqList 明细控件对象
     * @param parenttableName    父级表名
     */
    public TableChildInfoVO TableChildEidt(List<FormListViewControlReq> formControlReqList, String parenttableName, String listtitle) {
        final int rowCount = jdbcTemplate.queryForObject("SELECT count(*) totalcount FROM information_schema.TABLES WHERE table_schema =(SELECT DATABASE ()) and table_name='" + parenttableName + "'", Integer.class);
        if (rowCount == 0) {
            return CreateChildTable(formControlReqList, parenttableName, listtitle);
        } else {
            return AlterChildTable(formControlReqList, parenttableName, listtitle);
        }

    }
    public    String  getParamBizFromVO(List<ParamBizFromVO> paramlist){
        List<ParamBizFromVO> rootbizFromlist = new ArrayList<>();
        ParamBizFromVO bizFromVO = new ParamBizFromVO();
        bizFromVO.setParamname("Post参数");
        bizFromVO.setVariabletype("Object");
        bizFromVO.setIsfirst(true);
        bizFromVO.setProperty("body");
        bizFromVO.setChildren(paramlist);
        rootbizFromlist.add(bizFromVO);
        ParamBizFromVO bizFromVO1 = new ParamBizFromVO();
        bizFromVO1.setParamname("URL参数");
        bizFromVO1.setVariabletype("Object");
        bizFromVO1.setIsfirst(true);
        bizFromVO1.setProperty("$query");
        bizFromVO1.setChildren(new ArrayList<>());
        rootbizFromlist.add(bizFromVO1);
        ParamBizFromVO bizFromVO2 = new ParamBizFromVO();
        bizFromVO2.setParamname("header头部参数");
        bizFromVO2.setVariabletype("Object");
        bizFromVO2.setIsfirst(true);
        bizFromVO2.setProperty("$header");
        bizFromVO2.setChildren(new ArrayList<>());
        rootbizFromlist.add(bizFromVO2);
        return JSON.toJSONString(rootbizFromlist);
    }
    ///新增创建子表
    public TableChildInfoVO CreateChildTable(List<FormListViewControlReq> formControlReqList, String tableName, String listtitle) {
        String createTableSqlstr = "CREATE TABLE `" + tableName + "`  (\n" + getCreateTableDefaultField(true) + "   `fparentid` varchar(20) NOT NULL, ";
        StringBuffer createTableSql = new StringBuffer();
        StringBuffer selectListSql = new StringBuffer();
        StringBuffer updatesql = new StringBuffer();
        StringBuffer newupdatesql= new StringBuffer();
        StringBuffer batchupdatesql = new StringBuffer();
        updatesql.append("update `" + tableName + "` set " + getUpdateDefaultFieldAssignment(true));
        newupdatesql.append("update `" + tableName + "` <set>  " + getNewUpdateDefaultFieldAssignment(false));

        StringBuffer insertfiled = new StringBuffer();
        StringBuffer insertfiledValue = new StringBuffer();
        List<SelectSqlVO> selectSqlVOList=new ArrayList<SelectSqlVO>();
        selectListSql.append("select maintable.* ◐◐◐returnFild☼☼☼  from `" + tableName + "` as maintable ◐◐◐innerSql☼☼☼ <where> <if test=\"$tableNewId!=null and $tableNewId!=''\">and maintable.fparentid=#{$tableNewId} </if><if test=\"parentid!=null and parentid!=''\">and maintable.fparentid=#{parentid} </if><if test=\"fid!=null and fid!=''\">and maintable.fid=#{fid} </if><if test=\"$dataScope!=null and $dataScope!=''\">and ${$dataScope}</if>");
        List<HeadEntityVO> headEntityVOS = new ArrayList<>();
        StringBuilder chartQuerySql = new StringBuilder();
        List<ParamBizFromVO> paramlist = new ArrayList<>();
        for (FormListViewControlReq req : formControlReqList) {
            if (req.getOptions().getIssavedata()) {
                List<QuestionnaireSqlContentVO> questionnaireSqlContentVOlist = getChildQuestionnaireSqlContent(req, tableName, paramlist, headEntityVOS,selectSqlVOList);
                if (questionnaireSqlContentVOlist != null) {
                    for (QuestionnaireSqlContentVO questionnaireSqlContentVO : questionnaireSqlContentVOlist) {
                        if (questionnaireSqlContentVO != null) {
                            insertfiled.append("," + questionnaireSqlContentVO.getFiled());
                            createTableSql.append(questionnaireSqlContentVO.getTableEidtSql() + ",");
                            selectListSql.append(questionnaireSqlContentVO.getSelectListSql());
                            updatesql.append(questionnaireSqlContentVO.getUpdateFiledSql());
                            newupdatesql.append(questionnaireSqlContentVO.getNewupdateFiledSql());
                            insertfiledValue.append(questionnaireSqlContentVO.getInsertFiledSql());
                            if (!"".equals(questionnaireSqlContentVO.getChartQuerySql().toString())) {
                                if ("".equals(chartQuerySql.toString())) {
                                    chartQuerySql.append(questionnaireSqlContentVO.getChartQuerySql().toString());
                                } else {
                                    chartQuerySql.append("," + questionnaireSqlContentVO.getChartQuerySql().toString());
                                }
                            }
                        }
                    }
                }
            }
        }
        GetDefaultHeadEntity(headEntityVOS, paramlist);
        selectListSql.append(getWhereSql());
        selectListSql.append("</where>");
        selectListSql.append("<if test=\" sorter==null or sorter==''\"> order by flastupdatedate desc</if>");
        selectListSql.append("<if test=\" sorter!=null and sorter!=''\"> order by ${sorter}</if>");
        createTableSqlstr = createTableSqlstr + createTableSql.toString() + "   PRIMARY KEY (`fid`)) comment='" + fromname + "-" + listtitle + "';";
        String getEntitySql = "select *from " + tableName + " where fid=#{fid}";
        updatesql.append(" where fid=#{" + tableName + "[$index0].fid}");
        batchupdatesql.append(newupdatesql.toString());
        newupdatesql.append("</set> where fid=#{fid}");
        List<String> insertDefaultFieldAssignmentSql = getInsertDefaultFieldAssignment(false, true);
        String insertSql = "insert into " + tableName + "(" + insertDefaultFieldAssignmentSql.get(0) + ", fparentid " + insertfiled.toString() + ") values(" + insertDefaultFieldAssignmentSql.get(1) + " ,#{$tableNewId} " + insertfiledValue.toString() + ")";
        jdbcTemplate.execute(createTableSqlstr);
        R<String> r = pageService.addPage(tableName, fromname + "-" + listtitle);
        String selectSql=getNewSql(selectSqlVOList,selectListSql);
        SaveApiConfigReq apiParam = new SaveApiConfigReq();
        if (r.getCode() == 0) {
            apiParam.setMethodType(1);
            apiParam.setMethodName(fromname + "-" + listtitle + "_列表接口");
            apiParam.setMethodCode("querylist");
            apiParam.setPageId(r.getData());
            apiParam.setHeadList(JSON.toJSONString(headEntityVOS));
            apiParam.setRemarks(fromname + "-" + listtitle + "列表");
            apiParam.setIsSystem("1");

            apiParam.setParamConfig(getParamBizFromVO(paramlist));
            apiParam.setSqlContent(selectSql);
            apiParam.setDbId("business");
            apiParam.setIsSystem("1");
            formApiconfigService.saveApiConfigInfo(apiParam,headEntityVOS);
            if (!"".equals(chartQuerySql.toString())) {
                apiParam.setMethodType(2);
                apiParam.setMethodName(fromname + "-" + listtitle + "_统计接口");
                apiParam.setMethodCode("queryChartList");
                apiParam.setPageId(r.getData());
                apiParam.setRemarks(fromname + "-" + listtitle + "统计图查询sql");
                apiParam.setSqlContent("select " + chartQuerySql.toString() + ",count(*) as " + tableName + "total from " + tableName);
                apiParam.setDbId("business");
                apiParam.setIsSystem("1");
                formApiconfigService.saveApiConfigInfo(apiParam);
            }

            apiParam.setParamConfig(getParamBizFromVO(paramlist));
            apiParam.setMethodType(3);
            apiParam.setMethodName(fromname+"_批量修改接口");
            apiParam.setMethodCode("batchupdateInfo");
            apiParam.setPageId(r.getData());
            apiParam.setRemarks(fromname + "_批量修改接口");
            apiParam.setSqlContent("<for forname=\"ids\"><sql>"+batchupdatesql.toString()+"</set>  where fid=#{ids[$index0]}</sql></for>");
            apiParam.setDbId("business");
            apiParam.setIsSystem("1");
            formApiconfigService.saveApiConfigInfo(apiParam);

            ParamBizFromVO item = new ParamBizFromVO();
            item.setRequired(true);
            item.setVariabletype("常量");
            item.setProperty("fid");
            item.setParamname("主键ID");
            paramlist.add(item);
            apiParam.setParamConfig(getParamBizFromVO(paramlist));
            apiParam.setMethodType(2);
            apiParam.setMethodName(fromname + "-" + listtitle + "_根据Id修改接口");
            apiParam.setMethodCode("updateInfo");
            apiParam.setPageId(r.getData());
            apiParam.setRemarks(fromname + "-" + listtitle + "根据Id修改接口");
            apiParam.setSqlContent(newupdatesql.toString());
            apiParam.setDbId("business");
            apiParam.setIsSystem("1");
            formApiconfigService.saveApiConfigInfo(apiParam);
            List<ParamBizFromVO> paramlist1 =  new ArrayList<>();
            paramlist1.add(item);
            apiParam.setParamConfig(getParamBizFromVO(paramlist1));
            apiParam.setMethodType(2);
            apiParam.setMethodName(fromname + "-" + listtitle + "_根据Id删除接口");
            apiParam.setMethodCode("deleteInfo");
            apiParam.setPageId(r.getData());
            apiParam.setRemarks(fromname + "-" + listtitle + "根据Id删除");
            apiParam.setSqlContent("delete from " + tableName + " where fid=#{fid}");
            apiParam.setDbId("business");
            apiParam.setIsSystem("1");
            formApiconfigService.saveApiConfigInfo(apiParam);
            apiParam.setMethodType(5);
            apiParam.setMethodName(fromname + "-" + listtitle + "_根据Id获取接口");
            apiParam.setMethodCode("getDataInfo");
            apiParam.setPageId(r.getData());
            apiParam.setRemarks(fromname + "-" + listtitle + "根据Id获取信息");
            apiParam.setSqlContent(getEntitySql);
            apiParam.setDbId("business");
            apiParam.setIsSystem("1");
            formApiconfigService.saveApiConfigInfo(apiParam);
            List<ParamBizFromVO> paramlist2 =  new ArrayList<>();
            ParamBizFromVO item2 = new ParamBizFromVO();
            item2.setRequired(true);
            item2.setVariabletype("Array");
            item2.setProperty("ids");
            item2.setParamname("id");
            paramlist2.add(item2);
            apiParam.setParamConfig(getParamBizFromVO(paramlist2));
            apiParam.setMethodType(3);
            apiParam.setMethodName(fromname+"_批量删除接口");
            apiParam.setMethodCode("batchDeleteInfo");
            apiParam.setPageId(r.getData());
            apiParam.setRemarks(fromname + "_批量删除");
            apiParam.setSqlContent("<for forname=\"ids\"><sql>delete from " + tableName + " where fid=#{ids[$index0]}</sql></for>");
            apiParam.setDbId("business");
            apiParam.setIsSystem("1");
            formApiconfigService.saveApiConfigInfo(apiParam);

//            apiParam.setMethodType(ApiTypeEnum.OPERATE);
//            apiParam.setMethodName("数据保存接口");
//            apiParam.setMethodCode("SaveDataInfo");
//            apiParam.setPageId(r.getData());
//            apiParam.setRemarks("数据保存接口");
//            apiParam.setSqlContent(insertSql);
//            tappApiconfigService.saveApiConfigInfo(apiParam);
        }
        StringBuffer saveInfoSql = new StringBuffer();
        saveInfoSql.append("<sql param=\"" + tableName + "[$index0].$totalcount\"> select count(*) rowcount from " + tableName + " where fid=#{" + tableName + "[$index0].fid} </sql>");
        saveInfoSql.append("<sql test=\"" + tableName + "[$index0].$totalcount!=null and " + tableName + "[$index0].$totalcount[0].rowcount>0 \"> " + updatesql + " </sql>");
        saveInfoSql.append("<sql test=\"" + tableName + "[$index0].$totalcount==null or " + tableName + "[$index0].$totalcount[0].rowcount==0 \"> " + insertSql + " </sql>");
        TableChildInfoVO tableChildInfoVO = new TableChildInfoVO();
        tableChildInfoVO.setHeadEntity(headEntityVOS);
        tableChildInfoVO.setSaveInfoSql(saveInfoSql);
        return tableChildInfoVO;
    }
    String getNewSql(List<SelectSqlVO> selectSqlVOList,StringBuffer selectSqlBuilder) {
        String selectSql=selectSqlBuilder.toString();
        StringBuffer innerSqlBuilder=new StringBuffer();
        StringBuffer returnFildBuilder=new StringBuffer();
        selectSqlVOList.forEach(ff->{
            if(returnFildBuilder.length()==0) {
                returnFildBuilder.append(ff.getReturnFild());
                innerSqlBuilder.append(ff.getInnerSql());
            }else{
                returnFildBuilder.append(","+ff.getReturnFild());
                innerSqlBuilder.append(ff.getInnerSql());
            }

        });
        selectSql=  selectSql.replaceAll("◐◐◐returnFild☼☼☼",returnFildBuilder.toString());
        selectSql= selectSql.replaceAll("◐◐◐innerSql☼☼☼",innerSqlBuilder.toString());
       return selectSql;
    }
    ///新增修改子表结构
    public TableChildInfoVO AlterChildTable(List<FormListViewControlReq> formControlReqList, String tableName, String listtitle) {
        List<ColumnInfoVO> columnNameList = jdbcTemplate.query("select column_name as columnname ,column_type as columntype,column_comment as columnComment  from information_schema.columns where table_schema =(SELECT DATABASE ()) and table_name='" + tableName + "'", new Object[]{}, new BeanPropertyRowMapper<>(ColumnInfoVO.class));
        List<String> newColummNameList = getAllCreateField(true);
        newColummNameList.add("fparentid");
        List<String> columnNameStrList = columnNameList.stream().map(ee -> ee.getColumnname()).collect(Collectors.toList());
        if (!columnNameStrList.contains("fparentid")) {
            jdbcTemplate.execute("ALTER TABLE " + tableName + " ADD COLUMN `fparentid` varchar(20) NOT NULL");
        }
        ///获取删除表列字段集合
        jdbcTemplate.execute("ALTER TABLE " + tableName + " comment '" + fromname + "-" + listtitle + "'; ");
        StringBuffer selectListSql = new StringBuffer();
        StringBuffer updatesql = new StringBuffer();
        StringBuffer newupdatesql= new StringBuffer();
        StringBuffer batchupdatesql= new StringBuffer();
        updatesql.append("update `" + tableName + "` set " + getUpdateDefaultFieldAssignment(true));
        newupdatesql.append("update `" + tableName + "` set " + getNewUpdateDefaultFieldAssignment(true));
        StringBuffer insertfiled = new StringBuffer();
        StringBuffer insertfiledValue = new StringBuffer();
        List<SelectSqlVO> selectSqlVOList=new ArrayList<SelectSqlVO>();
        selectListSql.append("select maintable.* ◐◐◐returnFild☼☼☼  from `" + tableName + "`  as maintable ◐◐◐innerSql☼☼☼ <where> <if test=\"$tableNewId!=null and $tableNewId!=''\">and maintable.fparentid=#{$tableNewId} </if><if test=\"parentid!=null and parentid!=''\">and maintable.fparentid=#{parentid} </if><if test=\"fid!=null and fid!=''\">and maintable.fid=#{fid} </if><if test=\"$dataScope!=null and $dataScope!=''\">and ${$dataScope}</if>");
        ///对表结构进行修改
        List<HeadEntityVO> headEntityVOS = new ArrayList<>();
        StringBuilder chartQuerySql = new StringBuilder();
        List<ParamBizFromVO> paramlist = new ArrayList<>();
        for (FormListViewControlReq req : formControlReqList) {
            if (req.getOptions().getIssavedata()) {
                List<QuestionnaireSqlContentVO> questionnaireSqlContentVOlist = getChildQuestionnaireSqlContent(req, tableName, paramlist, headEntityVOS,selectSqlVOList);
                if (questionnaireSqlContentVOlist != null) {
                    for (QuestionnaireSqlContentVO questionnaireSqlContentVO : questionnaireSqlContentVOlist) {
                        if (questionnaireSqlContentVO == null) {
                            continue;
                        }
                        if (!newColummNameList.contains(questionnaireSqlContentVO.getFiled())) {
                            newColummNameList.add(questionnaireSqlContentVO.getFiled());
                        }
                        if (questionnaireSqlContentVO != null) {
                            insertfiled.append("," + questionnaireSqlContentVO.getFiled());
                            StringBuilder alterTableSql = new StringBuilder();
                            alterTableSql.append("ALTER TABLE " + tableName);
                            Optional<ColumnInfoVO> columnInfo = columnNameList.stream().filter(ee -> ee.getColumnname().equals(questionnaireSqlContentVO.getFiled())).findFirst();
                            if (!columnInfo.equals(Optional.empty())) {
                                alterTableSql.append("  MODIFY  ");
                            } else {
                                alterTableSql.append("  ADD  ");
                            }
                            alterTableSql.append(questionnaireSqlContentVO.getTableEidtSql());
                            if (columnInfo.equals(Optional.empty()) || (!columnInfo.equals(Optional.empty()) && !columnInfo.get().getColumntype().equals(questionnaireSqlContentVO.getColumnType()))) {
                                jdbcTemplate.execute(alterTableSql.toString());
                            }
                            selectListSql.append(questionnaireSqlContentVO.getSelectListSql());
                            updatesql.append(questionnaireSqlContentVO.getUpdateFiledSql());
                            newupdatesql.append(questionnaireSqlContentVO.getNewupdateFiledSql());
                            insertfiledValue.append(questionnaireSqlContentVO.getInsertFiledSql());
                            if (!"".equals(questionnaireSqlContentVO.getChartQuerySql().toString())) {
                                if ("".equals(chartQuerySql.toString())) {
                                    chartQuerySql.append(questionnaireSqlContentVO.getChartQuerySql().toString());
                                } else {
                                    chartQuerySql.append("," + questionnaireSqlContentVO.getChartQuerySql().toString());
                                }
                            }
                        }
                    }
                }
            }
        }
        List<ColumnInfoVO> delColumnList = columnNameList.stream().filter(ee -> !newColummNameList.contains(ee.getColumnname())).collect(Collectors.toList());
        delColumnList.forEach(ee -> {
            jdbcTemplate.execute("ALTER TABLE " + tableName + " DROP COLUMN " + ee.getColumnname() + " ; ");
        });
        List<String> oldcolumnlist = columnNameList.stream().map(ee -> ee.columnname).collect(Collectors.toList());
        List<DefaultFieldInfoVO> addfieldList = defaultFieldlist.stream().filter(ee -> true == ee.getIsPublic() && !oldcolumnlist.contains(ee.getFieldName())).collect(Collectors.toList());
        addfieldList.forEach(ee -> {
            jdbcTemplate.execute("ALTER TABLE " + tableName + " add COLUMN " + ee.getFieldName() + "  " + ee.getFieldType() + "   COMMENT '" + ee.getDescribe() + "'; ");

        });
        GetDefaultHeadEntity(headEntityVOS, paramlist);
        selectListSql.append(getWhereSql());
        selectListSql.append("</where>");
        selectListSql.append("<if test=\" sorter==null or sorter==''\"> order by flastupdatedate desc</if>");
        selectListSql.append("<if test=\" sorter!=null and sorter!=''\"> order by ${sorter}</if>");
        updatesql.append(" where fid=#{" + tableName + "[$index0].fid}");
        batchupdatesql.append(newupdatesql.toString());
        newupdatesql.append("</set> where fid=#{fid}");
        String getEntitySql = "select *from " + tableName + " where fid=#{fid}";
        List<String> insertDefaultFieldAssignmentSql = getInsertDefaultFieldAssignment(false, true);
        String insertSql = "insert into " + tableName + "(" + insertDefaultFieldAssignmentSql.get(0) + ",fparentid  " + insertfiled.toString() + ") values(" + insertDefaultFieldAssignmentSql.get(1) + " ,#{$tableNewId}" + insertfiledValue.toString() + ")";
        R<String> r = pageService.addPage(tableName, fromname + "-" + listtitle);
        String selectSql =getNewSql(selectSqlVOList,selectListSql);
        SaveApiConfigReq apiParam = new SaveApiConfigReq();
        if (r.getCode() == 0) {
            apiParam.setMethodType(1);
            apiParam.setMethodName(fromname + "-" + listtitle +"_查询列表接口");
            apiParam.setMethodCode("querylist");
            apiParam.setPageId(r.getData());
            apiParam.setIsSystem("1");
//            apiParam.setParamConfig(JSON.toJSONString(paramlist) );
            apiParam.setHeadList(JSON.toJSONString(headEntityVOS));
            apiParam.setRemarks(fromname + "-" + listtitle + "列表");

            apiParam.setParamConfig(getParamBizFromVO(paramlist));
            apiParam.setSqlContent(selectSql);
            apiParam.setDbId("business");
            apiParam.setIsSystem("1");
            formApiconfigService.saveApiConfigInfo(apiParam,headEntityVOS);
            if (!"".equals(chartQuerySql.toString())) {
                apiParam.setMethodType(2);
                apiParam.setMethodName(fromname + "-" + listtitle +"_统计接口");
                apiParam.setMethodCode("queryChartList");
                apiParam.setPageId(r.getData());
                apiParam.setRemarks(fromname + "-" + listtitle + "统计图查询sql");
                apiParam.setSqlContent("select " + chartQuerySql.toString() + ",count(*) as " + tableName + "total from " + tableName);
                apiParam.setDbId("business");
                apiParam.setIsSystem("1");
                formApiconfigService.saveApiConfigInfo(apiParam);
            }
            apiParam.setParamConfig(getParamBizFromVO(paramlist));
            apiParam.setMethodType(3);
            apiParam.setMethodName(fromname+"_批量修改接口");
            apiParam.setMethodCode("batchupdateInfo");
            apiParam.setPageId(r.getData());
            apiParam.setRemarks(fromname + "_批量修改接口");
            apiParam.setSqlContent("<for forname=\"ids\"><sql>"+batchupdatesql.toString()+"</set>  where fid=#{ids[$index0]}</sql></for>");
            apiParam.setDbId("business");
            apiParam.setIsSystem("1");
            formApiconfigService.saveApiConfigInfo(apiParam);

            ParamBizFromVO item = new ParamBizFromVO();
            item.setRequired(true);
            item.setVariabletype("常量");
            item.setProperty("fid");
            item.setParamname("主键ID");
            paramlist.add(item);
            apiParam.setParamConfig(getParamBizFromVO(paramlist));
            apiParam.setMethodType(2);
            apiParam.setMethodName(fromname + "-" + listtitle + "_根据Id修改接口");
            apiParam.setMethodCode("updateInfo");
            apiParam.setPageId(r.getData());
            apiParam.setRemarks(fromname + "-" + listtitle + "根据Id修改接口");
            apiParam.setSqlContent(newupdatesql.toString());
            apiParam.setDbId("business");
            apiParam.setIsSystem("1");
            formApiconfigService.saveApiConfigInfo(apiParam);

            List<ParamBizFromVO> paramlist1 =  new ArrayList<>();
            paramlist1.add(item);
            apiParam.setParamConfig(getParamBizFromVO(paramlist1));
            apiParam.setMethodType(2);
            apiParam.setMethodName(fromname + "-" + listtitle + "_根据Id删除接口");
            apiParam.setMethodCode("deleteInfo");
            apiParam.setPageId(r.getData());
            apiParam.setRemarks(fromname + "-" + listtitle + "根据Id删除");
            apiParam.setSqlContent("delete from " + tableName + " where fid=#{fid}");
            apiParam.setDbId("business");
            apiParam.setIsSystem("1");
            formApiconfigService.saveApiConfigInfo(apiParam);

            apiParam.setParamConfig(getParamBizFromVO(paramlist1));
            apiParam.setMethodType(5);
            apiParam.setMethodName(fromname + "-" + listtitle +"_根据Id获取接口");
            apiParam.setMethodCode("getDataInfo");
            apiParam.setPageId(r.getData());
            apiParam.setRemarks(fromname + "-" + listtitle + "根据Id获取信息");
            apiParam.setSqlContent(getEntitySql);
            apiParam.setDbId("business");
            apiParam.setIsSystem("1");
            formApiconfigService.saveApiConfigInfo(apiParam);
            List<ParamBizFromVO> paramlist2 =  new ArrayList<>();
            ParamBizFromVO item2 = new ParamBizFromVO();
            item2.setRequired(true);
            item2.setVariabletype("Array");
            item2.setProperty("ids");
            item2.setParamname("id");
            paramlist2.add(item2);
            apiParam.setParamConfig(getParamBizFromVO(paramlist2));
            apiParam.setMethodType(3);
            apiParam.setMethodName(fromname+"_批量删除接口");
            apiParam.setMethodCode("batchDeleteInfo");
            apiParam.setPageId(r.getData());
            apiParam.setRemarks(fromname + "_批量删除");
            apiParam.setSqlContent("<for forname=\"ids\"><sql>delete from " + tableName + " where fid=#{ids[$index0]}</sql></for>");
            apiParam.setDbId("business");
            apiParam.setIsSystem("1");
            formApiconfigService.saveApiConfigInfo(apiParam);
        }
        StringBuffer saveInfoSql = new StringBuffer();
        saveInfoSql.append("<sql param=\"" + tableName + "[$index0].$totalcount\"> select count(*) rowcount from " + tableName + " where fid=#{" + tableName + "[$index0].fid} </sql>");
        saveInfoSql.append("<sql test=\"" + tableName + "[$index0].$totalcount!=null and " + tableName + "[$index0].$totalcount[0].rowcount>0 \"> " + updatesql + " </sql>");
        saveInfoSql.append("<sql test=\"" + tableName + "[$index0].$totalcount==null or " + tableName + "[$index0].$totalcount[0].rowcount==0 \"> " + insertSql + " </sql>");
        TableChildInfoVO tableChildInfoVO = new TableChildInfoVO();
        tableChildInfoVO.setHeadEntity(headEntityVOS);
        tableChildInfoVO.setSaveInfoSql(saveInfoSql);
        return tableChildInfoVO;
    }

    List<QuestionnaireSqlContentVO> getChildQuestionnaireSqlContent(FormListViewControlReq req, String tableName, List<ParamBizFromVO> paramlist, List<HeadEntityVO> headEntityVOList, List<SelectSqlVO> selectSqlVOList) {
        List<QuestionnaireSqlContentVO> returnQuestionnairelist = new ArrayList<>();
        QuestionnaireSqlContentVO questionnaireSqlContentVO = getNewQuestionnaireContent();
        questionnaireSqlContentVO.setFiled(req.options.filed);
        HeadEntityVO headEntityVO = new HeadEntityVO();
        headEntityVO.setBindname(req.options.filed);
        headEntityVO.setHeadname(req.options.title);
        ParamBizFromVO item = new ParamBizFromVO();
        switch (req.type) {
            case "input":
                questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` varchar(" + req.options.dataLength + ")   DEFAULT NULL ");
                questionnaireSqlContentVO.getSelectListSql().append("<if test=\"" + req.options.filed + "!=null and " + req.options.filed + "!=''\">and maintable." + req.options.filed + " like CONCAT('%',#{" + req.options.filed + "},'%') </if>");
                questionnaireSqlContentVO.getInsertFiledSql().append(",#{" + tableName + "[$index0]." + req.options.filed + "}");
                questionnaireSqlContentVO.getUpdateFiledSql().append(" ," + req.options.filed + "=#{" + tableName + "[$index0]." + req.options.filed + "}");
                questionnaireSqlContentVO.getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "!=null\">" + req.options.filed + "=#{" + req.options.filed + "},</if>");

                item.setVariabletype("常量");
                item.setRequired(false);
                item.setProperty(req.options.filed);
                item.setParamname(req.options.title);
                paramlist.add(item);
                headEntityVOList.add(headEntityVO);
                break;
            case "textarea":
                questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` text   DEFAULT NULL ");
                questionnaireSqlContentVO.getInsertFiledSql().append(",#{" + tableName + "[$index0]." + req.options.filed + "}");
                questionnaireSqlContentVO.getUpdateFiledSql().append(" ," + req.options.filed + "=#{" + tableName + "[$index0]." + req.options.filed + "}");
                questionnaireSqlContentVO.getSelectListSql().append("<if test=\"" + req.options.filed + "!=null and " + req.options.filed + "!=''\">and maintable." + req.options.filed + " like CONCAT('%',#{" + req.options.filed + "},'%') </if>");
                questionnaireSqlContentVO.getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "!=null\">" + req.options.filed + "=#{" + req.options.filed + "},</if>");
                item.setVariabletype("常量");
                item.setRequired(false);
                item.setProperty(req.options.filed);
                item.setParamname(req.options.title);
                paramlist.add(item);
                headEntityVOList.add(headEntityVO);
                break;
            case "select":
                if (req.options.getIsMulty() != null && req.options.getIsMulty().equals(true)) {
                    questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` varchar(500)   DEFAULT NULL ");
                    questionnaireSqlContentVO.setColumnType("varchar(500)");
                    questionnaireSqlContentVO.getSelectListSql().append("<if test=\"" + req.options.filed + "!=null and " + req.options.filed + "!=''\">and maintable." + req.options.filed + "like CONCAT('%',#{" + req.options.filed + "},'%') </if>");
                } else {
                    questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` varchar(" + req.options.dataLength + ")   DEFAULT NULL ");
                    questionnaireSqlContentVO.setColumnType("varchar(" + req.options.dataLength + ")");
                    questionnaireSqlContentVO.getSelectListSql().append("<if test=\"" + req.options.filed + "!=null and " + req.options.filed + "!=''\">and maintable." + req.options.filed + " = #{" + req.options.filed + "} </if>");
                }
                questionnaireSqlContentVO.getChartQuerySql().append(chartFiledSql(req.options.getIsMulty(), req.options.filed, req.options.getOptions()));
                questionnaireSqlContentVO.getInsertFiledSql().append(",#{" + tableName + "[$index0]." + req.options.filed + "}");
                questionnaireSqlContentVO.getUpdateFiledSql().append(" ," + req.options.filed + "=#{" + tableName + "[$index0]." + req.options.filed + "}");
                questionnaireSqlContentVO.getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "!=null\">" + req.options.filed + "=#{" + req.options.filed + "},</if>");
                item.setVariabletype("常量");
                item.setRequired(false);
                item.setProperty(req.options.filed);
                item.setParamname(req.options.title);
                if ("1".equals(req.options.isRemote)) {
                    headEntityVO.setBindname(req.options.labelText);
                    item.setFormId(querybyFormId(req.options.sourceApi));
                    SelectSqlVO selectSqlVO=new SelectSqlVO();
                    selectSqlVO.setReturnFild(" , "+req.model+"."+req.options.labelText+" ");
                    selectSqlVO.setInnerSql(getLetSql(req));
                    selectSqlVOList.add(selectSqlVO);
                }
                paramlist.add(item);
                headEntityVOList.add(headEntityVO);
                break;
            case "date":
                switch (req.options.modeltype) {
                    case "date":
                        if("yyyy-MM-DD HH:mm:ss".equals(req.options.format)) {
                            questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` datetime(0)   DEFAULT NULL ");
                        }else if("yyyy-MM-DD".equals(req.options.format)){
                            questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` date   DEFAULT NULL ");
                        }else if("yyyy".equals(req.options.format)){
                            questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` varchar(50)   DEFAULT NULL ");
                        }else if("yyyy-MM".equals(req.options.format)){
                            questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` varchar(50)   DEFAULT NULL ");
                        }else{
                            questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` datetime(0)   DEFAULT NULL ");
                        }
                        questionnaireSqlContentVO.getSelectListSql().append("<if test=\"" + req.options.filed + "!=null and " + req.options.filed + ".size()>0\">and maintable." + req.options.filed + " between #{" + req.options.filed + "[0]} and #{" + req.options.filed + "[1]}  </if>");
                        break;
                    case "year":
                        questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` varchar(50)   DEFAULT NULL ");
                        questionnaireSqlContentVO.getSelectListSql().append("<if test=\"" + req.options.filed + "!=null and " + req.options.filed + "!=''\">and date_format(maintable." + req.options.filed + ",'%Y') =  #{" + req.options.filed + "} </if>");
                        break;
                    case "month":
                        questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` varchar(50)   DEFAULT NULL ");
                        questionnaireSqlContentVO.getSelectListSql().append("<if test=\"" + req.options.filed + "!=null and " + req.options.filed + ".size()>0\">and date_format(maintable." + req.options.filed + ",'%Y%m') between REPLACE(#{" + req.options.filed + "[0]},'-','') and REPLACE(#{" + req.options.filed + "[1]},'-','')  </if>");
                        break;
                    case "week":
                        questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "`  varchar(50)   DEFAULT NULL ");
                        questionnaireSqlContentVO.getSelectListSql().append("<if test=\"" + req.options.filed + "!=null and " + req.options.filed + ".size()>0\">and maintable." + req.options.filed + " between #{" + req.options.filed + "[0]} and #{" + req.options.filed + "[1]}  </if>");
                        break;
                    case "daterange":
                    case "monthrange":
                    case "datetimerange":
                        questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "`  varchar(100)   DEFAULT NULL ");
                        returnQuestionnairelist.add(getNewQuestionnaireContent());
                        returnQuestionnairelist.get(0).getTableEidtSql().append("`" + req.options.filed + "_start` datetime(0)    DEFAULT NULL ");
                        returnQuestionnairelist.get(0).getInsertFiledSql().append(",#{" + tableName + "[$index0]." +  req.options.filed + "==null&&" + tableName + "[$index0]." +  req.options.filed + ">=0?\"\":" + tableName + "[$index0]." +  req.options.filed + "[0]}");
                        returnQuestionnairelist.get(0).getUpdateFiledSql().append(" ," + req.options.filed + "_start=#{" + tableName + "[$index0]." +  req.options.filed + "==null&&" + tableName + "[$index0]." +  req.options.filed + ".size()>=0?\"\":" + tableName + "[$index0]." +  req.options.filed + "[0]}");
                        returnQuestionnairelist.get(0).getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "_start!=null\">" + req.options.filed + "_start=#{" + req.options.filed + "_start},</if>");

                        returnQuestionnairelist.get(0).setFiled(req.options.filed + "_start");
                        returnQuestionnairelist.add(getNewQuestionnaireContent());
                        returnQuestionnairelist.get(1).getTableEidtSql().append("`" + req.options.filed + "_end` datetime(0)    DEFAULT NULL ");
                        returnQuestionnairelist.get(1).getInsertFiledSql().append(",#{" + tableName + "[$index0]." +  req.options.filed + "==null&&" + tableName + "[$index0]." +  req.options.filed + ".size()>=1?\"\":" + tableName + "[$index0]." +  req.options.filed + "[1]}");
                        returnQuestionnairelist.get(1).getUpdateFiledSql().append(" ," + req.options.filed + "_end=#{" + tableName + "[$index0]." +  req.options.filed + "==null&&" + tableName + "[$index0]." +  req.options.filed + ".size()>=0?\"\":" +  req.options.filed + "[1]}");
                        returnQuestionnairelist.get(1).setFiled(req.options.filed + "_end");
                        returnQuestionnairelist.get(1).getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "_end!=null\">" + req.options.filed + "_end=#{" + req.options.filed + "_end},</if>");
                        break;
                    default:
                        questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` varchar(50)   DEFAULT NULL ");
                        questionnaireSqlContentVO.getSelectListSql().append("<if test=\"" + req.options.filed + "!=null and " + req.options.filed + "!=''\">and maintable." + req.options.filed + " = #{" + req.options.filed + "} </if>");
                        break;

                }
                questionnaireSqlContentVO.getInsertFiledSql().append(",#{" + tableName + "[$index0]." + req.options.filed + "}");
                questionnaireSqlContentVO.getUpdateFiledSql().append(" ," + req.options.filed + "=#{" + tableName + "[$index0]." + req.options.filed + "}");
                questionnaireSqlContentVO.getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "!=null\">" + req.options.filed + "=#{" + req.options.filed + "},</if>");

                headEntityVOList.add(headEntityVO);
                break;
            case "radio":
                questionnaireSqlContentVO.getChartQuerySql().append(chartFiledSql(false, req.options.filed, req.options.getOptions()));
                questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` varchar(" + req.options.dataLength + ")   DEFAULT NULL ");
                questionnaireSqlContentVO.getSelectListSql().append("<if test=\"" + req.options.filed + "!=null and " + req.options.filed + "!=''\">and maintable." + req.options.filed + " = #{" + req.options.filed + "} </if>");
                questionnaireSqlContentVO.getInsertFiledSql().append(",#{" + tableName + "[$index0]." + req.options.filed + "}");
                questionnaireSqlContentVO.getUpdateFiledSql().append(" ," + req.options.filed + "=#{" + tableName + "[$index0]." + req.options.filed + "}");
                questionnaireSqlContentVO.getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "!=null\">" + req.options.filed + "=#{" + req.options.filed + "},</if>");
                item.setVariabletype("常量");
                item.setRequired(false);
                item.setProperty(req.options.filed);
                item.setParamname(req.options.title);
                if ("1".equals(req.options.isRemote)) {
                    headEntityVO.setBindname(req.options.labelText);
                    item.setFormId(querybyFormId(req.options.sourceApi));
                    SelectSqlVO selectSqlVO=new SelectSqlVO();
                    selectSqlVO.setReturnFild(" , "+req.model+"."+req.options.labelText+" ");
                    selectSqlVO.setInnerSql(getLetSql(req));
                    selectSqlVOList.add(selectSqlVO);
                }
                paramlist.add(item);
                headEntityVOList.add(headEntityVO);
                break;

            case "checkbox":
                questionnaireSqlContentVO.getChartQuerySql().append(chartFiledSql(true, req.options.filed, req.options.getOptions()));
                questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` varchar(500)   DEFAULT NULL ");
                questionnaireSqlContentVO.getSelectListSql().append("<if test=\"" + req.options.filed + "!=null and " + req.options.filed + "!=''\">and maintable." + req.options.filed + " like CONCAT('%',#{" + req.options.filed + "},'%') </if>");
                questionnaireSqlContentVO.getInsertFiledSql().append(",#{" + tableName + "[$index0]." + req.options.filed + "}");
                questionnaireSqlContentVO.getUpdateFiledSql().append(" ," + req.options.filed + "=#{" + tableName + "[$index0]." + req.options.filed + "}");
                questionnaireSqlContentVO.getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "!=null\">" + req.options.filed + "=#{" + req.options.filed + "},</if>");
                item.setVariabletype("常量");
                item.setRequired(false);
                item.setProperty(req.options.filed);
                item.setParamname(req.options.title);
                if ("1".equals(req.options.isRemote)) {
                    headEntityVO.setBindname(req.options.labelText);
                    item.setFormId(querybyFormId(req.options.sourceApi));
                    SelectSqlVO selectSqlVO=new SelectSqlVO();
                    selectSqlVO.setReturnFild(" , "+req.model+"."+req.options.labelText+" ");
                    selectSqlVO.setInnerSql(getLetSql(req));
                    selectSqlVOList.add(selectSqlVO);
                }
                paramlist.add(item);
                headEntityVOList.add(headEntityVO);
                break;
            case "switch":
                questionnaireSqlContentVO.getChartQuerySql().append(chartswitchFiledSql(req.options.filed));
                questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` tinyint(1)   DEFAULT NULL ");
                questionnaireSqlContentVO.getSelectListSql().append("<if test=\"" + req.options.filed + "!=null and " + req.options.filed + "!=''\">and maintable." + req.options.filed + " = #{" + req.options.filed + "} </if>");
                questionnaireSqlContentVO.getInsertFiledSql().append(",#{" + tableName + "[$index0]." + req.options.filed + "==true?1:0}");
                questionnaireSqlContentVO.getUpdateFiledSql().append(" ," + req.options.filed + "#{" + tableName + "[$index0]." + req.options.filed + "==true?1:0}");
                questionnaireSqlContentVO.getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "!=null\">" + req.options.filed + "=#{" + req.options.filed + "},</if>");
                item.setVariabletype("常量");
                item.setRequired(false);
                item.setProperty(req.options.filed);
                item.setParamname(req.options.title);
                paramlist.add(item);
                headEntityVOList.add(headEntityVO);
                break;
            case "slider":
                questionnaireSqlContentVO.getChartQuerySql().append(chartNumberFiledSql(req.options.filed));
                questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` int(16)   DEFAULT NULL ");
                questionnaireSqlContentVO.getSelectListSql().append("<if test=\"" + req.options.filed + "!=null and " + req.options.filed + ".size()>0\">and maintable." + req.options.filed + " between  #{" + req.options.filed + "[0]} and #{" + req.options.filed + "[1]} </if>");
                questionnaireSqlContentVO.getInsertFiledSql().append(",#{" + tableName + "[$index0]." + req.options.filed + "}");
                questionnaireSqlContentVO.getUpdateFiledSql().append(" ," + req.options.filed + "=#{" + tableName + "[$index0]." + req.options.filed + "}");
                questionnaireSqlContentVO.getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "!=null\">" + req.options.filed + "=#{" + req.options.filed + "},</if>");
                item.setVariabletype("常量");
                item.setRequired(false);
                item.setProperty(req.options.filed);
                item.setParamname(req.options.title);
                paramlist.add(item);
                headEntityVOList.add(headEntityVO);
                break;
            case "colorpicker":
                questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` varchar(50)   DEFAULT NULL ");
                questionnaireSqlContentVO.getSelectListSql().append("<if test=\"" + req.options.filed + "!=null and " + req.options.filed + "!=''\">and maintable." + req.options.filed + " = #{" + req.options.filed + "} </if>");
                questionnaireSqlContentVO.getInsertFiledSql().append(",#{" + tableName + "[$index0]." + req.options.filed + "}");
                questionnaireSqlContentVO.getUpdateFiledSql().append(" ," + req.options.filed + "=#{" + tableName + "[$index0]." + req.options.filed + "}");
                questionnaireSqlContentVO.getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "!=null\">" + req.options.filed + "=#{" + req.options.filed + "},</if>");
                item.setVariabletype("常量");
                item.setRequired(false);
                item.setProperty(req.options.filed);
                item.setParamname(req.options.title);
                paramlist.add(item);
                headEntityVOList.add(headEntityVO);
                break;
            case "editor":
                questionnaireSqlContentVO.getSelectListSql().append("<if test=\"" + req.options.filed + "!=null and " + req.options.filed + "!=''\">and maintable." + req.options.filed + " like CONCAT('%',#{" + req.options.filed + "},'%') </if>");
                questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` text   DEFAULT NULL ");
                questionnaireSqlContentVO.getInsertFiledSql().append(",#{" + tableName + "[$index0]." + req.options.filed + "}");
                questionnaireSqlContentVO.getUpdateFiledSql().append(" ," + req.options.filed + "=#{" + tableName + "[$index0]." + req.options.filed + "}");
                questionnaireSqlContentVO.getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "!=null\">" + req.options.filed + "=#{" + req.options.filed + "},</if>");
                item.setVariabletype("常量");
                item.setRequired(false);
                item.setProperty(req.options.filed);
                item.setParamname(req.options.title);
                paramlist.add(item);
                headEntityVOList.add(headEntityVO);
                break;
            case "rate":
                questionnaireSqlContentVO.getChartQuerySql().append(chartNumberFiledSql(req.options.filed));
                questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` int(16)   DEFAULT NULL ");
                questionnaireSqlContentVO.getSelectListSql().append("<if test=\"" + req.options.filed + "!=null and " + req.options.filed + "!=''\">and maintable." + req.options.filed + " = #{" + req.options.filed + "} </if>");
                questionnaireSqlContentVO.getInsertFiledSql().append(",#{" + tableName + "[$index0]." + req.options.filed + "}");
                questionnaireSqlContentVO.getUpdateFiledSql().append(" ," + req.options.filed + "=#{" + tableName + "[$index0]." + req.options.filed + "}");
                questionnaireSqlContentVO.getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "!=null\">" + req.options.filed + "=#{" + req.options.filed + "},</if>");
                item.setVariabletype("常量");
                item.setRequired(false);
                item.setProperty(req.options.filed);
                item.setParamname(req.options.title);
                paramlist.add(item);
                headEntityVOList.add(headEntityVO);
                break;
            case "inputnumber":
                questionnaireSqlContentVO.getChartQuerySql().append(chartNumberFiledSql(req.options.filed));
                if(req.options.precision>0) {
                    questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "`  decimal(18, "+req.options.precision+")   DEFAULT NULL ");
                }else{
                    questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` int(16)   DEFAULT NULL ");
                }
                questionnaireSqlContentVO.getSelectListSql().append("<if test=\"" + req.options.filed + "!=null and " + req.options.filed + "!=''\">and maintable." + req.options.filed + " = #{" + req.options.filed + "} </if>");
                questionnaireSqlContentVO.getInsertFiledSql().append(",#{" + tableName + "[$index0]." + req.options.filed + "}");
                questionnaireSqlContentVO.getUpdateFiledSql().append(" ," + req.options.filed + "=#{" + tableName + "[$index0]." + req.options.filed + "}");
                questionnaireSqlContentVO.getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "!=null\">" + req.options.filed + "=#{" + req.options.filed + "},</if>");
                item.setVariabletype("常量");
                item.setRequired(false);
                item.setProperty(req.options.filed);
                item.setParamname(req.options.title);
                paramlist.add(item);
                headEntityVOList.add(headEntityVO);
                break;
            case "timepicker":
                questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` varchar(50)   DEFAULT NULL ");
                questionnaireSqlContentVO.getSelectListSql().append("<if test=\"" + req.options.filed + "!=null and " + req.options.filed + ".size()>0\">and maintable." + req.options.filed + " between #{" + req.options.filed + "[0]}  and #{" + req.options.filed + "[1]} </if>");
                questionnaireSqlContentVO.getInsertFiledSql().append(",#{" + tableName + "[$index0]." + req.options.filed + "}");
                questionnaireSqlContentVO.getUpdateFiledSql().append(" ," + req.options.filed + "=#{" + tableName + "[$index0]." + req.options.filed + "}");
                questionnaireSqlContentVO.getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "!=null\">" + req.options.filed + "=#{" + req.options.filed + "},</if>");
                item.setVariabletype("常量");
                item.setRequired(false);
                item.setProperty(req.options.filed);
                item.setParamname(req.options.title);
                paramlist.add(item);
                headEntityVOList.add(headEntityVO);
                break;
            case "upFilesComp":
            case "FileUpload":
            case "ImageUpload":
                questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` varchar(500)   DEFAULT NULL ");
                questionnaireSqlContentVO.getInsertFiledSql().append(",#{" + tableName + "[$index0]." + req.options.filed + "}");
                questionnaireSqlContentVO.getUpdateFiledSql().append(" ," + req.options.filed + "=#{" + tableName + "[$index0]." + req.options.filed + "}");
                questionnaireSqlContentVO.getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "!=null\">" + req.options.filed + "=#{" + req.options.filed + "},</if>");
                headEntityVOList.add(headEntityVO);
                break;
            case "user":
            case "organ":
            case "role":
            case "department":
                questionnaireSqlContentVO.getSelectListSql().append("<if test=\"" + req.options.filed + "!=null and " + req.options.filed + "!=''\" >and maintable." + req.options.filed + " = #{" + req.options.filed + "}  </if>");
                if (req.options.getIsMulty() == true) {
                    questionnaireSqlContentVO.setColumnType("varchar(2000)");
                    questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` varchar(2000)   DEFAULT NULL ");
                    returnQuestionnairelist.add(getNewQuestionnaireContent());
                    returnQuestionnairelist.get(0).getSelectListSql().append("<if test=\"" + req.options.filed + "!=null and " + req.options.filed + ".text!=''\" >and maintable." + req.options.filed + "_text = #{" + req.options.filed + ".text}  </if>");
                    returnQuestionnairelist.get(0).getTableEidtSql().append("`" + req.options.filed + "_text` varchar(500)   DEFAULT NULL ");
                    returnQuestionnairelist.get(0).getInsertFiledSql().append(",#{" +  req.options.filed + "==null?\"\":" +    req.options.filed + ".text}");
                    returnQuestionnairelist.get(0).getUpdateFiledSql().append(" ," + req.options.filed + "_text=#{" +  req.options.filed + "==null?\"\":" +  req.options.filed + ".text}");
                    returnQuestionnairelist.get(0).getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "_text!=null\">" + req.options.filed + "_text=#{" + req.options.filed + "_text},</if>");
                    returnQuestionnairelist.get(0).setFiled(req.options.filed + "_text");
                    headEntityVO.setBindname(req.options.filed + "_text");
                    headEntityVO.setHeadname(req.options.title);
                    headEntityVO.setFromId(item.getFormId());
                    ParamBizFromVO item2 = new ParamBizFromVO();
                    item2.setVariabletype("常量");
                    item2.setRequired(false);
                    item2.setProperty(req.options.filed + "_text");
                    item2.setParamname(req.options.title + "text");
                    paramlist.add(item2);
                } else {
                    questionnaireSqlContentVO.setColumnType("varchar(200)");
                    questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` varchar(200)   DEFAULT NULL ");
                    returnQuestionnairelist.add(getNewQuestionnaireContent());
                    returnQuestionnairelist.get(0).getSelectListSql().append("<if test=\"" + req.options.filed + "!=null and " + req.options.filed + ".text!=''\" >and maintable." + req.options.filed + "_text = #{" + req.options.filed + ".text}  </if>");
                    returnQuestionnairelist.get(0).getTableEidtSql().append("`" + req.options.filed + "_text` varchar(50)   DEFAULT NULL ");
                    returnQuestionnairelist.get(0).getInsertFiledSql().append(",#{" +  req.options.filed + "==null?\"\":" +    req.options.filed + ".text}");
                    returnQuestionnairelist.get(0).getUpdateFiledSql().append(" ," + req.options.filed + "_text=#{" +  req.options.filed + "==null?\"\":" +  req.options.filed + ".text}");
                    returnQuestionnairelist.get(0).getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "_text!=null\">" + req.options.filed + "_text=#{" + req.options.filed + "_text},</if>");
                    returnQuestionnairelist.get(0).setFiled(req.options.filed + "_text");
                    headEntityVO.setBindname(req.options.filed + "_text");
                    headEntityVO.setHeadname(req.options.title);
                    headEntityVO.setFromId(item.getFormId());
                    ParamBizFromVO item2 = new ParamBizFromVO();
                    item2.setVariabletype("常量");
                    item2.setRequired(false);
                    item2.setProperty(req.options.filed + "_text");
                    item2.setParamname(req.options.title + "text");
                    paramlist.add(item2);

                }
                questionnaireSqlContentVO.getInsertFiledSql().append(",#{" + tableName + "[$index0]." + req.options.filed + "}");
                questionnaireSqlContentVO.getUpdateFiledSql().append(" ," + req.options.filed + "=#{" + tableName + "[$index0]." + req.options.filed + "}");
                questionnaireSqlContentVO.getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "!=null\">" + req.options.filed + "=#{" + req.options.filed + "},</if>");
                item.setVariabletype("常量");
                item.setRequired(false);
                item.setProperty(req.options.filed);
                item.setParamname(req.options.title);
                paramlist.add(item);
                headEntityVOList.add(headEntityVO);
                break;
            case "imgage":
                questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` varchar(50)   DEFAULT NULL ");
                questionnaireSqlContentVO.getInsertFiledSql().append(",#{" + tableName + "[$index0]." + req.options.filed + "}");
                questionnaireSqlContentVO.getUpdateFiledSql().append(" ," + req.options.filed + "=#{" + tableName + "[$index0]." + req.options.filed + "}");
                questionnaireSqlContentVO.getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "!=null\">" + req.options.filed + "=#{" + req.options.filed + "},</if>");
                break;
            case "cityselector":
                questionnaireSqlContentVO.getSelectListSql().append("<if test=\"" + req.options.filed + "!=null and " + req.options.filed + "!=''\" >and maintable." + req.options.filed + " = #{" + req.options.filed + "}  </if>");
                questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` varchar(150)   DEFAULT NULL ");
                questionnaireSqlContentVO.getInsertFiledSql().append(",#{" + tableName + "[$index0]." + req.options.filed + "}");
                questionnaireSqlContentVO.getUpdateFiledSql().append(" ," + req.options.filed + "=#{" + tableName + "[$index0]." + req.options.filed + "}");
                questionnaireSqlContentVO.getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "!=null\">" + req.options.filed + "=#{" + req.options.filed + "},</if>");
                item.setVariabletype("常量");
                item.setRequired(false);
                item.setProperty(req.options.filed);
                item.setParamname(req.options.title);
                paramlist.add(item);
                headEntityVOList.add(headEntityVO);
                break;
            case "datarelevance":
                questionnaireSqlContentVO.getSelectListSql().append("<if test=\"" + req.options.filed + "!=null and " + req.options.filed + "!=''\" >and maintable." + req.options.filed + " = #{" + req.options.filed + "}  </if>");
                questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` varchar(150)   DEFAULT NULL ");
                questionnaireSqlContentVO.getInsertFiledSql().append(",#{" + tableName + "[$index0]." + req.options.filed + "}");
                questionnaireSqlContentVO.getUpdateFiledSql().append(" ," + req.options.filed + "=#{" + tableName + "[$index0]." + req.options.filed + "}");
                questionnaireSqlContentVO.getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "!=null\">" + req.options.filed + "=#{" + req.options.filed + "},</if>");
                item.setVariabletype("常量");
                item.setRequired(false);
                item.setProperty(req.options.filed);
                item.setParamname(req.options.title);
                item.setFormId(querybyFormId(req.options.sourceApi));
                paramlist.add(item);
                returnQuestionnairelist.add(getNewQuestionnaireContent());
                returnQuestionnairelist.get(0).getSelectListSql().append("<if test=\"" + req.options.filed + "_text!=null and " + req.options.filed + "_text!=''\" >and maintable." + req.options.filed + "_text = #{" + req.options.filed + "_text}  </if>");
                returnQuestionnairelist.get(0).getTableEidtSql().append("`" + req.options.filed + "_text` varchar(150)   DEFAULT NULL ");
                returnQuestionnairelist.get(0).getInsertFiledSql().append(",#{" + tableName + "[$index0]." + req.options.filed + "_text}");
                returnQuestionnairelist.get(0).getUpdateFiledSql().append(" ," + req.options.filed + "_text=#{" + tableName + "[$index0]." + req.options.filed + "_text}");
                returnQuestionnairelist.get(0).getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "_text!=null\">" + req.options.filed + "_text=#{" + req.options.filed + "_text},</if>");
                returnQuestionnairelist.get(0).setFiled(req.options.filed + "_text");
                headEntityVO.setBindname(req.options.filed + "_text");
                headEntityVO.setHeadname(req.options.title);
                headEntityVO.setFromId(item.getFormId());
                ParamBizFromVO item2 = new ParamBizFromVO();
                item2.setVariabletype("常量");
                item2.setRequired(false);
                item2.setProperty(req.options.filed + "_text");
                item2.setParamname(req.options.title + "text");
                paramlist.add(item2);
                headEntityVOList.add(headEntityVO);
                //                ParamBizFromVO item2=new ParamBizFromVO();
//                item2.setVariabletype("常量");
//                item2.setRequired(false);
//                item2.setProperty(req.dataIndex);
//                item2.setParamname(req.title);
//                item2.setFormId(querybyTableName( req.options.sourceApi));
//                paramlist.add(item2);
                break;
            case "fromlable":
                questionnaireSqlContentVO.getSelectListSql().append("<if test=\"" + req.options.filed + "!=null and " + req.options.filed + "!=''\" >and maintable." + req.options.filed + " like CONCAT('%', #{" + req.options.filed + "},'%')  </if>");
                questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` varchar(" + req.options.dataLength + ")   DEFAULT NULL ");
                questionnaireSqlContentVO.getInsertFiledSql().append(",#{" + tableName + "[$index0]." + req.options.filed + "}");
                questionnaireSqlContentVO.getUpdateFiledSql().append(" ," + req.options.filed + "=#{" + tableName + "[$index0]." + req.options.filed + "}");
                questionnaireSqlContentVO.getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "!=null\">" + req.options.filed + "=#{" + req.options.filed + "},</if>");
                item.setVariabletype("常量");
                item.setRequired(false);
                item.setProperty(req.options.filed);
                item.setParamname(req.options.title);
                paramlist.add(item);
                headEntityVOList.add(headEntityVO);
                break;
            case "hidden":
                questionnaireSqlContentVO.getSelectListSql().append("<if test=\"" + req.options.filed + "!=null and " + req.options.filed + "!=''\" >and maintable." + req.options.filed + " like CONCAT('%', #{" + req.options.filed + "},'%')  </if>");
                questionnaireSqlContentVO.getTableEidtSql().append("`" + req.options.filed + "` varchar(" + req.options.dataLength + ")   DEFAULT NULL ");
                questionnaireSqlContentVO.getInsertFiledSql().append(",#{" + tableName + "[$index0]." + req.options.filed + "}");
                questionnaireSqlContentVO.getUpdateFiledSql().append(" ," + req.options.filed + "=#{" + tableName + "[$index0]." + req.options.filed + "}");
                questionnaireSqlContentVO.getNewupdateFiledSql().append(" <if test=\"" + req.options.filed + "!=null\">" + req.options.filed + "=#{" + req.options.filed + "},</if>");
                break;
            default:
                questionnaireSqlContentVO = null;
                break;
        }
        if (questionnaireSqlContentVO != null) {
            questionnaireSqlContentVO.getTableEidtSql().append("COMMENT '" + req.options.title.replace("'", "''") + "' ");
        }
        returnQuestionnairelist.add(questionnaireSqlContentVO);
        return returnQuestionnairelist;
    }
    String getLetSql(FormControlReq req){
        if (StringUtils.isNotBlank(req.options.getSourceApi())) {
            String tableNameStr = req.options.getSourceApi().substring(0, req.options.getSourceApi().indexOf("."));
            if (StringUtils.isNotBlank(tableNameStr)) {
               return "left join "+tableNameStr+" as "+req.model+" on "+req.model+"." + req.options.getLabelValue() +"=maintable."+req.options.filed;
            }
        }
        return "";
    }
    ///根据数据源Id 查找对应表单信息
    String querybyFormId(String sourceApi) {
        if (StringUtils.isNotBlank(sourceApi)) {
            String tableNameStr = sourceApi.substring(0, sourceApi.indexOf("."));
            if (StringUtils.isNotBlank(tableNameStr)) {
                FormFormEntity fromEntity = tappFromService.querybyTableName(tableNameStr);
                if (fromEntity != null) {
                    return fromEntity.getFormFormId();
                }
            }
        }
        return "";
    }
}
