package com.smith.androidtest.screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.smith.androidtest.*;
import com.smith.androidtest.figure.Figure;
import com.smith.androidtest.player.PlayerController;

public class GameScreen extends MyScreen {

    private Dialog gameOverDialog;

    public static State state = State.ACTING;

    public GameScreen(SpriteBatch batch, final GameMain gameMain) {
        super(batch, gameMain);
        System.out.println("Created game Screen");
    }

    @Override
    public void initialize() {
        initGameObjects();
    }

    private void initGameObjects() {
        float cellWidth = 204;
        int width = 7;
        int height = 8;

        float viewCenterX = camera.viewportWidth / 2;
        float viewCenterY = camera.viewportHeight / 2;

        float fieldX = viewCenterX - cellWidth * (float) width / 2;
        float fieldY = viewCenterY - cellWidth * (float) height / 2;

        GameField gameField = GameField.createGameFiled(fieldX, fieldY, cellWidth, width, height);

        for(Tile tile: gameField.getTileGrid().getList()) {
            gameStage.addActor(tile);
        }

        for(Figure figure: gameField.getFigures()) {
            gameStage.addActor(figure);
        }

        gameStage.addActor(gameField.getPlayerController());

        gameOverDialog = new Dialog("", false);
        gameOverDialog.setPosition(camera.viewportWidth / 2, camera.viewportHeight / 2);

        Button restartGame = new Button(
                "buttons/restart_up.png",
                "buttons/restart_down.png",
                0, 0
        ) {
            @Override
            public void act() {
                getGameMain().setScreen(getGameMain().getNewGameScreen());
                PlayerController.getInstance().setGameOver(false);
                PlayerController.getInstance().reset();
            }
        };

        gameOverDialog.addButton(restartGame);
        restartGame.setPosition(-restartGame.getWidth() / 2, -96);

        guiStage.addActor(gameOverDialog);
    }

    @Override
    public void render(float delta) {
        if (PlayerController.getInstance().isGameOver()) {
            this.pause();
//            System.out.println(getState());
            gameOverDialog.setVisible(true);
            gameOverDialog.setText("Game over!\n" + PlayerController.getInstance().getCurrent().getColor() + " player has won");
        }
        else {
            gameOverDialog.setVisible(false);
        }
        super.render(delta);
    }

    public enum State {
        PAUSE, ACTING
    }

}
