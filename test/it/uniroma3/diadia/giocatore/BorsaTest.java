package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Questa classe testa tutti i metodi della classe Borsa
 *
 * @author Feded0 (609805) e Civan04 (605634)
 * @see Borsa
 * @version C
 */

class BorsaTest {
    private Borsa borsa;
    private Attrezzo osso;
    private Attrezzo martello;
    private Attrezzo falce;
    private Attrezzo pala;
    private Attrezzo racchetta;

    @BeforeEach
    public void setUp() {
        borsa = new Borsa();
        osso = new Attrezzo("osso", 1);
        martello = new Attrezzo("martello", 2);
        falce = new Attrezzo("falce", 2);
		pala = new Attrezzo("pala", 3);
		racchetta = new Attrezzo("racchetta", 3);
    }

    /* TEST per addAttrezzo */
    @Test
    public void testAddAttrezzo_SingoloAumentaPeso() {
        assertTrue(borsa.addAttrezzo(osso));
        assertEquals(1, borsa.getPeso());
    }

    @Test
    public void testAddAttrezzo_PesoEccessivoNonAggiunge() {
        Attrezzo incudine = new Attrezzo("incudine", 15);
        assertFalse(borsa.addAttrezzo(incudine));
        assertTrue(borsa.isEmpty());
    }
    
    @Test
    public void testAddAttrezzo_BorsaPienaNonAggiunge() {
    	for (int i = 0; i < 10; i++) {
            borsa.addAttrezzo(new Attrezzo("attrezzo" + i, 1));
        }
    	assertFalse(borsa.addAttrezzo(new Attrezzo("attrezzo10", 1)));
    }
    
    @Test
    public void testAddAttrezzo_BorsaConPesoMassimoRaggiuntoNonAggiunge() {
        Attrezzo incudine = new Attrezzo("incudine", 10);
        borsa.addAttrezzo(incudine);
        assertFalse(borsa.addAttrezzo(osso));
    }

    /* TEST per getAttrezzo */
    @Test
    public void testGetAttrezzo_RestituisceAttrezzoAggiunto() {
        borsa.addAttrezzo(osso);
        assertEquals(osso, borsa.getAttrezzo("osso"));
    }

    @Test
    public void testGetAttrezzo_ConNomeNonPresenteRestituisceNull() {
        assertNull(borsa.getAttrezzo("inesistente"));
    }

    @Test
    public void testGetAttrezzo_ConNomeNullRestituisceNull() {
        assertNull(borsa.getAttrezzo(null));
    }
    
    @Test
    public void testGetAttrezzo_ConDueAttrezziConLoStessoNomeTrovaPrimo() {
    	Attrezzo osso1 = new Attrezzo("osso", 1);
    	Attrezzo osso2 = new Attrezzo("osso", 1);
    	
    	borsa.addAttrezzo(osso1);
    	borsa.addAttrezzo(osso2);
    	
    	assertSame(osso1, borsa.getAttrezzo("osso"));
    }

    /* TEST per getPeso */
    @Test
    public void testGetPeso_RestituisceZeroSeBorsaVuota() {
        assertEquals(0, borsa.getPeso());
    }

    @Test
    public void testGetPeso_AumentaDopoAggiuntaAttrezzo() {
        borsa.addAttrezzo(osso);
        assertEquals(1, borsa.getPeso());
    }

    @Test
    public void testGetPeso_DiminuisceDopoRimozioneAttrezzo() {
        borsa.addAttrezzo(osso);
        assertEquals(1, borsa.getPeso());
        borsa.removeAttrezzo(osso.getNome());
        assertEquals(0, borsa.getPeso());
    }
    
    /* TEST per isEmpty */
    @Test
    public void testIsEmpty_RestituisceTrueSeBorsaVuota() {
        assertTrue(borsa.isEmpty());
    }

    @Test
    public void testIsEmpty_RestituisceFalseSeBorsaNonVuota() {
        borsa.addAttrezzo(osso);
        assertFalse(borsa.isEmpty());
    }

    @Test
    public void testIsEmpty_DopoRimozioneAttrezzo() {
        borsa.addAttrezzo(osso);
        borsa.removeAttrezzo(osso.getNome());
        assertTrue(borsa.isEmpty());
    }
    
    /* TEST per hasAttrezzo */
    @Test
    public void testHasAttrezzo_RestituisceTrueSePresente() {
        borsa.addAttrezzo(osso);
        assertTrue(borsa.hasAttrezzo("osso"));
    }

