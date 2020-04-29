/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.poweroutages;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import it.polito.tdp.poweroutages.model.Model;
import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.PowerOutages;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model; 
	
	public void setModel(Model model) {
		this.model=model; 
		
		List<Nerc> nerc= new ArrayList<Nerc>(this.model.getNercList()); 
		this.comboBox.getItems().addAll(nerc); 

		
	}

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="comboBox"
    private ComboBox<Nerc> comboBox; // Value injected by FXMLLoader

    @FXML // fx:id="txtYears"
    private TextField txtYears; // Value injected by FXMLLoader

    @FXML // fx:id="txtHours"
    private TextField txtHours; // Value injected by FXMLLoader

    @FXML // fx:id="btnWorstCaseAnalysis"
    private Button btnWorstCaseAnalysis; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doAnalysis(ActionEvent event) {

    	//txtResult.clear();
    	//txtHours.clear();
    	//txtYears.clear();
    	
    	//controlli su input 
    	if (txtHours.getText()==null || txtYears.getText()==null) {
    		txtResult.appendText("Inserire dei valori numerici \n");
    		return; 
    	}
    	int oreMax=0; 
    	int anniMax=0; 
    			
    	try {
    	oreMax= Integer.parseInt(txtHours.getText()); 
    	anniMax= Integer.parseInt(txtYears.getText());
    	
    }catch (Exception e) {
    	txtResult.appendText("Inserire NUMERI \n"); 
    	return; 
    }
    	if(this.comboBox.getValue()==null) {
    		txtResult.appendText("Selezionare NERC d'interesse! \n");
    		return; 
    	}
    	
    	Set<PowerOutages> lista= this.model.analisi(this.comboBox.getValue(), oreMax, anniMax); 
    	if (lista !=null) {
    		 txtResult.appendText("Eventi con maggior numero di clienti coinvolti : \n");
    		for (PowerOutages p : lista) {
    			txtResult.appendText(p+"\n");
    		}
    	}
    	else txtResult.appendText("Nessun risultato trovato!\n");
    }
    	
    	

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert comboBox != null : "fx:id=\"comboBox\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtYears != null : "fx:id=\"txtYears\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtHours != null : "fx:id=\"txtHours\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnWorstCaseAnalysis != null : "fx:id=\"btnWorstCaseAnalysis\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}
