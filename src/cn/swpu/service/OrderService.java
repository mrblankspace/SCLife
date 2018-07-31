package cn.swpu.service;

import java.util.List;

import cn.swpu.entity.Order;

public interface OrderService {
	public List<Order> ShowOrder();
	public int AddOrder(Order order);
	public int DeleteOrder(Order order);
	public int UpdateOrder(Order order);
	public List<Order> findWaimaiOrder();
	public List<Order> findOtherOrder();

	public List<Order> findJobOrder();
	public List<Order> findWaibaoOrder();
	
	public Order GetOrderById(int id);
	public List<Order> queryByOrderType(String nature);
	public List<Order> queryByOrderStatus(String nature);
	public List<Order> queryByOrdertime(String nature);
	public List<Order> queryByOrderReward(String nature);
	

}
