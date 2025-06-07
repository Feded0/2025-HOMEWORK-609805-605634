package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;

/**
 * Questa classe testa tutti i metodi della classe AbstractComando
 *
 * @author Feded0 (609805) e Civan04 (605634)
 * @see AbstractComando
 * @version C
 */

// Classe concreta fittizia per testare AbstractComando
class ComandoDiTest extends AbstractComando {
    private boolean eseguito = false;

    @Override
    public void esegui(Partita partita) {
        eseguito = true;
        if (io != null && parametro != null) {
            io.mostraMessaggio("Eseguito con parametro: " + parametro);
        }
    }

    public boolean isEseguito() {
        return eseguito;
    }

    public IO getIO() {
        return this.io;
    }

    @Override
    public String getNome() {
        return "comando di test";
    }
}

class AbstractComandoTest {

    private ComandoDiTest comando;
    private IOSimulator ioSimulator;

    @BeforeEach
    public void setUp() {
        comando = new ComandoDiTest();
        List<String> inputList = new ArrayList<>();
        inputList.add("input simulato");
        ioSimulator = new IOSimulator(inputList);
    }

    @Test
    public void testSetParametro_nonNull() {
        comando.setParametro("nord");
        assertEquals("nord", comando.getParametro());
    }

    @Test
    public void testSetParametro_null() {
        comando.setParametro(null);
        assertNull(comando.getParametro());
    }

    @Test
    public void testSetParametro_vuoto() {
        comando.setParametro("");
        assertEquals("", comando.getParametro());
    }

    @Test
    public void testSetIO_nonNull() {
        comando.setIO(ioSimulator);
        assertEquals(ioSimulator, comando.getIO());
    }

    @Test
    public void testEsegui_eseguitoFlag() throws Exception {
        Partita partita = new Partita(Labirinto.newBuilder("LabirintoPerTest.txt").getLabirinto());
        comando.esegui(partita);
        assertTrue(comando.isEseguito());
    }

    @Test
    public void testEsegui_conIOeParametro() throws Exception {
        Partita partita = new Partita(Labirinto.newBuilder("LabirintoPerTest.txt").getLabirinto());
        comando.setIO(ioSimulator);
        comando.setParametro("sud");
        assertDoesNotThrow(() -> comando.esegui(partita));
        assertTrue(comando.isEseguito());
    }
}