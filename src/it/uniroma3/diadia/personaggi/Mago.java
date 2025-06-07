package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Rappresenta un personaggio di tipo Mago nel gioco.
 * 
 * Il mago può donare un attrezzo presente nel suo inventario alla stanza corrente
 * quando il giocatore interagisce con lui. Se l'attrezzo è già stato donato, 
 * il mago non ha più nulla da offrire.
 * 
 * Quando riceve un regalo, alleggerisce l'attrezzo dimezzandone il peso
 * e lo deposita nella stanza corrente.
 * 
 * @author docente di POO
 * @author Modificato da Feded0 (609805) e Civan04 (605634)
 * @see AbstractPersonaggio
 * @see Partita
 * @see Attrezzo
 * @version C
 */

public class Mago extends AbstractPersonaggio {
	private static final String MESSAGGIO_DONO = "Sei un vero simpaticone, " +
			"con una mia magica azione, troverai un nuovo oggetto " +
			"per il tuo borsone!";
	private static final String MESSAGGIO_SCUSE = "Mi spiace, ma non ho piu' nulla...";
	private Attrezzo attrezzo;

	/**
	 * Costruisce un mago con nome, presentazione e un attrezzo da donare.
	 * 
	 * @param nome il nome del mago
	 * @param presentazione la presentazione del mago
	 * @param attrezzo l'attrezzo iniziale che può essere donato alla stanza
	 */
	public Mago(String nome, String presentazione, Attrezzo attrezzo) {
		super(nome, presentazione);
		this.attrezzo = attrezzo;
	}

	/**
	 * Definisce il comportamento del mago quando il giocatore interagisce con lui.
	 * Se ha ancora un attrezzo da donare, lo deposita nella stanza corrente.
	 * Altrimenti, informa il giocatore che non ha più nulla da offrire.
	 * 
	 * @param partita la partita in corso
	 * @return un messaggio che descrive l'azione compiuta dal mago
	 */
	@Override
	public String agisci(Partita partita) {
		String msg;
		if (this.attrezzo!=null) {
			partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
			this.attrezzo = null;
			msg = MESSAGGIO_DONO;
		}
		else {
			msg = MESSAGGIO_SCUSE;
		}
		return msg;
	}
	
	/**
	 * Definisce la reazione del mago quando riceve un attrezzo in regalo.
	 * Il mago restituisce lo stesso attrezzo con peso dimezzato,
	 * depositandolo nella stanza corrente.
	 * 
	 * @param attrezzo l'attrezzo regalato dal giocatore
	 * @param partita la partita in corso
	 * @return un messaggio che descrive l'interazione
	 */
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		StringBuilder risposta = new StringBuilder("Grazie per il regalo ");
		risposta.append(attrezzo.getNome()+".");
		risposta.append(" Ora lo alleggerirò!");
		
		Attrezzo attrezzoModificato = new Attrezzo(attrezzo.getNome(), attrezzo.getPeso()/2);
		partita.getStanzaCorrente().addAttrezzo(attrezzoModificato);
		
		return risposta.toString();
	}
	
}