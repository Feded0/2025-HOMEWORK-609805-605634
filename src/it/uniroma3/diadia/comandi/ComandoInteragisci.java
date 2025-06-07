package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

/**
 * Classe che gestisce il comando interagisci 
 * 
 * @author docente di POO 
 * @author Modificato da Feded0 (609805) e Civan04 (605634)
 * @see FabbricaDiComandiRiflessiva
 * @see AbstractComando
 * @version C
*/

public class ComandoInteragisci extends AbstractComando {
	private static final String MESSAGGIO_CON_CHI = "Con chi dovrei interagire?...";

	private String messaggio;
	
	/**
	 * Se nella stanza è presente un personaggio, esegue l'azione definita dal personaggio stesso
	 * altrimenti, informa l'utente che non c'è nessuno con cui interagire.
	 * 
	 * @param partita
	 */
	@Override
	public void esegui(Partita partita) {
		
		AbstractPersonaggio personaggio;
		personaggio = partita.getStanzaCorrente().getPersonaggio();
		if (personaggio!=null) {
			this.messaggio = personaggio.agisci(partita);
			io.mostraMessaggio(this.messaggio);

		} else io.mostraMessaggio(MESSAGGIO_CON_CHI);
	}

	/**
	 * Override per ottenere il nome del comando corrente
	 * 
	 * @return stringa che identifica il nome del comando
	 */
	@Override
	public String getNome() {
		return "Interagisci";
	}
}