package consoleui;

import annotations.Generated;
import exceptions.InvalidMoveException;
import gameutils.*;
import entities.*;
import gameutils.MoveParser;

import java.util.Scanner;

@Generated
public class GameplayConsole {
    private final Board board;
    private final BoardPrinter printer;
    private final ConsoleInputHandler consoleInputHandler;

    private final MoveParser moveParser;
    private final Player player1;
    private final Player player2;
    private final BoardChecker checker;
    private Player currentPlayer;
    private static final String THANKS_MESSAGE = "Thanks for playing!";
    private static final String INPUT_PROMPT = "Enter row,column: ";

    public GameplayConsole(Board board, ConsoleInputHandler consoleInputHandler, Player player1, Player player2) {
        this.board = board;
        this.printer = new BoardPrinter();
        this.consoleInputHandler = consoleInputHandler;
        this.moveParser = new MoveParser(board);
        this.player1 = player1;
        this.player2 = player2;
        this.checker = new BoardChecker(board);
        this.currentPlayer = player1;
    }

    public void play() {
        MessagePrinter.printWelcome();

        while (!checker.isGameOver()) {
            System.out.println("Current board:");
            printer.printBoard(board);

            System.out.println("It's " + currentPlayer.getName() + "'s turn.");
            int[] input = consoleInputHandler.getValidInput(INPUT_PROMPT, 1, board.getSize());
            int row = input[0] - 1;
            int col = input[1] - 1;

            CellState piece = consoleInputHandler.getPieceSelection();

            try {
                boolean moveMade = moveParser.makeMove(row, col, piece);
                if (!moveMade) {
                    continue;
                }
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

            currentPlayer = (currentPlayer == player1) ? player2 : player1;
        }

        System.out.println("Final board:");
        printer.printBoard(board);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to replay? Write 'Yes' or 'No'");
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("Yes")) {
            Board board = new Board();
            GameplayConsole gameplayConsole = new GameplayConsole(board, new ConsoleInputHandler(), player1, player2);
            gameplayConsole.play();
        } else {
            System.out.println(THANKS_MESSAGE);
            consoleInputHandler.close();
        }

    }

}