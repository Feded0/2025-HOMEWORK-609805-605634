package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;

/**
 * Interfaccia che gestisce l'esecuzione e i parametri dei vari comandi
 *
 * @author docente di POO
 * @author Modificato da Feded0 (609805) e Civan04 (605634)
 * @see FabbricaDiComandiRiflessiva
 * @version C
 */

public interface Comando {
	
	/**
	 * esecuzione del comando
	 */
	public void esegui(Partita partita);
	
	/**
	 * set parametro del comando
	 */
	public void setParametro(String parametro);
	 
	/**
	 * set per la console IO
	 */
	public void setIO(IO io);
	
	/**
	 * get per ottenere il nome del comando
	 */
	public String getNome();
	
	/**
	 * get per ottenere il nome del parametro passato al comando
	 */
	public String getParametro();
}