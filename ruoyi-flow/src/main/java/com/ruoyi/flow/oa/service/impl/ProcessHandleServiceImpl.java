package com.ruoyi.flow.oa.service.impl;

import com.alibaba.fastjson2.JSON;
import com.ruoyi.flow.comm.SpringBeanUtils;
import com.ruoyi.flow.form.entity.FormFormEntity;
import com.ruoyi.flow.form.entity.SysDeptEntity;
import com.ruoyi.flow.form.entity.SysUserEntity;
import com.ruoyi.flow.form.service.impl.*;

import com.ruoyi.flow.oa.entity.*;
import com.ruoyi.flow.oa.req.ProcessInfoReq;
import com.ruoyi.flow.oa.vo.*;
import com.ruoyi.flow.vo.CurrentUserInfoVO;
import com.ruoyi.flow.vo.R;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

/**
 * (TappActdatapermission)流程处理类
 *
 * @author makejava
 * @since 2019-12-25 10:32:28
 */
@Service
@Scope("prototype")
public class ProcessHandleServiceImpl {
    @Resource
    private OaProcessServiceImpl tappProcessDao;

    @Resource
    private OaProcesschartServiceImpl oaProcesschartService;
    @Resource
    private OaActServiceImpl tappActDao;
    @Resource
    private OaActroutingServiceImpl tappActroutingDao;
    @Resource
    private OaPorcessinsServiceImpl tappPorcessinsDao;
    @Resource
    private OaActinsServiceImpl tappActinsDao;
    @Resource
    private SysOaUserServiceImpl tappUserDao;
    @Resource
    private SysDeptFlowServiceImpl sysDeptService;
    @Resource
    private OaFromdataServiceImpl tappFromdataDao;
    @Resource
    private FormFormServiceImpl tappFromService;
    @Resource
    private OaActinscommunicateServiceImpl tappActinscommunicateDao;
    @Resource
    private OaProcauditServiceImpl tappProcauditDao;
    @Resource
    private SysRoleFlowServiceImpl tappRoleDao;
    @Resource
    private OaPorcessinsLogServiceImpl tappPorcessinsLogService;
    @Resource
    private ApiCommService apiCommService;

    @Resource
    private OaTempNoticeServiceImpl oaTempNoticeService;
    @Resource
    private OaSendNoticeServiceImpl oaSendNoticeService;
    @Autowired
    private SpringBeanUtils springBeanUtils;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    ProcInstHandleEntity Instance;


