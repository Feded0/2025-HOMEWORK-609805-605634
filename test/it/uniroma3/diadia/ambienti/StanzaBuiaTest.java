package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Questa classe testa tutti i metodi della classe StanzaBuia
 *
 * @author Feded0 (609805) e Civan04 (605634)
 * @see StanzaBuia
 * @version C
 */

class StanzaBuiaTest {

    private StanzaBuia stanzaBuia;
    private Attrezzo lanterna;
    private Attrezzo altroAttrezzo;

    @BeforeEach
    public void setUp() {
        stanzaBuia = new StanzaBuia("Stanza Buia", "lanterna");
        lanterna = new Attrezzo("lanterna", 1);
        altroAttrezzo = new Attrezzo("osso", 1);
    }

    /* TEST per getDescrizione */
    @Test
    public void testGetDescrizione_SenzaAttrezzoIlluminante() {
        assertEquals("Qui c'è buio pesto!", stanzaBuia.getDescrizione());
    }

    @Test
    public void testGetDescrizione_ConAttrezzoIlluminante() {
        stanzaBuia.addAttrezzo(lanterna);
        assertNotEquals("Qui c'è buio pesto!", stanzaBuia.getDescrizione());
        assertTrue(stanzaBuia.getDescrizione().contains("Stanza Buia"));
    }

    @Test
    public void testGetDescrizione_ConAltroAttrezzo() {
        stanzaBuia.addAttrezzo(altroAttrezzo);
        assertEquals("Qui c'è buio pesto!", stanzaBuia.getDescrizione());
    }

    @Test
    public void testGetDescrizione_DopoRimozioneAttrezzoIlluminante() {
        stanzaBuia.addAttrezzo(lanterna);
        assertNotEquals("Qui c'è buio pesto!", stanzaBuia.getDescrizione());
        stanzaBuia.removeAttrezzo(lanterna);
        assertEquals("Qui c'è buio pesto!", stanzaBuia.getDescrizione());
    }

    @Test
    public void testGetDescrizione_ConAttrezzoIlluminanteEDueAttrezzi() {
        stanzaBuia.addAttrezzo(altroAttrezzo);
        stanzaBuia.addAttrezzo(lanterna);
        assertNotEquals("Qui c'è buio pesto!", stanzaBuia.getDescrizione());
        assertTrue(stanzaBuia.getDescrizione().contains("Stanza Buia"));
        assertTrue(stanzaBuia.getDescrizione().contains("osso"));
        assertTrue(stanzaBuia.getDescrizione().contains("lanterna"));
    }

    @Test
    public void testGetDescrizione_ConAttrezzoIlluminanteNomeDiverso() {
        StanzaBuia stanzaConDiversoAttrezzo = new StanzaBuia("Stanza 2", "torcia");
        stanzaConDiversoAttrezzo.addAttrezzo(new Attrezzo("torcia", 1));
        assertNotEquals("Qui c'è buio pesto!", stanzaConDiversoAttrezzo.getDescrizione());
    }

    @Test
    public void testGetDescrizione_ConAttrezzoIlluminanteMaiuscolo() {
        stanzaBuia.addAttrezzo(new Attrezzo("LANTERNA", 1));
        assertEquals("Qui c'è buio pesto!", stanzaBuia.getDescrizione());
    }
    
}