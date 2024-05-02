package com.ruoyi.flow.oa.web;


import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.flow.oa.entity.OaSendNoticeEntity;
import com.ruoyi.flow.oa.vo.ProcessChartVO;
import com.ruoyi.flow.vo.PageVO;
import com.ruoyi.flow.vo.R;
import com.ruoyi.flow.oa.entity.OaTempNoticeEntity;
import com.ruoyi.flow.oa.service.impl.OaTempNoticeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/flow/oa/tempNotice")
public class OaTempNoticeController  extends BaseController {

    @Autowired
    private OaTempNoticeServiceImpl oaTempNoticeService;
    /**
     * 保存表单信息
     *
     * @param
     * @return
     * @throws IOException
     */
    @PostMapping("listData")
    @ResponseBody
    public R<List<OaTempNoticeEntity>> listData(@RequestBody PageVO<OaTempNoticeEntity> req)  {
        return oaTempNoticeService.getOaTempNoticeEntityList(req);

    }

    /**
     * 保存数据
     */
    @Log(title = "消息模板", businessType = BusinessType.SAVE)
    @PostMapping(value = "save")
    @ResponseBody
    public R<Object> save(@RequestBody OaTempNoticeEntity oaTempNotice) {
        oaTempNoticeService.saveOrUpdate(oaTempNotice);
        return R.newOk();
    }
    /**
     * 启动消息模板
     */
    @Log(title = "启动消息模板", businessType = BusinessType.OTHER)
    @PostMapping(value = "starttemp")
    @ResponseBody
    public R<Object> starttemp(@RequestBody OaTempNoticeEntity oaTempNotice) {
        oaTempNotice.setTempStatus("1");
        oaTempNoticeService.updateById(oaTempNotice);
        return R.newOk();
    }
    /**
     * 停止消息模板
     */
    @Log(title = "停止消息模板", businessType = BusinessType.OTHER)
    @PostMapping(value = "stoptemp")
    @ResponseBody
    public R<Object> stoptemp(@RequestBody OaTempNoticeEntity oaTempNotice) {
        oaTempNotice.setTempStatus("0");
        oaTempNoticeService.updateById(oaTempNotice);
        return R.newOk();
    }
    /**
     * 删除数据
     */
    @Log(title = "删除消息模板", businessType = BusinessType.DELETE)
    @RequestMapping(value = "delete")
    @ResponseBody
    public R<Object> delete(@RequestBody OaTempNoticeEntity oaTempNotice) {
        oaTempNoticeService.deleteById(oaTempNotice.getTempId());
        return R.newOk();
    }
    /**
     * 删除数据
     */
    @PostMapping(value = "queryTempNotice")
    @ResponseBody
    public  R<List< OaTempNoticeEntity>> queryTempNotice(@RequestBody  PageVO<OaTempNoticeEntity>  oaTempNotice){
       return oaTempNoticeService.queryTempNotice(oaTempNotice);
    }


}
