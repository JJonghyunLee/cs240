package hangman;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class EvilHangman {

    public static void main(String[] args) throws EmptyDictionaryException, IOException, GuessAlreadyMadeException {
        File dictionary = new File(args[0]);
        int wordLength = Integer.valueOf(args[1]);
        int guessCount = Integer.valueOf(args[2]);

        EvilHangmanGame evilHangmanGame = new EvilHangmanGame();
        evilHangmanGame.startGame(dictionary, wordLength);
        for(int i = guessCount; i > 0; i--) {
            boolean duplicate = false;
            System.out.println("You have " + i + " guesses left");
            System.out.println("Used letters: " + evilHangmanGame.getGuessedLetters());
            System.out.println(("Word: " + evilHangmanGame.currentPattern));
            System.out.print("Enter guess: ");
            Scanner scanner = new Scanner(System.in);
            char guessChar = scanner.next().charAt(0);
            guessChar = Character.toLowerCase(guessChar);
            if(guessChar < 'a' || guessChar > 'z') {
                System.out.println("Invalid input.");
                i++;
                continue;
            }
            for(char guessedLetter : evilHangmanGame.getGuessedLetters()) {
                if(guessedLetter == guessChar) {
                    System.out.println("That letter has already been guessed.");
                    duplicate = true;
                    i++;
                    continue;
                }
            }
            if(duplicate) continue;

            String previousPattern = evilHangmanGame.currentPattern;
            evilHangmanGame.makeGuess(guessChar);
            String newPattern = evilHangmanGame.currentPattern;
            if(previousPattern.equals(newPattern))  {
                System.out.println("Sorry, there are no " + guessChar);
            }
            else {
                System.out.println("Yes, there is " + diffCount(previousPattern, newPattern) + " " + guessChar);
            }
            if(evilHangmanGame.countBlank() == 0) {
                System.out.println("You win! You guessed the word " + evilHangmanGame.currentPattern);
                return;
            }
        }

    }

    public static int diffCount(String word1, String word2) {
        int diffCount = 0;
        for(int i = 0; i < word1.length(); i++) {
            if(word1.charAt(i) != word2.charAt(i)) diffCount++;
        }
        return diffCount;
    }

}
