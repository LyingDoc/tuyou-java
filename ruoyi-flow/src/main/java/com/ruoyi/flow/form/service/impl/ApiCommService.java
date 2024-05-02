package com.ruoyi.flow.form.service.impl;


import com.alibaba.fastjson2.JSON;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
//import com.ruoyi.flow.db.DBOperationHelper;
import com.ruoyi.flow.comm.SpringBeanUtils;
import com.ruoyi.flow.db.ExportExcel;
import com.ruoyi.flow.db.ValidateUtils;
import com.ruoyi.flow.form.entity.SysDeptEntity;
import com.ruoyi.flow.form.entity.SysRoleDeptEntity;
import com.ruoyi.flow.form.mapper.KylinDBCommonMapper;
import com.ruoyi.flow.form.req.KylinFiledInfoReq;
import com.ruoyi.flow.form.req.KylinTableInfoReq;
import com.ruoyi.flow.form.service.IImportExcelService;
import com.ruoyi.flow.form.vo.KylinFiledInfoVO;
import com.ruoyi.flow.form.vo.KylinTableInfoVO;
import com.ruoyi.flow.oa.service.impl.SysOaUserServiceImpl;
import com.ruoyi.flow.vo.*;
import com.ruoyi.flow.form.vo.ApiConfigVO;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 通用Api接口类
 */
@Slf4j
@Service
public class ApiCommService {
    @Autowired
    private FormApiconfigServiceImpl apiConfigService;
    @Autowired
    private SysOaUserServiceImpl sysUserService;

    @Autowired
    private SysDeptFlowServiceImpl sysDeptService;

    @Autowired
    private IImportExcelService iImportExcelService;
    //    @Autowired
//    private KylinDBCommonMapper kylinDBCommonMapper;
//    @Autowired
//    DBOperationHelperService dbOperationHelperService;
    @Autowired
    private SpringBeanUtils springBeanUtils;
    @Autowired
    private SysRoleFlowServiceImpl sysRoleFlowService;

    /**
     * 根据执行sql返回输出的字段名称
     *
     * @return
     */
    public R<List<String>> getApiFieldNames(JSONObject param) throws Exception {
        byte[] decodedBytes = Base64.getMimeDecoder().decode(param.getString("sqlcontent"));
        String sqlcontent = new String(decodedBytes);
        JSONObject dbParam = param.getJSONObject("paramjsonsrt");
        JSONObject requestparam = dbParam.getJSONObject("$query");
        JSONObject headparam = dbParam.getJSONObject("$header");
        JSONObject dbOperationParam = dbParam.getJSONObject("body");
//        CurrentUserInfoVO currentUserInfo = CurrentHelp.Ins.GetUser();
        param.put("$user", getCurrentUserInfo());
        getDataScopeFilter(sqlcontent,param);
        dbOperationParam.put("$query", requestparam);
        dbOperationParam.put("$header", headparam);
        String dbId = param.getString("dbId");
//        DBOperationHelper dbOperationHelper = new DBOperationHelper(jdbcTemplateService.getJdbcTemplate(dbId), dbParam);
        DBOperationHelperService dbOperationHelperService = springBeanUtils.getBean(DBOperationHelperService.class);
        dbOperationHelperService.setContext(dbParam);
        return R.newOk(dbOperationHelperService.getColumninfo(sqlcontent));
    }

    /**
     * 测试执行Api接口
     *
     * @param param
     * @return
     */
    public R<Object> testExecuteApi(JSONObject param) throws Exception {
        byte[] decodedBytes = Base64.getMimeDecoder().decode(param.getString("sqlcontent"));
        String sqlcontent = new String(decodedBytes);
        JSONObject dbParam = param.getJSONObject("paramjsonsrt");
        JSONObject requestparam = dbParam.getJSONObject("$query");
        JSONObject headparam = dbParam.getJSONObject("$header");
        JSONObject dbOperationParam = dbParam.getJSONObject("body");
//        CurrentUserInfoVO currentUserInfo = CurrentHelp.Ins.GetUser();
        param.put("$user", getCurrentUserInfo());
        getDataScopeFilter(sqlcontent,param);
        dbOperationParam.put("$query", requestparam);
        dbOperationParam.put("$header", headparam);
        String dbId = param.getString("dbId");
        DBOperationHelperService dbOperationHelperService = springBeanUtils.getBean(DBOperationHelperService.class);
        dbOperationHelperService.setContext(dbOperationParam);
//        DBOperationHelper dbOperationHelper = new DBOperationHelper(jdbcTemplateService.getJdbcTemplate(dbId), dbOperationParam);
        return R.newOk(dbOperationHelperService.queryForList(sqlcontent));
    }

