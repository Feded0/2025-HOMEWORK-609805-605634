package it.uniroma3.diadia.attrezzi;

import java.util.Comparator;

/**
 * Comparatore per oggetti di tipo Attrezzo basato sul peso.
 * 
 * Confronta due attrezzi in base al loro peso in ordine crescente. 
 * In caso di parità di peso, li ordina in base al nome in ordine lessicografico.

 * Utile per ordinare una collezione di attrezzi in strutture dati come liste o set ordinati.
 * 
 * @author Feded0 (609805) e Civan04 (605634)
 * @see Attrezzo
 * @version C
 */

public class ComparatorePerPeso implements Comparator<Attrezzo> {

	public int compare(Attrezzo attr1, Attrezzo attr2) {
		if(attr1.getPeso()-attr2.getPeso() == 0)
			return attr1.getNome().compareTo(attr2.getNome());
		return attr1.getPeso()-attr2.getPeso();
	}

}
