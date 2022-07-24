package tn.esprit.wellbeing.modules.inbox.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.wellbeing.modules.inbox.InboxService;
import tn.esprit.wellbeing.modules.inbox.data.Message;
import tn.esprit.wellbeing.modules.inbox.data.MessageRepository;
import tn.esprit.wellbeing.modules.inbox.data.MessageStatus;

@Service
public class InboxServiceImpl implements InboxService {

	@Autowired
	private MessageRepository repo;

	@Override
	public void sendMessage(Long userId, String body) {
		Message message = new Message();
		message.setBody(body);
		message.setUserId(userId);
		message.setStatus(MessageStatus.Created);
		message = repo.save(message);
		sendMessage(message);
	}

	@Override
	public void sendMessage(Message message) {
		message = repo.save(message);
		// send message in MQ
	}

	@Override
	public List<Message> getInbox() {
		Long userId = null; // get currentUserId
		return repo.findByUserIdOrCreatedBy(userId, userId);
	}

	@Override
	public List<Message> unreadMessages() {
		Long userId = null; // get currentUserId
		List<Message> messages = repo.findByUserIdAndMessageStatus(userId, MessageStatus.Sent);
		messages.forEach(m -> repo.updateMessageStatusById(MessageStatus.Read, m.getId()));
		return messages;
	}

	@Override
	public int updateMessageStatusById(MessageStatus status, Long id) {
		return repo.updateMessageStatusById(status, id);
	}

}
