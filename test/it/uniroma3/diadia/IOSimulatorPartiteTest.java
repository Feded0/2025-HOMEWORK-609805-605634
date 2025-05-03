package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Questa classe testa diverse partite simulando un giocatore reale
 *
 * @author Feded0 (609805) e Civan04 (605634)
 * @see IOSimulator
 * @version B
 */

class IOSimulatorPartiteTest { 

    @Test
    public void testPercorsoVittoriaDiretto() {
        String[] comandi = {"vai sud", "prendi lanterna", "vai nord", "vai ovest", 
        					"posa lanterna", "prendi chiave", "vai est",
        					"posa chiave", "vai nord"};
        
        IOSimulator io = new IOSimulator(comandi);
        DiaDia gioco = new DiaDia(io);
        gioco.gioca();
        
        assertTrue(io.contieneMessaggio("Hai vinto"));
    }

    @Test
    public void testSbloccoStanzaBloccata() {
        String[] comandi = {"vai ovest", "prendi chiave", "vai est", "posa chiave", 
        					"guarda", "vai nord"};
        
        IOSimulator io = new IOSimulator(comandi);
        DiaDia gioco = new DiaDia(io);
        gioco.gioca();
        
        assertTrue(io.contieneMessaggio("Direzione nord sbloccata con chiave"));
        assertTrue(io.contieneMessaggio("Biblioteca"));
    }

    @Test 
    public void testNavigazioneStanzaBuia() {
        String[] comandi = {"vai sud", "prendi lanterna", "vai ovest", "guarda", "fine"};
        
        IOSimulator io = new IOSimulator(comandi);
        DiaDia gioco = new DiaDia(io);
        gioco.gioca();
        
        assertTrue(io.contieneMessaggio("Qui c'è buio pesto"));
        assertTrue(io.contieneMessaggio("Laboratorio Campus"));
    }

    @Test
    public void testSenzaLanterna() {
        String[] comandi = {"vai ovest", "guarda", "fine"};
        
        IOSimulator io = new IOSimulator(comandi);
        DiaDia gioco = new DiaDia(io);
        gioco.gioca();
        
        assertTrue(io.contieneMessaggio("Qui c'è buio pesto"));
    }

    @Test
    public void testRaccoltaTuttiAttrezzi() {
        String[] comandi = {"prendi osso", "vai sud", "prendi lanterna", 
                           "vai ovest", "prendi chiave", "fine"};
        
        IOSimulator io = new IOSimulator(comandi);
        DiaDia gioco = new DiaDia(io);
        gioco.gioca();
        
        assertTrue(io.contieneMessaggio("osso aggiunto all'inventario"));
        assertTrue(io.contieneMessaggio("lanterna aggiunto all'inventario"));
        assertTrue(io.contieneMessaggio("chiave aggiunto all'inventario"));
    }

    @Test
    public void testMovimentiNonValidi() {
        String[] comandi = {"vai nord", "guarda","vai sud", "vai nordest", "fine"};
        
        IOSimulator io = new IOSimulator(comandi);
        DiaDia gioco = new DiaDia(io);
        gioco.gioca();
        
        assertTrue(io.contieneMessaggio("La direzione nord è attualmente bloccata"));
        assertTrue(io.contieneMessaggio("Direzione inesistente"));
    }

    @Test
    public void testEsaurimentoCfu() {
        String[] comandi = new String[22];
        for(int i=0; i<21; i++) {
            comandi[i] = "vai est"; // Movimenti che consumano CFU
        }
        comandi[21] = "fine";
        
        IOSimulator io = new IOSimulator(comandi);
        DiaDia gioco = new DiaDia(io);
        gioco.gioca();
        
        assertTrue(io.contieneMessaggio("Hai esaurito i CFU"));
    }

    @Test
    public void testCompletoConAiuto() {
        String[] comandi = {"aiuto", "vai sud", "prendi lanterna", 
                           "guarda", "vai ovest", "fine"};
        
        IOSimulator io = new IOSimulator(comandi);
        DiaDia gioco = new DiaDia(io);
        gioco.gioca();
        
        assertTrue(io.contieneMessaggio("vai"));
        assertTrue(io.contieneMessaggio("prendi"));
        assertTrue(io.contieneMessaggio("Laboratorio Campus"));
    }

}