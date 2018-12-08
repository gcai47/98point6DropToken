import java.util.List;
import java.util.Scanner;

/**
 * Class CLMain is a command line interface that lets a user play the game
 * 98point6 DropToken.
 */
public class CLMain {
    public static void main(String[] args) {
        Manager manager = new Manager();
        Scanner stdin = new Scanner(System.in);
        boolean gameOver = false;

        System.out.println("Welcome to 98point6 Drop Token!");
        help();

        while (!gameOver) {
            Scanner input = new Scanner(stdin.nextLine());
            if (input.hasNext()) {
                String command = input.next();
                if (command.equalsIgnoreCase("PUT")) {
                    if (input.hasNextInt()) {
                        int column = input.nextInt();
                        placeMove(manager, column);
                    } else {
                        help();
                    }
                } else if (command.equalsIgnoreCase("GET")) {
                    printPreviousMoves(manager);
                } else if (command.equalsIgnoreCase("BOARD")) {
                    manager.printBoard();
                } else if (command.equalsIgnoreCase("EXIT")) {
                    gameOver = true;
                } else {
                    help();
                }
            } else {
                help();
            }
        }
    }

    /**
     * Place a move on the board.
     *
     * @param manager  The game manager
     * @param column   The column to place a move in
     */
    private static void placeMove(Manager manager, int column) {
        boolean successful = manager.place(column);
        if (successful) {
            if (manager.checkWin(Player.ONE) || manager.checkWin(Player.TWO)) {
                System.out.println("WIN");
            } else if (manager.isFull()) {
                System.out.println("DRAW");
            } else {
                System.out.println("OK");
            }
        } else {
            System.out.println("ERROR");
        }
    }

    /**
     * Prints out all the previous moves that have been made.
     *
     * @param manager  The game manager
     */
    private static void printPreviousMoves(Manager manager) {
        List<Integer> previousMoves = manager.getPreviousMoves();
        for (int move : previousMoves) {
            System.out.println(move);
        }
    }

    /**
     * Print a message about how to use the program.
     */
    private static void help() {
        System.out.println("Commands: PUT <column>, GET, BOARD, EXIT");
    }
}
