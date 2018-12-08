import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class Manager manages a game of 98point6 DropToken.
 * The game takes place on a 4x4 grid, with columns labeled 1 through 4.
 * Players can place a token into any column, in which case the token drops
 * to the lowest unoccupied row in that column. A player wins when he or she
 * has four tokens in a row. Otherwise the game continues until the board
 * is full. The two players playing the game are Player One and Player Two.
 */
public class Manager {
    /**
     * The size of the square grid.
     */
    public static final int SIZE = 4;

    /**
     *  Represents the state of the board.
     */
    private int[][] board;

    /**
     * Represents the player currently making a move.
     */
    private Player currentPlayer;

    /**
     * Keeps track of the previous moves made.
     */
    private List<Integer> previousMoves;

    /**
     * Constructs a new Board to store the state of the game.
     * Each location on the Board is initialized to be empty.
     * Player One starts by making the first move.
     */ 
    public Manager() {
        this.board = new int[SIZE][SIZE];
        this.currentPlayer = Player.ONE;
        this.previousMoves = new ArrayList<Integer>();
    }

    /**
     * Place a move from the current player to the given column.
     * On a successful move, alternates the player who is currently
     * making a move.
     *
     * @param column  The column to place a move in
     * @return  true if the place was successful, false otherwise (column
     *          is invalid or column is full)
     */
    public boolean place(int column) {
        if (column < 1 || column > SIZE) {
            return false;
        }
        int player = this.currentPlayer == Player.ONE ? 1 : 2;
        column--;
        for (int i = 0; i < SIZE; i++) {
            if (this.board[i][column] == 0) {
                this.board[i][column] = player;
                this.previousMoves.add(column + 1);
                this.switchPlayer();
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the given player has won the game.
     *
     * @param player  Checks if this player won the game
     * @return  true if the player won, false otherwise
     */
    public boolean checkWin(Player player) {
        int p = player == Player.ONE ? 1 : 2;

        // Check each row
        for (int i = 0; i < SIZE; i++) {
            int count = 0;
            for (int j = 0; j < SIZE; j++) {
                 if (this.board[i][j] == p) {
                    count++;
                }
            }
            if (count == SIZE) {
                return true;
            }
        }

        // Check each column 
        for (int i = 0; i < SIZE; i++) {
            int count = 0;
            for (int j = 0; j < SIZE; j++) {
                 if (this.board[j][i] == p) {
                    count++;
                }
            }
            if (count == SIZE) {
                return true;
            }
        }

        // Check diagonals
        int count1 = 0;
        int count2 = 0;
        for (int i = 0; i < SIZE; i++) {
            if (this.board[i][i] == p) {
                count1++;
            }
            if (this.board[i][SIZE - i - 1] == p) {
                count2++;
            }
        }
        return count1 == SIZE || count2 == SIZE;
   }

    /**
     * Checks if the board is full.
     *
     * @return  true if board is full, false otherwise
     */
    public boolean isFull() {
        return this.previousMoves.size() == SIZE * SIZE;
    }

    /**
     * Prints the Board.
     */
    public void printBoard() {
        for (int i = SIZE - 1; i >= 0; i--) {
            System.out.print("|");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(" " + this.board[i][j]);
            }
            System.out.println();
        }
        System.out.println("+--------");
    }

    /**
     * Return a list of the previous moves that have been made.
     * The return list is unmodifiable.
     *
     * @return  A list of all the previous columns that have been added to
     */
    public List<Integer> getPreviousMoves() {
        return Collections.unmodifiableList(this.previousMoves);
    }

    /**
     * Updates the player who is currently making a move.
     */
    private void switchPlayer() {
        if (this.currentPlayer == Player.ONE) {
            this.currentPlayer = Player.TWO;
        } else {
            this.currentPlayer = Player.ONE;
        }
    }
}
