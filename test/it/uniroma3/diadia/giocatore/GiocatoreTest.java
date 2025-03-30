package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Questa classe testa tutti i metodi della classe Giocatore
 *
 * @author Feded0 (609805) e Civan04 (605634)
 * @see Giocatore
 * @version A
 */

class GiocatoreTest {
	private Giocatore giocatore;

	@BeforeEach
	public void setUp() {
		giocatore = new Giocatore();
	}

	/* TEST per getCfu */
	@Test
	public void testGetCfu_Iniziali() {
		assertEquals(20, giocatore.getCfu());
	}

	@Test
	public void testGetCfu_DopoModifica() {
		giocatore.setCfu(15);
		assertEquals(15, giocatore.getCfu());
	}

	@Test
	public void testGetCfu_ConValoreNegativo() {
		giocatore.setCfu(-5);
		assertEquals(-5, giocatore.getCfu());
	}

	/* TEST per setCfu */
	@Test
	public void testSetCfu_Zero() {
		giocatore.setCfu(0);
		assertEquals(0, giocatore.getCfu());
	}
	
	@Test
	public void testSetCfu_Positivo() {
		giocatore.setCfu(10);
		assertEquals(10, giocatore.getCfu());
	}

	@Test
	public void testSetCfu_Negativo() {
		giocatore.setCfu(-3);
		assertEquals(-3, giocatore.getCfu());
	}

	@Test
	public void testSetCfu_MassimoInt() {
		giocatore.setCfu(Integer.MAX_VALUE);
		assertEquals(Integer.MAX_VALUE, giocatore.getCfu());
	}

	@Test
	public void testSetCfu_MinimoInt() {
		giocatore.setCfu(Integer.MIN_VALUE);
		assertEquals(Integer.MIN_VALUE, giocatore.getCfu());
	}

	/* TEST per getBorsa */
	@Test
	public void testGetBorsa_RestituisceIstanzaCorretta() {
		assertTrue(giocatore.getBorsa() instanceof Borsa);
	}

	@Test
	public void testGetBorsa_AggiuntaAttrezzo() {
		giocatore.getBorsa().addAttrezzo(new Attrezzo("osso", 1));
		assertTrue(giocatore.getBorsa().hasAttrezzo("osso"));
	}

	@Test
	public void testGetBorsa_RimuoviAttrezzo() {
		Attrezzo attrezzo = new Attrezzo("osso", 1);
		giocatore.getBorsa().addAttrezzo(attrezzo);
		giocatore.getBorsa().removeAttrezzo(attrezzo);
		assertFalse(giocatore.getBorsa().hasAttrezzo("osso"));
	}

	@Test
	public void testGetBorsa_StessaIstanza() {
		Borsa borsaIniziale = giocatore.getBorsa();
		assertEquals(borsaIniziale, giocatore.getBorsa());
	}
	
}
