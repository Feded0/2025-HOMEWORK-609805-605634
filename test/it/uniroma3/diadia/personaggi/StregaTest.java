package it.uniroma3.diadia.personaggi;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Questa classe testa tutti i metodi della classe Strega
 *
 * @author Feded0 (609805) e Civan04 (605634)
 * @see Strega
 * @version C
 */

class StregaTest {

	private Strega strega;
	private Partita partita;

	@BeforeEach
	void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
		this.strega = new Strega("Malvagia", "Io ti guiderò...");
		this.partita = new Partita(Labirinto.newBuilder("LabirintoPerTest.txt").getLabirinto());
		this.partita.getStanzaCorrente().setPersonaggio(strega);
	}

	@Test
	void testAgisci_SenzaSalutoMandaNellaStanzaConMenoAttrezzi() {
		String messaggio = strega.agisci(partita);
		String nomeStanza = partita.getStanzaCorrente().getNome();
		assertTrue(nomeStanza.equals("AulaN11") || nomeStanza.equals("Biblioteca"));
		assertTrue(messaggio.contains("pessimo"));
	}

	@Test
	void testAgisci_ConSalutoMandaNellaStanzaConPiuAttrezzi() {
		strega.saluta();
		String messaggio = strega.agisci(partita);
		String nomeStanza = partita.getStanzaCorrente().getNome();
		assertEquals("LaboratorioCampus", nomeStanza);
		assertTrue(messaggio.contains("decenza"));
	}

	@Test
	void testRiceviRegalo() {
		String risposta = strega.riceviRegalo(new Attrezzo("finto", 1), partita);
		assertTrue(risposta.contains("corrompermi"));
	}
}