    public R<String> HandleProc(HandleProcessVO param) {
        try {
            Instance = new ProcInstHandleEntity();
            ProcInstHandleEntityParser(param);
            if ("发起".equals(Instance.getActionname())) {
                if (Instance.getProcid() == null) {
                    return R.newError("发起失败,找不到流程模板ID");
                }
                if (Instance.getCureentuser() == null) {
                    return R.newError("发起失败,当前用户不能为空");
                }

                if (Instance.getCurrentactentity() == null) {
                    return R.newError("发起失败,未找到当前流程节点信息!");
                }
                Instance.getRunlogList().add("发起流程开始！");
                AddProcAudit(Instance);
                RunProcess();
            } else if ("审批".equals(Instance.getActionname())) {
                if (Instance.getProcid() == null) {
                    return R.newError("审批失败,找不到流程模板ID");
                }
                if (Instance.getActinsid() == null) {
                    return R.newError("审批失败,找不到节点实例ID");
                }
                if (Instance.getProcinsid() == null) {
                    return R.newError("审批失败,找不到流程实例ID");
                }
                Instance.setTappPorcessins(tappPorcessinsDao.getById(Instance.getProcinsid()));
                if (Instance.getTappPorcessins() == null || "7".equals(Instance.getTappPorcessins().getPorcessinsStatus()) || "4".equals(Instance.getTappPorcessins().getPorcessinsStatus())) {
                    return R.newError("审批失败,找不到流程实例信息或者正在处理中！");
                }
                if ("8".equals(Instance.getTappPorcessins().getPorcessinsStatus()) || "9".equals(Instance.getTappPorcessins().getPorcessinsStatus())) {
                    return R.newError("审批失败,回写业务数据失败或者回调失败，当前流程已被锁定");
                }
                if (Instance.getCureentuser() == null) {
                    return R.newError("审批失败,当前用户不能为空");
                }
                OaActinsEntity tappActins = tappActinsDao.getById(Instance.getActinsid());
                if (tappActins == null || "7".equals(tappActins.getActinsStatus())) {
                    return R.newError("审批失败,找不到节点信息或者正在处理中！");
                }
                if ("0".equals(tappActins.getActinsStatus())) {
                    return R.newError("审批失败,可能已处理！");
                }
                if (!tappActins.getApproversuser().equals(Instance.getCureentuser().getUserCode())) {
                    return R.newError("当前操作用户没有权限！");
                }
                Instance.getRunlogList().add("审批流程开始！");
                Instance.getTappPorcessins().setPorcessinsStatus("7");
                tappPorcessinsDao.saveOrUpdate(Instance.getTappPorcessins());
                AddProcAudit(Instance);
                RunProcess();
            } else if ("驳回".equals(Instance.getActionname())) {
                if (Instance.getProcid() == null) {
                    return R.newError("驳回失败,找不到流程模板ID");
                }
                if (Instance.getActinsid() == null) {
                    return R.newError("驳回失败,找不到节点实例ID");
                }
                if (Instance.getProcinsid() == null) {
                    return R.newError("驳回失败,找不到流程实例ID");
                }
                Instance.setTappPorcessins(tappPorcessinsDao.getById(Instance.getProcinsid()));
                if (Instance.getTappPorcessins() == null || "7".equals(Instance.getTappPorcessins().getPorcessinsStatus()) || "4".equals(Instance.getTappPorcessins().getPorcessinsStatus())) {
                    return R.newError("驳回失败,找不到流程实例信息或者正在处理中！");
                }
                if ("8".equals(Instance.getTappPorcessins().getPorcessinsStatus()) || "9".equals(Instance.getTappPorcessins().getPorcessinsStatus())) {
                    return R.newError("驳回失败,回写业务数据失败或者回调失败，当前流程已被锁定");
                }
                if (Instance.getCureentuser() == null) {
                    return R.newError("驳回失败,当前用户不能为空");
                }
                OaActinsEntity tappActins = tappActinsDao.getById(Instance.getActinsid());
                if (tappActins == null || "7".equals(Instance.getTappPorcessins().getPorcessinsStatus())) {
                    return R.newError("驳回失败,找不到节点信息或者正在处理中！");
                }
                if ("0".equals(tappActins.getActinsStatus())) {
                    return R.newError("驳回失败,可能已处理！");
                }
                if (!tappActins.getApproversuser().equals(Instance.getCureentuser().getUserCode())) {
                    return R.newError("当前操作用户没有权限！");
                }
                Instance.getRunlogList().add("驳回流程开始！");
                Instance.getTappPorcessins().setPorcessinsStatus("7");
                tappPorcessinsDao.saveOrUpdate(Instance.getTappPorcessins());
                AddProcAudit(Instance);
                RunProcess();
            } else if ("暂存".equals(Instance.getActionname())) {
                if (Instance.getProcid() == null) {
                    return R.newError("暂存失败,找不到流程模板ID");
                }
                if (Instance.getFromid() == null) {
                    return R.newError("暂存失败,找不到表单模板ID");
                }
                Instance.getRunlogList().add("暂存流程开始！");
                ProcessHelperServiceImpl processHelper = springBeanUtils.getBean(ProcessHelperServiceImpl.class);
                processHelper.setInstance(Instance);
//                ProcessHelperServiceImpl processHelper = new ProcessHelperServiceImpl(Instance, tappPorcessinsDao, tappFromdataDao, tappActinsDao, tappProcauditDao, tappActinscommunicateDao, jdbcTemplate, tappUserDao,dbOperationHelperService);
                processHelper.TempSave();
            } else if ("转办".equals(Instance.getActionname())) {
                if (Instance.getProcinsid() == null) {
                    return R.newError("转办失败,找不到流程实例ID");
                }
                Instance.setTappPorcessins(tappPorcessinsDao.getById(Instance.getProcinsid()));
                if (Instance.getTappPorcessins() == null || "7".equals(Instance.getTappPorcessins().getPorcessinsStatus()) || "4".equals(Instance.getTappPorcessins().getPorcessinsStatus())) {
                    return R.newError("转办失败,找不到流程实例信息或者正在处理中！");
                }
                if ("8".equals(Instance.getTappPorcessins().getPorcessinsStatus()) || "9".equals(Instance.getTappPorcessins().getPorcessinsStatus())) {
                    return R.newError("转办失败,回写业务数据失败或者回调失败，当前流程已被锁定");
                }
                if (Instance.getActinsid() == null) {
                    return R.newError("转办失败,找不到节点实例ID");
                }
                OaActinsEntity tappActins = tappActinsDao.getById(Instance.getActinsid());
                if (tappActins == null || "7".equals(tappActins.getActinsStatus())) {
                    return R.newError("转办失败,找不到节点信息或者正在处理中！");
                }
                if ("0".equals(tappActins.getActinsStatus())) {
                    return R.newError("转办失败,可能已处理！");
                }
                if (Instance.getCureentuser() == null) {
                    return R.newError("转办失败,当前操作用户不能为空");
                }
                if (!tappActins.getApproversuser().equals(Instance.getCureentuser().getUserCode())) {
                    return R.newError("当前操作用户没有权限！");
                }
                Instance.getRunlogList().add("转办流程开始！");
                tappActins.setActinsStatus("7");
                tappActinsDao.saveOrUpdate(tappActins);

                AddProcAudit(Instance);
                if (Instance.getRedrituser() == null || Instance.getRedrituser().equals("")) {
                    return R.newError("转办失败,转办人不能为空");
                }
                ProcessHelperServiceImpl processHelper = springBeanUtils.getBean(ProcessHelperServiceImpl.class);
                processHelper.setInstance(Instance);
//                ProcessHelperServiceImpl processHelper = new ProcessHelperServiceImpl(Instance, tappPorcessinsDao, tappFromdataDao, tappActinsDao, tappProcauditDao, tappActinscommunicateDao, jdbcTemplate, tappUserDao,dbOperationHelperService);
                processHelper.TransferProcInst(tappActins);
                sendMsgNotice();
            } else if ("撤销".equals(Instance.getActionname()) || "审批人撤销".equals(Instance.getActionname())) {
                ///将流程实例的状态为6 并创建一个草稿单
                if (Instance.getProcinsid() == null) {
                    return R.newError("撤销失败,找不到流程实例ID");
                }
                if (Instance.getCureentuser() == null) {
                    return R.newError("撤销失败,当前用户不能为空");
                }
                Instance.setTappPorcessins(tappPorcessinsDao.getById(Instance.getProcinsid()));
                if (Instance.getTappPorcessins() == null || "7".equals(Instance.getTappPorcessins().getPorcessinsStatus()) || "4".equals(Instance.getTappPorcessins().getPorcessinsStatus())) {
                    return R.newError("撤销失败,找不到流程实例信息或者正在处理中！");
                }
                if ("8".equals(Instance.getTappPorcessins().getPorcessinsStatus()) || "9".equals(Instance.getTappPorcessins().getPorcessinsStatus())) {
                    return R.newError("撤销失败,回写业务数据失败或者回调失败，当前流程已被锁定");
                }
                Instance.getRunlogList().add("撤销流程开始！");
                Instance.getTappPorcessins().setPorcessinsStatus("7");
                tappPorcessinsDao.saveOrUpdate(Instance.getTappPorcessins());

                AddProcAudit(Instance);
                ProcessHelperServiceImpl processHelper = springBeanUtils.getBean(ProcessHelperServiceImpl.class);
                processHelper.setInstance(Instance);
//                ProcessHelperServiceImpl processHelper = new ProcessHelperServiceImpl(Instance, tappPorcessinsDao, tappFromdataDao, tappActinsDao, tappProcauditDao, tappActinscommunicateDao, jdbcTemplate, tappUserDao,dbOperationHelperService);
                processHelper.OperationProcInst(6);
                sendMsgNotice();
            } else if ("废弃".equals(Instance.getActionname()) || "审批人废弃".equals(Instance.getActionname())) {
                if (Instance.getProcinsid() == null) {
                    return R.newError("废弃失败,找不到流程实例ID");
                }
                Instance.setTappPorcessins(tappPorcessinsDao.getById(Instance.getProcinsid()));
                if (Instance.getTappPorcessins() == null || "7".equals(Instance.getTappPorcessins().getPorcessinsStatus()) || "4".equals(Instance.getTappPorcessins().getPorcessinsStatus())) {
                    return R.newError("废弃失败,找不到流程实例信息或者正在处理中！");
                }
                if ("8".equals(Instance.getTappPorcessins().getPorcessinsStatus()) || "9".equals(Instance.getTappPorcessins().getPorcessinsStatus())) {
                    return R.newError("废弃失败,回写业务数据失败或者回调失败，当前流程已被锁定");
                }
                if (Instance.getCureentuser() == null) {
                    return R.newError("废弃失败,当前用户不能为空");
                }
                Instance.getRunlogList().add("废弃流程开始！");
                Instance.getTappPorcessins().setPorcessinsStatus("7");
                tappPorcessinsDao.saveOrUpdate(Instance.getTappPorcessins());
                AddProcAudit(Instance);
                ProcessHelperServiceImpl processHelper = springBeanUtils.getBean(ProcessHelperServiceImpl.class);
                processHelper.setInstance(Instance);
//                ProcessHelperServiceImpl processHelper = new ProcessHelperServiceImpl(Instance, tappPorcessinsDao, tappFromdataDao, tappActinsDao, tappProcauditDao, tappActinscommunicateDao, jdbcTemplate, tappUserDao,dbOperationHelperService);
                processHelper.OperationProcInst(5);
                sendMsgNotice();
            } else if ("催办".equals(Instance.getActionname())) {
                Instance.getRunlogList().add("催办流程开始！");
                sendMsgNotice();
            } else if ("跳转".equals(Instance.getActionname())) {
//            if (procInstanceEntity.NextActID == null)
//                throw new Exception("跳转失败,找不到下一节点ID");
                if (Instance.getProcinsid() == null) {
                    return R.newError("跳转失败,找不到流程实例ID");
                }
                Instance.setTappPorcessins(tappPorcessinsDao.getById(Instance.getProcinsid()));
                if (Instance.getTappPorcessins() == null || "7".equals(Instance.getTappPorcessins().getPorcessinsStatus()) || "4".equals(Instance.getTappPorcessins().getPorcessinsStatus())) {
                    return R.newError("跳转失败,找不到流程实例信息或者正在处理中！");
                }
                if ("8".equals(Instance.getTappPorcessins().getPorcessinsStatus()) || "9".equals(Instance.getTappPorcessins().getPorcessinsStatus())) {
                    return R.newError("跳转失败,回写业务数据失败或者回调失败，当前流程已被锁定");
                }
                if (Instance.getCureentuser() == null) {
                    return R.newError("跳转失败,当前用户不能为空");
                }
                if (Instance.getActinsid() == null) {
                    return R.newError("跳转失败,找不到节点实例ID");
                }
                OaActinsEntity tappActins = tappActinsDao.getById(Instance.getActinsid());
                if (tappActins == null  || "7".equals(tappActins.getActinsStatus())) {
                    return R.newError("跳转失败,找不到节点信息或者正在处理中！");
                }
                if (!tappActins.getApproversuser().equals(Instance.getCureentuser().getUserCode())) {
                    return R.newError("当前操作用户没有权限！");
                }
                Instance.getRunlogList().add("跳转流程开始！");
                tappActins.setActinsStatus("7");
                tappActinsDao.saveOrUpdate(tappActins);
                sendMsgNotice();
                AddProcAudit(Instance);
            } else if ("终止".equals(Instance.getActionname())) {
                ///将流程实例的状态为4
                if (Instance.getProcinsid() == null) {
                    return R.newError("终止失败,找不到流程实例ID");
                }
                Instance.setTappPorcessins(tappPorcessinsDao.getById(Instance.getProcinsid()));
                if (Instance.getTappPorcessins() == null || "7".equals(Instance.getTappPorcessins().getPorcessinsStatus()) || "4".equals(Instance.getTappPorcessins().getPorcessinsStatus())) {
                    return R.newError("终止失败,找不到流程实例信息或者正在处理中！");
                }
                if ("8".equals(Instance.getTappPorcessins().getPorcessinsStatus()) || "9".equals(Instance.getTappPorcessins().getPorcessinsStatus())) {
                    return R.newError("终止失败,回写业务数据失败或者回调失败，当前流程已被锁定");
                }
                if (Instance.getCureentuser() == null) {
                    return R.newError("终止失败,当前用户不能为空");
                }
                Instance.getRunlogList().add("终止流程开始！");
                Instance.getTappPorcessins().setPorcessinsStatus("7");
                tappPorcessinsDao.saveOrUpdate(Instance.getTappPorcessins());
                AddProcAudit(Instance);
                ProcessHelperServiceImpl processHelper = springBeanUtils.getBean(ProcessHelperServiceImpl.class);
                processHelper.setInstance(Instance);
//                ProcessHelperServiceImpl processHelper = new ProcessHelperServiceImpl(Instance, tappPorcessinsDao, tappFromdataDao, tappActinsDao, tappProcauditDao, tappActinscommunicateDao, jdbcTemplate, tappUserDao,dbOperationHelperService);
                processHelper.OperationProcInst(4);
            } else if ("发起沟通".equals(Instance.getActionname())) {
                if (Instance.getProcinsid() == null) {
                    return R.newError("发起沟通失败,找不到流程实例ID");
                }
                Instance.setTappPorcessins(tappPorcessinsDao.getById(Instance.getProcinsid()));
                if (Instance.getTappPorcessins() == null || "7".equals(Instance.getTappPorcessins().getPorcessinsStatus()) || "4".equals(Instance.getTappPorcessins().getPorcessinsStatus())) {
                    return R.newError("发起沟通失败,找不到流程实例信息或者正在处理中！");
                }
                if ("8".equals(Instance.getTappPorcessins().getPorcessinsStatus()) || "9".equals(Instance.getTappPorcessins().getPorcessinsStatus())) {
                    return R.newError("发起沟通失败,回写业务数据失败或者回调失败，当前流程已被锁定");
                }
                if (Instance.getCureentuser() == null) {
                    return R.newError("发起沟通失败,当前用户不能为空");
                }
                if (Instance.copylist == null || Instance.copylist.isEmpty()) {
                    return R.newError("发起沟通失败,沟通人不能为空");
                }
                if (Instance.getActinsid() == null) {
                    return R.newError("发起沟通失败，找不到节点实例ID");
                }
                OaActinsEntity tappActins = tappActinsDao.getById(Instance.getActinsid());
                if (tappActins == null || "7".equals(tappActins.getActinsStatus())) {
                    return R.newError("发起沟通失败,找不到节点信息或者正在处理中！");
                }
                if ("0".equals(tappActins.getActinsStatus())) {
                    return R.newError("发起沟通失败,可能已处理！");
                }
                if (!tappActins.getApproversuser().equals(Instance.getCureentuser().getUserCode())) {
                    return R.newError("当前操作用户没有权限！");
                }
                Instance.getRunlogList().add("发起沟通流程开始！");
                tappActins.setActinsStatus("7");
                tappActinsDao.saveOrUpdate(tappActins);
                String ccNames = "";
                for (CopyUserEntity cc : Instance.copylist) {
                    ccNames = (ccNames.equals("") ? cc.getName() : ccNames + "、" + cc.getName());
                }
                ///创建待办待阅信息 抄送邮件，待办待阅邮件，流程结束邮件
                String communicationTitle = "【" + Instance.getCureentuser().getUserName() + "发起沟通:" + ccNames + "】" + "备注：" + Instance.getApprovedDes();
                AddProcAudit(Instance, communicationTitle);
                ProcessHelperServiceImpl processHelper = springBeanUtils.getBean(ProcessHelperServiceImpl.class);
                processHelper.setInstance(Instance);
//               ProcessHelperServiceImpl processHelper = new ProcessHelperServiceImpl(Instance, tappPorcessinsDao, tappFromdataDao, tappActinsDao, tappProcauditDao, tappActinscommunicateDao, jdbcTemplate, tappUserDao,dbOperationHelperService);
                processHelper.StartCommunication();
                sendMsgNotice();
            } else if ("回复沟通".equals(Instance.getActionname())) {
                if (Instance.getProcinsid() == null) {
                    return R.newError("回复沟通失败,找不到流程实例ID");
                }
                Instance.setTappPorcessins(tappPorcessinsDao.getById(Instance.getProcinsid()));
                if (Instance.getTappPorcessins() == null || "7".equals(Instance.getTappPorcessins().getPorcessinsStatus()) || "4".equals(Instance.getTappPorcessins().getPorcessinsStatus())) {
                    return R.newError("回复沟通失败,找不到流程实例信息或者正在处理中！");
                }
                if ("8".equals(Instance.getTappPorcessins().getPorcessinsStatus()) || "9".equals(Instance.getTappPorcessins().getPorcessinsStatus())) {
                    return R.newError("回复沟通失败,回写业务数据失败或者回调失败，当前流程已被锁定");
                }
                if (Instance.getCureentuser() == null) {
                    return R.newError("回复沟通失败,当前用户不能为空");
                }
                if (Instance.getActinsid() == null) {
                    return R.newError("回复沟通失败，找不到节点实例ID");
                }
                OaActinsEntity tappActins = tappActinsDao.getById(Instance.getActinsid());
                if (tappActins == null) {
                    return R.newError("回复沟通失败,找不到节点信息！");
                }
                Instance.getRunlogList().add("回复沟通流程开始！");
                AddProcAudit(Instance);
                ProcessHelperServiceImpl processHelper = springBeanUtils.getBean(ProcessHelperServiceImpl.class);
                processHelper.setInstance(Instance);
//                ProcessHelperServiceImpl processHelper = new ProcessHelperServiceImpl(Instance, tappPorcessinsDao, tappFromdataDao, tappActinsDao, tappProcauditDao, tappActinscommunicateDao, jdbcTemplate, tappUserDao,dbOperationHelperService);
                processHelper.AnswerCommunication();
                sendMsgNotice();
            } else if ("取消沟通".equals(Instance.getActionname())) {
                if (Instance.getProcinsid() == null) {
                    return R.newError("取消沟通失败,找不到流程实例ID");
                }
                Instance.setTappPorcessins(tappPorcessinsDao.getById(Instance.getProcinsid()));
                if (Instance.getTappPorcessins() == null || "7".equals(Instance.getTappPorcessins().getPorcessinsStatus()) || "4".equals(Instance.getTappPorcessins().getPorcessinsStatus())) {
                    return R.newError("取消沟通失败,找不到流程实例信息或者正在处理中！");
                }
                if ("8".equals(Instance.getTappPorcessins().getPorcessinsStatus()) || "9".equals(Instance.getTappPorcessins().getPorcessinsStatus())) {
                    return R.newError("取消沟通失败,回写业务数据失败或者回调失败，当前流程已被锁定");
                }
                if (Instance.getCureentuser() == null) {
                    return R.newError("取消沟通失败,当前用户不能为空");
                }
                if (Instance.getActinsid() == null) {
                    return R.newError("取消沟通失败，找不到节点实例ID");
                }
                OaActinsEntity tappActins = tappActinsDao.getById(Instance.getActinsid());
                if (tappActins == null || "7".equals(tappActins.getActinsStatus())) {
                    return R.newError("取消沟通失败,找不到节点信息或者正在处理中！");
                }
                if (!tappActins.getApproversuser().equals(Instance.getCureentuser().getUserCode())) {
                    return R.newError("当前操作用户没有权限！");
                }
                Instance.getRunlogList().add("取消沟通流程开始！");
                tappActins.setActinsStatus("7");
                tappActinsDao.saveOrUpdate(tappActins);
                AddProcAudit(Instance);
                ProcessHelperServiceImpl processHelper = springBeanUtils.getBean(ProcessHelperServiceImpl.class);
                processHelper.setInstance(Instance);
//                 ProcessHelperServiceImpl processHelper = new ProcessHelperServiceImpl(Instance, tappPorcessinsDao, tappFromdataDao, tappActinsDao, tappProcauditDao, tappActinscommunicateDao, jdbcTemplate, tappUserDao,dbOperationHelperService);
                processHelper.CancerCommunication();
                sendMsgNotice();
            } else if ("抄送".equals(Instance.getActionname())) {
                if (Instance.getProcinsid() == null) {
                    return R.newError("抄送失败,找不到流程实例ID");
                }
                Instance.setTappPorcessins(tappPorcessinsDao.getById(Instance.getProcinsid()));
                if (Instance.getTappPorcessins() == null || "4".equals(Instance.getTappPorcessins().getPorcessinsStatus())) {
                    return R.newError("抄送失败,找不到流程实例信息或者流程已结束！");
                }
                if (Instance.getActinsid() == null) {
                    return R.newError("抄送失败找不到节点实例ID");
                }
                if (Instance.copylist == null || Instance.copylist.isEmpty()) {
                    return R.newError("抄送失败,抄送人不能为空");
                }
                if (Instance.getCureentuser() == null) {
                    return R.newError("抄送失败,当前用户不能为空");
                }
                Instance.getRunlogList().add("抄送沟通流程开始！");
                OaActinsEntity tappActins = tappActinsDao.getById(Instance.getActinsid());
                ProcessHelperServiceImpl processHelper = springBeanUtils.getBean(ProcessHelperServiceImpl.class);
                processHelper.setInstance(Instance);
//                 ProcessHelperServiceImpl processHelper = new ProcessHelperServiceImpl(Instance, tappPorcessinsDao, tappFromdataDao, tappActinsDao, tappProcauditDao, tappActinscommunicateDao, jdbcTemplate, tappUserDao,dbOperationHelperService);
                processHelper.CCProcInst(tappActins);
                sendMsgNotice();
            }
        } catch (Exception ex) {
            Instance.addErrorlog("处理流程发生错误！具体错误信息为：" + ex.getMessage() + "堆栈信息：" + ex.toString());
//            return R.newError( ex.getMessage());
        }
        if (Instance.getErrorlog().size() > 0) {
            String errorstr = String.join("\r\n", Instance.getRunlogList());
            errorstr = errorstr + "========================================================\r\n" + String.join("\r\n", Instance.getErrorlog());
            SavePorcessinsLog(param, errorstr);
            return R.newError("流程处理发生错误！请于管理员联系。");
        }
        return R.newOk(Instance.getProcinsid());
    }

