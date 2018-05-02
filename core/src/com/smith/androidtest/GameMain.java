package com.smith.androidtest;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.smith.androidtest.screen.GameScreen;
import com.smith.androidtest.screen.MenuScreen;

public class GameMain extends Game {

	private GameScreen gameScreen;
	private MenuScreen menuScreen;
	private SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();

		this.gameScreen = new GameScreen(batch, this);
		this.menuScreen = new MenuScreen(batch, this);

		setScreen(menuScreen);
	}

	public GameScreen getGameScreen() {
		return gameScreen;
	}

	public MenuScreen getMenuScreen() {
		return menuScreen;
	}

	public GameScreen getNewGameScreen() {
		return new GameScreen(batch, this);
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
	}
}
