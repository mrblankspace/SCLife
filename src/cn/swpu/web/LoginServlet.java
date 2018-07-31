package cn.swpu.web;

import java.io.IOException;

import javax.imageio.spi.RegisterableService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import cn.swpu.entity.User;
import cn.swpu.service.UserService;
import cn.swpu.service.impl.UserServiceImpl;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//能够接受页面传送来的中文，并传输出去
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String flag=request.getParameter("flag");
		UserService us = new UserServiceImpl();
		if("login".equals(flag))
		{		
		String email = request.getParameter("userEmail");
		String password = request.getParameter("password");
		
		User tempuser=new User();
		tempuser.setEmail(email);
		tempuser.setPassword(password);
		 User imlUser = us.Login(tempuser);
		
		if(imlUser.getEmail()!=null){
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("user", imlUser);//此处已修改为传出user
			request.getRequestDispatcher("index.jsp")
				.forward(request, response);//转向哪个界面呢
		}else {
			response.sendRedirect("login.jsp");
		}
		}
		else if("register".equals(flag))
		{
			String username=request.getParameter("userName");
			String email=request.getParameter("userEmail");
			String password=request.getParameter("password");
			String address=request.getParameter("address");
			String tel=request.getParameter("userTel");
			
			
			User user=new User();
			user.setAddress(address);
			user.setEmail(email);
			user.setPassword(password);
			user.setTel(tel);
			user.setUsername(username);
			
			int row=us.Register(user);
			
			request.getRequestDispatcher("login.jsp").forward(request, response);
			
			
		}else if("edit".equals(flag))
		{
			int id=Integer.parseInt(request.getParameter("id"));
			User tempuser=new User();
			tempuser.setId(id);
			 User imlUser = us.queryUserById(tempuser);
			 request.setAttribute("user", imlUser);
			 request.getRequestDispatcher("jsp/user/edituser_inf.jsp").forward(request, response);
		}
		else if("save".equals(flag))
		{
			User user=new User();
			String username=request.getParameter("username");
			String email=request.getParameter("email");
			String password=request.getParameter("password");
			String tel=request.getParameter("tel");
			String address=request.getParameter("address");
			int id=Integer.parseInt(request.getParameter("id"));
			
			user.setId(id);
			user.setUsername(username);
			user.setEmail(email);
			user.setPassword(password);
			user.setTel(tel);
			user.setAddress(address);
			 System.out.println(user.getEmail());
			 
			int rows=us.UpdateUser(user);
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("user", user);
			//response.sendRedirect("index.jsp");
			request.getRequestDispatcher("jsp/user/userinfo_show.jsp").forward(request, response);
			
			
		}
	}

}

