package game;

import java.util.Scanner;
import exceptions.InvalidMoveException;
import gameutils.BoardChecker;
import consoleui.*;
import entities.*;

public class Gameplay {
    private final Board board;
    private final BoardChecker checker;
    private final BoardPrinter printer;
    private final Scanner scanner;
    private final Player player1;
    private final Player player2;

    private Player currentPlayer;

    public Gameplay() {
        scanner = new Scanner(System.in);

        System.out.print("Enter Order player name: ");
        String name1 = scanner.nextLine();
        player1 = new Player(name1, PlayerRole.ORDER);

        System.out.print("Enter Chaos player name: ");
        String name2 = scanner.nextLine();
        player2 = new Player(name2, PlayerRole.CHAOS);

        board = new Board(6);
        checker = new BoardChecker(board);
        printer = new BoardPrinter();
        currentPlayer = player1;
    }

    public void play() {
        MessagePrinter.printWelcome();

        while (!checker.isGameOver()) {
            System.out.println("Current board:");
            printer.printBoard(board);

            System.out.println("It's " + currentPlayer.getName() + "'s turn.");
            int row = getValidInput("Enter row: ");
            int col = getValidInput("Enter column: ");
            CellState piece = getPieceSelection();

            try {
                board.makeMove(row - 1, col - 1, piece);
            } catch (InvalidMoveException e) {
                System.out.println("Invalid move: " + e.getMessage());
                continue;
            }

            if (checker.isOrderWinner()) {
                MessagePrinter.printWinner(player1);
                break;
            }

            if (checker.isChaosWinner()) {
                MessagePrinter.printWinner(player2);
                break;
            }

            currentPlayer = currentPlayer == player1 ? player2 : player1;
        }

        System.out.println("Final board:");
        printer.printBoard(board);
        System.out.println("Thanks for playing!");
    }

    private int getValidInput(String message) {
        int input;

        while (true) {
            System.out.print(message);
            String line = scanner.nextLine();

            try {
                input = Integer.parseInt(line);
                if (input < 1 || input > 6) {
                    throw new NumberFormatException();
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number from 1 to 6.");
            }
        }

        return input;
    }

    private CellState getPieceSelection() {
        CellState piece = null;

        while (piece == null) {
            System.out.print("Enter piece (O or X): ");
            String line = scanner.nextLine();

            if (line.equalsIgnoreCase("O")) {
                piece = CellState.O;
            } else if (line.equalsIgnoreCase("X")) {
                piece = CellState.X;
            } else {
                System.out.println("Invalid input. Please enter O or X.");
            }
        }

        return piece;
    }


    public static void main(String[] args) {
        Gameplay game = new Gameplay();
        game.play();
    }

}
