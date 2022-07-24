package tn.esprit.wellbeing.modules.inbox.data;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {

	List<Message> findByUserIdAndMessageStatus(Long userId, MessageStatus status);

	List<Message> findByUserIdOrCreatedBy(Long reciever, Long sender);

	@Modifying
	@Query("update Message m set m.status = :status where m.id = :id")
	int updateMessageStatusById(@Param("status") MessageStatus status, @Param("id") Long id);

}
