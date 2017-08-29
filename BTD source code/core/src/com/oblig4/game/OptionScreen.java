package com.oblig4.game;

import java.util.concurrent.TimeUnit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import logicClasses.TDGame;

public class OptionScreen implements Screen {
	final TowerDefence game;
	private OrthographicCamera camera;
	private Stage stage;
	private Sound buttonClickSnd;
	private Texture bckgndImage;
	private Texture logoImage;
	private Label volumeValue;
	private Viewport viewport;
	private Label soundLabel;

	public OptionScreen(final TowerDefence game, final TDGame gamestate) {
		viewport = new FitViewport(TowerDefence.width, TowerDefence.height);
		
		
		this.game = game;

		this.camera = new OrthographicCamera();
		// Depends on the resolution we choose
		camera.setToOrtho(false, TowerDefence.width, TowerDefence.height);

		this.stage = new Stage();

		this.buttonClickSnd = Gdx.audio.newSound(Gdx.files.internal("sound/MenuSelectSound.ogg"));

		// Background image
		this.bckgndImage = new Texture(Gdx.files.internal("image/optionBckgnd.jpg"));
		this.logoImage = new Texture(Gdx.files.internal("image/logo.png"));

		// Label for showing slider value
		volumeValue = new Label("" + (int) (TowerDefence.VOLUME * 100), TowerDefence.uiSkin);
		volumeValue.setX(volumeValue.getX() + 10);
		volumeValue.setFontScale(2f);

		// Setup volume slider
		final Slider volumeSlider = new Slider(0f, 1f, 0.1f, false, TowerDefence.uiSkin);
		volumeSlider.setValue(TowerDefence.VOLUME);
		volumeSlider.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor act) {
				TowerDefence.VOLUME = volumeSlider.getValue();
				double val = Math.round(volumeSlider.getValue() * 100);
				volumeValue.setText("" + (int) val);
				game.menuMusic.setVolume(TowerDefence.VOLUME);

			}
		});

		// Setup buttons
		TextButton backButton = new TextButton("    Back    ", TowerDefence.uiSkin);
		backButton.addListener(new ClickListener() {
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
				soundLabel = new Label("Sound: ", TowerDefence.uiSkin);
				soundLabel.getStyle().fontColor = Color.WHITE;
				soundLabel.setFontScale(2f);

				game.setScreen(new MainMenuScreen(game));

				dispose();
			}
		});

		TextButton quitButton = new TextButton("    Quit    ", TowerDefence.uiSkin);
		quitButton.addListener(new ClickListener() {
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
				Gdx.app.exit();
				dispose();
			}
		});
		
		
		Label.LabelStyle titleFont = new Label.LabelStyle(TowerDefence.uiSkin.getFont("title"), Color.WHITE);
		Label gameOverLabel = new Label("OPTION", titleFont);
		gameOverLabel.setX(TowerDefence.width / 3 + 40);
		gameOverLabel.setY( TowerDefence.height - 100);
		stage.addActor(gameOverLabel);

		Table table = new Table();
		Table table2 = new Table();
		table.setX(Gdx.graphics.getWidth() / 2);
		table.setY(Gdx.graphics.getHeight() / 2);
		table2.setX(Gdx.graphics.getWidth() / 2);
		table2.setY(0);
		quitButton.setTransform(true);
		backButton.setTransform(true);
		quitButton.setScale(1f, 2f);
		backButton.setScale(1f, 2f);
		// Setup sound value row
		soundLabel = new Label("Sound: ", TowerDefence.uiSkin);
		soundLabel.getStyle().fontColor = Color.BLACK;
		soundLabel.setFontScale(2f);
		table.add(soundLabel);
		table.add(volumeSlider);
		table.add(volumeValue).pad(30f);
		// Setup row for back button
		table2.add(backButton);
		table2.add(quitButton);

		// Add table to the stage
		stage.addActor(table);
		stage.addActor(table2);
		// Start listening
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
		game.batch.draw(bckgndImage, 0, 0);
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
		logoImage.dispose();
	}
}
