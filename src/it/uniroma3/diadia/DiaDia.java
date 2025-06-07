package it.uniroma3.diadia;

import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author docente di POO (da un'idea di Michael Kolling and David J. Barnes)
 * @author Modificato da Feded0 (609805) e Civan04 (605634)
 * @version C
 */

public class DiaDia{

	static final private String MESSAGGIO_BENVENUTO = ""
			+ "Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n"
			+ "Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"
			+ "I locali sono popolati da strani personaggi, " + "alcuni amici, altri... chissa!\n"
			+ "Ci sono attrezzi che potrebbero servirti nell'impresa:\n"
			+ "puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n"
			+ "o regalarli se pensi che possano ingraziarti qualcuno.\n\n"
			+ "Per conoscere le istruzioni usa il comando 'aiuto'.";

	private Partita partita;
	private IO io;
	
	public DiaDia(Labirinto l, IO io) {
		this.io = io;
		this.partita = new Partita(l);
	}

	public void gioca() throws Exception {
		String istruzione;

		io.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do
			istruzione = io.leggiRiga();
		while (!processaIstruzione(istruzione));
	}

	/**
	 * Processa una istruzione
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false
	 *         altrimenti
	 * @throws Exception 
	 */

	private boolean processaIstruzione(String istruzione) throws Exception {
		Comando comandoDaEseguire;
		FabbricaDiComandiRiflessiva factory = new FabbricaDiComandiRiflessiva(this.io);

		comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita);

		if (this.partita.vinta())
			io.mostraMessaggio("Hai vinto!");

		if (!this.partita.giocatoreIsVivo())
			io.mostraMessaggio("Hai esaurito i CFU...");

		return this.partita.isFinita();
	}

	public static void main(String[] argc) throws Exception {
		Scanner scanner = new Scanner(System.in);
		IO io = new IOConsole(scanner);
		 
		Labirinto labirinto = Labirinto.newBuilder("LabirintoCorrente.txt").getLabirinto();
		 
		DiaDia gioco = new DiaDia(labirinto, io);
		gioco.gioca();
		scanner.close();
	}

}