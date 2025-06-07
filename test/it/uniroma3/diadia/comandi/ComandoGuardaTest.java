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
 * Questa classe testa tutti i metodi della classe ComandoGuarda
 *
 * @author Feded0 (609805) e Civan04 (605634)
 * @see ComandoGuarda
 * @version C
 */

class ComandoGuardaTest {

    private ComandoGuarda comandoGuarda;
    private Partita partita;
    private IOSimulator io;

    @BeforeEach
    public void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
        comandoGuarda = new ComandoGuarda();
        partita = new Partita(Labirinto.newBuilder("LabirintoPerTest.txt").getLabirinto());
        io = new IOSimulator(Arrays.asList());
        comandoGuarda.setIO(io);
    }

    /* TEST per esegui */
    @Test
    public void testEsegui_MostraDescrizioneStanza() {
        // Configura una stanza con descrizione nota
        Stanza stanza = new Stanza("Stanza di Test");
        stanza.addAttrezzo(new Attrezzo("osso", 1));
        partita.setStanzaCorrente(stanza);       
        comandoGuarda.esegui(partita);
        
        assertTrue(io.contieneMessaggio("Stanza di Test"));
        assertTrue(io.contieneMessaggio("osso"));
    }

    @Test
    public void testEsegui_MostraCFUCorretti() {
        int cfuIniziali = partita.getGiocatore().getCfu();
        comandoGuarda.esegui(partita);

        assertTrue(io.contieneMessaggio(String.valueOf(cfuIniziali)));
    }

    @Test
    public void testEsegui_MostraContenutoBorsa() {
        // Aggiungi un attrezzo alla borsa
        Borsa borsa = partita.getGiocatoreBorsa();
        borsa.addAttrezzo(new Attrezzo("lanterna", 3));
        comandoGuarda.esegui(partita);
        
        assertTrue(io.contieneMessaggio("lanterna"));
        assertTrue(io.contieneMessaggio("3kg"));
    }

    @Test
    public void testEsegui_ConBorsaVuota() {
        comandoGuarda.esegui(partita);       
        assertTrue(io.contieneMessaggio("Borsa vuota"));
    }

    @Test
    public void testEsegui_ConStanzaSenzaAttrezzi() {
        Stanza stanzaVuota = new Stanza("Stanza Vuota");
        partita.setStanzaCorrente(stanzaVuota);
        comandoGuarda.esegui(partita);
        
        assertTrue(io.contieneMessaggio("Stanza Vuota"));
        assertTrue(io.contieneMessaggio("Nessun attrezzo"));
    }
    
    @Test
    public void testEsegui_OutputContieneEsattamenteTreMessaggi() {
        comandoGuarda.esegui(partita);
        assertNotNull(io.contieneMessaggioAtIndice(0));
        assertNotNull(io.contieneMessaggioAtIndice(1));
        assertNotNull(io.contieneMessaggioAtIndice(2));
    }
    

    @Test
    public void testEseguiGuardaBorsa_Vuota() {
        this.comandoGuarda.setParametro("borsa");
        this.comandoGuarda.esegui(partita);
        assertTrue(io.contieneMessaggio("Borsa vuota"));
    }

    @Test
    public void testEseguiGuardaBorsa_ConUnAttrezzo() {
        partita.getGiocatoreBorsa().addAttrezzo(new Attrezzo("spada", 3));
        this.comandoGuarda.setParametro("borsa");
        this.comandoGuarda.esegui(partita);
        assertTrue(io.contieneMessaggio("spada"));
        assertTrue(io.contieneMessaggio("[Contenuto Borsa]"));
    }

    @Test
    public void testEseguiGuardaBorsa_ConAttrezziOrdinatiPerPeso() {
        partita.getGiocatoreBorsa().addAttrezzo(new Attrezzo("lanterna", 2));
        partita.getGiocatoreBorsa().addAttrezzo(new Attrezzo("spada", 5));
        this.comandoGuarda.setParametro("borsa");
        this.comandoGuarda.esegui(partita);
        assertTrue(io.contieneMessaggio("lanterna") && io.contieneMessaggio("spada"));
        assertTrue(io.contieneMessaggio("Ordinamento per peso"));
        assertTrue(io.contieneMessaggio("Ordinamento per nome"));
        assertTrue(io.contieneMessaggio("Raggruppamento per peso"));
    }

    @Test
    public void testEseguiGuardaBorsa_ParametroCaseInsensitive() {
        partita.getGiocatoreBorsa().addAttrezzo(new Attrezzo("chiave", 1));
        this.comandoGuarda.setParametro("BoRsA");
        this.comandoGuarda.esegui(partita);
        assertTrue(io.contieneMessaggio("chiave"));
        assertTrue(io.contieneMessaggio("[Contenuto Borsa]"));
    }
    
    /* TEST per get */
    @Test
    public void testGetNome() {
        assertEquals("guarda", comandoGuarda.getNome());
    }

    @Test
    public void testGetParametro() {
        assertNull(comandoGuarda.getParametro());
    }

    /* TEST per setParametro */
    @Test
    public void testSetParametro_Uguale() {
        comandoGuarda.setParametro("qualunque");
        assertEquals("qualunque", comandoGuarda.getParametro());
    }
    
}