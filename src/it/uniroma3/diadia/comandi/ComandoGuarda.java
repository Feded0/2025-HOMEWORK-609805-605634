package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

/**
 * Classe che gestisce il comando guarda 
 * 
 * @author docente di POO 
 * @author Modificato da Feded0 (609805) e Civan04 (605634)
 * @see FabbricaDiComandiRiflessiva
 * @see AbstractComando
 * @version C
*/

public class ComandoGuarda extends AbstractComando {
	
	/**
	 * Stampa le informazioni sulla stanza corrente e sullo stato della partita.
	 * 
	 * @param partita
	 */
	@Override
	public void esegui (Partita partita) {
		if(this.getParametro()!=null && this.getParametro().toLowerCase().equals("borsa")) {
			io.mostraMessaggio(partita.getGiocatoreBorsa().getContenutoOrdinato());
		}
		else {
			io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
			io.mostraMessaggio("\nHai ancora: "+partita.getGiocatore().getCfu()+ "CFU");
			io.mostraMessaggio(partita.getGiocatoreBorsa().toString());
		}
	}
	
	/**
	 * Override per ottenere il nome del comando corrente
	 * 
	 * @return stringa che identifica il nome del comando
	 */
	@Override
	public String getNome() {
		return "guarda";
	}
	
}
