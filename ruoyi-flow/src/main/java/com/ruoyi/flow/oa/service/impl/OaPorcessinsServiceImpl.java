package com.ruoyi.flow.oa.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.flow.form.entity.FormFormEntity;
import com.ruoyi.flow.oa.vo.ProcessInsInfoVO;
import com.ruoyi.flow.vo.CurrentUserInfoVO;
import com.ruoyi.flow.vo.HomeDataVO;
import com.ruoyi.flow.vo.PageVO;
import com.ruoyi.flow.vo.R;
import com.ruoyi.flow.oa.entity.*;
import com.ruoyi.flow.oa.mapper.OaPorcessinsMapper;
import com.ruoyi.flow.oa.req.NodeInfoReq;
import com.ruoyi.flow.oa.req.ProcessInfoReq;
import com.ruoyi.flow.oa.req.QueryPorcessInsListReq;
import com.ruoyi.flow.oa.service.IOaPorcessinsService;
import com.ruoyi.flow.oa.vo.QueryPorcessinsVO;
import com.ruoyi.flow.form.service.impl.ApiCommService;
import com.ruoyi.flow.form.service.impl.FormFormServiceImpl;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类富士达范德萨都是
 * </p>
 *
 * @author 刘亚平
 * @since 2023-01-15
 */
@Service
public class OaPorcessinsServiceImpl extends ServiceImpl<OaPorcessinsMapper, OaPorcessinsEntity> implements com.ruoyi.flow.oa.service.IOaPorcessinsService {
    @Resource
    private ApiCommService apiCommService;
    @Resource
    private OaProcessServiceImpl oaProcessService;
    @Resource
    private OaActinsServiceImpl oaActinsService;
    @Resource
    private OaProcesschartServiceImpl oaProcesschartService;
    @Resource
    private FormFormServiceImpl formFormService;
    @Resource
    private  OaActinscommunicateServiceImpl actinscommunicateService;
    @Resource
    private  OaFromdataServiceImpl oaFromdataService;
    @Resource
    private  OaActServiceImpl oaActService;
    /**
    * 通过主键获取数据
    *
    * @param fid 主键
    * @return 是否成功
    */
    @Override
    public  OaPorcessinsEntity getById(Long id){
          return    baseMapper.selectById(id);
    }
    /**
     * 通过主键删除数据
     *
     * @param fid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long fid) {
            return this.baseMapper.deleteById(fid) > 0;
    }
    /**
    * 通过主键修改数据
    *
    * @param fid 主键
    * @return 是否成功
    */
    @Override
    public R<OaPorcessinsEntity> saveOaPorcessinsEntity(OaPorcessinsEntity oaporcessinsentity){
        int row = 0;
        try {
          if( saveOrUpdate(oaporcessinsentity)){
                return R.newOk(oaporcessinsentity);
          }else{
               return R.newError("保存失败！");
          }
        } catch (Exception e) {
            e.printStackTrace();
            return R.newError("[{oaporcessinsentity}] 未知异常");
        }

    }

    /**
    * 通过id集合批量删除
    *
    * @param fid 主键
    * @return 是否成功
    */
    @Override
    public boolean deleteOaPorcessinsEntityList(List<Integer> ids) {
            int row = 0;
            try {
            row = this.baseMapper.deleteBatchIds(ids);
            } catch (Exception e) {
            e.printStackTrace();
            log.error("[{oaporcessinsentity}] 未知异常");
            }
            return row>0;
    }

    @Override
    public R<List<OaPorcessinsEntity>> getOaPorcessinsEntityList(PageVO<OaPorcessinsEntity> req){
            Page<OaPorcessinsEntity> page = new Page<>(req.getPage(),req.getPagesize());
            Page oaporcessinsentityPage = this.baseMapper.selectPage(page, new QueryWrapper<OaPorcessinsEntity>());
           return R.newOk(oaporcessinsentityPage.getRecords(),oaporcessinsentityPage.getTotal()) ;
    }


