package com.smith.androidtest.figure;

import com.smith.androidtest.Tile;

public interface FigureFactoryInterface {

    Figure createKnight(Tile tile, boolean upgraded);
    Figure createCavalry(Tile tile, boolean upgraded);
    Figure createArcher(Tile tile, boolean upgraded);
    Figure createSpear(Tile tile, boolean upgraded);
    Figure createKing(Tile tile, boolean upgraded);

}
