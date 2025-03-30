package it.uniroma3.diadia;

import java.util.Scanner;

/**
 * Questa classe modella le stampe e le letture da console.
 * 
 * @author  docente di POO
 * @version A
 */

public class IOConsole {
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}
	public String leggiRiga() {
		Scanner scannerDiLinee = new Scanner(System.in);
		String riga = scannerDiLinee.nextLine();
		//scannerDiLinee.close();
		return riga;
	}
	
}