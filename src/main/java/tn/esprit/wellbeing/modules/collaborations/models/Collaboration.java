package tn.esprit.wellbeing.modules.collaborations.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;

import tn.esprit.wellbeing.models.SuperEntity;

@Entity
public class Collaboration extends SuperEntity {

	private String description;

	private String partnerName;

	@JsonFormat(pattern="dd/MM/yyyy")
	private Date startDate;

	@JsonFormat(pattern="dd/MM/yyyy")
	private Date endDate;

	@OneToMany
	private Set<Offre> offersList;

	public Collaboration() {
		super();
	}

	public Collaboration(String description, String partnerName, Date startDate, Date endDate) {
		super();
		this.description = description;
		this.partnerName = partnerName;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Collaboration(String description, String partnerName, Date startDate, Date endDate, Set<Offre> offersList) {
		super();
		this.description = description;
		this.partnerName = partnerName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.offersList = offersList;
	}

	public Set<Offre> getOffersList() {
		return offersList;
	}

	public void setOffersList(Set<Offre> offersList) {
		this.offersList = offersList;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
