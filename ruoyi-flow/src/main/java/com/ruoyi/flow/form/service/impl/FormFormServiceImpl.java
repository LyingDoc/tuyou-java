package com.ruoyi.flow.form.service.impl;


import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.flow.comm.OnLineFormUtils;
import com.ruoyi.flow.comm.SpringBeanUtils;
import com.ruoyi.flow.form.entity.*;
import com.ruoyi.flow.form.mapper.*;
import com.ruoyi.flow.form.req.FromApiConfigReq;
import com.ruoyi.flow.form.req.FromSaveInfoReq;
import com.ruoyi.flow.form.req.QueryFromReq;
import com.ruoyi.flow.form.req.SaveFromMenuDataReq;
import com.ruoyi.flow.form.vo.*;
import com.ruoyi.flow.oa.service.impl.SysOaUserServiceImpl;
import com.ruoyi.flow.vo.OnLineFormVO;
import com.ruoyi.flow.vo.PageVO;
import com.ruoyi.flow.vo.R;
import com.ruoyi.flow.form.service.IFormFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类富士达范德萨都是
 * </p>
 *
 * @author 刘亚平
 * @since 2023-01-15
 */
@Service
public class FormFormServiceImpl extends ServiceImpl<FormFormMapper, FormFormEntity> implements IFormFormService {
    @Autowired
    SysMenuFlowMapper sysMenuMapper;
    @Autowired
    SysRoleMenuFlowMapper sysRoleMenuMapper;
    @Autowired
    FormApiconfigMapper formApiconfigMapper;

    @Autowired
    SysRoleFlowMapper sysRoleFlowMapper;
    @Autowired
    private SysOaUserServiceImpl sysOaUserService;

    @Autowired
    private SpringBeanUtils springBeanUtils;

