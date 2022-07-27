package tn.esprit.wellbeing.models;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import tn.esprit.wellbeing.WellBeingApplication;
import tn.esprit.wellbeing.modules.userManagement.user.services.UserService;

@MappedSuperclass
public abstract class SuperEntity {

	@Id
	@GeneratedValue
	private Long id;

	@CreatedBy
	private String createdBy;

	@LastModifiedBy
	private String updatedBy;

	@CreatedDate
	@JsonFormat(pattern =  "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdAt;

	@LastModifiedDate
	@JsonFormat(pattern =  "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updatedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public SuperEntity() {
		super();
	}

	public SuperEntity(Long id) {
		super();
		this.id = id;
	}

	@PrePersist
	public void preInsert() {
		this.createdBy = WellBeingApplication.context.getBean(UserService.class).getCurrentUser().getUsername();
		this.createdAt = LocalDateTime.now();
	}

	@PreUpdate
	public void preUpdate() {
		this.updatedBy = WellBeingApplication.context.getBean(UserService.class).getCurrentUser().getUsername();
		this.updatedAt = LocalDateTime.now();
	}

}
