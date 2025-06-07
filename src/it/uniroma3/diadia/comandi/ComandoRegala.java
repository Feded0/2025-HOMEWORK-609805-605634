package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe che gestisce il comando regala 
 * 
 * @author docente di POO 
 * @author Modificato da Feded0 (609805) e Civan04 (605634)
 * @see FabbricaDiComandiRiflessiva
 * @see AbstractComando
 * @version C
*/

public class ComandoRegala extends AbstractComando {
	
	/**
	 * Se un personaggio è presente nella stanza gli viene regalato 
	 * l'attrezzo passato come parametro
	 * 
	 * @param partita
	 */
	@Override
	public void esegui(Partita partita) {

		if(partita.getStanzaCorrente().getPersonaggio()!=null) {
			if(this.getParametro()==null) {
				io.mostraMessaggio("Devi specificare un oggetto da regalare");
			}
			else {
				Attrezzo attrezzo = partita.getGiocatore().getBorsa().getAttrezzo(this.getParametro());

				if(attrezzo!=null) {
					io.mostraMessaggio(partita.getStanzaCorrente().getPersonaggio().riceviRegalo(attrezzo, partita));	
					partita.getGiocatore().getBorsa().removeAttrezzo(this.getParametro());
				}
				else {
					io.mostraMessaggio("Attrezzo non trovato");
				}	
			}
		}
		else {
			io.mostraMessaggio("Non ci sono personaggi nella stanza, nulla da regalare");
		}
	}

	/**
	 * Override per ottenere il nome del comando corrente
	 * 
	 * @return stringa che identifica il nome del comando
	 */
	@Override
	public String getNome() {
		return "regala";
	}
}