    public R<String> HandleErrorProcess(HandleErrorProcessVO param) {
        OaPorcessinsLogEntity tappPorcessinsLogEntity = tappPorcessinsLogService.getById(param.getPorcessinsLogId());
        if (tappPorcessinsLogEntity == null) {
            return R.newError("未找到对应日志信息！");
        }
        if ("1".equals(tappPorcessinsLogEntity.getPorcessinsLogStatus())) {
            return R.newError("该流程异常日志已经处理！");
        }
        String porcessinsLogStatus=tappPorcessinsLogEntity.getPorcessinsLogStatus();
        tappPorcessinsLogEntity.setPorcessinsLogStatus("1");
        tappPorcessinsLogService.saveOrUpdate(tappPorcessinsLogEntity);
        HandleProcessVO handleProcessVO = JSON.parseObject(tappPorcessinsLogEntity.getHandleProcessParam(), HandleProcessVO.class);
        JSONObject fromdata = JSONObject.fromObject(param.getFromdata());
        handleProcessVO.setFromdata(fromdata);
        if (StringUtils.isNotBlank(param.getActionType())) {
            handleProcessVO.setActionType(param.getActionType());
            handleProcessVO.setActionName(param.getActionName());
            handleProcessVO.setOperationInfo(JSON.parseObject(param.getOperationInfo(), OperationInfoVO.class));
        }
        if ("8".equals(porcessinsLogStatus) || "9".equals(porcessinsLogStatus)) {
            try {
                Instance = new ProcInstHandleEntity();
                Instance.setProcessInsState(porcessinsLogStatus);
                ProcInstHandleEntityParser(handleProcessVO);
                if (StringUtils.isNotBlank(handleProcessVO.getNextactid())) {
                    Instance.setNexact(tappActDao.getById(handleProcessVO.getNextactid()));
                }
                if(handleProcessVO.getFlowEnd()!=null&&handleProcessVO.getFlowEnd()){
                    Instance.setFlowEnd(true);
                }
                Instance.setHandled(handleProcessVO.getHandled());
                Instance.setHandleType(handleProcessVO.getHandleType());
                Instance.setApiUrl(handleProcessVO.getApiUrl());
                Instance.setSqlcontent(handleProcessVO.getSqlcontent());
                ProcessHelperServiceImpl processHelper = springBeanUtils.getBean(ProcessHelperServiceImpl.class);
                processHelper.setInstance(Instance);
//                 ProcessHelperServiceImpl processHelper = new ProcessHelperServiceImpl(Instance, tappPorcessinsDao, tappFromdataDao, tappActinsDao, tappProcauditDao, tappActinscommunicateDao, jdbcTemplate, tappUserDao,dbOperationHelperService);
               if("8".equals(porcessinsLogStatus)) {
                   AfterProcessing(processHelper);
               }else{
                   processHelper.HandleCallback();
               }
            } catch (Exception ex) {
                Instance.addErrorlog("处理流程发生错误！具体错误信息为：" + ex.getMessage() + "堆栈信息：" + ex.toString());
            }
            if (Instance.getErrorlog().size() > 0) {

                String errorstr = String.join("\r\n", Instance.getRunlogList());
                errorstr = errorstr + "========================================================\r\n" + String.join("\r\n", Instance.getErrorlog());

                SavePorcessinsLog(handleProcessVO, errorstr);
                return R.newError("流程处理发生错误！请于管理员联系。");
            } else {
                OaPorcessinsEntity tappPorcessinsEntity = tappPorcessinsDao.getById(handleProcessVO.getProcInsID());
                if (Instance.getFlowEnd()) {
                    tappPorcessinsEntity.setPorcessinsStatus("2");
                } else {
                    tappPorcessinsEntity.setPorcessinsStatus("4");
                }
                tappPorcessinsDao.saveOrUpdate(tappPorcessinsEntity);
            }
            return R.newOk(Instance.getProcinsid());
        } else {
            unlockProcess(handleProcessVO);
            return HandleProc(handleProcessVO);
        }
    }

