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
 * Questa classe testa tutti i metodi della classe Mago
 *
 * @author Feded0 (609805) e Civan04 (605634)
 * @see Mago
 * @version C
 */

class MagoTest {

    private Mago mago;
    private Partita partita;
    private Attrezzo dono;

    @BeforeEach
    void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
        dono = new Attrezzo("bacchetta", 1);
        mago = new Mago("Merlino", "Sono un mago!", dono);
        this.partita = new Partita(Labirinto.newBuilder("LabirintoPerTest.txt").getLabirinto());
		this.partita.getStanzaCorrente().setPersonaggio(mago);
    }

    @Test
    void testAgisci_PrimaVoltaRiceveAttrezzo() {
        String risposta = mago.agisci(partita);
        
        assertTrue(risposta.contains("magica azione"));
        assertTrue(partita.getStanzaCorrente().hasAttrezzo("bacchetta"));
    }

    @Test
    void testAgisci_SecondaVoltaNullaDaDare() {
        mago.agisci(partita);
        String risposta = mago.agisci(partita);
        
        assertTrue(risposta.contains("non ho piu' nulla"));
        assertEquals(2, partita.getStanzaCorrente().getAttrezzi().size()); // la stanza corrente ha già un attrezzo (l'osso)
    }

    @Test
    void testRiceviRegalo_AggiungeAttrezzoModificato() {
        Attrezzo regalo = new Attrezzo("libro", 4);
        String risposta = mago.riceviRegalo(regalo, partita);

        assertTrue(risposta.contains("alleggerirò"));
        Attrezzo modificato = partita.getStanzaCorrente().getAttrezzo("libro");

        assertNotNull(modificato);
        assertEquals(2, modificato.getPeso());
    }

    @Test
    void testSaluta() {
        String saluto1 = mago.saluta();
        String saluto2 = mago.saluta();

        assertTrue(saluto1.contains("Merlino"));
        assertTrue(saluto1.contains("Sono un mago!"));
        assertTrue(saluto2.contains("già presentati"));
    }
}
