package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;

/**
 * Questa classe testa diverse partite simulando un giocatore reale
 *
 * @author Feded0 (609805) e Civan04 (605634)
 * @see IOSimulator
 * @version C
 */

class IOSimulatorPartiteTest { 
	private Labirinto labirinto;
	
	@BeforeEach
	public void setup() throws FileNotFoundException, FormatoFileNonValidoException {
		labirinto = Labirinto.newBuilder("LabirintoPerTest.txt").getLabirinto();
	}
	
    @Test
    public void testPercorsoVittoriaDiretto() throws Exception {
    	List<String> listaComandi = Arrays.asList(
    	        "vai sud", 
    	        "prendi lanterna", 
    	        "vai nord", 
    	        "vai ovest", 
    	        "posa lanterna", 
    	        "prendi chiave", 
    	        "vai est", 
    	        "posa chiave", 
    	        "vai nord"
    	    );
        
        IOSimulator io = new IOSimulator(listaComandi);
        DiaDia gioco = new DiaDia(labirinto, io);
        gioco.gioca();
        
        assertTrue(io.contieneMessaggio("Hai vinto!"));
    }

    @Test
    public void testSbloccoStanzaBloccata() throws Exception {
    	List<String> listaComandi = Arrays.asList(
    			"vai ovest", 
    			"prendi chiave", 
    			"vai est", 
    			"posa chiave", 		
    			"guarda", 
    			"vai nord"
    		);
        
        IOSimulator io = new IOSimulator(listaComandi);
        DiaDia gioco = new DiaDia(labirinto, io);
        gioco.gioca();
        
        assertTrue(io.contieneMessaggio("Direzione nord sbloccata con chiave"));
        assertTrue(io.contieneMessaggio("Biblioteca"));
        
       
    }

    @Test 
    public void testNavigazioneStanzaBuia() throws Exception {
    	List<String> listaComandi = Arrays.asList(
    			"vai sud", 
    			"prendi lanterna",
    			"vai ovest",
    			"guarda",
    			"fine"
    		);
        
        IOSimulator io = new IOSimulator(listaComandi);
        DiaDia gioco = new DiaDia(labirinto, io);
        gioco.gioca();
        
        assertTrue(io.contieneMessaggio("Qui c'è buio pesto"));
        assertTrue(io.contieneMessaggio("LaboratorioCampus"));
    }

    @Test
    public void testSenzaLanterna() throws Exception {
    	List<String> listaComandi = Arrays.asList(
    			"vai ovest",
    			"guarda",
    			"fine"
    		);
        
        IOSimulator io = new IOSimulator(listaComandi);
        DiaDia gioco = new DiaDia(labirinto, io);
        gioco.gioca();
        
        assertTrue(io.contieneMessaggio("Qui c'è buio pesto"));
    }

    @Test
    public void testRaccoltaTuttiAttrezzi() throws Exception {
    	List<String> listaComandi = Arrays.asList(
    			"prendi osso",
    			"vai sud",
    			"prendi lanterna", 
                "vai ovest",
                "prendi chiave",
                "fine"
            );
        
        IOSimulator io = new IOSimulator(listaComandi);
        DiaDia gioco = new DiaDia(labirinto, io);
        gioco.gioca();
        
        assertTrue(io.contieneMessaggio("osso aggiunto all'inventario"));
        assertTrue(io.contieneMessaggio("lanterna aggiunto all'inventario"));
        assertTrue(io.contieneMessaggio("chiave aggiunto all'inventario"));
    }

    @Test
    public void testMovimentiNonValidi() throws Exception {
    	List<String> listaComandi = Arrays.asList(
    			"vai nord",
    			"guarda",
    			"vai sud",
    			"vai nordest",
    			"fine"
    		);
        
        IOSimulator io = new IOSimulator(listaComandi);
        DiaDia gioco = new DiaDia(labirinto, io);
        gioco.gioca();
        
        assertTrue(io.contieneMessaggio("La direzione nord è attualmente bloccata"));
        assertTrue(io.contieneMessaggio("Direzione inesistente"));
    }

    @Test
    public void testEsaurimentoCfu() throws Exception {
    	List<String> listaComandi = new ArrayList<>();
    	for (int i = 0; i < 21; i++) {
    	    listaComandi.add("vai est");
    	}
    	listaComandi.add("fine");
        
        IOSimulator io = new IOSimulator(listaComandi);
        DiaDia gioco = new DiaDia(labirinto, io);
        gioco.gioca();
        
        assertTrue(io.contieneMessaggio("Hai esaurito i CFU"));
    }

    @Test
    public void testCompletoConAiuto() throws Exception {
    	List<String> listaComandi = Arrays.asList(
    			"aiuto",
    			"vai sud",
    			"prendi lanterna", 
                "guarda",
                "vai ovest",
                "fine"
             );
        
        IOSimulator io = new IOSimulator(listaComandi);
        DiaDia gioco = new DiaDia(labirinto, io);
        gioco.gioca();
        
        assertTrue(io.contieneMessaggio("Vai"));
        assertTrue(io.contieneMessaggio("Prendi"));
        assertTrue(io.contieneMessaggio("LaboratorioCampus"));
    }

}