package tn.esprit.wellbeing.modules.occurences.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import tn.esprit.wellbeing.modules.occurences.OccurenceRequestStatus;

@Entity
public class Invitation implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;
	private String createdBy; 
	private String updatedBy; 
	 @Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date createdAt; 
	private Date updatedAt; 
	@Enumerated(EnumType.STRING)
	private OccurenceRequestStatus status;
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	@Override
	public String toString() {
		return "Invitation [createdBy=" + createdBy + ", updatedBy=" + updatedBy + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", status=" + status + "]";
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public OccurenceRequestStatus getStatus() {
		return status;
	}
	public void setStatus(OccurenceRequestStatus status) {
		this.status = status;
	}

}
