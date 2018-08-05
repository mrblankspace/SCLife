package cn.swpu.web.socket;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

import cn.swpu.entity.User;

public class HttpSeesionConfigutation extends Configurator {

	@Override
	public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = (HttpSession)request.getHttpSession();
		//User attribute = (User) session.getAttribute("user");
		sec.getUserProperties().put(HttpSession.class.getName(), session);
		sec.getUserProperties().put("SesssionId",session.getId());
	}
	
}
