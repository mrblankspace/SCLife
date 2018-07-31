package cn.swpu.web;

import java.io.IOException;
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
		response.setCharacterEncoding("UTF-8");		
		Order or=(Order)request.getSession().getAttribute("order");
		OrderDaoImpl odimpl=new   OrderDaoImpl(); 
		odimpl.addOrder(or);
		
	}

}
