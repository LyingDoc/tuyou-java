package com.ruoyi.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WeixinConfiguration {
    @Value(value = "${wx.mp.configs.appId}")
    public  String appId;
    @Value(value = "${wx.mp.configs.secret}")
    public  String secret;
    @Value(value = "${wx.mp.configs.token}")
    public  String token;
    @Value(value = "${wx.mp.configs.aesKey}")
    public  String aesKey;
}
