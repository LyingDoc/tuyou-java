package com.ruoyi.flow.db;


import com.ruoyi.flow.vo.HeadEntityVO;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public class ExportExcel {
    /**
     * 创建excel表
     */
    public static HSSFWorkbook CreateExcel(String title, List<Map<String,Object>> dataList, List<HeadEntityVO> headList) throws IOException {
        //创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        creatSheet(wb, title,dataList,headList);

        return wb;
    }

    public static HSSFWorkbook creatSheet(HSSFWorkbook wb, String sheetName, List<Map<String,Object>> dataList, List<HeadEntityVO> headList){
        //建立新的sheet对象（excel的表单）
        HSSFSheet sheet = wb.createSheet(sheetName);
        //
        HSSFCellStyle styleHead = creatStyle(wb, "宋体", 12, true,true);

        //第三行
        HSSFRow row2 =sheet.createRow(0);

       for(int i=0;i<headList.size();i++) {
           sheet.setColumnWidth(i,headList.get(i).getHeadname().length()*5*256);
           HSSFCell cell2_0 = row2.createCell(i);

           cell2_0.setCellStyle(styleHead);
           cell2_0.setCellValue(headList.get(i).getHeadname());
       }
        //第四行
        int rowNumber = 1;
        for (Map<String,Object> rowdataMap : dataList) {
            HSSFRow row3 =sheet.createRow(rowNumber);
            for(int i=0;i<headList.size();i++) {
                HSSFCell cell3_0 = row3.createCell(i);
                Object value=rowdataMap.get(headList.get(i).getBindname());
                if(value!=null) {
                    cell3_0.setCellValue(value.toString());
                }
            }
            //
            rowNumber++;
        }
        sheet.addMergedRegion(new CellRangeAddress(rowNumber,rowNumber,0,18));
        return wb;
    }

    /**
     *
     * @param wb
     * @param fontName 字体名称
     * @param fontHeightInPoints 字体大小
     * @param
     * @param border 是否有边框
     * @return
     */
    public static HSSFCellStyle creatStyle(HSSFWorkbook wb, String fontName, int fontHeightInPoints, boolean border, boolean boldweight){
        //设置内容字体
        HSSFFont fontContent = wb.createFont();
        fontContent.setFontName(fontName);
        //创建内容样式
        HSSFCellStyle styleContent = wb.createCellStyle();
        styleContent.setFont(fontContent);
        styleContent.setWrapText(true);
        styleContent.setAlignment(HorizontalAlignment.CENTER);
        styleContent.setFillForegroundColor(HSSFColor.BRIGHT_GREEN.index);
        styleContent.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        styleContent.setFillBackgroundColor(HSSFColor.BRIGHT_GREEN.index);
        if (border){
         //   styleContent.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            styleContent.setBottomBorderColor(HSSFColor.BLACK.index);
           // styleContent.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            styleContent.setLeftBorderColor(HSSFColor.BLACK.index);
          //  styleContent.setBorderRight(HSSFCellStyle.BORDER_THIN);
            styleContent.setRightBorderColor(HSSFColor.BLACK.index);
          //  styleContent.setBorderTop(HSSFCellStyle.BORDER_THIN);
            styleContent.setTopBorderColor(HSSFColor.BLACK.index);
        }
        return styleContent;
    }
}
