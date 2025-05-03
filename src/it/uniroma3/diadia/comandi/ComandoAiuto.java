package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;

/**
 * Classe che gestisce il comando aiuto 
 * 
 * @author docente di POO 
 * @author Modificato da Feded0 (609805) e Civan04 (605634)
 * @see FabbricaDiComandiFisarmonica
 * @version B
*/

public class ComandoAiuto implements Comando {
	private IO io;
	static final private String[] elencoComandi = 
		{ "aiuto","vai", "prendi", "posa", "guarda", "fine" };

	/**
	 * Stampa informazioni di aiuto con l'elenco dei comandi disponibili
	 */
	@Override
	public void esegui (Partita partita) {

		for (int i = 0; i < elencoComandi.length; i++)
			io.mostraMessaggio(elencoComandi[i]);
		io.mostraMessaggio("");
	}

	/**
	 * Override per impostare il parametro corrente
	 * 
	 * @param parametro stringa dell'eventuale parametro
	 */
	@Override
	public void setParametro (String parametro) {
		//Nessun parametro richiesto
	}

	/**
	 * Override per impostare la console dal main
	 * 
	 * @param io console istanziata nel main
	 */
	@Override
	public void setIO(IO io) {
		this.io = io;
	}
	
	/**
	 * Override per ottenere il nome del comando corrente
	 * 
	 * @return stringa che identifica il nome del comando
	 */
	@Override
	public String getNome() {
		return "aiuto";
	}
	
	/**
	 * Override per ottenere il nome del parametro corrente
	 * 
	 * @return stringa che identifica il nome del parametro
	 */
	@Override
	public String getParametro() {
		return null;
	}
	
}
