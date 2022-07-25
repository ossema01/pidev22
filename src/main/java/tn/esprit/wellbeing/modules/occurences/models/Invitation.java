package tn.esprit.wellbeing.modules.occurences.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import tn.esprit.wellbeing.models.SuperEntity;
import tn.esprit.wellbeing.modules.occurences.OccurenceRequestStatus;

@Entity
public class Invitation extends SuperEntity implements Serializable{


	@Enumerated(EnumType.STRING)
	private OccurenceRequestStatus status;



	public OccurenceRequestStatus getStatus() {
		return status;
	}
	public void setStatus(OccurenceRequestStatus status) {
		this.status = status;
	}

}