    ///从错误运维流程实例中进来 所以需要解锁流程实例 和节点实例 否则流程无法处理
    void unlockProcess(HandleProcessVO handleProcessVO) {
        OaPorcessinsEntity tappPorcessinsEntity = tappPorcessinsDao.getById(handleProcessVO.getProcInsID());
        if (tappPorcessinsEntity != null && "7".equals(tappPorcessinsEntity.getPorcessinsStatus())) {
            tappPorcessinsEntity.setPorcessinsStatus("2");
            tappPorcessinsDao.saveOrUpdate(tappPorcessinsEntity);
        }
        OaActinsEntity tappActins = tappActinsDao.getById(handleProcessVO.getActInsID());
        if (tappActins != null && "7".equals(tappActins.getActinsStatus())) {
            tappActins.setActinsStatus("1");
            tappActinsDao.getBaseMapper().updateById(tappActins);
        }

    }

    ///保存流程日志
    void SavePorcessinsLog(HandleProcessVO param, String errorstr) {
        OaPorcessinsLogEntity tappPorcessinsLogEntity = new OaPorcessinsLogEntity();
        tappPorcessinsLogEntity.setActins(Instance.getActinsid());
        tappPorcessinsLogEntity.setOaProcessId(Instance.getProcid());
        tappPorcessinsLogEntity.setProcinsname(Instance.getProcinsname());
        tappPorcessinsLogEntity.setProcinsno(Instance.getProcinsno());
        tappPorcessinsLogEntity.setOaFromdataId(Instance.getFforminsid());
        tappPorcessinsLogEntity.setFromdata(JSON.toJSONString(param.getFromdata()));
        tappPorcessinsLogEntity.setActionname(Instance.getActionname());
        if(StringUtils.isNotBlank(Instance.getProcessInsState())){
            tappPorcessinsLogEntity.setPorcessinsLogStatus(Instance.getProcessInsState());
        }else {
            tappPorcessinsLogEntity.setPorcessinsLogStatus("0");
        }
        tappPorcessinsLogEntity.setOaPorcessinsId(Instance.getProcinsid());
        tappPorcessinsLogEntity.setOaActId(param.getActID());
        if (Instance.getNexact() != null) {
            param.setNextactid(Instance.getNexact().getOaActId());
            param.setNextactname(Instance.getNexact().getActname());
            param.setNextactchartid(Instance.getNexact().getActchartId());
        }
        if (Instance.getFlowEnd()) {
            param.setFlowEnd(Instance.getFlowEnd());
            param.setNextactid("");
            param.setNextactname("流程结束");
            param.setNextactchartid("F_End");
        }
        param.setHandleType(Instance.getHandleType());
        param.setHandled(Instance.getHandled());
        param.setApiUrl(Instance.getApiUrl());
        param.setSqlcontent(Instance.getSqlcontent());
        tappPorcessinsLogEntity.setHandleProcessParam(JSON.toJSONString(param));
        if (Instance.getCurrentactentity() != null) {
            tappPorcessinsLogEntity.setActinsname(Instance.getCurrentactentity().getActname());
        }
        tappPorcessinsLogEntity.setLogmsg(errorstr);
        tappPorcessinsLogService.saveOrUpdate(tappPorcessinsLogEntity);
    }

