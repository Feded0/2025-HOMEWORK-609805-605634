package it.uniroma3.diadia.personaggi;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Questa classe testa tutti i metodi della classe Cane
 *
 * @author Feded0 (609805) e Civan04 (605634)
 * @see Cane
 * @version C
 */

class CaneTest {

    private Cane cane;
    private Partita partita;

    @BeforeEach
    void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
        cane = new Cane("Fido", "Sono affamato");
        this.partita = new Partita(Labirinto.newBuilder("LabirintoPerTest.txt").getLabirinto());
		this.partita.getStanzaCorrente().setPersonaggio(cane);
    }

    @Test
    void testAgisci_DecrementaCfu() {
        int cfuIniziali = partita.getGiocatore().getCfu();
        String risposta = cane.agisci(partita);
        
        assertEquals("BAU! BAU! BAU!", risposta);
        assertEquals(cfuIniziali - 1, partita.getGiocatore().getCfu());
    }

    @Test
    void testRiceviRegalo_CiboPreferito() {
        Attrezzo osso = new Attrezzo("osso", 1);
        String risposta = cane.riceviRegalo(osso, partita);
        
        assertTrue(risposta.contains("è il mio cibo preferito!"));
        assertTrue(partita.getStanzaCorrente().hasAttrezzo("palla"));
    }

    @Test
    void testRiceviRegalo_CiboNonPreferito() {
        Attrezzo bastone = new Attrezzo("bastone", 1);
        int cfuIniziali = partita.getGiocatore().getCfu();
        
        String risposta = cane.riceviRegalo(bastone, partita);
        
        assertTrue(risposta.contains("non mi piace"));
        assertEquals(cfuIniziali - 1, partita.getGiocatore().getCfu());
        assertFalse(partita.getStanzaCorrente().hasAttrezzo("palla"));
    }

    @Test
    void testSaluta_ComportamentoEreditato() {
        String saluto1 = cane.saluta();
        String saluto2 = cane.saluta();

        assertTrue(saluto1.contains("Fido"));
        assertTrue(saluto1.contains("Sono affamato"));
        assertTrue(saluto2.contains("già presentati"));
    }
}
