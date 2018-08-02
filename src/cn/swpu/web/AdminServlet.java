package cn.swpu.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.swpu.entity.User;
import cn.swpu.service.AdminService;
import cn.swpu.service.UserService;
import cn.swpu.service.impl.AdminServiceImpl;
import cn.swpu.service.impl.UserServiceImpl;

/**
 * Servlet implementation class AdminServlet
 * 管理员处理的servlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService adminService = new AdminServiceImpl();
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		if ("showUsers".equals(request.getParameter("flag"))) {
			List<User> findAllUser = adminService.findAllUser();
			request.setAttribute("userList", findAllUser);
	
			request.getRequestDispatcher("/jsp/user/userinfo_list.jsp").forward(request, response);
		}else if("query".equals(request.getParameter("flag")))
		{
			
			String name=(String)request.getParameter("condition");
			if("username".equals(name))
			{
				String nature=request.getParameter("content");
				List<User> list=adminService.queryByUsername(nature);
				request.setAttribute("userList", list);
				request.getRequestDispatcher("jsp/user/userinfo_list.jsp").forward(request, response);
				
			}else if("address".equals(name))
			{
				String nature=request.getParameter("content");
				List<User> list=adminService.queryByAddress(nature);
				request.setAttribute("userList", list);
				request.getRequestDispatcher("jsp/user/userinfo_list.jsp").forward(request, response);
			}else if("email".equals(name))
			{
				String nature=request.getParameter("content");
				List<User> list=adminService.queryByEmail(nature);
				request.setAttribute("userList", list);
				request.getRequestDispatcher("jsp/user/userinfo_list.jsp").forward(request, response);
				
			}
		}else if("delete".equals(request.getParameter("flag")))
		{
			int userid=Integer.parseInt(request.getParameter("id"));
			UserService us=new UserServiceImpl();
			User user=adminService.findById(userid);
			int rows=us.DeleteUser(user);
			
			if(rows!=0)
			{
				request.getRequestDispatcher("AdminServlet?flag=showUsers").forward(request, response);
			}
		}else if("edit".equals(request.getParameter("flag")))
		{
			int id=Integer.parseInt(request.getParameter("id"));
			User tempuser=new User();
			tempuser.setId(id);
			UserService us=new UserServiceImpl();
			 User imlUser = us.queryUserById(tempuser);
			 request.setAttribute("user", imlUser);
			 request.getRequestDispatcher("jsp/user/adminedituser.jsp").forward(request, response);
		}else if("save".equals(request.getParameter("flag")))
		{
			User user=new User();
			UserService us=new UserServiceImpl();
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
			request.getRequestDispatcher("AdminServlet?flag=showUsers").forward(request, response);
		}
	}

	
   
	
	
}
