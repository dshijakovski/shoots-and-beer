package com.example.shotsandbeer.Models;

import com.example.shotsandbeer.NewGame;

// REVIEW: packages should be named with a lowercase per the package naming conventions
// Class representing a single cell in the game view rows of cells
// REVIEW: Should you not name this cell then instead based on the comment? Naming things as accurate to their use
// and with as much context as possible will make code way more readable than trying to remember
// that this Digit means that
public class Digit {
    public int color;
    public String value;

    public Digit(int color, char value) {
        this.color = color;
        this.value = String.valueOf(value);
    }
}
