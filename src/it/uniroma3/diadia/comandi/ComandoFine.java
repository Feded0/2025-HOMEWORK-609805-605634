package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;

/**
 * Classe che gestisce il comando fine 
 * 
 * @author docente di POO 
 * @author Modificato da Feded0 (609805) e Civan04 (605634)
 * @see FabbricaDiComandiRiflessiva
 * @see AbstractComando
 * @version C
*/

public class ComandoFine extends AbstractComando {

	/**
	 * Comando fine che termina immediatamente la partita
	 * 
	 * @param partita
	 */
	@Override
	public void esegui (Partita partita) {
		io.mostraMessaggio("Grazie di aver giocato!"); // si desidera smettere
		partita.setFinita();
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
		return "fine";
	}
	
}
