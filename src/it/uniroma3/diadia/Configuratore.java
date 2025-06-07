package it.uniroma3.diadia;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Questa classe si occupa di leggere e fornire configurazioni
 * dell'applicazione dal file diadia.properties, come il peso massimo della borsa
 * e i crediti formativi universitari (CFU) iniziali disponibili per il giocatore.
 * 
 * Il file di configurazione può essere caricato sia dal classpath (utile in caso di esecuzione
 * tramite JAR), sia direttamente dal filesystem. Se il file non viene trovato o se si verifica
 * un errore durante la lettura, la classe stampa un messaggio di errore su standard error.
 * 
 * Le proprietà vengono caricate una sola volta alla prima richiesta, e memorizzate in un oggetto statico.
 * 
 * 
 * @authorFeded0 (609805) e Civan04 (605634)
 * @version C
 */


public final class Configuratore {

	private static final String DIADIA_PROPERTIES = "diadia.properties";
	private static final String PESO_MAX = "pesoMax";
	private static final String CFU = "cfu";
	private static Properties prop = null;

	public static int getCFU() {
		if(prop == null)
			carica();
		return Integer.parseInt(prop.getProperty(CFU));
	}

	public static int getPesoMax() {
		if(prop == null)
			carica();
		return Integer.parseInt(prop.getProperty(PESO_MAX));
	}

	private static void carica() {
		prop = new Properties();

		//Lettura da classpath per JAR
		try (InputStream in = Configuratore.class.getResourceAsStream("/" + DIADIA_PROPERTIES)) {
			if (in != null) {
				prop.load(in);
				return; 
			} 
		} catch (IOException e) {
			System.err.println("Errore durante il caricamento da classpath:");
			e.printStackTrace();
		}

		//Se non presente in classpath allora legge da filesystem (JAR NON funziona)
		try (InputStream fis = new FileInputStream(DIADIA_PROPERTIES)) {
			prop.load(fis);
			return;

		} catch (IOException e) {
			System.err.println("Errore durante il caricamento da filesystem:");
			e.printStackTrace();
		}

		//Nessun file trovato
		System.err.println("Impossibile caricare " + DIADIA_PROPERTIES + ": file mancante.");
	}
}