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
	
}
