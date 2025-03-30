package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe testa tutti i metodi della classe Partita
 *
 * @author Feded0 (609805) e Civan04 (605634)
 * @see Partita
 * @version A
 */

class PartitaTest {
	private Partita partita;
	private Stanza stanza;
	private Stanza stanzaVincente;

	@BeforeEach
	public void setUp() {
		partita = new Partita();
		stanza = new Stanza("Stanza");
		
		// Otteniamo la stanza vincente spostandoci temporaneamente in essa
		partita.setStanzaCorrente(partita.getStanzaCorrente().getStanzaAdiacente("nord")); // Biblioteca è a nord dell'Atrio
		stanzaVincente = partita.getStanzaCorrente();

		// Torniamo all'atrio iniziale
		partita.setStanzaCorrente(stanzaVincente.getStanzaAdiacente("sud"));
	}

	/* TEST per vinta */
	@Test
	public void testVinta_InizialmenteFalse() {
		assertFalse(partita.vinta());
	}

	@Test
	public void testVinta_DopoAverImpostatoStanzaVincente() {
		partita.setStanzaCorrente(stanzaVincente);
		assertTrue(partita.vinta());
	}

	@Test
	public void testVinta_PassaggioPartitaVinta() {
		partita.setStanzaCorrente(stanza);
		assertFalse(partita.vinta());
		partita.setStanzaCorrente(stanzaVincente);
		assertTrue(partita.vinta());
	}

	@Test
	public void testVinta_Selettore() {
		partita.setStanzaCorrente(stanza);
		assertFalse(partita.vinta());
		partita.setStanzaCorrente(stanzaVincente);
		assertTrue(partita.vinta());
		partita.setStanzaCorrente(stanza);
		assertFalse(partita.vinta());
		partita.setStanzaCorrente(stanzaVincente);
		assertTrue(partita.vinta());
	}

	/* TEST per isFinita */
	@Test
	public void testIsFinita_SenzaCondizioni() {
		assertFalse(partita.isFinita());
	}

	@Test
	public void testIsFinita_DopoImpostatoFinita() {
		partita.setFinita();
		assertTrue(partita.isFinita());
	}

	@Test
	public void testIsFinita_QuandoVinta() {
		partita.setStanzaCorrente(stanzaVincente);
		assertTrue(partita.isFinita());
	}

	@Test
	public void testIsFinita_QuandoZeroCfu() {
		partita.getGiocatore().setCfu(0);
		assertTrue(partita.isFinita());
	}

	@Test
	public void testIsFinita_ConCondizioniMultiple() {
		partita.setFinita();
		partita.getGiocatore().setCfu(0);
		partita.setStanzaCorrente(stanzaVincente);
		assertTrue(partita.isFinita());
	}

	/* TEST per getGiocatore */
	@Test
	public void testGetGiocatore_NonNull() {
		assertNotNull(partita.getGiocatore());
	}

	@Test
	public void testGetGiocatore_StessoIndirizzo() {
		Giocatore g1 = partita.getGiocatore();
		Giocatore g2 = partita.getGiocatore();
		assertSame(g1, g2);
	}


	/* TEST per getGiocatoreBorsa */
	@Test
	public void testGetGiocatoreBorsa_NonNull() {
		assertNotNull(partita.getGiocatoreBorsa());
	}

	@Test
	public void testGetGiocatoreBorsa_ConsistenzaDaGiocatore() {
		assertSame(partita.getGiocatore().getBorsa(), partita.getGiocatoreBorsa());
	}

	/* TEST per setStanzaCorrente e getStanzaCorrente */
	@Test
	public void testSetAndGet_StanzaCorrente() {
		partita.setStanzaCorrente(stanza);
		assertSame(stanza, partita.getStanzaCorrente());
	}

	@Test
	public void testSetAndGet_StanzaCorrenteConNull() {
		partita.setStanzaCorrente(null);
		assertNull(partita.getStanzaCorrente());
	}

	@Test
	public void testSetStanza_CorrenteMultiple() {
		Stanza s1 = new Stanza("s1");
		Stanza s2 = new Stanza("s2");
		partita.setStanzaCorrente(s1);
		assertSame(s1, partita.getStanzaCorrente());
		partita.setStanzaCorrente(s2);
		assertSame(s2, partita.getStanzaCorrente());
	}

}