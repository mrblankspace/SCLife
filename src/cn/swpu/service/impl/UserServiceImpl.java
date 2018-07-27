package cn.swpu.service.impl;

import java.util.List;

import cn.swpu.dao.UserDao;
import cn.swpu.entity.User;
import cn.swpu.service.UserService;

/**
 * ���ܣ�����UserDao��ʵ��UserService�ӿ�
 * @author Administrator
 *
 */
public class UserServiceImpl implements UserService{
	private UserDao userDao;
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
}
