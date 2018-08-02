package cn.swpu.service;

import java.util.List;

import cn.swpu.entity.User;

public interface AdminService {
	public List<User> findAllUser();
	/**
	 * 模糊查询相关用户
	 * kong
	 */
	public List<User> queryByUsername(String nature);
	public List<User> queryByAddress(String nature);
	public List<User> queryByEmail(String nature);
	
	/**
	 * kong8.1
	 */
	public User findById(int id);
	
	
}
