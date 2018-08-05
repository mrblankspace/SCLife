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
	 * 功能:根据servlet传入的User对象进行数据库比对并返回新User对象，新User对象存有查询到的信息
	 */	
	public User login(User user)
	{
		User daoUser=new User();
		DbUtil du=new DbUtil();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Connection con=null;
		try {
		    con=du.getCon();
			String sql="select * from user where email=? and password=?";//传入邮箱和密码
			pstmt=con.prepareStatement(sql);//先编译下一句接受参数
			pstmt.setString(1, user.getEmail());//获取由
			pstmt.setString(2, user.getPassword());
			
			rs=pstmt.executeQuery();//使用executeQuery() 返回ResultSet 对象
			while(rs.next())
			{
				daoUser.setId(rs.getInt("id"));
				daoUser.setUsername(rs.getString("username"));
				daoUser.setPassword(rs.getString("password"));
				daoUser.setAddress(rs.getString("address"));
				daoUser.setIdentityId(rs.getInt("identityId"));
				daoUser.setEmail(rs.getString("email"));
				daoUser.setTel(rs.getString("tel"));
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				du.closeCon(con);//????
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return daoUser;
	}
	
	/**
	 * 功能：根据传入的User对象进行注册，返回数据库内表受影响行数，0==失败 1==成功
	 */
	public int register(User user)
	{

		DbUtil du=new DbUtil();	
		PreparedStatement pstmt = null;
		
		int row=0;//初始化为0
		
		try {
			Connection con=du.getCon();
			String regName=user.getUsername();
			String regMail=user.getEmail();
			String regAddress=user.getAddress();
			String regPwd=user.getPassword();
			String regTel=user.getTel();
			
			/**
			 * 待讨论部分
			 */
			int regIdenID=user.getIdentityId();//用户身份不能由用户选择
			
			String sql="insert into user (username,password,email,address,tel,identityId) values (?,?,?,?,?,'0')";//对应数据库字段，ID为自增量???user_number是否赋值，identityID是否为选择
			pstmt=con.prepareStatement(sql);		
			pstmt.setString(1, regName);//给sql语句赋值
			pstmt.setString(2, regPwd);
			pstmt.setString(3, regMail);
			pstmt.setString(4, regAddress);
			pstmt.setString(5, regTel);
			/**
			 * 注册时可以默认给用户identitID赋值为“用户”
			 */
			row=pstmt.executeUpdate();//使用executeUpdate() 返回受影响行数
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
		
		return row;//返回受影响行数 1==注册成功
	}
	
	/**
	 * 功能：查询数据库内所有用户信息，并返回一个<User>集合
	 */
	public List<User> queryAllUsers()
	{
		List<User> list=new ArrayList<User>();//创建User集合保存遍历出的User对象们
		DbUtil du=new DbUtil();
		Statement stmt=null;
		ResultSet rs=null;
		Connection con=null;
		try {
			con=du.getCon();
			stmt=con.createStatement();
			String sql="select * from user";
			rs=stmt.executeQuery(sql);//返回结果
			while(rs.next())//当rs.next()有结果循环给List添加进User对象
			{
				User user=new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setAddress(rs.getString("address"));
				user.setIdentityId(rs.getInt("identityId"));
				user.setEmail(rs.getString("email"));
				user.setTel(rs.getString("tel"));
				list.add(user);//给list添加User对象
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				stmt.close();
				du.closeCon(con);//?????
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	/**
	 * 功能：删除传入的用户
	 */
	public int deleteUser(User user)
	{
		DbUtil du=new DbUtil();
		PreparedStatement pstmt=null;
		int row=0;
		Connection con= null;
		try {
			int delId=user.getId();
			con=du.getCon();
			String sql="delete from user where id=?";//通过传入用户的id删除
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, delId);
			
			row=pstmt.executeUpdate();//接受返回受影响条数
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				du.closeCon(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	
	/**
	 * 功能：更新用户信息，根据传入的User对象
	 * 待讨论部分，这个方法提供了修改管理员才能修改内容的权限
	 */
	public int updateUser(User user)
	{
		DbUtil du=new DbUtil();
		PreparedStatement pstmt=null;
		int row=0;
		try {
			Connection con=du.getCon();
			
			int getId=user.getId();//获取用户id，判断依据
			
			String updName=user.getUsername();
			String updMail=user.getEmail();
			String updAddress=user.getAddress();
			String updPwd=user.getPassword();
			String updTel=user.getTel();
			int updIdenId=user.getIdentityId();//
			String sql="update user set username=?,password=?,address=?,identityId=?,email=?,tel=? where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, updName);
			pstmt.setString(2, updPwd);
			pstmt.setString(3, updAddress);
			pstmt.setInt(4, updIdenId);//可能删去,讨论部分，管理员可以提升用户权限，或者写成两个update，一个用户只能修改基础信息，一个提供给管理员修改所有信息
			pstmt.setString(5, updMail);
			pstmt.setString(6, updTel);
			pstmt.setInt(7, getId);
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
	public User findById(int id) {
		User daoUser=new User();
		DbUtil du=new DbUtil();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Connection con = null;
		try {
			con = du.getCon();
			String sql="select * from user where id=?";//传入邮箱和密码
			pstmt=con.prepareStatement(sql);//先编译下一句接受参数
			pstmt.setLong(1, id);//获取由

			
			rs=pstmt.executeQuery();//使用executeQuery() 返回ResultSet 对象
			while(rs.next())
			{
				daoUser.setId(rs.getInt("id"));
				daoUser.setUsername(rs.getString("username"));
				daoUser.setPassword(rs.getString("password"));
				daoUser.setAddress(rs.getString("address"));
				daoUser.setIdentityId(rs.getInt("identityId"));
				daoUser.setEmail(rs.getString("email"));
				daoUser.setTel(rs.getString("tel"));
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				du.closeCon(con);//????
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return daoUser;
	}
	
	/**
	 * 可以实现：关键字查找,
	 */
	/**
	 * 实现通过用户id获取用户的所用信息
	 */
	public User queryUserById(User user)
	{
		User daoUser=new User();
		DbUtil du=new DbUtil();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Connection con= null;
		try {
			 con=du.getCon();
			String sql="select * from user where id=? ";//传入邮箱和密码
			pstmt=con.prepareStatement(sql);//先编译下一句接受参数
			pstmt.setInt(1, user.getId());
			
			rs=pstmt.executeQuery();//使用executeQuery() 返回ResultSet 对象
			while(rs.next())
			{
				daoUser.setId(rs.getInt("id"));
				daoUser.setUsername(rs.getString("username"));
				daoUser.setPassword(rs.getString("password"));
				daoUser.setAddress(rs.getString("address"));
				daoUser.setIdentityId(rs.getInt("identityId"));
				daoUser.setEmail(rs.getString("email"));
				daoUser.setTel(rs.getString("tel"));
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				du.closeCon(con);//????
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return daoUser;
	}
	
	/**
	 * 模糊查询相关用户
	 * kong8.1
	 */
	public List<User> queryByNature(String nature,String sql)
	{
		List<User> list=new ArrayList<User>();//创建User集合保存遍历出的User对象们
		DbUtil du=new DbUtil();
		PreparedStatement stmt=null;
		ResultSet rs=null;
		Connection con=null;
		try {
			con=du.getCon();
			stmt=con.prepareStatement(sql);
			stmt.setString(1,"%"+nature+"%");
			rs=stmt.executeQuery();//返回结果
			
			while(rs.next())//当rs.next()有结果循环给List添加进list对象
			{
				User user=new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setAddress(rs.getString("address"));
				user.setTel(rs.getString("tel"));
				user.setIdentityId(rs.getInt("identityId"));
				list.add(user);
			}
			
		
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
				du.closeCon(con);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return list;
		
	}
	
	public List<User> queryByUsername(String nature)
	{
		String sql="select * from SCLife.user where username like ?";
		return queryByNature(nature, sql);
	}
	
	public List<User> queryByAddress(String nature)
	{
		String sql="select * from SCLife.user where address like ?";
		return queryByNature(nature, sql);
	}
	
	public List<User> queryByEmail(String nature)
	{
		String sql="select * from SCLife.user where email like ?";
		return queryByNature(nature, sql);
	}
	
	
}
