package cn.swpu.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.swpu.dao.MessageDao;
import cn.swpu.dao.UserDao;
import cn.swpu.entity.Message;
import cn.swpu.util.DbUtil;

public class MessageDaoImpl implements MessageDao{
	private DbUtil dbUtil = new DbUtil();
	private Connection connection;
	private PreparedStatement preparedStatement;
	private UserDao userDao = new UserDaoImpl();
	
	
	/**
	 * 查询用户未读消息
	 */
	public List<Message> queryMessageByUserId(int id) {
		// TODO Auto-generated method stub
		ArrayList<Message> list = new ArrayList<Message>();
		try {
			connection = dbUtil.getCon();
			String sql = "select *  from message where to_person_id=? and status=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,id);
			preparedStatement.setString(2,"未读");
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Message message = new Message();
				message.setId(rs.getInt("id"));
				message.setDate(rs.getString("date"));
				message.setContent(rs.getString("content"));
				message.setStatus(rs.getString("status"));
				message.setTo_person(userDao.findById(rs.getInt("to_person_id")));
				list.add(message);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public void saveMessage(Message message) {
		// TODO Auto-generated method stub
		try {
			connection = dbUtil.getCon();
			String sql = "insert into message (content, to_person_id, status, date) values(?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, message.getContent());
			preparedStatement.setInt(2, message.getTo_person().getId());
			preparedStatement.setString(3, message.getStatus());
			preparedStatement.setString(4, message.getDate());
			preparedStatement.execute();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				connection.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				try {
					connection.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				e1.printStackTrace();
			}
		}
	}

	public void deleteMessage(Message message) {
		// TODO Auto-generated method stub
		try {
			connection = dbUtil.getCon();
			String sql = "delete from message where id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, message.getId());
			preparedStatement.execute();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connection.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
	}
	
}
