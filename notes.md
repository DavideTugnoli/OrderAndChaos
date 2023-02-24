# Test Ideas

## Test classe Cell:
- [x] Test 1: testNewCellIsEmpty: verifica che una cella appena creata sia vuota.
- [x] Test 2: testSetState: verifica che lo stato di una cella possa essere impostato correttamente.
- [x] Test 3: testSetStateInvalidValue: verifica che non sia possibile impostare uno stato non valido per una cella.
- [x] Test 4: testToString: verifica che il metodo toString() restituisca una stringa corretta per la cella.

## Test classe Board:
- [X] Test 1: verifica che una cella venga restituita correttamente.
- [x] Test 2: verifica che tutte le celle della tavola siano vuote quando la tavola viene inizializzata.
- [X] Test 3: verifica che una mossa valida venga aggiunta correttamente alla tavola.
- [x] Test 4: verifica che l'eccezione InvalidMoveException sia lanciata quando si cerca di fare una mossa in una cella già occupata.
- [x] Test 5: verifica che una mossa in una posizione fuori dalla tavola (ad es. con riga o colonna negative o maggiori del limite della tavola) lanci un'eccezione InvalidMoveException.
- [X] Test 6: verifica che dopo aver fatto una mossa nella cella (0,0), la stessa cella non è più vuota.
- [X] Test 7: verifica che la dimensione della tavola sia effettivamente quella specificata nel costruttore.
- [X] Test 8: verificare che una board senza parametri sia una 6x6 di default.
