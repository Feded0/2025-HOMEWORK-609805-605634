package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Questa classe testa tutti i metodi della classe ComandoPosa
 *
 * @author Feded0 (609805) e Civan04 (605634)
 * @see ComandoPosa
 * @version B
 */

class ComandoPosaTest {

    private ComandoPosa comandoPosa;
    private Partita partita;
    private IOSimulator io;
    private Stanza stanza;
    private Attrezzo attrezzoValido;

    @BeforeEach
    public void setUp() {
        comandoPosa = new ComandoPosa();
        partita = new Partita();
        io = new IOSimulator(new String[0]);
        comandoPosa.setIO(io);
        
        stanza = new Stanza("Stanza Test");
        partita.setStanzaCorrente(stanza);
        
        attrezzoValido = new Attrezzo("martello", 2);
        partita.getGiocatoreBorsa().addAttrezzo(attrezzoValido);
    }

    /* TEST per esegui */
    @Test
    public void testEsegui_AttrezzoValido() {
        comandoPosa.setParametro("martello");
        comandoPosa.esegui(partita);
        
        assertTrue(io.contieneMessaggio("martello aggiunto alla stanza"));
        assertFalse(partita.getGiocatoreBorsa().hasAttrezzo("martello"));
        assertTrue(partita.getStanzaCorrente().hasAttrezzo("martello"));
    }

    @Test
    public void testEsegui_AttrezzoNonPresenteInBorsa() {
        comandoPosa.setParametro("spada");
        comandoPosa.esegui(partita);
        
        assertTrue(io.contieneMessaggio("L'attrezzo non è stato trovato"));
        assertTrue(io.contieneMessaggio("Contenuto borsa"));
    }

    @Test
    public void testEsegui_ConBorsaVuota() {
        partita.getGiocatoreBorsa().removeAttrezzo(attrezzoValido);
        comandoPosa.setParametro("martello");
        comandoPosa.esegui(partita);
        
        assertTrue(io.contieneMessaggio("La borsa non ha attrezzi"));
    }

    @Test
    public void testEsegui_SenzaSpecificareAttrezzo() {
        comandoPosa.setParametro(null);
        comandoPosa.esegui(partita);
        
        assertTrue(io.contieneMessaggio("Nessun attrezzo inserito"));
    }

    @Test
    public void testEsegui_ConStanzaPiena() {
        // Riempie la stanza
        for(int i = 0; i < 10; i++) {
            partita.getStanzaCorrente().addAttrezzo(new Attrezzo("attrezzo"+i, 1));
        }
        
        comandoPosa.setParametro("martello");
        comandoPosa.esegui(partita);
        
        assertTrue(io.contieneMessaggio("Non c'è abbastanza spazio nella stanza"));
        assertTrue(partita.getGiocatoreBorsa().hasAttrezzo("martello"));
    }

    @Test
    public void testEsegui_AttrezzoConStessoNomeInStanza() {
        partita.getStanzaCorrente().addAttrezzo(new Attrezzo("martello", 3));
        comandoPosa.setParametro("martello");
        comandoPosa.esegui(partita);
        
        assertTrue(io.contieneMessaggio("martello aggiunto alla stanza"));
        assertEquals("martello", partita.getStanzaCorrente().getAttrezzi()[0].getNome());
        assertEquals("martello", partita.getStanzaCorrente().getAttrezzi()[1].getNome());
    }

    @Test
    public void testEsegui_AttrezzoPesante() {
        Attrezzo attrezzoPesante = new Attrezzo("incudine", 15);
        partita.getGiocatoreBorsa().removeAttrezzo(attrezzoValido);
        partita.getGiocatoreBorsa().addAttrezzo(attrezzoPesante);
        
        comandoPosa.setParametro("incudine");
        comandoPosa.esegui(partita);
        
        assertFalse(io.contieneMessaggio("incudine aggiunto alla stanza"));
    }

    @Test
    public void testEsegui_MultipliAttrezzi() {
        partita.getGiocatoreBorsa().addAttrezzo(new Attrezzo("chiodo", 1));
        
        comandoPosa.setParametro("martello");
        comandoPosa.esegui(partita);
        
        comandoPosa.setParametro("chiodo");
        comandoPosa.esegui(partita);
        
        assertTrue(partita.getStanzaCorrente().hasAttrezzo("martello"));
        assertTrue(partita.getStanzaCorrente().hasAttrezzo("chiodo"));
        assertEquals("martello", partita.getStanzaCorrente().getAttrezzi()[0].getNome());
        assertEquals("chiodo", partita.getStanzaCorrente().getAttrezzi()[1].getNome());
    }
    
    /* TEST per get */
    @Test
    public void testGetNome() {
        assertEquals("posa", comandoPosa.getNome());
    }

    /* TEST per setParametro */
    @Test
    public void testSetParametro() {
        comandoPosa.setParametro("lanterna");
        assertEquals("lanterna", comandoPosa.getParametro());
    }
    
}