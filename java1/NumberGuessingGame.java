import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int maxAttempts = 10;
        int roundsWon = 0;
        int roundsPlayed = 0;
        boolean playAgain = true;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("Try to guess the number between 1 and 100.");
        
        while (playAgain) {
            int numberToGuess = random.nextInt(100) + 1;
            int attempts = 0;
            boolean guessedCorrectly = false;
            roundsPlayed++;

            System.out.println("\nRound " + roundsPlayed + " starts now!");

            while (attempts < maxAttempts && !guessedCorrectly) {
                System.out.print("Enter your guess (Attempt " + (attempts + 1) + " of " + maxAttempts + "): ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess < numberToGuess) {
                    System.out.println("Too low! Try again.");
                } else if (userGuess > numberToGuess) {
                    System.out.println("Too high! Try again.");
                } else {
                    System.out.println("Congratulations! You've guessed the number.");
                    guessedCorrectly = true;
                    roundsWon++;
                }
            }

            if (!guessedCorrectly) {
                System.out.println("You've used all attempts! The number was " + numberToGuess + ".");
            }

            System.out.print("Do you want to play again? (yes/no): ");
            scanner.nextLine(); // Consume newline
            String response = scanner.nextLine();
            playAgain = response.equalsIgnoreCase("yes");

            System.out.println("Score: " + roundsWon + " round(s) won out of " + roundsPlayed + " round(s) played.");
        }

        System.out.println("Thank you for playing! Final score: " + roundsWon + " round(s) won out of " + roundsPlayed + " round(s) played.");
        scanner.close();
    }
}
