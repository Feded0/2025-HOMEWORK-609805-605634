package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;

/**
 * Questa classe testa tutti i metodi della classe ComandoNonValido
 *
 * @author Feded0 (609805) e Civan04 (605634)
 * @see ComandoNonValido
 * @version C
 */

class ComandoNonValidoTest {

    private ComandoNonValido comandoNonValido;
    private Partita partita;
    private IOSimulator io;

    @BeforeEach
    public void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
        comandoNonValido = new ComandoNonValido();
        partita = new Partita(Labirinto.newBuilder("LabirintoPerTest.txt").getLabirinto());
        io = new IOSimulator(Arrays.asList());
        comandoNonValido.setIO(io);
    }

    /* TEST per esegui */
    @Test
    public void testEsegui_MostraMessaggioErrore() {
        comandoNonValido.esegui(partita);
        
        assertTrue(io.contieneMessaggio("Hai inserito un comando non valido"));
    }

    @Test
    public void testEsegui_NonModificaPartita() {
        boolean statoIniziale = partita.isFinita();
        int cfuIniziali = partita.getGiocatore().getCfu();
        
        comandoNonValido.esegui(partita);
        
        assertEquals(statoIniziale, partita.isFinita());
        assertEquals(cfuIniziali, partita.getGiocatore().getCfu());
    }

    @Test
    public void testEsegui_MultipliMostranoMultipliMessaggi() {
        comandoNonValido.esegui(partita);
        comandoNonValido.esegui(partita);
        
        assertEquals("Hai inserito un comando non valido", io.contieneMessaggioAtIndice(0));
        assertEquals("Hai inserito un comando non valido", io.contieneMessaggioAtIndice(1));
    }
    
    /* TEST per get */
    @Test
    public void testGetNome() {
        assertEquals("non valido", comandoNonValido.getNome());
    }

    @Test
    public void testGetParametro() {
        assertNull(comandoNonValido.getParametro());
    }

    /* TEST per setParametro */
    @Test
    public void testSetParametro_Uguale() {
        comandoNonValido.setParametro("qualunque");
        assertEquals("qualunque", comandoNonValido.getParametro());
    }
    
}