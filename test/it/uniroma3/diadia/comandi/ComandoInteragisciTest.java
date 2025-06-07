package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

import java.io.FileNotFoundException;
import java.util.Arrays;

/**
 * Questa classe testa tutti i metodi della classe ComandoInteragisci
 *
 * @author Feded0 (609805) e Civan04 (605634)
 * @see ComandoInteragisci
 * @version C
 */

class ComandoInteragisciTest {

    private ComandoInteragisci comando;
    private IOSimulator io;
    private Partita partita;

    @BeforeEach
    void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
        this.comando = new ComandoInteragisci();
        this.io = new IOSimulator(Arrays.asList()); // Nessun input necessario
        this.comando.setIO(this.io);

        partita = new Partita(Labirinto.newBuilder("LabirintoPerTest.txt").getLabirinto());
    }

    @Test
    void testInteragisciConPersonaggio() {
        AbstractPersonaggio personaggio = new AbstractPersonaggio("Drago", "Un drago amichevole") {
            @Override
            public String agisci(Partita partita) {
                return "Il drago ti ringrazia.";
            }

            @Override
            public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
                return "Grazie per il regalo!";
            }
        };
        
        this.partita.getStanzaCorrente().setPersonaggio(personaggio);
        this.comando.esegui(this.partita);

        assertTrue(io.contieneMessaggio("Il drago ti ringrazia."));
    }

    @Test
    void testInteragisciSenzaPersonaggio() {
        this.comando.esegui(this.partita);
        assertTrue(io.contieneMessaggio("Con chi dovrei interagire?..."));
    }
}
