package cn.swpu.dao;

import java.util.List;

import cn.swpu.entity.Order;

public interface OrderDao {
	public List<Order> showOrder();
	public int addOrder(Order order);
	public int deleteOrder(Order order);
	public int updateOrder(Order order);
	public List<Order> findWaimaiOrder();
	public List<Order> findOtherOrder();
	public List<Order> findJobOrder();
	public List<Order> findWaibaoOrder();
	public Order getOrderById(int id);
	
	/**
	 * 孔绍坤添加
	 * @param nature
	 * @return
	 */
	public List<Order> queryByOrderType(String nature);
	public List<Order> queryByOrderStatus(String nature);
	public List<Order> queryByOrdertime(String nature);
	public List<Order> queryByOrderReward(String nature);
}
