package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Questa classe modella le interazioni con la borsa
 * e la sua capienza massima
 *
 * @author  docente di POO
 * @author Modificato da Feded0 (609805) e Civan04 (605634)
 * @see Stanza
 * @see Attrezzo
 * @version A
 */

public class Borsa {
	
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	
	private Attrezzo[] attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;
	
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	
	/**
	 * Crea una nuova Borsa impostandone il peso massimo
	 * e con un massimo di 10 attrezzi.
	 * @param pesoMax
	 */
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new Attrezzo[10]; // speriamo bastino...
		this.numeroAttrezzi = 0;
	}
	
	/**
	 * Aggiunge un attrezzo alla Borsa.
	 * @param attrezzo
	 * @return true se l'attrezzo è stato aggiunto e 
	 * quindi la Borsa non è piena, falso altrimenti
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		if (this.numeroAttrezzi==10)
			return false;
		this.attrezzi[this.numeroAttrezzi] = attrezzo;
		this.numeroAttrezzi++;
		return true;
	}
	
	/**
	 * Verifica il peso massimo che la borsa può supportare.
	 * @return valore intero del peso massimo
	 */
	public int getPesoMax() {
		return pesoMax;
	}
	
	/**
	 * Prende un attrezzo dalla Borsa.
	 * @param nomeAttrezzo
	 * @return il riferimento all'attrezzo se trovato, null altrimenti
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		for (int i= 0; i<this.numeroAttrezzi; i++)
			if (this.attrezzi[i].getNome().equals(nomeAttrezzo))
				a = attrezzi[i];
		return a;
	}
	
	/**
	 * Verifica il peso corrente della Borsa.
	 * @return valore intero del peso della Borsa
	 */
	public int getPeso() {
		int peso = 0;
		for (int i= 0; i<this.numeroAttrezzi; i++)
			peso += this.attrezzi[i].getPeso();
		return peso;
	}
	
	/**
	 * Verifica se la Borsa è vuota.
	 * @return true se vuota, falso altrimenti
	 */
	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}
	
	/**
	 * Verifica se un attrezzo è presente nella Borsa.
	 * @param nomeAttrezzo
	 * @return true se presente, falso altrimenti
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}
	
	/**
	 * Rimuove un attrezzo dalla Borsa (ricerca in base all'indirizzo).
	 * @param attrezzo
	 * @return riferimento all'attrezzo appena rimosso
	 */
	public Attrezzo removeAttrezzo(Attrezzo attrezzo) {
		Attrezzo rimosso = null;
		for (int i = 0; i < this.numeroAttrezzi; i++) {
	        if (this.attrezzi[i]==attrezzo) {
	            rimosso = this.attrezzi[i];
	        	for (int j = i; j < this.attrezzi.length - 1; j++) {
	                this.attrezzi[j] = this.attrezzi[j + 1];
	            }
	            this.numeroAttrezzi--;
	            break;
	        }
		}
		return rimosso;
	}
	
	/**
	 * Restituisce una rappresentazione stringa degli elementi 
	 * nella borsa con relativo peso
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (int i= 0; i<this.numeroAttrezzi; i++)
				s.append(attrezzi[i].toString()+" ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
	
}