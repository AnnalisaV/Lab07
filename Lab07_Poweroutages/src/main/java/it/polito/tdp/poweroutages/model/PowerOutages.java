package it.polito.tdp.poweroutages.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PowerOutages {

	private int id; 
	private EventType eventType; 
	private Nerc nerc; 
	private int customerAffected; 
	private LocalDateTime dataInizio; 
	private LocalDateTime dataFine;
	/**
	 * @param id
	 * @param eventType
	 * @param nerc
	 * @param customerAffected
	 * @param dataInizio
	 * @param dataFine
	 */
	public PowerOutages(int id, EventType eventType, Nerc nerc, int customerAffected, LocalDateTime dataInizio,
			LocalDateTime dataFine) {
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
	public LocalDateTime getDataInizio() {
		return dataInizio;
	}
	public void setDataInizioTime (LocalDateTime dataInizio) {
		this.dataInizio = dataInizio;
	}
	//descrizione
	@Override
	public String toString() {
		return nerc.getValue()+" "+eventType.getValue()+" #clienti coinvolti "+customerAffected
				 +" "+dataInizio+" , "+dataFine;
	}
	public LocalDateTime getDataFine() {
		return dataFine;
	}
	public void setDataFineTime(LocalDateTime dataFine) {
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
