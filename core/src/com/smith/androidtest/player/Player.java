package com.smith.androidtest.player;

public class Player {

    private String color;

    public Player(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Player) {
            return ((Player) obj).color.equals(this.color);
        }
        else return false;
    }
}
