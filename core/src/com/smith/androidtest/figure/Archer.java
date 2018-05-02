package com.smith.androidtest.figure;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.smith.androidtest.GameField;
import com.smith.androidtest.player.Player;
import com.smith.androidtest.Tile;

import java.util.LinkedList;

public class Archer extends Figure {

    private int distance = 4;

    public Archer(Sprite sprite, Tile tile, Player player) {
        super(sprite, tile, player);
        setMoveAfterAttack(false);
    }

    @Override
    public void updateMoves() {
        setMoves(new LinkedList<Tile>());

        GameField gameField = GameField.getInstance();
        int selfX = getArrayX();
        int selfY = getArrayY();

        for (int i = 1; i < distance; i++) {
            Tile tile;
            if (getPlayer().getColor().equals("white")) {
                tile = gameField.getTileGrid().get(selfX, selfY - i);
            }
            else {
                tile = gameField.getTileGrid().get(selfX, selfY + i);
            }
            if (tile != null) {
                if (tile.getFigure() != null) {
                    if (isEnemyFigure(tile.getFigure())) {
                        addMove(tile);
                        break;
                    }
                }
            }
            addMove(tile);
        }

        System.out.println(getMoves().size());
        if (getMoves().size() > 2) {
            if (getPlayer().getColor().equals("white")) {
                addMove(gameField.get(selfX + 1, selfY - (distance-1)));
                addMove(gameField.get(selfX - 1, selfY - (distance-1)));
            }
            else {
                addMove(gameField.get(selfX + 1, selfY + (distance-1)));
                addMove(gameField.get(selfX - 1, selfY + (distance-1)));
            }
        }

    }
}
