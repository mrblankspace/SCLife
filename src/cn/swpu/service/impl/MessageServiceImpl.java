package cn.swpu.service.impl;

import java.util.List;

import cn.swpu.dao.MessageDao;
import cn.swpu.dao.impl.MessageDaoImpl;
import cn.swpu.entity.Message;
import cn.swpu.service.MessageService;

public class MessageServiceImpl implements MessageService{
	private MessageDao messageDao = new MessageDaoImpl();
	@Override
	public void saveMessage(Message message) {
		// TODO Auto-generated method stub
		messageDao.saveMessage(message);
	}

	@Override
	public void deleteMessage(Message message) {
		// TODO Auto-generated method stub
		messageDao.deleteMessage(message);
	}

	@Override
	public List<Message> queryMessageByUserId(int id) {
		// TODO Auto-generated method stub
		return messageDao.queryMessageByUserId(id);
	}
	
}