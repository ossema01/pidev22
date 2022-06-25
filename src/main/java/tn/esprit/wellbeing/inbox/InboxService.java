package tn.esprit.wellbeing.inbox;

import tn.esprit.wellbeing.inbox.data.Message;
import tn.esprit.wellbeing.models.SuperEntity;

public interface InboxService {
	
	void sendMessage(Long userId, String message);
	
	void sendMessage(SuperEntity user, String message);

	void sendMessage(Message message);

}
