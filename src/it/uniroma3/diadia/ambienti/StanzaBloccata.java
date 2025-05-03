package it.uniroma3.diadia.ambienti;

/**
 * Questa classe è un estensione della classe Stanza, permette di creare 
 * una stanza che ha una delle direzioni adiacenti bloccate, sarà necessario 
 * un attrezzo speficio per poterla sbloccare
 *
 * @author Feded0 (609805) e Civan04 (605634)
 * @see Stanza
 * @version B
 */

public class StanzaBloccata extends Stanza {
	String direzioneBloccata;
	String attrSblocco;
	
	/**
     * Costruttore per la stanza bloccata
     * 
     * @param nome il nome della stanza
     * @param direzioneBloccata la direzione non accessibile
     * @param attrSblocco l'attrezzo di sblocco
     */
	public StanzaBloccata(String nome, String direzioneBloccata, String attrSblocco) {
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.attrSblocco = attrSblocco;
	}
	
	/**
     * Override per il getter della stanza adiacente, verifica se la 
     * direzione scelta è quella bloccata, altrimenti permette di procedere
     * 
     * @param direzione
     * @return stanza corrente se direzione bloccata, stanza adiacente altrimenti
     */
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if(direzione.equals(this.direzioneBloccata)&&!(this.hasAttrezzo(this.attrSblocco))) {
			return this;
		}
		else {
			return super.getStanzaAdiacente(direzione);
		}
	}
	
	/**
     * Override per la descrizione della stanza in base
     * se la stanza è bloccata o no
     * 
     * @return la descrizione della stanza
     */
	@Override
	public String getDescrizione() {
		if(!(this.hasAttrezzo(this.attrSblocco))) {
			return super.getDescrizione() + "\n\nLa direzione " + direzioneBloccata + 
					" è attualmente bloccata, serve " + attrSblocco + " per aprire";
		}
		else {
			return super.getDescrizione() + "\n\nDirezione " + direzioneBloccata + 
					" sbloccata con " + attrSblocco;
		}
	}
	
}
