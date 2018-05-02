package com.smith.androidtest.figure;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.smith.androidtest.player.Player;
import com.smith.androidtest.Tile;

public class FigureFactory<T extends Player> implements FigureFactoryInterface{

    private T player;
    private String dir = "figures/";

    public FigureFactory(T player) {
        this.player = player;
    }

    public T getPlayer() {
        return player;
    }

    public void setPlayer(T player) {
        this.player = player;
    }

    public Figure create(FigureType figureType, Tile tile, boolean upgraded) {
        switch (figureType) {
            case KING:
                return createKing(tile, upgraded);
            case SPEAR:
                return createSpear(tile, upgraded);
            case ARCHER:
                return createArcher(tile, upgraded);
            case KNIGHT:
                return createKnight(tile, upgraded);
            case CAVALRY:
                return createCavalry(tile, upgraded);
            default: return null;
        }
    }

    @Override
    public Figure createKnight(Tile tile, boolean upgraded) {
        String up = upgraded ? "_up" : "";
        return new Knight(
                new Sprite(new Texture(dir + getPlayer().getColor() + "_knight" + up + ".png")),
                tile,
                getPlayer()
                );
    }

    @Override
    public Figure createCavalry(Tile tile, boolean upgraded) {
        String up = upgraded ? "_up" : "";
        return new Cavalry(
                new Sprite(new Texture(dir + getPlayer().getColor() + "_cavalry" + up + ".png")),
                tile,
                getPlayer()
        );
    }

    @Override
    public Figure createArcher(Tile tile, boolean upgraded) {
        String up = upgraded ? "_up" : "";
        return new Archer(
                new Sprite(new Texture(dir + getPlayer().getColor() + "_archer" + up + ".png")),
                tile,
                getPlayer()
        );
    }

    @Override
    public Figure createSpear(Tile tile, boolean upgraded) {
        String up = upgraded ? "_up" : "";
        return new Spear(
                new Sprite(new Texture(dir + getPlayer().getColor() + "_spear" + up + ".png")),
                tile,
                getPlayer()
        );
    }

    @Override
    public Figure createKing(Tile tile, boolean upgraded) {
        String up = upgraded ? "_up" : "";
        return new King(
                new Sprite(new Texture(dir + getPlayer().getColor() + "_king" + up + ".png")),
                tile,
                getPlayer()
        );
    }

    public enum FigureType {
        KNIGHT, ARCHER, CAVALRY, SPEAR, KING,
    }
}
