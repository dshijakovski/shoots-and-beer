package com.example.shotsandbeer.Models;

// Class representing the HighScore object in the list of high scores
public class HighScore {
    public String timestamp;
    public Integer attempts;

    public HighScore(String timestamp, Integer attempts) {
        this.timestamp = timestamp;
        this.attempts = attempts;
    }
}
