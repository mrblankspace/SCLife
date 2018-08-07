package cn.swpu.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.swpu.dao.impl.OrderDaoImpl;
import cn.swpu.entity.Order;

/**
 * Servlet implementation class AddOrderServelet
 */
@WebServlet("/AddOrderServelet")
public class AddOrderServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddOrderServelet() {
        super();
        
    }
       protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException {
		request.setCharacterEncoding("UTF-8");			
		Order or=(Order)request.getSession().getAttribute("order");
		String order_id=or.getOrder_id();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.print("<script language='javascript'>alert('有消息');");
		System.out.println(order_id);
		OrderDaoImpl odimpl=new   OrderDaoImpl(); 
		odimpl.addOrder(or);
		response.sendRedirect("http://zb.mrblankspace.cn/SCLife");	
	}

}
