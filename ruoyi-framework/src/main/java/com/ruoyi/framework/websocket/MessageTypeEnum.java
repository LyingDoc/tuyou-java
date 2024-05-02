package com.ruoyi.framework.websocket;

public enum MessageTypeEnum implements IBaseEnum<Integer> {
    SENDNEWMESSAGE(1, "发送新的普通消息"),
    SENDNEWPORCESSMSG(2, "发送新的流程待办消息"),
    SENDPORCESSLOCK(3, "锁流，解锁程消息"),
    SENDCHATMESSSAGE(4, "发送在线聊天消息"),
    SENDOPERATE(5, "发送操作类消息"),
    WXPCLOGON(6, "微信扫码PC登录");
    private int value;
    private String description;

    MessageTypeEnum(int value, String description) {
        this.value = value;
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
