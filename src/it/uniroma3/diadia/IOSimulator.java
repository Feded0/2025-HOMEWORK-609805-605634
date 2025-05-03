package it.uniroma3.diadia;

/**
 * Questa classe permette di creare un simulatore di console,
 * da utilizzare nei test per emulare input del giocatore e
 * i relativi output da console
 *
 * @author Feded0 (609805) e Civan04 (605634)
 * @see IOConsole
 * @version B
 */

public class IOSimulator implements IO {

	private String[] input;
	private int indiceInput;

	private String[] output;
	private int indiceOutput;

	public IOSimulator(String[] inputUtente) {
		this.input = inputUtente;
		this.indiceInput = 0;
		this.output = new String[10]; //Dimensione output iniziale di 10
		this.indiceOutput = 0;
	}

	/**
	 * Legge dall'array di istruzioni passato nel costruttore
	 * la successiva istruzione da eseguire
	 *
	 * @return stringa da eseguire, oppure la stringa "fine" 
	 * 			se è stato visitato tutto l'array
	 */
	@Override
	public String leggiRiga() {
	    if (indiceInput >= input.length) {
	        return "fine"; //Forza esplicitamente il comando di terminazione 
	        			   //se non viene passato nell'input della stringa dei comandi
	    }
	    return this.input[indiceInput++];
	}

	/**
	 * Salva la stringa che dovrebbe essere mostrata in console in un array
	 * 
	 * @param stringa da salvare
	 */
	@Override
	public void mostraMessaggio(String msg) {
		if(indiceOutput>=output.length) {
			espandiArrayOutput();
		}
		this.output[indiceOutput++] = msg;
	}
	
	/**
	 * Raddoppia la dimensione dell'array nel caso sia stato riempito
	 */
	private void espandiArrayOutput() {
        String[] nuovoOutput = new String[output.length * 2];
        
        for (int i = 0; i < output.length; i++) {
            nuovoOutput[i] = output[i];
        }
        output = nuovoOutput;
    }
	
	/**
	 * Verifica se la stringa ricevuta è presente fra tutte le stringhe
	 * che sono state stampate in output
	 * 
	 * @param stringa da verificre
	 */
	public boolean contieneMessaggio(String messaggio) {
        this.output = getOutput();
		
		for(String s : this.output) {
            if(s.contains(messaggio)) return true;
        }
        return false;
    }
	
	/**
	 * Ricopia le stringhe di output in un nuovo array di output rimuovendo eventuali 
	 * null finali (per via del raddoppiamento della dimensione dell'array), quindi 
	 * permette di ottenere l'output completo di tutti i messaggi stampati in console
	 *
	 * @return array di stringhe
	 */
	public String[] getOutput() {
		String[] output = new String[indiceOutput];
		
		for(int i=0; i<indiceOutput; i++) {
			output[i] = this.output[i];
		}
		return output;
	}
	
}
