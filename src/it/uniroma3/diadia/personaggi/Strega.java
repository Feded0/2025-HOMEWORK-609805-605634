package it.uniroma3.diadia.personaggi;

import java.util.List;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Rappresenta un personaggio di tipo Strega nel gioco.
 * Il comportamento della strega varia in base a se il giocatore l'ha salutata o meno:
 * 
 * Se è stata salutata, manda il giocatore nella stanza adiacente con più attrezzi.
 * Se NON è stata salutata, lo manda in quella con meno attrezzi.
 * 
 * La strega non accetta regali: li rifiuta sempre con disprezzo.
 * 
 * @author docente di POO
 * @author Modificato da Feded0 (609805) e Civan04 (605634)
 * @see AbstractPersonaggio
 * @see Partita
 * @see Attrezzo
 * @version C
 */

public class Strega extends AbstractPersonaggio {

	private static final String MESSAGGIO_SALUTATA = "Hai avuto la decenza di salutarmi... ora vai!";
	private static final String MESSAGGIO_NON_SALUTATA = "Ah, sei proprio pessimo... stanza con meno attrezzi per te!";

	/**
	 * Costruisce una strega con nome e presentazione iniziali.
	 * 
	 * @param nome il nome della strega
	 * @param presentaz la frase di presentazione
	 */
	public Strega(String nome, String presentaz) {
		super(nome, presentaz);
	}

	/**
	 * Definisce il comportamento della strega quando interagisce con il giocatore.
	 * In base al fatto che il giocatore l'abbia salutata o meno,
	 * lo sposta nella stanza adiacente con più o meno attrezzi.
	 * 
	 * @param partita la partita in corso
	 * @return il messaggio risultante dall'azione della strega
	 */
	@Override
	public String agisci(Partita partita) {
		String msg;
		List<Stanza> stanzeAdiacenti = partita.getStanzaCorrente().getStanzeAdiacenti();

		Stanza maxAtrezzi = stanzeAdiacenti.get(0);
		Stanza minAtrezzi = stanzeAdiacenti.get(0);

		for(Stanza s : stanzeAdiacenti) {
			if(s != null) {
				if(s.getNumeroAttrezzi() > maxAtrezzi.getNumeroAttrezzi())
					maxAtrezzi = s;
				if(s.getNumeroAttrezzi() < minAtrezzi.getNumeroAttrezzi())
					minAtrezzi = s;
			}
		}

		if(this.haSalutato()) {
			partita.setStanzaCorrente(maxAtrezzi);
			msg = MESSAGGIO_SALUTATA;
		} else {
			partita.setStanzaCorrente(minAtrezzi);
			msg = MESSAGGIO_NON_SALUTATA;
		}

		return msg;
	}
	
	/**
	 * La strega rifiuta qualsiasi regalo.
	 * 
	 * @param attrezzo l'attrezzo regalato dal giocatore
	 * @param partita la partita in corso
	 * @return un messaggio di disprezzo nei confronti del regalo ricevuto
	 */
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		return "Pensavi di corrompermi? AHAHAHAHAHAHA";
	}

}
