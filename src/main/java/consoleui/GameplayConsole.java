package consoleui;

import exceptions.InvalidMoveException;
import gameutils.*;
import entities.*;
import gameutils.MoveParser;

import java.util.Scanner;

public class GameplayConsole {
    private final Board board;
    private final BoardPrinter printer;
    private final ConsoleInputHandler consoleInputHandler;

    private final MoveParser moveParser;
    private final Player player1;
    private final Player player2;
    private final BoardChecker checker;
    private Player currentPlayer;

    public GameplayConsole(Board board, ConsoleInputHandler consoleInputHandler, Player player1, Player player2) {
        this.board = board;
        this.moveParser = new MoveParser(board);
        this.printer = new BoardPrinter();
        this.consoleInputHandler = consoleInputHandler;
        this.player1 = player1;
        this.player2 = player2;
        this.checker = new BoardChecker(board);
        currentPlayer = player1;
    }

    public void play() {
        MessagePrinter.printWelcome();

        while (!checker.isGameOver()) {
            System.out.println("Current board:");
            printer.printBoard(board);

            System.out.println("It's " + currentPlayer.getName() + "'s turn.");
            int[] input = consoleInputHandler.getValidInput("Enter row,column: ", 1, board.getSize());

            int row = input[0];
            int col = input[1];

            CellState piece = consoleInputHandler.getPieceSelection();

            try {
                boolean moveMade = moveParser.makeMove(row - 1, col - 1, piece);
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

            currentPlayer = currentPlayer == player1 ? player2 : player1;
        }

        System.out.println("Final board:");
        printer.printBoard(board);
        System.out.println("Thanks for playing!");
    }


    public static void main(String[] args) {
        Board board = new Board(6);
       /*
       InputHandler inputHandler = new InputHandler(new Scanner(System.in));
        Player player1 = new Player("Order", PlayerRole.ORDER);
        Player player2 = new Player("Chaos", PlayerRole.CHAOS);
        */

        // get player names from input keyboard
        ConsoleInputHandler consoleInputHandler = new ConsoleInputHandler();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Order player name: ");
        String name1 = scanner.nextLine();
        Player player1 = new Player(name1, PlayerRole.ORDER);
        System.out.print("Enter Chaos player name: ");
        String name2 = scanner.nextLine();
        Player player2 = new Player(name2, PlayerRole.CHAOS);



        GameplayConsole gameplayConsole = new GameplayConsole(board, consoleInputHandler, player1, player2);
        gameplayConsole.play();
    }


}