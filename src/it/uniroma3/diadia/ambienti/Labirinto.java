package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.CaricatoreLabirinto;
import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

/**
 * Questa classe modella le stanze del gioco creando il percorso per il
 * giocatore.
 * 
 * @author docente di POO
 * @author Modificato da Feded0 (609805) e Civan04 (605634)
 * @see Stanza
 * @version C
 */

public class Labirinto {

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;

	private Labirinto(String nomeFile) throws FileNotFoundException, FormatoFileNonValidoException {
		CaricatoreLabirinto c = new CaricatoreLabirinto(nomeFile);
		c.carica();
		this.stanzaIniziale = c.getStanzaIniziale();
		this.stanzaVincente = c.getStanzaVincente();
	}

	public static LabirintoBuilder newBuilder(String labirinto) throws FileNotFoundException, FormatoFileNonValidoException {
		return new LabirintoBuilder(labirinto);
	}
	
	/**
	 * Metodo getter per la stanza vincente
	 * 
	 * @return la stanza vincente
	 */
	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}
	
	/**
	 * Metodo setter per la stanza vincente
	 * 
	 * @param stanzaVincente
	 */
	public void setStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente = stanzaVincente;
	}

	/**
	 * Metodo getter per la stanza corrente
	 * 
	 * @return la stanza corrente
	 */
	public Stanza getStanzaCorrente() {
		return this.stanzaIniziale;
	}
	
	/**
	 * Metodo setter per la stanza corrente
	 * 
	 * @param stanzaCorrente
	 */
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaIniziale = stanzaCorrente;
	}

	
	public static class LabirintoBuilder {

		private Labirinto labirinto;
		private Stanza ultimaStanzaAggiunta;
		private Map<String, Stanza> nome2stanza;

		public LabirintoBuilder(String labirinto) throws FileNotFoundException, FormatoFileNonValidoException {
			this.labirinto = new Labirinto(labirinto);
			this.nome2stanza = new HashMap<>();
		}

		public Map<String, Stanza> getNome2stanza() {
			return nome2stanza;
		}

		public Labirinto getLabirinto() {
			return this.labirinto;
		}

		public LabirintoBuilder addStanzaIniziale(String stanzaIniziale) {
			Stanza i = new Stanza(stanzaIniziale);
			this.labirinto.setStanzaCorrente(i);
			this.UltimaStanzaAggiuntaEAggiorna(i);
			return this;
		}

		public LabirintoBuilder addStanzaVincente(String stanzaVincente) {
			Stanza s = new Stanza(stanzaVincente);
			this.labirinto.setStanzaVincente(s);
			this.UltimaStanzaAggiuntaEAggiorna(s);
			return this;
		}

		public LabirintoBuilder addStanza(String stanza) {
			Stanza s = new Stanza(stanza);
			this.UltimaStanzaAggiuntaEAggiorna(s);
			return this;
		}

		public LabirintoBuilder addMago(String nome, String presentazione, Attrezzo attrezzo) {
			Mago m = new Mago(nome, presentazione, attrezzo);
			if (this.ultimaStanzaAggiunta == null)
				return this;
			this.ultimaStanzaAggiunta.setPersonaggio(m);
			return this;
		}

		public LabirintoBuilder addCane(String nome, String presentazione) {
			Cane c = new Cane(nome, presentazione);
			if (this.ultimaStanzaAggiunta == null)
				return this;
			this.ultimaStanzaAggiunta.setPersonaggio(c);
			return this;
		}

		public LabirintoBuilder addStrega(String nome, String presentazione) {
			Strega s = new Strega(nome, presentazione);
			if (this.ultimaStanzaAggiunta == null)
				return this;
			this.ultimaStanzaAggiunta.setPersonaggio(s);
			return this;
		}

		public LabirintoBuilder addAttrezzo(String attrezzo, int peso) {
			Attrezzo a = new Attrezzo(attrezzo, peso);
			if (this.ultimaStanzaAggiunta == null)
				return this;
			this.ultimaStanzaAggiunta.addAttrezzo(a);
			return this;
		}

		public LabirintoBuilder addAdiacenza(String stanzaCorrente, String stanzaAdiecente, Direzione direzione) {
			Stanza c = this.nome2stanza.get(stanzaCorrente);
			Stanza a = this.nome2stanza.get(stanzaAdiecente);
			c.impostaStanzaAdiacente(direzione, a);
			return this;
		}

		public LabirintoBuilder addStanzaMagica(String nome) {
			Stanza stanza = new StanzaMagica(nome);
			this.UltimaStanzaAggiuntaEAggiorna(stanza);
			return this;
		}

		public LabirintoBuilder addStanzaBuia(String nome, String attrezzoPerVedere) {
			Stanza stanza = new StanzaBuia(nome, attrezzoPerVedere);
			this.UltimaStanzaAggiuntaEAggiorna(stanza);
			return this;
		}

		public LabirintoBuilder addStanzaBloccata(String nome, String attrezzoSbloccante, String direzioneBloccata) {
			Stanza stanza = new StanzaBloccata(nome, Direzione.valueOf(direzioneBloccata), attrezzoSbloccante);
			this.UltimaStanzaAggiuntaEAggiorna(stanza);
			return this;
		}

		public void UltimaStanzaAggiuntaEAggiorna(Stanza stanza) {
			this.ultimaStanzaAggiunta = stanza;
			this.nome2stanza.put(stanza.getNome(), stanza);
		}

	}

}
