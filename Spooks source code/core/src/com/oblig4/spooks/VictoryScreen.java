package com.oblig4.spooks;

import java.util.concurrent.TimeUnit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import logicClasses.SpooksGame;
public class VictoryScreen implements Screen {
	private SpooksGame gamestate;
	private Spooks game;
	private OrthographicCamera camera;
	private Stage stage;
	private Sound buttonClickSnd;
	private Texture bckgndImage;
	private Texture logoImage;
	private Label volumeValue;
	private Viewport viewport;
	public ButtonOwnStyle btnStyle;

	public VictoryScreen(final Spooks game, final SpooksGame gamestate, Texture bckgndImage) {
		
		viewport = new FitViewport(Spooks.width, Spooks.height);
		this.gamestate = gamestate;
		this.game = game;
		this.camera = new OrthographicCamera();
		// Depends on the resolution we choose
		camera.setToOrtho(false, 800, 480);
		this.stage = new Stage();
		this.buttonClickSnd = Gdx.audio.newSound(Gdx.files.internal("sound/MenuSelectSound.wav"));
		// Background image
		this.bckgndImage = bckgndImage;
		TextureRegionDrawable drawableUp = new TextureRegionDrawable(Spooks.mainButtonsAtals.findRegion("btnNorm"));
		TextureRegionDrawable drawableDown = new TextureRegionDrawable(Spooks.mainButtonsAtals.findRegion("btnPress"));
		TextureRegionDrawable drawableChecked = new TextureRegionDrawable(Spooks.mainButtonsAtals.findRegion("btnNorm"));
		TextureRegionDrawable drawableOver = new TextureRegionDrawable(Spooks.mainButtonsAtals.findRegion("btnOv"));

		btnStyle = new ButtonOwnStyle(drawableUp, drawableDown, drawableChecked, drawableOver);
		btnStyle.font = Spooks.font;

		// Setup buttons
		TextButton resumeButton = new TextButton(" Main Menu ", btnStyle);
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
			}
		});
		
		
		// Game over and Score
	Label victoryLabel = new Label("Victory Achieved!\nYou escaped the house", new Label.LabelStyle(new BitmapFont(), Color.YELLOW));
	
		Table table = new Table();
		Table table2 = new Table();
		table.setX(Gdx.graphics.getWidth() / 2);
		table.setY(Gdx.graphics.getHeight() / 2);
		table2.setX(Gdx.graphics.getWidth() / 2);
		table2.setY(100);
		victoryLabel.setFontScale(3);
		resumeButton.setTransform(true);
		// Setup sound value row
		// Setup row for back button
		table2.add(resumeButton);
		table.add(victoryLabel);
	
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
		logoImage.dispose();
		bckgndImage.dispose();
	}
}