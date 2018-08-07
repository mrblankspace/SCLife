package cn.swpu.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;

import cn.swpu.dao.impl.OrderDaoImpl;
import cn.swpu.entity.Order;
import cn.swpu.entity.User;
import cn.swpu.util.MD5Util;
import cn.swpu.service.impl.*;
import sun.awt.Symbol;

@WebServlet(description = "支付", urlPatterns = { "/PayServelet" })
public class PayServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OrderServiceImpl oServiceImpl = new OrderServiceImpl();

	public PayServelet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		Order or = new Order();// 订单对象
		String order_id = oServiceImpl.AutogetOrder_id(); // 订单id
		or.setOrder_id(order_id);
		char type = request.getParameter("ordertype").charAt(0);// 获取订单的类型
		String ordertype = type(type);// 订单实际类型
		or.setCatagory(ordertype);
		String describe = request.getParameter("textarea");
		or.setDescribe(describe);// 订单内容
		User usr = (User) session.getAttribute("user");
		or.setSend_person(usr);// 发起订单的人
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = simpleDateFormat.format(date);
		or.setOrder_date(format);
		or.setOrder_status("已付押金");
		float price = Float.parseFloat(request.getParameter("inputprice"));// 价格
		or.setOrder_money(price);
		session.setAttribute("order", or);
		if (!"0".equals(request.getParameter("inputprice"))) {
			// 生成订单信息

			// 生成支付信息
			String uid = "e060d5b0a8347544149bd9f2";// 商户唯一id:
			String token = "1155e116c6f542aeca5337d12e09cdf4";// 商户token
			int istype = Integer.parseInt(request.getParameter("payway").toString());
			String notify_url = "http://zb.mrblankspace.cn/SCLife/AddOrderServelet";// 返回post路径
			String return_url = "http://zb.mrblankspace.cn/SCLife/test.jsp";// 跳转路径
			String orderid = order_id;
			String orderuid = "usr.getId()";// 订购者id
			String goodsname = "押金";
			String key = MD5Util.encodePassword(
					goodsname + istype + notify_url + orderid + orderuid + price + return_url + token + uid);// 密匙
			request.setAttribute("uid", uid);
			request.setAttribute("istype", istype);
			request.setAttribute("price", price);
			request.setAttribute("notify_url", notify_url);
			request.setAttribute("return_url", return_url);
			request.setAttribute("orderid", orderid);
			request.setAttribute("orderuid", orderuid);
			request.setAttribute("goodsname", goodsname);
			request.setAttribute("key", key);
			request.getRequestDispatcher("jsp/order/pay.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("AddOrderServelet").forward(request, response);
		}

	}

	public String type(char type) {
		String ordertype = null;
		switch (type) {
		case '1': {
			ordertype = "快递外卖";
			break;
		}

		case '2': {
			ordertype = "服务外包";
			break;
		}
		case '3': {
			ordertype = "兼职";
			break;
		}
		case '4': {
			ordertype = "其他";
		}
		}
		return ordertype;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
