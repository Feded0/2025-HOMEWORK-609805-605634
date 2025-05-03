package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Questa classe testa tutti i metodi della classe StanzaMagica
 *
 * @author Feded0 (609805) e Civan04 (605634)
 * @see StanzaMagica
 * @version B
 */

class StanzaMagicaTest {

    private StanzaMagica stanzaMagica;
    private Attrezzo attrezzoNormale;
    private Attrezzo attrezzoMagico;

    @BeforeEach
    public void setUp() {
        stanzaMagica = new StanzaMagica("Stanza Magica");
        attrezzoNormale = new Attrezzo("spada", 3);
        attrezzoMagico = new Attrezzo("pala", 2);
    }

    @Test
    public void testAttrezziNormali_PrimaDellaSoglia() {
        // Aggiunge attrezzi senza superare la soglia (default 3)
        assertTrue(stanzaMagica.addAttrezzo(new Attrezzo("osso", 1)));
        assertTrue(stanzaMagica.addAttrezzo(new Attrezzo("lanterna", 2)));
        assertTrue(stanzaMagica.addAttrezzo(new Attrezzo("chiave", 1)));
        
        // Verifica che gli attrezzi non siano stati modificati
        assertEquals("osso", stanzaMagica.getAttrezzo("osso").getNome());
        assertEquals(1, stanzaMagica.getAttrezzo("osso").getPeso());
        assertEquals("lanterna", stanzaMagica.getAttrezzo("lanterna").getNome());
        assertEquals(2, stanzaMagica.getAttrezzo("lanterna").getPeso());
    }

    @Test
    public void testAttrezziMagici_DopoLaSoglia() {
        // Raggiunge la soglia
        stanzaMagica.addAttrezzo(new Attrezzo("osso", 1));
        stanzaMagica.addAttrezzo(new Attrezzo("lanterna", 2));
        stanzaMagica.addAttrezzo(new Attrezzo("chiave", 1));
        
        // Il quarto attrezzo dovrebbe diventare magico
        assertTrue(stanzaMagica.addAttrezzo(attrezzoMagico));
        
        // Verifica la trasformazione magica
        assertNull(stanzaMagica.getAttrezzo("pala")); // Nome originale non presente
        Attrezzo trasformato = stanzaMagica.getAttrezzo("alap");
        assertNotNull(trasformato);
        assertEquals("alap", trasformato.getNome());
        assertEquals(4, trasformato.getPeso()); // Peso raddoppiato
    }

    @Test
    public void testSogliaPersonalizzata() {
        StanzaMagica stanzaCustom = new StanzaMagica("Custom", 1); // Soglia a 1
        
        // Primo attrezzo normale
        assertTrue(stanzaCustom.addAttrezzo(attrezzoNormale));
        assertEquals("spada", stanzaCustom.getAttrezzo("spada").getNome());
        
        // Secondo attrezzo dovrebbe essere magico
        assertTrue(stanzaCustom.addAttrezzo(attrezzoMagico));
        assertNull(stanzaCustom.getAttrezzo("pala"));
        assertNotNull(stanzaCustom.getAttrezzo("alap"));
        assertEquals(4, stanzaCustom.getAttrezzo("alap").getPeso());
    }

    @Test
    public void testMultipliAttrezziMagici() {
        // Supera la soglia
        stanzaMagica.addAttrezzo(new Attrezzo("osso", 1));
        stanzaMagica.addAttrezzo(new Attrezzo("lanterna", 2));
        stanzaMagica.addAttrezzo(new Attrezzo("chiave", 1));
        
        // Aggiunge due attrezzi magici
        assertTrue(stanzaMagica.addAttrezzo(new Attrezzo("martello", 3)));
        assertTrue(stanzaMagica.addAttrezzo(new Attrezzo("scudo", 2)));
        
        // Verifica le trasformazioni
        assertNotNull(stanzaMagica.getAttrezzo("olletram"));
        assertEquals(6, stanzaMagica.getAttrezzo("olletram").getPeso());
        assertNotNull(stanzaMagica.getAttrezzo("oducs"));
        assertEquals(4, stanzaMagica.getAttrezzo("oducs").getPeso());
    }

    @Test
    public void testAttrezzoConNomeLungo() {
        stanzaMagica = new StanzaMagica("Magica", 0); // Soglia a 0 per test immediato
        
        Attrezzo lungo = new Attrezzo("qwertyuiopasdfghjklzxcvbnm", 1);
        assertTrue(stanzaMagica.addAttrezzo(lungo));
        
        String nomeInvertito = "mnbvcxzlkjhgfdsapoiuytrewq";
        assertNotNull(stanzaMagica.getAttrezzo(nomeInvertito));
        assertEquals(2, stanzaMagica.getAttrezzo(nomeInvertito).getPeso());
    }
    
}