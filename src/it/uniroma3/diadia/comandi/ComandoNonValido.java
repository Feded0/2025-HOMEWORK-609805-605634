package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

/**
 * Classe che gestisce un comando non valido 
 * 
 * @author docente di POO 
 * @author Modificato da Feded0 (609805) e Civan04 (605634)
 * @see FabbricaDiComandiRiflessiva
 * @see AbstractComando
 * @version C
*/

public class ComandoNonValido extends AbstractComando {

	/**
	 * Indica che è stato inserito un comando non valido.
	 * 
	 * @param partita
	 */
	@Override
	public void esegui (Partita partita) {
		io.mostraMessaggio("Hai inserito un comando non valido");
	}
	
	/**
	 * Override per ottenere il nome del comando corrente
	 * 
	 * @return stringa che identifica il nome del comando
	 */
	@Override
	public String getNome() {
		return "non valido";
	}
	
}
