package it.uniroma3.diadia.comandi;

import java.util.Scanner;

import it.uniroma3.diadia.IO;

/**
 * Fabbrica di comandi che costruisce dinamicamente i comandi utilizzando la riflessione.

 * Data una stringa di istruzione, estrae il nome del comando e l'eventuale parametro,
 * istanzia la relativa classe comando cercandola nel package it.uniroma3.diadia.comandi, 
 * e inizializza il comando con parametro e IO.
 * Se il comando non è valido o non esiste, viene restituito un ComandoNonValido.

 * @author docente di POO
 * @author Modificato da Feded0 (609805) e Civan04 (605634)
 * @see Comando
 * @version C
 */

public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi {
	private IO io;

	public FabbricaDiComandiRiflessiva(IO io) {
		this.io = io;
	}
	
	/**
	 * Costruisce il comando con la riflessione
	 * 
	 * @param istruzione da eseguire con relativo parametro
	 * @return il comando da eseguire
	 */
	@Override
	public Comando costruisciComando(String istruzione) {
		try (Scanner scannerDiParole = new Scanner(istruzione)) {
			String nomeComando = null;
			String parametro = null;
			Comando comando = null;
			
			if (scannerDiParole.hasNext())
				nomeComando = scannerDiParole.next();//prima parola: nome del comando
			if (scannerDiParole.hasNext())
				parametro = scannerDiParole.next();//seconda parola: eventuale parametro
			
			try {
				String nomeClasse = "it.uniroma3.diadia.comandi.Comando";
				nomeClasse += Character.toUpperCase(nomeComando.charAt(0));
				nomeClasse += nomeComando.substring(1);
				comando = (Comando)Class.forName(nomeClasse).newInstance();
				comando.setParametro(parametro);
				comando.setIO(io);
			} catch (Exception e) {
				comando = new ComandoNonValido();
				comando.setIO(io);
			}
			return comando;
		}
	}
}