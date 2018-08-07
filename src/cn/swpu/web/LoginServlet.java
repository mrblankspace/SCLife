package cn.swpu.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.imageio.spi.RegisterableService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import cn.swpu.entity.User;
import cn.swpu.service.UserService;
import cn.swpu.service.impl.UserServiceImpl;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 能够接受页面传送来的中文，并传输出去
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String flag = request.getParameter("flag");
		UserService us = new UserServiceImpl();
		if ("login".equals(flag)) {

			String email = request.getParameter("userEmail");
			String password = request.getParameter("password");
			User tempuser = new User();
			tempuser.setEmail(email);
			tempuser.setPassword(password);
			User imlUser = us.Login(tempuser);

			if (imlUser.getEmail() == null) {
				Cookie[] cookies = request.getCookies();
				for (Cookie cookie : cookies) {
					if ("userEmail".equals(cookie.getName())) {
						email = URLDecoder.decode(cookie.getValue(), "UTf-8");
					}
					if ("password".equals(cookie.getName())) {
						password = cookie.getValue();
					}
				}
				tempuser.setEmail(email);
				tempuser.setPassword(password);
				imlUser = us.Login(tempuser);
				if (imlUser.getEmail() != null) {
					// 跳转有两种方式：1、请求转发，2、重定向
					HttpSession httpsession = request.getSession();
					httpsession.setAttribute("user", imlUser);
					httpsession.setMaxInactiveInterval(30 * 60);
					request.getRequestDispatcher("index.jsp").forward(request, response);
				} else {
					// 重定向，回到登录页面
					response.sendRedirect("login.jsp");
				}
			} else {
				if (imlUser.getEmail() != null) {
					String imageText = request.getParameter("image");
					// 图片的验证码
					String text = (String) request.getSession().getAttribute("text");
					if (text!=null&&!text.equalsIgnoreCase(imageText)) {
						request.setAttribute("imageMess", "验证码输入错误!");
						request.getRequestDispatcher("login.jsp").forward(request, response);
					} else {
						HttpSession httpSession = request.getSession();
						httpSession.setAttribute("user", imlUser);// 此处已修改为传出user

						httpSession.setMaxInactiveInterval(30 * 60);

						// 把用户名和密码都放到cookie当中
						Cookie emailCookie = new Cookie("userEmail", URLEncoder.encode(imlUser.getEmail(), "UTF-8"));// 支持中文
						Cookie pwdCookie = new Cookie("password", imlUser.getPassword());
						emailCookie.setMaxAge(60 * 60);
						emailCookie.setPath("/");
						pwdCookie.setMaxAge(60 * 60);
						pwdCookie.setPath("/");
						response.addCookie(emailCookie);
						response.addCookie(pwdCookie);
						// request.("index.jsp").forward(request,
						// response);// 转向哪个界面呢
						response.sendRedirect("index.jsp");
					}
				} else {
					response.sendRedirect("login.jsp");
				}
			}
		} else if ("register".equals(flag)) {
			String username = request.getParameter("userName");
			String email = request.getParameter("userEmail");
			String password = request.getParameter("password");
			String address = request.getParameter("address");
			String tel = request.getParameter("userTel");

			User user = new User();
			user.setAddress(address);
			user.setEmail(email);
			user.setPassword(password);
			user.setTel(tel);
			user.setUsername(username);

			int row = us.Register(user);

			request.getRequestDispatcher("login.jsp").forward(request, response);

		} else if ("edit".equals(flag)) {
			int id = Integer.parseInt(request.getParameter("id"));
			User tempuser = new User();
			tempuser.setId(id);
			User imlUser = us.queryUserById(tempuser);
			request.setAttribute("user", imlUser);
			request.getRequestDispatcher("jsp/user/edituser_inf.jsp").forward(request, response);
		} else if ("save".equals(flag)) {
			User user = new User();
			String username = request.getParameter("username");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String tel = request.getParameter("tel");
			String address = request.getParameter("address");
			int id = Integer.parseInt(request.getParameter("id"));

			user.setId(id);
			user.setUsername(username);
			user.setEmail(email);
			user.setPassword(password);
			user.setTel(tel);
			user.setAddress(address);
			System.out.println(user.getEmail());

			int rows = us.UpdateUser(user);
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("user", user);
			// response.sendRedirect("index.jsp");
			request.getRequestDispatcher("OrderServlet?flag=findOrderByUser").forward(request, response);

		}
	}

}
