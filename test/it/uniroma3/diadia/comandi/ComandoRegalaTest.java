package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.Arrays;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Cane;

/**
 * Questa classe testa tutti i metodi della classe ComandoRegala
 *
 * @author Feded0 (609805) e Civan04 (605634)
 * @see ComandoRegala
 * @version C
 */

class ComandoRegalaTest {

	private ComandoRegala comando;
	private IOSimulator io;
	private Partita partita;
	private Attrezzo osso;
	private Cane personaggio;
	
	@BeforeEach
	void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
		this.comando = new ComandoRegala();
		this.io = new IOSimulator(Arrays.asList());
		this.comando.setIO(io);
		this.osso = new Attrezzo("osso", 1);
		this.personaggio = new Cane("Cane", "Sono affamato");
		this.partita = new Partita(Labirinto.newBuilder("LabirintoPerTest.txt").getLabirinto());
		this.partita.getStanzaCorrente().setPersonaggio(personaggio);
	}

	@Test
	void testEsegui_ConPersonaggioEAttrezzoPresente() {
		partita.getGiocatore().getBorsa().addAttrezzo(osso);
		comando.setParametro("osso");
		comando.esegui(partita);
		assertTrue(io.contieneMessaggio("BAU grazie per avermi regalato osso, è il mio cibo preferito!"));
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("osso"));
	}

	@Test
	void testEsegui_ConPersonaggioSenzaAttrezzo() {
		comando.setParametro("osso");
		comando.esegui(partita);
		
		assertTrue(io.contieneMessaggio("Attrezzo non trovato"));
	}

	@Test
	void testEsegui_ConPersonaggioMaSenzaParametro() {
		comando.setParametro(null); // parametro non specificato
		comando.esegui(partita);

		assertTrue(io.contieneMessaggio("Devi specificare un oggetto da regalare"));
	}

	@Test
	void testEsegui_SenzaPersonaggio() {
		comando.setParametro("osso");
		partita.getGiocatore().getBorsa().addAttrezzo(osso);
		this.partita.getStanzaCorrente().setPersonaggio(null); // rimuoviamo il personaggio dalla stanza
		comando.esegui(partita);
		
		assertTrue(io.contieneMessaggio("Non ci sono personaggi nella stanza, nulla da regalare"));
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("osso")); // attrezzo ancora presente
	}
	
	@Test
    public void testGetNome() {
        assertEquals("regala", comando.getNome());
    }
}