    //组装处理的实体
    void ProcInstHandleEntityParser(HandleProcessVO param) throws Exception {
        Instance.getRunlogList().add("开始组装处理的流程实体，为流程运行做好准备！");
        Instance.setActionname(param.getActionName());
        // Instance.setAction(processJson.getString("ActionType"));
        Instance.setProcid(param.getProcID());
        Instance.setFromid(tappProcessDao.getById(Instance.getProcid()).getFormFormId());
        FormFormEntity tappFromEntity = tappFromService.getById(Instance.getFromid());
        Instance.setBusinessTable(tappFromEntity.getFromTableName());
        Instance.setActid(param.getActID());
        Instance.setProcinsid(param.getProcInsID());
        Instance.setActinsid(param.getActInsID());
        Instance.setFromdata(param.getFromdata());
        if (StringUtils.isEmpty(param.getCurrentUserCode())) {
            Instance.setCureentuser(apiCommService.getCurrentUserInfo());
        } else {
            SysUserEntity user = tappUserDao.getById(param.getCurrentUserCode());
            CurrentUserInfoVO currentUserInfoVO = new CurrentUserInfoVO();
            currentUserInfoVO.setId(user.getUserId().toString());
            currentUserInfoVO.setLastLoginDate(user.getLoginDate());
            currentUserInfoVO.setLastLoginIp(user.getLoginIp());
            currentUserInfoVO.setLoginCode(user.getUserName());
            currentUserInfoVO.setStatus(user.getStatus());
            currentUserInfoVO.setUserCode(user.getUserName());
            currentUserInfoVO.setUserName(user.getNickName());
            SysDeptEntity deptEntity= sysDeptService.getById(user.getDeptId()) ;
            if(deptEntity!=null){
                SysDeptEntity offceinfo=sysDeptService.getById(deptEntity.getParentId());
                if(offceinfo!=null) {
                    currentUserInfoVO.setOfficeCode(offceinfo.getDeptId().toString());
                    currentUserInfoVO.setOfficeName(offceinfo.getDeptName());
                }
                currentUserInfoVO.setDeptName(deptEntity.getDeptId().toString());
                currentUserInfoVO.setDeptCode(deptEntity.getDeptName() );
            }
            Instance.setCureentuser(currentUserInfoVO);
        }
        Instance.setProcinsno(Instance.getFromdata().getString("Header_formNumber"));
        Instance.setProcinsname(Instance.getFromdata().getString("Header_formSubTitle"));
        Instance.setPrevActName(param.getPrevActName());
        Instance.setApplicantuser(Instance.getFromdata().getString("Header_applicantUserName"));
        Instance.setApplicantuserno(Instance.getFromdata().getString("Header_applicantUserID"));
        Instance.setProcname(param.getProcname());
        Instance.setFromdataJson(JSON.toJSONString(Instance.getFromdata()));
        Instance.setActionType(param.getActionType());
        String copyuserJson = "";
        switch (param.getActionType()) {
            case "Start":
                Instance.setActionname("发起");
                Instance.setAction(1);
                Instance.setStartuserid(Instance.getCureentuser().getUserCode());
                Instance.setStartuserName(Instance.getCureentuser().getUserName());
               if(StringUtils.isEmpty(Instance.getActid())){
                 OaActEntity oaActEntity=  tappActDao.queryByProcessIdActchartId(Instance.getProcid(),"F_Start");
                 if(oaActEntity!=null){
                     Instance.setActid(oaActEntity.getOaActId());
                     Instance.setCurrentactentity(oaActEntity);
                 }
               }
                break;
            case "Save":
                Instance.setActionname("暂存");
                break;
            case "Approval":
                Instance.setActionname("审批");
                Instance.setAction(5);
                Instance.setApplicantuserno(Instance.getCureentuser().getUserCode());
                Instance.setFapproversuser(Instance.getCureentuser().getUserName());
                Instance.setApprovedDes(param.getOperationInfo().getApprovedDes());
                break;
            case "Sendback":
                Instance.setActionname("驳回");
                Instance.setAction(3);
                Instance.setApplicantuserno(Instance.getCureentuser().getUserCode());
                Instance.setFapproversuser(Instance.getCureentuser().getUserName());
                Instance.setApprovedDes(param.getOperationInfo().getApprovedDes());
                break;
            case "Tranfer":
                Instance.setActionname("转办");
                Instance.setAction(4);
                Instance.setApplicantuserno(Instance.getCureentuser().getUserCode());
                Instance.setFapproversuser(Instance.getCureentuser().getUserName());
                Instance.setApprovedDes(param.getOperationInfo().getApprovedDes());
                Instance.setRedrituser(param.getOperationInfo().getRedritUser());
                break;
            case "Repeal":
                Instance.setActionname("撤销");
                Instance.setAction(2);
                Instance.setApplicantuserno(Instance.getCureentuser().getUserCode());
                Instance.setFapproversuser(Instance.getCureentuser().getUserName());
                Instance.setApprovedDes(param.getOperationInfo().getApprovedDes());
                break;
            case "Abandon":
                Instance.setActionname("废弃");
                Instance.setAction(7);
                Instance.setApplicantuserno(Instance.getCureentuser().getUserCode());
                Instance.setFapproversuser(Instance.getCureentuser().getUserName());
                Instance.setApprovedDes(param.getOperationInfo().getApprovedDes());
                break;
            case "HastenWork":
                Instance.setActionname("催办");
                Instance.setAction(9);
                Instance.setApplicantuserno(Instance.getCureentuser().getUserCode());
                Instance.setFapproversuser(Instance.getCureentuser().getUserName());
                Instance.setApprovedDes(param.getOperationInfo().getApprovedDes());
                break;
            case "FlowSkip":
                Instance.setActionname("跳转");
                Instance.setAction(11);
                Instance.setApplicantuserno(Instance.getCureentuser().getUserCode());
                Instance.setFapproversuser(Instance.getCureentuser().getUserName());
                Instance.setApprovedDes(param.getOperationInfo().getApprovedDes());

                break;
            case "终止":
                Instance.setActionname("终止");
                Instance.setAction(12);
                Instance.setApplicantuserno(Instance.getCureentuser().getUserCode());
                Instance.setFapproversuser(Instance.getCureentuser().getUserName());
                Instance.setApprovedDes(param.getOperationInfo().getApprovedDes());
                break;
            case "StartQuery":
                Instance.setActionname("发起沟通");
                Instance.setApprovedDes(param.getOperationInfo().getApprovedDes());
                copyuserJson = JSON.toJSONString(param.getOperationInfo().getCopyUserlist());
                Instance.copylist = JSON.parseArray(copyuserJson, CopyUserEntity.class);
                break;
            case "AnswerQuery":
                Instance.setActionname("回复沟通");
                Instance.setApprovedDes(param.getOperationInfo().getApprovedDes());

                break;
            case "CancelQuery":
                Instance.setActionname("取消沟通");
                break;
            case "CopySend":
                Instance.setActionname("抄送");
                Instance.setApprovedDes(param.getOperationInfo().getApprovedDes());
                copyuserJson = JSON.toJSONString(param.getOperationInfo().getCopyUserlist());
                Instance.copylist = JSON.parseArray(copyuserJson, CopyUserEntity.class);
                break;
            case "Abandon_App":
                Instance.setActionname("审批人废弃");
                Instance.setAction(7);
                Instance.setApplicantuserno(Instance.getCureentuser().getUserCode());
                Instance.setFapproversuser(Instance.getCureentuser().getUserName());
                Instance.setApprovedDes(param.getOperationInfo().getApprovedDes());
                break;
            case "Repeal_App":
                Instance.setActionname("审批人撤销");
                Instance.setAction(2);
                Instance.setApplicantuserno(Instance.getCureentuser().getUserCode());
                Instance.setFapproversuser(Instance.getCureentuser().getUserName());
                Instance.setApprovedDes(param.getOperationInfo().getApprovedDes());
                break;
            default:
                break;
        }
        if(Instance.getCurrentactentity()==null) {
            Instance.setCurrentactentity(tappActDao.getById(Instance.getActid()));
        }
        Instance.setTappActroutingList(tappActroutingDao.queryByActID(Instance.getActid()));
        Instance.setCallbackApprovalUrl(getDefaultCallbackApprovalurl());
        Instance.getRunlogList().add("开始组装完成！");
    }
    String getDefaultCallbackApprovalurl(){
        if(!StringUtils.isEmpty(Instance.getProcid())){
          return  tappProcessDao.getById(Instance.getProcid()).getCallbackApprovalUrl();
        }
        return  tappProcessDao.getById(Instance.getCurrentactentity().getProcessid()).getCallbackApprovalUrl();
    }
    ///处理运行流程
    void RunProcess() throws Exception {
        Instance.getRunlogList().add("开始解析流程路由！");
        ProcRouteComm procRouteComm = new ProcRouteComm(Instance, Instance.getAction());
        String nextActchartid = procRouteComm.Route();
        if (!StringUtils.isNotBlank(nextActchartid)) {
            throw new Exception("路由解析未找到下一节点！");
        }
        Instance.getRunlogList().add("解析流程路由完成,开始判断下一个节点是否流程结束！");
        Instance.setNextactchartid(nextActchartid);
        if (!nextActchartid.equals("F_End")) {
            List<OaActEntity> actlist = tappActDao.queryByNodeIdAndProcid(nextActchartid, Instance.getProcid());
            if (actlist.isEmpty() || actlist.size() == 0) {
                throw new Exception("下一节点【" + nextActchartid + "】在数据库中未找到！\r\n");
            }
            Instance.setNextactid(actlist.get(0).getOaActId());
            Instance.setNexact(actlist.get(0));
            Instance.getRunlogList().add("开始解析下一节点审批人");
            ApproverParsing approverParsing = new ApproverParsing(Instance, tappUserDao);
            String nextActApprover = approverParsing.GetNextActApprover();
            if (!StringUtils.isNotBlank(nextActApprover)) {
                throw new Exception("流程错误，下一个节点审批人未解析到！\r\n");
            }
            Instance.setApprovalobject(approverParsing.GetNextActApprover());
            Instance.getRunlogList().add("下一节点审批人为：" + approverParsing.GetNextActApprover());
            if (Instance.getApprovalobject() == null && Instance.getApprovalobject().length() == 0) {
                throw new Exception("下一节点：" + Instance.getNexact().getActname() + "审批人获取失败！\r\n");
            } else {
                Instance.getRunlogList().add("流程路由解析完成,开始解析会签，串行各种模式！");
                ProcessHelperServiceImpl processHelper = springBeanUtils.getBean(ProcessHelperServiceImpl.class);
                processHelper.setInstance(Instance);
//                ProcessHelperServiceImpl processHelper = new ProcessHelperServiceImpl(Instance, tappPorcessinsDao, tappFromdataDao, tappActinsDao, tappProcauditDao, tappActinscommunicateDao, jdbcTemplate, tappUserDao,dbOperationHelperService);
                if (processHelper.SaveProcInfo()) {
                    sendMsgNotice();
                    AfterProcessing(processHelper);
                }
            }
        } else {
            Instance.getRunlogList().add("流程路由解析完成,开始解析会签，串行各种模式！");
            ProcessHelperServiceImpl processHelper = springBeanUtils.getBean(ProcessHelperServiceImpl.class);
            processHelper.setInstance(Instance);
//            ProcessHelperServiceImpl processHelper = new ProcessHelperServiceImpl(Instance, tappPorcessinsDao, tappFromdataDao, tappActinsDao, tappProcauditDao, tappActinscommunicateDao, jdbcTemplate, tappUserDao,dbOperationHelperService);
            if (processHelper.SaveProcInfo()) {
                sendMsgNotice();
                AfterProcessing(processHelper);
            }
            Instance.getRunlogList().add("保存流程数据入库完成！");
        }
    }

