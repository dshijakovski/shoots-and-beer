package com.example.shotsandbeer;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

// REVIEW: - You have a GameManager, but you don’t hold all of the Game logic there -
// some is scattered in NewGame still (like validating the attempt and setting directly
// that variable from the view) making it harder to follow and mixing UI with business logic
public class GameManager {
    public static final String TIMESTAMP_FORMAT = "EEE, d MMM yyyy HH:mm";
    public static final String HIGH_SCORES_PREFS = "HighScoresPrefs";
    public static final int DIGITS = 4;

    public int attempts;
    public String answer;

    // REVIEW: digitFrequencies would be more accurate, also again - why Integer not int?
    private Map<Character, Integer> digitFrequency = new HashMap<>();

    // REVIEW: you do not need an empty constructor here
    public GameManager() {}

    public void startNewGame() {
        // REVIEW: do not need to use this.
        this.attempts = 0;

        // Generate a new answer
        // REVIEW: nice use of a string builder here :D
        StringBuilder targetString = new StringBuilder();

        for (int i = 0; i < DIGITS; i++) {
            // REVIEW: instead of creating a new Random object every time, cache it in a member variable and use that
            int randomDigit = new Random().nextInt(10);
            targetString.append(randomDigit);
        }
        // REVIEW: do not need to use this.
        this.answer = targetString.toString();

        // Generate a map of each character's frequency in the word
        // REVIEW: do not need to use this.
        for (char digit: this.answer.toCharArray()) {
            if (this.digitFrequency.containsKey(digit)) {
                Integer frequency = this.digitFrequency.get(digit);
                this.digitFrequency.put(digit, frequency + 1);
            } else {
                this.digitFrequency.put(digit, 1);
            }
        }
    }

    // Returns a corresponding colors list based on each digit's presence in the target number
    public int[] getDigitColors(String guess) {
        // Make shallow copy of the digitFrequency
        // REVIEW: call this digitFrequenciesCopy better then
        Map<Character, Integer> digitFreq = new HashMap<>(this.digitFrequency);

        int[] colors = new int[DIGITS];

        for (int i = 0; i < DIGITS; i++) {
            char guessDigit = guess.charAt(i);

            if (answer.contains(Character.toString(guessDigit))) {
                if (answer.charAt(i) == guessDigit) {
                    // Correct position
                    colors[i] = Colors.CORRECT;

                    // Decrement the freq of that letter
                    // REVIEW: why not call it frequency like before?
                    Integer freq = digitFreq.get(guessDigit);
                    digitFreq.put(guessDigit, freq - 1);
                } else {
                    // Incorrect position
                    Integer freq = digitFreq.get(guessDigit);
                    // REVIEW: Android Studio complains about not checking for null here for freq
                    if (freq > 0) {
                        // There is still a letter not matched -> mark as incorrect position
                        colors[i] = Colors.INCORRECT_POSITION;
                    } else {
                        // All occurrences of this digit have been matched -> mark as incorrect
                        colors[i] = Colors.INCORRECT;
                    }
                }
            }
            // REVIEW: you can use Ctrl+Alt+L on Windows to auto-format: it would show you also that
            // in Java standards, you have } else { in the above line
            else {
                colors[i] = Colors.INCORRECT;
            }
        }

        return colors;
    }

    // REVIEW: this was not part of the requirements, but adding fun extras is good - though
    // honestly this got a bit annoying :p
    public String getRandomFailedMessage() {
        String[] messages = {
                "Almost there! Try again...",
                "Use the information from the previous guesses!",
                "Not quite! How about another try?",
                "Nah, it isn't that easy ;)",
                "This is fun right? ...Right?"
        };

        // REVIEW: instead of creating a new Random object every time, cache it in a member variable and use that
        int randomIndex = new Random().nextInt(messages.length);
        return messages[randomIndex];
    }

}
