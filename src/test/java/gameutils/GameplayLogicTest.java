package gameutils;

import consoleui.BoardPrinter;
import entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.*;

class GameplayLogicTest {
    private Board board;
    private Player player1;
    private Player player2;
    private GameEventListener gameEventListener;
    private GameplayLogic gameplayLogic;
    private GameplayLogic gameplayLogicWithComputer;

    @BeforeEach
    void setUp() {
        board = new Board(6);
    }

    @Test
    void testGetCurrentPlayer() {
        player1 = new HumanPlayer("Player 1", PlayerRole.ORDER);
        player2 = new HumanPlayer("Player 2", PlayerRole.CHAOS);
        gameEventListener = new GameEventListenerStub();
        gameplayLogic = new GameplayLogic(board, player1, player2, gameEventListener);
        Player currentPlayer = gameplayLogic.getCurrentPlayer();
        assertEquals(player1, currentPlayer);
    }

    @Test
    void testPlayTurn() {
        player1 = new HumanPlayer("Player 1", PlayerRole.ORDER);
        player2 = new HumanPlayer("Player 2", PlayerRole.CHAOS);
        gameEventListener = new GameEventListenerStub();
        gameplayLogic = new GameplayLogic(board, player1, player2, gameEventListener);
        Cell cell = new Cell(0, 0, CellState.X);
        gameplayLogic.handleTurn(cell);

        assertFalse(board.isCellEmpty(0, 0));
    }

    @Test
    void testDetermineFirstPlayer() {
        player1 = new HumanPlayer("Player 1", PlayerRole.ORDER);
        player2 = new HumanPlayer("Player 2", PlayerRole.CHAOS);
        gameEventListener = new GameEventListenerStub();
        gameplayLogic = new GameplayLogic(board, player1, player2, gameEventListener);
        assertEquals(player1, gameplayLogic.getCurrentPlayer());
    }

    @Test
    void testNextPlayer() {
        player1 = new HumanPlayer("Player 1", PlayerRole.ORDER);
        player2 = new HumanPlayer("Player 2", PlayerRole.CHAOS);
        gameEventListener = new GameEventListenerStub();
        gameplayLogic = new GameplayLogic(board, player1, player2, gameEventListener);
        Cell cell = new Cell(0, 0);
        gameplayLogic.handleTurn(cell); // Dopo questo turno, il giocatore corrente dovrebbe cambiare

        assertEquals(player2, gameplayLogic.getCurrentPlayer());
    }

    @Test
    void testGetCurrentPlayerName() {
        player1 = new HumanPlayer("Player 1", PlayerRole.ORDER);
        player2 = new HumanPlayer("Player 2", PlayerRole.CHAOS);
        gameEventListener = new GameEventListenerStub();
        gameplayLogic = new GameplayLogic(board, player1, player2, gameEventListener);
        String currentPlayerName = gameplayLogic.getCurrentPlayerName();
        assertEquals("Player 1", currentPlayerName);
    }

    @Test
    void testIsGameOver() {
        player1 = new HumanPlayer("Player 1", PlayerRole.ORDER);
        player2 = new HumanPlayer("Player 2", PlayerRole.CHAOS);
        gameEventListener = new GameEventListenerStub();
        gameplayLogic = new GameplayLogic(board, player1, player2, gameEventListener);
        assertFalse(gameplayLogic.isGameOver());
    }

    @Test
    void testGetBoard() {
        player1 = new HumanPlayer("Player 1", PlayerRole.ORDER);
        player2 = new HumanPlayer("Player 2", PlayerRole.CHAOS);
        gameEventListener = new GameEventListenerStub();
        gameplayLogic = new GameplayLogic(board, player1, player2, gameEventListener);
        Board currentBoard = gameplayLogic.getBoard();
        assertEquals(board, currentBoard);
    }

    @Test
    void testGetPlayer1() {
        player1 = new HumanPlayer("Player 1", PlayerRole.ORDER);
        player2 = new HumanPlayer("Player 2", PlayerRole.CHAOS);
        gameEventListener = new GameEventListenerStub();
        gameplayLogic = new GameplayLogic(board, player1, player2, gameEventListener);
        Player player = gameplayLogic.getPlayer1();
        assertEquals(player1, player);
    }

