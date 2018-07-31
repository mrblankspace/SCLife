package cn.swpu.service.impl;

import java.util.List;

import cn.swpu.dao.OrderDao;
import cn.swpu.dao.UserDao;
import cn.swpu.dao.impl.OrderDaoImpl;
import cn.swpu.dao.impl.UserDaoImpl;
import cn.swpu.entity.Order;
import cn.swpu.service.OrderService;

public class OrderServiceImpl implements OrderService {
	private OrderDao od = new OrderDaoImpl();

	public List<Order> ShowOrder()
	{
		return od.showOrder();
	}
	public int AddOrder(Order order)
	{
		return od.addOrder(order);
	}
	public int DeleteOrder(Order order)
	{
		return od.deleteOrder(order);
	}
	public int UpdateOrder(Order order)
	{
		return od.updateOrder(order);
	}
	public Order GetOrderById(int id)
	{
		return od.getOrderById(id);
	}
	@Override
	public List<Order> findWaimaiOrder() {
		// TODO Auto-generated method stub
		return od.findWaimaiOrder();
	}
	@Override
	public List<Order> findOtherOrder() {
		// TODO Auto-generated method stub
		return od.findOtherOrder() ;
	}
	@Override
	public List<Order> findJobOrder() {
		// TODO Auto-generated method stub
		return od.findJobOrder();
	}
	@Override
	public List<Order> findWaibaoOrder() {
		// TODO Auto-generated method stub
		return od.findWaibaoOrder();
	}
	
	
	/**
	 * 孔绍坤添加
	 * @param nature
	 * @return
	 */
	public List<Order> queryByOrderType(String nature)
	{
		return od.queryByOrderType(nature);
	}
	public List<Order> queryByOrderStatus(String nature)
	{
		return od.queryByOrderStatus(nature);
	}
	
	public List<Order> queryByOrdertime(String nature)
	{
		return od.queryByOrdertime(nature);
	}
	public List<Order> queryByOrderReward(String nature)
	{
		return od.queryByOrderReward(nature);
	}
}
