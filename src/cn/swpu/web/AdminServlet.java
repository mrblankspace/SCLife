package cn.swpu.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.swpu.entity.User;
import cn.swpu.service.AdminService;
import cn.swpu.service.impl.AdminServiceImpl;

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
		if ("showUsers".equals(request.getParameter("flag"))) {
			List<User> findAllUser = adminService.findAllUser();
			request.setAttribute("userList", findAllUser);
	
			request.getRequestDispatcher("/jsp/user/userinfo_list.jsp").forward(request, response);
		}
		
	}

	
   
	
	
}
