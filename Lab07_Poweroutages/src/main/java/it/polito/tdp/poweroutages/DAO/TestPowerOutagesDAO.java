package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.util.List;

import it.polito.tdp.poweroutages.model.Model;
import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.PowerOutages;

public class TestPowerOutagesDAO {

	public static void main(String[] args) {
		
		try {
			Connection connection = ConnectDB.getConnection();
			connection.close();
			System.out.println("Connection Test PASSED");
			
			PowerOutageDAO dao = new PowerOutageDAO() ;
			
			System.out.println(dao.getNercList()) ;
			
			Model m= new Model(); 
			List<PowerOutages> lista= dao.getPowerOutages(new Nerc(2,"HECO"), m.getIdMapEventType(), m.getIdMapNerc() ); 
			
			for(PowerOutages p: lista) {
			System.out.println(p.getId()+" "+p.getDataInizio()+" "+p.getDataFine()+"\n"); 
			}

		} catch (Exception e) {
			System.err.println("Test FAILED");
		}

	}

}
