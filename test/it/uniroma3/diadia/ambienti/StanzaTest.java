package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Questa classe testa tutti i metodi della classe Stanza
 *
 * @author Feded0 (609805) e Civan04 (605634)
 * @see Stanza
 * @version C
 */

class StanzaTest {
	private Stanza stanza;
	private Attrezzo osso;
	private Attrezzo lanterna;
	private Attrezzo libro;

	@BeforeEach
	public void setUp() {
		stanza = new Stanza("Atrio");
		osso = new Attrezzo("osso", 1);
		libro = new Attrezzo("libro", 2);
		lanterna = new Attrezzo("lanterna", 3);
	}

	/* TEST per impostaStanzaAdiacente */
	@Test
	public void testImpostaStanzaAdiacente_StanzaNull(){
		stanza.impostaStanzaAdiacente(Direzione.nord, null);
		assertNull(stanza.getStanzaAdiacente(Direzione.nord));
	}
	
	@Test
	public void testImpostaStanzaAdiacente_NuovaDirezione() {
		Stanza nuovaStanza = new Stanza("AulaN10");
		stanza.impostaStanzaAdiacente(Direzione.nord, nuovaStanza);
		assertEquals(nuovaStanza, stanza.getStanzaAdiacente(Direzione.nord));
	}

	@Test
	public void testImpostaStanzaAdiacente_AggiornamentoStanzaEsistente() {
		Stanza nuovaStanza1 = new Stanza("AulaN10");
		Stanza nuovaStanza2 = new Stanza("AulaN11");
		stanza.impostaStanzaAdiacente(Direzione.nord, nuovaStanza1);
		assertEquals(nuovaStanza1, stanza.getStanzaAdiacente(Direzione.nord));
		stanza.impostaStanzaAdiacente(Direzione.nord, nuovaStanza2);
		assertEquals(nuovaStanza2, stanza.getStanzaAdiacente(Direzione.nord));
	}

	@Test
	public void testImpostaStanzaAdiacente_AggiuntaMultipla() {
		stanza.impostaStanzaAdiacente(Direzione.nord, new Stanza("Atrio"));
		int numeroDirezioniPrima = stanza.getDirezioni().size();
		stanza.impostaStanzaAdiacente(Direzione.sud, new Stanza("AulaN10"));
		int numeroDirezioniDopo = stanza.getDirezioni().size();
		assertNotEquals(numeroDirezioniPrima, numeroDirezioniDopo);
	}

	/* TEST per getStanzaAdiacente */
	@Test
	public void testGetStanzaAdiacente_NonEsistente() {
		assertNull(stanza.getStanzaAdiacente(Direzione.nord));
	}
	
	@Test
	public void testGetStanzaAdiacente_Esistente() {
		Stanza nuovaStanza = new Stanza("AulaN10");
		stanza.impostaStanzaAdiacente(Direzione.nord, nuovaStanza);
		assertNotNull(stanza.getStanzaAdiacente(Direzione.nord));
	}

	public void testGetStanzaAdiacente_DueStanza() {
		Stanza nuovaStanza1 = new Stanza("Biblioteca");
		Stanza nuovaStanza2 = new Stanza("AulaN10");
		stanza.impostaStanzaAdiacente(Direzione.nord, nuovaStanza1);
		stanza.impostaStanzaAdiacente(Direzione.est, nuovaStanza2);
		assertEquals(nuovaStanza1, stanza.getStanzaAdiacente(Direzione.nord));
		assertEquals(nuovaStanza2, stanza.getStanzaAdiacente(Direzione.est));
	}

	/* TEST per getNome */
	@Test
	public void testGetNome_RestituisceNomeCorretto() {
		assertEquals("Atrio", stanza.getNome());
	}

	@Test
	public void testGetNome_ConNomeVuoto() {
		Stanza stanza = new Stanza("");
		assertEquals("", stanza.getNome());
	}

	@Test
	public void testGetNome_ConSpaziECaratteriSpeciali() {
		String nomeStanza = "Stanza Segreta #123!_-[]'";
		Stanza stanza = new Stanza(nomeStanza);
		assertEquals(nomeStanza, stanza.getNome());
	}

	/* TEST per getDescrizione */
	@Test
	public void testGetDescrizione_IncludiNomeStanza() {
		assertTrue(stanza.getDescrizione().contains("Atrio"));
	}

	@Test
	public void testGetDescrizione_IncludiDirezioni() {
		Stanza nuovaStanza = new Stanza("AulaN10");
		stanza.impostaStanzaAdiacente(Direzione.nord, nuovaStanza);
		assertTrue(stanza.getDescrizione().contains(Direzione.nord.toString()));
	}

	@Test
	public void testGetDescrizione_IncludiAttrezzi() {
		stanza.addAttrezzo(osso);
		assertTrue(stanza.getDescrizione().contains("osso"));
	}

	/* TEST per getAttrezzi */
	@Test
	public void testGetAttrezzi_RestituisceArrayCorretto() {
		stanza.addAttrezzo(osso);
		assertTrue(stanza.getAttrezzi().contains(osso));
	}

