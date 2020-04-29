package it.polito.tdp.poweroutages.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PowerOutages {

	private int id; 
	private EventType eventType; 
	private Nerc nerc; 
	private int customerAffected; 
	private LocalDate dataInizio; 
	private LocalDate dataFine;
	/**
	 * @param id
	 * @param eventType
	 * @param nerc
	 * @param customerAffected
	 * @param dataInizio
	 * @param dataFine
	 */
	public PowerOutages(int id, EventType eventType, Nerc nerc, int customerAffected, LocalDate dataInizio,
			LocalDate dataFine) {
		super();
		this.id = id;
		this.eventType = eventType;
		this.nerc = nerc;
		this.customerAffected = customerAffected;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public EventType getEventType() {
		return eventType;
	}
	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}
	public Nerc getNerc() {
		return nerc;
	}
	public void setNerc(Nerc nerc) {
		this.nerc = nerc;
	}
	public int getCustomerAffected() {
		return customerAffected;
	}
	public void setCustomerAffected(int customerAffected) {
		this.customerAffected = customerAffected;
	}
	public LocalDate getDataInizio() {
		return dataInizio;
	}
	public void setDataInizio(LocalDate dataInizio) {
		this.dataInizio = dataInizio;
	}
	public LocalDate getDataFine() {
		return dataFine;
	}
	public void setDataFine(LocalDate dataFine) {
		this.dataFine = dataFine;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PowerOutages other = (PowerOutages) obj;
		if (id != other.id)
			return false;
		return true;
	} 
	
	
}