    @Test
    void testGetPlayer2() {
        player1 = new HumanPlayer("Player 1", PlayerRole.ORDER);
        player2 = new HumanPlayer("Player 2", PlayerRole.CHAOS);
        gameEventListener = new GameEventListenerStub();
        gameplayLogic = new GameplayLogic(board, player1, player2, gameEventListener);
        Player player = gameplayLogic.getPlayer2();
        assertEquals(player2, player);
    }

    @Test
    void testGetWinner() {
        player1 = new HumanPlayer("Player 1", PlayerRole.ORDER);
        player2 = new HumanPlayer("Player 2", PlayerRole.CHAOS);
        gameEventListener = new GameEventListenerStub();
        gameplayLogic = new GameplayLogic(board, player1, player2, gameEventListener);
        assertNull(gameplayLogic.getWinner());
    }

    @Test
    void testIsComputerPlayer() {
        player1 = new HumanPlayer("Player 1", PlayerRole.ORDER);
        gameEventListener = new GameEventListenerStub();
        gameplayLogic = new GameplayLogic(board, player1, player2, gameEventListener);

        player2 = new ComputerPlayer("Computer", PlayerRole.CHAOS);
        gameplayLogicWithComputer = new GameplayLogic(board, player1, player2, gameEventListener);
        assertFalse(gameplayLogic.isComputerPlayer());
    }


    @Test
    void testPlayTurnWithComputerPlayer() {
        player1 = new HumanPlayer("Player 1", PlayerRole.ORDER);
        gameEventListener = new GameEventListenerStub();
        gameplayLogic = new GameplayLogic(board, player1, player2, gameEventListener);

        player2 = new ComputerPlayer("Computer", PlayerRole.CHAOS);
        gameplayLogicWithComputer = new GameplayLogic(board, player1, player2, gameEventListener);
        Cell cell = new Cell(0, 0, CellState.X);
        gameplayLogicWithComputer.handleTurn(cell);

        assertFalse(board.isCellEmpty(0, 0));

        // Attendi fino a 2 secondi affinché il computer abbia effettivamente giocato un turno
        await().atMost(2, TimeUnit.SECONDS).untilAsserted(() -> assertTrue(hasComputerPlayedTurn(board)));
    }

    @Test
    void testNextPlayerWithComputerPlayer() {
        player1 = new HumanPlayer("Player 1", PlayerRole.ORDER);
        gameEventListener = new GameEventListenerStub();
        gameplayLogic = new GameplayLogic(board, player1, player2, gameEventListener);

        player2 = new ComputerPlayer("Computer", PlayerRole.CHAOS);
        gameplayLogicWithComputer = new GameplayLogic(board, player1, player2, gameEventListener);
        Cell cell = new Cell(0, 0, CellState.X);
        gameplayLogicWithComputer.handleTurn(cell); // Dopo questo turno, il giocatore corrente dovrebbe cambiare

        await().atMost(2, TimeUnit.SECONDS).untilAsserted(() ->
                // Se il ComputerPlayer ha effettivamente giocato un turno, il giocatore corrente dovrebbe essere nuovamente il player1
                assertEquals(player1, gameplayLogicWithComputer.getCurrentPlayer())
        );
    }

