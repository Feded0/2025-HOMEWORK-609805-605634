package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;

/**
 * Questa classe testa tutti i metodi della classe ComandoAiuto
 *
 * @author Feded0 (609805) e Civan04 (605634)
 * @see ComandoAiuto
 * @version B
 */

class ComandoAiutoTest {

    private ComandoAiuto comandoAiuto;
    private Partita partita;
    private IOSimulator io;

    @BeforeEach
    public void setUp() {
        comandoAiuto = new ComandoAiuto();
        partita = new Partita();
        io = new IOSimulator(new String[0]);
        comandoAiuto.setIO(io);
    }

    /* TEST per esegui */
    @Test
    public void testEsegui_MostraTuttiComandi() {
        comandoAiuto.esegui(partita);
        String[] output = io.getOutput();
        
        assertEquals(7, output.length); // 6 comandi + stringa vuota finale di a capo
        assertEquals("aiuto", output[0]);
        assertEquals("vai", output[1]);
        assertEquals("prendi", output[2]);
        assertEquals("posa", output[3]);
        assertEquals("guarda", output[4]);
        assertEquals("fine", output[5]);
        assertEquals("", output[6]);
    }

    /* TEST per get */
    @Test
    public void testGetNome() {
        assertEquals("aiuto", comandoAiuto.getNome());
    }

    @Test
    public void testGetParametro() {
        assertNull(comandoAiuto.getParametro());
    }

    /* TEST per setParametro */
    @Test
    public void testSetParametro_NonFaNiente() {
        comandoAiuto.setParametro("qualunque");
        assertNull(comandoAiuto.getParametro());
    }

}