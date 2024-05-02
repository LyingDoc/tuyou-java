package com.ruoyi.flow.oa.web;


import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.flow.vo.R;
import com.ruoyi.flow.oa.req.SelectorSearchReq;
import com.ruoyi.flow.oa.service.impl.SysOaUserServiceImpl;
import com.ruoyi.flow.oa.vo.OrganSearchVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/flow/oa/userinfo")
public class OaUserInfoController extends BaseController {
    @Autowired
    SysOaUserServiceImpl oaUserService;
    @ApiOperation("机构选择接口")
    @PostMapping("SelectOrgan")
    @ResponseBody
    public R<List<OrganSearchVO>> SelectOrgan(@RequestBody SelectorSearchReq search)  {
        return oaUserService.SelectOrgan(search.getSearch());
    }

    @ApiOperation("机构搜索")
    @PostMapping("SearchOrgan")
    @ResponseBody
    public R<List<OrganSearchVO>> SearchOrgan(@RequestBody SelectorSearchReq search){
        return oaUserService.SearchOrgan(search.getSearch());
    }
    @ApiOperation("人员选择")
    @PostMapping("SelectUser")
    @ResponseBody
    public R<List<OrganSearchVO>> SelectUser(@RequestBody SelectorSearchReq search)  {
        return oaUserService.SelectUser(search.getSearch());
    }

    @ApiOperation("人员搜索")
    @PostMapping("SearchUser")
    @ResponseBody
    public R<List<OrganSearchVO>> SearchUser(@RequestBody SelectorSearchReq search){
        return oaUserService.SearchUser(search.getSearch());
    }
    @ApiOperation("角色选择")
    @PostMapping("SelectRole")
    @ResponseBody
    public R<List<OrganSearchVO>> SelectRole(@RequestBody SelectorSearchReq search)  {
        return oaUserService.SelectRole(search.getSearch());
    }

    @ApiOperation("角色搜索")
    @PostMapping("SearchRole")
    @ResponseBody
    public R<List<OrganSearchVO>> SearchRole(@RequestBody SelectorSearchReq search){
        return oaUserService.SearchRole(search.getSearch());
    }
}
