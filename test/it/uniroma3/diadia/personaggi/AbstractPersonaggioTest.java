package it.uniroma3.diadia.personaggi;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Questa classe testa tutti i metodi della classe AbstractPersonaggio
 *
 * @author Feded0 (609805) e Civan04 (605634)
 * @see AbstractPersonaggio
 * @version C
 */

class AbstractPersonaggioTest {

    private AbstractPersonaggio personaggio;

    @BeforeEach
    void setUp() {
        // Uso una sottoclasse anonima concreta per testare AbstractPersonaggio
        personaggio = new FakePersonaggio("Gandalf", "Sono un mago saggio.");
    }
    
    // Classe fittizia per testare la classe astratta
    private static class FakePersonaggio extends AbstractPersonaggio {

        public FakePersonaggio(String nome, String presentazione) {
            super(nome, presentazione);
        }

        @Override
        public String agisci(Partita partita) {
            return "Azione fittizia";
        }

        @Override
        public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
            return "Grazie per il " + attrezzo.getNome();
        }
    }
    
    @Test
    void testGetNome() {
        assertEquals("Gandalf", personaggio.getNome());
    }

    @Test
    void testSaluta_PrimaVolta() {
        String messaggio = personaggio.saluta();
        assertTrue(messaggio.contains("Ciao, io sono Gandalf."));
        assertTrue(messaggio.contains("Sono un mago saggio."));
    }

    @Test
    void testSaluta_DopoPrimaVolta() {
        personaggio.saluta();
        String secondoSaluto = personaggio.saluta();
        assertTrue(secondoSaluto.contains("Ci siamo già presentati!"));
    }

    @Test
    void testHaSalutato() {
        assertFalse(personaggio.haSalutato());
        personaggio.saluta();
        assertTrue(personaggio.haSalutato());
    }

    @Test
    void testToString() {
        assertEquals("Gandalf", personaggio.toString());
    }

}
