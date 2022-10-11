package hangman;

import java.awt.desktop.SystemSleepEvent;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class EvilHangmanGame implements IEvilHangmanGame{
    public Set<String> wordList = new TreeSet<>();
    String currentPattern = null;
    SortedSet<Character> guessedLetters = new TreeSet<>();
    Map<String, Set<String>> wordMap = new TreeMap<>();

    @Override
    public void startGame(File dictionary, int wordLength) throws IOException, EmptyDictionaryException {
        wordList.clear();
        Scanner scanner = new Scanner(dictionary);
        while(scanner.hasNext()) {
            String currentWord = scanner.next();
            if(currentWord.length() == wordLength) wordList.add(currentWord);
        }
        if(wordList.isEmpty()) throw new EmptyDictionaryException();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < wordLength; i++) {
            sb.append('_');
        }
        currentPattern = sb.toString();
    }

    @Override
    public Set<String> makeGuess(char guess) throws GuessAlreadyMadeException {
        wordMap.clear();
        guess = Character.toLowerCase(guess);
        for(Character letter : guessedLetters) {
            if(guess == letter) throw new GuessAlreadyMadeException();
        }
        guessedLetters.add(guess);
        String currentPattern;
        for(String word : wordList) {
            currentPattern = getPattern(word, guess);
            if(wordMap.containsKey(currentPattern)) {
                wordMap.get(currentPattern).add(word);
            }
            else {
                wordMap.put(currentPattern, new TreeSet<>());
                wordMap.get(currentPattern).add(word);
            }
        }
        wordList = findLargestSet(wordMap);
        return findLargestSet(wordMap);
    }

    public Set<String> findLargestSet(Map<String, Set<String>> wordMap) {
        Set<String> currentSet = null;
        int currentSize=0;
        String currentPattern=null;
        for(Map.Entry<String, Set<String>> entry : wordMap.entrySet()) {
            if(currentSize < entry.getValue().size()) {
                currentSet = entry.getValue();
                currentSize = entry.getValue().size();
                currentPattern = entry.getKey();
            }
        }
        addPattern(currentPattern);
        return currentSet;
    }

    public String getPattern(String word, char letter) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < word.length(); i++) {
            if(word.charAt(i) == letter) {
                sb.append(letter);
            }
            else {
                sb.append('_');
            }
        }
        return sb.toString();
    }

    public void addPattern(String newPattern) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < currentPattern.length(); i++) {
            if(currentPattern.charAt(i) > newPattern.charAt(i)) sb.append(currentPattern.charAt(i));
            else if(currentPattern.charAt(i) < newPattern.charAt(i)) sb.append(newPattern.charAt(i));
            else sb.append(currentPattern.charAt(i));
        }
        currentPattern = sb.toString();
    }

    @Override
    public SortedSet<Character> getGuessedLetters() {
        return guessedLetters;
    }

    public int countBlank() {
        int blankCount = 0;
        for(int i = 0; i < currentPattern.length(); i++) {
            if(currentPattern.charAt(i) == '_') blankCount++;
        }
        return blankCount;
    }
}
