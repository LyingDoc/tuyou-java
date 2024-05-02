package com.ruoyi.flow.oa.web;



import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.flow.oa.entity.OaProcesstypeEntity;
import com.ruoyi.flow.oa.entity.OaTempNoticeEntity;
import com.ruoyi.flow.oa.vo.ProcessChartVO;
import com.ruoyi.flow.oa.vo.QuerySendNoticeVO;
import com.ruoyi.flow.vo.PageVO;
import com.ruoyi.flow.vo.R;
import com.ruoyi.flow.oa.entity.OaSendNoticeEntity;
import com.ruoyi.flow.oa.service.impl.OaSendNoticeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping(value = "/flow/oa/sendNotice")
public class    OaSendNoticeController extends BaseController {

    @Autowired
    private OaSendNoticeServiceImpl oaSendNoticeService;

    /**
     * 保存表单信息
     *
     * @param
     * @return
     * @throws IOException
     */
    @PostMapping("listData")
    @ResponseBody
    public R<List<OaSendNoticeEntity>> listData(@RequestBody PageVO<OaSendNoticeEntity> req)  {
        return oaSendNoticeService.getOaSendNoticeEntityList(req);

    }
    /**
     * 保存数据
     */
    @Log(title = "消息通知保存", businessType = BusinessType.SAVE)
    @PostMapping(value = "save")
    @ResponseBody
    public R<Object> save(@RequestBody OaSendNoticeEntity oaSendNotice) {
        oaSendNoticeService.saveOrUpdate(oaSendNotice);
        return R.newOk();
    }
    /**
     * 查询字典数据详细
     */
    @RequestMapping(value = "getinfo")
    @ResponseBody
    public  R<Object> getInfo(@RequestParam("noticeId") String noticeId) {
        return R.newOk(oaSendNoticeService.getById(noticeId));
    }
    /**
     * 删除数据
     */
    @Log(title = "消息通知删除", businessType = BusinessType.DELETE)
    @RequestMapping(value = "delete")
    @ResponseBody
    public R<Object> delete(@RequestBody OaSendNoticeEntity oaSendNotice) {
        oaSendNoticeService.deleteById(oaSendNotice.getNoticeId());
        return R.newOk();
    }

    @PostMapping("querySendNotice")
    @ResponseBody
    public R<List<QuerySendNoticeVO>>  querySendNotice(@RequestBody  PageVO<QuerySendNoticeVO>  req){
        return oaSendNoticeService.querySendNotice(req);
    }
}
