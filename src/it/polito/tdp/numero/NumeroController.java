package it.polito.tdp.numero;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import it.polito.tdp.numero.model.*;

public class NumeroController {
	
	private NumeroModel model;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private HBox boxControlloPartita;

    @FXML
    private TextField txtRimasti;

    @FXML
    private HBox boxControlloTentativi;

    @FXML
    private TextField txtTentativo;

    @FXML
    private TextArea txtMessaggi;

    @FXML
    void handleNuovaPartita(ActionEvent event) {
    	//Gestisco l'interfaccia di inizio partita
    	boxControlloPartita.setDisable(true);
    	boxControlloTentativi.setDisable(false);
    	txtMessaggi.clear();
    	txtRimasti.setText(model.getTMAX()+"");

    	//Inizio una nuova partita (delego al modello)
    	model.newGame();
    }

    @FXML
    void handleProvaTentativo(ActionEvent event) {
    	//Acquisisco il tentativo in formato stringa
    	String txt = txtTentativo.getText().trim();
    	int tentativo;
    
    	//Controllo se è valido il tipo di dato
    	try {
    		tentativo = Integer.parseInt(txt);
    	}catch(NumberFormatException e) {
    		txtMessaggi.appendText("Errore, numero non valido");
    		return;
    	}
    	
 
    	//Controllo se il numero è nel range
    	if(!model.tentativoValido(tentativo)) {
    		txtMessaggi.appendText("Il tentativo non è tra 1 e "+model.getTMAX()+"\n");
    		return;
    	}
    	
    	//Delego al modello la verifica della correttezza del tentativo
    	int risultato = model.tentativo(tentativo);
    	if(risultato == 0 ) {
    		txtMessaggi.appendText("Complimenti, hai indovinato in "+model.getTentativiFatti()+" tentativi \n");
    		boxControlloPartita.setDisable(false);
    		boxControlloTentativi.setDisable(true);
    		
    	}
    	if(risultato == -1) {
    		txtMessaggi.appendText("Il numero da indovinare è più basso\n");
    	}
    	if(risultato == 1) {
    		txtMessaggi.appendText("Il numero da indovinare è più alto\n");
    	}
    	
    	//Aggiorno l'iterfaccia
    	txtTentativo.clear();
    	txtRimasti.setText(""+(model.getTMAX()-model.getTentativiFatti()));
    	
    	//Se la partita è finita
    	if(!model.isInGioco()) {
    		boxControlloPartita.setDisable(false);
    		boxControlloTentativi.setDisable(true);
    	}
    }

    @FXML
    void initialize() {
        assert boxControlloPartita != null : "fx:id=\"boxControlloPartita\" was not injected: check your FXML file 'IndovinaNumero.fxml'.";
        assert txtRimasti != null : "fx:id=\"txtRimasti\" was not injected: check your FXML file 'IndovinaNumero.fxml'.";
        assert boxControlloTentativi != null : "fx:id=\"boxControlloTentativi\" was not injected: check your FXML file 'IndovinaNumero.fxml'.";
        assert txtTentativo != null : "fx:id=\"txtTentativo\" was not injected: check your FXML file 'IndovinaNumero.fxml'.";
        assert txtMessaggi != null : "fx:id=\"txtMessaggi\" was not injected: check your FXML file 'IndovinaNumero.fxml'.";

    }
    
    public NumeroModel getModel() {
    	return model;
    }
    
    public void setModel(NumeroModel model) {
    	this.model=model;
    }
}
