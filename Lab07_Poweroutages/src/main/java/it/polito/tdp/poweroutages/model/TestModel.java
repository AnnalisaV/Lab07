package it.polito.tdp.poweroutages.model;

import java.util.Set;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		//System.out.println(model.getNercList());
		
		
		Set<PowerOutages> po= model.analisi(new Nerc(2, "HECO"), 200, 4); 
		for (PowerOutages p : po) {
			System.out.println(p+"\n"); 
		}

	}

}
