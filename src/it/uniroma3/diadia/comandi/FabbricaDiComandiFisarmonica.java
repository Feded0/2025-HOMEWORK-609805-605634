package it.uniroma3.diadia.comandi;

import java.util.Scanner;
import it.uniroma3.diadia.IO;

/**
 * Classe che permette la lettura di un comando 
 * con il relativo parametro e ne invoca l'esecuzione
 * 
 * @author docente di POO 
 * @author Modificato da Feded0 (609805) e Civan04 (605634)
 * @see FabbricaDiComandi
 * @version C
*/

public class FabbricaDiComandiFisarmonica implements FabbricaDiComandi {
	private IO io;
	
	public FabbricaDiComandiFisarmonica(IO io) {
		this.io = io;
	}
	
	/**
	 * Override di costruisciComando che verifica in base all'input
	 * dell'utente il corretto comando da costruire
	 *
	 * @param istruzione input dell'utente
	 * @return comando il comando da eseguire
	 */
	@Override
	public Comando costruisciComando(String istruzione) {
		if(istruzione == null) { // per prevenire errori nello scanner
			istruzione = "";
		}
		
		try (Scanner scannerDiParole = new Scanner(istruzione)) {
			String nomeComando = null;
			String parametro = null;
			Comando comando = null;
			
			if (scannerDiParole.hasNext())
			nomeComando = scannerDiParole.next(); // prima parola: nome del comando
			if (scannerDiParole.hasNext())
				parametro = scannerDiParole.next(); // seconda parola: eventuale parametro
			if (nomeComando == null)
				comando = new ComandoNonValido();
			else if (nomeComando.equals("vai"))
				comando = new ComandoVai();
			else if (nomeComando.equals("prendi"))
				comando = new ComandoPrendi();
			else if (nomeComando.equals("posa"))
				comando = new ComandoPosa();
			else if (nomeComando.equals("aiuto"))
				comando = new ComandoAiuto();
			else if (nomeComando.equals("fine"))
				comando = new ComandoFine();
			else if (nomeComando.equals("guarda"))
				comando = new ComandoGuarda();
			else comando = new ComandoNonValido();
			
			comando.setParametro(parametro);
			comando.setIO(this.io);
			
			return comando;
		}
	}
}
