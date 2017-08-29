package com.oblig4.spooks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import logicClasses.KeyItem;
import logicClasses.SpooksGame;
import rooms.Livingroom;


public class JumpScarePuzzleScreen implements Screen {

	private Texture gameImage;
	private Pixmap pix;
	private Color mouseColor;
	private Spooks game;
	private Stage stage, stageTwo;
	private SpooksGame gamestate;
	private Hud hud;
	private StateSpooks state;
	private OrthographicCamera camera;
	private Viewport viewport;

	public JumpScarePuzzleScreen(Spooks games, Stage stages, Hud huds, SpooksGame gameState) {
	
		stage = new Stage();
		stageTwo = stages;
		this.game = games;
		this.hud = huds;
		this.gamestate = gameState;
		gameImage = new Texture("puzzles\\JumpScarePicture.png");
		gameImage.getTextureData().prepare();
		pix = gameImage.getTextureData().consumePixmap();
		
		this.camera = new OrthographicCamera();
		this.viewport = new FitViewport(Spooks.width, Spooks.height, camera);
		this.viewport = new FillViewport(Spooks.width, Spooks.height);
		
		
		state = StateSpooks.NotRun;
	}

	@Override
	
	public void show() {
		// TODO Auto-generated method stub

	}

	private void waitForStart() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		game.batch.begin();
		game.batch.draw(gameImage, 0, 0, Spooks.width, Spooks.height);
		game.batch.end();

		stage.clear();
		Label.LabelStyle font = new Label.LabelStyle(Spooks.skin.getFont("title"), Color.BLACK);
		Label startText = new Label("Trykk her for å starte", font);
		startText.setPosition(Spooks.width / 2 - 225, 20);
		startText.setFontScale(0.5f);
		stage.addActor(startText);
		stage.act();
		stage.draw();

		if (Gdx.input.isTouched() && Gdx.input.getX() >= ((Spooks.width / 2) - 225) &&
				(Gdx.input.getX() <= Spooks.width / 2) && Gdx.graphics.getHeight() - Gdx.input.getY() >= 20 &&
				Gdx.graphics.getHeight() - Gdx.input.getY() <= 85) {
			state = StateSpooks.Run;
		}
		
	}

	@Override
	public void render(float delta) {
		switch (state) {
		case Run:
			renderGame(delta);
			break;
		case NotRun:
			waitForStart();
			break;
		}
	}

	public void renderGame(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.clear();
		mouseColor = new Color(pix.getPixel(Gdx.input.getX(), Gdx.input.getY()));
		game.batch.begin();
		game.batch.draw(gameImage, 0, 0, Spooks.width, Spooks.height);
			
		game.batch.end();

		if (!mouseColor.equals(Color.WHITE) && !mouseColor.equals(Color.BLACK))
			game.setScreen(new ScaryFace(game, stageTwo, hud, gamestate,gameImage));
		
		if (mouseColor.equals(Color.BLACK) && !SpooksGame.getInstance().isSolvedMazePuzzle()){
			hud.addItemToHud(KeyItem.createKeyItem("earring"));
			SpooksGame.getInstance().setSolvedMazePuzzle(true);
			game.setScreen(new Livingroom(game, stageTwo, hud, gamestate));
		}
		
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
		gameImage.dispose();
		pix.dispose();
		stage.dispose();

	}

}
