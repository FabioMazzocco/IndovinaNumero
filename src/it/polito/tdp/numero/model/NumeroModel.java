package it.polito.tdp.numero.model;

import java.security.InvalidParameterException;

public class NumeroModel {
	
	private final int NMAX = 100;
	private final int TMAX = 8;
	
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco;
	
	
	public NumeroModel() {
		inGioco = false;
	}
	
	/**
	 *  Avvia nuova partita
	 */
	public void newGame() {
		this.segreto = ((int)(Math.random()*NMAX))+1;
    	this.tentativiFatti = 0 ;
    	inGioco = true;
	}
	
	/**
	 * Gestisce il tentativo, controlla se la partita è in corso, se il tentativo è nei limiti, restituisce 0 se
	 * il tentativo è corretto, -1 se il tentativo è troppo alto, +1 se troppo basso
	 * @param t è il valore del tentativo
	 * @return 0 se giusto, -1 se il numero da indovinare è più basso, +1 se più alto
	 */
	public int tentativo(int t) {
		//Controllo se la partita è in corso
		if(!inGioco)
			throw new IllegalStateException("La partita è terminata");
		
		//Controllo se l'input è corretto (non il tipo ma l'intervallo)
		if(!tentativoValido(t))
			throw new InvalidParameterException(String.format("Devi inserire un numero tra %d e %d", 1, NMAX));
		
		//Aumento il #tentativi
		tentativiFatti++;
		if(tentativiFatti == TMAX)
			inGioco = false;
		
		//Gestisco il tentativo
		if(this.segreto == t) {
			this.inGioco = false;
			return 0;
		}
		if(this.segreto > t)
			return +1;
		return -1;
	}
	
	public boolean tentativoValido(int t) {
		if(t<1 || t>NMAX)
			return false;
		return true;
	}

	public boolean isInGioco() {
		return inGioco;
	}

	public int getSegreto() {
		return segreto;
	}

	public int getTentativiFatti() {
		return tentativiFatti;
	}

	public int getTMAX() {
		return TMAX;
	}
	
	
	
	
}
