package com.oblig4.spooks;

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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import logicClasses.SpooksGame;

public class OptionScreen2 implements Screen {
	final Spooks game;
	private SpooksGame gamestate;
	private OrthographicCamera camera;
	private Stage stage;
	private Sound buttonClickSnd;
	private Texture bckgndImage;
	private Texture logoImage;
	private Label volumeValue;
	private Viewport viewport;
	private Label soundLabel;
	private MainMenuScreen menu;
	private Skin uiSkin;
	
	public OptionScreen2(final Spooks games, Stage stages, final Hud hud, SpooksGame gameState, final String screen) {
		viewport = new FitViewport(Spooks.width,Spooks.height);
		this.gamestate = gameState;

		this.game = games;

		this.camera = new OrthographicCamera();
		// Depends on the resolution we choose
		camera.setToOrtho(false, Spooks.width, Spooks.height);

		this.stage = new Stage();
		
		menu = new MainMenuScreen(game);
		uiSkin = new Skin(Gdx.files.internal("skin/tracer-ui.json"));

		this.buttonClickSnd = Gdx.audio.newSound(Gdx.files.internal("sound/MenuSelectSound.wav"));
		
		// Background image
		this.bckgndImage = new Texture(Gdx.files.internal("background/basement.png"));
		this.logoImage = new Texture(Gdx.files.internal("images/SpooksLogo.png"));

		// Label for showing slider value
		volumeValue = new Label("" + (int) (Spooks.VOLUME * 100), uiSkin);
		volumeValue.setX(volumeValue.getX() + 10);
		volumeValue.setFontScale(2f);

		// Setup volume slider
		final Slider volumeSlider = new Slider(0.0f, 1.0f, 0.1f, false, uiSkin);
		volumeSlider.setValue(Spooks.VOLUME);
		volumeSlider.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor act) {
				Spooks.VOLUME = volumeSlider.getValue();
				double val = Math.round(volumeSlider.getValue() * 100 * 10 / 10.0);
				volumeValue.setText("" + val);
				game.menuMusic.setVolume(Spooks.VOLUME);
			}
		});

		// Setup buttons
		TextButton backButton = new TextButton("    Back    ", menu.btnStyle);
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
				soundLabel = new Label("Sound: ", uiSkin);
				soundLabel.getStyle().fontColor = Color.WHITE;
				soundLabel.setFontScale(2f);
				game.setScreen(new PauseMenu(game, stage, hud, gamestate, screen));
				dispose();
			}
		});

		TextButton mainMenuButton = new TextButton("  Main Menu  ", menu.btnStyle);
		mainMenuButton.addListener(new ClickListener() {
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
		
		Table table = new Table();
		Table table2 = new Table();
		table.setX(Gdx.graphics.getWidth() / 2);
		table.setY(Gdx.graphics.getHeight() / 2);
		table2.setX(Gdx.graphics.getWidth() / 2);
		table2.setY(Gdx.graphics.getHeight() / 4);
		// Setup sound value row
		soundLabel = new Label("Sound: ", uiSkin);
		soundLabel.getStyle().fontColor = Color.RED;
		soundLabel.setFontScale(2f);
		table.add(soundLabel);
		table.add(volumeSlider);
		table.add(volumeValue).pad(30f);
		// Setup row for back button
		table2.add(backButton);
		table2.row();
		table2.add(mainMenuButton);

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
		game.batch.draw(bckgndImage, 0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		game.batch.draw(logoImage, Gdx.graphics.getWidth() / 4 - 50, Gdx.graphics.getHeight() - 200);
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
