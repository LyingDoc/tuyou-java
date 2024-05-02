package com.ruoyi.flow.oa.web;



import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.flow.vo.R;
import com.ruoyi.flow.oa.entity.OaActEntity;
import com.ruoyi.flow.oa.service.impl.OaActServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * OaActController
 * @author 刘亚平
 * @version 2022-12-01
 */
@Controller
@RequestMapping(value = "/flow/oa/act")
public class OaActController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private OaActServiceImpl actService;
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("queryByProcId")
    @ResponseBody
    public R<List<OaActEntity>> queryByProcId(String procId){
        return R.newOk( this.actService.queryByProcessId(procId));
    }
}
