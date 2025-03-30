package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 * @author Modificato da Feded0 (609805) e Civan04 (605634)
 * @version A
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	static final private String[] elencoComandi = {"vai", "prendi", "posa", "aiuto", "fine"};

	private Partita partita;
	private IOConsole ioConsole;

	public DiaDia() {
		this.ioConsole = new IOConsole();
		this.partita = new Partita();
	}

	public void gioca() {
		String istruzione; 

		ioConsole.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do		
			istruzione = ioConsole.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);
		
		if(comandoDaEseguire.getNome()==null)
			ioConsole.mostraMessaggio("Nessun comando inserito");
		else if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else if (comandoDaEseguire.getNome().equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("posa"))
			this.posa(comandoDaEseguire.getParametro());
		else
			ioConsole.mostraMessaggio("Comando sconosciuto");
		if (this.partita.vinta()) {
			ioConsole.mostraMessaggio("Hai vinto!");
			return true;
		}
		else if(this.partita.isFinita()) {
				ioConsole.mostraMessaggio("Hai perso, partita terminata");
				return true;
		} else
			return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			ioConsole.mostraMessaggio("Dove vuoi andare ?");
		else {
			Stanza prossimaStanza = null;
			prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
			if (prossimaStanza == null)
				ioConsole.mostraMessaggio("Direzione inesistente");
			else {
				this.partita.setStanzaCorrente(prossimaStanza);
				int cfu = this.partita.getGiocatore().getCfu();
				this.partita.getGiocatore().setCfu(cfu-1);
			}	
		}
		ioConsole.mostraMessaggio(partita.getStanzaCorrente().getDescrizione()+"\n[CFU correnti: "+this.partita.getGiocatore().getCfu()+"]");
	}
	
	/**
	 * Prende un oggetto (se presente) dalla stanza corrente 
	 * e lo aggiunge alla borsa (se libera) 
	 * @param nomeAttrezzo
	 */
	private void prendi(String nomeAttrezzo) {

		if(nomeAttrezzo==null) {
			ioConsole.mostraMessaggio("Nessun attrezzo inserito");
		}
		else {
			if(this.partita.getStanzaCorrente().isEmpty()) {
				ioConsole.mostraMessaggio("La stanza non ha attrezzi, nulla da raccogliere");
			}
			else {
				Attrezzo attrezzo = this.partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
				if(attrezzo==null) {
					ioConsole.mostraMessaggio("L'attrezzo non è stato trovato");
				}
				else {
					if(this.partita.getGiocatoreBorsa().addAttrezzo(attrezzo)) {
						this.partita.getStanzaCorrente().removeAttrezzo(attrezzo);
						ioConsole.mostraMessaggio(nomeAttrezzo+" aggiunto all'inventario\n"+this.partita.getGiocatoreBorsa());
					}
					else {
						ioConsole.mostraMessaggio("Borsa piena non posso aggiungere l'attrezzo");	
					}
				}
			}
		}
	}

	/**
	 * Prende un oggetto (se presente) dalla borsa 
	 * e lo aggiunge alla stanza corrente (se libera) 
	 * @param nomeAttrezzo
	 */
	private void posa(String nomeAttrezzo) {

		if(nomeAttrezzo==null) {
			ioConsole.mostraMessaggio("Nessun attrezzo inserito");
		}
		else {
			if(this.partita.getGiocatoreBorsa().isEmpty()) {
				ioConsole.mostraMessaggio("La borsa non ha attrezzi, nulla da posare");
			}
			else {
				Attrezzo attrezzo = this.partita.getGiocatoreBorsa().getAttrezzo(nomeAttrezzo);
				if(attrezzo==null) {
					ioConsole.mostraMessaggio("L'attrezzo non è stato trovato\n"+this.partita.getGiocatoreBorsa());
				}
				else {
					if(this.partita.getStanzaCorrente().addAttrezzo(attrezzo)) {
						this.partita.getGiocatoreBorsa().removeAttrezzo(attrezzo);
						ioConsole.mostraMessaggio(nomeAttrezzo+" aggiunto alla stanza\n"+this.partita.getGiocatoreBorsa());
					}
					else {
						ioConsole.mostraMessaggio("Stanza piena non posso aggiungere l'attrezzo");	
					}
				}
			}
		}
	}
	
	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			ioConsole.mostraMessaggio(elencoComandi[i]);
		ioConsole.mostraMessaggio("");
	}
	
	/**
	 * Comando "Fine".
	 */
	private void fine() {
		ioConsole.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}
	
}