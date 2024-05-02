package com.ruoyi.framework.websocket;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("发送消息实体")
@Data
public class SendMessageVO {
    String msg;
    MessageTypeEnum msgType;
}
