package it.uniroma3.diadia.comandi;

/**
 * Interfaccia che permette la costruzione di un comando
 *
 * @author docente di POO
 * @see FabbricaDiComandiRiflessiva
 * @version C
 */

public interface FabbricaDiComandi {
	public Comando costruisciComando(String istruzione) throws Exception; // (>> eccezioni)
}
