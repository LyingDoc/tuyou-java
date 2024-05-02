package com.ruoyi.flow.form.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.ruoyi.flow.db.ValidateUtils;
import com.ruoyi.flow.vo.FormPageVO;
import com.ruoyi.flow.vo.ParamBizFromVO;
import com.ruoyi.flow.vo.R;
import com.ruoyi.flow.vo.SqlParamVO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;
import org.springframework.stereotype.Service;

import java.sql.Types;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Scope("prototype")
public class DBOperationHelperService {
    @Autowired
    @Qualifier("secondJdbcTemplate")
    private JdbcTemplate jdbcTemplate;
    OgnlContext context;
//     @Autowired
//    JdbcTemplateService  jdbcTemplateService;

    public void setContext(JSONObject param) {
        this.context = getOgnlContext(param);

    }

    public List<String> getColumninfo(String xmlStr) throws Exception {
        SqlParamVO sqlParam = getQuerySqlParam(xmlStr);
        return getColumninfo(sqlParam);
    }

    public List<String> getColumninfo(SqlParamVO sqlParam) throws Exception {
        String sql = "select  * from  (" + sqlParam.getSql() + ") counttable_ limit 0,1 ";
        SqlRowSet srcSqlRowSet = sqlParam.getParam().size() > 0 ? jdbcTemplate.queryForRowSet(sql, sqlParam.getParam().toArray(), sqlParam.getTypes().stream().mapToInt(Integer::valueOf).toArray()) : jdbcTemplate.queryForRowSet(sql); //注意limit 0更合适
        SqlRowSetMetaData sqlRowSetMetaData = srcSqlRowSet.getMetaData();
        int columnCount = sqlRowSetMetaData.getColumnCount();
        List<String> Columnlist = new ArrayList<>();
        for (int i = 1; i <= columnCount; i++) {
            Columnlist.add(sqlRowSetMetaData.getColumnLabel(i)); //获取字段的名称、类型和描述
        }
        return Columnlist;
    }

    /***
     * 检查查询接口配置sql内容是否正常
     * @param sqlstr
     * @throws Exception
     */
    public void CheckQuerySql(String sqlstr) throws Exception {
        String outerXML = "";
        Element root;
        String innerXML = "";
        if (!StringUtils.isNotBlank(sqlstr)) {
            throw new Exception("sql配置参数不能为空！");
        }
        try {
            sqlstr = sqlstr.replaceAll("\r\n", " ");
            Document document2 = DocumentHelper.parseText(HandAttrSpecialChar("<select>" + sqlstr + "</select>"));
            root = document2.getRootElement();
            outerXML = root.asXML(); //得到 outerXML
            innerXML = outerXML.replaceAll("^<select.*?>|</select>$", "");
        } catch (Exception ex) {
            throw new Exception("sql配置xml格式错误！具体错误：" + ex.toString());
        }
        if (StringUtils.isNotBlank(root.getText()) && (root.selectNodes("sql").size() > 0)) {
            throw new Exception("配置语法错误！直接sql语句不能嵌套sql节点");
        }
        if (!StringUtils.isNotBlank(root.getText())) {
            List<Element> listElement = root.elements();
            for (Element e : listElement) {//遍历所有一级子节点
                if (!e.getName().toLowerCase().equals("sql")) {
                    throw new Exception("配置语法错误！查询sql配置只能配置sql节点");
                }
            }
        }
    }

    ///sql 模板
    //<sql ></sql>
    //<opt></opt>
    //<opt></opt>
    //<opt></opt>
    //<opt></opt>
    //<for  name="objde[$index0].bbb">
    //    <for  name="objde[$index0].bbb.dsfd[$index1]">
    //            <sql param="dfds" test="dsfds">
    //                select *from tab where bbb=#{dfdsf}
    //           </sql>
    //          <sql test="objde[$index0].bbb.dsfd[$index1].dfds==dfdf">
    //               select *from  tab where dfds=#{fdsfd}
    //        </sql>
    //    </for>
    //</for><return param=""/>
    ////检验传入的sql配置是否正确 定义规则：操作api 操作配置接口信息 检查
    public void CheckOptSql(String sqlstr) throws Exception {
        String outerXML = "";
        Element root;
        String innerXML = "";
        if (!StringUtils.isNotBlank(sqlstr)) {
            throw new Exception("sql配置参数不能为空！");
        }
        try {
            sqlstr = sqlstr.replaceAll("\r\n", " ");
            Document document2 = DocumentHelper.parseText(HandAttrSpecialChar("<select>" + sqlstr + "</select>"));
            root = document2.getRootElement();
            outerXML = root.asXML(); //得到 outerXML
            innerXML = outerXML.replaceAll("^<select.*?>|</select>$", "");
        } catch (Exception ex) {
            throw new Exception("sql配置xml格式错误！具体错误：" + ex.toString());
        }
        if (StringUtils.isNotBlank(root.getText()) && (root.selectNodes("sql").size() > 0 || root.selectNodes("for").size() > 0)) {
            throw new Exception("配置语法错误！直接sql语句不能嵌套sql,for,opt节点");
        }
        if (!StringUtils.isNotBlank(root.getText())) {
            List<Element> listElement = root.elements();
            for (Element e : listElement) {//遍历所有一级子节点
                CheckSqlOrOptNode(e);
                CheckforNode(e);
            }
        }
    }

