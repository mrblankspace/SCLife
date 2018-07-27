package cn.swpu.dao;

import java.util.List;

import cn.swpu.entity.User;

public interface UserDao {
	public User login(User user);//��¼�ӿ�
	public int register(User user);//ע��ӿ�/�����û��ӿ�
	public List<User> queryAllUsers();//��ѯ�����û��ӿ�
	public int deleteUser(User user);//ɾ���û��ӿ�
	public int updateUser(User user);//�����û��ӿ�
}
