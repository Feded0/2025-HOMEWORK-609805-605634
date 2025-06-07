package it.uniroma3.diadia.giocatore;

import java.util.*;

import it.uniroma3.diadia.Configuratore;
import it.uniroma3.diadia.attrezzi.*;

/**
 * La classe Borsa rappresenta il contenitore degli oggetti trasportati dal giocatore.
 * 
 * La borsa ha un limite massimo di peso configurabile, oltre il quale non è possibile
 * inserire nuovi attrezzi. Gli attrezzi sono memorizzati in una mappa indicizzata per nome
 * e possono essere aggiunti, rimossi, ordinati o raggruppati secondo diversi criteri:
 * per peso, per nome o per peso raggruppato.
 * 
 * La borsa fornisce inoltre metodi per verificarne lo stato (vuota/piena),
 * ottenere il contenuto attuale e rappresentazioni testuali ordinate.
 * 
 * @author docente di POO
 * @author Modificato da Feded0 (609805) e Civan04 (605634)
 * @see Attrezzo
 * @version C
 */

public class Borsa {
	
	public final static int DEFAULT_PESO_MAX_BORSA = Configuratore.getPesoMax();
	
	private Map<String, Attrezzo> attrezzi;
	private int pesoMax;
	private int pesoAttuale;
	
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	
	/**
	 * Crea una nuova Borsa impostandone il peso massimo
	 * 
	 * @param pesoMax
	 */
	public Borsa(int pesoMax) {
		this.attrezzi = new TreeMap<>();
		this.pesoMax = pesoMax;
		this.pesoAttuale = 0;
	}
	
	/**
	 * Aggiunge un attrezzo alla Borsa, se l'attrezzo è già presente
	 * la borsa rimane inalterata.
	 * @param attrezzo
	 * @return true se l'attrezzo è stato aggiunto e 
	 * quindi la Borsa non è piena, falso altrimenti
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
	    if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
	        return false;

	    if (!this.attrezzi.containsKey(attrezzo.getNome())) {
	        this.attrezzi.put(attrezzo.getNome(), attrezzo);
	        this.pesoAttuale += attrezzo.getPeso();
	        return true;
	    }
	    
	    return false;
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
		
		if(nomeAttrezzo != null && this.attrezzi.containsKey(nomeAttrezzo))
			a = this.attrezzi.get(nomeAttrezzo);
		return a;
	}
	
	/**
	 * Verifica il peso corrente della Borsa.
	 * @return valore intero del peso della Borsa
	 */
	public int getPeso() {
		return this.pesoAttuale;
	}
	
	/**
	 * Verifica se la Borsa è vuota.
	 * @return true se vuota, falso altrimenti
	 */
	public boolean isEmpty() {
		return this.attrezzi.size()==0;
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
	 * Rimuove un attrezzo dalla Borsa (ricerca in base al nome).
	 * @param attrezzo
	 * @return l'attrezzo appena rimosso
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		if(nomeAttrezzo!=null){
			a = this.attrezzi.remove(nomeAttrezzo);
			if(a!=null) {
				this.pesoAttuale -= a.getPeso();
			}
		}
		return a;
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
			s.append(this.attrezzi.values());
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
	
	/**
	 * Restituisce una rappresentazione stringa degli elementi 
	 * ordinati secondo diversi criteri 
	 * @return la rappresentazione stringa
	 */
	public String getContenutoOrdinato() {
		StringBuilder s = new StringBuilder();

		if(!this.isEmpty()) {
			s.append("[Contenuto Borsa]");
			s.append("\nOrdinamento per peso    " + getContenutoOrdinatoPerPeso().toString().replaceAll("\\s*\\(\\d+kg\\)", ""));
			s.append("\nOrdinamento per nome    " + getContenutoOrdinatoPerNome().toString().replaceAll("\\s*\\(\\d+kg\\)", ""));
			s.append("\nRaggruppamento per peso " + getContenutoRaggruppatoPerPeso().toString().replaceAll("\\s*\\(\\d+kg\\)", ""));
		}
		else {
			s.append("Borsa vuota");
		}
	    
	    return s.toString();
	}
	
	/**
	 * Restituisce una lista con tutti gli attrezzi 
	 * ordinati per peso crescente
	 * @return lista degli attrezzi ordinati
	 */
	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		List<Attrezzo> lista = new ArrayList<>();
		
		lista.addAll(this.attrezzi.values());
		Collections.sort(lista, new ComparatorePerPeso());
		
		return lista;
	}

	/**
	 * Restituisce un set con tutti gli attrezzi 
	 * ordinati per ordine alfabetico
	 * @return set degli attrezzi ordinati
	 */
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		return new TreeSet<Attrezzo>(this.attrezzi.values());
	}

	/**
	 * Restituisce una mappa con tutti gli attrezzi 
	 * ordinati in gruppi in base al loro peso in ordine crescente
	 * @return mappa degli attrezzi ordinati
	 */
	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		Map<Integer, Set<Attrezzo>> map = new TreeMap<>();
		
		for(Attrezzo a : this.attrezzi.values()){
			if(map.containsKey(a.getPeso())) {
				map.get(a.getPeso()).add(a);
			}
			else {
				Set<Attrezzo> s = new HashSet<Attrezzo>();
				s.add(a);
				map.put(a.getPeso(), s);
			}
		}
		return map;
	}
	
	/**
	 * Restituisce un set con tutti gli attrezzi 
	 * ordinati per peso
	 * @return set degli attrezzi ordinati
	 */
	SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		SortedSet<Attrezzo> s = new TreeSet<Attrezzo>(new ComparatorePerPeso());
		s.addAll(this.attrezzi.values());
		return s;
	}
	
}