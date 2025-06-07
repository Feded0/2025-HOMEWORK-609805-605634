package it.uniroma3.diadia.ambienti;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @author Modificato da Feded0 (609805) e Civan04 (605634)
 * @see Attrezzo
 * @version C
*/

public class Stanza {
	
	static final private int NUMERO_MASSIMO_DIREZIONI = 4;
	static final private int NUMERO_MASSIMO_ATTREZZI = 10;
	
	private String nome;
	
    private Map<String, Attrezzo> attrezzi;
    private int numeroAttrezzi;
    
    private Map<Direzione, Stanza> stanzeAdiacenti;
    private int numeroStanzeAdiacenti;
    
    private AbstractPersonaggio personaggio;
    
    
    /**
     * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
     * @param nome il nome della stanza
     */
    public Stanza(String nome) {
        this.nome = nome;
        this.numeroStanzeAdiacenti = 0;
        this.numeroAttrezzi = 0;
        this.stanzeAdiacenti = new HashMap<>();
        this.attrezzi = new HashMap<>();
        this.personaggio = null;
    }

    /**
     * Imposta una stanza adiacente.
     *
     * @param direzione direzione in cui sara' posta la stanza adiacente.
     * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
     */
    public void impostaStanzaAdiacente(Direzione direzione, Stanza stanza) {
    	boolean aggiornato = false;
    	
    	if (stanzeAdiacenti.containsKey(direzione)) {
			this.stanzeAdiacenti.put(direzione,stanza);
			aggiornato = true;
		}
		if (!aggiornato) {
			if (this.numeroStanzeAdiacenti < NUMERO_MASSIMO_DIREZIONI) {
				this.stanzeAdiacenti.put(direzione,stanza);
				this.numeroStanzeAdiacenti++;
			}
		}
    }

    /**
     * Restituisce la stanza adiacente nella direzione specificata
     * @param direzione
     */
	public Stanza getStanzaAdiacente(Direzione direzione) {
		Stanza stanza = null;
		
		if (this.stanzeAdiacenti.containsKey(direzione))
			stanza = this.stanzeAdiacenti.get(direzione);
		return stanza;
	}
	
	public List<Stanza> getStanzeAdiacenti() {
		List<Stanza> listaStanzeAdiacenti = new ArrayList<>();
		for (Stanza s : stanzeAdiacenti.values()) {
			listaStanzeAdiacenti.add(s);
		}
		return listaStanzeAdiacenti;
	}

    /**
     * Restituisce il nome della stanza.
     * @return il nome della stanza
     */
    public String getNome() {
        return this.nome;
    }
    
    /**
     * Restituisce il numero di attrezzi nella stanza.
     * @return il numero di attrezzi
     */
    public int getNumeroAttrezzi() {
		return numeroAttrezzi;
	}
    
    /**
     * Restituisce la descrizione della stanza.
     * @return la descrizione della stanza
     */
    public String getDescrizione() {
        return this.toString();
    }

    /**
     * Restituisce la collezione di attrezzi presenti nella stanza.
     * @return la collezione di attrezzi nella stanza.
     */
    public Collection<Attrezzo> getAttrezzi() {
        return this.attrezzi.values();
    }

    /**
     * Verifica se sono presenti attrezzi nella stanza.
     * @return true se non ci sono attrezzi, false altrimenti.
     */
    public boolean isEmpty() {
    	return this.numeroAttrezzi == 0;
    }
    
    /**
     * Mette un attrezzo nella stanza se l'attrezzo è già presente
	 * la stanza rimane inalterata.
     * @param attrezzo l'attrezzo da mettere nella stanza.
     * @return true se riesce ad aggiungere l'attrezzo, false altrimenti.
     */
    public boolean addAttrezzo(Attrezzo attrezzo) {
    	if(attrezzo==null) return false;
    	
        if (this.numeroAttrezzi < NUMERO_MASSIMO_ATTREZZI && !this.attrezzi.containsKey(attrezzo.getNome())) {
        	this.attrezzi.put(attrezzo.getNome(), attrezzo);
        	this.numeroAttrezzi++;
        	return true;
        }
        else {
        	return false;
        }
    }

    /**
	* Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	* @return true se l'attrezzo esiste nella stanza, false altrimenti.
	*/
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}

	/**
     * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
     * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo attrezzoCercato = null;

		if (this.attrezzi.containsKey(nomeAttrezzo))
			attrezzoCercato = this.attrezzi.get(nomeAttrezzo);
		return attrezzoCercato;
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base all'attrezzo).
	 * @param attrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		if(attrezzo!=null && this.attrezzi.containsKey(attrezzo.getNome())){
			this.attrezzi.remove(attrezzo.getNome(), attrezzo);
			numeroAttrezzi--;
			
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Cerca tutte le direzioni della stanza corrente memorizzandole in un Set.
	 * @return Set di stringhe delle direzioni possibili della stanza
	 */
	public Set<Direzione> getDirezioni() {
		return stanzeAdiacenti.keySet();
	}
	
	/**
	 * Aggiunge un personaggio alla stanza corrente
	 * @param il personaggio da aggiungere
	 */
	public void setPersonaggio(AbstractPersonaggio personaggio) {
		this.personaggio = personaggio;
	}
	
	/**
	 * Ritorna il personaggio presente nella stanza
	 * @return il personaggio nella stanza
	 */
	public AbstractPersonaggio getPersonaggio() {
		return this.personaggio;
	}
	
	/**
	* Restituisce una rappresentazione stringa di questa stanza,
	* stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	* @return la rappresentazione stringa
	*/
    public String toString() {
    	StringBuilder risultato = new StringBuilder();
    	risultato.append(this.nome);
    	risultato.append("\nUscite: ");
    	risultato.append(this.getDirezioni().toString());
    	risultato.append("\nAttrezzi nella stanza: ");
    	
    	if(numeroAttrezzi!=0) {
    		risultato.append(this.getAttrezzi().toString());
    	}
    	else
    		risultato.append("Nessun attrezzo");
    	
    	if(this.personaggio!=null) {
    		risultato.append("\nC'è " + this.personaggio.getClass().getSimpleName() + " " 
    						+ this.personaggio.getNome() + " nella stanza, prova a parlargli!");
    	}

    	return risultato.toString();
    }
	
    @Override
	public int hashCode() {
		return this.nome.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Stanza that = (Stanza) obj;
		return this.getNome().equals(that.getNome());
	}

}