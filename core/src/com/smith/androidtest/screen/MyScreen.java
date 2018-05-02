package com.smith.androidtest.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.smith.androidtest.GameMain;

import java.util.Comparator;

public abstract class MyScreen implements Screen {

    private final GameMain gameMain;

    protected SpriteBatch batch;
    protected SpriteBatch guiBatch;

    protected OrthographicCamera camera;

    protected ExtendViewport viewport;
    protected FillViewport guiViewport;

    protected Stage gameStage, guiStage;

    protected InputMultiplexer inputMultiplexer;

    public MyScreen(SpriteBatch batch, final GameMain gameMain) {
        this.gameMain = gameMain;
        this.batch = batch;

        this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        this.viewport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        this.guiViewport = new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        this.guiBatch = new SpriteBatch();
        guiBatch.setProjectionMatrix(camera.combined);

        gameStage = new Stage(viewport, this.batch);
        guiStage = new Stage(guiViewport, guiBatch);

        initialize();
    }

    public abstract void initialize();

    @Override
    public void show() {
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(guiStage);
        inputMultiplexer.addProcessor(gameStage);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        gameStage.getActors().sort(new Comparator<Actor>() {
            @Override
            public int compare(Actor o1, Actor o2) {
                return o1.getZIndex() - o2.getZIndex();
            }
        });

        gameStage.act(delta);
        guiStage.act(delta);

        gameStage.draw();
        guiStage.draw();

    }

    @Override
    public void resize(int width, int height) {
        guiViewport.update(width, height);
        viewport.update(width, height);
    }


    @Override
    public void pause() {
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(guiStage);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void resume() {
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(guiStage);
        inputMultiplexer.addProcessor(gameStage);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        guiBatch.dispose();

        gameStage.dispose();
        guiStage.dispose();
    }

    public InputMultiplexer getInputMultiplexer() {
        return inputMultiplexer;
    }

    public void setInputMultiplexer(InputMultiplexer inputMultiplexer) {
        this.inputMultiplexer = inputMultiplexer;
    }

    public GameMain getGameMain() {
        return gameMain;
    }
}

