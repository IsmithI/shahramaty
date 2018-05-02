package com.smith.androidtest;

import java.util.Arrays;

public class Coordinates {

    private char letter;
    private int number;

    public static char[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i'};

    public Coordinates(char letter, int number) {
        this.letter = letter;
        this.number = number;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getLetterToNum() {
        return Arrays.binarySearch(letters, letter);
    }

    @Override
    public String toString() {
        return letter + ":" + number;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Coordinates) {
            Coordinates t = (Coordinates) obj;
            return t.getLetter() == this.getLetter() && t.getNumber() == this.getNumber();
        }
        else {
            return false;
        }
    }
}