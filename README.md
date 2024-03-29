# Order And Chaos
Final Project SOFTWARE DEVELOPMENT METHODS

Order And Chaos è un gioco da tavolo strategico, ispirato al Tris, in cui due giocatori, Order e Chaos, si sfidano su una griglia 6x6. Il gioco può essere giocato sia in italiano che in inglese, con un'interfaccia a console o grafica, e in modalità single-player o multiplayer.

## Obiettivo del gioco

- **Obiettivo di Order**: Posizionare 5 pezzi uguali (X o O) in fila, colonna o diagonale.
- **Obiettivo di Chaos**: Riempire l'intera griglia senza permettere ad Order di realizzare una sequenza di 5 pezzi uguali.

## Caratteristiche

- Interfacce console e grafica disponibili
- Gioca in italiano o inglese
- Scegli tra modalità single-player e multiplayer

## Come giocare

1. Clona la repository.
2. Fai il Build del progetto:
   - Su un sistema **Unix** (come Linux o Mac), esegui il comando `./build.sh` da una shell.
   - Su un sistema **Windows**, esegui `build.bat` da un prompt dei comandi.
3. Questo creerà un file JAR chiamato `OrderAndChaos.jar` nella cartella `build/libs` del tuo progetto.
4. Esegui il file JAR con il comando `java -jar build/libs/OrderAndChaos.jar`, passando i parametri appropriati come mostrato negli esempi qui sotto.

### Esempi di comandi

#### Inglese, interfaccia grafica, single-player:

\```
java -jar OrderAndChaos.jar --english --graphic --single-player
\```

#### Italiano, interfaccia console, multiplayer:

\```
java -jar OrderAndChaos.jar --italian --console --multi-player
\```

## Regole

1. I giocatori si alternano nel posizionare un pezzo X o O sulla griglia di gioco.
2. La partita termina quando Order posiziona 5 pezzi uguali in fila, colonna o diagonale, oppure quando Chaos riempie la griglia di gioco senza che Order realizzi questa configurazione.

Buon divertimento!
