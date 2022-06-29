package tn.esprit.wellbeing.modules.collaborations.models;

import java.util.Date;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import tn.esprit.wellbeing.models.SuperEntity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Reservation extends SuperEntity {

	private String description;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date reservationDate;

	private double price;

	private int nbrOfreservedPlaces;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getNbrOfreservedPlaces() {
		return nbrOfreservedPlaces;
	}

	public void setNbrOfreservedPlaces(int nbrOfreservedPlaces) {
		this.nbrOfreservedPlaces = nbrOfreservedPlaces;
	}

}