    public R<ProcessInsInfoVO> GetProcessFromInfo(String processid, String processinsid, String actinsid, String UserName, String viewmodel, String processBid, String processCode)  {
        ///进入页面的场景有 我的待办 我的已办已阅 我的发起 查看
        ////进入该页面有如下几种场景  发起流程，我的待办，申请人查看流程，审批人查看流程，抄送人查看，沟通人查看 其他方式查看
        ////发起流程 processid！=null processinsid==null actinsid==null 获取发起的节点权限
        ////我的待办 processid！=null processinsid!=null actinsid!=null viewmodel==todo 并且当前节点的审批人是 当前用户，并且状态为未处理  包含沟通 抄送 参数必须传入 获取节点权限
        ////申请人查看流程 processid！=null processinsid!=null  actinsid!=null viewmodel==start 当前用户等于发起人或申请人 参数必须传入 获取发起的节点权限
        // 审批人查看流程  processid！=null processinsid!=null  actinsid!=null viewmodel=read 只要求传入 processinsid 获取节点权限
        // processid！=null processinsid!=null   viewmodel=其它 并且对应抄送表中抄送人等于当前用户，数据权限默认设置为默认
        OaProcesschartEntity processchart=new OaProcesschartEntity();
        if(!StringUtils.isNotBlank(viewmodel)||viewmodel.equals("start")){
            if(StringUtils.isNotBlank(processid)){
                processchart= this.oaProcesschartService.queryByProcessId(processid);
            }else if(StringUtils.isNotBlank(processBid)){
                OaProcessEntity tappProcessEntity= this.oaProcessService.getStartMaxVersionProcess(processBid);
                if(tappProcessEntity!=null){
                    if(1==tappProcessEntity.getProcessState()||3==tappProcessEntity.getProcessState()){
                        return R.newError("流程已停用,请联系管理员！");
                    }
                    processchart=oaProcesschartService.queryByProcessId(tappProcessEntity.getOaProcessId());
                    processid=tappProcessEntity.getOaProcessId();
                }else{
                    return R.newError("未找到流程模板信息");
                }
            }else if(StringUtils.isNotBlank(processCode)){
                OaProcessEntity tappProcessEntity= this.oaProcessService.getStartMaxVersionProcessCode(processCode);
                if(tappProcessEntity!=null){
                    if(1==tappProcessEntity.getProcessState()||3==tappProcessEntity.getProcessState()){
                        return R.newError("流程已停用,请联系管理员！");
                    }
                    processchart=oaProcesschartService.queryByProcessId(tappProcessEntity.getOaProcessId());
                    processid=tappProcessEntity.getOaProcessId();
                }else{
                    return R.newError("未找到流程模板信息");
                }
            }else{
                return R.newError("未找到流程模板信息");
            }
        }else{
            if(StringUtils.isNotBlank(processid)){
                processchart= this.oaProcesschartService.queryByProcessId(processid);
            }else if(StringUtils.isNotBlank(processinsid)){
                OaPorcessinsEntity porcessinsEntity=   this.getById(processinsid);
                if(porcessinsEntity==null){
                    return R.newError("找不到流程实例信息！");
                }
                processchart= this.oaProcesschartService.queryByProcessId(porcessinsEntity.getOaProcessId());
                processid=porcessinsEntity.getOaProcessId();
            }else{
                return R.newError("非法进入或者没有权限查看！");
            }
        }
        if(processchart.getOaProcesschartId()==null){
            return R.newError("未找到流程信息");
        }
        ProcessInsInfoVO resultJson = new ProcessInsInfoVO();
        ProcessInfoReq processInfoReq = JSON.parseObject(processchart.getFlowdata(),ProcessInfoReq.class);
        FormFormEntity from = formFormService.getById(processInfoReq.getFromID());
        if(from==null){
            return R.newError("找不到流程表单信息");
        }
        resultJson.setProcessid(processid);
        resultJson.setHead( JSON.toJSONString(processInfoReq.getHead()));
        resultJson.setFlowname(processInfoReq.getFlowName());
        resultJson.setBusinessModuleID(processInfoReq.getBusinessModuleID());
        resultJson.setBusinessMoudle(processInfoReq.getBusinessMoudle());
        resultJson.setFlowTempContext( from.getFromcontent());
        List<OaActinsEntity> tappActinsEntityList=  oaActinsService.getCompletedNodeList(processinsid);
        List<String> actchartidList=new ArrayList<>();
        String actChartId = "F_Start";
        String currentChartId="";
        if(tappActinsEntityList!=null){
            actchartidList=   tappActinsEntityList.stream().map(OaActinsEntity::getActchartId).distinct().collect(Collectors.toList());
            String findchartId= tappActinsEntityList.stream().filter(ee->"1".equals(ee.getActinsStatus())).map(OaActinsEntity::getActchartId).findFirst().orElse(null);
            if(StringUtils.isNotBlank(findchartId)){
                currentChartId= findchartId;
            }
        }
        resultJson.setActchartids( actchartidList);
        String flowstatusName="";
        String flowstatus="0";
        resultJson.setOperateType(viewmodel);
        if (viewmodel!=null&&viewmodel.equals("todo")) { //我的待办待阅进入
            if (!StringUtils.isNotBlank(processinsid) ||!StringUtils.isNotBlank(actinsid) ) {
                return R.newError("非法进入或者没有权限查看！");
            }
            OaActinsEntity tappActins = oaActinsService.getById(actinsid);
            if (tappActins == null) {
                return R.newError("非法进入或者没有权限查看！");
            }
            OaPorcessinsEntity tappPorcessins= this.getById(processinsid);
            if(tappPorcessins==null){
                return R.newError("非法进入或者没有权限查看！");
            }
            flowstatusName=tappPorcessins.getCurrentactname();
            flowstatus=tappPorcessins.getPorcessinsStatus();
            actChartId=tappActins.getActchartId();
            ///tappActins.getFstatus()=0 流程正在沟通 等于1正常审批界面
            if (tappActins.getApproversuser().equals(UserName) && ("1".equals(tappActins.getActinsStatus())|| "0".equals(tappActins.getActinsStatus()))) {
                actChartId = tappActins.getActchartId();
                NodeInfoReq nodeInfoReq=getNodeInfo(processInfoReq.getNodeList(),actChartId);
//				JSONObject currentNodeJson = json.getJSONObject("Node").getJSONObject(actChartId);
                resultJson.setOperatePermission(JSON.toJSONString(nodeInfoReq.getNodeinfo().getOperatePermission()));
                resultJson.setAttachmentPermission( JSON.toJSONString(nodeInfoReq.getNodeinfo().getAttachmentPermission()));
                resultJson.setDataPermission(JSON.toJSONString(nodeInfoReq.getNodeinfo().getDataPermission()));
                resultJson.setIsRead("0".equals(tappActins.getActinsStatus()));
                resultJson.setActid( tappActins.getOaActId());
                if("0".equals(tappActins.getActinsStatus())){
                    List<OaActinscommunicateEntity> tappActinscommunicateList= actinscommunicateService.queryTodoCommunicate(UserName,actinsid,processinsid);
                    List<OaActinscommunicateEntity> actinscommunicateList= tappActinscommunicateList.stream().filter(ee->"5".equals(ee.getCommunicatetype())).collect(Collectors.toList());
                    if(actinscommunicateList.size()>0){
                        resultJson.setIsRead( true);
                        resultJson.setOperateType("goutong");
                    }else {
                        resultJson.setOperateType( "quxiaogoutong");
                    }
                }else{
                    if(tappActins.getOpendate()!=null) {
                        tappActins.setOpendate(new Date());
                        oaActinsService.saveOrUpdate(tappActins);
                    }
                }
                resultJson.setFromData(oaFromdataService.getById(tappPorcessins.getOaFromdataId()).getFromdata());
            }   else {
                List<OaActinscommunicateEntity> tappActinscommunicateList= actinscommunicateService.queryTodoCommunicate(UserName,actinsid,processinsid);
                if(!tappActinscommunicateList.isEmpty()&&tappActinscommunicateList.size()>0){
                    actChartId = tappActins.getActchartId();
                    NodeInfoReq nodeInfoReq=getNodeInfo(processInfoReq.getNodeList(),actChartId);

                    resultJson.setOperatePermission("[]");
                    resultJson.setAttachmentPermission( JSON.toJSONString(nodeInfoReq.getNodeinfo().getAttachmentPermission()));
                    resultJson.setDataPermission(JSON.toJSONString(nodeInfoReq.getNodeinfo().getDataPermission()));
                    resultJson.setActid( tappActins.getOaActId());
                    resultJson.setFromData(oaFromdataService.getById(tappPorcessins.getOaFromdataId()).getFromdata());
                    for (OaActinscommunicateEntity item:tappActinscommunicateList) {
                        if("5".equals(item.getCommunicatetype())){ ///沟通
                            item.setOpendate(new Date());
                            resultJson.setIsRead( true);
                            resultJson.setOperateType("goutong");
                        }
                        if("6".equals(item.getCommunicatetype())){
                            item.setOpendate(new Date());
                            item.setCompleteddate(new Date());
                            item.setCommunicateStatus("2");
                            resultJson.setIsRead(true);
                            resultJson.setOperateType( "CC");
                        }
                        actinscommunicateService.updateById(item);
                    }
                }else {
                    ///这里需要判断当前节点是否有抄送和沟通  如果都不是 就是非法进入
                    return R.newError("当前节点已处理或者没有权限！");
                }
            }
        }
        else if (viewmodel!=null&&viewmodel.equals("read")) { //我的已办已阅进入
            if (StringUtils.isEmpty(processinsid) || StringUtils.isEmpty(actinsid)) {
                return R.newError("非法进入或者没有权限查看！");
            }
            OaActinsEntity tappActins = oaActinsService.getById(actinsid);
            if (tappActins == null) {
                return R.newError("非法进入或者没有权限查看！");
            }
            OaPorcessinsEntity tappPorcessins= this.getById(processinsid);
            if(tappPorcessins==null){
                return R.newError("非法进入或者没有权限查看！");
            }
            flowstatusName=tappPorcessins.getCurrentactname();
            flowstatus=tappPorcessins.getPorcessinsStatus();
            actChartId = tappActins.getActchartId();
            NodeInfoReq nodeInfoReq=getNodeInfo(processInfoReq.getNodeList(),actChartId);
            resultJson.setOperatePermission(JSON.toJSONString(nodeInfoReq.getNodeinfo().getOperatePermission()));
            resultJson.setAttachmentPermission(JSON.toJSONString(nodeInfoReq.getNodeinfo().getAttachmentPermission()));
            resultJson.setDataPermission( JSON.toJSONString(nodeInfoReq.getNodeinfo().getDataPermission()));
            resultJson.setIsRead( true);
            resultJson.setActid( tappActins.getOaActId());
            resultJson.setFromData(oaFromdataService.getById(tappPorcessins.getOaFromdataId()).getFromdata());
        }
        else if (viewmodel!=null&&viewmodel.equals("start")) { ///从我的发起进入 主要是控制是否需要撤销废弃
            if (StringUtils.isEmpty(processinsid)  ) {
                return R.newError("非法进入或者没有权限查看！");
            }
            OaPorcessinsEntity tappPorcessins= this.getById(processinsid);
            if(tappPorcessins==null){
                return R.newError("非法进入或者没有权限查看！");
            }
            flowstatusName=tappPorcessins.getCurrentactname();
            flowstatus=tappPorcessins.getPorcessinsStatus();
            ///判断发起人跟当前用户是否一致 如果不一致 代表非法进入
            if(!tappPorcessins.getStarter().equals(UserName)){
                return R.newError("非法进入或者没有权限查看！");
            }
            resultJson.setIsRead(true);
            if(StringUtils.isEmpty(actinsid)){
                OaActinsEntity tappActins = oaActinsService.getById(actinsid);
                if (tappActins != null) {
                    actChartId = tappActins.getActchartId();
                    resultJson.setActid(tappActins.getOaActId());
                }
            }else{
                List<OaActinsEntity> tappActins =   oaActinsService.queryTodoActins(processinsid);
                if (tappActins != null) {
                    OaActinsEntity actins=tappActins.get(0);
                    actChartId = actins.getActchartId();
                    resultJson.setActid(actins.getOaActId());
                }
            }
            NodeInfoReq nodeInfoReq=getNodeInfo(processInfoReq.getNodeList(),actChartId);

            resultJson.setOperatePermission(JSON.toJSONString(nodeInfoReq.getNodeinfo().getOperatePermission()));
            resultJson.setAttachmentPermission(JSON.toJSONString(nodeInfoReq.getNodeinfo().getAttachmentPermission()));
            resultJson.setDataPermission( JSON.toJSONString(nodeInfoReq.getNodeinfo().getDataPermission()));
            resultJson.setFromData(oaFromdataService.getById(tappPorcessins.getOaFromdataId()).getFromdata());
        }
        else if (viewmodel!=null&&viewmodel.equals("draft")) {
            if (StringUtils.isEmpty(processinsid) ) {
                return R.newError("非法进入或者没有权限查看！");
            }
            OaPorcessinsEntity tappPorcessins= this.getById(processinsid);
            if(tappPorcessins==null){
                return R.newError("非法进入或者没有权限查看！");
            }
            flowstatusName=tappPorcessins.getCurrentactname();
            flowstatus=tappPorcessins.getPorcessinsStatus();
            ///判断发起人跟当前用户是否一致 如果不一致 代表非法进入
            if(!tappPorcessins.getStarter().equals(UserName)){
                return R.newError("非法进入或者没有权限查看！");
            }
            if("0".equals(tappPorcessins.getStarter())) {
                resultJson.setIsRead( true);
            }else{
                resultJson.setIsRead( false);
            }
            if(tappActinsEntityList.size()>0&&"F_Start".equals(actChartId)){
                resultJson.setOperateType("read");
            }
            NodeInfoReq nodeInfoReq=getNodeInfo(processInfoReq.getNodeList(),actChartId);
            resultJson.setOperatePermission( JSON.toJSONString(nodeInfoReq.getNodeinfo().getOperatePermission()));
            resultJson.setAttachmentPermission( JSON.toJSONString(nodeInfoReq.getNodeinfo().getAttachmentPermission()));
            resultJson.setDataPermission(JSON.toJSONString(nodeInfoReq.getNodeinfo().getDataPermission()));
            //resultJson.put("actid", currentNodeJson.getString("atctid"));
            resultJson.setFromData(oaFromdataService.getById(tappPorcessins.getOaFromdataId()).getFromdata());
        }
        else {
            if (StringUtils.isEmpty(processinsid)  && StringUtils.isEmpty(actinsid) &&StringUtils.isEmpty(viewmodel)) { ///发起的入口
                NodeInfoReq nodeInfoReq=getNodeInfo(processInfoReq.getNodeList(),actChartId);
                resultJson.setOperatePermission( JSON.toJSONString(nodeInfoReq.getNodeinfo().getOperatePermission()));
                resultJson.setAttachmentPermission( JSON.toJSONString(nodeInfoReq.getNodeinfo().getAttachmentPermission()));
                resultJson.setDataPermission(JSON.toJSONString(nodeInfoReq.getNodeinfo().getDataPermission()));
                resultJson.setIsRead( false);
                resultJson.setActid(oaActService.queryByProcessIdActchartId(processid,actChartId).getOaActId());
                resultJson.setFromData("{}");
                resultJson.setOperateType( "faqi");
            } else {  ///其他入口  如果不给需要判断有没有权限 查看 后期再加
                resultJson.setIsRead(true);
                resultJson.setOperatePermission( "[]");
                resultJson.setAttachmentPermission( "[]");
                resultJson.setDataPermission( "[]");
                resultJson.setActid("0");
                if(StringUtils.isNotBlank(processinsid)) {
                    OaPorcessinsEntity tappPorcessins= this.getById(processinsid);
                    if(tappPorcessins==null){
                        return R.newError("非法进入或者没有权限查看！");
                    }
                    flowstatus=tappPorcessins.getPorcessinsStatus();
                    flowstatusName= tappPorcessins.getCurrentactname();
                    resultJson.setFromData( oaFromdataService.getById(tappPorcessins.getOaFromdataId()).getFromdata());
                }else{
                    return R.newError("非法进入或者没有权限查看！");
                }
            }
        }
        if(StringUtils.isNotBlank(currentChartId)) {
            resultJson.setCurrentChartId( currentChartId);
        }else{
            if(!"4".equals(flowstatus)) {
                resultJson.setCurrentChartId(actChartId);
            }else{
                resultJson.setCurrentChartId( "F_End");
            }
        }
        resultJson.setFlowstatus( flowstatus);
        resultJson.setFlowstatusName( flowstatusName==null?"":flowstatusName);
        return R.newOk(resultJson);
    }

