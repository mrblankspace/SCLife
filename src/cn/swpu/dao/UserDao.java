package cn.swpu.dao;

import java.util.List;

import cn.swpu.entity.User;

public interface UserDao {
	public User login(User user);//登录接口
	public int register(User user);//注册接口/增加用户接口
	public List<User> queryAllUsers();//查询所有用户接口
	public int deleteUser(User user);//删除用户接口
	public int updateUser(User user);//增加用户接口
}
