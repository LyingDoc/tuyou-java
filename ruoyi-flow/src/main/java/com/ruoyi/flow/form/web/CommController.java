package com.ruoyi.flow.form.web;


import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DictUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.flow.form.req.KylinFiledInfoReq;
import com.ruoyi.flow.form.req.KylinTableInfoReq;
import com.ruoyi.flow.form.vo.KylinFiledInfoVO;
import com.ruoyi.flow.form.vo.KylinTableInfoVO;
import com.ruoyi.flow.vo.CurrentUserInfoVO;
import com.ruoyi.flow.vo.HeadEntityVO;
import com.ruoyi.flow.vo.R;
import com.ruoyi.flow.form.service.impl.ApiCommService;
import com.ruoyi.flow.form.service.impl.IdWorker;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Api(value = "comm", tags = "通用方法")
@RestController
@RequestMapping(value = "/flow/oa/comm")
public class CommController {
    @Autowired
    private ApiCommService apiCommService;

    /**
     * 根据执行sql返回输出的字段名称
     *
     * @return
     */
    @ApiOperation("根据执行sql返回输出的字段名称")
    @PostMapping("getApiFieldNames")
    @ResponseBody
    public R<List<String>> getApiFieldNames(@RequestBody JSONObject param) throws Exception {
        return apiCommService.getApiFieldNames(param);
    }

    /**
     * 测试执行Api接口
     *
     * @param param
     * @return
     */
    @ApiOperation("测试执行Api接口")
    @PostMapping(value = "testCommApi/{ApiCode:.+}")
    @ResponseBody
    public R<Object> testCommApi(@RequestBody JSONObject param, @PathVariable("ApiCode") String ApiCode, HttpServletRequest req) throws Exception {
        return apiCommService.testCommApi(param, ApiCode, req);
    }

    /**
     * 测试执行Api接口
     *
     * @param param
     * @return
     */
    @ApiOperation("测试执行Api接口")
    @PostMapping("testExecuteApi")
    @ResponseBody
    public R<Object> testExecuteApi(@RequestBody JSONObject param) throws Exception {
        return apiCommService.testExecuteApi(param);
    }

    /**
     * 导出xls
     *
     * @return 返回查询结果
     */
    @Log(title = "通用Exlce导出接口", businessType = BusinessType.EXPORT)
    @ApiOperation(value = "通用Exlce导出接口", notes = "")
    @RequestMapping(value = "ExportExlce/{ApiCode:.+}", method = RequestMethod.POST)
    public HttpServletResponse ExportExlce(@RequestBody JSONObject param, @PathVariable("ApiCode") String ApiCode, HttpServletRequest req, HttpServletResponse response) throws Exception {
        apiCommService.ExportExlce(param, ApiCode, response, req);
        return null;
    }

    /**
     * 根据Api编码获取表头信息
     *
     * @return 返回查询结果
     */
    @ApiOperation(value = "根据Api编码获取表头信息", notes = "")
    @PostMapping(value = "getListHeadCell/{ApiCode:.+}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public R<List<HeadEntityVO>> getListHeadCell(@PathVariable("ApiCode") String ApiCode) {
        return apiCommService.getListHeadCell(ApiCode);
    }

    /**
     * 通用接口
     *
     * @return 返回查询结果
     */
    @Log(title = "通用接口Api调用", businessType = BusinessType.OTHER)
    @ApiOperation("通用接口")
    @PostMapping("apiComm/{ApiCode:.+}")
    @ResponseBody
    public R<Object> apiComm(@RequestBody JSONObject param, @PathVariable("ApiCode") String ApiCode, HttpServletRequest req) throws Exception {
        return apiCommService.apiComm(param, ApiCode, req);
    }
    @GetMapping("getCurrentUserInfo")
    @ResponseBody
    public CurrentUserInfoVO getCurrentUserInfo() {
        return apiCommService.getCurrentUserInfo();

    }

    private static Set<String> imageTypeSet = new HashSet<>();
    private static Set<String> fileTypeSet = new HashSet<>();

    static {
        imageTypeSet.add(".jpg");
        imageTypeSet.add(".png");
        imageTypeSet.add(".gif");
        imageTypeSet.add(".jpeg");
        imageTypeSet.add(".tif");
        imageTypeSet.add(".bmp");
        //其他省略...
    }

    static {
        fileTypeSet.add(".txt");
        fileTypeSet.add(".doc");
        fileTypeSet.add(".xls");
        fileTypeSet.add(".docx");
        fileTypeSet.add(".xlsx");
        fileTypeSet.add(".pdf");
        fileTypeSet.add(".et");
        fileTypeSet.add(".jpg");
        fileTypeSet.add(".png");
        fileTypeSet.add(".gif");
        fileTypeSet.add(".jpeg");
        fileTypeSet.add(".tif");
        fileTypeSet.add(".bmp");
        //其他省略...
    }

    @Value(value = "${file.baseDir}")
    private String baseDir;

