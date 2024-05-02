package com.ruoyi.flow.form.service.impl;


import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.flow.comm.WebApiUtils;
import com.ruoyi.flow.form.req.QueryApiListReq;
import com.ruoyi.flow.form.vo.QueryApiListVO;
import com.ruoyi.flow.vo.HeadEntityVO;
import com.ruoyi.flow.vo.PageVO;
import com.ruoyi.flow.vo.R;
import com.ruoyi.flow.form.entity.FormApiconfigBackEntity;
import com.ruoyi.flow.form.entity.FormApiconfigEntity;
import com.ruoyi.flow.form.entity.FormPageEntity;
import com.ruoyi.flow.form.mapper.FormApiconfigMapper;
import com.ruoyi.flow.form.req.SaveApiConfigReq;
import com.ruoyi.flow.form.req.SaveConfigShowCelNamesReq;
import com.ruoyi.flow.form.req.SaveShowCtrlWhereReq;
import com.ruoyi.flow.form.vo.ApiConfigVO;
import com.ruoyi.flow.form.service.IFormApiconfigService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类富士达范德萨都是
 * </p>
 *
 * @author 刘亚平
 * @since 2023-01-15
 */
@Service
public class FormApiconfigServiceImpl extends ServiceImpl<FormApiconfigMapper, FormApiconfigEntity> implements IFormApiconfigService {
    /**
     * 通过主键获取数据
     *
     * @param fid 主键
     * @return 是否成功
     */
    @Override
    public FormApiconfigEntity getById(String id) {
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
        FormApiconfigEntity formApiconfigEntity=  this.getById(fid);
        WebApiUtils.removeWebApiCache(formApiconfigEntity.getCallMethodCode());
        return this.baseMapper.deleteById(fid) > 0;
    }

