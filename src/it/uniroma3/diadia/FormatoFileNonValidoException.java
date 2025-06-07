package it.uniroma3.diadia;

/**
 * Eccezione personalizzata che rappresenta un errore nel formato del file
 * durante il caricamento del labirinto o di altri componenti del gioco.
 * 
 * Questa eccezione viene sollevata quando un file di configurazione o di descrizione
 * non rispetta il formato atteso, ad esempio per righe malformate o dati mancanti.
 * 
 * @author docente di POO
 * @see Exception
 * @version C
 */


public class FormatoFileNonValidoException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public FormatoFileNonValidoException(String msg){
		super(msg);
	}
}
