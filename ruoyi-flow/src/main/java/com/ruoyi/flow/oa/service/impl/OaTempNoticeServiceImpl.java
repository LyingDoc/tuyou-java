package com.ruoyi.flow.oa.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.flow.oa.entity.OaSendNoticeEntity;
import com.ruoyi.flow.oa.vo.ProcessChartVO;
import com.ruoyi.flow.vo.PageVO;
import com.ruoyi.flow.vo.R;
import com.ruoyi.flow.oa.entity.OaTempNoticeEntity;
import com.ruoyi.flow.oa.mapper.OaTempNoticeMapper;
import com.ruoyi.flow.oa.service.IOaTempNoticeService;
import net.sf.json.JSONObject;
import ognl.Ognl;
import ognl.OgnlContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * 通知模板表 服务实现类富士达范德萨都是
 * </p>
 *
 * @author 刘亚平
 * @since 2023-01-15
 */
@Service
public class OaTempNoticeServiceImpl extends ServiceImpl<OaTempNoticeMapper, OaTempNoticeEntity> implements IOaTempNoticeService {
    /**
    * 通过主键获取数据
    *
    * @param fid 主键
    * @return 是否成功
    */
    @Override
    public  OaTempNoticeEntity getById(String id){
          return    baseMapper.selectById(id);
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
    * @param fid 主键
    * @return 是否成功
    */
    @Override
    public R<OaTempNoticeEntity> saveOaTempNoticeEntity(OaTempNoticeEntity oatempnoticeentity){
        int row = 0;
        try {
          if( saveOrUpdate(oatempnoticeentity)){
                return R.newOk(oatempnoticeentity);
          }else{
               return R.newError("保存失败！");
          }
        } catch (Exception e) {
            e.printStackTrace();
            return R.newError("[{oatempnoticeentity}] 未知异常");
        }

    }

    /**
    * 通过id集合批量删除
    *
    * @param fid 主键
    * @return 是否成功
    */
    @Override
    public boolean deleteOaTempNoticeEntityList(List<Integer> ids) {
            int row = 0;
            try {
            row = this.baseMapper.deleteBatchIds(ids);
            } catch (Exception e) {
            e.printStackTrace();
            log.error("[{oatempnoticeentity}] 未知异常");
            }
            return row>0;
    }

    @Override
    public R<List<OaTempNoticeEntity>> getOaTempNoticeEntityList(PageVO<OaTempNoticeEntity> req){
            Page<OaTempNoticeEntity> page = new Page<>(req.getPage(),req.getPagesize());
        QueryWrapper<OaTempNoticeEntity> queryWrapper=     new QueryWrapper<OaTempNoticeEntity>();
        if(StringUtils.isNotBlank(req.getParam().getTempName())){
            queryWrapper.like("temp_name",req.getParam().getTempName());
        }
        if(StringUtils.isNotBlank(req.getParam().getTempType())){
            queryWrapper.eq("temp_type",req.getParam().getTempType());
        }
        queryWrapper.orderByAsc("update_Date");
            Page oatempnoticeentityPage = this.baseMapper.selectPage(page, queryWrapper);
           return R.newOk(oatempnoticeentityPage.getRecords(),oatempnoticeentityPage.getTotal()) ;
    }


    public R<String> SendNoticeContentById(String tempNoticeId, JSONObject jsonObject) {
        OaTempNoticeEntity oaTempNotice = baseMapper.selectById(tempNoticeId);
        if (oaTempNotice == null) {
            return R.newError("获取发送消息模板出错！");
        }
        return R.newOk(SendNoticeContent(oaTempNotice.getTempContent(), jsonObject));
    }

    /**
     * 根据消息模板内容 替换成发送消息模板模板内容
     *
     * @param
     */
    public String SendNoticeContent(String tempContent, JSONObject jsonObject) {
        OgnlContext context = getOgnlContext(jsonObject);
        Pattern p = Pattern.compile("\\#\\{([^}]*)\\}");
        Matcher m = p.matcher(tempContent); // 获取 matcher 对象
        while (m.find()) {
            try {
                String filed = m.group();
                String filedName = filed.substring(2, filed.length() - 1);
                Object expression = Ognl.parseExpression(ReversalFormatBeForeTrans(filedName));
                Object value = Ognl.getValue(expression, context, context);
                if (value.toString().toLowerCase().equals("null")) {
                    tempContent = tempContent.replace(filed, "");
                } else if (!StringUtils.isNotBlank(value.toString())) {
                    tempContent = tempContent.replace(filed, "");
                } else {
                    tempContent = tempContent.replace(filed, value.toString());
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        return tempContent;
    }

    OgnlContext getOgnlContext(JSONObject param) {
        OgnlContext context = new OgnlContext();
        Iterator<String> it = param.keys();
        while (it.hasNext()) {
            String key = it.next();  // 获得key
            Object value = param.get(key);
            context.put(key, value);
        }
        return context;
    }

    /***
     * 替换xml不能识别的&,<,>特殊字符
     * @param data
     * @return
     */
    String ReversalFormatBeForeTrans(String data) {
        if ((data == null) | "".equals(data)) {
            return "";
        } else {
            data = data.replace("&amp;", "&");
            data = data.replace("&lt;", "<");
            data = data.replace("&gt;", ">");
            return data;
        }
    }

   public R<List<OaTempNoticeEntity>> queryTempNotice( PageVO<OaTempNoticeEntity>  oaTempNotice){
       Page<OaTempNoticeEntity> page = new Page<>(oaTempNotice.getPage(), oaTempNotice.getPagesize());
       QueryWrapper<OaTempNoticeEntity> queryWrapper=  new QueryWrapper<OaTempNoticeEntity>();
       if(StringUtils.isNotBlank( oaTempNotice.getParam().getTempName())) {
          queryWrapper.like("temp_name",oaTempNotice.getParam().getTempName());
       }
       if(StringUtils.isNotBlank( oaTempNotice.getParam().getTempType())) {
           queryWrapper.eq("temp_type",oaTempNotice.getParam().getTempType());
       }
       Page oaprocessentityPage = this.baseMapper.selectPage(page,queryWrapper);
       return R.newOk(oaprocessentityPage.getRecords(), oaprocessentityPage.getTotal());
    }


}

