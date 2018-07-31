package cn.swpu.service;

import java.util.List;

import cn.swpu.entity.User;

/**
 * UserService�ӿ�
 * @author Administrator
 *
 */
public interface UserService {
	public User Login(User user);
	public int Register(User user);
	public List<User> QueryAllUsers();
	public int DeleteUser(User user);
	public int UpdateUser(User user);
	public User queryUserById(User user);
}
