package com.example.shotsandbeer;

import java.util.Random;

public class GameManager {
    public static final int DIGITS = 4;

    public int attempts;
    public String targetNumber;

    public GameManager() {}

    public void startNewGame() {
        this.attempts = 0;
        generateTargetNumber();
    }

    // TODO: May need to move code to startGame
    public void generateTargetNumber() {
        StringBuilder targetString = new StringBuilder();

        for (int i = 0; i < DIGITS; i++) {
            int randomDigit = new Random().nextInt(10);
            targetString.append(randomDigit);
        }

        this.targetNumber = targetString.toString();
    }

    // Returns a corresponding color based on the digit's presence in the target number
    public int getDigitColor(char digit, int position) {
        // TODO: Fix for repeating numbers
        int indexFound = this.targetNumber.indexOf(String.valueOf(digit));

        // Digit not present in target number
        if (indexFound == -1) return R.drawable.incorrect_rounded;

        // Digit present AND correct position
        if (digit == this.targetNumber.charAt(position)) return R.drawable.correct_position_rounded;

        // Digit present BUT incorrect position
        return R.drawable.incorrect_position_rounded;
    }

}
