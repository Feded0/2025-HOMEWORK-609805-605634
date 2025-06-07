package it.uniroma3.diadia;

import java.util.Scanner;

/**
 * Questa classe modella le stampe e le letture da console.
 * 
 * @author docente di POO
 * @version C
 */

public class IOConsole implements IO {
	
Scanner scannerDiLinee;
	
	public IOConsole(Scanner scanner) {
		this.scannerDiLinee = scanner;
	}
	
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}
	
	public String leggiRiga() {
		String riga = scannerDiLinee.nextLine();
		return riga;
	}
	
}