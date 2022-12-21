package com.example.shotsandbeer;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class GameManager {
    public static final String TIMESTAMP_FORMAT = "EEE, d MMM yyyy HH:mm";
    public static final String HIGH_SCORES_PREFS = "HighScoresPrefs";
    public static final int DIGITS = 4;

    public int attempts;
    public String answer;

    private Map<Character, Integer> digitFrequency = new HashMap<>();

    public GameManager() {}

    public void startNewGame() {
        this.attempts = 0;

        // Generate a new answer
        StringBuilder targetString = new StringBuilder();

        for (int i = 0; i < DIGITS; i++) {
            int randomDigit = new Random().nextInt(10);
            targetString.append(randomDigit);
        }
        this.answer = targetString.toString();

        // Generate a map of each character's frequency in the word
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
        Map<Character, Integer> digitFreq = new HashMap<>(this.digitFrequency);

        int[] colors = new int[DIGITS];

        for (int i = 0; i < DIGITS; i++) {
            char guessDigit = guess.charAt(i);

            if (answer.contains(Character.toString(guessDigit))) {
                if (answer.charAt(i) == guessDigit) {
                    // Correct position
                    colors[i] = Colors.CORRECT;

                    // Decrement the freq of that letter
                    Integer freq = digitFreq.get(guessDigit);
                    digitFreq.put(guessDigit, freq - 1);
                } else {
                    // Incorrect position
                    Integer freq = digitFreq.get(guessDigit);
                    if (freq > 0) {
                        // There is still a letter not matched -> mark as incorrect position
                        colors[i] = Colors.INCORRECT_POSITION;
                    } else {
                        // All occurrences of this digit have been matched -> mark as incorrect
                        colors[i] = Colors.INCORRECT;
                    }
                }
            }
            else {
                colors[i] = Colors.INCORRECT;
            }
        }

        return colors;
    }

    public String getRandomFailedMessage() {
        String[] messages = {
                "Almost there! Try again...",
                "Use the information from the previous guesses!",
                "Not quite! How about another try?",
                "Nah, it isn't that easy ;)",
                "This is fun right? ...Right?"
        };

        int randomIndex = new Random().nextInt(messages.length);
        return messages[randomIndex];
    }

}