    ///处理之后bug
    void AfterProcessing(ProcessHelperServiceImpl processHelper) throws Exception {
        Instance.getRunlogList().add("开始保存业务表数据！");
        Instance.getFromdata().put("procinsid", Instance.getProcinsid());
        Instance.getFromdata().put("actinsid", Instance.getActinsid());
        Instance.getFromdata().put("procinsname", Instance.getProcinsname());
        Instance.getFromdata().put("procinsno", Instance.getProcinsno());
        try {
            R<Object> r = apiCommService.apiComm(Instance.getFromdata(), Instance.getBusinessTable() + ".SaveDataInfo", null);
            if (r.getCode()!=0) {
                ///当流程保存业务数据失败时，将状态改为8 锁住流程
                OaPorcessinsEntity oaPorcessins = tappPorcessinsDao.getById(Instance.getProcinsid());
                oaPorcessins.setPorcessinsStatus("8");
                Instance.setProcessInsState("8");
                tappPorcessinsDao.saveOrUpdate(oaPorcessins);
                Instance.getErrorlog().add("保存业务数据失败！具体错误：" + r.getMsg());
                return ;
            }
        }catch (Exception ex){
            Instance.getErrorlog().add("保存业务数据失败！具体错误：" + ex.getMessage());
            OaPorcessinsEntity oaPorcessins = tappPorcessinsDao.getById(Instance.getProcinsid());
            oaPorcessins.setPorcessinsStatus("8");
            Instance.setProcessInsState("8");
            tappPorcessinsDao.saveOrUpdate(oaPorcessins);
            return ;
        }
        if (Instance.getHandled()) {
            processHelper.HandleCallback();
        }
    }

