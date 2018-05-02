package com.smith.androidtest;

import com.smith.androidtest.figure.Figure;
import com.smith.androidtest.figure.FigureFactory;
import com.smith.androidtest.player.Player;
import com.smith.androidtest.player.PlayerController;

import java.util.LinkedList;
import java.util.List;

import static com.smith.androidtest.figure.FigureFactory.FigureType.*;

public class GameField {

    private static GameField instance = null;

    public static GameField getInstance() {
        return instance;
    }

    public static GameField createGameFiled(float x, float y, float cellWidth, int width, int height) {
        return instance = new GameField(x, y, cellWidth, width, height);
    }

    private Grid<Tile> tileGrid;
    private PlayerController playerController;

    private GameField(float x, float y, float cellWidth, int width, int height) {
        playerController = PlayerController.getInstance();

        FigureFactory<Player> blackFactory = new FigureFactory<Player>(playerController.getBlack());
        FigureFactory<Player> whiteFactory = new FigureFactory<Player>(playerController.getWhite());

        tileGrid = new Grid<Tile>(width, height);
        for (int j = 0; j < height; j ++) {
            for (int i = 0; i < width; i++) {
                Tile tile = new Tile(x + i * cellWidth, y + j * cellWidth, Coordinates.letters[i], j+1);
                tileGrid.set(i, j, tile);
            }
        }

        for(int i = 0; i < figureMap[0].length; i++) {
            Tile tile = tileGrid.get(i, 0);
            Figure figure = blackFactory.create(figureMap[0][i], tile, false);

            figure.setTile(tile);
            tile.setFigure(figure);
        }

        for(int i = 0; i < figureMap[1].length; i++) {
            Tile tile = tileGrid.get(i, tileGrid.getHeight()-1);
            Figure figure = whiteFactory.create(figureMap[1][i], tile, false);

            figure.setTile(tile);
            tile.setFigure(figure);
        }


    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public List<Figure> getFigures() {
        List<Figure> figures = new LinkedList<Figure>();
        for (Tile tile : tileGrid.getList()) {
            if (tile.getFigure() != null) {
                figures.add(tile.getFigure());
            }
        }
        return figures;
    }

    public void deselectAll() {
        for (Figure figure: getFigures()) {
            figure.setSelected(false);
        }

        for (Tile tile: getTileGrid().getList()) {
            tile.setState(Tile.State.IDLE);
        }
    }

    public Grid<Tile> getTileGrid() {
        return tileGrid;
    }

    private static FigureFactory.FigureType[][] figureMap = {
            {ARCHER, SPEAR, KNIGHT, CAVALRY, KING, KNIGHT, SPEAR},
            {SPEAR, KNIGHT, KING, CAVALRY, KNIGHT, SPEAR, ARCHER}
    };

    public Tile get(int x, int y ) {
        return tileGrid.get(x, y);
    }



    public Figure findFigure(int x, int y) {
        Tile tile = get(x, y);
        if (tile == null) {
            System.out.printf("No figure found at %s | %s", x, y);
            return null;
        }
        else return tile.getFigure();
    }


}
