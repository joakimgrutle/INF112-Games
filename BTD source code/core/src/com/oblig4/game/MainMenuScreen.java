package com.oblig4.game;

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

/**
 * Class for Main-Menu
 * 
 * @author Oyvind
 *
 */
public class MainMenuScreen implements Screen {
	final TowerDefence game;
	private OrthographicCamera camera;
	private Stage stage;
	private Sound buttonClickSnd;
	private Texture bckgndImage;
	private Texture logo;
	
	private Viewport viewport;

	/**
	 * Screen does not have a create method so we use a constructor instead
	 * 
	 * @param game
	 *            - Towerdefence-object created by DesktopLauncher.java
	 */
	public MainMenuScreen(final TowerDefence game) {
		viewport = new FitViewport(TowerDefence.width,TowerDefence.height);
		this.game = game;
		this.stage = new Stage();

		camera = new OrthographicCamera();
		// Depends on the resolution we choose
		camera.setToOrtho(false, TowerDefence.width, TowerDefence.height);

		// Image
		bckgndImage = new Texture(Gdx.files.internal("image/bckgndImage.jpg"));
		buttonClickSnd = Gdx.audio.newSound(Gdx.files.internal("sound/MenuSelectSound.ogg"));

		// Setup buttons
		TextButton startButton = new TextButton("    Start Game    ", TowerDefence.uiSkin);
		TextButton quitButton = new TextButton("         Quit         ", TowerDefence.uiSkin);
		TextButton optionButton = new TextButton("       Options      ", TowerDefence.uiSkin);
		startButton.setTransform(true);
		quitButton.setTransform(true);
		optionButton.setTransform(true);
		startButton.setScale(1f, 2f);
		quitButton.setScale(1f, 2f);
		optionButton.setScale(1f, 2f);

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
				game.setScreen(new GameScreen(game, null));
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
				game.setScreen(new OptionScreen(game,null));
				dispose();
			}
		});

		// Add buttons to the stage
		Table table = new Table();
		table.setX(Gdx.graphics.getWidth()/2);
		table.setY(0);
		table.add(startButton);
		startButton.moveBy(0, 100);
		table.add(optionButton);
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
		game.batch.draw(bckgndImage, 0, 0);
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
	}

}
