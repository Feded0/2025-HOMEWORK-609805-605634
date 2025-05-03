package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

/**
 * Classe che gestisce il comando vai 
 * 
 * @author docente di POO 
 * @author Modificato da Feded0 (609805) e Civan04 (605634)
 * @see FabbricaDiComandiFisarmonica
 * @version B
*/

public class ComandoVai implements Comando {
	private IO io;
	private String direzione;
	
	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	@Override
	public void esegui (Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = null;
		
		if (this.direzione==null) {
			io.mostraMessaggio("Dove vuoi andare? Devi specificare una direzione");
			return;		
		}
		
		prossimaStanza = stanzaCorrente.getStanzaAdiacente (this.direzione);
		if (prossimaStanza==null) {
			io.mostraMessaggio("Direzione inesistente");
			return;
		}
		
		partita.setStanzaCorrente (prossimaStanza);
		io.mostraMessaggio(partita.getStanzaCorrente().getNome());
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
	}
	
	/**
	 * Override per impostare il parametro corrente
	 * 
	 * @param parametro stringa dell'eventuale parametro
	 */
	@Override
	public void setParametro (String parametro) {
		this.direzione = parametro;
	}
	
	/**
	 * Override per impostare la console dal main
	 * 
	 * @param io console istanziata nel main
	 */
	@Override
	public void setIO(IO io) {
		this.io = io;
	}
	
	/**
	 * Override per ottenere il nome del comando corrente
	 * 
	 * @return stringa che identifica il nome del comando
	 */
	@Override
	public String getNome() {
		return "vai";
	}
	
	/**
	 * Override per ottenere il nome del parametro corrente
	 * 
	 * @return stringa che identifica il nome del parametro
	 */
	@Override
	public String getParametro() {
		return this.direzione;
	}
	
}