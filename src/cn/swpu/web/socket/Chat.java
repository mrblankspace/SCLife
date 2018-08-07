package cn.swpu.web.socket;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import cn.swpu.entity.Message;
import cn.swpu.entity.User;
import cn.swpu.service.MessageService;
import cn.swpu.service.UserService;
import cn.swpu.service.impl.MessageServiceImpl;
import cn.swpu.service.impl.UserServiceImpl;
import net.sf.json.JSONObject;

//websocket服务
@ServerEndpoint(value = "/websocket/chat", configurator=HttpSeesionConfigutation.class)
public class Chat {
	UserService userService = new UserServiceImpl();
	MessageService messageService = new MessageServiceImpl();
	private static final ConcurrentHashMap<Integer, Chat> webSocketConnect  = new ConcurrentHashMap<>();
	private HttpSession httpSession;
	private Session session;
	/**
	 * websocket建立连接 存储连接
	 * @param session
	 * @param config
	 */
	@OnOpen
	public void start(Session session, EndpointConfig config){
		//Map<String, Object> userProperties = config.getUserProperties();
		this.session = session;
		this.httpSession = (HttpSession)config.getUserProperties().get(HttpSession.class.getName());
		User user = (User) httpSession.getAttribute("user");
		webSocketConnect.put(user.getId(),this);//把webscoket连接放入集合
		System.out.println(((User)httpSession.getAttribute("user")).getUsername()+"连接");
		System.out.println("当前websocket连接数:"+webSocketConnect.size());
	}
	/**
	 * 处理消息   如果用户在线  则webscoket推送消息给客户并插入数据库    如果不在线只插入数据库
	 * 这里的用户在线   指的是用户打开了聊天窗    
	 * @param message
	 */
	@OnMessage
	public void handleMessage(String message){
		JSONObject jsonObject = JSONObject.fromObject(message);
		Integer from_person_id = Integer.parseInt(jsonObject.getString("from_person_id"));
		Integer to_person_id = Integer.parseInt(jsonObject.getString("to_person_id"));
		String content = jsonObject.getString("content");
		//封装消息
		Message sendMessage = new Message();
		sendMessage.setContent(content);
		User from_person = new User();
		from_person.setId(from_person_id);
		from_person = userService.queryUserById(from_person);
		User  to_person = new User();
		to_person.setId(to_person_id);
		to_person = userService.queryUserById(to_person);
		sendMessage.setFrom_person(from_person);
		sendMessage.setTo_person(to_person);
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sendMessage.setDate(simpleDateFormat.format(date));
		JSONObject jsonMessage1 = JSONObject.fromObject(sendMessage);
		sendMessage.setStatus("未读");
		//测试   消息能否返回给原用户
//		JSONObject jsonMessage = JSONObject.fromObject(sendMessage);
//		try {
//			session.getBasicRemote().sendText(jsonMessage.toString());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		if (webSocketConnect.containsKey(to_person_id)) {
			//发送消息
			Chat chat = webSocketConnect.get(to_person_id);
			JSONObject jsonMessage = JSONObject.fromObject(sendMessage);
			try {
				chat.session.getBasicRemote().sendText(jsonMessage.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//用户打开窗口  默认已读
			sendMessage.setStatus("已读");
		}
		messageService.saveMessage(sendMessage);
	}
	
	@OnClose
	public void close(){
		webSocketConnect.remove(((User)httpSession.getAttribute("user")).getId());
		System.out.println(((User)httpSession.getAttribute("user")).getUsername()+"断开连接");
		System.out.println("当前连接数:"+webSocketConnect.size());
	}
	
	

}
