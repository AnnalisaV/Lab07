package it.polito.tdp.poweroutages.model;

import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	Map<Integer, EventType> idMapEventType;
	Map<Integer, Nerc> idMapNerc; 
	Set <PowerOutages> migliore; 
	
	public Model() {
		podao = new PowerOutageDAO();
		
	  idMapEventType= new HashMap<>(podao.eventType());
	   idMapNerc= new HashMap<>(podao.Nerc()); 
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}

	/**
	 * Ricerca degli eventi che max il numero di clienti coinvolti
	 * @param n Nerc d'interesse
	 * @param oreMax ore max di disservizio
	 * @param y     nuemro di anni coinvolti
	 * @return elenco degli eventi che soddisfano tali criteri
	 */
	public Set<PowerOutages> analisi(Nerc n, int oreMax, int y){
		this.migliore= null; 
		List<PowerOutages> poDelNerc= this.podao.getPowerOutages(n, idMapEventType, idMapNerc);
		Set<PowerOutages> parziale= new HashSet<>(); 
		
		ricorsione(parziale,poDelNerc, 0 , oreMax, y);
		return migliore; 
		
	}

	/**
	 * Algoritmo ricorsivo
	 * @param parziale
	 * @param poDelNerc
	 * @param livello
	 * @param ore
	 * @param y
	 */
	private void ricorsione(Set<PowerOutages> parziale, List<PowerOutages> poDelNerc, int livello, int ore, int y) {
	
		
		// caso terminale 
		// ho considerato tutti gli eevnti della lista per quel nerc
		if(livello== poDelNerc.size()){
			
			// ma quella migliore? cioe' ha numero max di clienti coinvolti
			if(this.migliore== null || calcola(parziale)>calcola(this.migliore)) {
			// e' la soluzione migliore
			this.migliore= new HashSet<>(parziale); 
			}
		
		return; // in ogni caso devo uscire perche' non ho piu' considerazioni da fare 
	}
		
		
		// caso generico di ricorsione 
		for (PowerOutages p : poDelNerc) {
			if(!parziale.contains(p) && adatto(p, parziale, ore, y )) {
				parziale.add(p); 
				ricorsione(parziale, poDelNerc, livello+1, ore, y); 
				parziale.remove(parziale.size()-1); // backtracking
				
			}
		}
	}

	/**
	 * L'evento e' da considerare o meno a seconda dei vincoli imposti
	 * @param p evento in questione
	 * @return true se va inserito nella sequenza, false altrimenti
	 */
	private boolean adatto(PowerOutages p, Set<PowerOutages> parziale, int disservOreMax, int y) {
		
		
		long disservizio=0; 
		for (PowerOutages po : parziale ) {
	    disservizio= Duration.between(po.getDataFine(), po.getDataInizio()).getSeconds(); // ottengo i seocndi di disservizio
			}
		
		long disservP= Duration.between(p.getDataFine(), p.getDataInizio()).getSeconds(); 
		
		// posso ancora stare nel limite max di ore di disservizio? (trasformate in sec per semplicita')
		if (disservizio+disservP <= (disservOreMax*3600) ) {
			
		//verifica sull'anno 
			int max=0; 
			int min=9999; //devo mettere un numero grande altrimenti non trovero' mai un anno piccino
			
			Set<PowerOutages> parzialeNuova= new HashSet<>(parziale); //per non 'sporcare' quella che ho gia'
			parzialeNuova.add(p); 
			
			for (PowerOutages pow: parzialeNuova) {
				if(pow.getDataFine().getYear() > max) {
					max= pow.getDataFine().getYear(); 
				}
				if(pow.getDataInizio().getYear() <min) {
					min= pow.getDataInizio().getYear();
				}
			}
			if ((max-min )<= y) return true; 
			
			else return false; //allora  non torna il conto dell'anno
			
		}
		
		return false; // allora non torna il conto di ore disservizio
	}

	/**
	 * Calcola il numero di clienti affetti dal blackout di questa sequenza 
	 * @param parziale sottoinsieme di eventi blackout 
	 * @return numero di clienti coinvolti
	 */
	private int calcola(Set<PowerOutages> parziale) {
		
		int clienti=0; 
		
		for (PowerOutages p: parziale) {
			clienti+= p.getCustomerAffected(); 
		}
		return clienti;
	}

	public Map<Integer, EventType> getIdMapEventType() {
		return idMapEventType;
	}

	public Map<Integer, Nerc> getIdMapNerc() {
		return idMapNerc;
	}
	
	
	
	
	
}
