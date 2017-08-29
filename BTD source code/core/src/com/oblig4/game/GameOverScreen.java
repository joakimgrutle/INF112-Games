package com.oblig4.game;

import java.util.concurrent.TimeUnit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import logicClasses.TDGame;

public class GameOverScreen implements Screen {
	private TDGame gamestate;
	private TowerDefence game;
	private OrthographicCamera camera;
	private Stage stage;
	private Sound buttonClickSnd;
	private Texture bckgndImage;
	private Texture logoImage;
	private Label volumeValue;
	private Viewport viewport;

	public GameOverScreen(final TowerDefence game, final TDGame gamestate) {
		viewport = new FitViewport(TowerDefence.width,TowerDefence.height);
		this.gamestate = gamestate;
		this.game = game;
		this.camera = new OrthographicCamera();
		// Depends on the resolution we choose
		camera.setToOrtho(false, 800, 480);
		this.stage = new Stage();
		this.buttonClickSnd = Gdx.audio.newSound(Gdx.files.internal("sound/MenuSelectSound.ogg"));
		// Background image
		this.bckgndImage = new Texture(Gdx.files.internal("image/pauseBckgnd.jpg"));


		// Setup buttons
		TextButton resumeButton = new TextButton("    Main Menu   ", TowerDefence.uiSkin);
		resumeButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				buttonClickSnd.play();
				// Wait for sound to play
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// Exit game
				game.setScreen(new MainMenuScreen(game));
				dispose();
			}
		});
		
		
		// Game over and Score
		Label.LabelStyle titleFont = new Label.LabelStyle(TowerDefence.uiSkin.getFont("title"), Color.WHITE);
		Label.LabelStyle font = new Label.LabelStyle(TowerDefence.uiSkin.getFont("font"), Color.BLACK);
		Label gameOverLabel = new Label("GAME OVER", titleFont);
		Label score = new Label("Your score: " + TDGame.getInstance().getScore(), font);
		
		Table table = new Table();
		Table table2 = new Table();
		table.setX(Gdx.graphics.getWidth() / 2);
		table.setY(Gdx.graphics.getHeight() / 2);
		table2.setX(Gdx.graphics.getWidth() / 2);
		table2.setY(0);
		resumeButton.setTransform(true);
		resumeButton.setScale(1f,2f);
		// Setup sound value row
		// Setup row for back button
		table2.add(resumeButton);
		table.add(gameOverLabel);
		table.row();
		table.add(score);

		// Add table to the stage
		stage.addActor(table);
		stage.addActor(table2);
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();

		// Draw background elements
		game.batch.begin();
		game.batch.end();

		// Draw buttons/HUD
		stage.act();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
		
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
		stage.dispose();
		buttonClickSnd.dispose();
		bckgndImage.dispose();
	}
}