    NodeInfoReq getNodeInfo(List<NodeInfoReq> NodeList, String actChartId){
        NodeInfoReq nodeInfoReq= NodeList.stream().filter(ee->actChartId.equals(ee.getId())).findFirst().get();
        return nodeInfoReq;
    }
    public R<List<OaPorcessinsEntity>> queryporcessinsList(QueryPorcessInsListReq param){

        try {
            QueryPorcessinsVO porcessins = new QueryPorcessinsVO();
            porcessins.setActName(param.getActName());
            if(param.getApprovingUser()!=null) {
                porcessins.setApprovingUser(param.getApprovingUser().getNo());
            }
            if( param.getApplicantuser()!=null) {
                porcessins.setApplicantuser(param.getApplicantuser().getNo());
            }

            porcessins.setProcinsname(param.getProcinsname());
            porcessins.setProcinsno(param.getProcinsno());
            if(param.getStarttime()!=null&&param.getStarttime().size()>0) {
                porcessins.setStarttimeStart( param.getStarttime().get(0));
                porcessins.setStarttimeEnd( param.getStarttime().get(1));
            }
            if(param.getStartUser()!=null) {
                porcessins.setStartUser(param.getStartUser().getNo());
            }
            porcessins.setProcInsState(param.getProcInsState());

            int page =param.getPage();
            int rows = param.getRows();

            String viewstate=param.getViewState()==null||"".equals(param.getViewState()) ?"1":param.getViewState();
            CurrentUserInfoVO currentUserInfo = apiCommService.getCurrentUserInfo();
            porcessins.setUsername(currentUserInfo.getUserCode());
            List<OaPorcessinsEntity> list=null;
            PageHelper.startPage(page, rows);
            switch (viewstate){
                case "1": list=baseMapper.MyAllPorcessinsList(porcessins);break;
                case "2":list=baseMapper.MyDraftPorcessinsList(porcessins);break;
                case "3":list=baseMapper.MyStartPorcessinsList(porcessins);

                    break;
                case "4":list=baseMapper.MyToDoPorcessinsList(porcessins);break;
                case "5":list=baseMapper.MyReadporcessinsList(porcessins);break;
                default:list=baseMapper.MyAllPorcessinsList(porcessins);break;
            }
            for (OaPorcessinsEntity item:list) {
                if("2".equals(item.getPorcessinsStatus())|| "7".equals(item.getPorcessinsStatus())|| "8".equals(item.getPorcessinsStatus())|| "9".equals(item.getPorcessinsStatus())) {
                    List<OaActinsEntity> tappActins=   oaActinsService.queryTodoActins(item.getOaPorcessinsId());
                    if(tappActins!=null&&tappActins.size()>0){
                        item.setActinsid(tappActins.get(0).getOaActId());
                    }
                }
                OaProcessEntity tappProcess=  oaProcessService.getById(item.getOaProcessId());
                if(tappProcess!=null){
                    item.setProcname(tappProcess.getProcessname());
                }
            }
            PageInfo pageInfo = new PageInfo(list);
            return R.newOk(list,(int)pageInfo.getTotal());
        }catch (Exception ex){
            return R.newError(ex.getMessage());

        }

    }

    /**
     * 获取当前用户流程待办数 和第一个流程的名称
      * @return
     */
    public HomeDataVO getHomeData(){
        HomeDataVO homeDataVO=new HomeDataVO();
        QueryPorcessinsVO porcessins = new QueryPorcessinsVO();
        CurrentUserInfoVO currentUserInfo = apiCommService.getCurrentUserInfo();
        porcessins.setUsername(currentUserInfo.getUserCode());
        homeDataVO.setToNum(baseMapper.MyToDoPorcessinsCount(porcessins));
        PageHelper.startPage(1, 1);
        List<OaPorcessinsEntity>  todolist=baseMapper.MyToDoPorcessinsList(porcessins);
         if(todolist!=null&&todolist.size()>0){
             homeDataVO.setTitle(todolist.get(0).getProcinsname());
             homeDataVO.setPublishTime(todolist.get(0).getUpdateDate());
         }
        return homeDataVO;
    }
}