	@Test
	public void testGetAttrezzi_DopoRimozione() {
		stanza.addAttrezzo(osso);
		assertTrue(stanza.getAttrezzi().contains(osso));
		stanza.removeAttrezzo(osso);
		assertFalse(stanza.getAttrezzi().contains(osso));
	}

	@Test
	public void testGetAttrezzi_Pieno() {
		for(int i=0; i < 10; i++) {
			stanza.addAttrezzo(new Attrezzo("attrezzo" + i, i));
		}
		
		Collection<Attrezzo> atr = stanza.getAttrezzi();

		for(int i = 0; i < 10; i++) {
			assertTrue(atr.contains(stanza.getAttrezzo("attrezzo"+i)));
		}
	}

	/* TEST per isEmpty */
	@Test
	public void testIsEmpty_StanzaVuota() {
		assertTrue(stanza.isEmpty());
	}

	@Test
	public void testIsEmpty_StanzaConAttrezzo() {
		stanza.addAttrezzo(osso);
		assertFalse(stanza.isEmpty());
	}

	@Test
	public void testIsEmpty_DopoAggiuntaERimozione() {
		stanza.addAttrezzo(osso);
		stanza.removeAttrezzo(osso);
		assertTrue(stanza.isEmpty());
	}

	/* TEST per addAttrezzo */
	@Test
	public void testAddAttrezzo_Successo() {
		assertTrue(stanza.addAttrezzo(osso));
		assertTrue(stanza.addAttrezzo(lanterna));
		assertTrue(stanza.addAttrezzo(libro));
	}

	@Test
	public void testAddAttrezzo_RaggiungeLimite() {
		for (int i = 0; i < 10; i++) {
			assertTrue(stanza.addAttrezzo(new Attrezzo("attrezzo" + i, i)));
		}
		assertFalse(stanza.addAttrezzo(osso));
	}

	@Test
	public void testAddAttrezzo_VerificaInserimento() {
		stanza.addAttrezzo(osso);
		stanza.addAttrezzo(lanterna);
		stanza.addAttrezzo(libro);
		assertEquals(osso, stanza.getAttrezzo("osso"));
		assertEquals(lanterna, stanza.getAttrezzo("lanterna"));
		assertEquals(libro, stanza.getAttrezzo("libro"));
	}

	@Test
	public void testAddAttrezzo_AttrezzoNull() {
		assertFalse(stanza.addAttrezzo(null));
	}

	@Test
	public void testAddAttrezzo_StessaIstanzaDueVolte() {
		assertTrue(stanza.addAttrezzo(osso));
		assertFalse(stanza.addAttrezzo(osso));
		assertEquals(osso, stanza.getAttrezzo("osso"));
		assertEquals(1, stanza.getAttrezzi().size());
	}

	@Test
	public void testAddAttrezzo_RaggiungiLimiteEsatto() {
		for (int i = 0; i < 9; i++) {
			stanza.addAttrezzo(new Attrezzo("attrezzo" + i, i));
		}
		assertTrue(stanza.addAttrezzo(new Attrezzo("ultimo", 10)));
	}

	@Test
	public void testAddAttrezzo_RimozioneEDopodAggiunta() {
		for (int i = 0; i < 10; i++) {
			stanza.addAttrezzo(new Attrezzo("attrezzo" + i, i));
		}
		Attrezzo attrezzoDaRimuovere = stanza.getAttrezzo("attrezzo5");
		assertTrue(stanza.removeAttrezzo(attrezzoDaRimuovere));
		assertTrue(stanza.addAttrezzo(new Attrezzo("nuovo", 1)));
	}

	/* TEST per hasAttrezzo */
	@Test
	public void testHasAttrezzo_Esistente() {
		stanza.addAttrezzo(osso);
		assertTrue(stanza.hasAttrezzo("osso"));
	}

	@Test
	public void testHasAttrezzo_Inesistente() {
		stanza.addAttrezzo(osso);
		assertFalse(stanza.hasAttrezzo("lanterna"));
	}

	@Test
	public void testHas_AttrezzoDopoRimozione() {
		stanza.addAttrezzo(osso);
		stanza.removeAttrezzo(osso);
		assertFalse(stanza.hasAttrezzo("osso"));
	}

	/* TEST per getAttrezzo */
	@Test
	public void testGetAttrezzo_Esistente() {
		stanza.addAttrezzo(osso);
		assertSame(osso, stanza.getAttrezzo("osso"));
	}

	@Test
	public void testGetAttrezzo_Inesistente() {
		stanza.addAttrezzo(osso);
		assertNull(stanza.getAttrezzo("lanterna"));
	}

	@Test
	public void testGetAttrezzo_ConStessoNomeRitornaPrimo() {
		Attrezzo attrezzo1 = new Attrezzo("osso",1);
		Attrezzo attrezzo2 = new Attrezzo("osso",1);
		stanza.addAttrezzo(attrezzo1);
		stanza.addAttrezzo(attrezzo2);
		assertSame(attrezzo1, stanza.getAttrezzo("osso"));
	}

