package cn.swpu.dao;

import java.util.List;

import cn.swpu.entity.Order;
import cn.swpu.entity.User;

public interface OrderDao {
	public List<Order> showOrder();
	public int addOrder(Order order) ;
	public int deleteOrder(Order order);
	public int updateOrder(Order order);
	public List<Order> findWaimaiOrder();
	public List<Order> findOtherOrder();
	public List<Order> findJobOrder();
	public List<Order> findWaibaoOrder();
	public Order getOrderById(String id);
	public int updateStatus(Order order);
	/**
	 * 孔绍坤添加
	 * @param nature
	 * @return
	 */
	public List<Order> queryByOrderType(String nature);
	public List<Order> queryByOrderStatus(String nature);
	public List<Order> queryByOrdertime(String nature);
	public List<Order> queryByOrderReward(String nature);
	
	
	public List<Order> showMyAcpOrder(User user);
	public List<Order> showMySendOrder(User user);
	
	/*
	 * 
	 * 8.3 李成洪添加
	 */
	public String AutogetOrder_id();
	
}
