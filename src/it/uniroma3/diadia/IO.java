package it.uniroma3.diadia;

/**
 * Interfaccia che permette di disaccoppiare l’I/O astraendo IOConsole
 *
 * @author docente di POO
 * @see IOConsole
 * @version C
 */

public interface IO {
	public void mostraMessaggio(String messaggio);
	public String leggiRiga();
}