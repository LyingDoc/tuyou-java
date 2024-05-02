package com.ruoyi.flow.form.web;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.flow.form.req.QueryApiListReq;
import com.ruoyi.flow.form.vo.QueryApiListVO;
import com.ruoyi.flow.vo.R;
import com.ruoyi.flow.form.req.SaveApiConfigReq;
import com.ruoyi.flow.form.req.SaveConfigShowCelNamesReq;
import com.ruoyi.flow.form.req.SaveShowCtrlWhereReq;
import com.ruoyi.flow.form.service.impl.FormApiconfigServiceImpl;
import com.ruoyi.flow.form.vo.ApiConfigBaseInfoVO;
import com.ruoyi.flow.form.vo.ApiConfigVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping(value = "/flow/oa/formapi")
public class FormApiconfigController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private FormApiconfigServiceImpl tappApiconfigService;

    /**
     * Api保存方法
     *
     * @param apiParam
     * @return
     */
    @Log(title = "DBAPI", businessType = BusinessType.UPDATE)
    @PostMapping("saveApiConfigInfo")
    @ResponseBody
    public R<String> saveApiConfigInfo(@RequestBody SaveApiConfigReq apiParam) throws Exception {
        byte[] decodedBytes = Base64.getMimeDecoder().decode(apiParam.getSqlContent());
        String sqlcontent= new String(decodedBytes);
        apiParam.setSqlContent(sqlcontent);
        return tappApiconfigService.saveApiConfigInfo(apiParam);

    }

    /**
     * 回滚指定版本
     *
     * @param callMethodCode
     * @param versions
     */
    @Log(title = "DBAPI回滚指定版本", businessType = BusinessType.UPDATE)
    @PostMapping("rollBack")
    @ResponseBody
    public R<String> rollBack(@RequestParam("callMethodCode") String callMethodCode, @RequestParam("versions") Integer versions) {
        tappApiconfigService.rollBack(callMethodCode, versions);
        return R.newOk();
    }

    /**
     * 根据回调方法编码获取接口配置信息
     *
     * @param callMethodCode
     * @return
     */
    @PostMapping("getApiConfigByCallMethodCode")
    @ResponseBody
    public R<ApiConfigBaseInfoVO> getApiConfigByCallMethodCode(@RequestParam("code") String callMethodCode) {
        ApiConfigVO apiConfigVO = tappApiconfigService.getApiConfigByCallMethodCode(callMethodCode);
        ApiConfigBaseInfoVO apiConfigBaseInfoVO = new ApiConfigBaseInfoVO();
        apiConfigBaseInfoVO.setFid(apiConfigVO.getFormApiconfigId());
        apiConfigBaseInfoVO.setHeadList(apiConfigVO.getHeadList());
        apiConfigBaseInfoVO.setMethodName(apiConfigVO.getMethodName());
        apiConfigBaseInfoVO.setParamConfig(apiConfigVO.getParamConfig());
        apiConfigBaseInfoVO.setRemarks(apiConfigVO.getRemarks());
        apiConfigBaseInfoVO.setShowCelNames(apiConfigVO.getShowCelNames());
        return R.newOk(apiConfigBaseInfoVO);
    }

    /**
     * 根据ID 获取Api配置方法
     *
     * @param fid
     * @return
     */
    @PostMapping("getApiConfigById")
    @ResponseBody
    public R<ApiConfigVO> getApiConfigById(@RequestParam("id") String fid) {
        ApiConfigVO apiConfigVO= tappApiconfigService.getApiConfigById(fid);
        if(apiConfigVO!=null) {
            return R .newOk(apiConfigVO);
        }
        else {
            return R .newError("未找到接口信息");
        }
    }

    /**
     * 根据ID 进行删除Api接口
     *
     * @param fidsaveApiConfigInfo
     * @return
     */
    @Log(title = "删除API接口", businessType = BusinessType.DELETE)
    @PostMapping("deleteMapping")
    @ResponseBody
    public R<String> deleteMapping(@RequestParam("id") String fid) {
         tappApiconfigService.deleteById(fid);
        return R.newOk("删除成功！");
    }

    /**
     * 保存列表显示的列
     *
     * @param param
     * @return
     */
    @Log(title = "列表的列设置", businessType = BusinessType.UPDATE)
    @PostMapping("saveConfigShowCelNames")
    @ResponseBody
    public R<String> saveConfigShowCelNames(@RequestBody SaveConfigShowCelNamesReq param) {
        Boolean isSave = tappApiconfigService.saveConfigShowCelNames(param);
        if (isSave) {
            return R.newOk("保存成功！");
        }
        return R.newError("保存失败");
    }
    /**
     * 保存列表显示条件
     *
     * @param param
     * @return
     */
    @Log(title = "API接口显示查询条件设置", businessType = BusinessType.UPDATE)
    @PostMapping("saveShowCtrlWhere")
    @ResponseBody
    public R<String> saveShowCtrlWhere(@RequestBody SaveShowCtrlWhereReq param) {
        Boolean isSave = tappApiconfigService.saveShowCtrlWhere(param);
        if (isSave) {
            return R.newOk("保存成功！");
        }
        return R.newError("保存失败");
    }
    @Log(title = "清空Api缓存", businessType = BusinessType.CLEAN)
    @GetMapping("clearWebApiCache")
    @ResponseBody
    public R<String> clearWebApiCache(){
        tappApiconfigService.clearWebApiCache();
        return R.newOk("刷新缓存成功！");
    }

    @PostMapping("queryApiList")
    @ResponseBody
    public R<List<QueryApiListVO>>  queryApiList(@RequestBody QueryApiListReq req){

        return tappApiconfigService.queryApiList(req);
    }
}
