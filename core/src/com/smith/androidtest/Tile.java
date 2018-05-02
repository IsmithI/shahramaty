package com.smith.androidtest;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.smith.androidtest.figure.Figure;
import com.smith.androidtest.player.PlayerController;

public class Tile extends Actor {

    private Sprite sprite = null, dot = null;
    private Coordinates coordinates;
    private Figure figure;
    private BitmapFont font;
    private State state = State.IDLE;

    public Tile(float x, float y, Coordinates coordinates) {
        sprite = new Sprite(new Texture("tile.png"));
        dot = new Sprite(new Texture("dot.png"));

        setPosition(x, y);

        this.font = new BitmapFont();
        this.font.setColor(Color.BLACK);
        this.coordinates = coordinates;


        setBounds(getX(), getY(), sprite.getWidth(), sprite.getHeight());

        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (state == State.MOVE) {
                    Figure figure = PlayerController.getInstance().getCurrentFigure();
                    figure.getTile().setFigure(null);
                    figure.setTile(Tile.this);
                    setFigure(figure);

                    PlayerController.getInstance().setCurrentFigure(null);
                    PlayerController.getInstance().next();

                }
                GameField.getInstance().deselectAll();
            }
        });
    }

    public Tile(float x, float y, char letter, int number) {
        this(x, y, new Coordinates(letter, number));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.setColor(Color.WHITE);
        switch (state) {
            case MOVE:
                sprite.draw(batch, parentAlpha);
                dot.setColor(Color.BLUE);
                dot.draw(batch, parentAlpha);
                break;
            case ATTACK:
                sprite.setColor(Color.RED);
                sprite.draw(batch, parentAlpha);
                break;
            default:
                sprite.draw(batch, parentAlpha);
        }

    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        if (sprite != null) sprite.setPosition(x, y);
        if (dot != null) dot.setPosition(x + getWidth() / 2 - 32, y + getHeight() / 2 - 32);
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Figure getFigure() {
        return figure;
    }

    public void setFigure(Figure figure) {
        if (figure != null) figure.setPosition(getX(), getY());
        this.figure = figure;
    }

    @Override
    public String toString() {
        return getCoordinates().toString() + "|" + state;
    }

    @Override
    public float getWidth() {
        return sprite.getWidth();
    }

    @Override
    public float getHeight() {
        return sprite.getHeight();
    }

    public enum State {
        MOVE, ATTACK, IDLE
    }
}

