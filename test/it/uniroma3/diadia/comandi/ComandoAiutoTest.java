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
 * Questa classe testa tutti i metodi della classe ComandoAiuto
 *
 * @author Feded0 (609805) e Civan04 (605634)
 * @see ComandoAiuto
 * @version C
 */

class ComandoAiutoTest {

    private ComandoAiuto comandoAiuto;
    private Partita partita;
    private IOSimulator io;

    @BeforeEach
    public void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
        comandoAiuto = new ComandoAiuto();
        partita = new Partita(Labirinto.newBuilder("LabirintoPerTest.txt").getLabirinto());
        io = new IOSimulator(Arrays.asList());
        comandoAiuto.setIO(io);
    }

    /* TEST per esegui */
    @Test
    public void testEsegui_MostraTuttiComandi() {
        comandoAiuto.esegui(partita);
        
        assertEquals("Aiuto",  		io.contieneMessaggioAtIndice(0));
        assertEquals("Fine",  		io.contieneMessaggioAtIndice(1));
        assertEquals("Guarda", 		io.contieneMessaggioAtIndice(2));
        assertEquals("Interagisci", io.contieneMessaggioAtIndice(3));
        assertEquals("Posa",   		io.contieneMessaggioAtIndice(4));
        assertEquals("Prendi", 		io.contieneMessaggioAtIndice(5));
        assertEquals("Regala",    	io.contieneMessaggioAtIndice(6));
        assertEquals("Saluta",    	io.contieneMessaggioAtIndice(7));
        assertEquals("Vai",    		io.contieneMessaggioAtIndice(8));
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
    public void testSetParametro_Uguale() {
        comandoAiuto.setParametro("qualunque");
        assertEquals("qualunque", comandoAiuto.getParametro());
    }

}