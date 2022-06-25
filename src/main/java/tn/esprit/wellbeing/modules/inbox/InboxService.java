package tn.esprit.wellbeing.modules.inbox;

import tn.esprit.wellbeing.models.SuperEntity;
import tn.esprit.wellbeing.modules.inbox.data.Message;

public interface InboxService {
	
	void sendMessage(Long userId, String message);
	
	void sendMessage(SuperEntity user, String message);

	void sendMessage(Message message);

}
