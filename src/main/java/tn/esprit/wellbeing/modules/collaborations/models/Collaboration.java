package tn.esprit.wellbeing.modules.collaborations.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import tn.esprit.wellbeing.models.SuperEntity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Collaboration extends SuperEntity {

	private String description;

	private String partnerName;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date startDate;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date endDate;

	@OneToMany
	private Collection<Offre> offersList = new ArrayList<>();

	public Collection<Offre> getOffersList() {
		return offersList;
	}

	public void setOffersList(Collection<Offre> offersList) {
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
