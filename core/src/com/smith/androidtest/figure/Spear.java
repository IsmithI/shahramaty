package com.smith.androidtest.figure;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.smith.androidtest.GameField;
import com.smith.androidtest.player.Player;
import com.smith.androidtest.Tile;

public class Spear extends Figure {

    private int distance = 3;

    public Spear(Sprite sprite, Tile tile, Player player) {
        super(sprite, tile, player);
    }

    @Override
    public void updateMoves() {
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

        addMove(gameField.get(selfX + 1, selfY));
        addMove(gameField.get(selfX - 1, selfY));


    }
}
