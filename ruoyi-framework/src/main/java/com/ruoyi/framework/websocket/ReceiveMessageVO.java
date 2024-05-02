package com.ruoyi.framework.websocket;

import lombok.Data;

import java.util.List;

///接收消息实体
@Data
public class ReceiveMessageVO {
    private String userid;///当前用户信息
    private  String msg; ///接收的消息
    private  String msgtype; ///消息类别 allmsg 发送所有人，msg 发送具体消息用户 Login 登录方法 Logout 注销方法
    private  List<String> users; ///发送用户
    private  String socketGuid;

}