    /**
     * 通过主键修改数据
     *
     * @param fid 主键
     * @return 是否成功
     */
    @Override
    public R<FormApiconfigEntity> saveFormApiconfigEntity(FormApiconfigEntity formapiconfigentity) {
        int row = 0;
        try {
            WebApiUtils.removeWebApiCache(formapiconfigentity.getCallMethodCode());
            if (saveOrUpdate(formapiconfigentity)) {
                return R.newOk(formapiconfigentity);
            } else {
                return R.newError("保存失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.newError("[{formapiconfigentity}] 未知异常");
        }

    }

    /**
     * 通过id集合批量删除
     *
     * @param fid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteFormApiconfigEntityList(List<Integer> ids) {
        int row = 0;
        try {
            List<FormApiconfigEntity> apiconfigEntityList=    this.baseMapper.selectBatchIds(ids);
            apiconfigEntityList.forEach(ee->{
                WebApiUtils.removeWebApiCache(ee.getCallMethodCode());
            });
            row = this.baseMapper.deleteBatchIds(ids);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[{formapiconfigentity}] 未知异常");
        }
        return row > 0;
    }

    @Override
    public R<List<FormApiconfigEntity>> getFormApiconfigEntityList(PageVO<FormApiconfigEntity> req) {
        Page<FormApiconfigEntity> page = new Page<>(req.getPage(), req.getPagesize());
        Page formapiconfigentityPage = this.baseMapper.selectPage(page, new QueryWrapper<FormApiconfigEntity>());
        return R.newOk(formapiconfigentityPage.getRecords(), formapiconfigentityPage.getTotal());
    }

    @Autowired
    private com.ruoyi.flow.form.service.impl.FormPageServiceImpl pageService;

    @Autowired
    private com.ruoyi.flow.form.service.impl.FormApiconfigBackServiceImpl apiConfigBackService;
    @Autowired
    private FormSqlcontentServiceImpl sqlContentService;
    ///该方法提供给controller使用
    public  R<String> saveApiConfigInfo(SaveApiConfigReq param){
        return saveApiConfigInfo(param,null);
    }
    ///该方法提供给保存表单创建 列表接口使用
    public R<String> saveApiConfigInfo(SaveApiConfigReq param, List<HeadEntityVO> newheadlist) {
        QueryWrapper<FormApiconfigEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("page_id", param.getPageId()).eq("method_code", param.getMethodCode());

        FormApiconfigEntity entity = this.getOne(queryWrapper,false);
        FormPageEntity pageEntity = pageService.getById(param.getPageId());
        if (pageEntity == null) {
            return R.newError("找不到包信息");
        }
        String[] showCelList=null;
        String[] showCtrlWhereList=null;
        List<HeadEntityVO> oldheadlist=new ArrayList<>();
        String showCelstr="";
        String showCtrlWherestr="";
        ///如果存在就进行备份
        if (entity != null) {
            FormApiconfigBackEntity backEntity = new FormApiconfigBackEntity();
            BeanUtils.copyProperties(entity, backEntity);
            apiConfigBackService.backApiConfig(backEntity);
            this.baseMapper.deleteById(entity.getFormApiconfigId());

        }
        //newheadlist不等于null 说明表单保存时 创建列表接口
        if(newheadlist!=null&&entity!=null) {
            if (StringUtils.isNotBlank(entity.getShowCelNames())) {
                showCelList=  entity.getShowCelNames().split(";");
                for(int i=0;i<showCelList.length;i++) {
                    String item=showCelList[i];
                    if(  newheadlist.stream().filter(ee->item.equals(ee.getBindname())).count()>0)    {
                        showCelstr=StringUtils.isNotBlank(showCelstr)?  showCelstr+";"+item:item;
                    }
                }
            }
            if (StringUtils.isNotBlank(entity.getShowCtrlWhere())) {
                showCtrlWhereList=  entity.getShowCtrlWhere().split(";");
                for(int i=0;i<showCtrlWhereList.length;i++) {
                    String item=showCtrlWhereList[i];
                    if(  newheadlist.stream().filter(ee->item.equals(ee.getBindname())).count()>0)    {
                        showCtrlWherestr=StringUtils.isNotBlank(showCtrlWherestr)?  showCtrlWherestr+";"+item:item;
                    }
                }
            }
        }
        Long sqlId = IdWorker.getId();
        entity = new FormApiconfigEntity();
        BeanUtils.copyProperties(param, entity);
        entity.setMethodType(param.getMethodType());
        entity.setExecuteSqlId(sqlId.toString());
        entity.setCallMethodCode(pageEntity.getPageName() + "." + entity.getMethodCode());
        entity.setIsSystem(param.getIsSystem());
        sqlContentService.saveSqlContent(param.getSqlContent(), sqlId);
        if(newheadlist!=null) {
            entity.setShowCelNames(showCelstr);
            entity.setShowCtrlWhere(showCtrlWherestr);
        }
        this.baseMapper.insert(entity);
        //修改了接口 清除对应缓存
        WebApiUtils.removeWebApiCache(entity.getCallMethodCode());
        return R.newOk(entity.getFormApiconfigId());
    }

    public ApiConfigVO getApiConfigById(String fid) {
        ApiConfigVO apiEntity = super.baseMapper.getApiConfigByCallMethodCode(null, fid);
        if (apiEntity != null) {
            if (apiEntity.getMethodType() != null) {
                switch (apiEntity.getMethodType()) {
                    case "1":
                        apiEntity.setMethodTypeName("列表");
                        break;
                    case "2":
                        apiEntity.setMethodTypeName("查询");
                        break;
                    case "3":
                        apiEntity.setMethodTypeName("操作");
                        break;
                    case "4":
                        apiEntity.setMethodTypeName("导入");
                        break;
                    case "5":
                        apiEntity.setMethodTypeName("对象");
                        break;
                }

            }
            if(!"4".equals(apiEntity.getMethodType()) ) {
                apiEntity.setApiUrl("comm/apiComm/" + apiEntity.getCallMethodCode());
            }else{
                apiEntity.setApiUrl("comm/importExlce/" + apiEntity.getCallMethodCode());
            }
        }
        return apiEntity;
    }

    /**
     * 根据回调方法编码获取接口配置信息
     *
     * @param callMethodCode
     * @return
     */
    public ApiConfigVO getApiConfigByCallMethodCode(String callMethodCode) {
        ApiConfigVO apiEntity = WebApiUtils.getWebApiCache(callMethodCode);
        if(apiEntity==null){
            apiEntity= super.baseMapper.getApiConfigByCallMethodCode(callMethodCode, null);
            if (apiEntity != null) {
                WebApiUtils.setWebApiCache(callMethodCode,apiEntity);
            }
        }
        if (apiEntity != null) {
            apiEntity.setApiUrl("comm/apiComm/" + callMethodCode);
        }
        return apiEntity;
    }

    public FormApiconfigEntity queryByApiCode(String apicode, int apitype) {
        QueryWrapper<FormApiconfigEntity> queryWrapper = new QueryWrapper();
        if (StringUtils.isNotBlank(apicode)) {
            queryWrapper.eq("methodCode", apicode);
        }
        queryWrapper.eq("methodType", apitype);
        return this.getOne(queryWrapper,false);

    }

    public void rollBack(String callMethodCode, Integer versions) {
        FormApiconfigBackEntity backEntity = apiConfigBackService.getApiConfigVersion(callMethodCode, versions);
        QueryWrapper<FormApiconfigEntity> queryWrapper = new QueryWrapper();
        queryWrapper.eq("call_method_code", callMethodCode);
        this.baseMapper.delete(queryWrapper);
        FormApiconfigEntity entity = new FormApiconfigEntity();
        BeanUtils.copyProperties(backEntity, entity);
        WebApiUtils.removeWebApiCache(backEntity.getCallMethodCode());
        this.saveOrUpdate(entity);
        apiConfigBackService.deleteById(backEntity.getFormApiconfigBackId());
    }

    public boolean saveConfigShowCelNames(SaveConfigShowCelNamesReq param) {
        QueryWrapper<FormApiconfigEntity> queryWrapper = new QueryWrapper();
        queryWrapper.eq("call_method_code", param.getCallMethodCodeName());

        FormApiconfigEntity apiConfigEntity = this.getOne(queryWrapper,false);
        apiConfigEntity.setShowCelNames(param.getShowCelNames());
        apiConfigEntity.setHeadList(param.getHeadList());
        WebApiUtils.removeWebApiCache(apiConfigEntity.getCallMethodCode());
        this.saveOrUpdate(apiConfigEntity);
        return true;
    }

    public boolean saveShowCtrlWhere(SaveShowCtrlWhereReq param) {
        QueryWrapper<FormApiconfigEntity> queryWrapper = new QueryWrapper();
        queryWrapper.eq("call_method_code", param.getCallMethodCodeName());
        FormApiconfigEntity apiConfigEntity = this.getOne(queryWrapper,false);
        apiConfigEntity.setShowCtrlWhere(param.getShowCtrlWhere());
        WebApiUtils.removeWebApiCache(apiConfigEntity.getCallMethodCode());
        this.saveOrUpdate(apiConfigEntity);
        return true;
    }

    public void clearWebApiCache(){
        WebApiUtils.clearWebApiCache();
    }


    public  R<List<QueryApiListVO>>  queryApiList(QueryApiListReq req){
        int page =req.getPage();
        int rows = req.getRows();
        PageHelper.startPage(page, rows);
        List<QueryApiListVO> list=    this.baseMapper.queryApiList(req);
        PageInfo pageInfo = new PageInfo(list);
        return R.newOk(list,(int)pageInfo.getTotal());
    }
}

