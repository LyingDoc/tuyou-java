package com.ruoyi.flow.oa.service.impl;

import com.ruoyi.flow.oa.entity.OaActroutingEntity;
import com.ruoyi.flow.oa.vo.ProcInstHandleEntity;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ProcRouteComm {
    private ProcInstHandleEntity ProcInst;
    private int ActionValue;
    private ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
    FlowCommon flowCommon;
    public ProcRouteComm(ProcInstHandleEntity procInst, int actionValue){
        ProcInst=procInst;
        ActionValue=actionValue;
        flowCommon=new FlowCommon(procInst);
    }
    /// <summary>
    /// 路由解析 得到下一节点ID
    /// </summary>
    /// <returns></returns>
    public String Route() throws ScriptException
    {
        for (int i=0;i<ProcInst.getTappActroutingList().size();i++) {
               OaActroutingEntity item=ProcInst.getTappActroutingList().get(i);
               JSONArray routingJSON= JSONArray.fromObject(item.getRoutingjson()) ;
               JSONArray expressionJson= JSONArray.fromObject(item.getExpressionjson());
               Boolean isresult=true;
               for(int j=0;j<expressionJson.size();j++){
                   JSONObject expressionitem=expressionJson.getJSONObject(j);
                   boolean routing1= RunRouting(expressionitem.getInt("where")-1,routingJSON);
                   boolean routing2= RunRouting(expressionitem.getInt("toWhere")-1,routingJSON);
                   String operator2=expressionitem.getString("operator2");
                   String operator=expressionitem.getString("operator");
                   Boolean itemresult=true;
                   switch (operator2){
                       case "and":itemresult=routing1&&routing2;break;
                       case "or":itemresult=routing1||routing2;break;
                   }
                   switch (operator){
                       case "and":isresult=isresult&&itemresult;break;
                       case "or":isresult=isresult||itemresult;break;
                       default:isresult=itemresult;break;
                   }
               }
               if(isresult){
                   ProcInst.setHandleType(item.getHandleType());
                   ProcInst.setApiUrl(item.getApiUrl());
                   ProcInst.setAjaxType(item.getAjaxType());
                   ProcInst.setSqlcontent(item.getSqlcontent());
                  return item.getRunActId();
               }

        }
        return null;
    }
    private  boolean RunRouting(int idex, JSONArray routingJSON) throws ScriptException {
        JSONObject routingitem=routingJSON.getJSONObject(idex);
        String variableName= routingitem.getString("variableName");
        String variable=  routingitem.getString("variable");
        String operator=  routingitem.getString("operator");
        String value=routingitem.getString("value");
        String variablevalue="";
//        if(!variableName.contains("@")){
//            variable = variable.substring(3, variable.length());
//        }
        variablevalue=flowCommon.controlVlaue(variable);
        if(variablevalue==null){
            return false;
        }
        String operation="";
        Boolean operatResult=false;
        switch (operator){
            case "=":operatResult=variablevalue.equals(value);break;
            case ">=":
            case ">":
            case "<=":
            case "<": operation=variablevalue+operator+value; break;
            case "like":operatResult=variablevalue.contains(value);  break;
        }
        if(operation.length()==0){
            return operatResult;
        }else
        {
            try {
                return (boolean) engine.eval(operation);
            }catch (Exception ex){
                ProcInst.getRunlogList().add("路由【"+operation+"】异常！");
               return false;
            }
        }
    }

}
