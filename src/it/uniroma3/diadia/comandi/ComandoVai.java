package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;
import it.uniroma3.diadia.Partita;

/**
 * Classe che gestisce il comando vai 
 * 
 * @author docente di POO 
 * @author Modificato da Feded0 (609805) e Civan04 (605634)
 * @see FabbricaDiComandiRiflessiva
 * @see AbstractComando
 * @version C
*/

public class ComandoVai extends AbstractComando {
	
	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 * 
	 * @param partita
	 */
	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = null;
		
		if (this.getParametro() == null) {
			io.mostraMessaggio("Dove vuoi andare? Devi specificare una direzione");
			return;
		}
		
		if(this.getParametro()!=null) 
			try {
			prossimaStanza = stanzaCorrente.getStanzaAdiacente(Direzione.valueOf(this.getParametro()));
			} catch(IllegalArgumentException e) {
				io.mostraMessaggio("Direzione inesistente");
				return;
			}
			
			if (prossimaStanza == null) {
			io.mostraMessaggio("Direzione inesistente");
			return;
			}

		partita.setStanzaCorrente(prossimaStanza);
		io.mostraMessaggio(partita.getStanzaCorrente().getNome());
		Giocatore giocatore = partita.getGiocatore();
		giocatore.setCfu(giocatore.getCfu() - 1);
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
	
}