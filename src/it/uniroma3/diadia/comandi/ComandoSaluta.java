package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

/**
 * Classe che gestisce il comando saluta 
 * 
 * @author docente di POO 
 * @author Modificato da Feded0 (609805) e Civan04 (605634)
 * @see FabbricaDiComandiRiflessiva
 * @see AbstractComando
 * @version C
*/

public class ComandoSaluta extends AbstractComando {

	/**
	 * Se un personaggio è presente nella stanza viene salutato
	 * 
	 * @param partita
	 */
	@Override
	public void esegui(Partita partita) {
		if(partita.getStanzaCorrente().getPersonaggio()!=null)
			io.mostraMessaggio(partita.getStanzaCorrente().getPersonaggio().saluta());
		else 
			io.mostraMessaggio("Non c'è alcun personaggio in questa stanza!");
	}

	/**
	 * Override per ottenere il nome del comando corrente
	 * 
	 * @return stringa che identifica il nome del comando
	 */
	@Override
	public String getNome() {
		return "saluta";
	}
	
}
