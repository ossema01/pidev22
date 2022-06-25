package tn.esprit.wellbeing.modules.inbox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.wellbeing.models.SuperEntity;
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
	public void sendMessage(SuperEntity user, String body) { // tobeChanged to USER
		sendMessage(user.getId(), body);
	}

	@Override
	public void sendMessage(Message message) {
		message = repo.save(message);
		// send message in MQ
	}
	

}