    /***
     * 检验for节点 是否正常
     * @param e
     * @throws Exception
     */
    void CheckforNode(Element e) throws Exception {
        if (e.getName().toLowerCase().equals("for")) {
            if (e.selectNodes("/if").size() > 0) {
                throw new Exception("配置语法错误！for节点下不能嵌套if等节点。xml节点内容：" + e.asXML());
            }
        }
        List<Element> listElement = e.elements();
        for (Element node : listElement) {//遍历所有一级子节点
            CheckSqlOrOptNode(node);
            CheckforNode(e);
        }
    }

    /***
     * 检验sql和opt节点 是否正常
     */
    void CheckSqlOrOptNode(Element e) throws Exception {
        if (e.getName().toLowerCase().equals("sql")) {
            if (e.selectNodes("//sql").size() > 0 || e.selectNodes("//for").size() > 0) {
                throw new Exception("配置语法错误！sql节点下不能嵌套sql、for、opt等节点。xml节点内容：" + e.asXML());
            }
        }
    }

    /***
     * 替换xml不能识别的&,<,>特殊字符
     * @param data
     * @return
     */
    String FormatBeForeTrans(String data) {
        if ((data == null) | "".equals(data)) {
            return "";
        } else {
            data = data.replaceAll("\\&", "&amp;");
            data = data.replaceAll("\\<", "&lt;");
            data = data.replaceAll("\\>", "&gt;");
            return data;
        }
    }

    /***
     * 替换xml不能识别的&,<,>特殊字符
     * @param data
     * @return
     */
    String ReversalFormatBeForeTrans(String data) {
        if ((data == null) | "".equals(data)) {
            return "";
        } else {
            data = data.replace("&amp;", "&");
            data = data.replace("&lt;", "<");
            data = data.replace("&gt;", ">");
            return data;
        }
    }

    /***
     * 处理属性中特殊字符
     * @param xml
     * @return
     */
    String HandAttrSpecialChar(String xml) {
        String rgex = "\".*?\"";
        Pattern pattern = Pattern.compile(rgex);// 匹配的模式
        Matcher m = pattern.matcher(xml);
        //匹配的有多个
        List<String> list = new ArrayList<String>();
        int i = 0;
        while (m.find()) {
            xml = xml.replace(m.group(0), FormatBeForeTrans(m.group(0)));
        }
        return HandTextSpecialChar(xml);
    }

    /***
     * 处理文本串中的字符串
     * @param xml
     * @return
     */
    String HandTextSpecialChar(String xml) {
        //正则表达式
        String rgex = "<(.*?)>";
        Pattern pattern = Pattern.compile(rgex);// 匹配的模式
        Matcher m = pattern.matcher(xml);
        //匹配的有多个
        List<String> list = new ArrayList<String>();
        int i = 0;
        while (m.find()) {
            xml = xml.replace(m.group(0), "『" + i + "』");
            list.add(m.group(0));
            i++;
        }
        xml = FormatBeForeTrans(xml);
        for (int idex = 0; idex < list.size(); idex++) {
            xml = xml.replace("『" + idex + "』", list.get(idex));
        }
        return xml;
    }

