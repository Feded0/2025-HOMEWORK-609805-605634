package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

/**
 * Classe astratta che fornisce una base comune per l'implementazione dei comandi del gioco.
 * 
 * Incapsula il comportamento comune di tutti i comandi, come la gestione dell'input/output
 * e del parametro, e delega l'esecuzione specifica alla sottoclasse concreta esegui.
 * 
 * @author docente di POO
 * @author Modificato da Feded0 (609805) e Civan04 (605634)
 * @see Comando
 * @version C
 */

public abstract class AbstractComando implements Comando {
    protected IO io;
    protected String parametro;

    abstract public void esegui(Partita partita);
    
    /**
	 * Override per impostare la console dal main
	 * 
	 * @param io console istanziata nel main
	 */
    @Override
    public void setIO(IO io) {
        this.io = io;
    }
    
    /**
	 * Override per impostare il parametro corrente
	 * 
	 * @param parametro stringa dell'eventuale parametro
	 */
    @Override
    public void setParametro(String parametro) {
        this.parametro = parametro;
    }
    
    /**
	 * Override per ottenere il nome del parametro corrente
	 * 
	 * @return stringa che identifica il nome del parametro
	 */
    @Override
    public String getParametro() {
        return this.parametro;
    }

}
