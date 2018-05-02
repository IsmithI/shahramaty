package com.smith.androidtest.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Align;
import com.smith.androidtest.Button;
import com.smith.androidtest.GameMain;
import com.smith.androidtest.Text;

public class MenuScreen extends MyScreen{


    public MenuScreen(SpriteBatch batch, final GameMain gameMain) {
        super(batch, gameMain);
    }

    @Override
    public void initialize() {
        Text headerText = new Text.TextBuilder()
                .withText("Chahramaty")
                .alignBy(Align.center)
                .withFont(Text.generateFont(Gdx.files.internal("fonts/germanica/Shadowed Germanica.ttf"), 120), Color.WHITE)
                .atPosition(camera.viewportWidth / 2, camera.viewportHeight * 0.9f)
                .build();

        guiStage.addActor(headerText);

        Group menuButtons = new Group();

        Button startGame = new Button(
                "buttons/start_up.png",
                "buttons/start_down.png",
                camera.viewportWidth / 2 - 128,
                camera.viewportHeight / 2 - 48
        ) {
            @Override
            public void act() {
                getGameMain().setScreen(getGameMain().getGameScreen());
            }
        };
        startGame.addText(new Text.TextBuilder()
                .withText("Start game")
                .alignBy(Align.center)
                .atPosition(startGame.getX(), startGame.getY())
                .withFont(Text.generateFont(Gdx.files.internal("fonts/germanica/Shadowed Germanica.ttf"), 48), Color.BLACK)
                .build()
        );
//        menuButtons.addActor(startGame);

        guiStage.addActor(startGame);
    }

}
