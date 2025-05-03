package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Questa classe è un estensione della classe Stanza, permette di creare una stanza magica 
 * ossia una stanza che dopo N volte che viene posato (aggiunto) un qualsiasi attrezzo da parte
 * del giocatore, la stanza inizierà a comportarsi «magicamente» – quando la stanza si comporta 
 * magicamente, ogni volta che posiamo un attrezzo, la stanza "inverte" il nome dell'attrezzo e
 * ne raddoppia il peso.
 *
 * Versione creata usando metodi che sfruttando variabili con visibilità protected in StanzaProtected
 *
 * @author docente di POO
 * @author Modificato da Feded0 (609805) e Civan04 (605634)
 * @see StanzaProtected
 * @version B
 */

public class StanzaMagicaProtected extends StanzaProtected {
	final static private int SOGLIA_MAGICA_DEFAULT = 3;
	private int contatoreAttrezziPosati;
	private int sogliaMagica;

	/**
     * Costruttore per la stanza magica che usa la soglia di default
     * 
     * @param nome il nome della stanza
     */
	public StanzaMagicaProtected(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
	}

	/**
     * Costruttore per la stanza magica con soglia custom
     * 
     * @param nome il nome della stanza
     * @param soglia quante volte un attrezzo 
     * 		  deve essere posato prima di far 
     * 		  diventare la stanza magica
     */
	public StanzaMagicaProtected(String nome, int soglia) {
		super(nome);
		this.contatoreAttrezziPosati = 0;
		this.sogliaMagica = soglia;
	}

	/**
     * Override per addAttrezzo che verifica se l'attrezzo 
     * deve essere inserito in modo magico, e successivamente 
     * viene aggiunto dal metodo add della superclasse Stanza
     * 
     * @param attrezzo l'attrezzo da mettere nella stanza.
     * @return true se riesce ad aggiungere l'attrezzo, false altrimenti.
     */
	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		this.contatoreAttrezziPosati++;
		
		if (this.contatoreAttrezziPosati > this.sogliaMagica)
			attrezzo = this.modificaAttrezzo(attrezzo);
		
		if (this.numeroAttrezzi<this.attrezzi.length) {
			this.attrezzi[this.numeroAttrezzi] = attrezzo;
			this.numeroAttrezzi++;
			return true;

		}
		else return false;
	}

	/**
     * Modifica un attrezzo invertendone il nome 
     * e raddoppiandone il peso
     * 
     * @param attrezzo l'attrezzo da modificare
     * @return attrezzo l'attrezzo modificato
     */
	private Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		StringBuilder nomeInvertito;
		int pesoX2 = attrezzo.getPeso() * 2;
		nomeInvertito = new StringBuilder(attrezzo.getNome());
		nomeInvertito = nomeInvertito.reverse();
		attrezzo = new Attrezzo(nomeInvertito.toString(),pesoX2);

		return attrezzo;
	}
	
}