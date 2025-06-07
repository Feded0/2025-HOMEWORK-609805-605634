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
import it.uniroma3.diadia.personaggi.Cane;

/**
 * Questa classe testa tutti i metodi della classe ComandoSaluta
 * 
 * @author Feded0 (609805) e Civan04 (605634)
 * @see ComandoSaluta
 * @version C
 */

class ComandoSalutaTest {

	private ComandoSaluta comando;
	private IOSimulator io;
	private Partita partita;
	private Cane personaggio;
	
	@BeforeEach
	void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
		this.comando = new ComandoSaluta();
		this.io = new IOSimulator(Arrays.asList());
		this.comando.setIO(io);
		this.personaggio = new Cane("Cane", "Sono affamato");
		this.partita = new Partita(Labirinto.newBuilder("LabirintoPerTest.txt").getLabirinto());
	}

	@Test
	void testEsegui_ConPersonaggio() {
		this.partita.getStanzaCorrente().setPersonaggio(personaggio);
		comando.esegui(partita);
		
		assertTrue(io.contieneMessaggio("Ciao, io sono Cane. Sono affamato"));
	}

	@Test
	void testEsegui_SenzaPersonaggio() {
		comando.esegui(partita);

		assertTrue(io.contieneMessaggio("Non c'è alcun personaggio in questa stanza!"));
	}
	
	@Test
    public void testGetNome() {
        assertEquals("saluta", comando.getNome());
    }
}
