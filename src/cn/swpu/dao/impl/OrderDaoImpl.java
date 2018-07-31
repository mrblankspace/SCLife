package cn.swpu.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.swpu.dao.OrderDao;
import cn.swpu.dao.UserDao;
import cn.swpu.entity.Order;
import cn.swpu.entity.User;
import cn.swpu.util.DbUtil;

/*
 * (non-Javadoc) 7.29修改    
 * @see cn.swpu.dao.OrderDao#showOrder()
 */
public class OrderDaoImpl implements OrderDao {
	private UserDao userDao = new UserDaoImpl();

	//7.30新增通过id获取订单对象
		public Order getOrderById(int id)
		{
			DbUtil du=new DbUtil();
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			Order order=new Order();
			try {
				Connection con=du.getCon();
				String sql="select * from SCLife.order where order_id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, id);
				rs=pstmt.executeQuery();
			
				while(rs.next())
				{
					order.setOrder_id(rs.getInt("order_id"));
					User sender = userDao.findById(rs.getInt("send_id"));
					order.setSend_person(sender);
					if (rs.getInt("accept_id")!=0) {
						User accepter = userDao.findById(rs.getInt("send_id"));
						order.setSend_person(accepter);
					}
					order.setCatagory(rs.getString("catagory"));
					order.setDescribe(rs.getString("order_describe"));
					order.setOrder_money(rs.getFloat("order_money"));
					order.setOrder_status(rs.getString("order_status"));
					order.setOrder_date(rs.getString("order_date"));
					order.setFinish_date(rs.getString("finish_date"));
				}
				du.closeCon(con);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					pstmt.close();
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			return order;
	}
	public List<Order> showOrder()
	{
		List<Order> list=new ArrayList<Order>();//创建User集合保存遍历出的User对象们
		DbUtil du=new DbUtil();
		Statement stmt=null;
		ResultSet rs=null;
		try {
			Connection con=du.getCon();
			stmt=con.createStatement();
			String sql="select * from SCLife.order";
			rs=stmt.executeQuery(sql);//返回结果
			while(rs.next())//当rs.next()有结果循环给List添加进User对象
			{
				Order order=new Order();
				order.setOrder_id(rs.getInt("order_id"));
				User sender = userDao.findById(rs.getInt("send_id"));
				order.setSend_person(sender);
				if (rs.getInt("accept_id")!=0) {
					User accepter = userDao.findById(rs.getInt("send_id"));
					order.setSend_person(accepter);
				}
				order.setCatagory(rs.getString("catagory"));
				order.setDescribe(rs.getString("order_describe"));
				order.setOrder_money(rs.getFloat("order_money"));
				order.setOrder_status(rs.getString("order_status"));
				order.setOrder_date(rs.getString("order_date"));
				order.setFinish_date(rs.getString("finish_date"));
				list.add(order);
			}
			
			du.closeCon(con);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public int addOrder(Order order)
	{
		
		DbUtil du=new DbUtil();
		PreparedStatement pstmt=null;
		
		int row=0;
		try {
			Connection con=du.getCon();
			/**
			 * 最后三个参数默认设置为空，等待用户接单后重新update
			 */
			String sql="insert into SCLife.order (catagory,order_describe,order_money,send_id,order_status,order_date) values (?,?,?,?,?,?)";	  
			
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, order.getCatagory());
			pstmt.setString(2, order.getDescribe());
			pstmt.setFloat(3, order.getOrder_money());
			pstmt.setInt(4, order.getSend_person().getId());
			pstmt.setString(5, order.getOrder_status());
			pstmt.setString(6, order.getOrder_date());
			
						
			row=pstmt.executeUpdate();
			
			du.closeCon(con);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return row;
		
	}
	
	
	public int deleteOrder(Order order)
	{
		DbUtil du=new DbUtil();
		PreparedStatement pstmt=null;
		int row=0;
		
		try {
			Connection con=du.getCon();
			String sql="delete from order where id=?";//通过传入用户的id删除
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,order.getOrder_id());
			
			row=pstmt.executeUpdate();//接受返回受影响条数
			
			du.closeCon(con);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	
	/*public int updateOrder(Order order)
	{
		DbUtil du=new DbUtil();
		PreparedStatement pstmt=null;
		int row=0;
		try {
			Connection con=du.getCon();
			String sql="update user set catagory_id=?,order_describe=?,order_money=?,send_id=?,order_date=?,accept_id=?,order_status=?，finish_date=? where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, order.getOrder_id());
			pstmt.setString(2, order.getDescribe());
			pstmt.setFloat(3, order.getOrder_money());
		//	pstmt.setInt(4, order.getSend_id());
			pstmt.setString(5, order.getOrder_date());
			pstmt.setString(6, order.getFinish_date());
			pstmt.setString(7, order.getOrder_status());
			pstmt.setString(8, order.getFinish_date());
			pstmt.setInt(9, order.getOrder_id());
			
			pstmt.executeUpdate();
			
			du.closeCon(con);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return row;
	}
*/
	public int updateOrder(Order order)
	{
		DbUtil du=new DbUtil();
		PreparedStatement pstmt=null;
		int row=0;
		try {
			Connection con=du.getCon();
			String sql="update `order` set catagory=?,order_describe=?,order_money=?,send_id=?,order_date=?,accept_id=?,order_status=?,finish_date=? where order_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, order.getCatagory());
			pstmt.setString(2, order.getDescribe());
			pstmt.setFloat(3, order.getOrder_money());
			int send_id=order.getSend_person().getId();//获取发单人的id
			pstmt.setInt(4, send_id);
			pstmt.setString(5, order.getOrder_date());
			int accept_id=order.getAccept_person().getId();//获取接单人的id
			pstmt.setInt(6, accept_id);
			pstmt.setString(7, order.getOrder_status());
			pstmt.setString(8, order.getFinish_date());
			pstmt.setInt(9, order.getOrder_id());
			
			row=pstmt.executeUpdate();
			
			du.closeCon(con);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return row;
	}

	@Override
	public List<Order> findWaimaiOrder() {
		// TODO Auto-generated method stub
		return findOrderByCondition("快递 外卖");
	}

	@Override
	public List<Order> findOtherOrder() {
		// TODO Auto-generated method stub
		return findOrderByCondition("其他");
	}

	@Override
	public List<Order> findJobOrder() {
		// TODO Auto-generated method stub
		return findOrderByCondition("兼职");
	}
	
	public List<Order> findOrderByCondition(String requirement){
		List<Order> list=new ArrayList<Order>();//创建User集合保存遍历出的User对象们
		DbUtil du=new DbUtil();
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			Connection con=du.getCon();
			stmt=con.prepareStatement("select * from SCLife.order where catagory=? and order_status=?");
			stmt.setString(1, requirement);
			stmt.setString(2, "未完成");
			rs=stmt.executeQuery();//返回结果
			while(rs.next())//当rs.next()有结果循环给List添加进User对象
			{
				Order order=new Order();
				order.setOrder_id(rs.getInt("order_id"));
				User sender = userDao.findById(rs.getInt("send_id"));
				order.setSend_person(sender);
				if (rs.getInt("accept_id")!=0) {
					User accepter = userDao.findById(rs.getInt("send_id"));
					order.setSend_person(accepter);
				}
				order.setCatagory(rs.getString("catagory"));
				order.setDescribe(rs.getString("order_describe"));
				order.setOrder_money(rs.getFloat("order_money"));
				order.setOrder_status(rs.getString("order_status"));
				order.setOrder_date(rs.getString("order_date"));
				order.setFinish_date(rs.getString("finish_date"));
				list.add(order);
			}
			
			du.closeCon(con);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;

	}

	@Override
	public List<Order> findWaibaoOrder() {
		// TODO Auto-generated method stub
		return findOrderByCondition("服务外包");
	}
	
	/**
	 * 以下方法用于实现通过订单的不同属性查询订单
	 * kong
	 */
	
	public List<Order> queryByNature(String nature,String sql)
	{
		List<Order> list=new ArrayList<Order>();//创建User集合保存遍历出的User对象们
		DbUtil du=new DbUtil();
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			Connection con=du.getCon();
			stmt=con.prepareStatement(sql);
			stmt.setString(1,"%"+nature+"%");
			rs=stmt.executeQuery();//返回结果
			
			while(rs.next())//当rs.next()有结果循环给List添加进list对象
			{
				Order order=new Order();
				order.setOrder_id(rs.getInt("order_id"));
				User sender = userDao.findById(rs.getInt("send_id"));
				order.setSend_person(sender);
				if (rs.getInt("accept_id")!=0) {
					User accepter = userDao.findById(rs.getInt("send_id"));
					order.setSend_person(accepter);
				}
				order.setCatagory(rs.getString("catagory"));
				order.setDescribe(rs.getString("order_describe"));
				order.setOrder_money(rs.getFloat("order_money"));
				order.setOrder_status(rs.getString("order_status"));
				order.setOrder_date(rs.getString("order_date"));
				order.setFinish_date(rs.getString("finish_date"));
				list.add(order);
			}
			
			du.closeCon(con);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return list;
		
	}
	
	public List<Order> queryByOrderType(String nature)
	{
		String sql="select * from SCLife.order where catagory like ?";
		return queryByNature(nature, sql);
	}
	
	public List<Order> queryByOrderStatus(String nature)
	{
		String sql="select * from SCLife.order where order_status like ?";
		return queryByNature(nature, sql);
	}
	
	public List<Order> queryByOrdertime(String nature)
	{
		String sql="select * from SCLife.order where order_date like ?";
		return queryByNature(nature, sql);
	}
	public List<Order> queryByOrderReward(String nature)
	{
		
		List<Order> list=new ArrayList<Order>();//创建User集合保存遍历出的User对象们
		DbUtil du=new DbUtil();
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			Connection con=du.getCon();
			String sql="select * from SCLife.order where order_money >= ?";
			stmt=con.prepareStatement(sql);
			stmt.setString(1,nature);
			rs=stmt.executeQuery();//返回结果
			
			while(rs.next())//当rs.next()有结果循环给List添加进list对象
			{
				Order order=new Order();
				order.setOrder_id(rs.getInt("order_id"));
				User sender = userDao.findById(rs.getInt("send_id"));
				order.setSend_person(sender);
				if (rs.getInt("accept_id")!=0) {
					User accepter = userDao.findById(rs.getInt("send_id"));
					order.setSend_person(accepter);
				}
				order.setCatagory(rs.getString("catagory"));
				order.setDescribe(rs.getString("order_describe"));
				order.setOrder_money(rs.getFloat("order_money"));
				order.setOrder_status(rs.getString("order_status"));
				order.setOrder_date(rs.getString("order_date"));
				order.setFinish_date(rs.getString("finish_date"));
				list.add(order);
			}
			
			du.closeCon(con);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return list;
	}
}

























