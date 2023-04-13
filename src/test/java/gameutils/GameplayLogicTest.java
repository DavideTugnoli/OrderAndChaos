package gameutils;

import consoleui.BoardPrinter;
import entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameplayLogicTest {
    private Board board;
    private Player player1;
    private Player player2;
    private GameEventListener gameEventListener;
    private GameplayLogic gameplayLogic;
    private GameplayLogic gameplayLogicWithComputer;

    @BeforeEach
    public void setUp() {
        board = new Board(6);
    }

    @Test
    public void testGetCurrentPlayer() {
        player1 = new HumanPlayer("Player 1", PlayerRole.ORDER);
        player2 = new HumanPlayer("Player 2", PlayerRole.CHAOS);
        gameEventListener = new GameEventListenerStub();
        gameplayLogic = new GameplayLogic(board, player1, player2, gameEventListener);
        Player currentPlayer = gameplayLogic.getCurrentPlayer();
        assertEquals(player1, currentPlayer);
    }

    @Test
    public void testPlayTurn() {
        player1 = new HumanPlayer("Player 1", PlayerRole.ORDER);
        player2 = new HumanPlayer("Player 2", PlayerRole.CHAOS);
        gameEventListener = new GameEventListenerStub();
        gameplayLogic = new GameplayLogic(board, player1, player2, gameEventListener);
        Cell cell = new Cell(0, 0, CellState.X);
        gameplayLogic.playTurn(cell);

        assertFalse(board.isCellEmpty(0, 0));
    }

    @Test
    public void testDetermineFirstPlayer() {
        player1 = new HumanPlayer("Player 1", PlayerRole.ORDER);
        player2 = new HumanPlayer("Player 2", PlayerRole.CHAOS);
        gameEventListener = new GameEventListenerStub();
        gameplayLogic = new GameplayLogic(board, player1, player2, gameEventListener);
        assertEquals(player1, gameplayLogic.getCurrentPlayer());
    }

    @Test
    public void testNextPlayer() {
        player1 = new HumanPlayer("Player 1", PlayerRole.ORDER);
        player2 = new HumanPlayer("Player 2", PlayerRole.CHAOS);
        gameEventListener = new GameEventListenerStub();
        gameplayLogic = new GameplayLogic(board, player1, player2, gameEventListener);
        Cell cell = new Cell(0, 0);
        gameplayLogic.playTurn(cell); // Dopo questo turno, il giocatore corrente dovrebbe cambiare

        assertEquals(player2, gameplayLogic.getCurrentPlayer());
    }

    @Test
    public void testGetCurrentPlayerName() {
        player1 = new HumanPlayer("Player 1", PlayerRole.ORDER);
        player2 = new HumanPlayer("Player 2", PlayerRole.CHAOS);
        gameEventListener = new GameEventListenerStub();
        gameplayLogic = new GameplayLogic(board, player1, player2, gameEventListener);
        String currentPlayerName = gameplayLogic.getCurrentPlayerName();
        assertEquals("Player 1", currentPlayerName);
    }

    @Test
    public void testIsGameOver() {
        player1 = new HumanPlayer("Player 1", PlayerRole.ORDER);
        player2 = new HumanPlayer("Player 2", PlayerRole.CHAOS);
        gameEventListener = new GameEventListenerStub();
        gameplayLogic = new GameplayLogic(board, player1, player2, gameEventListener);
        assertFalse(gameplayLogic.isGameOver());
    }

    @Test
    public void testGetBoard() {
        player1 = new HumanPlayer("Player 1", PlayerRole.ORDER);
        player2 = new HumanPlayer("Player 2", PlayerRole.CHAOS);
        gameEventListener = new GameEventListenerStub();
        gameplayLogic = new GameplayLogic(board, player1, player2, gameEventListener);
        Board currentBoard = gameplayLogic.getBoard();
        assertEquals(board, currentBoard);
    }

    @Test
    public void testGetPlayer1() {
        player1 = new HumanPlayer("Player 1", PlayerRole.ORDER);
        player2 = new HumanPlayer("Player 2", PlayerRole.CHAOS);
        gameEventListener = new GameEventListenerStub();
        gameplayLogic = new GameplayLogic(board, player1, player2, gameEventListener);
        Player player = gameplayLogic.getPlayer1();
        assertEquals(player1, player);
    }

    @Test
    public void testGetPlayer2() {
        player1 = new HumanPlayer("Player 1", PlayerRole.ORDER);
        player2 = new HumanPlayer("Player 2", PlayerRole.CHAOS);
        gameEventListener = new GameEventListenerStub();
        gameplayLogic = new GameplayLogic(board, player1, player2, gameEventListener);
        Player player = gameplayLogic.getPlayer2();
        assertEquals(player2, player);
    }

    @Test
    public void testGetWinner() {
        player1 = new HumanPlayer("Player 1", PlayerRole.ORDER);
        player2 = new HumanPlayer("Player 2", PlayerRole.CHAOS);
        gameEventListener = new GameEventListenerStub();
        gameplayLogic = new GameplayLogic(board, player1, player2, gameEventListener);
        assertNull(gameplayLogic.getWinner());
    }

    @Test
    public void testIsComputerPlayer() {
        player1 = new HumanPlayer("Player 1", PlayerRole.ORDER);
        gameEventListener = new GameEventListenerStub();
        gameplayLogic = new GameplayLogic(board, player1, player2, gameEventListener);

        player2 = new ComputerPlayer("Computer", PlayerRole.CHAOS);
        gameplayLogicWithComputer = new GameplayLogic(board, player1, player2, gameEventListener);
        assertFalse(gameplayLogic.isComputerPlayer());
    }

    @Test
    public void testPlayTurnWithComputerPlayer() {
        player1 = new HumanPlayer("Player 1", PlayerRole.ORDER);
        gameEventListener = new GameEventListenerStub();
        gameplayLogic = new GameplayLogic(board, player1, player2, gameEventListener);

        player2 = new ComputerPlayer("Computer", PlayerRole.CHAOS);
        gameplayLogicWithComputer = new GameplayLogic(board, player1, player2, gameEventListener);
        Cell cell = new Cell(0, 0, CellState.X);
        gameplayLogicWithComputer.playTurn(cell);

        assertFalse(board.isCellEmpty(0, 0));
        // Controlla che il ComputerPlayer abbia effettivamente giocato un turno
        assertTrue(hasComputerPlayedTurn(board));
    }

    @Test
    public void testNextPlayerWithComputerPlayer() {
        player1 = new HumanPlayer("Player 1", PlayerRole.ORDER);
        gameEventListener = new GameEventListenerStub();
        gameplayLogic = new GameplayLogic(board, player1, player2, gameEventListener);

        player2 = new ComputerPlayer("Computer", PlayerRole.CHAOS);
        gameplayLogicWithComputer = new GameplayLogic(board, player1, player2, gameEventListener);
        Cell cell = new Cell(0, 0, CellState.X);
        gameplayLogicWithComputer.playTurn(cell); // Dopo questo turno, il giocatore corrente dovrebbe cambiare

        // Se il ComputerPlayer ha effettivamente giocato un turno, il giocatore corrente dovrebbe essere nuovamente il player1
        assertEquals(player1, gameplayLogicWithComputer.getCurrentPlayer());
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
    public void testPlayTurnInvalidMove() {
        player1 = new HumanPlayer("Player 1", PlayerRole.ORDER);
        player2 = new HumanPlayer("Player 2", PlayerRole.CHAOS);
        gameEventListener = new GameEventListenerStub();
        gameplayLogic = new GameplayLogic(board, player1, player2, gameEventListener);
        Cell cell = new Cell(0, 0, CellState.X);
        gameplayLogic.playTurn(cell);
        gameplayLogic.playTurn(cell); // Dovrebbe ignorare questa mossa perché è stata già occupata la cella (0, 0)

        assertEquals(player2.getName(), gameplayLogic.getCurrentPlayer().getName()); // Il giocatore corrente non dovrebbe cambiare
    }

    @Test
    public void testGameOverWithOrderWinner() {
        player1 = new HumanPlayer("Player 1", PlayerRole.ORDER);
        player2 = new HumanPlayer("Player 2", PlayerRole.CHAOS);
        gameEventListener = new GameEventListenerStub();
        gameplayLogic = new GameplayLogic(board, player1, player2, gameEventListener);
        fillBoardWithOrderWinner(gameplayLogic);

        assertTrue(gameplayLogic.isGameOver());
        assertEquals(player1, gameplayLogic.getWinner());
    }

    @Test
    public void testGameOverWithChaosWinner() {
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
            gameplayLogic.playTurn(cell);
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
                gameplayLogic.playTurn(cell);
            }
        }
        BoardPrinter printer = new BoardPrinter();
        printer.printBoard(gameplayLogic.getBoard());
    }


    @Test
    public void testPlayTurnWithGameAlreadyOver() {
        player1 = new HumanPlayer("Player 1", PlayerRole.ORDER);
        player2 = new HumanPlayer("Player 2", PlayerRole.CHAOS);
        gameEventListener = new GameEventListenerStub();
        gameplayLogic = new GameplayLogic(board, player1, player2, gameEventListener);

        Cell[] cells = new Cell[5];
        for (int i = 0; i < 5; i++) {
            cells[i] = new Cell(0, i, CellState.X);
            gameplayLogic.playTurn(cells[i]);
        }

        Cell cell = new Cell(1, 0, CellState.O);
        gameplayLogic.playTurn(cell); // La mossa dovrebbe essere ignorata perché il gioco è già finito

        assertTrue(gameplayLogic.isGameOver());
        assertEquals(player1, gameplayLogic.getWinner());
        assertTrue(board.isCellEmpty(1, 0));
    }


}
