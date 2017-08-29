package logicClasses;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.oblig4.spooks.Hud;
import com.oblig4.spooks.Inventory;
import com.oblig4.spooks.Spooks;

import interfaces.ISpooksGame;

public class SpooksGame implements ISpooksGame {

	// Time logic
	private long startTime;
	private long timePaused;
	private long pausedBuffer;
	private long timeBeforeGameEnds;
	private static SpooksGame instance;
	private Item ghost;
	private Item currItem;
	private Hud hud;
	private boolean firstTimeBasement;
	private Inventory inventory;
	private Screen currentRoom;
	private ArrayList<Item> basementItemList;
	private ArrayList<Item> atticItemList;
	private ArrayList<Item> bathroomItemList;
	private ArrayList<Item> kitchenItemList;
	private ArrayList<Item> livingroomItemList;
	private boolean solvedMazePuzzle;
	private boolean solvedColorPuzzle;
	private boolean solvedPinPuzzle;
	public boolean isBasementOpened;
	public boolean hasGlove;

	private boolean timerStopped;
	private Spooks game;
	private float shaderIndex;
	// Puzzles
	private PaintedButtonPuzzle buttonPuzzle;

	public SpooksGame(Hud hud, Spooks game) {
		// puzzles
		buttonPuzzle = new PaintedButtonPuzzle();
		this.game = game;
		currItem = null;
		startTime = TimeUtils.millis();
		timerStopped = false;
		isBasementOpened = false;
		hasGlove=false;
		timeBeforeGameEnds = 60000; // 10 minutes
		instance = this;
		this.hud = hud;
		this.inventory = hud.getInventory();
		// TODO parameters
		this.basementItemList = new ArrayList<>();
		this.atticItemList = new ArrayList<>();
		this.bathroomItemList = new ArrayList<>();
		this.kitchenItemList = new ArrayList<>();
		this.livingroomItemList = new ArrayList<>();
		this.solvedColorPuzzle = false;
		this.solvedMazePuzzle = false;
		this.solvedPinPuzzle = false;
		this.firstTimeBasement = true;
		addAllRoomItems();
	}

	public float getShaderIndex() {
		return shaderIndex;
	}

	public boolean lightFade() {
		if (getCountDown() <= 6) {
			shaderIndex += 0.0001;
			return true;
		} else
			return false;
	}

		public boolean isFirstTimeBasement() {
		return firstTimeBasement;
	}

	public void setFirstTimeBasement(boolean firstTimeBasement) {
		this.firstTimeBasement = firstTimeBasement;
	}

	public boolean isSolvedMazePuzzle() {
		return solvedMazePuzzle;
	}

	public void setSolvedMazePuzzle(boolean solvedMazePuzzle) {
		this.solvedMazePuzzle = solvedMazePuzzle;
	}

	public boolean isSolvedColorPuzzle() {
		return solvedColorPuzzle;
	}

	public void setSolvedColorPuzzle(boolean solvedColorPuzzle) {
		this.solvedColorPuzzle = solvedColorPuzzle;
	}

	public boolean isSolvedPinPuzzle() {
		return solvedPinPuzzle;
	}

	public void setSolvedPinPuzzle(boolean solvedPinPuzzle) {
		this.solvedPinPuzzle = solvedPinPuzzle;
	}

	public ArrayList<Item> getBasementItemList() {
		return basementItemList;
	}

	public ArrayList<Item> getAtticItemList() {
		return atticItemList;
	}

	public ArrayList<Item> getBathroomItemList() {
		return bathroomItemList;
	}

	public ArrayList<Item> getLivingroomItemList() {
		return livingroomItemList;
	}

	public ArrayList<Item> getKitchenItemList() {
		return kitchenItemList;
	}

	@Override
	public long getElapsedTime() {
		if (timerStopped) {
			timePaused += TimeUtils.timeSinceMillis(pausedBuffer);
			pausedBuffer = TimeUtils.millis();
		}
		return (TimeUtils.timeSinceMillis(startTime) - timePaused);
	}

	public long getElapsedTimeSeconds() {
		if (timerStopped) {
			timePaused += TimeUtils.timeSinceMillis(pausedBuffer);
			pausedBuffer = TimeUtils.millis();
		}
		return (TimeUtils.timeSinceMillis(startTime) - timePaused) / 1000;
	}

	public long getCountDown() {
		if (timerStopped) {
			timePaused += TimeUtils.timeSinceMillis(pausedBuffer);
			pausedBuffer = TimeUtils.millis();
		}
		return 300 - (TimeUtils.timeSinceMillis(startTime) - timePaused) / 1000;
	}

