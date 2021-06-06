package com.ts.template.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wxl
 * @date 2020-04-20 下午5:57
 * @company www.dfdk.com.cn
 */
@ServerEndpoint(value = "/websocket/{symbol}")
@Component
public class WebSocketServer {

    private static Logger log = LoggerFactory.getLogger(WebSocketServer.class);

    /***静态变量，用来记录当前在线连接数。应该把它设计成线程安全的*/
    private static final AtomicInteger ON_LINE_COUNT = new AtomicInteger(0);

    /**
     * 存活的session集合（使用线程安全的map保存）
     */
    private static Map<String, Session> sessionMap = new HashMap<>();

    private static CopyOnWriteArraySet<Session> sessionSet = new CopyOnWriteArraySet<Session>();

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session, @PathParam("symbol")String symbol) {
        log.info("连接的客户端的标志为:{}",symbol);
        sessionMap.put(symbol, session);
        sessionSet.add(session);
        int count = ON_LINE_COUNT.incrementAndGet();
        log.info("有新的连接加进来,当前的连接数为{}",count);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session, @PathParam("symbol")String symbol) {
        sessionSet.remove(session);
        int count = ON_LINE_COUNT.decrementAndGet();
        log.info("有一连接关闭！当前在线人数为" + count);
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("来自客户端的消息:" + message);
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        //error.printStackTrace();
    }

    /**
     * 给指定vid的用户发送自定义消息
     * message {userName:"",data:{}}
     * */
    public void sendMessage(String message,String symbol) {
        Session session = sessionMap.get(symbol);
        try {
            if (session != null && session.isOpen()) {
                session.getBasicRemote().sendText(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据会话发送消息
     * @author wxl
     * @date 2020-03-10
     * @param session 会话
     * @param msg need send msg
     * @return null
     **/
    public static void sendMsg (Session session,String msg) {
        try {
            session.getBasicRemote().sendText(msg);
        } catch (IOException e) {
            log.error("发送消息出错:{}",e.getMessage());
        }
    }

    /**
     * 群发消息
     * @author wxl
     * @date 2020-03-10
     * @param message need send msg
     * @exception IOException IOException
     * @return null
     **/
    public static void sendInfo(String message) throws IOException {
        for (Map.Entry<String, Session> entry : sessionMap.entrySet()) {
            Session session = entry.getValue();
            try {
                if (session != null && session.isOpen()) {
                    session.getBasicRemote().sendText(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static Map<String, Session> getSessionMap() {
        return sessionMap;
    }
}