    @Test
    public void testHasAttrezzo_RestituisceFalseSeNonPresente() {
        assertFalse(borsa.hasAttrezzo("inesistente"));
    }

    @Test
    public void testHasAttrezzo_ConNomeNullRestituisceFalse() {
        assertFalse(borsa.hasAttrezzo(null));
    }
    
    /* TEST per removeAttrezzo */
    @Test
    public void testRemoveAttrezzo_RimossoNonPiùPresente() {
        borsa.addAttrezzo(osso);
        Attrezzo rimosso = borsa.removeAttrezzo(osso.getNome());
        
        assertSame(osso, rimosso);
        assertNull(borsa.getAttrezzo("osso"));
    }

    @Test
    public void testRemoveAttrezzo_PresenteRestituisceRiferimentoCorretto() {
        borsa.addAttrezzo(osso);
        assertSame(osso, borsa.removeAttrezzo(osso.getNome()));
    }

    @Test
    public void testRemoveAttrezzo_AggiornaPesoBorsa() {
        borsa.addAttrezzo(osso);
        int pesoIniziale = borsa.getPeso();
        borsa.removeAttrezzo(osso.getNome());
        assertEquals(pesoIniziale - osso.getPeso(), borsa.getPeso());
    }

    @Test
    public void testRemoveAttrezzo_DaBorsaVuota() {
        assertNull(borsa.removeAttrezzo(osso.getNome()));
    }

    @Test
    public void testRemoveAttrezzo_ConRiferimentoDiversoMaStessoNome() {
        Attrezzo osso1 = new Attrezzo("osso", 1);
        Attrezzo osso2 = new Attrezzo("osso", 1);
        borsa.addAttrezzo(osso1);
        assertEquals(osso2, borsa.removeAttrezzo(osso2.getNome()));
    }

    @Test
    public void testRemoveAttrezzo_Null() {
        assertNull(borsa.removeAttrezzo(null));
    }

    @Test
    public void testRemoveUltimo_AttrezzoNonLasciaRiferimenti() {
        borsa.addAttrezzo(osso);
        borsa.removeAttrezzo(osso.getNome());
        assertNull(borsa.getAttrezzo("osso"));
    }
    
    @Test
    public void testRemoveAttrezzo_MantieneOrdineArray() {
        Attrezzo primo = new Attrezzo("primo", 1);
        Attrezzo secondo = new Attrezzo("secondo", 1);
        Attrezzo terzo = new Attrezzo("terzo", 1);
        
        borsa.addAttrezzo(primo);
        borsa.addAttrezzo(secondo);
        borsa.addAttrezzo(terzo);
        
        borsa.removeAttrezzo(secondo.getNome());
        
        assertSame(primo, borsa.getAttrezzo("primo"));
        assertSame(terzo, borsa.getAttrezzo("terzo"));
        assertNull(borsa.getAttrezzo("secondo"));
    }

    @Test
    public void testRemoveAttrezzo_UltimoDaBorsaPiena() {
    	for (int i = 0; i < 9; i++) {
            borsa.addAttrezzo(new Attrezzo("attrezzo" + i, 1));
        }
    	Attrezzo attrezzo9 = new Attrezzo("attrezzo9", 1);
    	borsa.addAttrezzo(attrezzo9);
    	assertSame(borsa.getAttrezzo("attrezzo9"), borsa.removeAttrezzo(attrezzo9.getNome()));
    }

    @Test
    public void testRemoveAttrezzo_PrimoDaBorsaPiena() {
    	Attrezzo attrezzo0 = new Attrezzo("attrezzo0", 1);
        borsa.addAttrezzo(attrezzo0);
    	for (int i = 1; i < 10; i++) {
            borsa.addAttrezzo(new Attrezzo("attrezzo" + i, 1));
        }
    	assertSame(borsa.getAttrezzo("attrezzo0"), borsa.removeAttrezzo(attrezzo0.getNome()));
    }
    
	/* TEST per toString */
	@Test
	public void testToString_BorsaVuota() {
		assertEquals("Borsa vuota", borsa.toString());
	}

	@Test
	public void testToString_ConUnAttrezzo() {
		borsa.addAttrezzo(osso);
		String descrizione = borsa.toString();
		assertTrue(descrizione.contains("Contenuto borsa (1kg/10kg): [osso (1kg)]"));
	}

