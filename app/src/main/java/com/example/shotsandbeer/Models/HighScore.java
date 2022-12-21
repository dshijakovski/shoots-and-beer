package com.example.shotsandbeer.Models;

public class HighScore {
    public String timestamp;
    public Integer attempts;

    public HighScore(String timestamp, Integer attempts) {
        this.timestamp = timestamp;
        this.attempts = attempts;
    }
}
