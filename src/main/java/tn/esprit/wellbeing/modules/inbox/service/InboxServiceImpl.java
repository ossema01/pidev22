package tn.esprit.wellbeing.modules.inbox.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import tn.esprit.wellbeing.modules.inbox.InboxException;
import tn.esprit.wellbeing.modules.inbox.InboxService;
import tn.esprit.wellbeing.modules.inbox.data.Message;
import tn.esprit.wellbeing.modules.inbox.data.MessageRepository;
import tn.esprit.wellbeing.modules.inbox.data.MessageStatus;
import tn.esprit.wellbeing.modules.userManagement.user.services.UserService;

@Service
public class InboxServiceImpl implements InboxService {

	@Autowired
	private MessageRepository repo;

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@Autowired
	private UserService us;

	@Override
	public void sendMessage(String toUserName, String body) {
		Message message = new Message();
		message.setBody(body);
		message.setToUser(toUserName);
		message.setStatus(MessageStatus.Created);
		message = repo.save(message);
		sendMessage(message);
	}

	@Override
	public void sendMessage(Message message) {
		message = repo.save(message);
		message.computeSuggestions();
		simpMessagingTemplate.convertAndSend("/topic/inbox-" + message.getToUser(), message);
		message.setStatus(MessageStatus.Sent);
		message = repo.save(message);
	}

	@Override
	public List<Message> getInbox() {
		String userId = us.getCurrentUser().getUsername();
		return repo.findByToUserOrCreatedBy(userId, userId);
	}

	@Override
	public List<Message> unreadMessages() {
		String userId = us.getCurrentUser().getUsername();
		List<Message> messages = repo.findByToUserAndStatus(userId, MessageStatus.Sent);
		messages.forEach(m -> updateMessageStatusById(MessageStatus.Read, m.getId()));
		return messages;
	}

	@Override
	public int updateMessageStatusById(MessageStatus status, Long id) {
		return repo.updateMessageStatusById(status, id);
	}

	@Override
	public Message fireEvent(Long id) {
		Optional<Message> msg = repo.findById(id);
		if (msg.isEmpty()) {
			throw new InboxException("Message not found. ID = " + id);
		}
		Message message = msg.get();
		switch (message.getStatus()) {
		case Created:
			message.setStatus(MessageStatus.Sent);
			break;
		case Sent:
			message.setStatus(MessageStatus.Read);
			break;
		case Read:
			message.setStatus(MessageStatus.Deleted);
			break;
		default:
			break;
		}
		return repo.save(message);
	}

}
