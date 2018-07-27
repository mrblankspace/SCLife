package cn.swpu.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.swpu.dao.UserDao;
import cn.swpu.entity.User;
import cn.swpu.util.DbUtil;

public class UserDaoImpl implements UserDao{
	
	/**
	 * ����:����servlet�����User����������ݿ�ȶԲ�������User������User������в�ѯ������Ϣ
	 */
	public User login(User user)
	{
		User daoUser=new User();
		DbUtil du=new DbUtil();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			Connection con=du.getCon();
			String sql="select * from user where email=? and password=?";//�������������
			pstmt=con.prepareStatement(sql);//�ȱ�����һ����ܲ���
			pstmt.setString(1, user.getEmail());//��ȡ��
			pstmt.setString(2, user.getPassword());
			
			rs=pstmt.executeQuery();//ʹ��executeQuery() ����ResultSet ����
			while(rs.next())
			{
				daoUser.setId(rs.getInt("id"));
				daoUser.setUsername(rs.getString("username"));
				daoUser.setPassword(rs.getString("password"));
				daoUser.setAddress(rs.getString("address"));
				daoUser.setIdentityId(rs.getInt("identityId"));
				daoUser.setEmail(rs.getString("email"));
				daoUser.setOrder_num(rs.getInt("order_num"));
				daoUser.setTel(rs.getString("tel"));
			}
			du.closeCon(con);//????
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return daoUser;
	}
	
	/**
	 * ���ܣ����ݴ����User�������ע�ᣬ�������ݿ��ڱ���Ӱ��������0==ʧ�� 1==�ɹ�
	 */
	public int register(User user)
	{
		DbUtil du=new DbUtil();
		PreparedStatement pstmt=null;
		int row=0;//��ʼ��Ϊ0
		
		try {
			Connection con=du.getCon();
			String regName=user.getUsername();
			String regMail=user.getEmail();
			String regAddress=user.getAddress();
			String regPwd=user.getPassword();
			String regTel=user.getTel();
			
			/**
			 * �����۲���
			 */
			int regOrderNum=user.getOrder_num();//��������ʼ��ֵΪ0������
			int regIdenID=user.getIdentityId();//�û���ݲ������û�ѡ��
			
			String sql="insert into user (username,password,email,address,tel) values(?,?,?,?,?)";//��Ӧ���ݿ��ֶΣ�IDΪ������???order_number�Ƿ�ֵ��identityID�Ƿ�Ϊѡ��
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, regName);//��sql��丳ֵ
			pstmt.setString(2, regPwd);
			pstmt.setString(3, regMail);
			pstmt.setString(4, regAddress);
			pstmt.setString(5, regTel);
			/**
			 * ע��ʱ����Ĭ�ϸ��û�identitID��ֵΪ���û���
			 */
			row=pstmt.executeUpdate();//ʹ��executeUpdate() ������Ӱ������
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
		
		return row;//������Ӱ������ 1==ע��ɹ�
	}
	
	/**
	 * ���ܣ���ѯ���ݿ��������û���Ϣ��������һ��<User>����
	 */
	public List<User> queryAllUsers()
	{
		List<User> list=new ArrayList<User>();//����User���ϱ����������User������
		DbUtil du=new DbUtil();
		Statement stmt=null;
		ResultSet rs=null;
		try {
			Connection con=du.getCon();
			stmt=con.createStatement();
			String sql="select * from user";
			rs=stmt.executeQuery(sql);//���ؽ��
			while(rs.next())//��rs.next()�н��ѭ����List��ӽ�User����
			{
				User user=new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setAddress(rs.getString("address"));
				user.setIdentityId(rs.getInt("balance"));
				user.setEmail(rs.getString("email"));
				user.setOrder_num(rs.getInt("order_num"));
				user.setTel(rs.getString("tel"));
				list.add(user);//��list���User����
			}
			du.closeCon(con);//?????
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	/**
	 * ���ܣ�ɾ��������û�
	 */
	public int deleteUser(User user)
	{
		DbUtil du=new DbUtil();
		PreparedStatement pstmt=null;
		int row=0;
		try {
			int delId=user.getId();
			Connection con=du.getCon();
			String sql="delete from user where id=?";//ͨ�������û���idɾ��
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, delId);
			
			row=pstmt.executeUpdate();//���ܷ�����Ӱ������
			
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
	
	/**
	 * ���ܣ������û���Ϣ�����ݴ����User����
	 * �����۲��֣���������ṩ���޸Ĺ���Ա�����޸����ݵ�Ȩ��
	 */
	public int updateUser(User user)
	{
		DbUtil du=new DbUtil();
		PreparedStatement pstmt=null;
		int row=0;
		try {
			Connection con=du.getCon();
			
			int getId=user.getId();//��ȡ�û�id���ж�����
			
			String updName=user.getUsername();
			String updMail=user.getEmail();
			String updAddress=user.getAddress();
			String updPwd=user.getPassword();
			String updTel=user.getTel();
			int updOrderNum=user.getOrder_num();
			int updIdenId=user.getIdentityId();//
			String sql="update user set username=?,password=?,address=?,identityId=?,email=?,order_num=��,tel=? where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, updName);
			pstmt.setString(2, updPwd);
			pstmt.setString(3, updAddress);
			pstmt.setInt(4, updIdenId);//����ɾȥ,���۲��֣�����Ա���������û�Ȩ�ޣ�����д������update��һ���û�ֻ���޸Ļ�����Ϣ��һ���ṩ������Ա�޸�������Ϣ
			pstmt.setString(5, updMail);
			pstmt.setInt(6, updOrderNum);
			pstmt.setString(7, updTel);
			pstmt.setInt(8, getId);
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
	
	/**
	 * ����ʵ�֣��ؼ��ֲ���,
	 */
}
