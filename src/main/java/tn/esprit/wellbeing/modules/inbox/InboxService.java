package tn.esprit.wellbeing.modules.inbox;

import java.util.List;

import tn.esprit.wellbeing.modules.inbox.data.Message;
import tn.esprit.wellbeing.modules.inbox.data.MessageStatus;

public interface InboxService {

	void sendMessage(String toUserName, String message);

	void sendMessage(Message message);

	List<Message> getInbox();

	List<Message> unreadMessages();

	int updateMessageStatusById(MessageStatus status, Long id);

	Message fireEvent(Long id);

}
