package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Questa classe testa tutti i metodi della classe Labirinto
 *
 * @author Feded0 (609805) e Civan04 (605634)
 * @see Labirinto
 * @version A
 */

class LabirintoTest {
	private Labirinto labirinto;
	private Stanza stanzaCorrente;
	
	@BeforeEach
	public void setUp() {
		labirinto = new Labirinto();
		stanzaCorrente = labirinto.getStanzaCorrente(); // Attualmente l'atrio
	}

	/* TEST per getStanzaVincente */
	@Test
	public void testGetStanzaVincente_NotNull() {
		assertNotNull(labirinto.getStanzaVincente());
	}

	@Test
	public void testGetStanzaVincente_Nome() {
		assertEquals("Biblioteca", labirinto.getStanzaVincente().getNome());
	}

	@Test
	public void testGetStanzaVincente_DifferenteDaStanzaCorrente() {
		assertNotSame(labirinto.getStanzaVincente(), labirinto.getStanzaCorrente());
	}

	/* TEST per getStanzaCorrente e setStanzaCorrente */
	@Test
	public void testGetStanzaCorrente_Inizializzazione() {
		assertEquals("Atrio", labirinto.getStanzaCorrente().getNome());
	}

	@Test
	public void testSetStanzaCorrente_ConNuovaStanza() {
		Stanza s = new Stanza("s");
		labirinto.setStanzaCorrente(s);
		assertSame(s, labirinto.getStanzaCorrente());
	}

	@Test
	public void testSetStanzaCorrente_Multiplo() {
		Stanza s1 = new Stanza("s1");
		Stanza s2 = new Stanza("s2");
		labirinto.setStanzaCorrente(s1);
		assertSame(s1, labirinto.getStanzaCorrente());
		labirinto.setStanzaCorrente(s2);
		assertSame(s2, labirinto.getStanzaCorrente());
	}

	/* TEST per creaStanze */
	@Test
	public void testCreaStanze_AdiacenzeAtrio() {
		assertNotNull(stanzaCorrente.getStanzaAdiacente("nord"));
		assertNotNull(stanzaCorrente.getStanzaAdiacente("est"));
		assertNotNull(stanzaCorrente.getStanzaAdiacente("sud"));
		assertNotNull(stanzaCorrente.getStanzaAdiacente("ovest"));
	}

	@Test
	public void testCreaStanze_AdiacenzaNordAtrio() {
		Stanza nord = stanzaCorrente.getStanzaAdiacente("nord");
		assertEquals("Biblioteca", nord.getNome());
	}

	@Test
	public void testCreaStanze_AdiacenzaEstAtrio() {
		Stanza est = stanzaCorrente.getStanzaAdiacente("est");
		assertEquals("Aula N11", est.getNome());
	}

	@Test
	public void testCreaStanze_AdiacenzaSudAtrio() {
		Stanza sud = stanzaCorrente.getStanzaAdiacente("sud");
		assertEquals("Aula N10", sud.getNome());
	}

	@Test
	public void testCreaStanze_AdiacenzaOvestAtrio() {
		Stanza ovest = stanzaCorrente.getStanzaAdiacente("ovest");
		assertEquals("Laboratorio Campus", ovest.getNome());
	}
	
	@Test
	public void testCreaStanze_AdiacenzaBiblioteca() {
		Stanza biblioteca = labirinto.getStanzaVincente();
		Stanza sud = biblioteca.getStanzaAdiacente("sud");
		assertNotNull(sud);
		assertEquals("Atrio", sud.getNome());
	}

	@Test
	public void testCreaStanze_AttrezzoInAtrio() {
		Attrezzo osso = stanzaCorrente.getAttrezzo("osso");
		assertNotNull(osso);
	}
	
	@Test
	public void testCreaStanze_AttrezzoInAulaN10() {
		Stanza aulaN10 = stanzaCorrente.getStanzaAdiacente("sud");
		Attrezzo lanterna = aulaN10.getAttrezzo("lanterna");
		assertNotNull(lanterna);
	}

	@Test
	public void testCreaStanze_DirezioneNonDefinita() {
		assertNull(stanzaCorrente.getStanzaAdiacente("sud-est"));
	}

	@Test
	public void testCreaStanze_CoerenzaLabirinto() {
		Stanza aulaN11 = stanzaCorrente.getStanzaAdiacente("est");
		assertNotNull(aulaN11);
		Stanza versoAtrio = aulaN11.getStanzaAdiacente("ovest");
		assertNotNull(versoAtrio);
		assertEquals("Atrio", versoAtrio.getNome());
	}
	
}
