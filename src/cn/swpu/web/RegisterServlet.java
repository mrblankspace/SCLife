package cn.swpu.web;

import java.io.IOException;
import java.util.regex.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.swpu.entity.User;
import cn.swpu.service.UserService;
import cn.swpu.service.impl.UserServiceImpl;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserService us = new UserServiceImpl();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String username = request.getParameter("userName");
		String email = request.getParameter("userEmail");
		String password = request.getParameter("password");
		String address = request.getParameter("address");
		String tel = request.getParameter("userTel");

		boolean telflag = false;
		boolean emailflag = false;

		telflag = isMobileNO(tel);
		emailflag = isEmailNO(email);

		if (telflag == true && emailflag == true) {
			User user = new User();
			user.setAddress(address);
			user.setEmail(email);
			user.setPassword(password);
			user.setTel(tel);
			user.setUsername(username);

			int row = us.Register(user);

			request.getRequestDispatcher("login.jsp")
					.forward(request, response);
		} else if(telflag == false && emailflag==false){
			  
				request.setAttribute("telMess", "手机号码格式不正确!");
				request.setAttribute("emailMess", "邮箱格式不正确!");
				request.getRequestDispatcher("register.jsp")
				.forward(request, response);
		}else if(telflag == false && emailflag==true){
			request.setAttribute("telMess", "手机号码格式不正确!");
			request.getRequestDispatcher("register.jsp")
			.forward(request, response);
			
		}else {
			request.setAttribute("emailMess", "邮箱格式不正确!");
			request.getRequestDispatcher("register.jsp")
			.forward(request, response);
		}
			
	}

	public static boolean isEmailNO(String emails) {
		String reg = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(emails);
		return m.matches();
	}

	public static boolean isMobileNO(String mobiles) {
		String reg = "^^1((3[0-9])|(4[5|7])|(5([0-3]|[5-9]))|(8[0,5-9])|(7[0-9]))\\d{8}$";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

}