	/* TEST per removeAttrezzo */
	 @Test
	    public void testRemoveAttrezzo_DaStanzaVuota() {
	        assertFalse(stanza.removeAttrezzo(osso));
	    }
	
	@Test
	public void testRemoveAttrezzo_Esistente() {
		stanza.addAttrezzo(osso);
		assertTrue(stanza.removeAttrezzo(osso));
		assertFalse(stanza.hasAttrezzo("osso"));
	}

	@Test
	public void testRemoveAttrezzo_Inesistente() {
		stanza.addAttrezzo(osso);
		Attrezzo lanterna = new Attrezzo("lanterna", 3);
		assertFalse(stanza.removeAttrezzo(lanterna));
	}

	@Test
	public void testRemoveAttrezzo_RimozioneCorretta() {
		stanza.addAttrezzo(osso);
		stanza.addAttrezzo(lanterna);
		assertTrue(stanza.removeAttrezzo(osso));
		assertNull(stanza.getAttrezzo("osso"));
	}

    @Test
    public void testRemoveAttrezzo_ConRiferimentoDiversoMaStessoNome() {
        Attrezzo osso1 = new Attrezzo("osso", 1);
        Attrezzo osso2 = new Attrezzo("osso", 1);
        stanza.addAttrezzo(osso1);
        assertTrue(stanza.removeAttrezzo(osso2));
    }
    
    @Test
    public void testRemoveAttrezzo_NonModificaAltriAttrezzi() {
        Attrezzo primo = new Attrezzo("primo", 1);
        Attrezzo secondo = new Attrezzo("secondo", 1);
        Attrezzo terzo = new Attrezzo("terzo", 1);
        
        stanza.addAttrezzo(primo);
        stanza.addAttrezzo(secondo);
        stanza.addAttrezzo(terzo);
        
        stanza.removeAttrezzo(secondo);
        
        assertSame(primo, stanza.getAttrezzo("primo"));
        assertSame(terzo, stanza.getAttrezzo("terzo"));
        assertNull(stanza.getAttrezzo("secondo"));
    }

    @Test
    public void testRemoveAttrezzo_UltimoDaStanzaPiena() {
    	for (int i = 0; i < 8; i++) {
            stanza.addAttrezzo(new Attrezzo("attrezzo" + i, 1));
        }
    	Attrezzo attrezzo9 = new Attrezzo("attrezzo9", 1);
    	stanza.removeAttrezzo(attrezzo9);
    	assertNull(stanza.getAttrezzo("attrezzo9"));
    }

    @Test
    public void testRemoveAttrezzo_PrimoDaStanzaPiena() {
    	Attrezzo attrezzo0 = new Attrezzo("attrezzo0", 1);
    	Attrezzo attrezzo1 = new Attrezzo("attrezzo1", 1);
    	stanza.addAttrezzo(attrezzo0);
    	stanza.addAttrezzo(attrezzo1);
    	for (int i = 2; i < 9; i++) {
            stanza.addAttrezzo(new Attrezzo("attrezzo" + i, 1));
        }
    	stanza.removeAttrezzo(attrezzo0);
    	assertSame(attrezzo1, stanza.getAttrezzo("attrezzo1"));
    	assertNull(stanza.getAttrezzo("attrezzo0"));
    }
	
	/* TEST per getDirezioni */
	@Test
	public void testGetDirezioni_Vuoto() {
		assertEquals(0, stanza.getDirezioni().size());
	}

	@Test
	public void testGetDirezioni_Singola() {
		stanza.impostaStanzaAdiacente(Direzione.nord, new Stanza("AulaN10"));
		assertEquals(1, stanza.getDirezioni().size());
		assertTrue(stanza.getDirezioni().contains(Direzione.nord));
	}

	@Test
	public void testGetDirezioni_Multiple() {
		stanza.impostaStanzaAdiacente(Direzione.nord, new Stanza("AulaN10"));
		stanza.impostaStanzaAdiacente(Direzione.sud, new Stanza("AulaN11"));
		assertEquals(2, stanza.getDirezioni().size());
		assertTrue(stanza.getDirezioni().contains(Direzione.nord));
		assertTrue(stanza.getDirezioni().contains(Direzione.sud));
	}

	/* TEST per toString */
	@Test
	public void testToString_SenzaAttrezzi() {
		assertTrue(stanza.toString().contains("Nessun attrezzo"));
	}

	@Test
	public void testToString_ConAttrezzi() {
		stanza.addAttrezzo(osso);
		assertTrue(stanza.toString().contains("osso"));
	}

	@Test
	public void testToString_ConDirezioni() {
		stanza.impostaStanzaAdiacente(Direzione.nord, new Stanza("AulaN10"));
		stanza.impostaStanzaAdiacente(Direzione.sud, new Stanza("AulaN11"));
		String descrizione = stanza.toString();
		assertTrue(descrizione.contains(Direzione.nord.toString()));
		assertTrue(descrizione.contains(Direzione.sud.toString()));
	}
	
}
