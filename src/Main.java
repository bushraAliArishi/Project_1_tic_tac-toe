import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            boolean winner = false;
            char[][] ticTacToeBoard = new char[][]{{'1', '2', '3'}, {'4', '5', '6'}, {'7', '8', '9'}};
            char roll = ' ';
            int rounds = 1; // Number of rounds played
            int userWins = 0; // User wins
            int computerWins = 0; // Computer wins

            // Input for player's symbol with exception handling
            while (true) {
                try {
                    System.out.println("What will you play with: (x|o)");
                    roll = input.next().charAt(0);
                    if (roll != 'x' && roll != 'o') {
                        throw new InputMismatchException("Invalid input. Please enter 'x' or 'o'.");
                    }
                    break;
                } catch (InputMismatchException mis) {
                    System.out.println(mis.getMessage());
                    input.nextLine(); // Clear the invalid input
                }
            }

            // Choose game type: one round or three rounds
            int gameType = 0;
            while (gameType != 1 && gameType != 3) {
                System.out.println("Choose game type: 1 for one round, 3 for three rounds.");
                try {
                    gameType = input.nextInt();
                    if (gameType != 1 && gameType != 3) {
                        throw new InputMismatchException("Please enter either 1 or 3.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println(e.getMessage());
                    input.nextLine(); // Clear the invalid input
                }
            }

            while (rounds <= gameType) {
                winner = ticTacToe(roll, ticTacToeBoard);

                if (winner) {
                    System.out.println("\nYou win this round!");
                    userWins++;
                } else {
                    System.out.println("\nYou lose this round. Try again.");
                    computerWins++;
                }

                // Clear the board for the next round
                ticTacToeBoard = new char[][]{{'1', '2', '3'}, {'4', '5', '6'}, {'7', '8', '9'}};
                rounds++;
            }

            // Display final results
            System.out.println("\nFinal results: You won " + userWins + " rounds, Computer won " + computerWins + " rounds.");

            // Ask the player if they want to play again
            System.out.print("Do you want to play again? (Y/N): ");
            char replay;
            while (true) {
                try {
                    replay = input.next().charAt(0);
                    if (replay == 'Y' || replay == 'y') {
                        playAgain = true;
                        break;
                    } else if (replay == 'N' || replay == 'n') {
                        playAgain = false;
                        break;
                    } else {
                        throw new InputMismatchException("Invalid input. Please enter 'Y' or 'N'.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println(e.getMessage());
                    input.nextLine(); // Clear the invalid input
                }
            }
        }
        input.close(); // Close the scanner to avoid resource leaks
    }

    public static boolean ticTacToe(char roll, char[][] board) {
        Scanner input = new Scanner(System.in);
        boolean userWin = false;
        boolean playGame = true;
        ArrayList<Character> takenPlaces = new ArrayList<>();

        while (playGame) {
            displayBoard(board);

            int place = 0;
            boolean validInput = false;
            while (!validInput) {
                try {
                    System.out.println("\nEnter the position you will play in (1-9): ");
                    place = Integer.parseInt(input.nextLine());

                    // Check if input is valid and not already taken
                    if (place < 1 || place > 9 || takenPlaces.contains((char) (place + '0'))) {
                        throw new InputMismatchException("Invalid position. Please enter a valid number (1-9) that is not taken.");
                    }
                    validInput = true;
                } catch (InputMismatchException e) {
                    System.out.println(e.getMessage());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number between 1 and 9.");
                }
            }

            takenPlaces.add((char) (place + '0'));
            placeMarker(roll, place, board);

            // Check if the user wins after their turn
            userWin = checkWin(roll, board);

            if (userWin) {
                playGame = false; // End game if user wins
                break;
            }

            // Computer's turn
            int computerMove = computerRoll(1, 9, takenPlaces);
            char cRoll = (roll == 'x') ? 'o' : 'x'; // Set computer roll based on user roll
            placeMarker(cRoll, computerMove, board);
            takenPlaces.add((char) (computerMove + '0'));

            // Check if the computer wins
            if (checkWin(cRoll, board)) {
                playGame = false; // End game if computer wins
            }

            // Check if the game ended in a tie
            if (takenPlaces.size() == 9) {
                System.out.println("It's a tie!");
                playGame = false; // End game if it's a tie
            }
        }

        return userWin;
    }

    public static void displayBoard(char[][] board) {
        System.out.println();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
        }
    }

    public static void placeMarker(char roll, int place, char[][] board) {
        int row = (place - 1) / 3;
        int col = (place - 1) % 3;
        if (board[row][col] != 'x' && board[row][col] != 'o') {
            board[row][col] = roll;
        } else {
            System.out.println("This place is taken.");
        }
    }

    public static boolean checkWin(char roll, char[][] board) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == roll && board[i][1] == roll && board[i][2] == roll) return true;
            if (board[0][i] == roll && board[1][i] == roll && board[2][i] == roll) return true;
        }
        if (board[0][0] == roll && board[1][1] == roll && board[2][2] == roll) return true;
        if (board[0][2] == roll && board[1][1] == roll && board[2][0] == roll) return true;

        return false;
    }

    public static int computerRoll(int min, int max, ArrayList<Character> takenPlaces) {
        Random random = new Random();
        int computerMove;
        do {
            computerMove = random.nextInt(max - min + 1) + min;
        } while (takenPlaces.contains((char) (computerMove + '0')));
        return computerMove;
    }
}
