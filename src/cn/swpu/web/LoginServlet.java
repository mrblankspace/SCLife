package cn.swpu.web;

import java.io.IOException;

import javax.imageio.spi.RegisterableService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
		System.out.println("进来啦，这是doGet");
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String flag=request.getParameter("flag");
		//能够接受页面传送来的中文，并传输出去
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		UserService us = new UserServiceImpl();
		if("login".equals(flag))
		{		
		String email = request.getParameter("userAccount");
		String password = request.getParameter("userPw");
		
		User tempuser=new User();
		tempuser.setEmail(email);
		tempuser.setPassword(password);
		User user = us.Login(tempuser);
		
		if(user.getEmail()!=null){
			request.setAttribute("user", user);//此处已修改为传出user
			request.getRequestDispatcher("index.jsp")
				.forward(request, response);//转向哪个界面呢
			//Session user['role']="lichenghong";百度搜索session
		}else {
			response.sendRedirect("login.jsp");
		}
		}
		else if("register".equals(flag))
		{
			String username=request.getParameter("userName");
			String email=request.getParameter("userNumber");
			String password=request.getParameter("userPw");
			String address=request.getParameter("userAge");
			String tel=request.getParameter("userSex");
			
			
			User user=new User();
			user.setAddress(address);
			user.setEmail(email);
			user.setPassword(password);
			user.setTel(tel);
			user.setUsername(username);
			
			int row=us.Register(user);
			
			request.getRequestDispatcher("login.jsp").forward(request, response);
			
			/*
			 * Id，订单量，用户身份都不能允许用户设置*/
			/**
			 * 前端用的是重定向，我这写的是转发的位置，将前端修改
			 */
			
		}else if("sendUser".equals(flag))
		{
			User user=(User)request.getAttribute("user");
			
			request.setAttribute("user", user);
			request.getRequestDispatcher("jsp/userinfo_show.jsp").forward(request, response);//新版本路径需要修改
			
			
			
			
			
		}
	}
	

}

