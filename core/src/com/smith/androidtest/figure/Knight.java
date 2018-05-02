package com.smith.androidtest.figure;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.smith.androidtest.GameField;
import com.smith.androidtest.player.Player;
import com.smith.androidtest.Tile;

import java.util.LinkedList;
import java.util.List;

public class Knight extends Figure {

    public Knight(Sprite sprite, Tile tile, Player player) {
        super(sprite, tile, player);
    }

    @Override
    public void updateMoves() {
        List<Tile> moves = new LinkedList<Tile>();
        GameField gameField = GameField.getInstance();
        int selfX = getArrayX();
        int selfY = getArrayY();

        if (getPlayer().getColor().equals("black")) {
            addMove(gameField.get(selfX + 1, selfY + 1));
            addMove(gameField.get(selfX - 1, selfY + 1));
        }
        else {
            addMove(gameField.get(selfX + 1, selfY - 1));
            addMove(gameField.get(selfX - 1, selfY - 1));
        }
    }
}
