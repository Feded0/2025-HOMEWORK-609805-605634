package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Set;
import java.util.TreeSet;
import java.net.URL;
import java.net.URLDecoder;
import java.util.jar.*;

/**
 * Classe che gestisce il comando aiuto 
 * 
 * @author docente di POO 
 * @author Modificato da Feded0 (609805) e Civan04 (605634)
 * @see FabbricaDiComandiRiflessiva
 * @see AbstractComando
 * @version C
 */

public class ComandoAiuto extends AbstractComando {

	private static final String PACKAGE = "it.uniroma3.diadia.comandi";

	/**
	 * Stampa informazioni di aiuto con l'elenco dei comandi disponibili
	 * leggendoli direttamente dal package comandi
	 * 
	 * @param partita
	 */
	@Override
	public void esegui (Partita partita) {

		// Ottieni il ClassLoader per il package
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

		// Carica il package come risorsa
		String path = PACKAGE.replace('.', '/');
		URL resource = classLoader.getResource(path);

		if (resource == null) {
			throw new RuntimeException("Package non trovato: " + PACKAGE);
		}	


		// Gestione da file JAR
		if (resource.getProtocol().equals("jar")) {

			try {
				String rawJarPath = resource.getPath().substring(5, resource.getPath().indexOf("!"));
				
				// Decodifica path se il file JAR è inserito in una cartella con nome che contiene spazi
                String jarPath = URLDecoder.decode(rawJarPath, "UTF-8");
                JarFile jar = new JarFile(jarPath);

				Enumeration<JarEntry> entries = jar.entries();
				Set<String> set = new TreeSet<String>(); // TreeSet per la stampa in ordine alfabetico
				while (entries.hasMoreElements()) {
					String entry = entries.nextElement().toString();

					if (entry.contains(path) && entry.contains("Comando") &&
							!entry.contains("Test") && !entry.contains("NonValido") && !entry.contains("Abstract")) {

						entry = entry
								.replace(path + "/", "")
								.replace("Comando", "")
								.replace(".class", "");
						
						if(!entry.equals("")) {
							set.add(entry);
						}
					}
				}
				for (String comando : set) {
				    io.mostraMessaggio(comando);
				}
				jar.close();
			} catch (IOException e)  {
				io.mostraMessaggio("Errore durante la lettura del JAR");
				e.printStackTrace();
			};
		} 
		else {

			// Gestione da filesystem
			File directory = new File(resource.getFile());
			if (directory.exists()) {
				for (String file : directory.list()) {
					if (file.startsWith("Comando") && 
							!file.contains("Test") && !file.contains("NonValido")) {

						file = file
								.replace("Comando", "")
								.replace(".class", "");
						
						if(!file.equals("")) {
							io.mostraMessaggio(file);
						}
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
		return "aiuto";
	}

}
