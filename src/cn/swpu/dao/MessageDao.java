package cn.swpu.dao;

import java.util.List;

import cn.swpu.entity.Message;

public interface MessageDao {
	public void saveMessage(Message message);
	public void deleteMessage(Message message);
	public List<Message> queryMessageByUserId(int id);
}
