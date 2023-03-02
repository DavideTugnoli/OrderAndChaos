package gameutils;

import entities.Board;
import entities.CellState;
import entities.Player;
import entities.PlayerRole;
import gameutils.GameplayLogic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameplayLogicTest {

    private Board board;
    private Player player1;
    private Player player2;

    private GameplayLogic gameplayLogic;

    @BeforeEach
    void setUp() {
        board = new Board();
        player1 = new Player("Player 1", PlayerRole.ORDER);
        player2 = new Player("Player 2", PlayerRole.CHAOS);
        gameplayLogic = new GameplayLogic(board, player1, player2);
    }

    @Test
    void testPlayTurn() {
        // test making a valid move
        gameplayLogic.playTurn(0, 0, CellState.X);
        assertEquals(CellState.X, board.getCellState(0, 0));
        assertEquals(player2, gameplayLogic.getCurrentPlayer());

        // test making an invalid move
        gameplayLogic.playTurn(0, 0, CellState.O);
        assertEquals(CellState.X, board.getCellState(0, 0));
        assertEquals(player2, gameplayLogic.getCurrentPlayer());
    }

    @Test
    void testGetCurrentPlayer() {
        GameplayLogic gameplayLogic = new GameplayLogic(board, player1, player2);
        assertEquals(player1, gameplayLogic.getCurrentPlayer());

        // test after a turn has been played
        gameplayLogic.playTurn(0, 0, CellState.X);
        assertEquals(player2, gameplayLogic.getCurrentPlayer());
    }

    @Test
    void testIsGameOver() {
        GameplayLogic gameplayLogic = new GameplayLogic(board, player1, player2);

        // test when game is not over
        assertFalse(gameplayLogic.isGameOver());

        // test when game is over
        board.setCellState(0, 0, CellState.X);
        board.setCellState(0, 1, CellState.X);
        board.setCellState(0, 2, CellState.X);
        board.setCellState(0, 3, CellState.X);
        board.setCellState(0, 4, CellState.X);
        board.setCellState(0, 5, CellState.X);
        assertTrue(gameplayLogic.isGameOver());
    }

    @Test
    void testGetBoard() {
        assertEquals(board, gameplayLogic.getBoard());
    }

    @Test
    void testGetPlayer1() {
        assertEquals(player1, gameplayLogic.getPlayer1());
    }

    @Test
    void testGetPlayer2() {
        assertEquals(player2, gameplayLogic.getPlayer2());
    }

    @Test
    void testGetNoWinner() {
        assertEquals(null, gameplayLogic.getWinner());
    }

    @Test
    void testGetWinner() {
        gameplayLogic.playTurn(0, 0, CellState.X);
        gameplayLogic.playTurn(0, 1, CellState.X);
        gameplayLogic.playTurn(0, 2, CellState.X);
        gameplayLogic.playTurn(0, 3, CellState.X);
        gameplayLogic.playTurn(0, 4, CellState.X);
        gameplayLogic.playTurn(0, 5, CellState.X);
        assertEquals(player1, gameplayLogic.getWinner());
    }
}
