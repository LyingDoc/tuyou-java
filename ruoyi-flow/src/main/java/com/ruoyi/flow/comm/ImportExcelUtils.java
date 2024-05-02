package com.ruoyi.flow.comm;


import com.alibaba.fastjson2.JSON;
import com.ruoyi.flow.db.ValidateUtils;
import com.ruoyi.flow.vo.ParamBizFromVO;
import com.ruoyi.flow.vo.R;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ImportExcelUtils {
    private final static String excel2003L =".xls";    //2003- 版本的excel  
    private final static String excel2007U =".xlsx";   //2007+ 版本的excel  

    public static Map getBankListByExcel(InputStream in, String fileName,List<Integer> excelTitleRowNums) throws Exception{
        return getBankListByExcel(in,fileName,excelTitleRowNums,"");
    }
    /** 
     * 描述：获取IO流中的数据，组装成List<List<Object>>对象 
     * @param in,fileName 
     * @return 
     * @throws IOException  
     */  
    public static Map getBankListByExcel(InputStream in, String fileName,List<Integer> excelTitleRowNums,String paramconfig) throws Exception{
        Map returnMap = new LinkedHashMap();
        returnMap.put("code",0);
        //创建Excel工作薄  
        Workbook work = getWorkbook(in,fileName);
        int excelTitleRowNumsSize = excelTitleRowNums.size();
        if(null == work){
            returnMap.put("code",1);
            returnMap.put("msg","导入的Excel工作薄为空！");
            return  returnMap;

        }
        if(excelTitleRowNumsSize > work.getNumberOfSheets()){
            returnMap.put("code",1);
            returnMap.put("msg","sheet页标题行输入有误,长度大于sheet页面数量！");
            return  returnMap;
        }
        if(excelTitleRowNumsSize < work.getNumberOfSheets()){
            for (int i = 0; i < work.getNumberOfSheets()-excelTitleRowNumsSize; i++) {
                excelTitleRowNums.add(1);
            }
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;
        Map<String, Map<String, ParamBizFromVO>> mapparam=null;
        if(StringUtils.isNotBlank(paramconfig)){
            List<ParamBizFromVO> list= JSON.parseArray(paramconfig, ParamBizFromVO.class);
            if(list.size()>0){
                mapparam=new HashMap<>();
            }
            for (ParamBizFromVO item : list){
                if(item.getParamname().contains(";")){
                 String[] paramnames=  item.getParamname().split(";");
                   if(mapparam.containsKey(paramnames[0])){
                       mapparam.get(paramnames[0]).put(paramnames[1],item);
                   }else{
                       Map<String, ParamBizFromVO> paramitem=new HashMap<>();
                       paramitem.put(paramnames[1],item);
                       mapparam.put(paramnames[0],paramitem);
                   }
                }
            }
        }
        //遍历Excel中所有的sheet  
        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            List<Map<String,String>> list = new ArrayList<>();;
            sheet = work.getSheetAt(i);  
            if(sheet==null){continue;}
            if(sheet.getLastRowNum()==0){continue;}
            ArrayList<String> listKey = new ArrayList<>();
            if((excelTitleRowNums.get(i)-1)>sheet.getLastRowNum()){
                returnMap.put("code",1);
                returnMap.put("msg","sheet页标题行输入有误，有行号大于excel表高度！");
                return  returnMap;
            }
            //拿到模板行
            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
                row = sheet.getRow(j);
                if(row==null){continue;}
                if(j==(excelTitleRowNums.get(i)-1)){
                    for (int k = row.getFirstCellNum(); k <row.getLastCellNum(); k++) {
                        cell = row.getCell(k);
                        if(cell==null){continue;}
                        listKey.add(getCellValue(cell)+"");
                    }
                }
            }
            //遍历当前sheet中的所有行，拿到非模板行
            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
                row = sheet.getRow(j);
                if(row==null){continue;}
                if(j!=(excelTitleRowNums.get(i)-1)){
                    Map<String, String> map = new LinkedHashMap<>();
                    for (int k = row.getFirstCellNum(); k <row.getFirstCellNum()+listKey.size(); k++) {
                        cell = row.getCell(k);
                        if(cell==null){continue;}
                        ////添加每行数据的验证
                        if(mapparam!=null) {
                            if(mapparam.containsKey(sheet.getSheetName())) {
                                ParamBizFromVO parambiz = mapparam.get(sheet.getSheetName()).get(listKey.get(k));
                              if(parambiz!=null) {
                                  R<String> result = ValidateUtils.ValidateParam(parambiz, getCellValue(cell));
                                  if (result.getCode() != 0) {
                                      returnMap.put("code",1);
                                      returnMap.put("msg","SheetName:" + sheet.getSheetName() + "第" + j + "行[" + listKey.get(k) + "]格式不正确或者为空！具体错误信息为：" + result.getMsg());
                                      return  returnMap;
                                  }
                              }
                            }
                        }
                        map.put(listKey.get(k),getCellValue(cell)+"");
                    }
                    list.add(map);
                }
            }
            returnMap.put(sheet.getSheetName(),list);
        }
        return returnMap;
        }

    /** 
     * 描述：根据文件后缀，自适应上传文件的版本  
     * @param inStr,fileName 
     * @return 
     * @throws Exception 
     */  
    public static Workbook getWorkbook(InputStream inStr, String fileName) throws Exception{
        Workbook wb = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));  
        if(excel2003L.equals(fileType)){  
            wb = new HSSFWorkbook(inStr);  //2003-
        }else if(excel2007U.equals(fileType)){  
            wb = new XSSFWorkbook(inStr);  //2007+
        }else{  
            throw new Exception("解析的文件格式有误！");  
        }  
        return wb;  
    }  


    /** 
     * 描述：对表格中数值进行格式化 
     * @param cell 
     * @return 
     */  
    public static Object getCellValue(Cell cell){
        Object value = null;  
        DecimalFormat df = new DecimalFormat("0");  //格式化number String字符
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");  //日期格式化
        DecimalFormat df2 = new DecimalFormat("0.00");  //格式化数字  

        switch (cell.getCellType()) {
        case Cell.CELL_TYPE_STRING:
            value = cell.getRichStringCellValue().getString();  
            break;  
        case Cell.CELL_TYPE_NUMERIC:
            if("General".equals(cell.getCellStyle().getDataFormatString())){  
                value = df.format(cell.getNumericCellValue());  
            }else if("m/d/yy".equals(cell.getCellStyle().getDataFormatString())){  
                value = sdf.format(cell.getDateCellValue());  
            }else{  
                value = df2.format(cell.getNumericCellValue());  
            }  
            break;  
        case Cell.CELL_TYPE_BOOLEAN:
            value = cell.getBooleanCellValue();  
            break;  
        case Cell.CELL_TYPE_BLANK:
            value = "";  
            break;  
        default:  
            break;  
        }  
        return value;  
    }

    public static String getExcelFileToJsonStr(String filePath) {

        BufferedReader reader = null;
        String excelJsonStr = "";
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                excelJsonStr += tempString;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return excelJsonStr;
    }

}