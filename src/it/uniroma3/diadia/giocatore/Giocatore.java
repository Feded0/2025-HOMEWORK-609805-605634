package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.Configuratore;

/**
 * Questa classe modella la gestione dei cfu del Giocatore
 *
 * @author docente di POO
 * @author Modificato da Feded0 (609805) e Civan04 (605634)
 * @version C
 */

public class Giocatore {
	
	static final private int CFU_INIZIALI = Configuratore.getCFU();
	
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
