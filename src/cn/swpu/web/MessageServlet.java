package cn.swpu.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.swpu.entity.Message;
import cn.swpu.entity.User;
import cn.swpu.service.MessageService;
import cn.swpu.service.impl.MessageServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

/**
 * Servlet implementation class MessageServlet
 */
@WebServlet("/MessageServlet")
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MessageService messageService = new MessageServiceImpl();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
		HttpSession httpSession = request.getSession();
		User user = (User)httpSession.getAttribute("user");
		String flag = request.getParameter("flag");
		if("queryMessage".equals(flag)){                //ajax请求
			List<Message> messagesList = messageService.queryMessageByUserId(user.getId());
			JsonConfig jsonConfig = new JsonConfig();
			JSONArray jsonArray = JSONArray.fromObject(messagesList,jsonConfig);
			response.getWriter().println(jsonArray.toString());
		}else if("readMessage".equals(flag)){
			int messageId = Integer.parseInt(request.getParameter("messageId"));
			messageService.readMessage(messageId);
		}
	}
       
   

}
