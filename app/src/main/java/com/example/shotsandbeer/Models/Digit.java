package com.example.shotsandbeer.Models;

public class Digit {
    public int color;
    public String value;

    public Digit(int color, char value) {
        this.color = color;
        this.value = String.valueOf(value);
    }
}
