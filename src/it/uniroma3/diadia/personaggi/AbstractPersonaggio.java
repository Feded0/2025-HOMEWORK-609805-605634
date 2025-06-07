package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe astratta che rappresenta un personaggio all'interno del gioco.
 * 
 * Ogni personaggio ha un nome, una presentazione e uno stato interno che 
 * indica se ha già salutato il giocatore. I personaggi possono eseguire
 * azioni specifiche tramite il metodo agisci e reagire alla ricezione 
 * di un attrezzo tramite riceviRegalo
 * 
 * @author docente di POO
 * @author Modificato da Feded0 (609805) e Civan04 (605634)
 * @see Partita
 * @version C
 */

public abstract class AbstractPersonaggio {
	private String nome;
	private String presentazione;
	private boolean haSalutato;
	
	/**
	 * Costruisce un personaggio con un nome e un messaggio di presentazione.
	 * 
	 * @param nome il nome del personaggio
	 * @param presentaz il messaggio di presentazione del personaggio
	 */
	public AbstractPersonaggio(String nome, String presentaz) {
		this.nome = nome;
		this.presentazione = presentaz;
		this.haSalutato = false;
	}
	
	/**
	 * Restituisce il nome del personaggio.
	 * 
	 * @return una stringa contenente il nome del personaggio
	 */
	public String getNome() {
		return this.nome;
	}
	
	/**
	 * Verifica se il personaggio ha già salutato.
	 * 
	 * @return true se ha già salutato, false altrimenti
	 */
	public boolean haSalutato() {
		return this.haSalutato;
	}

	/**
	 * Restituisce un messaggio di saluto del personaggio. 
	 * Se ha già salutato in precedenza, restituisce un messaggio diverso.
	 * 
	 * @return una stringa contenente il saluto o un messaggio alternativo
	 */
	public String saluta() {
		StringBuilder risposta = new StringBuilder("Ciao, io sono ");

		risposta.append(this.getNome()+".");
		if (!haSalutato) {
			risposta.append(" " + this.presentazione);
		}
		else {
			risposta.append(" Ci siamo già presentati!");
		}
		
		this.haSalutato = true;
		return risposta.toString();
	}
	
	/**
	 * Metodo astratto che definisce il comportamento del personaggio
	 * quando agisce durante la partita.
	 * 
	 * @param partita la partita in corso
	 * @return un messaggio risultante dall'azione del personaggio
	 */
	abstract public String agisci(Partita partita);
	
	/**
	 * Metodo astratto per gestire la ricezione di un attrezzo da parte del personaggio.
	 * 
	 * @param attrezzo l'attrezzo offerto al personaggio
	 * @param partita la partita in corso
	 * @return un messaggio risultante dalla reazione del personaggio
	 */
	abstract public String riceviRegalo(Attrezzo attrezzo, Partita partita);
	
	/**
	 * Restituisce una rappresentazione testuale del personaggio, 
	 * che corrisponde al suo nome.
	 * 
	 * @return il nome del personaggio
	 */
	@Override
	public String toString() {
		return this.getNome();
	}
}