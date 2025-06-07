package it.uniroma3.diadia.ambienti;

/**
 * Questa classe è un estensione della classe Stanza, permette di creare 
 * una stanza buia ossia che non permette di leggere la sua descrizione
 * con le eventuali direzioni e attrezzi presenti
 *
 * @author Feded0 (609805) e Civan04 (605634)
 * @see Stanza
 * @version C
 */

public class StanzaBuia extends Stanza {
	String attrIlluminante;
	
	/**
     * Costruttore per la stanza buia
     * 
     * @param nome il nome della stanza
     * @param attrIlluminante attrezzo per illuminare la stanza
     */
	public StanzaBuia(String nome, String attrIlluminante) {
		super(nome);
		this.attrIlluminante = attrIlluminante;
	}
	
	/**
     * Override per la descrizione della stanza in base
     * se la stanza è buia o no
     * 
     * @return la descrizione della stanza
     */
	@Override
    public String getDescrizione() {
        if(!(this.hasAttrezzo(this.attrIlluminante))) {
            return "Qui c'è buio pesto!";
        }
        return super.getDescrizione();
    }
	
}