    /**
     * 通过主键获取数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public FormFormEntity getById(String id) {
        return baseMapper.selectById(id);
    }

    /**
     * 通过主键删除数据
     *
     * @param fid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String fid) {
        return this.baseMapper.deleteById(fid) > 0;
    }

    /**
     * 通过主键修改数据
     *
     * @param formformentity 主键
     * @return 是否成功
     */
    @Override
    public R<FormFormEntity> saveFormFormEntity(FormFormEntity formformentity) {
        int row = 0;
        try {
            if (saveOrUpdate(formformentity)) {
                return R.newOk(formformentity);
            } else {
                return R.newError("保存失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.newError("[{formformentity}] 未知异常");
        }

    }

    /**
     * 通过id集合批量删除
     *
     * @param ids 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteFormFormEntityList(List<Integer> ids) {
        int row = 0;
        try {
            row = this.baseMapper.deleteBatchIds(ids);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[{formformentity}] 未知异常");
        }
        return row > 0;
    }

    @Override
    public R<List<FormFormEntity>> getFormFormEntityList(PageVO<FormFormEntity> req) {
        Page<FormFormEntity> page = new Page<>(req.getPage(), req.getPagesize());
        Page formformentityPage = this.baseMapper.selectPage(page, new QueryWrapper<FormFormEntity>());
        return R.newOk(formformentityPage.getRecords(), formformentityPage.getTotal());
    }

    public FormFormEntity querybyTableName(String strTableName) {
        QueryWrapper<FormFormEntity> queryWrapper = new QueryWrapper();
        queryWrapper.eq("from_table_name", strTableName).eq("is_buildapi", "1");
        return this.getOne(queryWrapper, false);

    }

    @Override
    public R<SaveFromMenuDataReq> getFromMenuData(String fromId) {
        LambdaQueryWrapper<SysMenuEntity> sysMenuQueryWrapper = new LambdaQueryWrapper<>();
        sysMenuQueryWrapper.eq(SysMenuEntity::getFormId, fromId);
        SysMenuEntity sysMenuEntity = sysMenuMapper.selectOne(sysMenuQueryWrapper);
        SaveFromMenuDataReq fromMenuDataReq = new SaveFromMenuDataReq();
        if (sysMenuEntity != null) {
            fromMenuDataReq.setFromId(sysMenuEntity.getFormId());
            fromMenuDataReq.setIcon(sysMenuEntity.getIcon());
            fromMenuDataReq.setMenuurl(sysMenuEntity.getPath());
            if (fromMenuDataReq.getMenuurl().contains("formview?")) {
                fromMenuDataReq.setMenuLinkType("1");
            } else {
                fromMenuDataReq.setMenuLinkType("2");
            }
            fromMenuDataReq.setMenuName(sysMenuEntity.getMenuName());
            fromMenuDataReq.setParentId(sysMenuEntity.getParentId());
//            LambdaQueryWrapper<SysMenuEntity> sysMenubutWrapper = new LambdaQueryWrapper<>();
//            sysMenubutWrapper.eq(SysMenuEntity::getParentId, sysMenuEntity.getMenuId());
//            sysMenubutWrapper.eq(SysMenuEntity::getMenuType,"F");
//            List<SysMenuEntity>  menulist=  sysMenuMapper.selectList(sysMenubutWrapper);
//            fromMenuDataReq.butPowerList=new ArrayList<>();
//            menulist.forEach(ee->{
//                fromMenuDataReq.butPowerList.add(ee.getPerms());
//            });
            LambdaQueryWrapper<SysRoleMenuEntity> sysRoleMenuLambdaQueryWrapper = new LambdaQueryWrapper<>();
            sysRoleMenuLambdaQueryWrapper.eq(SysRoleMenuEntity::getMenuId, sysMenuEntity.getMenuId());
            SysRoleMenuEntity sysRoleMenu=  sysRoleMenuMapper.selectOne(sysRoleMenuLambdaQueryWrapper);
            if(sysRoleMenu!=null) {
                SysRoleEntity role= sysRoleFlowMapper.selectById(sysRoleMenu.getRoleId());
                fromMenuDataReq.setRole(sysRoleMenu.getRoleId().toString());
                fromMenuDataReq.setRoleName(role.getRoleName());
            }
        }
        return R.newOk(fromMenuDataReq);
    }

    /**
     * 保存表单菜单数据
     */
    @Transactional
    @Override
    public R<String> saveFromMenuData(SaveFromMenuDataReq fromMenuDataReq) {
        LambdaQueryWrapper<SysMenuEntity> sysMenuQueryWrapper = new LambdaQueryWrapper<>();
        sysMenuQueryWrapper.eq(SysMenuEntity::getFormId, fromMenuDataReq.getFromId());
        SysMenuEntity sysMenuEntity = sysMenuMapper.selectOne(sysMenuQueryWrapper);
        LambdaQueryWrapper<SysMenuEntity> sysMenuChindQueryWrapper = new LambdaQueryWrapper<>();
        sysMenuChindQueryWrapper.eq(SysMenuEntity::getMenuId, fromMenuDataReq.getParentId());
        List<SysMenuEntity> childList = sysMenuMapper.selectList(sysMenuChindQueryWrapper);
        SysMenuEntity sysMenuEntity1 = childList.stream().max(Comparator.comparing(SysMenuEntity::getOrderNum)).get();
        LoginUser loginUser = SecurityUtils.getLoginUser();
        boolean isAdd = false;
        if (sysMenuEntity == null) {
            sysMenuEntity = new SysMenuEntity();
            sysMenuEntity.setCreateBy(loginUser.getUsername());
            sysMenuEntity.setCreateTime(new Date());
            sysMenuEntity.setFormId(fromMenuDataReq.getFromId());
            isAdd = true;
        }
        sysMenuEntity.setUpdateBy(loginUser.getUsername());
        sysMenuEntity.setUpdateTime(new Date());
        sysMenuEntity.setMenuName(fromMenuDataReq.getMenuName());
        sysMenuEntity.setParentId(fromMenuDataReq.getParentId());
        sysMenuEntity.setOrderNum(sysMenuEntity1.getOrderNum() + 1);
        sysMenuEntity.setPath(fromMenuDataReq.getMenuurl());
        sysMenuEntity.setIsFrame(1L);
        sysMenuEntity.setIsCache(1L);
        sysMenuEntity.setMenuType("C");
        sysMenuEntity.setVisible("0");
        sysMenuEntity.setStatus("0");
        sysMenuEntity.setIcon(fromMenuDataReq.getIcon());
        FormFormEntity formFormEntity = this.getById(fromMenuDataReq.getFromId());
        sysMenuEntity.setPerms("form:" + formFormEntity.getFromTableName() + ":*");
        if (isAdd) {
            sysMenuMapper.insertSysMenu(sysMenuEntity);
        } else {
            sysMenuMapper.updateSysMenu(sysMenuEntity);
        }
        if(fromMenuDataReq.getButPowerList()==null){
            fromMenuDataReq.setButPowerList(new ArrayList<>());
        }
        SaveButPowerList(fromMenuDataReq.getButPowerList(),sysMenuEntity,fromMenuDataReq.getRole());
        if (StringUtils.isNotEmpty(fromMenuDataReq.getRole())) {
            LambdaQueryWrapper<SysRoleMenuEntity> sysRoleMenuLambdaQueryWrapper = new LambdaQueryWrapper<>();
            sysRoleMenuLambdaQueryWrapper.eq(SysRoleMenuEntity::getMenuId, sysMenuEntity.getMenuId());
            sysRoleMenuLambdaQueryWrapper.eq(SysRoleMenuEntity::getRoleId, fromMenuDataReq.getRole());
            SysRoleMenuEntity sysRoleMenuEntity = sysRoleMenuMapper.selectOne(sysRoleMenuLambdaQueryWrapper);
            if (sysRoleMenuEntity == null) {
                sysRoleMenuEntity = new SysRoleMenuEntity();
                sysRoleMenuEntity.setMenuId(sysMenuEntity.getMenuId());
                sysRoleMenuEntity.setRoleId(Long.parseLong(fromMenuDataReq.getRole()));
                sysRoleMenuMapper.insertSysRoleMenu(sysRoleMenuEntity);
            }
        }

        return R.newOk("保存成功！");
    }

    void SaveButPowerList(List<String> butPowerList,SysMenuEntity sysMenuEntity,String roleid) {

//        LambdaQueryWrapper<SysMenuEntity> sysMenuQueryWrapper = new LambdaQueryWrapper<>();
//        sysMenuQueryWrapper.eq(SysMenuEntity::getParentId,sysMenuEntity.getMenuId());
//        List<SysMenuEntity> sysMenuEntityList=sysMenuMapper.selectList(sysMenuQueryWrapper);
//        List<SysMenuEntity> delSysMenuList= sysMenuEntityList.stream().filter(ee->butPowerList.contains(ee.getPerms())).collect(Collectors.toList());
//        delSysMenuList.forEach(ee->
//        {
//            butPowerList.remove(ee.getPerms());
//            sysMenuMapper.deleteSysMenuByMenuId(ee.getMenuId());
//        });
    }
    @Override
    public R<List<ApiConfigVO>> getFromApiConfig(FromApiConfigReq fromApiConfigReq) {
        return R.newOk(formApiconfigMapper.queryFormApiconfigList(fromApiConfigReq));
    }

    @Override
    @Transactional
    public R<String> SaveFromData(FromSaveInfoReq param) {
        FormFormEntity entity = this.getById(param.getId());
        if (true == param.getIsBuildApi()) {
            switch (param.getFromtype()) {
                case "1":
                    springBeanUtils.getBean(BuildFormMybatisInterfaceService.class).TableEidt(param.getFromdesignjson(), param.getFromTableName(), param.getQueryWhere(), param.getFromsavelogic());
                    break;
                case "2":
                    springBeanUtils.getBean(BuildFormMybatisInterfaceService.class).TableEidt(param.getFromdesignjson(), param.getFromTableName(), param.getQueryWhere(), param.getFromsavelogic());
                    break;
                case "3":
                    springBeanUtils.getBean(BuildFlowMybatisInterfaceService.class).TableEidt(param.getFromdesignjson(), param.getFromTableName(), param.getQueryWhere(), param.getFromsavelogic());
                    break;
                case "4":
                    BuildMybatisInterfaceService buildMybatisInterfaceService=   springBeanUtils.getBean(BuildQuestionnaireMybatisInterfaceService.class);
                    String miandatabasename= this.getBaseMapper().getMainDatabaseName();
                    StringBuffer saveInfoSql = new StringBuffer();
                    saveInfoSql.append("<sql  param=\"$userbusinesscount\"> select count(*) rowcount from `"+miandatabasename+"`.form_question_link where  form_data_id=#{$tableNewId} </sql>");
                    saveInfoSql.append("<sql test=\"$userbusinesscount==null or $userbusinesscount[0].rowcount==0 \"> insert into `"+miandatabasename+"`.form_question_link(question_link_id,create_by,create_date,update_by,update_date,form_id,form_data_id)values(#{$newid},#{$user.userCode},DATE_FORMAT(now(),'%Y-%m-%d %H:%i:%s'),#{$user.userCode},DATE_FORMAT(now(),'%Y-%m-%d %H:%i:%s'),#{$fromid},#{$tableNewId})</sql>");
                    buildMybatisInterfaceService.saveFormInfoSql=saveInfoSql.toString();
                    buildMybatisInterfaceService.TableEidt(param.getFromdesignjson(), param.getFromTableName(), param.getQueryWhere(), param.getFromsavelogic());
                    break;
                default:
                    springBeanUtils.getBean(BuildFormMybatisInterfaceService.class).TableEidt(param.getFromdesignjson(), param.getFromTableName(), param.getQueryWhere(), param.getFromsavelogic());
                    break;
            }
        }
        if (entity == null) {
            entity = new FormFormEntity();
            if (StringUtils.isNotEmpty(param.getId())) {
                entity.setFormFormId(param.getId());
            }
            entity.setCreateBy(SecurityUtils.getUsername());
            entity.setUpdateBy(SecurityUtils.getUsername());
            entity.setCreateDate(new Date());
            entity.setUpdateDate(new Date());
            entity.setFromtype(param.getFromtype());
            entity.setFremarks(param.getFremarks());
            entity.setFromcontent(param.getFromcontent());
            entity.setFromdesignjson(param.getFromdesignjson());
            entity.setFromname(param.getFromName());
            entity.setFromcode(param.getFromCode());
            entity.setFromTableName(param.getFromTableName());
            entity.setIsBuildapi(param.getIsBuildApi() == true ? 1L : 0L);
            entity.setDialogwidth(param.getDialogwidth());
            this.saveOrUpdate(entity);
        } else {
            entity.setUpdateBy(SecurityUtils.getUsername());
            entity.setUpdateDate(new Date());
            entity.setFremarks(param.getFremarks());
            entity.setFromcontent(param.getFromcontent());
            entity.setFromtype(param.getFromtype());
            entity.setFromdesignjson(param.getFromdesignjson());
            entity.setFromname(param.getFromName());
            entity.setFromcode(param.getFromCode());
            entity.setFromTableName(param.getFromTableName());
            entity.setIsBuildapi(param.getIsBuildApi() == true ? 1L : 0L);
            entity.setDialogwidth(param.getDialogwidth());
            this.updateById(entity);
        }
        OnLineFormUtils.removeOnLineFormCache(entity.getFormFormId());
        return R.newOk(entity.getFormFormId());
    }

    @Override
    public R<List<FormFormEntity>> getRelevanceForm(FormFormEntity formFormEntity) {
        LambdaQueryWrapper<FormFormEntity> formFormEntityLambdaQueryWrapper = new LambdaQueryWrapper<>();
        formFormEntityLambdaQueryWrapper.eq(FormFormEntity::getRelevanceFormId, formFormEntity.getRelevanceFormId());
        if (StringUtils.isNotEmpty(formFormEntity.getFromname())) {
            formFormEntityLambdaQueryWrapper.like(FormFormEntity::getFromname, formFormEntity.getFromname());
        }
        if (StringUtils.isNotEmpty(formFormEntity.getFromtype())) {
            formFormEntityLambdaQueryWrapper.eq(FormFormEntity::getFromtype, formFormEntity.getFromtype());
        }
        if (StringUtils.isNotEmpty(formFormEntity.getFremarks())) {
            formFormEntityLambdaQueryWrapper.like(FormFormEntity::getFremarks, formFormEntity.getFremarks());
        }
        return R.newOk(this.getBaseMapper().selectList(formFormEntityLambdaQueryWrapper));
    }

    @Override
    public OnLineFormVO getFromContent(String fid) {
        OnLineFormVO onLineFormVO = OnLineFormUtils.getOnLineFormCache(fid);
        if (onLineFormVO == null) {
            onLineFormVO = new OnLineFormVO();
            FormFormEntity formFormEntity = this.getById(fid);
            onLineFormVO.setFromName(formFormEntity.getFromname());
            onLineFormVO.setFromcontent(formFormEntity.getFromcontent());
            onLineFormVO.setFromdesignjson(formFormEntity.getFromdesignjson());
            onLineFormVO.setQueryJson(formFormEntity.getQueryJson());
            onLineFormVO.setFormFormId(formFormEntity.getFormFormId());
            onLineFormVO.setFromtype(formFormEntity.getFromtype());
            onLineFormVO.setDialogwidth(formFormEntity.getDialogwidth());
            OnLineFormUtils.setOnLineFormCache(fid, onLineFormVO);
        }
        onLineFormVO.setFromdesignjson("");
        return onLineFormVO;

    }

    @Override
    public OnLineFormVO getFromDesignJson(String fid) {
        OnLineFormVO onLineFormVO = OnLineFormUtils.getOnLineFormCache(fid);
        if (onLineFormVO == null) {
            onLineFormVO = new OnLineFormVO();
            FormFormEntity formFormEntity = this.getById(fid);
            onLineFormVO.setFromName(formFormEntity.getFromname());
            onLineFormVO.setFromcontent(formFormEntity.getFromcontent());
            onLineFormVO.setFromdesignjson(formFormEntity.getFromdesignjson());
            onLineFormVO.setQueryJson(formFormEntity.getQueryJson());
            onLineFormVO.setFormFormId(formFormEntity.getFormFormId());
            onLineFormVO.setFromtype(formFormEntity.getFromtype());
            onLineFormVO.setDialogwidth(formFormEntity.getDialogwidth());
            OnLineFormUtils.setOnLineFormCache(fid, onLineFormVO);
        }
        onLineFormVO.setFromcontent("");
        return onLineFormVO;
    }

    @Override
    public OnLineFormVO getFromDesignJsonOrFromContent(String fid) {
        OnLineFormVO onLineFormVO = OnLineFormUtils.getOnLineFormCache(fid);
        if (onLineFormVO == null) {
            onLineFormVO = new OnLineFormVO();
            FormFormEntity formFormEntity = this.getById(fid);
            onLineFormVO.setFromName(formFormEntity.getFromname());
            onLineFormVO.setFromcontent(formFormEntity.getFromcontent());
            onLineFormVO.setFromdesignjson(formFormEntity.getFromdesignjson());
            onLineFormVO.setQueryJson(formFormEntity.getQueryJson());
            onLineFormVO.setFormFormId(formFormEntity.getFormFormId());
            onLineFormVO.setFromtype(formFormEntity.getFromtype());
            onLineFormVO.setDialogwidth(formFormEntity.getDialogwidth());
            OnLineFormUtils.setOnLineFormCache(fid, onLineFormVO);
        }

        return onLineFormVO;
    }

    @Override
    public R<List<QueryFromVO>> queryFromList(QueryFromReq req) {
        int page = req.getPage();
        int rows = req.getRows();
        PageHelper.startPage(page, rows);
        List<QueryFromVO> list = this.baseMapper.queryFromList(req);
        PageInfo pageInfo = new PageInfo(list);
        return R.newOk(list, (int) pageInfo.getTotal());
    }
    @Override
    public R<List<QueryFromVO>> queryFromDesignList(QueryFromReq req) {
        int page = req.getPage();
        int rows = req.getRows();
        PageHelper.startPage(page, rows);
        List<QueryFromVO> list = this.baseMapper.queryFromDesignList(req);
        PageInfo pageInfo = new PageInfo(list);
        return R.newOk(list, (int) pageInfo.getTotal());
    }
    @Override
    public void clearFromCache() {
        OnLineFormUtils.clearOnLineFormCache();
    }

    @Override
    public List<FromSaveInfoReq> exportFormConfig(List<String> formIds) {
        List<FormFormEntity> formlist = this.baseMapper.selectBatchIds(formIds);
        List<FromSaveInfoReq> formInfoVOList = new ArrayList<>();
        formlist.forEach(ee -> {
            formInfoVOList.add(getFromConfigInfo(ee));
        });
        return formInfoVOList;
    }

    public FromSaveInfoReq getFromConfigInfo(FormFormEntity ee) {
        FromSaveInfoReq param = new FromSaveInfoReq();
        FormInfoVO formInfoVO = JSON.parseObject(ee.getFromdesignjson(), FormInfoVO.class);
        param.setId(ee.getFormFormId());
        param.setFromName(ee.getFromname());
        param.setFromCode(ee.getFromcode());
        param.setFremarks(ee.getFremarks());
        param.setDesignType(formInfoVO.getDesignType());
        param.setFromcontent(ee.getFromcontent());
        param.setFromdesignjson(ee.getFromdesignjson());
        param.setFromtype(ee.getFromtype());
        param.setFromTableName(ee.getFromTableName());
        param.setDialogwidth(ee.getDialogwidth());
        param.setIsBuildApi(ee.getIsBuildapi() == 1L);
        param.setQueryWhere(formInfoVO.getQueryWhere());
        param.setRolelisted(formInfoVO.getRolelisted());
        param.setMenuType(formInfoVO.getMenuType());
        param.setParentMenuid(formInfoVO.getParentMenuid());
        param.setFormIcon(formInfoVO.getFormIcon());
        param.setFicontype(formInfoVO.getFicontype());
        param.setFromsavelogic(formInfoVO.getFromsavelogic());
        return param;
    }

    ///将表单信息导入
    @Override
    @Transactional
    public R importFormConfig(List<FromSaveInfoReq> formlist, BuildFormMybatisInterfaceService buildFormMybatisInterfaceService, BuildFlowMybatisInterfaceService buildFlowMybatisInterfaceService) {
        formlist.forEach(ee -> {
            SaveFromData(ee);
//            SaveFromMenuDataReq fromMenuDataReq=new SaveFromMenuDataReq()
//            saveFromMenuData()
        });
        return R.newOk("导入成功！");
    }
}