    public R<Object> testCommApi(JSONObject param, String ApiCode, HttpServletRequest req) throws Exception {
        JSONObject requestparam = param.getJSONObject("$query");
        JSONObject headparam = param.getJSONObject("$header");
        JSONObject dbOperationParam = param.getJSONObject("body");
//        CurrentUserInfoVO currentUserInfo = CurrentHelp.Ins.GetUser();
        param.put("$user", getCurrentUserInfo());
        dbOperationParam.put("$query", requestparam);
        dbOperationParam.put("$header", headparam);
        return apiComm(dbOperationParam, ApiCode, req);
    }


    /**
     * 导出xls
     *
     * @return 返回查询结果
     */
    public HttpServletResponse ExportExlce(JSONObject param, String ApiCode, HttpServletResponse response, HttpServletRequest req) throws Exception {
        ApiConfigVO apiconfig = apiConfigService.getApiConfigByCallMethodCode(ApiCode);
        if (apiconfig == null) {
            response.setContentType("text/html; charset=UTF-8"); //转码
            PrintWriter out = response.getWriter();
            out.flush();
            out.println("Api地址错误！");
            return response;
        }
        if (apiconfig.getMethodType().equals(1L)) {
            response.setContentType("text/html; charset=UTF-8"); //转码
            PrintWriter out = response.getWriter();
            out.flush();
            out.println("导出接口只能支持列表类型Api接口！");
            return response;
        }
        if (!StringUtils.isNotBlank(apiconfig.getHeadList()) || apiconfig.getHeadList().equals("[]")) {
            throw new Exception("未设置头部信息，导出失败！请联系管理员！");
        }
//        CurrentUserInfoVO currentUserInfo = CurrentHelp.Ins.GetUser();
        param.put("$user", getCurrentUserInfo());
        getDataScopeFilter(apiconfig.getSqlContent(),param);
        DBOperationHelperService dbOperationHelperService = springBeanUtils.getBean(DBOperationHelperService.class);
        dbOperationHelperService.setContext(param);
//        DBOperationHelper dbOperationHelper = new DBOperationHelper(jdbcTemplateService.getJdbcTemplate("dbform"), param);
        if (StringUtils.isNotBlank(apiconfig.getParamConfig())) {
            R<String> resultdata = dbOperationHelperService.bizformdata(JSON.parseArray(apiconfig.getParamConfig(), ParamBizFromVO.class));
            if (resultdata.getCode() != 0) {
                response.setContentType("text/html; charset=UTF-8"); //转码
                PrintWriter out = response.getWriter();
                out.flush();
                out.println(resultdata.getMsg());
                return response;
            }
        }
        List<HeadEntityVO> headEntityList = JSON.parseArray(apiconfig.getHeadList(), HeadEntityVO.class);
        List<Map<String, Object>> datalist = dbOperationHelperService.queryForList(apiconfig.getSqlContent());
        HSSFWorkbook workbook = ExportExcel.CreateExcel(apiconfig.getMethodName(), datalist, headEntityList);
        // 设置response的Header
        response.setHeader("content-type", "application/octet-stream");
        String excelFileName = apiconfig.getMethodName() + ".xlsx";
        response.setHeader("fileName", excelFileName);
        response.setContentType("application/octet-stream");
        try {
            response.flushBuffer();
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }

    /**
     * 通用接口
     *
     * @return 返回查询结果
     */
    public R<Object> apiComm(JSONObject param, String ApiCode, HttpServletRequest req) throws Exception {
        R<Object> r = new R<>();
        ApiConfigVO apiconfig = apiConfigService.getApiConfigByCallMethodCode(ApiCode);
        if (apiconfig == null) {
            return R.newError("请求路径有误！");
        }
        switch (apiconfig.getMethodType()) {
            case "1":
                r = apiQueryList(apiconfig, param, req);
                break;
            case "2":
                r = apiQuery(apiconfig, param, req);
                break;
            case "3":
                r = operateApi(apiconfig, param, req);
                break;
            case "5":
                r = queryForObject(apiconfig, param, req);
                break;
            default:
                r = R.newOk();
                break;
        }
        return r;
    }


    ///通用导入方法
    public R<String> importExlce(String ApiCode, MultipartFile file, HttpServletRequest req, HttpServletResponse resp) {
        R<Object> r = new R<>();
        ApiConfigVO apiconfig = apiConfigService.getApiConfigByCallMethodCode(ApiCode);
        if (apiconfig == null) {
            return R.newError("请求导入Api路径有误！");
        }
        if (!"4".equals(apiconfig.getMethodType())) {
            return R.newError("找不到导入Api接口。");
        }
        try {   //拆解excelTitleRowNum
            List<Integer> excelTitleRowNums = new ArrayList();
            if (org.apache.commons.lang.StringUtils.isNotBlank(apiconfig.getXlsTemplateRowNum())) {
                String[] titleRowNumStrs = apiconfig.getXlsTemplateRowNum().split("-");
                for (String titleRowNumStr : titleRowNumStrs) {
                    if (org.apache.commons.lang.StringUtils.isNotBlank(titleRowNumStr)) {
                        if (NumberUtils.isDigits(titleRowNumStr)) {
                            excelTitleRowNums.add(Integer.valueOf(titleRowNumStr));
                        } else {
                            return R.newError("sheet页标题行输入不正确，请输入数字和‘-’");
                        }
                    }
                }
            }
            JSONObject jsonObject = iImportExcelService.importExcelJSON(file, excelTitleRowNums, req, resp, apiconfig.getParamConfig());
            if (jsonObject == null) {
                return R.newError("上传失败！未获取到数据！");
            }
            if (jsonObject.optInt("code") != 0) {
                return R.newError(jsonObject.optString("msg"));
            }
            jsonObject.put("$user", getCurrentUserInfo());
            getDataScopeFilter(apiconfig.getSqlContent(),jsonObject);
            DBOperationHelperService dbOperationHelperService = springBeanUtils.getBean(DBOperationHelperService.class);
            dbOperationHelperService.setContext(jsonObject);
            dbOperationHelperService.ExecuteOptSql(apiconfig.getSqlContent());
            return R.newOk();
        } catch (Exception ex) {
            log.error("importExlce",ex);
            return R.newError("导入失败！具体错误："+ex.toString());
        }


    }

    /**
     * 根据ApiCode 获取返回列表的头部信息
     *
     * @param ApiCode
     * @return
     */
    public R<List<HeadEntityVO>> getListHeadCell(@PathVariable("ApiCode") String ApiCode) {
        ApiConfigVO apiconfig = apiConfigService.getApiConfigByCallMethodCode(ApiCode);
        if (apiconfig == null) {
            return R.newError("Api地址错误！");
        }
        List<HeadEntityVO> headEntityList = new ArrayList<>();
        if (StringUtils.isNotBlank(apiconfig.getHeadList())) {
            headEntityList = JSON.parseArray(apiconfig.getHeadList(), HeadEntityVO.class);
            for (int i = 0; i < headEntityList.size(); i++) {
                HeadEntityVO headEntity = headEntityList.get(i);
                headEntity.setId(i + "");
            }
        }
        return R.newOk(headEntityList);
    }

    /**
     * 通用查询列表接口
     *
     * @param param
     * @param
     * @return
     * @throws Exception
     */
    public R<Object> apiQueryList(ApiConfigVO apiconfig, JSONObject param, HttpServletRequest req) throws Exception {
        if (apiconfig == null) {
            return R.newError("Api地址错误！");
        }
//        CurrentUserInfoVO currentUserInfo = CurrentHelp.Ins.GetUser();
        param.put("$user", getCurrentUserInfo());
        getDataScopeFilter(apiconfig.getSqlContent(),param);
        DBOperationHelperService dbOperationHelperService = springBeanUtils.getBean(DBOperationHelperService.class);
        dbOperationHelperService.setContext(param);
//        DBOperationHelper dbOperationHelper = new DBOperationHelper(jdbcTemplateService.getJdbcTemplate(apiconfig.getDbId()), param);
        R<String> resultdata = bizformdata(req, dbOperationHelperService, param, apiconfig);
        if (resultdata.getCode() != 0) {
            return R.newError(resultdata.getMsg());
        }
        List<HeadEntityVO> headEntityList = new ArrayList<>();
        if (StringUtils.isNotBlank(apiconfig.getHeadList())) {
            headEntityList = JSON.parseArray(apiconfig.getHeadList(), HeadEntityVO.class);
            for (int i = 0; i < headEntityList.size(); i++) {
                HeadEntityVO headEntity = headEntityList.get(i);
                headEntity.setId(i + "");
            }
        }
        Map<String, Object> map = new HashMap<>();
        if (param.get("page") != null && param.get("page").toString() != "") {
            FormPageVO<Map<String, Object>> data = dbOperationHelperService.queryPageForList(apiconfig.getSqlContent());
            map.put("showCelNames", apiconfig.getShowCelNames());
            map.put("showCtrlWhere", apiconfig.getShowCtrlWhere());
            map.put("columns", headEntityList);
            map.put("list", data.getRows());
            map.put("pageNum", data.getPage());
            return R.newOk(map, data.getRecords());
        } else {
            List<Map<String, Object>> datalist = dbOperationHelperService.queryForList(apiconfig.getSqlContent());
            map.put("columns", headEntityList);
            map.put("list", datalist);
            map.put("showCelNames", apiconfig.getShowCelNames());
            map.put("showCtrlWhere", apiconfig.getShowCtrlWhere());
            return R.newOk(map);
        }
    }

    /**
     * 通用查询接口
     *
     * @param param
     * @param
     * @return
     * @throws Exception
     */
    private R<Object> apiQuery(ApiConfigVO apiconfig, JSONObject param, HttpServletRequest req) throws Exception {
        if (apiconfig == null) {
            return R.newError("Api地址错误！");
        }
//        CurrentUserInfoVO currentUserInfo = CurrentHelp.Ins.GetUser();
        param.put("$user", getCurrentUserInfo());
        getDataScopeFilter(apiconfig.getSqlContent(),param);
        DBOperationHelperService dbOperationHelperService = springBeanUtils.getBean(DBOperationHelperService.class);
        dbOperationHelperService.setContext(param);
//        DBOperationHelper dbOperationHelper = new DBOperationHelper(jdbcTemplateService.getJdbcTemplate(apiconfig.getDbId()), param);
        R<String> resultdata = bizformdata(req, dbOperationHelperService, param, apiconfig);
        if (resultdata.getCode() != 0) {
            return R.newError(resultdata.getMsg());
        }
        return R.newOk(dbOperationHelperService.queryForList(apiconfig.getSqlContent()));
    }

    //    @Autowired
//    private EmployeeService employeeService;
    public CurrentUserInfoVO getCurrentUserInfo() {
        Long currentUserId = SecurityUtils.getUserId();
        SysUser user = sysUserService.selectUserById(currentUserId);
        CurrentUserInfoVO currentUserInfoVO = new CurrentUserInfoVO();
        currentUserInfoVO.setId(currentUserId.toString());
        currentUserInfoVO.setLastLoginIp(SecurityUtils.getLoginUser().getIpaddr());
        currentUserInfoVO.setLoginCode(user.getUserName());
        currentUserInfoVO.setStatus(user.getStatus());
        currentUserInfoVO.setUserCode(user.getUserName());
        currentUserInfoVO.setUserName(user.getNickName());
        currentUserInfoVO.setSex(user.getSex());
        currentUserInfoVO.setEmail(user.getEmail());
        currentUserInfoVO.setAvatar(user.getAvatar());
        currentUserInfoVO.setPhonenumber(user.getPhonenumber());
//        currentUserInfoVO.setIsAdmin(user.isAdmin());
        SysDept deptEntity = user.getDept();
        if (deptEntity != null) {
//            SysDeptEntity offceinfo = sysDeptService.selectDeptById(deptEntity.getParentId());
//            if (offceinfo != null) {
//                currentUserInfoVO.setOfficeCode(offceinfo.getDeptId().toString());
//                currentUserInfoVO.setOfficeName(offceinfo.getDeptName());
//            }
            currentUserInfoVO.setOfficeCode(deptEntity.getDeptId().toString());
            currentUserInfoVO.setOfficeName(deptEntity.getDeptName());
            currentUserInfoVO.setDeptName(deptEntity.getDeptId().toString());
            currentUserInfoVO.setDeptCode(deptEntity.getDeptName());
        }

        return currentUserInfoVO;
    }

    /**
     * 通用查询实体对象
     *
     * @param param
     * @param
     * @return
     * @throws Exception
     */
    private R<Object> queryForObject(ApiConfigVO apiconfig, JSONObject param, HttpServletRequest req) throws Exception {
        if (apiconfig == null) {
            return R.newError("Api地址错误！");
        }
//        CurrentUserInfoVO currentUserInfo = CurrentHelp.Ins.GetUser();
        param.put("$user", getCurrentUserInfo());
        getDataScopeFilter(apiconfig.getSqlContent(),param);
        DBOperationHelperService dbOperationHelperService = springBeanUtils.getBean(DBOperationHelperService.class);
        dbOperationHelperService.setContext(param);
//        DBOperationHelper dbOperationHelper = new DBOperationHelper(jdbcTemplateService.getJdbcTemplate(apiconfig.getDbId()), param);
        R<String> resultdata = bizformdata(req, dbOperationHelperService, param, apiconfig);
        if (resultdata.getCode() != 0) {
            return R.newError(resultdata.getMsg());
        }
        Object obj = dbOperationHelperService.queryForObject(apiconfig.getSqlContent());
        if (obj == null) {
            return R.newError("未查询到信息");
        } else {
            return R.newOk(obj);
        }
    }

    /**
     * 通用保存类接口方法
     *
     * @return 返回查询结果
     */
    private R<Object> operateApi(ApiConfigVO apiconfig, JSONObject param, HttpServletRequest req) throws Exception {
        StringBuilder logStr = new StringBuilder();
        try {
            logStr.append("================进入operateApi保存方法！" + JSON.toJSONString(param) + "\n");
            if (apiconfig == null) {
                return R.newError("Api地址错误！");
            }
            param.put("$user", getCurrentUserInfo());
            getDataScopeFilter(apiconfig.getSqlContent(),param);
            logStr.append("================组装当前用户的环境部变量！\n");
            DBOperationHelperService dbOperationHelperService = springBeanUtils.getBean(DBOperationHelperService.class);
            dbOperationHelperService.setContext(param);
//            DBOperationHelper dbOperationHelper = new DBOperationHelper(jdbcTemplateService.getJdbcTemplate(apiconfig.getDbId()), param);
            logStr.append("================验证数据的格式是否正确！\n");
            R<String> resultdata = bizformdata(req, dbOperationHelperService, param, apiconfig);
            if (resultdata.getCode() != 0) {
                return R.newError(resultdata.getMsg());
            }
            logStr.append("================开始执行sql！" + apiconfig.getSqlContent() + "\n");
            dbOperationHelperService.ExecuteOptSql(apiconfig.getSqlContent());
            System.out.println(logStr.toString());
            return R.newOk();
        } catch (Exception ex) {
            log.error("operateApi",ex);
            System.out.println(logStr.toString() + "\r\n 具体错误信息：" + ex.toString());
            throw ex;
        }

    }

    /**
     * 验证参数
     *
     * @param
     * @return
     */
    private R<String> bizformdata(HttpServletRequest req, DBOperationHelperService dbOperationHelperService, JSONObject param, ApiConfigVO apiconfig) throws Exception {
        R<String> result = new R<String>();
        if (StringUtils.isNotBlank(apiconfig.getParamConfig())) {
            List<ParamBizFromVO> paramlist = JSON.parseArray(apiconfig.getParamConfig(), ParamBizFromVO.class);
            JSONObject requestparam = new JSONObject();
            JSONObject headparam = new JSONObject();
            for (ParamBizFromVO item : paramlist) {
                if (item.getProperty().equals("body")) {
                    result = dbOperationHelperService.bizformdata(item.getChildren());
                    if (result.getCode() != 0) {
                        return result;
                    }
                } else if (item.getProperty().equals("query")) {
                    if (item.getChildren() != null && req != null) {
                        for (ParamBizFromVO childrenitem : item.getChildren()) {
                            result = ValidateUtils.ValidateParam(childrenitem, req.getParameter(childrenitem.getProperty()));
                            if (result.getCode() != 0) {
                                return result;
                            } else {
                                requestparam.put(childrenitem.getProperty(), req.getParameter(childrenitem.getProperty()));
                            }
                        }
                    }

                } else if (item.getProperty().equals("header")) {
                    if (item.getChildren() != null && req != null) {
                        for (ParamBizFromVO childrenitem : item.getChildren()) {
                            if (childrenitem.getRequired()) {
                                result = ValidateUtils.ValidateParam(childrenitem, req.getHeader(childrenitem.getProperty()));
                                if (result.getCode() != 0) {
                                    return result;
                                } else {
                                    headparam.put(childrenitem.getProperty(), req.getHeader(childrenitem.getProperty()));
                                }
                            }
                        }
                    }
                }
            }
            if(!param.containsKey("$query")){
                param.put("$query", requestparam);
                dbOperationHelperService.put("$query", requestparam);
            }
            if(!param.containsKey("$header")) {
                param.put("$header", headparam);
                dbOperationHelperService.put("$header", headparam);
            }
        }
        return result.newOk();
    }

    public List<Map<String, Object>> getAlltablename(KylinTableInfoReq req) throws Exception {
        DBOperationHelperService dbOperationHelperService = springBeanUtils.getBean(DBOperationHelperService.class);
        JSONObject param = new JSONObject();
        param.put("tablecomment", req.getTablecomment());
        param.put("tablename", req.getTablename());
        dbOperationHelperService.setContext(param);
//        DBOperationHelper dbOperationHelper = new DBOperationHelper(jdbcTemplateService.getJdbcTemplate("business"), param);
        return dbOperationHelperService.queryForList("\t\n" +
                "\t\tSELECT table_name as tablename ,table_comment as tablecomment FROM\n" +
                "\t\tinformation_schema.TABLES WHERE table_schema = ( SELECT DATABASE (\n" +
                "\t\t) )\n" +
                "\n" +
                "\t\t<if test=\"tablename!=null and tablename!=''\">\n" +
                "\t\t\tand table_name like concat('%', #{tablename}, '%')\n" +
                "\t\t</if>\n" +
                "\t\t<if test=\"tablecomment!=null and tablecomment!=''\">\n" +
                "\t\t\tand TABLE_COMMENT like concat('%', #{tablecomment}, '%')\n" +
                "\t\t</if>\n" +
                "\t");

    }

    public List<Map<String, Object>> getTableColumn(KylinFiledInfoReq req) throws Exception {
        DBOperationHelperService dbOperationHelperService = springBeanUtils.getBean(DBOperationHelperService.class);
        JSONObject param = new JSONObject();
        param.put("columnComment", req.getColumnComment());
        param.put("columnName", req.getColumnName());
        param.put("tableName", req.getTableName());
        dbOperationHelperService.setContext(param);
//        DBOperationHelper dbOperationHelper = new DBOperationHelper(jdbcTemplateService.getJdbcTemplate("business"), param);
        return dbOperationHelperService.queryForList("\tselect table_name as tablename ,column_name as columnname,column_comment as detext\n" +
                "\t\tfrom information_schema.columns where table_schema = ( SELECT DATABASE (\n" +
                "\t\t) )\n" +
                "\n" +
                "\t\t<if test=\"tableName!=null and tableName!=''\">\n" +
                "\t\t\tand table_name like concat('%', #{tableName}, '%')\n" +
                "\t\t</if>\n" +
                "\t\t<if test=\"columnName!=null and columnName!=''\">\n" +
                "\t\t\tand Column_Name like concat('%', #{columnName}, '%')\n" +
                "\t\t</if>\n" +
                "\t\t<if test=\"columnComment!=null and columnComment!=''\">\n" +
                "\t\t\tand COLUMN_COMMENT like concat('%', #{columnComment}, '%')\n" +
                "\t\t</if>");
    }


    public void getDataScopeFilter(String sqlstr,JSONObject param) {
        Pattern p = Pattern.compile("\\$\\{([^}]*)\\}");
        Matcher m = p.matcher(sqlstr); // 获取 matcher 对象
        String $dataScope=dataScopeFilter();
        param.put("$dataScope",$dataScope.replace("${table}.",""));
        while (m.find()) {
            String filed = m.group();
            String filedName = filed.substring(2, filed.length() - 1);
            if(filedName.contains("$dataScope")&&!filedName.equals("$dataScope")){
                JSONObject dataScopeItem=new JSONObject();
                dataScopeItem.put("$dataScope",$dataScope.replace("${table}",filedName.replace(".$dataScope","")));
                param.put(filedName.replace(".$dataScope",""),dataScopeItem);
            }
        }
    }

    public String dataScopeFilter() {
        List<SysRole> sysRoleList = SecurityUtils.getLoginUser().getUser().getRoles();
        List<String> conditions = new ArrayList<String>();
        Boolean isAllData = false; //拥有所有数据权限
        boolean isUserData = false; //仅拥有自己的数据权限
        for (SysRole role : sysRoleList) {
            String dataScope = role.getDataScope();
            switch (dataScope) {
                case "1":
                    conditions = new ArrayList<String>();
                    isAllData = true;
                    break;
                case "2":
                    List<SysRoleDeptEntity> listRoleDept = sysRoleFlowService.getDeptIDByRoleId(role.getRoleId());
                    for (Integer i = 0; i < listRoleDept.size(); i++) {
                        if (!conditions.contains(listRoleDept.get(i).getDeptId().toString())) {
                            conditions.add(listRoleDept.get(i).getDeptId().toString());
                        }
                    }
                    break;
                case "3":
                    if (!conditions.contains(SecurityUtils.getLoginUser().getUser().getDeptId())) {
                        conditions.add(SecurityUtils.getLoginUser().getUser().getDeptId().toString());
                    }
                    break;
                case "4":
                    if (!conditions.contains(SecurityUtils.getLoginUser().getUser().getDept().getAncestors())) {
                        String ancestorscode = SecurityUtils.getLoginUser().getUser().getDept().getAncestors() + "," + SecurityUtils.getLoginUser().getUser().getDept().getDeptId();
                        conditions.add(SecurityUtils.getLoginUser().getUser().getDept().getDeptId().toString());
                        List<Long> deptlist = sysDeptService.getChildDeptId(ancestorscode);
                        for (Integer i = 0; i < deptlist.size(); i++) {
                            if (!conditions.contains(deptlist.get(i).toString())) {
                                conditions.add(deptlist.get(i).toString());
                            }
                        }
                    }
                    break;
                case "5":
                    isUserData = true;
                    break;

            }
            if (isAllData) {
                break;
            }
        }
        if (isAllData) {
            return "";
        }
        if (conditions.size() == 0 && isUserData) {
            return " (${table}.fcreateby='" + SecurityUtils.getLoginUser().getUsername() + "')";
        }
        if (conditions.size() > 0) {
            return " (${table}.organcode in ('" + String.join("','", conditions) + "'))";
        }
        return "";
    }


}
