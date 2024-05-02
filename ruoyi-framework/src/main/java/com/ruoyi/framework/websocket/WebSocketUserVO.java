package com.ruoyi.framework.websocket;

import lombok.Data;

@Data
public class WebSocketUserVO {
    public String userId; ///这个是用户ID
    public String sessionId; ///服务器SessionId
    public String socketGuId; ///客户端ID
}
