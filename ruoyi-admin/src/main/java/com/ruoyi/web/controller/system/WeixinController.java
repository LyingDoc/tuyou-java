package com.ruoyi.web.controller.system;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.framework.web.service.SysLoginService;

import com.ruoyi.system.domain.SysWeixinUser;
import com.ruoyi.system.service.ISysWeixinUserService;
import com.ruoyi.system.service.impl.SysUserServiceImpl;
import com.ruoyi.web.config.WeixinConfiguration;
import com.ruoyi.web.vo.WeixinVo;
import com.ruoyi.framework.websocket.OneToManyWebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.*;
import java.security.cert.CertificateException;
import java.util.Dictionary;
import java.util.Hashtable;

@RestController
@RequestMapping("/system/weixin")
public class WeixinController extends BaseController {
    private static final String url = "https://api.weixin.qq.com/sns/jscode2session";
    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private WeixinConfiguration weixinConfiguration;
    @Autowired
    private ISysWeixinUserService sysWeixinUserService;

    @Autowired
    private SysLoginService loginService;

    @Autowired
    private SysUserServiceImpl sysUserService;
    @Autowired
    private OneToManyWebSocket oneToManyWebSocket;

    private static final Logger logger = LoggerFactory.getLogger(WeixinController.class);
    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @GetMapping("/weixinlogin")
    public AjaxResult weixinlogin(@RequestParam("code") String code) {
        String openId=null;
        String resultstr = restTemplate.getForObject(String.format(url + "?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code", weixinConfiguration.appId, weixinConfiguration.secret, code), String.class);
        WeixinVo weixinVo = JSON.parseObject(resultstr, WeixinVo.class);
        if (weixinVo.getCode() == 0&&weixinVo.getErrcode() == 0) {
                openId=weixinVo.getOpenid();
                QueryWrapper<SysWeixinUser> queryWrapper=new QueryWrapper<>();
                queryWrapper.eq("open_id",openId);
                SysWeixinUser sysWeixinUser=  sysWeixinUserService.getBaseMapper().selectOne(queryWrapper);
                if(sysWeixinUser==null){
                    AjaxResult ajax = AjaxResult.success();
                    ajax.put("register", openId);
                    return ajax;
                }else{
                 SysUser sysUser= sysUserService.getById(sysWeixinUser.getUserId());
                    AjaxResult ajax = AjaxResult.success();
                    // 生成令牌
                    String token = loginService.login(sysUser.getUserName(), sysUser.getPassword());
                    ajax.put(Constants.TOKEN, token);
                    return ajax;
                }
        }else {
            return AjaxResult.error("微信登录失败！");
        }

    }

    /**
     * 登录方法
     *
     * @return 结果
     */
    @GetMapping("/pcweixinlogin")
    public AjaxResult pcweixinlogin(@RequestParam("code") String code,@RequestParam("guid") String guid) {
        String openId=null;
        logger.info("扫码登录记录code:"+code+" guid:"+guid);
        String resultstr = restTemplate.getForObject(String.format(url + "?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code", weixinConfiguration.appId, weixinConfiguration.secret, code), String.class);
        WeixinVo weixinVo = JSON.parseObject(resultstr, WeixinVo.class);
        if (weixinVo.getCode() == 0&&weixinVo.getErrcode() == 0) {
                openId=weixinVo.getOpenid();
                QueryWrapper<SysWeixinUser> queryWrapper=new QueryWrapper<>();
                queryWrapper.eq("open_id",openId);
                SysWeixinUser sysWeixinUser=  sysWeixinUserService.getBaseMapper().selectOne(queryWrapper);
                if(sysWeixinUser==null){
                    Dictionary<String,String> dic=new Hashtable<>();
                    dic.put("open_id",openId);
                    dic.put("guid",guid);
                    dic.put("funname","scanCodeLogon");
                    logger.info("扫码登录成功:"+JSON.toJSONString(dic));
                    oneToManyWebSocket.sendSocketGuidMessage(JSON.toJSONString(dic),guid);
                    return AjaxResult.success("扫码成功");
                }else{
                    SysUser sysUser= sysUserService.selectUserById(Long.parseLong(sysWeixinUser.getUserId()));
                    // 生成令牌
                    String token = loginService.scanCodeLogon(sysUser);
                    Dictionary<String,String> dic=new Hashtable<>();
                    dic.put("token",token);
                    dic.put("guid",guid);
                    dic.put("funname","scanCodeLogon");
                    logger.info("扫码登录成功:"+JSON.toJSONString(dic));
                    oneToManyWebSocket.sendSocketGuidMessage(JSON.toJSONString(dic),guid);
                    return AjaxResult.success("扫码成功");
                }

        }else {
            return AjaxResult.error("扫码失败！");
        }
    }

    private void disableSslCertCheck() {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            @Override
            public void checkClientTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) throws CertificateException {

            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }


        }
        };
        try {
            // Install the all-trusting trust manager
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };

        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
    }



}
