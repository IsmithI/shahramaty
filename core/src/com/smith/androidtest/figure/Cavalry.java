package com.smith.androidtest.figure;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.smith.androidtest.GameField;
import com.smith.androidtest.player.Player;
import com.smith.androidtest.Tile;

public class Cavalry extends Figure {
    public Cavalry(Sprite sprite, Tile tile, Player player) {
        super(sprite, tile, player);
    }

    @Override
    public void updateMoves() {
        GameField gameField = GameField.getInstance();
        int selfX = getArrayX();
        int selfY = getArrayY();

        addMove(gameField.get(selfX + 2, selfY));
        addMove(gameField.get(selfX - 2, selfY));
        addMove(gameField.get(selfX, selfY + 2));
        addMove(gameField.get(selfX, selfY - 2));
    }
}
