package com.oblig4.spooks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.Viewport;

import logicClasses.Item;
import logicClasses.SpooksGame;

public class Hud implements Disposable {
	public Stage stage;
	public Viewport viewport;
	public Table table;
	public Texture hudArea;
	public SpriteBatch spriteBatch;

	private Label timeValue;

	Item ghostItem;
	private Inventory inv;
	private SpooksGame gameState;
	private boolean isTouched;

	public Hud(int width, int height, SpriteBatch sb, Stage s) {

		spriteBatch = sb;
		this.gameState = SpooksGame.getInstance();
		ghostItem = null;

		stage = s;

		timeValue = new Label(String.format("%d : %d", 5, 0), new Label.LabelStyle(new BitmapFont(), Color.RED));
		timeValue.setFontScale(2f);
		timeValue.setPosition(Gdx.graphics.getWidth() / 2, 700);

		stage.addActor(timeValue);

		hudArea = new Texture(Gdx.files.internal("images/hudSpooks.png"));


		// Inventory
		this.inv = new Inventory(stage);
	
	}

	public Inventory getInventory() {
		return inv;
	}

	public void addsTimerToHudSinceYouGuysClearsTheStageAllTheTime() {

		stage.addActor(timeValue);
	}

	public void updateTimer() {
		long time = SpooksGame.getInstance().getCountDown();
		long hours = Math.floorDiv(time, 60);
		long min = time % 60;

		timeValue.setText(String.format("%d : %d", hours, min));
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

	public void addItemToHud(Item item) {
		inv.addItem(item);
	}

}
