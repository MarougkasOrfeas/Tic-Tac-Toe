import java.util.*;
/**
 * The TicTacToe class implements a simple command-line version of the Tic-Tac-Toe game.
 * @author marou
 */
public class TicTacToe {

    private static final int BOARD_SIZE = 9;
    private static final String[] board = new String[BOARD_SIZE];
    private static final Scanner scanner = new Scanner(System.in);
    private static final String[] WINNING_COMBINATIONS = {
            "123", "456", "789", // Rows
            "147", "258", "369", // Columns
            "159", "357" // Diagonals
    };
    private static String turn;

    /**
     * Checks if there is a winner in the current game state.
     * @return The symbol of the winner ("X" or "O") if there is a winner, "draw" if it's a draw,
     *         or null if the game is still ongoing.
     */
    private static String checkWinner(){

        for (String combination : WINNING_COMBINATIONS) {
            String line = board[combination.charAt(0) - '1'] + board[combination.charAt(1) - '1'] + board[combination.charAt(2) - '1'];
            if (line.equals("XXX")) {
                return "X";
            } else if (line.equals("OOO")) {
                return "O";
            }
        }

        for(int i=0; i<BOARD_SIZE; i++){
            if(Arrays.asList(board).contains(String.valueOf(i + 1))){
                break;
            }else if(i == BOARD_SIZE -1){
                return "draw";
            }
        }

        System.out.println(turn + "'s turn\nEnter a slot number to place " + turn + " in:\t" );
        return null;
    }

    /**
     * Displays the current state of the Tic-Tac-Toe board.
     */
    private static void showBoard(){
        System.out.println("|---|---|---|");
        for (int i = 0; i < BOARD_SIZE; i += 3) {
            System.out.println("| " + board[i] + " | " + board[i + 1] + " | " + board[i + 2] + " |");
            System.out.println("|---|---|---|");
        }
    }

    /**
     * The main method that starts the Tic-Tac-Toe game.
     * @param args The command-line arguments (not used).
     */
    public static void main(String[] args) {
        turn = "X";
        String winner = null;

        for(int i = 0; i < BOARD_SIZE; i++){
            board[i] = String.valueOf(i + 1);
        }

        System.out.println("Welcome to Tic Tac Toe!.");
        showBoard();
        System.out.println("X will play first. Enter a slot number to place X in:\t");

        while(winner == null){
            int userInput;
            try{
                userInput = scanner.nextInt();
                if(!(userInput > 0 && userInput <= BOARD_SIZE)){
                    System.out.println("Invalid input\nPlease re-enter a valid slot number:\t");
                    continue;
                }
            }catch (InputMismatchException e){
                System.out.println("Invalid input.\nRe-Enter slot number:\t");
                scanner.next(); // Clear the invalid input from the scanner
                continue;
            }

            if (board[userInput - 1].equals(String.valueOf(userInput))){
                board[userInput - 1] = turn;
                turn = turn.equals("X") ? "O" : "X";
                showBoard();
                winner = checkWinner();
            }else {
                System.out.println("Slot already taken; re-enter slot number:");
            }
        }

        // Display results-ending messages
        if (winner.equalsIgnoreCase("draw")) {
            System.out.println("It's a draw! Thanks for playing.");
        }
        else {
            System.out.println("Congratulations! " + winner + "'s have won! Thanks for playing.");
        }
    }
}