    private boolean hasComputerPlayedTurn(Board board) {
        int occupiedCells = 0;
        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                if (board.getCellState(row, col) != CellState.EMPTY) {
                    occupiedCells++;
                }
            }
        }
        // Controlla se ci sono almeno due celle occupate (una dall'utente e una dal ComputerPlayer)
        return occupiedCells >= 2;
    }

    @Test
    void testPlayTurnInvalidMove() {
        player1 = new HumanPlayer("Player 1", PlayerRole.ORDER);
        player2 = new HumanPlayer("Player 2", PlayerRole.CHAOS);
        gameEventListener = new GameEventListenerStub();
        gameplayLogic = new GameplayLogic(board, player1, player2, gameEventListener);
        Cell cell = new Cell(0, 0, CellState.X);
        gameplayLogic.handleTurn(cell);
        gameplayLogic.handleTurn(cell); // Dovrebbe ignorare questa mossa perché è stata già occupata la cella (0, 0)

        assertEquals(player2.getName(), gameplayLogic.getCurrentPlayer().getName()); // Il giocatore corrente non dovrebbe cambiare
    }

    @Test
    void testGameOverWithOrderWinner() {
        player1 = new HumanPlayer("Player 1", PlayerRole.ORDER);
        player2 = new HumanPlayer("Player 2", PlayerRole.CHAOS);
        gameEventListener = new GameEventListenerStub();
        gameplayLogic = new GameplayLogic(board, player1, player2, gameEventListener);
        fillBoardWithOrderWinner(gameplayLogic);

        assertTrue(gameplayLogic.isGameOver());
        assertEquals(player1, gameplayLogic.getWinner());
    }

    @Test
    void testGameOverWithChaosWinner() {
        player1 = new HumanPlayer("Player 1", PlayerRole.ORDER);
        player2 = new HumanPlayer("Player 2", PlayerRole.CHAOS);
        gameEventListener = new GameEventListenerStub();
        gameplayLogic = new GameplayLogic(board, player1, player2, gameEventListener);
        fillBoardWithChaosWinner(gameplayLogic);


        assertTrue(gameplayLogic.isGameOver());
        System.out.println(gameplayLogic.getWinner().getName());
        assertEquals(gameplayLogic.getPlayer2(), gameplayLogic.getWinner());
    }

    private void fillBoardWithOrderWinner(GameplayLogic gameplayLogic) {

        // fill board of 5 O in a row
        for (int i = 0; i < 5; i++) {
            Cell cell = new Cell(0, i, CellState.O);
            gameplayLogic.handleTurn(cell);
        }

    }

    private void fillBoardWithChaosWinner(GameplayLogic gameplayLogic) {
        int[][] pattern = {
                {0, 0, 1, 0, 1, 0},
                {1, 1, 0, 1, 0, 1},
                {0, 1, 1, 1, 1, 0},
                {1, 0, 1, 0, 0, 0},
                {1, 1, 0, 0, 1, 0},
                {1, 0, 1, 0, 1, 1}
        };

        for (int row = 0; row < pattern.length; row++) {
            for (int col = 0; col < pattern[row].length; col++) {
                CellState cellState = (pattern[row][col] == 0) ? CellState.X : CellState.O;
                Cell cell = new Cell(row, col, cellState);
                gameplayLogic.handleTurn(cell);
            }
        }
        BoardPrinter printer = new BoardPrinter();
        printer.printBoard(gameplayLogic.getBoard());
    }


    @Test
    void testPlayTurnWithGameAlreadyOver() {
        player1 = new HumanPlayer("Player 1", PlayerRole.ORDER);
        player2 = new HumanPlayer("Player 2", PlayerRole.CHAOS);
        gameEventListener = new GameEventListenerStub();
        gameplayLogic = new GameplayLogic(board, player1, player2, gameEventListener);

        Cell[] cells = new Cell[5];
        for (int i = 0; i < 5; i++) {
            cells[i] = new Cell(0, i, CellState.X);
            gameplayLogic.handleTurn(cells[i]);
        }

        Cell cell = new Cell(1, 0, CellState.O);
        gameplayLogic.handleTurn(cell); // La mossa dovrebbe essere ignorata perché il gioco è già finito

        assertTrue(gameplayLogic.isGameOver());
        assertEquals(player1, gameplayLogic.getWinner());
        assertTrue(board.isCellEmpty(1, 0));
    }

    @Test
    void testIsSinglePlayer() {
        player1 = new HumanPlayer("Player 1", PlayerRole.ORDER);
        player2 = new HumanPlayer("Player 2", PlayerRole.CHAOS);
        gameEventListener = new GameEventListenerStub();
        gameplayLogic = new GameplayLogic(board, player1, player2, gameEventListener);
        assertFalse(gameplayLogic.isSinglePlayer());
    }

    @Test
    void testIsSinglePlayerWithComputerPlayer() {
        player1 = new HumanPlayer("Player 1", PlayerRole.ORDER);
        player2 = new ComputerPlayer("Computer", PlayerRole.CHAOS);
        gameEventListener = new GameEventListenerStub();
        gameplayLogic = new GameplayLogic(board, player1, player2, gameEventListener);
        assertTrue(gameplayLogic.isSinglePlayer());
    }

}