	@Override
	public void startTimer() {
		if (timerStopped) {
			timePaused += TimeUtils.timeSinceMillis(pausedBuffer);
			pausedBuffer = 0;
			timerStopped = false;
		}
	}

	@Override
	public void setGhostItem(Item item) {
		this.ghost = item;
	}

	@Override
	public Item getGhostItem() {
		return this.ghost;
	}

	@Override
	public void stopTimer() {
		if (!timerStopped) {
			pausedBuffer = TimeUtils.millis();
			timerStopped = true;
		}
	}

	@Override
	public boolean isTimeOver() {
		if (getElapsedTime() >= timeBeforeGameEnds)
			return true;
		else
			return false;
	}

	public static SpooksGame getInstance() {
		return instance;
	}

	@Override
	public void addOneMinute() {
		timeBeforeGameEnds += 60000;
	}

	public long getTimeBeforeGameEnds() {
		return timeBeforeGameEnds;
	}

	// TODO Kanskje fjerne denne?
	public void setTimeBeforeGameEnds(long timeBeforeGameEnds) {
		this.timeBeforeGameEnds = timeBeforeGameEnds;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public boolean lostGame() {
		if (getCountDown() <= 0)
			return true;
		return false;
	}

	private void dispGhostItem(SpriteBatch spriteBatch) {
		spriteBatch.begin();
		if (this.getGhostItem() != null) {

			this.getGhostItem().setPosition(new Vector2(
					Gdx.input.getX() - this.getGhostItem().getSprite().getWidth() / 2,
					(Gdx.graphics.getHeight() - Gdx.input.getY() - this.getGhostItem().getSprite().getHeight() / 2)));
			this.getGhostItem().draw(spriteBatch);
		}
		spriteBatch.end();
	}

	@Override
	public void update(float dt) {
	}

	@Override
	public void render(SpriteBatch spriteBatch) {
		dispGhostItem(spriteBatch);
	}

	@Override
	public void setCurrentItem(Item item) {
		this.currItem = item;
	}

	public void currentItemReset() {
		this.currItem = null;
	}

	@Override
	public Item getCurrentItem() {
		return this.currItem;
	}

	public Hud getHud() {
		return hud;
	}

	@Override
	public void combineItem(Item item) {
		ArrayList<Item> newItem = currItem.combineTwoItems(item);
		item.setUsedStatus(true);
		currItem.setUsedStatus(true);
		hud.getInventory().removeUsedItems();
		for (Item e : newItem)
			hud.getInventory().addItem(e);
		currItem = null;
	}

	// Add items to rooms
	private void addAllRoomItems() {
		addBasementItems();
		addAttickItems();
		addKitchenItems();
		addLivingroomItems();
		addBathroomItems();
	}

	private void addKitchenItems() {
		Item item = buttonPuzzle.getDescriptionItem();
		item.setPosition(new Vector2(450, 600));
		kitchenItemList.add(item);

		item = Item.createItem("knife");
		item.setPosition(new Vector2(900, 500));
		item.getSprite().setScale(0.5f);
		item.getSprite().rotate(-15);
		kitchenItemList.add(item);

	}

	// Add items to attic
	private void addAttickItems() {
		Item item = Item.createItem("pumpkin");
		item.setPosition(new Vector2(920, 250));
		atticItemList.add(item);
		
		item = KeyItem.createKeyItem("glove");
		item.setPosition(new Vector2((float)Gdx.graphics.getWidth() / 2 -90,(float) Gdx.graphics.getHeight()-90));
		item.getSprite().setScale(0.3f);
		atticItemList.add(item);
	}

	// Add items to basement
	private void addBasementItems() {

		Item item = Item.createItem("screwdriver");
		item.setPosition(new Vector2(1100, 200));
		item.getSprite().setScale(0.5f);
		basementItemList.add(item);

		item = Item.createItem("screw");
		item.setPosition(new Vector2(350, 200));
		basementItemList.add(item);
		
	}

	// Add items to living room
	private void addLivingroomItems() {
		Item item = Item.createItem("candle");
		item.setPosition(new Vector2(620, 345));
		item.getSprite().rotate(90);
		item.getSprite().setSize(32, 32);
		;
		livingroomItemList.add(item);

	}

	private void addBathroomItems() {
		Item item = Item.createItem("candlestick");
		item.setPosition(new Vector2(300, 200));
		item.getSprite().setSize(32, 32);
		bathroomItemList.add(item);

	}

	public PaintedButtonPuzzle getButtonPuzzle() {
		return buttonPuzzle;
	}
}
