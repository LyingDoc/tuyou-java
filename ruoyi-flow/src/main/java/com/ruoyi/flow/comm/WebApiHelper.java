package com.ruoyi.flow.comm;



import net.sf.json.JSONObject;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class WebApiHelper {
    /**
     *
     * @param url 请求url地址
     * @param headMap 头部配置信息
     * @param paramMap url参数链接信息
     * @return
     * @throws Exception
     */
    public static String AjaxGet(String url, JSONObject headMap, JSONObject paramMap) throws Exception {
        /**
         * 1.生成HttpClient对象并设置参数
         */
        HttpClient httpClient = new HttpClient();
        // 设置Http连接超时为5秒
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);

        /**
         * 2.生成GetMethod对象并设置参数
         */
        url= FormatUrl(url,paramMap);
        GetMethod getMethod = new GetMethod(url);
        getMethod.addRequestHeader("Content-Type", "application/json;charset=utf-8");
        // 必须设置下面这个Header
        getMethod.addRequestHeader("User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36");
        if(headMap!=null) {
            for (Object key : headMap.names()) {
                getMethod.addRequestHeader(key.toString(), headMap.get(key).toString());
            }
        }

        // 设置get请求超时为5秒
        getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
        // 设置请求重试处理，用的是默认的重试处理：请求三次
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());

        String response = "";

        /**
         * 3.执行HTTP GET 请求
         */
        try {
            int statusCode = httpClient.executeMethod(getMethod);

            /**
             * 4.判断访问的状态码
             */
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("请求出错：" + getMethod.getStatusLine());
            }

            /**
             * 5.处理HTTP响应内容
             */
            // HTTP响应头部信息，这里简单打印
            Header[] headers = getMethod.getResponseHeaders();
            for (Header h : headers) {
                System.out.println(h.getName() + "---------------" + h.getValue());
            }
            // 读取HTTP响应内容，这里简单打印网页内容
            // 读取为字节数组
            byte[] responseBody = getMethod.getResponseBody();
            response = new String(responseBody, "utf-8");
            System.out.println("-----------response:" + response);
            // 读取为InputStream，在网页内容数据量大时候推荐使用
            // InputStream response = getMethod.getResponseBodyAsStream();

        } catch (HttpException e) {
            // 发生致命的异常，可能是协议不对或者返回的内容有问题
            System.out.println("请检查输入的URL!");
            e.printStackTrace();
        } catch (IOException e) {
            // 发生网络异常
            System.out.println("发生网络异常!");
        } finally {
            /**
             * 6.释放连接
             */
            getMethod.releaseConnection();
        }
        return response;
    }

    /**
     *
     * @param url 请求url地址
     * @return
     * @throws Exception
     */
    public static String AjaxGet(String url)throws Exception{
        return AjaxGet(url,null,null);
    }

    /**
     *
     * @param url 请求url地址
     * @param headMap 头部信息
     * @return
     * @throws Exception
     */
    public static String AjaxGet(String url, JSONObject headMap)throws Exception{
        return AjaxGet(url,headMap,null);
    }
    public static String AjaxGet(String url,String headMap)throws Exception{
        JSONObject headJson=new JSONObject();
        if(!(headMap.equals("[]")||!StringUtils.isNotBlank(headMap))){
            headJson= JSONObject.fromObject(headMap);
        }
        return AjaxGet(url,headJson,null);
    }

    /**
     *
     * @param url 请求url地址
     * @param paramMap 头部信息
     * @return
     * @throws Exception
     */
    private static  String FormatUrl(String url, JSONObject paramMap) throws Exception
    {
        if (!StringUtils.isNotBlank(url))
        {
            return "";
        }
        if (paramMap != null )
        {
            StringBuilder sb = new StringBuilder();
            for (Object key : paramMap.names()) {
                sb.append(key+"="+paramMap.get(key)+"&");
            }
            String paramStr = sb.toString().trim();
            if(!StringUtils.isNotBlank(paramStr)){
                return url;
            }
            paramStr =paramStr.substring(0,paramStr.length()-1);
            if (url.contains("?"))
            {
                url += "&" + paramStr;
            }
            else
            {
                url += "?" + paramStr;
            }
        }
        if (url.contains("http"))
        {
            return url;
        }
        throw new Exception("url地址不正确");
    }

    public static String ParamUrl(String url, HttpServletRequest request){
        if(url.contains("?")){
            String[] urlarry=url.split("\\?");
            String[] paramlist= urlarry[1].split("&");
            String newparam="";
            for (String param:paramlist) {
                if(StringUtils.isNotBlank(param)&&param.contains("=")){
                    String[] itemparams=param.split("=");
                    String requestParam= request!=null? request.getParameter(itemparams[0]):"";
                    if(StringUtils.isNotBlank(requestParam)){
                        newparam= StringUtils.isNotBlank(newparam)? newparam+"&"+itemparams[0]+"="+requestParam:newparam+itemparams[0]+"="+requestParam;
                    }else{
                        if(itemparams[1].contains("$time")){
                            newparam= StringUtils.isNotBlank(newparam)? newparam+"&"+itemparams[0]+"="+VariableValue(itemparams[1]):newparam+itemparams[0]+"="+VariableValue(itemparams[1]);
                        }else {
                            newparam = StringUtils.isNotBlank(newparam) ? newparam + "&" + param : newparam + param;
                        }
                    }
                }else{
                    newparam= StringUtils.isNotBlank(newparam)?newparam+"&"+param:newparam+param;
                }

            }
            return urlarry[0]+"?"+newparam;
        }
        return url;
    }
    public static String VariableValue(String itemparamval){
        String[] variablestr=itemparamval.split("|");
        if(variablestr.length>2){
            if(variablestr[0]=="$time"){
                Calendar c = Calendar.getInstance();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
                //过去七天
                c.setTime(new Date());
                Date time;
                String timestr;
                switch (variablestr[2].toLowerCase()){
                    case "day":c.add(Calendar.DATE, Integer.parseInt((variablestr[1])));
                        time = c.getTime();
                        timestr = format2.format(time);
                        return timestr;
                    case "month":  c.add(Calendar.MONTH, Integer.parseInt((variablestr[1])));
                        time = c.getTime();
                        timestr = format2.format(time);
                        return timestr;
                    case "year":  c.add(Calendar.YEAR, Integer.parseInt((variablestr[1])));
                        time = c.getTime();
                        timestr = format2.format(time);
                        return timestr;
                    case "hour":  c.add(Calendar.HOUR, Integer.parseInt((variablestr[1])));
                        time = c.getTime();
                        timestr = format.format(time);
                        return timestr;
                    case "minute":  c.add(Calendar.MINUTE, Integer.parseInt((variablestr[1])));
                        time = c.getTime();
                        timestr = format.format(time);
                        return timestr;
                    default:  time = c.getTime();
                        timestr = format.format(time);
                        return timestr;
                }
            }
            return "";
        }
        if(itemparamval=="$time"){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return format.format(new Date());
        }
        return "";
    }
    public  static String Postjson(String postjson, JSONObject param){
        JSONObject postObject= JSONObject.fromObject(postjson);
        for (Object keyobj :postObject.names()){
            String keyname=keyobj.toString();
            String itemobj=  param.optString(keyname);
            if(StringUtils.isNotBlank(itemobj)){
                postObject.put(keyname,itemobj);
            }
        }
        return com.alibaba.fastjson2.JSONObject.toJSONString(postObject);
    }

    public static String AjaxStrPost(String url,String headMap, String postjson,String paramMap) throws Exception {
        return AjaxPost(url, JSONObject.fromObject(headMap),postjson, JSONObject.fromObject(paramMap));
    }
    public static String AjaxStrPost(String url,String headMap, String postjson) throws Exception {
        JSONObject headJson=new JSONObject();
        if(!(headMap.equals("[]")||!StringUtils.isNotBlank(headMap))){
            headJson= JSONObject.fromObject(headMap);
        }
        return AjaxPost(url,headJson,postjson,null);
    }
    /**
     *
     * @param url 请求url地址
     * @param headMap head头部信息
     * @param postjson post参数
     * @param paramMap url参数信息
     * @return
     * @throws Exception
     */
    public static String AjaxPost(String url, JSONObject headMap, String postjson, JSONObject paramMap) throws Exception {
        HttpClient httpClient = new HttpClient();
        url= FormatUrl(url,paramMap);
        PostMethod postMethod = new PostMethod(url);
        postMethod.addRequestHeader("authorization", "Basic dG91cjp0b3Vy");
        postMethod.addRequestHeader("accept", "*/*");
        postMethod.addRequestHeader("connection", "Keep-Alive");
        // 设置json格式传送
        postMethod.addRequestHeader("Content-Type", "application/json;charset=utf-8");
        // 必须设置下面这个Header
        postMethod.addRequestHeader("User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36");
        // 添加请求参数
        if(headMap!=null) {
            for (Object key : headMap.names()) {
                postMethod.addRequestHeader(key.toString(), headMap.get(key).toString());
            }
        }
        if(StringUtils.isNotBlank(postjson)) {
            RequestEntity requestEntity = new StringRequestEntity(postjson);
            postMethod.setRequestEntity(requestEntity);
        }

        String res = "";
        try {
            int code = httpClient.executeMethod(postMethod);
            if (code == 200) {
                res = new String(postMethod.getResponseBody(), "utf-8");
                return res;
            } else if(code==500) {
                res = new String(postMethod.getResponseBody(), "utf-8");
                JSONObject resultJsonObject = JSONObject.fromObject(res);
                throw new Exception(resultJsonObject.optString("message"));
            }else {
                res = postMethod.getResponseBodyAsString();
                throw new Exception(res);
            }
        } catch (IOException e) {

            e.printStackTrace();
            throw e;
        }

    }

    /**
     *
     * @param url 请求url地址
     * @return
     * @throws Exception
     */
    public static String AjaxPost(String url)throws Exception {
        return AjaxPost(url,null,null,null);
    }

    /**
     *
     * @param url  请求url地址
     * @param postjson postJson
     * @return
     * @throws Exception
     */
    public static String AjaxPost(String url,String postjson)throws Exception {
        return AjaxPost(url,null,postjson,null);
    }

    /**
     *
     * @param url 请求url地址
     * @param headMap  head头部信息
     * @param postjson postJson
     * @return
     * @throws Exception
     */
    public static String AjaxPost(String url, JSONObject headMap, String postjson)throws Exception {
        return AjaxPost(url,headMap,postjson,null);
    }

    /**
     *
     * @param url 链接地址
     * @param postjson postJson
     * @param paramMap url参数
     * @return
     * @throws Exception
     */
    public static String AjaxPost(String url, String postjson, JSONObject paramMap)throws Exception {
        return AjaxPost(url,null,postjson,paramMap);
    }
}
