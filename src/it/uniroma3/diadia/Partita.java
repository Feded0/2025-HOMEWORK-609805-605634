package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.giocatore.*;

/**
 * Questa classe modella una partita del gioco
 *
 * @author docente di POO
 * @author Modificato da Feded0 (609805) e Civan04 (605634)
 * @see Stanza
 * @version C
 */

public class Partita {
	
	private Giocatore giocatore;
	private Labirinto labirinto;
	private boolean finita;
	
	public Partita(Labirinto l){
		this.giocatore = new Giocatore();
		this.labirinto = l;
		this.finita = false;
	}
	
	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.labirinto.getStanzaCorrente()==this.labirinto.getStanzaVincente();
	}
	
	public boolean giocatoreIsVivo() {
		return this.giocatore.getCfu() != 0;
	}
	
	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (this.giocatore.getCfu() == 0);
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}
	
	/**
	 * Metodo getter per il giocatore
	 * @return giocatore
	 */
	public Giocatore getGiocatore() {
		return this.giocatore;
	}
	
	/**
	 * Metodo getter per la borsa del giocatore
	 * @return borsa del giocatore
	 */
	public Borsa getGiocatoreBorsa() {
		return this.giocatore.getBorsa();
	}
	
	/**
	 * Metodo setter per la stanza corrente
	 * @param stanzaCorrente
	 */
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.labirinto.setStanzaCorrente(stanzaCorrente);
	}
	
	/**
	 * Metodo getter per la stanza corrente
	 * @return la stanza corrente
	 */
	public Stanza getStanzaCorrente() {
		return this.labirinto.getStanzaCorrente();
	}
	
	/**
	 * Metodo getter per il labirinto
	 * @return il labirinto
	 */
	public Labirinto getLabirinto() {
		return this.labirinto;
	}
	
}
