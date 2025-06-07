package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe concreta che rappresenta un personaggio di tipo Cane all'interno del gioco.
 * 
 * Il Cane ha un comportamento ostile: quando il giocatore interagisce con lui,
 * perde un CFU. Tuttavia, se riceve il suo cibo preferito ("osso"), 
 * reagisce positivamente regalando una palla. In caso contrario, ringhia
 * e penalizza il giocatore con la perdita di un altro CFU.
 * 
 * @author docente di POO
 * @author Modificato da Feded0 (609805) e Civan04 (605634)
 * @see AbstractPersonaggio
 * @see Partita
 * @see Attrezzo
 * @version C
 */

public class Cane extends AbstractPersonaggio {
	private static final String MESSAGGIO_DEFAULT = "BAU! BAU! BAU!";
	private static final String CIBO_PREFERITO = "osso";
	
	/**
	 * Costruisce un personaggio di tipo Cane con nome e messaggio di presentazione.
	 * 
	 * @param nome il nome del cane
	 * @param presentazione il messaggio di presentazione del cane
	 */
	public Cane(String nome, String presentazione) {
		super(nome, presentazione);
	}
	
	/**
	 * Metodo che definisce il comportamento del cane quando il giocatore interagisce con lui.
	 * Il giocatore perde un CFU.
	 * 
	 * @param partita la partita in corso
	 * @return un messaggio di ringhio
	 */
	@Override
	public String agisci(Partita partita) {
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		
		return MESSAGGIO_DEFAULT;
	}
	
	/**
	 * Metodo che definisce la reazione del cane quando riceve un attrezzo come regalo.
	 * Se il regalo è il cibo preferito (ossia "osso"), il cane regala al giocatore una palla.
	 * Altrimenti, ringhia e toglie un CFU al giocatore.
	 * 
	 * @param attrezzo l'attrezzo offerto come regalo
	 * @param partita la partita in corso
	 * @return un messaggio che descrive la reazione del cane
	 */
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		StringBuilder risposta = new StringBuilder("BAU grazie per avermi regalato ");

		if(attrezzo.getNome().equals(CIBO_PREFERITO)) {
			risposta.append(attrezzo.getNome() + ", è il mio cibo preferito! Tieni una palla");
			partita.getStanzaCorrente().addAttrezzo(new Attrezzo("palla", 1));
		} else {
			risposta.append(attrezzo.getNome() + ", ma non mi piace, BAU! BAU!");
			partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		}

		return risposta.toString();
	}
	
}