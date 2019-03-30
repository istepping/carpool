package com.carpool.webSocket;

import com.carpool.dao.JoinGroupMapper;
import com.carpool.dao.MessageMapper;
import com.carpool.dao.UserMapper;
import com.carpool.dto.GroupInfo;
import com.carpool.dto.MessageDto;
import com.carpool.entity.JoinGroup;
import com.carpool.entity.Message;
import com.carpool.entity.User;
import com.carpool.utils.AuthUtil;
import com.carpool.utils.FastJsonUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author sunLei on 2019/3/30 11:24
 * @version 1.0
 */
@Component
@ServerEndpoint("/webSocket/{gId}/{uId}")
public class WebSocketServer {
    /**在线人数,群-人数*/
    private static Map<String,Integer> onlineNumber=new ConcurrentHashMap<String, Integer>();
    /**保存连接信息(gId+uId)-webSocket*/
    private static Map<String,WebSocketServer> clients=new ConcurrentHashMap<String, WebSocketServer>();
    /**消息缓存uId-list<Message>*/
    private static Map<String,List<MessageDto>> messageCache=new ConcurrentHashMap<>();
    /**相关连接信息*/
    private Session session;
    private String gId;
    private String uId;
    private User user;
    private List<User> users;//加群的人员信息
    //群信息,用户信息groupInfo
    /**连接*/
    @OnOpen
    public void onOpen(@PathParam("gId") String gId,@PathParam("uId") String uId, Session session){
        if(!AuthUtil.isNumber(gId) || !AuthUtil.isNumber(uId)){
            System.out.println("参数为空!");
            return;
        }
        System.out.println("群"+gId+"-用户"+uId+"-接入连接"+gId+uId);
        Integer number=onlineNumber.get(gId);
        if(number==null){
            number=1;
        }else{
            number++;
        }
        //在线人数增加
        onlineNumber.put(gId,number);
        this.gId=gId;
        this.session=session;
        this.uId=uId;
        //主动注入
        ApplicationContext context=new ClassPathXmlApplicationContext("classpath:spring-mybatis.xml");
        UserMapper userMapper=(UserMapper) context.getBean("userMapper");
        this.user=userMapper.selectByPrimaryKey(Long.parseLong(uId));
        JoinGroupMapper joinGroupMapper=(JoinGroupMapper) context.getBean("joinGroupMapper");
        //群用户信息
        List<JoinGroup> joinGroups=joinGroupMapper.selectByGId(Long.valueOf(gId));
        List<User> users=new ArrayList<>();
        for(JoinGroup joinGroup: joinGroups){
            users.add(userMapper.selectByPrimaryKey(joinGroup.getuId()));
        }
        this.users=users;
        clients.put(gId+uId,this);
        //查看是否有缓存信息,及时推送
        if(messageCache.containsKey(uId)){
            List<MessageDto> messageDtos=messageCache.get(uId);
            this.session.getAsyncRemote().sendText(FastJsonUtils.toJSONString(messageDtos));
        }
        System.out.println("连接完成!");
    }
    /**收到客户端消息*/
    @OnMessage
    public void onMessage(String message,Session session) {
        System.out.println("收到消息:"+message);
        //转换为消息对象
        Date now=new Date();
        MessageDto newMessageDto =new MessageDto(gId,"mes"+now.getTime(),"文本",uId,user.getuAvatarUrl(),user.getuNickName(),String.valueOf(now.getTime()),message);
        //持久化到数据库
        ApplicationContext context=new ClassPathXmlApplicationContext("classpath:spring-mybatis.xml");
        MessageMapper messageMapper=(MessageMapper) context.getBean("messageMapper");
        messageMapper.insertSelective(new Message(Long.valueOf(gId),Long.valueOf(uId),message,now,newMessageDto.getType(),0));
        //发送消息给本群所有在线的人
        for(User user:users){
            WebSocketServer item=onLine(user.getuId().toString());
            if(item!=null){
                //在线
                List<MessageDto> messageDtoList=new ArrayList<>();
                messageDtoList.add(newMessageDto);
                item.session.getAsyncRemote().sendText(FastJsonUtils.toJSONString(messageDtoList));
            }else{
                //不在线,先缓存
                List<MessageDto> messages=messageCache.get(user.getuId().toString());
                if(messages==null){
                    messages=new ArrayList<>();
                }
                messages.add(newMessageDto);
                messageCache.put(user.getuId().toString(),messages);
            }
        }
    }
    /**服务器异常调用*/
    @OnError
    public void onError(Session session,Throwable error){
        System.out.println("服务器发生错误");
        error.printStackTrace();
    }
    /**连接关闭调用*/
    @OnClose
    public void onClose(){
        if(!AuthUtil.isNumber(gId) || !AuthUtil.isNumber(uId)){
            return;
        }
        System.out.println("退出连接");
        Integer number=onlineNumber.get(gId);
        if(number==null){
            number=0;
        }else{
            number--;
        }
        //在线人数减少
        onlineNumber.put(gId,number);
        clients.remove(gId+uId);
    }
    public static synchronized int getOnLineNumber(String gId){
        Integer number=onlineNumber.get(gId);
        if(number==null)
        {
            number=0;
        }
        return number;
    }
    /**判断使用是否在线*/
    public static synchronized WebSocketServer onLine(String uId){
        for(WebSocketServer item:clients.values()){
            if(item.uId.equals(uId)){
               return item;
            }
        }
        return null;
    }
}
