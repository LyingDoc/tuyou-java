package com.ruoyi.flow.form.web;


import com.ruoyi.flow.vo.R;
import com.ruoyi.flow.form.service.impl.ApiCommService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Api(value = "comm", tags = "通用方法")
@RestController
@RequestMapping(value = "/flow/oa/formfile")
@Controller
public class FileController {


    /**
     * 文件下载
     *
     * @return
     * @throws Exception
     */
    @ApiOperation("文件下载")
    @RequestMapping(value = "/file/download", method = RequestMethod.GET)
    public String download(@RequestParam("path") String path, HttpServletResponse response) {
        try {
            if (!path.contains("AttachmentFile")) {
//                response.setContentType("text/html; charset=UTF-8"); //转码
//                PrintWriter out = response.getWriter();
//                out.flush();
//                out.println();
                return "文件不存在或已经被删除！";
            }
            // path是指欲下载的文件的路径。
            File file = new File(File.separator +path);
            if (!file.exists()) {
//                response.setContentType("text/html; charset=UTF-8"); //转码
//                PrintWriter out = response.getWriter();
//                out.flush();
//                out.println("文件不存在或已经被删除！");

                return "文件不存在或已经被删除！";
            }
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + filename+"."+ext);
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("multipart/form-data");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 文件下载
     *
     * @return
     * @throws Exception
     */
    @ApiOperation("文件下载")
    @RequestMapping(value = "/file/downloadImg", method = RequestMethod.GET)
    @ResponseBody
    public String downloadImg(@RequestParam("path") String path, HttpServletResponse response) throws IOException {
        if (!path.contains("AttachmentFile")) {
            return "文件不存在或已经被删除";
        }
        // path是指欲下载的文件的路径。
        File file = new File(File.separator +path);
        if (!file.exists()) {
            return "文件不存在或已经被删除";
        }
        ServletOutputStream outputStream = null;
        FileInputStream inputStream = null;
        try {
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
            inputStream = new FileInputStream(file);
            response.setContentType("multipart/form-data");
            response.addHeader("Content-Disposition", "attachment;filename=" + filename+"."+ext);
            outputStream = response.getOutputStream();
            //读取文件流
            int len = 0;
            byte[] buffer = new byte[1024 * 10];
            while ((len = inputStream.read(buffer)) != -1){
                outputStream.write(buffer,0,len);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;


    }

    @Autowired
    private ApiCommService apiCommService;
    /**
     * 通用接口
     *
     * @return 返回查询结果
     */
    @ApiOperation("通用接口")
    @RequestMapping(value ="/foreign/apiComm/{ApiCode}", method = RequestMethod.POST)
    @ResponseBody
    public R<Object> apiComm(@RequestBody JSONObject param, @PathVariable("ApiCode") String ApiCode, HttpServletRequest req) throws Exception {
        return apiCommService.apiComm(param, ApiCode, req);
    }
}
