package com.ruoyi.flow.form.service;

import net.sf.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface IImportExcelService {
    
    /**
     * 获取导入的Excel表中数据
     * @param file 文件
     * @param req 
     * @param resp
     * @return 返回集合
     */
    public Map importExcelWithSimple(MultipartFile file, List<Integer> excelTitleRowNums, HttpServletRequest req, HttpServletResponse resp);
    /**
     * 获取导入的Excel表中数据
     * @param file 文件
     * @param req
     * @param resp
     * @return ExcelJSON
     */
    public JSONObject importExcelJSON(MultipartFile file, List<Integer> excelTitleRowNums, HttpServletRequest req, HttpServletResponse resp, String paramconfiglist);
}