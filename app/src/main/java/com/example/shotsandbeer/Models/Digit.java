package com.example.shotsandbeer.Models;

// Class representing a single cell in the game view rows of cells
public class Digit {
    public int color;
    public String value;

    public Digit(int color, char value) {
        this.color = color;
        this.value = String.valueOf(value);
    }
}
