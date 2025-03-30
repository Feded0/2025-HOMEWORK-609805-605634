package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Questa classe modella la gestione dei cfu del Giocatore
 *
 * @author  docente di POO
 * @author Modificato da Feded0 (609805) e Civan04 (605634)
 * @see Stanza
 * @see Attrezzo
 * @version A
 */

public class Giocatore {
	
	static final private int CFU_INIZIALI = 20;
	
	private int cfu;
	private Borsa borsa;
	
	public Giocatore() {
		this.cfu = CFU_INIZIALI;
		this.borsa = new Borsa();
	}
	
	/**
	 * Metodo getter per i cfu
	 * @return cfu
	 */
	public int getCfu() {
		return this.cfu;
	}

	/**
	 * Metodo setter per i cfu
	 */
	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}
	
	/**
	 * Metodo getter per la borsa
	 * @return borsa
	 */
	public Borsa getBorsa() {
		return this.borsa;
	}
	
}
