package com.smith.androidtest;

import java.util.ArrayList;
import java.util.List;

public class Grid<T> {

    private int width;
    private int height;

    private List<T> list;

    public Grid(int width, int height) {
        this.list = new ArrayList<T>(width * height);
        this.width = width;
        this.height = height;
    }

    public T get(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            int i = y * width + x;
            return list.get(i);
        }
        else return null;
    }

    public void set(int x, int y, T t) {
        int i = y * width + x;
        list.add(i, t);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<T> getList() {
        return list;
    }
}
