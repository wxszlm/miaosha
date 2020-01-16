package top.yxf.miaosha.socket;


import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/ws/{sid}")
@Component
public class SocketServer {

    private static int onlineCount = 0;
    private static Map<String, SocketServer> clients = new ConcurrentHashMap<>();
    private Session session;
    private String sid;

    /**
     * 连接建立成功调用的方法
     * @param sid
     * @param session
     * @throws IOException
     */
    @OnOpen
    public void onOpen(@PathParam("sid") String sid, Session session) throws IOException {
        this.sid = sid;

        this.session = session;
        addOnlineCount();//在线数加1
        clients.put(sid, this);//加入map中
        System.out.println("连接建立成功");
    }


    /**
     * 连接关闭调用的方法
     * @throws IOException
     */
    @OnClose
    public void onClose() throws IOException {
        clients.remove(sid);//从map中删除
        subOnlineCount();//在线数-1
        System.out.println("连接关闭成功");
    }


    /**
     * 连接错误方法
     * @param error
     */

    @OnError
    public void onError( Throwable error) {
        System.out.println("连接异常");
        error.printStackTrace();
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message
     * @throws IOException
     */
    @OnMessage
    public void onMessage(String message,@PathParam("sid") String sid){
        //解析字符串
        if(null!=message && !"".equals(message)){
            //根据sid拆分得到消息发送类型
            String type=sid.substring(sid.length() - 1);
            if("A".equals(type)) //群发
                for (String key : clients.keySet()) {
                    try {
                        clients.get(key).sendMessage(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            else
                try {
                    sendMessageAll(message,sid);
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }


    /**
     * 指定用户发送
     * @param message
     * @param sid
     * @throws IOException
     */
    public void sendMessageAll(String message,@PathParam("sid") String sid) throws IOException {
        try {
            if (clients.get(sid) != null) {
                clients.get(sid).sendMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * 实现服务器主动推送
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        this.session.getAsyncRemote().sendText(message);//异步防止阻塞
        System.out.println("发送消息成功！");
    }


    /**
     * 获取连接池中所有在线人数
     * @return
     */
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }


    /**
     * 向连接池中添加连接
     * @return
     */
    public static synchronized void addOnlineCount() {
        SocketServer.onlineCount++;
    }

    /**
     * 移除连接池中的连接
     * @return
     */
    public static synchronized void subOnlineCount() {
        SocketServer.onlineCount--;
    }


    /**
     * 获取连接池中所有连接
     * @return
     */
    public static synchronized Map<String, SocketServer> getClients() {
        return clients;
    }

}
