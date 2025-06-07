package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe che gestisce il comando posa 
 * 
 * @author docente di POO 
 * @author Modificato da Feded0 (609805) e Civan04 (605634)
 * @see FabbricaDiComandiRiflessiva
 * @see AbstractComando
 * @version C
*/

public class ComandoPosa extends AbstractComando {
	private String nomeAttrezzo;
	
	/**
	 * Prende un oggetto (se presente) dalla borsa e lo aggiunge alla stanza
	 * corrente (se libera)
	 * 
	 * @param partita
	 */
	@Override
	public void esegui (Partita partita) {
		nomeAttrezzo = getParametro();
		
		if (nomeAttrezzo == null) {
			io.mostraMessaggio("Nessun attrezzo inserito");
		} else {
			if (partita.getGiocatoreBorsa().isEmpty()) {
				io.mostraMessaggio("La borsa non ha attrezzi, nulla da posare");
			} else {
				Attrezzo attrezzo = partita.getGiocatoreBorsa().getAttrezzo(nomeAttrezzo);
				if (attrezzo == null) {
					io.mostraMessaggio("L'attrezzo non è stato trovato\n" + partita.getGiocatoreBorsa());
				} else {
					if (partita.getStanzaCorrente().addAttrezzo(attrezzo)) {
						partita.getGiocatoreBorsa().removeAttrezzo(attrezzo.getNome());
						io.mostraMessaggio(nomeAttrezzo + " aggiunto alla stanza\n" + partita.getGiocatoreBorsa());
					} else {
						io.mostraMessaggio("Non c'è abbastanza spazio nella stanza, non posso aggiungere l'attrezzo");
					}
				}
			}
		}
	}
	
	/**
	 * Override per ottenere il nome del comando corrente
	 * 
	 * @return stringa che identifica il nome del comando
	 */
	@Override
	public String getNome() {
		return "posa";
	}
	
}
