package com.oblig4.spooks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.TimeUtils;

import logicClasses.SpooksGame;
import rooms.Livingroom;

public class ScaryFace implements Screen {

	Spooks game;
	Texture img;
	Music music;
	Texture gameOverImage;
	float startTime;
	private Stage stage;
	private Hud hud;
	private SpooksGame gamestate;

	public ScaryFace(Spooks games, Stage stages, Hud huds, SpooksGame gameState, Texture gameOverImage) {
		this.game = games;
		this.stage = stages;
		this.gamestate = gameState;
		this.hud = huds;
		games.menuMusic.stop();
		music = Gdx.audio.newMusic(Gdx.files.internal("sound\\Scream.wav"));
		music.setLooping(true);
		music.play();
		music.setVolume(1f);

		this.img = new Texture("puzzles\\scaryFace.jpg");
		startTime = 0;
		this.gameOverImage = gameOverImage;
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();
		game.batch.draw(img, 0, 0);
		game.batch.end();
		startTime++;
		if (startTime >= 200) {

			music.stop();
			game.setScreen(new GameOverScreen(game, gamestate, gameOverImage));



		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		img.dispose();

	}

}
