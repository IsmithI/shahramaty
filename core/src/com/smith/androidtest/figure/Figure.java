package com.smith.androidtest.figure;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.smith.androidtest.*;
import com.smith.androidtest.player.Player;
import com.smith.androidtest.player.PlayerController;
import com.smith.androidtest.screen.GameScreen;
import com.smith.androidtest.screen.MyScreen;

import java.util.LinkedList;
import java.util.List;

public class Figure extends Actor {

    private Sprite sprite, select;
    private Tile tile;
    private Player player;
    private boolean selected = false;
    private boolean moveAfterAttack = true;

    private List<Tile> moves = new LinkedList<Tile>();

    public Figure(Sprite sprite, Tile tile, final Player player) {
        this.sprite = sprite;
        this.tile = tile;
        this.player = player;

        this.select = new Sprite(new Texture("tile.png"));
        this.select.setColor(Color.YELLOW);

        setPosition(tile.getX(), tile.getY());
        setBounds(getX(), getY(), sprite.getWidth(), sprite.getHeight());

        addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (!player.equals(PlayerController.getInstance().getCurrent())) {
                    System.out.println("Enemy clicked!");
                    System.out.println(getTile().getState());
                    if (getTile().getState() == Tile.State.ATTACK) {

                        //Tile of the attacked one
                        Tile tile = getTile();
                        tile.setFigure(null);

                        //Attacker
                        Figure figure = PlayerController.getInstance().getCurrentFigure();


                        if (figure.isMoveAfterAttack()) {
                            figure.getTile().setFigure(null);

                            figure.setTile(tile);
                            tile.setFigure(figure);

                            figure.setPosition(tile.getX(), tile.getY());
                        }


                        Figure.this.setTile(null);
                        Figure.this.remove();
                        if (Figure.this.toString().equals("King")) {
                            PlayerController.getInstance().gameOver();
                        }
                        else {
                            PlayerController.getInstance().setCurrentFigure(null);
                            PlayerController.getInstance().next();
                        }

                    }

                    GameField.getInstance().deselectAll();
                    return;
                }

                GameField.getInstance().deselectAll();

                if (player.equals(PlayerController.getInstance().getCurrent())) {
                    selected = !selected;
                    System.out.println("My lord!");
                    PlayerController.getInstance().setCurrentFigure(Figure.this);

                    updateMoves();
                }


            }
        });
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    protected void addMove(Tile tile) {
        if (tile != null) {
            if (tile.getFigure() != null) {
                if (isEnemyFigure(tile.getFigure())) {
                    tile.setState(Tile.State.ATTACK);
                    System.out.println(tile.toString());
                    getMoves().add(tile);
                }
            }
            else {
                tile.setState(Tile.State.MOVE);
                getMoves().add(tile);
            }
        }

    }

    public boolean isEnemyFigure(Figure figure) {
        return !figure.getPlayer().equals(getPlayer());
    }

    public void updateMoves() {};

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (isSelected()) {
            select.draw(batch, parentAlpha);
        }
        sprite.draw(batch, parentAlpha);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        if (sprite != null) sprite.setPosition(x, y);
        if (select != null) select.setPosition(x, y);
    }


    public void setMoves(List<Tile> moves) {
        this.moves = moves;
    }


    public List<Tile> getMoves() {
        return moves;
    }

    public int getArrayX() {
        return getTile().getCoordinates().getLetterToNum();
    }

    public int getArrayY() {
        return getTile().getCoordinates().getNumber()-1;
    }

    public boolean isMoveAfterAttack() {
        return moveAfterAttack;
    }

    public void setMoveAfterAttack(boolean moveAfterAttack) {
        this.moveAfterAttack = moveAfterAttack;
    }
}
