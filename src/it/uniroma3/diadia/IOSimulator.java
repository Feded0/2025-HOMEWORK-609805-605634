package it.uniroma3.diadia;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Questa classe permette di creare un simulatore di console,
 * da utilizzare nei test per emulare input del giocatore e
 * i relativi output da console
 *
 * @author Feded0 (609805) e Civan04 (605634)
 * @see IOConsole
 * @version C
 */

public class IOSimulator implements IO {

	private List<String> input;
	private int indiceInput;
	
	private Map<Integer, String> output;
	private Integer indiceOutput;

	public IOSimulator(List<String> inputUtente) {
		this.input = inputUtente;
		this.indiceInput = 0;
		
		this.output = new HashMap<Integer, String>();
		this.indiceOutput = 0;
	}

	/**
	 * Legge dalla lista di istruzioni passato nel costruttore
	 * la successiva istruzione da eseguire
	 *
	 * @return stringa da eseguire, oppure la stringa "fine" 
	 * 			se è stato visitata tutta la lista
	 */
	@Override
	public String leggiRiga() {
	    if (indiceInput >= input.size()) {
	        return "fine"; //Forza esplicitamente il comando di terminazione 
	        			   //se non viene passato nell'input della stringa dei comandi
	    }
	    return this.input.get(indiceInput++);
	}

	/**
	 * Salva la stringa che dovrebbe essere mostrata in console in una mappa
	 * 
	 * @param stringa da salvare
	 */
	@Override
	public void mostraMessaggio(String msg) {
		this.output.put(indiceOutput++, msg);
	}
	
	/**
	 * Verifica se la stringa ricevuta è presente fra tutte le stringhe
	 * che sono state stampate in output
	 * 
	 * @param stringa da verificre
	 */
	public boolean contieneMessaggio(String messaggio) {
		
		for (String msg : this.output.values()) {
	        if (msg.contains(messaggio)) {
	            return true;
	        }
	    }
	    return false;
    }
	
	/**
	 * Verifica se all'indice specificato nella
	 * mappa è presente una certa stringa
	 * 
	 * @param intero rappresentante l'indice
	 * @return la stringa corrispettiva
	 */
	public String contieneMessaggioAtIndice(Integer indice) {
		return this.output.get(indice);
	}
	
}
