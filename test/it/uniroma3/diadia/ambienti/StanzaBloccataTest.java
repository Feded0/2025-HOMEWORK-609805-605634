package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Questa classe testa tutti i metodi della classe StanzaBloccata
 *
 * @author Feded0 (609805) e Civan04 (605634)
 * @see StanzaBloccata
 * @version C
 */

class StanzaBloccataTest {

    private StanzaBloccata stanzaBloccata;
    private Stanza stanzaAdiacente;
    private Attrezzo attrezzoSbloccante;

    @BeforeEach
    public void setUp() {
        stanzaBloccata = new StanzaBloccata("StanzaBloccata", Direzione.nord, "chiave");
        stanzaAdiacente = new Stanza("StanzaAdiacente");
        stanzaBloccata.impostaStanzaAdiacente(Direzione.nord, stanzaAdiacente);
        attrezzoSbloccante = new Attrezzo("chiave", 1);
    }

    /* TEST per getStanzaAdiacente */
    @Test
    public void testGetStanzaAdiacente_DirezioneBloccataSenzaAttrezzo() {
        Stanza risultato = stanzaBloccata.getStanzaAdiacente(Direzione.nord);
        assertEquals(stanzaBloccata, risultato);
    }

    @Test
    public void testGetStanzaAdiacente_DirezioneBloccataConAttrezzo() {
        stanzaBloccata.addAttrezzo(attrezzoSbloccante);
        Stanza risultato = stanzaBloccata.getStanzaAdiacente(Direzione.nord);
        assertEquals(stanzaAdiacente, risultato);
    }

    @Test
    public void testGetStanzaAdiacente_DirezioneNonBloccataNull() {
        Stanza risultato = stanzaBloccata.getStanzaAdiacente(Direzione.sud);
        assertNull(risultato); // Non c'è stanza adiacente a sud
    }

    @Test
    public void testGetStanzaAdiacente_ConAttrezzoSbagliato() {
        stanzaBloccata.addAttrezzo(new Attrezzo("pala", 1));
        Stanza risultato = stanzaBloccata.getStanzaAdiacente(Direzione.nord);
        assertEquals(stanzaBloccata, risultato);
    }

    @Test
    public void testGetStanzaAdiacente_ConDirezioneDiversa() {
        stanzaBloccata.impostaStanzaAdiacente(Direzione.est, stanzaAdiacente);
        Stanza risultato = stanzaBloccata.getStanzaAdiacente(Direzione.est);
        assertEquals(stanzaAdiacente, risultato);
    }
    
    /* TEST per getDescrizione */
    @Test
    public void testGetDescrizione_SenzaAttrezzo() {
        String descrizione = stanzaBloccata.getDescrizione();
        assertTrue(descrizione.contains("bloccata"));
        assertTrue(descrizione.contains("serve chiave"));
    }
    
    @Test
    public void testGetDescrizione_ConAttrezzo() {
        stanzaBloccata.addAttrezzo(attrezzoSbloccante);
        String descrizione = stanzaBloccata.getDescrizione();
        assertTrue(descrizione.contains("sbloccata"));
        assertTrue(descrizione.contains("chiave"));
    }
    
}