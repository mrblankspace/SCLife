package cn.swpu.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.swpu.entity.Order;
import cn.swpu.service.OrderService;
import cn.swpu.service.impl.OrderServiceImpl;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderService orderService = new OrderServiceImpl();
	

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if("findOrder_index".equals(request.getParameter("flag"))){
			List<Order> list = orderService.ShowOrder();
			request.setAttribute("list", list);
			request.setAttribute("path", "首页");
			request.getRequestDispatcher("jsp/order/orderinfo_list.jsp").forward(request, response);
		}else if ("findOrder_waimai".equals(request.getParameter("flag"))) {
			List<Order> list = orderService.findWaimaiOrder();
			request.setAttribute("list", list);
			request.setAttribute("path", "快递 外卖");
			request.getRequestDispatcher("jsp/order/orderinfo_list.jsp").forward(request, response);
		}else if ("findOrder_parttimejob".equals(request.getParameter("flag"))) {
			List<Order> list = orderService.findJobOrder();
			request.setAttribute("list", list);
			request.setAttribute("path", "兼职");
			request.getRequestDispatcher("jsp/order/orderinfo_list.jsp").forward(request, response);
		}else if ("findOrder_other".equals(request.getParameter("flag"))) {
			List<Order> list = orderService.findOtherOrder();
			request.setAttribute("list", list);
			request.setAttribute("path", "其他");
			request.getRequestDispatcher("jsp/order/orderinfo_list.jsp").forward(request, response);
		}else if ("findOrder_waibao".equals(request.getParameter("flag"))) {
			List<Order> list = orderService.findWaibaoOrder();
			request.setAttribute("list", list);
			request.setAttribute("path", "服务外包");
			request.getRequestDispatcher("jsp/order/orderinfo_list.jsp").forward(request, response);
		}
	}
	
	

}