    ///处理自动跳过的流程
    void NewCheckNextApprovalObject() {
        boolean iscontinuousby = Instance.getCurrentactentity().getIscontinuousby();
        boolean notiscontinuousby = Instance.getCurrentactentity().getIsnotcontinuousby();
        if (notiscontinuousby) {


        }

    }

    void AddProcAudit(ProcInstHandleEntity procInstanceEntity) {
        AddProcAudit(procInstanceEntity, procInstanceEntity.getApprovedDes());
    }

    ///新增审批记录
    void AddProcAudit(ProcInstHandleEntity procInstanceEntity, String approvedDes) {

        try {
            OaProcauditEntity procAudi = new OaProcauditEntity();
            procAudi.setOaProcinsId(procInstanceEntity.getProcinsid());
            if (procInstanceEntity.getActinsid() != null) {
                procAudi.setOaActinsId(procInstanceEntity.getActinsid());
                if (!"取消沟通，回复沟通".contains(procInstanceEntity.getActionname())) {
                    OaActinsEntity actInctEntity = tappActinsDao.getById(procInstanceEntity.getActinsid());
                    if (actInctEntity != null)
                        procAudi.setCreateDate(actInctEntity.getCreateDate()); //开封时间
                } else {
//                    var communicationEntity = CacheHelper.Instance.GetCommunicationState(procAudi.FActInsID).FirstOrDefault();
//                    if (communicationEntity != null)
//                    {
//                        procAudi.FActCreateDate = communicationEntity.FCreationDate;
//                    }
//                    else
//                    {
//                        procAudi.FActCreateDate = DateTime.Now;
//                    }
                }
            } else {
                procAudi.setActcreatedate(new Date());
            }
            if (procInstanceEntity.getCurrentactentity() != null) {
                procAudi.setActname(procInstanceEntity.getCurrentactentity().getActname());

            } else {

            }
//            if (string.IsNullOrEmpty(procAudi.FActName))
//            {
//                var actins = CacheHelper.Instance.GetActIns(procInstanceEntity.ProcInsID.Value).OrderByDescending(x => x.FCreationDate).FirstOrDefault();
//                if (actins != null)
//                {
//                    procAudi.FActName = actins.FActName;
//                }
//            }
            procAudi.setApprovelaction(procInstanceEntity.getActionname());
            procAudi.setApproveldescr(approvedDes != null ? approvedDes : procInstanceEntity.getApprovedDes());

            procAudi.setActopendate(new Date());// = DateTime.Now;//开始时间
            procAudi.setActfishdate(new Date());// = DateTime.Now;//结束时间
            procAudi.setApproversname(procInstanceEntity.getCureentuser().getUserName());
            procAudi.setCreateBy(procInstanceEntity.getCureentuser().getOfficeCode());// = procInstanceEntity.CureentUserID;
            procAudi.setCreateDate(new Date()); //= DateTime.Now;
            procAudi.setUpdateDate(new Date()); //= procAudi.FCreationDate;
            procInstanceEntity.getTappProcauditList().add(procAudi);
        } catch (Exception ex) {
            Instance.addErrorlog("新增审批记录报错！具体错误信息：" + ex.getMessage() + ", 具体堆栈信息：" + ex.toString());
        }

    }

