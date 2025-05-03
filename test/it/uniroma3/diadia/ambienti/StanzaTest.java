package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Questa classe testa tutti i metodi della classe Stanza
 *
 * @author Feded0 (609805) e Civan04 (605634)
 * @see Stanza
 * @version B
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
		stanza.impostaStanzaAdiacente("nord", null);
		assertNull(stanza.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testImpostaStanzaAdiacente_NuovaDirezione() {
		Stanza nuovaStanza = new Stanza("Aula N10");
		stanza.impostaStanzaAdiacente("nord", nuovaStanza);
		assertEquals(nuovaStanza, stanza.getStanzaAdiacente("nord"));
	}

	@Test
	public void testImpostaStanzaAdiacente_AggiornamentoStanzaEsistente() {
		Stanza nuovaStanza1 = new Stanza("Aula N10");
		Stanza nuovaStanza2 = new Stanza("Aula N11");
		stanza.impostaStanzaAdiacente("nord", nuovaStanza1);
		assertEquals(nuovaStanza1, stanza.getStanzaAdiacente("nord"));
		stanza.impostaStanzaAdiacente("nord", nuovaStanza2);
		assertEquals(nuovaStanza2, stanza.getStanzaAdiacente("nord"));
	}

	@Test
	public void testImpostaStanzaAdiacente_LimiteMassimoDirezioni() {
		stanza.impostaStanzaAdiacente("nord", new Stanza("Aula N11"));
		stanza.impostaStanzaAdiacente("sud", new Stanza("Aula N10"));
		stanza.impostaStanzaAdiacente("est", new Stanza("Laboratorio Campus"));
		stanza.impostaStanzaAdiacente("ovest", new Stanza("Biblioteca"));
		stanza.impostaStanzaAdiacente("nord-est", new Stanza("Tetto"));
		assertNull(stanza.getStanzaAdiacente("nord-est"));
	}

	@Test
	public void testImpostaStanzaAdiacente_AggiuntaMultipla() {
		stanza.impostaStanzaAdiacente("nord", new Stanza("Atrio"));
		int numeroDirezioniPrima = stanza.getDirezioni().length;
		stanza.impostaStanzaAdiacente("sud", new Stanza("Aula N10"));
		int numeroDirezioniDopo = stanza.getDirezioni().length;
		assertNotEquals(numeroDirezioniPrima, numeroDirezioniDopo);
	}

	/* TEST per getStanzaAdiacente */
	@Test
	public void testGetStanzaAdiacente_NonEsistente() {
		assertNull(stanza.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testGetStanzaAdiacente_Esistente() {
		Stanza nuovaStanza = new Stanza("Aula N10");
		stanza.impostaStanzaAdiacente("nord", nuovaStanza);
		assertNotNull(stanza.getStanzaAdiacente("nord"));
	}

	public void testGetStanzaAdiacente_DueStanza() {
		Stanza nuovaStanza1 = new Stanza("Biblioteca");
		Stanza nuovaStanza2 = new Stanza("Aula N10");
		stanza.impostaStanzaAdiacente("nord", nuovaStanza1);
		stanza.impostaStanzaAdiacente("est", nuovaStanza2);
		assertEquals(nuovaStanza1, stanza.getStanzaAdiacente("nord"));
		assertEquals(nuovaStanza2, stanza.getStanzaAdiacente("est"));
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
		Stanza nuovaStanza = new Stanza("Aula N10");
		stanza.impostaStanzaAdiacente("nord", nuovaStanza);
		assertTrue(stanza.getDescrizione().contains("nord"));
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
		Attrezzo[] attrezzi = stanza.getAttrezzi();
		assertEquals(osso, attrezzi[0]);
	}

	@Test
	public void testGetAttrezzi_DopoRimozione() {
		stanza.addAttrezzo(osso);
		assertEquals(osso, stanza.getAttrezzi()[0]);
		stanza.removeAttrezzo(osso);
		assertNull(stanza.getAttrezzi()[0]);
	}

	@Test
	public void testGetAttrezzi_ArrayPieno() {
		for(int i=0; i < 10; i++) {
			stanza.addAttrezzo(new Attrezzo("attrezzo" + i, i));
		}
		Attrezzo[] attrezzi = stanza.getAttrezzi();
		for(int i = 0; i < 10; i++) {
			assertNotNull(attrezzi[i]);
			assertEquals("attrezzo" + i, attrezzi[i].getNome());
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
	public void testAddAttrezzo_VerificaOrdineInserimento() {
		stanza.addAttrezzo(osso);
		stanza.addAttrezzo(lanterna);
		stanza.addAttrezzo(libro);
		Attrezzo[] attrezzi = stanza.getAttrezzi();
		assertEquals(osso, attrezzi[0]);
		assertEquals(lanterna, attrezzi[1]);
		assertEquals(libro, attrezzi[2]);
	}

	@Test
	public void testAddAttrezzo_AttrezzoNull() {
		assertTrue(stanza.addAttrezzo(null));
	}

	@Test
	public void testAddAttrezzo_StessaIstanzaDueVolte() {
		assertTrue(stanza.addAttrezzo(osso));
		assertTrue(stanza.addAttrezzo(osso));
		int count = 0;
		for (Attrezzo attrezzo : stanza.getAttrezzi()) {
			if (attrezzo != null && attrezzo.equals(osso)) {
				count++;
			}
		}
		assertEquals(2, count);
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
	public void testGetAttrezzo_ConStessoNomeRitornaUltimo() {
		Attrezzo attrezzo1 = new Attrezzo("osso",1);
		Attrezzo attrezzo2 = new Attrezzo("osso",1);
		stanza.addAttrezzo(attrezzo1);
		stanza.addAttrezzo(attrezzo2);
		assertSame(attrezzo2, stanza.getAttrezzo("osso"));
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
		Attrezzo[] attrezzi = stanza.getAttrezzi();
		assertSame(lanterna, attrezzi[0]);
	}

    @Test
    public void testRemoveAttrezzo_ConRiferimentoDiversoMaStessoNome() {
        Attrezzo osso1 = new Attrezzo("osso", 1);
        Attrezzo osso2 = new Attrezzo("osso", 1);
        stanza.addAttrezzo(osso1);
        assertFalse(stanza.removeAttrezzo(osso2));
    }
    
    @Test
    public void testRemoveAttrezzo_MantieneOrdineArray() {
        Attrezzo primo = new Attrezzo("primo", 1);
        Attrezzo secondo = new Attrezzo("secondo", 1);
        Attrezzo terzo = new Attrezzo("terzo", 1);
        
        stanza.addAttrezzo(primo);
        stanza.addAttrezzo(secondo);
        stanza.addAttrezzo(terzo);
        
        stanza.removeAttrezzo(secondo);
        
        assertSame(primo, stanza.getAttrezzi()[0]);
        assertSame(terzo, stanza.getAttrezzi()[1]);
        assertNull(stanza.getAttrezzo("secondo"));
    }

    @Test
    public void testRemoveAttrezzo_UltimoDaStanzaPiena() {
    	for (int i = 0; i < 8; i++) {
            stanza.addAttrezzo(new Attrezzo("attrezzo" + i, 1));
        }
    	Attrezzo attrezzo9 = new Attrezzo("attrezzo9", 1);
    	stanza.removeAttrezzo(attrezzo9);
    	assertNull(stanza.getAttrezzi()[9]);
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
    	assertSame(attrezzo1, stanza.getAttrezzi()[0]);
    	assertNull(stanza.getAttrezzi()[9]);
    }
	
	/* TEST per getDirezioni */
	@Test
	public void testGetDirezioni_Vuoto() {
		assertEquals(0, stanza.getDirezioni().length);
	}

	@Test
	public void testGetDirezioni_Singola() {
		stanza.impostaStanzaAdiacente("nord", new Stanza("Aula N10"));
		assertEquals(1, stanza.getDirezioni().length);
		assertEquals("nord", stanza.getDirezioni()[0]);
	}

	@Test
	public void testGetDirezioni_Multiple() {
		stanza.impostaStanzaAdiacente("nord", new Stanza("Aula N10"));
		stanza.impostaStanzaAdiacente("sud", new Stanza("Aula N11"));
		assertEquals(2, stanza.getDirezioni().length);
		assertTrue(stanza.getDirezioni()[0].equals("nord"));
		assertTrue(stanza.getDirezioni()[1].equals("sud"));
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
		stanza.impostaStanzaAdiacente("nord", new Stanza("Aula N10"));
		stanza.impostaStanzaAdiacente("sud", new Stanza("Aula N11"));
		String descrizione = stanza.toString();
		assertTrue(descrizione.contains("nord"));
		assertTrue(descrizione.contains("sud"));
	}
	
}
