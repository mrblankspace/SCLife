package cn.swpu.service.impl;

import java.util.List;

import cn.swpu.dao.UserDao;
import cn.swpu.dao.impl.UserDaoImpl;
import cn.swpu.entity.User;
import cn.swpu.service.AdminService;

public class AdminServiceImpl implements AdminService{
	UserDao UserDao = new UserDaoImpl();

	@Override
	public List<User> findAllUser() {
		// TODO Auto-generated method stub
		return UserDao.queryAllUsers();
	}
	

	/**
	 * 模糊查询相关用户
	 * kong8.1
	 */
	
	public List<User> queryByUsername(String nature)
	{
		return UserDao.queryByUsername(nature);
	}
	public List<User> queryByAddress(String nature)
	{
		return UserDao.queryByAddress(nature);
	}
	public List<User> queryByEmail(String nature)
	{
		return UserDao.queryByEmail(nature); 
		
	}
	
	/**
	 * kong8.1
	 */
	public User findById(int id)
	{
		return UserDao.findById(id);
	}
	
}
