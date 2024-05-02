package com.ruoyi.flow.form.service.impl;


import com.alibaba.fastjson2.JSON;
import com.ruoyi.flow.comm.ImportExcelUtils;
import com.ruoyi.flow.form.service.IImportExcelService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ImportExcelServiceImpl extends ImportExcelBaseService implements IImportExcelService {

    @Override
    public Map importExcelWithSimple(MultipartFile file, List<Integer> excelTitleRowNums, HttpServletRequest req, HttpServletResponse resp) {
        if (file.isEmpty()){
            return null;
        }
        //方法返回的map
        Map returnMap = new HashMap();

        returnMap.put("exceloriginalname",file.getOriginalFilename());

        //解析文件，返回map
        Map map = new LinkedHashMap();
        try {
            map = ImportExcelUtils.getBankListByExcel(file.getInputStream(), file.getOriginalFilename(), excelTitleRowNums);
            Map excelMouldsMap = new LinkedHashMap();
            Iterator<Map.Entry<String,List>> iterator = map.entrySet().iterator();
            while(iterator.hasNext()){
                List<Object> list = new ArrayList<>();
                Map.Entry<String,List> next = iterator.next();
                if(!next.getKey().equals("code")&&next.getValue().size()>0) {
                    list.add(next.getValue().get(0));
                    excelMouldsMap.put(next.getKey(),list);
                }

            }
            String excelMouldsJsonStr = JSON.toJSONString(excelMouldsMap);
            returnMap.put("excelMoulds",excelMouldsJsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        //文件上传本地
        String fileName = file.getOriginalFilename();//获取文件名
        fileName = getFileName(fileName);
        String filepath = getUploadPath();
        try {
            String excelFileUrl = filepath + File.separator + fileName;
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(excelFileUrl)));
            out.write(file.getBytes());
            out.flush();

            String excelJsonUrl = excelFileUrl.substring(0,excelFileUrl.lastIndexOf(".")) + ".json";
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(excelJsonUrl,false),"UTF-8"));
            //拿到excel模板
            String jsonString = JSON.toJSONString(map);
            writer.write(jsonString);
            writer.flush();
            out.close();
            writer.close();

            returnMap.put("excelJsonUrl",excelJsonUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }



        return returnMap;
    }

    @Override
    public JSONObject importExcelJSON(MultipartFile file, List<Integer> excelTitleRowNums, HttpServletRequest req, HttpServletResponse resp, String paramconfig){
        if (file.isEmpty()){
            return null;
        }
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("code","0");
        try {
           Map map = ImportExcelUtils.getBankListByExcel(file.getInputStream(), file.getOriginalFilename(), excelTitleRowNums,paramconfig);
           if((int)map.get("code")!=0){
               jsonObject.put("code",map.get("code"));
               jsonObject.put("msg",map.get("msg"));
               return jsonObject;
           }
            Iterator<Map.Entry<String,List>> iterator = map.entrySet().iterator();
            while(iterator.hasNext()){
                List<Object> list = new ArrayList<>();
                Map.Entry<String,List> next = iterator.next();
                jsonObject.put(next.getKey(),next.getValue());
            }
            Map<String,String> other=new HashMap<>();
            String filename=file.getOriginalFilename().substring(0,file.getOriginalFilename().lastIndexOf(".") + 1);
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
            other.put("filename",filename);
            other.put("filetype",suffix);
            other.put("excelcode",UUID.randomUUID().toString());///批次code
            jsonObject.put("$other",other);
           return jsonObject;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 文件名后缀前添加一个时间戳
     */
    private String getFileName(String fileName) {
        int index = fileName.lastIndexOf(".");
        final SimpleDateFormat sDateFormate = new SimpleDateFormat("yyyymmddHHmmss");  //设置时间格式
        String nowTimeStr = sDateFormate.format(new Date()); // 当前时间
        fileName = fileName.substring(0, index) + "_" + nowTimeStr + fileName.substring(index);
        return fileName;
    }

    /**
     * 获取当前系统路径
     */
    private String getUploadPath() {
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (!path.exists()) path = new File("");
        File upload = new File(path.getAbsolutePath(), "static/upload/");
        if (!upload.exists()) upload.mkdirs();
        return upload.getAbsolutePath();
    }
}