# DiaDia
*Lo Studio di Caso a Supporto del Corso di Programmazione Orientata agli Oggetti*

## Descrizione del gioco
Ti trovi nell'Università, ma oggi è diversa dal solito... Meglio andare al più presto in **biblioteca** a studiare. Ma dov'è? I locali sono popolati da strani personaggi alcuni amici, altri... chissà! Ci sono **attrezzi** che potrebbero servirti nell'impresa: puoi raccoglierli, usarli, posarli quando ti sembrano inutili o regalarli se pensi che possano ingraziarti qualcuno.

## Comandi disponibili
| Comando      | Sintassi                 | Descrizione                                                                 |
|:------------:|:------------------------:|:---------------------------------------------------------------------------:|
| `Aiuto`      | `aiuto`                  | Mostra un messaggio di aiuto con la lista dei comandi disponibili         |
| `Fine`       | `fine`                   | Termina la partita ed esce dal gioco                                      |
| `Guarda`     | `guarda`<br>`guarda borsa`| Senza parametri mostra info della stanza;<br>con `borsa` mostra il contenuto ordinato della borsa |
| `Interagisci`| `interagisci`            | Interagisce con un personaggio nella stanza, se presente                  |
| `Posa`       | `posa <nome_oggetto>`    | Posa un oggetto nella stanza specificando il nome                         |
| `Prendi`     | `prendi <nome_oggetto>`  | Prende un oggetto dalla stanza specificando il nome                       |
| `Regala`     | `regala <nome_oggetto>`  | Regala un oggetto a un personaggio, se presente                           |
| `Saluta`     | `saluta`                 | Saluta un personaggio, se presente                                        |
| `Vai`        | `vai <direzione>`        | Seleziona una direzione da seguire                                        |

## Requisiti
* [Eclipse](https://www.eclipse.org/downloads/)
* [Java 8](https://www.oracle.com/java/technologies/downloads/#java8) o superiore

## Compilazione
Per una corretta compilazione del progetto con Eclipse, assicurarsi che nel file `.classpath` siano presenti:

- La cartella **Labirinti** inclusa nel percorso di build.
- La libreria di **JUnit 5** importata correttamente.

Per semplicità, si consiglia di utilizzare il file `.classpath` incluso nel codice sorgente, importando il progetto direttamente dal filesystem tramite **File > Open Projects from File System > Directory...**. In questo modo Eclipse configurerà automaticamente i percorsi necessari leggendoli dal `.classpath` fornito.

---

### Compatibilità Java
Prima di eseguire il programma, verifica che la versione di Java installata sul dispositivo sia compatibile con la versione di compilazione impostata in Eclipse. Puoi controllare la versione di Java da linea di comando con:

```bash
java -version
```
Se necessario è possibile modificare la versione di compilazione in **Project > Properties > Java Compiler** nella sezione `Compiler compliance level`

### Avviare da JAR
Per avviare il Runnable JAR eseguire da linea di comando:
```bash
java -jar DiaDia.jar
```

## Autori
* Docente di Programmazione Orientata agli Oggetti [Università Roma Tre](https://www.uniroma3.it/)
* [Feded0](https://github.com/Feded0) - *Matricola 609805*
* [Civan04](https://github.com/Civan04) - *Matricola 605634*

### Cronologia Versioni
* Versione C - *Pre Release*
    * Vedi [correzioni](https://github.com/Feded0/2025-HOMEWORK-609805-605634/releases/tag/versione.C)
* Versione B - *Pre Release*
    * Vedi [correzioni](https://github.com/Feded0/2025-HOMEWORK-609805-605634/releases/tag/versione.B)
* Versione A - *Pre Release*
    * Vedi [correzioni](https://github.com/Feded0/2025-HOMEWORK-609805-605634/releases/tag/versione.A)
* Versione base
    * release iniziale con errori - Vedi [errori](https://github.com/Feded0/2025-HOMEWORK-609805-605634/releases/tag/base)