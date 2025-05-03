package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;

/**
 * Questa classe testa tutti i metodi della classe ComandoNonValido
 *
 * @author Feded0 (609805) e Civan04 (605634)
 * @see ComandoNonValido
 * @version B
 */

class ComandoNonValidoTest {

    private ComandoNonValido comandoNonValido;
    private Partita partita;
    private IOSimulator io;

    @BeforeEach
    public void setUp() {
        comandoNonValido = new ComandoNonValido();
        partita = new Partita();
        io = new IOSimulator(new String[0]);
        comandoNonValido.setIO(io);
    }

    /* TEST per esegui */
    @Test
    public void testEsegui_MostraMessaggioErrore() {
        comandoNonValido.esegui(partita);
        
        assertEquals(1, io.getOutput().length);
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
        String[] output = io.getOutput();
        
        assertEquals(2, output.length);
        assertEquals("Hai inserito un comando non valido", output[0]);
        assertEquals("Hai inserito un comando non valido", output[1]);
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
    public void testSetParametro_NonHaEffetti() {
        comandoNonValido.setParametro("qualunque");
        assertNull(comandoNonValido.getParametro());
    }
    
}