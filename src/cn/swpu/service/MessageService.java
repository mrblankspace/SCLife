package cn.swpu.service;

import java.util.List;

import cn.swpu.entity.Message;

public interface MessageService {
	public void saveMessage(Message message);
	public void deleteMessage(Message message);
	public List<Message> queryMessageByUserId(int id);
	public void readMessage(int messageId);

}
