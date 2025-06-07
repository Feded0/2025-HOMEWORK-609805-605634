package it.uniroma3.diadia;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.personaggi.*;

/**
 * Questa classe testa il caricatore del labirinto
 *
 * @author Feded0 (609805) e Civan04 (605634)
 * @see CaricatoreLabirinto
 * @version C
 */

class CaricatoreLabirintoTest {

    private final String labirintoComplesso = 
        "Stanze:atrio,biblioteca,laboratorio,N10,N11,magica,buia,bloccata\n" +
        "Magica:magica\n" +
        "Buia:buia lanterna\n" +
        "Bloccata:bloccata nord chiave\n" +
        "Inizio:atrio\n"+
        "Vincente:laboratorio\n" +
        "Mago:atrio Merlino Magic bacchetta\n"+
        "Cane:biblioteca Fido Bau!\"\n" +
        "Strega:laboratorio Morgana HAHA\n" +
        "Attrezzi:lanterna 2 atrio,chiave 1 bloccata,spada 5 N11,scudo 3 biblioteca\n" +
        "Uscite:atrio nord N10,atrio est biblioteca,biblioteca sud laboratorio," +
        "N10 ovest atrio,N10 sud N11,N11 nord N10,laboratorio nord biblioteca," +
        "bloccata nord magica,magica sud bloccata,buia est atrio\n";

    private CaricatoreLabirinto caricatore;

    @Before
    public void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
        caricatore = new CaricatoreLabirinto(new StringReader(labirintoComplesso));
        caricatore.carica();
    }

    @Test
    public void testStanzeSpecialiCaricateCorrettamente() {
        //magica dovrebbe essere StanzaMagica
        assertTrue(caricatore.getStanza("magica") instanceof StanzaMagica);
        //buia dovrebbe essere StanzaBuia
        assertTrue(caricatore.getStanza("buia") instanceof StanzaBuia);
        //bloccata dovrebbe essere StanzaBloccata
        assertTrue(caricatore.getStanza("bloccata") instanceof StanzaBloccata);
    }

    @Test
    public void testComportamentoStanzaBuia() {
        StanzaBuia buia = (StanzaBuia)caricatore.getStanza("buia");
        String descrizione = buia.getDescrizione();
        //La descrizione dovrebbe indicare che la stanza è buia
        assertTrue(descrizione.contains("buio") || descrizione.contains("buia"));
    }

    @Test
    public void testComportamentoStanzaBloccata() {
        StanzaBloccata bloccata = (StanzaBloccata)caricatore.getStanza("bloccata");
        //Verifica che la direzione bloccata non sia accessibile
        assertEquals(caricatore.getStanza("magica"), bloccata.getStanzaAdiacente(Direzione.nord));
    }

    @Test
    public void testPresenzaPersonaggi() {
        //Dovrebbe esserci un personaggio nell'atrio
        assertNotNull(caricatore.getStanza("atrio").getPersonaggio());
        //Dovrebbe esserci un personaggio nel laboratorio
        assertNotNull(caricatore.getStanza("laboratorio").getPersonaggio());
        //Dovrebbe esserci un personaggio nella biblioteca
        assertNotNull(caricatore.getStanza("biblioteca").getPersonaggio());
    }

    @Test
    public void testTipiPersonaggi() {
        //Personaggio in atrio dovrebbe essere Mago
        assertTrue(caricatore.getStanza("atrio").getPersonaggio() instanceof Mago);
        //Personaggio in laboratorio dovrebbe essere Strega
        assertTrue(caricatore.getStanza("laboratorio").getPersonaggio() instanceof Strega);
        //Personaggio in biblioteca dovrebbe essere Cane
        assertTrue(caricatore.getStanza("biblioteca").getPersonaggio() instanceof Cane);
    }

    @Test
    public void testAttrezziPosizionatiCorrettamente() {
        //Nell'atrio dovrebbe esserci una lanterna
        assertNotNull(caricatore.getStanza("atrio").getAttrezzo("lanterna"));
        //Nella bloccata dovrebbe esserci una chiave
        assertNotNull(caricatore.getStanza("bloccata").getAttrezzo("chiave"));
        //In N11 dovrebbe esserci una spada
        assertNotNull(caricatore.getStanza("N11").getAttrezzo("spada"));
        //Nella biblioteca dovrebbe esserci uno scudo
        assertNotNull(caricatore.getStanza("biblioteca").getAttrezzo("scudo"));
    }

    @Test
    public void testUsciteCorrette() {
        List<Direzione> direzioniAtrio = Arrays.asList(Direzione.nord, Direzione.est);
        //L'atrio dovrebbe avere uscite a nord e est
        assertTrue(caricatore.getStanza("atrio").getDirezioni().containsAll(direzioniAtrio));

        //L'uscita nord dell'atrio dovrebbe portare a N10
        assertEquals("N10", caricatore.getStanza("atrio").getStanzaAdiacente(Direzione.nord).getNome());
        //L'uscita est dell'atrio dovrebbe portare alla biblioteca
        assertEquals("biblioteca", caricatore.getStanza("atrio").getStanzaAdiacente(Direzione.est).getNome());
        //L'uscita sud della biblioteca dovrebbe portare al laboratorio
        assertEquals("laboratorio", caricatore.getStanza("biblioteca").getStanzaAdiacente(Direzione.sud).getNome());
    }

    @Test
    public void testStanzaInizialeEVincente() {
        //La stanza iniziale dovrebbe essere l'atrio
        assertEquals("atrio", caricatore.getStanzaIniziale().getNome());
        //La stanza vincente dovrebbe essere il laboratorio
        assertEquals("laboratorio", caricatore.getStanzaVincente().getNome());
    }
}
