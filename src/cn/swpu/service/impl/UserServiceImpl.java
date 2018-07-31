package cn.swpu.service.impl;

import java.util.List;

import cn.swpu.dao.UserDao;
import cn.swpu.dao.impl.UserDaoImpl;
import cn.swpu.entity.User;
import cn.swpu.service.UserService;

/**
 * 功能：调用UserDao类实现UserService接口
 * @author Administrator
 *
 */
public class UserServiceImpl implements UserService{
	private UserDao userDao = new UserDaoImpl();
	@Override
	public User Login(User user) 
	{
		return userDao.login(user);
	}
	
	public int Register(User user)
	{
		return userDao.register(user);
	}
	
	public List<User> QueryAllUsers()
	{
		return userDao.queryAllUsers();
	}
	
	public int DeleteUser(User user)
	{
		return userDao.deleteUser(user);
	}
	
	public int UpdateUser(User user)
	{
		return userDao.updateUser(user);
	}
	public User queryUserById(User user)
	{
		return userDao.queryUserById(user);
	}
}