	@Test
	public void testToString_ConDueAttrezziUguali() {
		borsa.addAttrezzo(osso);
		borsa.addAttrezzo(osso);
		String descrizione = borsa.toString();
		assertTrue(descrizione.contains("Contenuto borsa (1kg/10kg): [osso (1kg)]"));
	}
	
	@Test
    void testContenutoOrdinato_nonVuoto() {
		borsa.addAttrezzo(osso);
		borsa.addAttrezzo(martello);
		borsa.addAttrezzo(falce);
        String contenuto = borsa.getContenutoOrdinato();

        assertTrue(contenuto.contains("Ordinamento per peso"));
        assertTrue(contenuto.contains("Ordinamento per nome"));
        assertTrue(contenuto.contains("Raggruppamento per peso"));

        // Verifica che i nomi degli attrezzi siano presenti nella stringa finale (senza pesi)
        assertTrue(contenuto.contains("osso"));
        assertTrue(contenuto.contains("martello"));
        assertTrue(contenuto.contains("falce"));
    }

    @Test
    void testContenutoOrdinato_vuoto() {
        String contenuto = borsa.getContenutoOrdinato();
        assertEquals("Borsa vuota", contenuto);
    }
	
	@Test
	public void testControllaMap() {
		Map<Integer, Set<Attrezzo>> e = new TreeMap<>();
		Set<Attrezzo> sing1 = new TreeSet<>();
		Set<Attrezzo> sing2 = new TreeSet<>();
		
		sing1.add(martello);
		sing2.add(pala);
		
		e.put(2, sing1);
		e.put(3, sing2);
		assertTrue(this.controllaMap(e, e));
	}
	
	@Test
	public void testGetContenuto_OrdinatoPerNomeStessoPeso() {
		borsa.addAttrezzo(martello);
		borsa.addAttrezzo(falce);

		Set<Attrezzo> e = new TreeSet<>();
		e.add(falce);
		e.add(martello);
		assertTrue(this.controllaSet(e, borsa.getSortedSetOrdinatoPerPeso()));
	}
	
	@Test
	public void testGetContenuto_RaggruppatoPerPesoPesiDiversiSingleton() {
		borsa.addAttrezzo(martello);
		borsa.addAttrezzo(pala);

		Map<Integer, Set<Attrezzo>> e = new TreeMap<>();
		Set<Attrezzo> sing1 = new TreeSet<>();
		Set<Attrezzo> sing2 = new TreeSet<>();
		
		sing1.add(martello);
		sing2.add(pala);
		
		e.put(2, sing1);
		e.put(3, sing2);
		
		assertTrue(this.controllaMap(e,borsa.getContenutoRaggruppatoPerPeso()));
	}
	
	@Test
	public void testGetContenuto_RaggruppatoPerPesoPesiDiversiGruppi() {
		borsa.addAttrezzo(martello);
		borsa.addAttrezzo(pala);
		borsa.addAttrezzo(racchetta);
		borsa.addAttrezzo(falce);

		Map<Integer, Set<Attrezzo>> e = new TreeMap<>();
		Set<Attrezzo> sing1 = new HashSet<>();
		Set<Attrezzo> sing2 = new HashSet<>();
		
		sing1.add(martello);
		sing2.add(pala);
		sing2.add(racchetta);
		sing1.add(falce);
		
		e.put(2, sing1);
		e.put(3, sing2);
		
		assertTrue(this.controllaMap(e, borsa.getContenutoRaggruppatoPerPeso()));
	}
	
	public boolean controllaSet(Set<Attrezzo> m1, Set<Attrezzo> m2) {
		if(m1.size()!=m2.size())
			return false;
		Iterator<Attrezzo> iter1 = m1.iterator();
		Iterator<Attrezzo> iter2 = m2.iterator();
		while(iter1.hasNext() && iter2.hasNext()) {
			if(!iter1.next().equals(iter2.next()))
				return false;
		}
		return true;
	}
	
	public boolean controllaMap(Map<Integer, Set<Attrezzo>> m1, Map<Integer, Set<Attrezzo>> m2) {
		if(m1.size()!=m2.size())
			return false;
		
		Iterator<Integer> iter1 = m1.keySet().iterator();
		Iterator<Integer> iter2 = m2.keySet().iterator();
		while(iter1.hasNext() && iter2.hasNext()) {
			if(!this.controllaSet(m1.get(iter1.next()), m2.get(iter2.next()))) {
				return false;
			}
		}
		return true;
	}
}