    /**
     * Exlce 文件上传
     *
     * @return
     * @throws Exception
     */
    @Log(title = "图片上传", businessType = BusinessType.OTHER)
    @ApiOperation("图片上传")
    @RequestMapping(value = "/uploadImg", method = RequestMethod.POST)
    @ResponseBody
    public R<Object> uploadImg(@RequestParam(value = "imageFile", required = false) MultipartFile file, HttpServletRequest request,
                               HttpServletResponse response) {
        if (file == null || file.isEmpty()) {
            return R.newError("图片上传失败");
        }
        String fileName = file.getOriginalFilename();
        //String fileType = ".jpg";
        int index = fileName.lastIndexOf(".");
        String fileType = fileName.substring(index);
        if (!imageTypeSet.contains(fileType)) {
            return R.newError("图片上传失败");
        }
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String filePath = new File(File.separator + "AttachmentFile" + File.separator + sdf.format(d)).getAbsolutePath();
        File fileUpload = new File(filePath);
        if (!fileUpload.exists()) {
            fileUpload.mkdirs();
        }
        UUID uuid = UUID.randomUUID();
        fileUpload = new File(filePath, uuid + fileType);
        if (fileUpload.exists()) {
            return R.newError("图片上传失败,上传的图片已存在");
        }
        try {
            file.transferTo(fileUpload);
            Map<String, String> dthashMap = new HashMap<>();
            dthashMap.put("name", fileName);
            dthashMap.put("uid", uuid.toString());
            dthashMap.put("size", file.getSize() + "");
            dthashMap.put("format", fileType);
            dthashMap.put("url", "/flow/oa/formfile/file/downloadImg?path=" + URLEncoder.encode("AttachmentFile/" + sdf.format(d) + "/" + uuid + fileType, "utf-8"));
            dthashMap.put("creater", "");
            Date createtime = new Date();
            SimpleDateFormat createtimesdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dthashMap.put("createtime", createtimesdf.format(createtime));
            return R.newOk(dthashMap);
        } catch (IOException e) {
            return R.newError("图片上传失败：" + e.toString());
        }

    }

    /**
     * Exlce 文件上传
     *
     * @return
     * @throws Exception
     */
    @Log(title = "文件上传", businessType = BusinessType.OTHER)
    @ApiOperation("文件上传")
    @RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
    @ResponseBody
    public R<Object> uploadfile(@RequestParam(value = "imageFile", required = false) MultipartFile file, HttpServletRequest request,
                                HttpServletResponse response) {
        if (file == null || file.isEmpty()) {
            return R.newError("上传文件失败");
        }
        String fileName = file.getOriginalFilename();
        //String fileType = ".jpg";
        int index = fileName.lastIndexOf(".");
        String fileType = fileName.substring(index);
        if (!fileTypeSet.contains(fileType)) {
            return R.newError("请上传txt/doc/xls/docx/xlsx/jpg/gif/jpeg/png/pdf/et/tif/bmp格式的文件!");
        }
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String filePath = new File("AttachmentFile\\" + sdf.format(d)).getAbsolutePath();
        File fileUpload = new File(filePath);
        if (!fileUpload.exists()) {
            fileUpload.mkdirs();
        }
        UUID uuid = UUID.randomUUID();
        fileUpload = new File(filePath, uuid + fileType);
        if (fileUpload.exists()) {
            return R.newError("上传失败,上传的文件已存在");
        }
        try {
            file.transferTo(fileUpload);
            Map<String, String> dthashMap = new HashMap<>();
            dthashMap.put("name", fileName);
            dthashMap.put("uid", uuid.toString());
            dthashMap.put("size", file.getSize() + "");
            dthashMap.put("format", fileType);
            dthashMap.put("url", "/flow/oa/formfile/file/download?path=" + URLEncoder.encode("AttachmentFile\\" + sdf.format(d) + "\\" + uuid + fileType, "utf-8"));
            dthashMap.put("creater", "");
            Date createtime = new Date();
            SimpleDateFormat createtimesdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dthashMap.put("createtime", createtimesdf.format(createtime));
            return R.newOk(dthashMap);
        } catch (IOException e) {
            return R.newError("上传的文件失败：" + e.toString());
        }

    }

    /**
     * Exlce 导入接口
     *
     * @param ApiCode
     * @param file
     * @param req
     * @param resp
     * @return
     * @throws Exception
     */
    @Log(title = "通用导入接口", businessType = BusinessType.IMPORT)
    @RequestMapping(value = "/importExlce/{ApiCode:.+}", method = RequestMethod.POST)
    @ResponseBody
    public R<String> importExlce(@PathVariable("ApiCode") String ApiCode, MultipartFile file, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        return  apiCommService.importExlce(ApiCode, file, req, resp);
    }

    @ApiOperation("获取唯一的雪花ID")
    @RequestMapping(value = "/getNewId", method = RequestMethod.POST)
    @ResponseBody
    public R<Long> getNewId() {
        IdWorker idWorker = new IdWorker();

        return R.newOk(idWorker.nextId());
    }

    @ApiOperation("获取字典数据")
    @RequestMapping(value = "/getDictData", method = RequestMethod.GET)
    @ResponseBody
    public R<List<SysDictData>> getDictData(@RequestParam("dictType") String dictType) {
        if (StringUtils.isEmpty(dictType)) {
            return R.newError("传入参数失败！");
        }
        List<SysDictData> sysDictDataList = DictUtils.getDictCache(dictType);
        return R.newOk(sysDictDataList);
    }

    @RequestMapping(value = "/getAlltablename", method = RequestMethod.POST)
    @ResponseBody
    public R<List<Map<String, Object>>> getAlltablename(@RequestBody KylinTableInfoReq req) throws Exception{
        return R.newOk(apiCommService.getAlltablename(req));
    }

    @RequestMapping(value = "/getTableColumn", method = RequestMethod.POST)
    @ResponseBody
    public R<List<Map<String, Object>>> getTableColumn(@RequestBody KylinFiledInfoReq req) throws Exception {
        return R.newOk(apiCommService.getTableColumn(req));
    }
}
