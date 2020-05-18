package it.polito.tdp.poweroutages.model;

import java.util.List;
import java.util.Set;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		//System.out.println(model.getNercList());
		
		
		List<PowerOutages> po= model.analisi(new Nerc(13, "MAIN"), 3, 3); 
		for (PowerOutages p : po) {
			System.out.println(p+"\n"); 
		}

	}

}