    void sendMsgNotice() {
        if (Instance.GetProcessSendNoticeVOList().size() > 0) {
            OaProcesschartEntity oaProcesschart = oaProcesschartService.queryByProcessId(Instance.getProcid());
            if (oaProcesschart != null) {
                ProcessInfoReq processInfoReq = JSON.parseObject(oaProcesschart.getFlowdata(), ProcessInfoReq.class);
                if (processInfoReq.getTempNoticeList()!=null&&processInfoReq.getTempNoticeList().size() > 0) {
                    Instance.getFromdata().put("procinsid", Instance.getProcinsid());
                    Instance.getFromdata().put("actinsid", Instance.getActinsid());
                    Instance.getFromdata().put("procinsname", Instance.getProcinsname());
                    Instance.getFromdata().put("procinsno", Instance.getProcinsno());
                    processInfoReq.getTempNoticeList().forEach(ff -> {
                        OaTempNoticeEntity oaTempNotice = oaTempNoticeService.getById(ff.getTempId());
                        byte[] decodedBytes = Base64.getMimeDecoder().decode(oaTempNotice.getTempContent());
                        String tempContent =new String(decodedBytes);
                        tempContent = oaTempNoticeService.SendNoticeContent(tempContent, Instance.getFromdata());
                        List<String> adduserlist = new ArrayList<>();
                        for (ProcessSendNoticeVO ee : Instance.GetProcessSendNoticeVOList()) {
                            if (ff.getTempType().equals(ee.getTempNoticeType()) && !adduserlist.contains(ee.getUserCode())) {
                                adduserlist.add(ee.getUserCode());
                                OaSendNoticeEntity oaSendNotice = new OaSendNoticeEntity();
                                oaSendNotice.setTempNoticeId(oaTempNotice.getTempId());
                                oaSendNotice.setSendUserId(ee.getUserCode());
                                oaSendNotice.setProcessinsId(Instance.getProcinsid());
                                oaSendNotice.setSendContent(tempContent);
                                oaSendNotice.setProcessinsName(Instance.getProcinsname());
                                oaSendNotice.setIsRead(0);
                                oaSendNoticeService.saveOrUpdate(oaSendNotice);
                            }
                        }
                    });
                }
            }
        }
    }
}
