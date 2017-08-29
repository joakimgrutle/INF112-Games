package com.oblig4.spooks;

import java.util.concurrent.TimeUnit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import rooms.Basement;

/**
 * Main menu class for Spooks
 * 
 * @author Emilia
 *
 */
public class MainMenuScreen implements Screen {
	final Spooks game;
	private OrthographicCamera camera;
	private Stage stage;
	private Sound buttonClickSnd;
	private Texture bckgndImage;
	private Texture logo;
	private Viewport viewport;
	Music menuMusic;
	public ButtonOwnStyle btnStyle;

	public MainMenuScreen(final Spooks game) {
		viewport = new FitViewport(Spooks.width, Spooks.height);
		this.game = game;
		this.stage = new Stage();

		TextureRegionDrawable drawableUp = new TextureRegionDrawable(Spooks.mainButtonsAtals.findRegion("btnNorm"));
		TextureRegionDrawable drawableDown = new TextureRegionDrawable(Spooks.mainButtonsAtals.findRegion("btnPress"));
		TextureRegionDrawable drawableChecked = new TextureRegionDrawable(Spooks.mainButtonsAtals.findRegion("btnNorm"));
		TextureRegionDrawable drawableOver = new TextureRegionDrawable(Spooks.mainButtonsAtals.findRegion("btnOv"));

		btnStyle = new ButtonOwnStyle(drawableUp, drawableDown, drawableChecked, drawableOver);
		btnStyle.font = Spooks.font;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, Spooks.width, Spooks.height);
		
		this.bckgndImage = new Texture(Gdx.files.internal("images/MainScreen.png"));
		logo = new Texture(Gdx.files.internal("images/SpooksLogo.png"));
		
		this.buttonClickSnd = Gdx.audio.newSound(Gdx.files.internal("sound/MenuSelectSound.wav"));

		// Setup buttons
		TextButton startButton = new TextButton("New Game", btnStyle);
		TextButton quitButton = new TextButton("Quit", btnStyle);
		TextButton optionButton = new TextButton("Options", btnStyle);

		// Setup listeners for the buttons
		startButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				buttonClickSnd.play();
				// Wait for sound to play
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// Set new screen
				game.setScreen(new Basement(game, null, null, null));
				Spooks.manager.get("sound/rain.wav", Music.class).play();
				Spooks.manager.get("sound/rain.wav", Music.class).setPan(0.8f, 0.2f);
				dispose();
			}
		});
		
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
			    game.setScreen(new OptionScreen(game));
				dispose();
			}
		});
		

		// Add buttons to the stage
		Table table = new Table();
		table.setX(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth()/2));
		table.setY(Gdx.graphics.getHeight() - (Gdx.graphics.getHeight()/2));
		table.add(startButton);
		table.row();
		table.add(optionButton);
		table.row();
		table.add(quitButton);
		stage.addActor(table);
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

		game.batch.begin();
		game.batch.draw(bckgndImage, 0, 0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		game.batch.draw(logo, Gdx.graphics.getWidth() / 4 - 50, Gdx.graphics.getHeight() - 200);
		game.batch.end();
	
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
		logo.dispose();
	}
}
