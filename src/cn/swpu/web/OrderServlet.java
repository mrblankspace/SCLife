package cn.swpu.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.swpu.entity.Message;
import cn.swpu.entity.Order;
import cn.swpu.entity.User;
import cn.swpu.service.MessageService;
import cn.swpu.service.OrderService;
import cn.swpu.service.impl.MessageServiceImpl;
import cn.swpu.service.impl.OrderServiceImpl;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderService orderService = new OrderServiceImpl();
	private MessageService messageService = new MessageServiceImpl();
	HttpSession httpSession;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
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
		}else if ("aceptOrder".equals(request.getParameter("flag"))) {
			httpSession = request.getSession();//获取session内容，session内是user实例
			User user = (User) httpSession.getAttribute("user");//获取接单人实例
			//int id=user.getId();//获取接收者id
			int order_id=Integer.parseInt(request.getParameter("order_id"));//获取订单id
			OrderService os=new OrderServiceImpl();
			Order tempOrder=os.GetOrderById(order_id);//获得id对应的订单,此订单有所有信息，除了接单人，完成时间，
			Message message = new Message();
			message.setContent("有人接单啦");
			Date date = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			message.setDate(simpleDateFormat.format(date));
			message.setTo_person(tempOrder.getSend_person());
			message.setStatus("未读");
			messageService.saveMessage(message);
			tempOrder.setAccept_person(user);//设置接单人
			tempOrder.setOrder_status("已接收");
			int result=os.UpdateOrder(tempOrder);//更新数据库，传入接单人实例，update方法中将接单人实例的id读取并读入数据库
			if(result!=0)
			{
				request.getRequestDispatcher("jsp/order/accept_order_success.jsp").forward(request, response);
			}
			
		}else if("query".equals(request.getParameter("flag")))
		{
			String name=(String)request.getParameter("condition");
			if("ordertype".equals(name))
			{
				String nature=request.getParameter("content");
				List<Order> list=orderService.queryByOrderType(nature);
				request.setAttribute("list", list);
				request.getRequestDispatcher("jsp/order/orderinfo_list.jsp").forward(request, response);
				
			}else if("orderstatus".equals(name))
			{
				String nature=request.getParameter("content");
				List<Order> list=orderService.queryByOrderStatus(nature);
				request.setAttribute("list", list);
				request.getRequestDispatcher("jsp/order/orderinfo_list.jsp").forward(request, response);
			}else if("ordertime".equals(name))
			{
				String nature=request.getParameter("content");
				List<Order> list=orderService.queryByOrdertime(nature);
				request.setAttribute("list", list);
				request.getRequestDispatcher("jsp/order/orderinfo_list.jsp").forward(request, response);
				
			}else if("orderreward".equals(name))
			{
				String nature=request.getParameter("content");
				List<Order> list=orderService.queryByOrderReward(nature);
				request.setAttribute("list", list);
				request.getRequestDispatcher("jsp/order/orderinfo_list.jsp").forward(request, response);
			}
		}else if("findOrderByUser".equals(request.getParameter("flag"))) {
			httpSession=request.getSession();
			User user=(User) httpSession.getAttribute("user");
			List<Order> list1=orderService.findBySendId(user);
			List<Order> list2=orderService.findByAcptId(user);
			request.setAttribute("listBySendId", list1);
			request.setAttribute("listByAcptId", list2);
			request.getRequestDispatcher("jsp/user/userinfo_show.jsp").forward(request, response);
		}
	}
	
	

}
