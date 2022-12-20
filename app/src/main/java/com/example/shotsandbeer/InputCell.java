package com.example.shotsandbeer;

public class InputCell {
    public int color;
    public String value;

    InputCell(int color, char value) {
        this.color = color;
        this.value = String.valueOf(value);
    }
}
