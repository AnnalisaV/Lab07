package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.poweroutages.model.EventType;
import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.PowerOutages;

public class PowerOutageDAO {
	
	/**
	 * Ricerca di tutti i NERC nel db
	 * @return
	 */
	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}
	
	/**
	 * 
	 */
	public Map<Integer, EventType> eventType(){
		String sql="SELECT * FROM eventtype "; 
		Map<Integer, EventType> ev= new HashMap<Integer, EventType>(); 

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				EventType e= new EventType(res.getInt("id"), res.getString("value")); 
				ev.put(e.getId(), e); 
			}

			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return ev;
}
	
	public Map<Integer, Nerc> Nerc(){
		String sql="SELECT * FROM nerc "; 
		Map<Integer, Nerc> ne= new HashMap<Integer, Nerc>(); 

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n= new Nerc(res.getInt("id"), res.getString("value")); 
				ne.put(n.getId(), n); 
			}

			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return ne;
}
	
	/**
	 * Sottoinsieme di PowerOutages che stanno sotto quel NERC
	 */
	public List<PowerOutages> getPowerOutages(Nerc nerc, Map<Integer, EventType> ev, Map<Integer, Nerc> n){
		
		String sql= "SELECT * FROM poweroutages WHERE nerc_id=? "; 
		
		List<PowerOutages> listaPO= new ArrayList<>(); 
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,  nerc.getId());
			ResultSet res = st.executeQuery();


			while (res.next()) {
				PowerOutages po= new PowerOutages(res.getInt("id"), ev.get(res.getInt("event_type_id")), n.get(res.getInt("nerc_id")), res.getInt("customers_affected"), res.getTimestamp("date_event_began").toLocalDateTime(), res.getTimestamp("date_event_finished").toLocalDateTime()) ;
				listaPO.add(po);
			}
		}
		catch(SQLException e) {
			throw new RuntimeException(e);
		}

		return listaPO;
	}

}