    private String getSqlContent(List<Element> sonElemetList, OgnlContext context, String XmlStr) throws Exception {

        for (Element sonElement : sonElemetList) {
            String eleName = sonElement.getName();
            eleName=eleName.toLowerCase();
            if (eleName.equals("where")) {
                if (sonElement.elements().size() != 0) {
                    // XmlStr=  replaceFirst(XmlStr,sonElement.asXML(),"&%where%&");
                    String whereSqlstr = getSqlContent(sonElement.elements(), context, getInnerXML(sonElement));
                    if (StringUtils.isNotBlank(whereSqlstr)) {
                        if (whereSqlstr.trim().indexOf("and") == 0) {
                            XmlStr = replaceFirst(XmlStr, sonElement.asXML(), " where " + whereSqlstr.trim().substring(3, whereSqlstr.trim().length()));
                        } else {
                            XmlStr = replaceFirst(XmlStr, sonElement.asXML(), " where  " + whereSqlstr);
                        }
                    } else {
                        XmlStr = replaceFirst(XmlStr, sonElement.asXML(), "");
                    }
                }

            }else if(eleName.equals("set")){
                if (sonElement.elements().size() != 0) {
                    // XmlStr=  replaceFirst(XmlStr,sonElement.asXML(),"&%where%&");
                    String whereSqlstr = getSqlContent(sonElement.elements(), context, getInnerXML(sonElement));
                    if (StringUtils.isNotBlank(whereSqlstr)) {
                        whereSqlstr=whereSqlstr.trim();
                        if (",".equals(whereSqlstr.substring( whereSqlstr.length()-1))) {
                            XmlStr = replaceFirst(XmlStr, sonElement.asXML(), " set " + whereSqlstr.substring(0, whereSqlstr.length()-1))+" ";
                        } else {
                            XmlStr = replaceFirst(XmlStr, sonElement.asXML(), " set  " + whereSqlstr);
                        }
                    } else {
                        XmlStr = replaceFirst(XmlStr, sonElement.asXML(), " set 1=1 ");
                    }
                }
            }
            else   if (eleName.equals("if")) {
                String expressionStr = sonElement.attribute("test").getValue();
                Object expression = Ognl.parseExpression(ReversalFormatBeForeTrans(expressionStr));
                // 解析树状表达式，返回结果
                Boolean expressionResult = (boolean) Ognl.getValue(expression, context, context);
                System.out.println(expressionStr + "结果" + expressionResult.toString());
                if (expressionResult == true) {
                    if (sonElement.elements().size() > 0) {
                        String childSqlstr = getSqlContent(sonElement.elements(), context, getInnerXML(sonElement));
                        XmlStr = replaceFirst(XmlStr, sonElement.asXML(), childSqlstr);
                    } else {
                        XmlStr = replaceFirst(XmlStr, sonElement.asXML(), sonElement.getText());
                    }
                } else {
                    XmlStr = replaceFirst(XmlStr, sonElement.asXML(), "");
                }
            }
        }
        return XmlStr;
    }

    private String getInnerXML(Element node) {
        String nodeName = node.getName(); //确定节点名
        String outerXML = node.asXML(); //得到 outerXML
        //注意起始节点可能会带属性
        String innerXML = outerXML.replaceAll("^<" + nodeName + ".*?>|</" + nodeName + ">$", "");
        return innerXML;
    }

