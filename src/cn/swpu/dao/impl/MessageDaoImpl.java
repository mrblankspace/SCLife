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
	//private Connection connection;
	//private PreparedStatement preparedStatement;
	private UserDao userDao = new UserDaoImpl();
	
	/**
	 * 查询用户未读消息
	 */
	public List<Message> queryMessageByUserId(int id) {
		// TODO Auto-generated method stub
		ArrayList<Message> list = new ArrayList<Message>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
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
				message.setFrom_person(userDao.findById(rs.getInt("from_person_id")));
				list.add(message);
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	public void saveMessage(Message message) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dbUtil.getCon();
			String sql = "insert into message (content, to_person_id, status, date, from_person_id) values(?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, message.getContent());
			preparedStatement.setInt(2, message.getTo_person().getId());
			preparedStatement.setString(3, message.getStatus());
			preparedStatement.setString(4, message.getDate());
			preparedStatement.setInt(5, message.getFrom_person().getId());
			preparedStatement.execute();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void deleteMessage(Message message) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
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
			e.printStackTrace();
		}finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void readMessage(int messageId) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dbUtil.getCon();
			String sql = "update message set status='已读' where id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, messageId);
			preparedStatement.execute();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
