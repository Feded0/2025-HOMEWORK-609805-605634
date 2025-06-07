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
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

/**
 * Questa classe testa tutti i metodi della classe ComandoPrendi
 *
 * @author Feded0 (609805) e Civan04 (605634)
 * @see ComandoPrendi
 * @version C
 */

class ComandoPrendiTest {

    private ComandoPrendi comandoPrendi;
    private Partita partita;
    private IOSimulator io;
    private Stanza stanza;
    private Attrezzo attrezzoTest;

    @BeforeEach
    public void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
        comandoPrendi = new ComandoPrendi();
        partita = new Partita(Labirinto.newBuilder("LabirintoPerTest.txt").getLabirinto());
        io = new IOSimulator(Arrays.asList());
        comandoPrendi.setIO(io);
        
        stanza = new Stanza("Stanza Test");
        partita.setStanzaCorrente(stanza);
        
        attrezzoTest = new Attrezzo("osso", 1);
        stanza.addAttrezzo(attrezzoTest);
    }

    /* TEST per esegui */
    @Test
    public void testEsegui_AttrezzoPresente() {
        comandoPrendi.setParametro("osso");
        comandoPrendi.esegui(partita);
        
        assertTrue(io.contieneMessaggio("osso aggiunto all'inventario"));
        assertFalse(stanza.hasAttrezzo("osso"));
        assertTrue(partita.getGiocatoreBorsa().hasAttrezzo("osso"));
    }

    @Test
    public void testEsegui_AttrezzoNonPresente() {
        comandoPrendi.setParametro("lanterna");
        comandoPrendi.esegui(partita);
        
        assertTrue(io.contieneMessaggio("L'attrezzo non è stato trovato"));
    }

    @Test
    public void testEsegui_ConStanzaVuota() {
        stanza.removeAttrezzo(attrezzoTest);
        comandoPrendi.setParametro("osso");
        comandoPrendi.esegui(partita);
        
        assertTrue(io.contieneMessaggio("La stanza non ha attrezzi"));
    }

    @Test
    public void testEsegui_SenzaSpecificareAttrezzo() {
        comandoPrendi.setParametro(null);
        comandoPrendi.esegui(partita);
        
        assertTrue(io.contieneMessaggio("Nessun attrezzo inserito"));
    }

    @Test
    public void testEsegui_ConBorsaPiena() {
        // Riempie la borsa
        Borsa borsa = partita.getGiocatoreBorsa();
        for(int i = 0; i < 10; i++) {
            borsa.addAttrezzo(new Attrezzo("attrezzo"+i, 1));
        }
        
        comandoPrendi.setParametro("osso");
        comandoPrendi.esegui(partita);
        
        assertTrue(io.contieneMessaggio("Non c'è abbastanza spazio nella borsa"));
        assertTrue(stanza.hasAttrezzo("osso"));
    }

    @Test
    public void testEsegui_ConBorsaQuasiPiena() {
        // Riempie la borsa quasi completamente
        Borsa borsa = partita.getGiocatoreBorsa();
        for(int i = 0; i < 9; i++) {
            borsa.addAttrezzo(new Attrezzo("attrezzo"+i, 1));
        }
        
        comandoPrendi.setParametro("osso");
        comandoPrendi.esegui(partita);
        
        assertFalse(stanza.hasAttrezzo("osso"));
        assertTrue(borsa.hasAttrezzo("osso"));
    }

    @Test
    public void testEsegui_AttrezzoPesanteLimiteBorsa() {
        Attrezzo pesante = new Attrezzo("incudine", 10);
        stanza.addAttrezzo(pesante);
        
        comandoPrendi.setParametro("incudine");
        comandoPrendi.esegui(partita);
        
        assertTrue(io.contieneMessaggio("incudine aggiunto all'inventario"));
        assertTrue(partita.getGiocatoreBorsa().hasAttrezzo("incudine"));
    }

    @Test
    public void testEsegui_AttrezzoConStessoNomeInBorsa() {
        partita.getGiocatoreBorsa().addAttrezzo(new Attrezzo("osso", 2));
        comandoPrendi.setParametro("osso");
        comandoPrendi.esegui(partita);
        
        assertFalse(io.contieneMessaggio("osso aggiunto all'inventario"));
        assertEquals("osso (2kg)", partita.getGiocatoreBorsa().getAttrezzo("osso").toString());
    }

    @Test
    public void testEsegui_MultipliAttrezzi() {
        stanza.addAttrezzo(new Attrezzo("lanterna", 3));
        
        comandoPrendi.setParametro("osso");
        comandoPrendi.esegui(partita);
        
        comandoPrendi.setParametro("lanterna");
        comandoPrendi.esegui(partita);
        
        assertTrue(partita.getGiocatoreBorsa().hasAttrezzo("osso"));
        assertTrue(partita.getGiocatoreBorsa().hasAttrezzo("lanterna"));
        assertTrue(stanza.isEmpty());
    }

    @Test
    public void testEsegui_ConPesoSuperioreALimiteBorsa() {
        Attrezzo pesante = new Attrezzo("macigno", 15);
        stanza.addAttrezzo(pesante);
        
        comandoPrendi.setParametro("macigno");
        comandoPrendi.esegui(partita);
        
        assertTrue(io.contieneMessaggio("Non c'è abbastanza spazio nella borsa"));
        assertTrue(stanza.hasAttrezzo("macigno"));
    }
    
    /* TEST per get */
    @Test
    public void testGetNome() {
        assertEquals("prendi", comandoPrendi.getNome());
    }

    @Test
    public void testGetParametro() {
        comandoPrendi.setParametro("osso");
        assertEquals("osso", comandoPrendi.getParametro());
    }

    /* TEST per setParametro */
    @Test
    public void testSetParametro() {
        comandoPrendi.setParametro("spada");
        assertEquals("spada", comandoPrendi.getParametro());
    }
    
}