    public static String replaceFirst(String str, String oldStr, String newStr) {
        int i = str.indexOf(oldStr);
        if (i == -1) return str;
        str = str.substring(0, i) + newStr + str.substring(i + oldStr.length());
        return str;
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

    public void put(String key, Object value) {
        context.put(key, value);
    }

    public Object GetJsonValue(String expressionStr, OgnlContext context1) throws org.apache.ibatis.ognl.OgnlException {
        if (!StringUtils.isNotBlank(expressionStr)) {
            return null;
        }
        try {
            Object expression = org.apache.ibatis.ognl.Ognl.parseExpression(ReversalFormatBeForeTrans(expressionStr));
            Object value = org.apache.ibatis.ognl.Ognl.getValue(expression, context1, context1);
            return Double.parseDouble(value == null ? "0" : value.toString());
        } catch (org.apache.ibatis.ognl.OgnlException ex) {
            throw new org.apache.ibatis.ognl.OgnlException("获取参数" + expressionStr + "出错！具体错误" + ex.toString());

        }

    }

    public Object GetJsonValue(String expressionStr) throws OgnlException {
        if (!StringUtils.isNotBlank(expressionStr)) {
            return null;
        }
        try {
            Object expression = Ognl.parseExpression(ReversalFormatBeForeTrans(expressionStr));
            Object value = Ognl.getValue(expression, context, context);
            return value;
        } catch (OgnlException ex) {
            throw new OgnlException("获取参数" + expressionStr + "出错！具体错误" + ex.toString());

        }
    }

    private SqlParamVO getSqlParam(String sqlstr) throws Exception {
        Pattern p = Pattern.compile("\\#\\{([^}]*)\\}");
        Matcher m = p.matcher(sqlstr); // 获取 matcher 对象
        List<Object> list = new ArrayList<>();
        List<Integer> types = new ArrayList<>();
        while (m.find()) {
            String filed = m.group();
            String filedName = filed.substring(2, filed.length() - 1);
            if (filedName.contains("$newid")) {
                sqlstr = sqlstr.replace(filed, "?");
                list.add(IdWorker.getId());
                types.add(Types.VARCHAR);
            } else {
                Object expression = Ognl.parseExpression(ReversalFormatBeForeTrans(filedName));
                Object value = Ognl.getValue(expression, context, context);
                if (value == null) {
                    sqlstr = sqlstr.replace(filed, "null");
                } else {
                    if (value.toString().toLowerCase().equals("null")) {
                        sqlstr = sqlstr.replace(filed, "null");
                    } else if (!StringUtils.isNotBlank(value.toString())) {
                        sqlstr = sqlstr.replace(filed, "null");
                    } else {
                        sqlstr = sqlstr.replace(filed, "?");
                        list.add(value.toString().trim());
                        types.add(Types.VARCHAR);
                    }
                }
            }
        }
        sqlstr = ReversalFormatBeForeTrans(sqlstr);
        sqlstr = getAssembleSql(sqlstr);
        SqlParamVO sqlParam = new SqlParamVO();
        sqlParam.setSql(sqlstr);
        sqlParam.setParam(list);
        sqlParam.setTypes(types);
        return sqlParam;
    }

    ///重新组装sql 增加${} 自定义sql
    private String getAssembleSql(String sqlstr) throws Exception {
        Pattern p = Pattern.compile("\\$\\{([^}]*)\\}");
        Matcher m = p.matcher(sqlstr); // 获取 matcher 对象
        while (m.find()) {
            String filed = m.group();
            String filedName = filed.substring(2, filed.length() - 1);
            if (filedName.contains("$newid")) {
                Long newid = IdWorker.getId();
                sqlstr = sqlstr.replace(filed, newid.toString());
            } else {
                Object expression = Ognl.parseExpression(ReversalFormatBeForeTrans(filedName));
                Object value = Ognl.getValue(expression, context, context);
                if (value == null) {
                    sqlstr = sqlstr.replace(filed, "");
                } else {
                    if (value.toString().toLowerCase().equals("null")) {
                        sqlstr = sqlstr.replace(filed, "");
                    } else if (!StringUtils.isNotBlank(value.toString())) {
                        sqlstr = sqlstr.replace(filed, "");
                    } else {
                        sqlstr = sqlstr.replace(filed, value.toString().trim());

                    }
                }
            }
        }
        sqlstr = ReversalFormatBeForeTrans(sqlstr);
        return sqlstr;
    }

    private SqlParamVO getSqlParambyElement(Element node) throws Exception {
        return getSqlParambyElement(node, null);
    }

    private SqlParamVO getSqlParambyElement(Element node, Map<String, String> levelMap) throws Exception {
        Element ele = (Element) node;
        String itemouterXML = ele.asXML();
        String iteminnerXML = getConversionIndex(itemouterXML.replaceAll("^<sql.*?>|</sql>$", ""), levelMap);
        if (ele.attribute("test") == null) {
            String sqlstr = getSqlContent(node.elements(), context, iteminnerXML);
            SqlParamVO sqlParam = getSqlParam(sqlstr);
            return sqlParam;
        }
        String expressionStr = ele.attribute("test").getValue();
        expressionStr = getConversionIndex(expressionStr, levelMap);
        Object expression = Ognl.parseExpression(ReversalFormatBeForeTrans(expressionStr));
        // 解析树状表达式，返回结果
        Boolean expressionResult = (boolean) Ognl.getValue(expression, context, context);
        System.out.println(expressionStr + "结果" + expressionResult.toString());
        if (expressionResult == true) {
            String sqlstr = getSqlContent(node.elements(), context, iteminnerXML);
            SqlParamVO sqlParam = getSqlParam(sqlstr);
            return sqlParam;
        }
        return null;
    }

    public SqlParamVO getQuerySqlParam(String xmlStr) throws Exception {
        String outerXML = "";
        Element root;
        String innerXML = "";
        xmlStr = xmlStr.replaceAll("\r\n", " ");
        if (!StringUtils.isNotBlank(xmlStr)) {
            throw new Exception("sql配置参数不能为空！");
        }
        try {
            Document document2 = DocumentHelper.parseText(HandAttrSpecialChar("<select>" + xmlStr + "</select>"));
            root = document2.getRootElement();
            outerXML = root.asXML(); //得到 outerXML
            innerXML = outerXML.replaceAll("^<select.*?>|</select>$", "");
        } catch (Exception ex) {
            throw new Exception("sql配置xml格式错误！具体错误：" + ex.toString());
        }
        if (StringUtils.isNotBlank(root.getText())) {
            String sqlstr = getSqlContent(root.elements(), context, innerXML);
            SqlParamVO sqlParam = getSqlParam(sqlstr);
            return sqlParam;
        } else {
            List<Element> listElement = root.elements();
            for (Element node : listElement) {//遍历所有一级子节点
                if (node.getName().toLowerCase().equals("sql")) {
                    SqlParamVO sqlParam = getSqlParambyElement(node);
                    if (sqlParam != null) {
                        return sqlParam;
                    }
                }
            }
        }
        throw new Exception("未找到可执行的sql");
    }

    /***
     *  处理保存的方法
     * @param xmlStr
     * @return
     * @throws Exception
     */
    public void ExecuteOptSql(String xmlStr) throws Exception {
        String outerXML = "";
        Element root;
        String innerXML = "";
        if (!StringUtils.isNotBlank(xmlStr)) {
            throw new Exception("sql配置参数不能为空！");
        }
        xmlStr = xmlStr.replaceAll("\r\n", " ");
        try {
            Document document2 = DocumentHelper.parseText(HandAttrSpecialChar("<select>" + xmlStr + "</select>"));
            root = document2.getRootElement();
            outerXML = root.asXML(); //得到 outerXML
            innerXML = outerXML.replaceAll("^<select.*?>|</select>$", "");
        } catch (Exception ex) {
            throw new Exception("sql配置xml格式错误！具体错误：" + ex.toString());
        }
        if (StringUtils.isNotBlank(root.getText())) {
            String sqlstr = getSqlContent(root.elements(), context, innerXML);
            SqlParamVO sqlParam = getSqlParam(sqlstr);
            try {
                System.out.println(sqlParam.getSql());
                jdbcTemplate.update(sqlParam.getSql(), sqlParam.getParam().toArray(), sqlParam.getTypes().stream().mapToInt(Integer::valueOf).toArray());
            } catch (Exception ex) {
                throw new Exception("组装的SQL语句为：" + sqlParam.getSql() + " 具体错误信息：" + ex.toString());
            }
        }
        if (!StringUtils.isNotBlank(root.getText())) {
            Map<String, String> levelMap = new HashMap<>();
            ExecuteSql(root, levelMap, 0);
        }
    }

    void ExecuteSql(Element root, Map<String, String> levelMap, int leveIndex) throws Exception {
        List<Element> listElement = root.elements();
        for (Element e : listElement) {//遍历所有一级子节点
            if (e.getName().toLowerCase().equals("for")) {
                if (e.selectNodes("/if").size() > 0) {
                    throw new Exception("配置语法错误！for节点下不能嵌套if等节点。xml节点内容：" + e.asXML());
                }
                Boolean expressionResult = true;
                if (e.attribute("test") != null) {
                    Object expression = Ognl.parseExpression(ReversalFormatBeForeTrans(e.attribute("test").getValue()));
                    // 解析树状表达式，返回结果
                    expressionResult = (boolean) Ognl.getValue(expression, context, context);
                }
                if (expressionResult) {
                    if (e.attribute("forname") == null) {
                        throw new Exception("for的forname属性找不到");
                    }
                    Object expression = Ognl.parseExpression(getConversionIndex(e.attribute("forname").getValue(), levelMap));
                    List<Map<String, Object>> jsonArray = (List<Map<String, Object>>) Ognl.getValue(expression, context, context);
                    if (jsonArray != null) {
                        for (int i = 0; i < jsonArray.size(); i++) {
                            levelMap.put(leveIndex + "", i + "");
                            if(jsonArray.get(i) instanceof Map) {
                                jsonArray.get(i).put("$newid", IdWorker.getId());
                            }
                            ExecuteSql(e, levelMap, leveIndex + 1);
                        }
                    }
                }
            }
            else if (e.getName().toLowerCase().equals("sql")) {
                SqlParamVO sqlParam = getSqlParambyElement(e, levelMap);
                if (sqlParam != null) {
                    if (e.attribute("param") != null) {
                        ///将执行的sql结果放到该对象中
                        List<Map<String, Object>> datalist = new ArrayList<>();
                        try {
                            getOracleQuerySql(sqlParam);
                            if (sqlParam.getParam().size() > 0) {
                                System.out.println(sqlParam.getSql());
                                datalist = jdbcTemplate.queryForList(sqlParam.getSql(), sqlParam.getParam().toArray(), sqlParam.getTypes().stream().mapToInt(Integer::valueOf).toArray());
                            } else {
                                System.out.println(sqlParam.getSql());
                                datalist = jdbcTemplate.queryForList(sqlParam.getSql());
                            }
                        } catch (Exception ex) {
                            throw new Exception("组装的SQL语句为：" + sqlParam.getSql() + " 具体错误信息：" + ex.toString());
                        }
                        String paramname = getConversionIndex(e.attribute("param").getValue(), levelMap);
                        if (paramname.contains(".")) {
                            Object expression = Ognl.parseExpression(paramname.substring(0, paramname.lastIndexOf(".")));
                            Map<String, Object> paramvalue = (Map<String, Object>) Ognl.getValue(expression, context, context);
                            paramvalue.put(paramname.substring(paramname.lastIndexOf(".") + 1, paramname.length()), datalist);
                        } else {
                            context.put(paramname, datalist);
                        }
                        // context.put(paramname, datalist); //   param.opt(e.attribute("param"),)
                    } else {
                        try {
                            System.out.println(sqlParam.getSql());
                            jdbcTemplate.update(sqlParam.getSql(), sqlParam.getParam().toArray(), sqlParam.getTypes().stream().mapToInt(Integer::valueOf).toArray());
                        } catch (Exception ex) {
                            throw new Exception("组装的SQL语句为：" + sqlParam.getSql() + " 具体错误信息：" + ex.toString());
                        }
                    }
                }
            }else {
                throw new Exception("SQL语句语法错误,for循环必须包含<sql>标签");
            }
        }
    }

    ///将$index变量进行转化
    String getConversionIndex(String Sqlstr, Map<String, String> levelMap) {
        if (levelMap != null) {
            for (String key : levelMap.keySet()) {
                Sqlstr = Sqlstr.replace("$index" + key, levelMap.get(key));
            }
        }
        return Sqlstr;
    }

    /**
     * 判断是否Oracle数据库 或者达梦数据
     *
     * @return
     */
    public boolean IsOracleData() {
        try {
//            String driverclassName = jdbcTemplate.getDataSource().getConnection().getMetaData().getDriverName();
//            if (driverclassName.contains("dm.jdbc") || driverclassName.contains("oracle.jdbc")) {
//                return true;
//            }
            return false;

        } catch (Exception e) {
            return false;
        }
    }

    //如果是达梦数据库 或者Oracle数据 就需要将执行的查询sql语句进行转译 因为他们两者输出后的字段都是大写。 为了兼容达梦数据库 和Oracle数据
    public void getOracleQuerySql(SqlParamVO sqlParam) {
        if (IsOracleData()) {
            try {
                List<String> columnlist = getColumninfo(sqlParam);
                ArrayList<String> sqlcolumn = new ArrayList<String>();
                columnlist.forEach(ee ->
                {
                    sqlcolumn.add(ee.toString() + " as \"" + ee.toLowerCase() + "\"");
                });
                sqlParam.setSql("select " + String.join(",", sqlcolumn) + " from (" + sqlParam.getSql() + ") as data_table");
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

    }

    public List<Map<String, Object>> queryForList(String xmlStr) throws Exception {
        SqlParamVO sqlParam = getQuerySqlParam(xmlStr);
        try {
            getOracleQuerySql(sqlParam);
            if (sqlParam.getParam().size() > 0) {
                System.out.println(sqlParam.getSql());
                return jdbcTemplate.queryForList(sqlParam.getSql(), sqlParam.getParam().toArray(), sqlParam.getTypes().stream().mapToInt(Integer::valueOf).toArray());
            } else {
                System.out.println(sqlParam.getSql());
                return jdbcTemplate.queryForList(sqlParam.getSql());
            }
        } catch (Exception ex) {
            throw new Exception("组装的SQL语句为：" + sqlParam.getSql() + " 具体错误信息：" + ex.toString());
        }
    }


    public Map<String, Object> queryForObject(String xmlStr) throws Exception {
        SqlParamVO sqlParam = getQuerySqlParam(xmlStr);
        try {
            getOracleQuerySql(sqlParam);
            if (sqlParam.getParam().size() > 0) {
                System.out.println(sqlParam.getSql());
                return jdbcTemplate.queryForMap(sqlParam.getSql(), sqlParam.getParam().toArray(), sqlParam.getTypes().stream().mapToInt(Integer::valueOf).toArray());
            } else {
                System.out.println(sqlParam.getSql());
                return jdbcTemplate.queryForMap(sqlParam.getSql());
            }
        } catch (Exception ex) {
            if (ex.toString().contains("Incorrect result size: expected 1, actual 0")) {
                return null;
            } else {
                throw new Exception("组装的SQL语句为：" + sqlParam.getSql() + " 具体错误信息：" + ex.toString());
            }
        }
    }

    /**
     * 返回一个对象
     *
     * @param xmlStr
     * @param tClass
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> T queryForObject(String xmlStr, Class<T> tClass) throws Exception {
        SqlParamVO sqlParam = getQuerySqlParam(xmlStr);
        try {
            getOracleQuerySql(sqlParam);
            if (sqlParam.getParam().size() > 0) {
                System.out.println(sqlParam.getSql());
                return jdbcTemplate.queryForObject(sqlParam.getSql(), sqlParam.getParam().toArray(), sqlParam.getTypes().stream().mapToInt(Integer::valueOf).toArray(), tClass);
            } else {
                System.out.println(sqlParam.getSql());
                return jdbcTemplate.queryForObject(sqlParam.getSql(), tClass);
            }
        } catch (Exception ex) {
            throw new Exception("组装的SQL语句为：" + sqlParam.getSql() + " 具体错误信息：" + ex.toString());
        }
    }

    /**
     * 查询list表信息
     *
     * @param xmlStr
     * @param tClass
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> List<T> queryForList(String xmlStr, Class<T> tClass) throws Exception {
        SqlParamVO sqlParam = getQuerySqlParam(xmlStr);
        try {
            getOracleQuerySql(sqlParam);
            if (sqlParam.getParam().size() > 0) {
                System.out.println(sqlParam.getSql());
                return jdbcTemplate.queryForList(sqlParam.getSql(), sqlParam.getParam().toArray(), sqlParam.getTypes().stream().mapToInt(Integer::valueOf).toArray(), tClass);
            } else {
                System.out.println(sqlParam.getSql());
                return jdbcTemplate.queryForList(sqlParam.getSql(), tClass);
            }
        } catch (Exception ex) {
            throw new Exception("组装的SQL语句为：" + sqlParam.getSql() + " 具体错误信息：" + ex.toString());
        }
    }

    /**
     * 获取分页对象
     *
     * @param xmlStr
     * @return
     * @throws Exception
     */
    public <T> FormPageVO<T> queryPageForList(String xmlStr, Class<T> tClass) throws Exception {
        if (context.get("page") != null && context.get("page").toString() != "") {
            int page = Integer.parseInt(context.get("page").toString());
            int rows = Integer.parseInt(context.get("rows").toString());
            SqlParamVO sqlParam = getQuerySqlParam(xmlStr);
            getOracleQuerySql(sqlParam);
            String sqlstr = sqlParam.getSql();
            FormPageVO<T> formPageVO = new FormPageVO<T>();
            String countSql = "select Count(*) from  (" + sqlParam.getSql() + ") counttable_ ";
            if (sqlParam.getParam().size() > 0) {
                System.out.println(countSql);
                final int rowCount = jdbcTemplate.queryForObject(countSql, sqlParam.getParam().toArray(), sqlParam.getTypes().stream().mapToInt(Integer::valueOf).toArray(), Integer.class);
                formPageVO.setTotal(rowCount / rows + (rowCount % rows != 0 ? 1 : 0));
                if (page <= 1) {
                    sqlstr += " limit 0," + rows;
                } else {
                    if(page>formPageVO.getTotal()){
                        page=formPageVO.getTotal();
                    }
                    sqlstr += " limit " + ((page - 1) * rows) + "," + rows;
                }
                System.out.println(sqlstr);
                List<T> datalist = jdbcTemplate.queryForList(sqlstr, sqlParam.getParam().toArray(), sqlParam.getTypes().stream().mapToInt(Integer::valueOf).toArray(), tClass);
                formPageVO.setRecords(rowCount);
                formPageVO.setPage(page);

                formPageVO.setRows(datalist);
                formPageVO.setSuccess(true);
                return formPageVO;
            } else {
                System.out.println(countSql);
                final int rowCount = jdbcTemplate.queryForObject(countSql, Integer.class);
                formPageVO.setTotal(rowCount / rows + (rowCount % rows != 0 ? 1 : 0));
                System.out.println(sqlstr);
                if (page <= 1) {
                    sqlstr += " limit 0," + rows;
                } else {
                    if(page>formPageVO.getTotal()){
                        page=formPageVO.getTotal();
                    }
                    sqlstr += " limit " + ((page - 1) * rows) + "," + rows;
                }
                List<T> datalist = jdbcTemplate.queryForList(sqlstr, tClass);
                formPageVO.setRecords(rowCount);
                formPageVO.setPage(page);
                formPageVO.setRows(datalist);
                formPageVO.setSuccess(true);
                return formPageVO;
            }
        } else {
            throw new Exception("分页参数不能为空");

        }
    }

    /**
     * 获取分页对象
     *
     * @param xmlStr
     * @return
     * @throws Exception
     */
    public FormPageVO<Map<String, Object>> queryPageForList(String xmlStr) throws Exception {
        if (context.get("page") != null && context.get("page").toString() != "") {
            int page = Integer.parseInt(context.get("page").toString());
            int rows = Integer.parseInt(context.get("rows").toString());
            SqlParamVO sqlParam = getQuerySqlParam(xmlStr);
            getOracleQuerySql(sqlParam);
            String sqlstr = sqlParam.getSql();
            FormPageVO<Map<String, Object>> formPageVO = new FormPageVO<Map<String, Object>>();
            String countSql = "select Count(*) from  (" + sqlParam.getSql() + ") counttable_ ";
            if (sqlParam.getParam().size() > 0) {
                System.out.println(countSql);
                final int rowCount = jdbcTemplate.queryForObject(countSql, sqlParam.getParam().toArray(), sqlParam.getTypes().stream().mapToInt(Integer::valueOf).toArray(), Integer.class);
                formPageVO.setTotal(rowCount / rows + (rowCount % rows != 0 ? 1 : 0));
                if (page <= 1) {
                    sqlstr += " limit 0," + rows;
                } else {
                    if(page>formPageVO.getTotal()){
                        page=formPageVO.getTotal();
                    }
                    sqlstr += " limit " + ((page - 1) * rows) + "," + rows;
                }
                List<Map<String, Object>> datalist = jdbcTemplate.queryForList(sqlstr, sqlParam.getParam().toArray(), sqlParam.getTypes().stream().mapToInt(Integer::valueOf).toArray());
                formPageVO.setRecords(rowCount);
                formPageVO.setPage(page);
                formPageVO.setRows(datalist);
                formPageVO.setSuccess(true);
                return formPageVO;
            } else {
                System.out.println(countSql);
                final int rowCount = jdbcTemplate.queryForObject(countSql, Integer.class);
                formPageVO.setTotal(rowCount / rows + (rowCount % rows != 0 ? 1 : 0));
                if (page <= 1) {
                    sqlstr += " limit 0," + rows;
                } else {
                    if(page>formPageVO.getTotal()){
                        page=formPageVO.getTotal();
                    }
                    sqlstr += " limit " + ((page - 1) * rows) + "," + rows;
                }
                System.out.println(sqlstr);
                List<Map<String, Object>> datalist = jdbcTemplate.queryForList(sqlstr);
                formPageVO.setRecords(rowCount);
                formPageVO.setPage(page);
                formPageVO.setRows(datalist);
                formPageVO.setSuccess(true);
                return formPageVO;
            }
        } else {
            throw new Exception("分页参数不能为空");

        }
    }


    /**
     * 验证参数格式是否正确
     *
     * @param paramlist
     * @return
     * @throws OgnlException
     */
    public R<String> bizformdata(List<ParamBizFromVO> paramlist) throws OgnlException {
        R<String> result = new R<String>();
        for (ParamBizFromVO paramitem : paramlist) {

            if (paramitem.getVariabletype().equals("常量")) {
                Object jsonValue = GetJsonValue(paramitem.getProperty());
                result = ValidateUtils.ValidateParam(paramitem, jsonValue);
                if (result.getCode() != 0)
                    return result;
            } else if (paramitem.getVariabletype().equals("Object")) {
                result = bizChildFormdata(paramitem.getChildren(), paramitem.getProperty());
                if (result.getCode() != 0)
                    return result;
            } else if (paramitem.getVariabletype().equals("Array")) {
                JSONArray rowdata = (JSONArray) GetJsonValue(paramitem.getProperty());
                if (paramitem.getRequired() && rowdata == null) {
                    return result.newError("至少一行！");
                }
                if (rowdata == null) {
                    continue;
                }
                for (int i = 0; i < rowdata.size(); i++) {
                    result = bizChildFormdata(paramitem.getChildren(), paramitem.getProperty() + "[" + i + "]");
                    if (result.getCode() != 0)
                        return result;
                }

            }

        }
        return result.newOk("成功！");
    }

    /**
     * 验证参数是否必填
     *
     * @param paramlist
     * @param parentProperty
     * @return
     * @throws OgnlException
     */
    public R<String> bizChildFormdata(List<ParamBizFromVO> paramlist, String parentProperty) throws OgnlException {
        R<String> result = new R<String>();
        if (paramlist != null) {
            for (ParamBizFromVO paramitem : paramlist) {
                if (paramitem.getRequired()) {
                    if (paramitem.getVariabletype().equals("常量")) {
                        Object jsonValue = GetJsonValue(parentProperty + "." + paramitem.getProperty());
                        result = ValidateUtils.ValidateParam(paramitem, jsonValue);
                        if (result.getCode() != 0)
                            return result;
                    } else if (paramitem.getVariabletype().equals("Object")) {
                        result = bizChildFormdata(paramitem.getChildren(), parentProperty + "." + paramitem.getProperty());
                        if (result.getCode() != 0)
                            return result;
                    } else if (paramitem.getVariabletype().equals("Array")) {
                        JSONArray rowdata = (JSONArray) GetJsonValue(paramitem.getProperty());
                        if (paramitem.getRequired() && rowdata == null) {
                            return result.newError("至少一行！");
                        }
                        if (rowdata == null) {
                            continue;
                        }
                        for (int i = 0; i < rowdata.size(); i++) {
                            result = bizChildFormdata(paramitem.getChildren(), paramitem.getProperty() + "[" + i + "]");
                            if (result.getCode() != 0)
                                return result;
                        }
                    }
                }
            }
        }
        return R.newOk();
    }
}
