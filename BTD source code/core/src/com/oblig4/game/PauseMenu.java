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

public class PauseMenu implements Screen {
	
	private TDGame gamestate;
	private TowerDefence game;
	private OrthographicCamera camera;
	private Stage stage;
	private Sound buttonClickSnd;
	private Texture bckgndImage;
	private Texture logoImage;
	private Viewport viewport;

	public PauseMenu(final TowerDefence game, final TDGame gamestate) {
		viewport = new FitViewport(TowerDefence.width,TowerDefence.height);
		this.gamestate = gamestate;
		this.game = game;
		this.stage = new Stage();
		this.camera = new OrthographicCamera();
		// Depends on the resolution we choose
		camera.setToOrtho(false, TowerDefence.width, TowerDefence.height);
		
		this.buttonClickSnd = Gdx.audio.newSound(Gdx.files.internal("sound/MenuSelectSound.ogg"));
		// Background image
		this.bckgndImage = new Texture(Gdx.files.internal("image/optionBckgnd.jpg"));



		// Setup buttons
		TextButton resumeButton = new TextButton("    Resume    ", TowerDefence.uiSkin);
		TextButton optionButton = new TextButton("     Options     ", TowerDefence.uiSkin);
		TextButton mainMenuButton = new TextButton("   Main Menu   ", TowerDefence.uiSkin);
		mainMenuButton.setTransform(true);
		resumeButton.setTransform(true);
		optionButton.setTransform(true);
		mainMenuButton.setScale(1f,2f);
		resumeButton.setScale(1f,2f);
		optionButton.setScale(1f,2f);
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
				
				game.setScreen(new GameScreen(game, gamestate));
				dispose();
			}
		});
		
		
		optionButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				buttonClickSnd.play();
				// Wait for sound to play
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				game.setScreen(new OptionScreen2(game,gamestate));
				dispose();
			}
		});

		
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
				GameScreen.gameMusicSnd.stop();
				game.menuMusic.play();
				game.setScreen(new MainMenuScreen(game));
				dispose();
			}
		});
		
		
		Label.LabelStyle titleFont = new Label.LabelStyle(TowerDefence.uiSkin.getFont("title"), Color.WHITE);
		Label gameOverLabel = new Label("PAUSE", titleFont);
		gameOverLabel.setX(TowerDefence.width / 3 + 60);
		gameOverLabel.setY( TowerDefence.height - 100);
		stage.addActor(gameOverLabel);
		

		Table table2 = new Table();

		table2.setX(Gdx.graphics.getWidth() / 2);
		table2.setY(0);
		
		// Setup sound value row
		// Setup row for back button
		table2.add(resumeButton);
		table2.add(optionButton);
		table2.add(mainMenuButton);

		// Add table to the stage
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
	}
}
