package com.oblig4.spooks;

import java.util.concurrent.TimeUnit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import logicClasses.SpooksGame;
import rooms.Attic;
import rooms.Basement;
import rooms.Bathroom;
import rooms.Kitchen;
import rooms.Livingroom;

public class PauseMenu implements Screen {
	
	private SpooksGame gamestate;
	private Spooks game;
	private OrthographicCamera camera;
	private Stage stage;
	private Sound buttonClickSnd;
	private Texture bckgndImage;
	private Texture logoImage;
	private Viewport viewport;
	private MainMenuScreen menu;
	private Hud hud;

	public PauseMenu(Spooks games, final Stage stages, Hud huds,  SpooksGame gameState, final String screen) {
		viewport = new FitViewport(Spooks.width,Spooks.height);
		this.gamestate = gameState;
		gamestate.stopTimer();
		this.hud = huds;
		this.game = games;
		this.stage = new Stage();
		this.camera = new OrthographicCamera();
		// Depends on the resolution we choose
		camera.setToOrtho(false, Spooks.width, Spooks.height);
		
		this.buttonClickSnd = Gdx.audio.newSound(Gdx.files.internal("sound/MenuSelectSound.wav"));
		// Background image
		this.bckgndImage = new Texture(Gdx.files.internal("background/basement.png"));
		this.logoImage = new Texture(Gdx.files.internal("images/SpooksLogo.png"));
		
		menu = new MainMenuScreen(game);

		// Setup buttons
		TextButton resumeButton = new TextButton("    Resume    ", menu.btnStyle);
		TextButton optionButton = new TextButton("     Option     ", menu.btnStyle);
		TextButton mainMenuButton = new TextButton("   Main Menu   ", menu.btnStyle);
		menu.dispose();
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
				//GameScreen.gameMusicSnd.stop();
				switch (screen) {
					case "Attic":
						game.setScreen(new Attic(game, stages, hud, gamestate));
						break;
					case "Basement":
						game.setScreen(new Basement(game, stages, hud, gamestate));
						break;
					case "Bathroom":
						game.setScreen(new Bathroom(game, stages, hud, gamestate));
						break;
					case "Kitchen":
						game.setScreen(new Kitchen(game, stages, hud, gamestate));
						break;
					case "Livingroom":
						game.setScreen(new Livingroom(game, stages, hud, gamestate));
						break;
					default:
						game.setScreen(new Basement(game, stages, hud, gamestate));
						break;
				}
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
				game.setScreen(new OptionScreen2(game, stage, hud, gamestate, screen));
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
				game.setScreen(new MainMenuScreen(game));
				dispose();
			}
		});
		

		Table table2 = new Table();

		table2.setX(Gdx.graphics.getWidth() / 2);
		table2.setY(Gdx.graphics.getHeight() / 2);
		
		// Setup sound value row
		// Setup row for back button
		table2.add(resumeButton);
		table2.row();
		table2.add(optionButton);
		table2.row();
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
		game.batch.draw(bckgndImage, 0, 0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
		logoImage.dispose();
		bckgndImage.dispose();
	}
}
