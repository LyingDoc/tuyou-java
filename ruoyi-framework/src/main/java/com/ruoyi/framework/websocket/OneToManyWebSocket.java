package com.ruoyi.framework.websocket;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * 前后端交互的类实现消息的接收推送(自己发送给所有人(不包括自己))
 *
 * @ServerEndpoint(value = "/test/oneToMany") 前端通过此URI 和后端交互，建立连接
 */
@Slf4j
@ServerEndpoint(value = "/WebSocket/sendMessage/{socketGuid}")
@Component
public class OneToManyWebSocket {

    /**
     * 记录当前在线连接数
     */
    private static AtomicInteger onlineCount = new AtomicInteger(0);

    /**
     * 存放所有在线的客户端
     */
    private static Map<String, Session> clients = new ConcurrentHashMap<>();
    private static List<WebSocketUserVO> webSocketUserVOList=new ArrayList<>();
    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("socketGuid") String socketGuid) {
        clients.put(session.getId(), session);
        if(webSocketUserVOList.stream().filter(ee->ee.getSocketGuId().equals(socketGuid)).count()<=0){
            WebSocketUserVO webSocketUserVO=new WebSocketUserVO();
            webSocketUserVO.setSessionId(session.getId());
            webSocketUserVO.setSocketGuId(socketGuid);
            webSocketUserVOList.add(webSocketUserVO);
        }
       log.info("有新连接加入：{}，客户端连接总数{}，当前在线人数为：{}", session.getId(), webSocketUserVOList.size(),onlineCount.get());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        clients.remove(session.getId());
        List<WebSocketUserVO> findWebSocketList= webSocketUserVOList.stream().filter(ee->ee.getSessionId().equals(session.getId())).collect(Collectors.toList());
       if(findWebSocketList.size()>0){
           WebSocketUserVO webSocketUserVO=findWebSocketList.get(0);
           findWebSocketList.remove(webSocketUserVO);
           if(StringUtils.isNotEmpty(webSocketUserVO.getUserId()) ){
               onlineCount.decrementAndGet(); // 在线数减1
           }
       }
        log.info("有一连接关闭：{}，客户端连接总数{}，当前在线人数为：{}", session.getId(), webSocketUserVOList.size(), onlineCount.get());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("服务端收到客户端[{}]的消息:{}", session.getId(), message);
        ReceiveMessageVO receiveMessageVO= JSON.parseObject(message,ReceiveMessageVO.class);
        List<WebSocketUserVO> findWebSocketList= webSocketUserVOList.stream().filter(ee->ee.getSocketGuId().equals(receiveMessageVO.getSocketGuid())).collect(Collectors.toList());
        WebSocketUserVO    currentWebSocket=findWebSocketList.get(0);
        switch (receiveMessageVO.getMsgtype()){
            case "msg":sendMessage(receiveMessageVO.getMsg(),receiveMessageVO.getUserid());
            break;
            case "allmsg":sendAllMessage(receiveMessageVO.getMsg(),currentWebSocket);
                break;
            case "Login":
                if(StringUtils.isNotEmpty(currentWebSocket.getUserId())) {
                    onlineCount.incrementAndGet();
                }
                currentWebSocket.setUserId(receiveMessageVO.getUserid());
                break;
            case "Logout":
                currentWebSocket.setUserId("");
                onlineCount.decrementAndGet(); // 在线数减1
                break;
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }
    /**
     * 群发消息,根据用户Id发送消息
     *
     * @param message 消息内容
     */
    public void sendSocketGuidMessage(String message, String socketGuid) {
        List<WebSocketUserVO> socketUserVOList=  webSocketUserVOList.stream().filter(ee->ee.getSocketGuId().equals(socketGuid)).collect(Collectors.toList());
        socketUserVOList.forEach(ee->{
            if(StringUtils.isNotBlank(ee.getSessionId()) &&clients.containsKey(ee.getSessionId())) {
                Session toSession = clients.get(ee.getSessionId());
                synchronized (OneToManyWebSocket.class) {
                    SendMessageVO sendMessageVO=new SendMessageVO();
                    sendMessageVO.setMsg(message);
//                    sendMessageVO.setMsgType(messageType);
                    toSession.getAsyncRemote().sendText(JSON.toJSONString(sendMessageVO));
                }
            }
        });
    }
    /**
     * 群发消息,根据用户Id发送消息
     *
     * @param message 消息内容
     */
    public void sendMessage(String message,  List<String> userList) {
        for(String userId :userList) {
            sendMessage(message,userId);
        }
    }
    /**
     * 群发消息,根据用户Id发送消息
     *
     * @param message 消息内容
     */
    public void sendMessage(String message, String userid) {
     List<WebSocketUserVO> socketUserVOList=  webSocketUserVOList.stream().filter(ee->ee.getUserId().equals(userid)).collect(Collectors.toList());
        socketUserVOList.forEach(ee->{
            if(StringUtils.isNotBlank(ee.getSessionId()) &&clients.containsKey(ee.getSessionId())) {
                Session toSession = clients.get(ee.getSessionId());
                synchronized (OneToManyWebSocket.class) {
                    SendMessageVO sendMessageVO=new SendMessageVO();
                    sendMessageVO.setMsg(message);
//                    sendMessageVO.setMsgType(messageType);
                    toSession.getAsyncRemote().sendText(JSON.toJSONString(sendMessageVO));
                }
            }
        });


    }
    /**
     * 发送在线发送消息给所有人 除了自己
     *
     * @param message 消息内容
     */
    public void sendAllMessage(String message,WebSocketUserVO currentWebSocket) {
        for (Map.Entry<String, Session> sessionEntry : clients.entrySet()) {
            Session toSession = sessionEntry.getValue();
            if(!currentWebSocket.getSessionId().equals(toSession)) {
//            log.info("服务端给客户端[{}]发送消息{}", toSession.getId(), message);
                synchronized (OneToManyWebSocket.class) {
                    toSession.getAsyncRemote().sendText(message);
                }
            }
        }
    }
}
