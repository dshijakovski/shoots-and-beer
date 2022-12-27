package com.example.shotsandbeer.Models;

// REVIEW: redundant comment
// Class representing the HighScore object in the list of high scores
public class HighScore {
    public String timestamp;
    // REVIEW: Why Integer, the wrapped object, instead of int, the value?
    public Integer attempts;

    public HighScore(String timestamp, Integer attempts) {
        this.timestamp = timestamp;
        this.attempts = attempts;
    }
}
