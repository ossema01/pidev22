package tn.esprit.wellbeing.modules.collaborations.models;

import java.util.Date;

import javax.persistence.Entity;

import tn.esprit.wellbeing.models.SuperEntity;

@Entity
public class Reservation extends SuperEntity {

	private String description;

	private Date reservationDate;

	private double price;

	private int nbrOfreservedPlaces;

	public Reservation() {
		super();
	}

	public Reservation(String description, Date reservationDate, double price, int nbrOfreservedPlaces) {
		super();
		this.description = description;
		this.reservationDate = reservationDate;
		this.price = price;
		this.nbrOfreservedPlaces = nbrOfreservedPlaces;
	}

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
