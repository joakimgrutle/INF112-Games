package com.oblig4.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import logicClasses.TDGame;

public class Hud implements Disposable {

	public Stage stage;
	public SpriteBatch spriteBatch;
	public Texture hudArea;
	protected Viewport viewport;

	// score && time tracking variables
	private float timeCount;
	private boolean timeUp;

	private TDGame gameState;
	private TowerGUI towerGUI;

	// Scene2D Widgets
	private Label countdownLabel, linkLabel, moneyLabel, livesLabel, oldScoreLabel, moneyText, livesText, waveText, waveNumb;
	private static Label scoreLabel;
	private TextButton newWaveButton;
	private boolean waveButtonDrawn = false;
	public boolean buildTower;
	public String towerName;
	private OrthographicCamera camera;

	public Hud(final TowerDefence game, TDGame gameState) {
		// define tracking variables
		timeCount = 0; // new
		buildTower = false;
		towerName = "";

		// setup the HUD viewport using a new camera seperate from gamecam
		// define stage using that viewport and games spritebatch
		viewport = new FitViewport(TowerDefence.width, TowerDefence.height);
		this.camera = new OrthographicCamera();
		// Depends on the resolution we choose
		camera.setToOrtho(false, TowerDefence.width, TowerDefence.height);
		stage = new Stage();
		this.gameState = gameState;

		// define labels using the String, and a Label style consisting of a
		// font and color
		scoreLabel = new Label(String.format("%03d", TDGame.getInstance().getScore()), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		linkLabel = new Label("POINTS", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

		moneyText = new Label("MONEY", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		moneyLabel = new Label(String.format("%03d", TDGame.getInstance().getMoneyAmount()),
				new Label.LabelStyle(new BitmapFont(), Color.WHITE));

		livesText = new Label("LIVES", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		livesLabel = new Label(String.format("%03d", TDGame.getInstance().getPlayerLives()),
				new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		
		waveText = new Label("WAVE", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		waveNumb = new Label(String.format("%01d", TDGame.getInstance().getLevel()),
				new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		
		// Create Tower buttons
		this.towerGUI = new TowerGUI(this.gameState, this);
		towerGUI.createTowerButtons();

		// define a table used to organize hud's labels
		Table table = new Table();
		table.right().top();
		table.setFillParent(true);

		// add labels to table, padding the top, and giving them all equal width
		// with expandX
		table.add(linkLabel).padRight(10).padTop(10); // "POINTS"
		table.add(scoreLabel).padLeft(20).padTop(10).padRight(12.5f); // ACTUAL
																	// SCORE
		table.row();
		table.add(moneyText).padRight(10);
		table.add(moneyLabel).padLeft(1);
		table.row();
		table.add(livesText).padRight(21);
		table.add(livesLabel).padLeft(9);
		table.row();
		table.add(waveText).padRight(17.5f);
		table.add(waveNumb).padLeft(24.8f);

		// add table to the stage
		stage.addActor(table);

		// HUD Image Experimental
		hudArea = new Texture(Gdx.files.internal("image/hudtest2.png"));
		spriteBatch = new SpriteBatch();

	}
	

	public void buyTower() {
		towerGUI.buyTower();
	}
 
	public void update(float dt) {
		timeCount += dt;
		if (timeCount >= 1) {
			scoreLabel.setText(String.format("%03d", TDGame.getInstance().getScore()));
			moneyLabel.setText(String.format("%03d", TDGame.getInstance().getMoneyAmount()));
			livesLabel.setText(String.format("%03d", TDGame.getInstance().getPlayerLives()));
			waveNumb.setText(String.format("%01d", TDGame.getInstance().getLevel()));
			timeCount = 0;
		}
	}


	public void drawNewWaveButton(final TDGame game) {
		if (game.roundHasStarted()) {
			Gdx.input.setInputProcessor(stage);
			return;
	}
		if (!waveButtonDrawn) {
			newWaveButton = new TextButton("Start wave", TowerDefence.uiSkin);
			newWaveButton.setPosition(viewport.getWorldWidth() - 155, -15);
			newWaveButton.setTransform(true);
			newWaveButton.setScale(0.7f,1.7f);
			newWaveButton.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					game.startNewRound();
					newWaveButton.remove();
					waveButtonDrawn = false;
				}
			});
			stage.addActor(newWaveButton);
			Gdx.input.setInputProcessor(stage);
			waveButtonDrawn = true;
		}
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

	public boolean isTimeUp() {
		return timeUp;
	}


	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);

	}
}
