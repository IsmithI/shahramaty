package com.smith.androidtest.player;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.smith.androidtest.figure.Figure;

public class PlayerController extends Actor {

    private static PlayerController instance;

    public static PlayerController getInstance() {
        if (instance == null) instance = new PlayerController();
        return instance;
    }

    private BitmapFont font;
    private Player black, white, current;
    private Figure currentFigure = null;
    private boolean gameOver = false;

    private PlayerController() {
        this.black = new Player("black");
        this.white = new Player("white");
        this.current = white;
        this.font = new BitmapFont();
        font.setColor(Color.WHITE);
    }

    public void next() {
        setCurrentFigure(null);
        if (current.equals(white)) {
            current = black;
        }
        else {
            current = white;
        }
    }

    public void reset() {
        this.current = white;
    }

    public void gameOver() {
        setGameOver(true);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        font.draw(batch, current.getColor() + "'s move", 32, 32);
    }

    public Player getBlack() {
        return black;
    }


    public Player getWhite() {
        return white;
    }

    public Player getCurrent() {
        return current;
    }

    public Figure getCurrentFigure() {
        return currentFigure;
    }

    public void setCurrentFigure(Figure currentFigure) {
        this.currentFigure = currentFigure;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}
