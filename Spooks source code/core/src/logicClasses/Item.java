package logicClasses;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.oblig4.spooks.Gamesprites;
import com.oblig4.spooks.Hud;
import com.oblig4.spooks.ItemInfoWindow;
import com.oblig4.spooks.Spooks;

import interfaces.IItem;
import items.*;

public abstract class Item extends Gamesprites implements IItem {

	private String itemName;
	private ArrayList<String> compatibleItems = new ArrayList<>();

	private boolean used = false;
	private boolean pickable = true;
	private boolean consumeable = false;
	private boolean justCreated;
	private boolean isKeyItem = false;
	public ItemInfoWindow window;

	public Item(Sprite sprite) {
		super(sprite);
		this.justCreated = true;
	}

	@Override
	public void setPosition(Vector2 vec) {
		this.position = vec;
	}

	public static Item createItem(String it) {

		String item = it.toLowerCase();

		switch (item) {
		// Case for single item.
		case "boltcutter":
			return new BoltCutter(new Sprite(new Texture(Gdx.files.internal("images/boltesaks.png"))));
		case "brokenboltcutter":
			return new BrokenBoltCutter(new Sprite(new Texture(Gdx.files.internal("images/boltesaksUtenSkrue.png"))));
		case "boltcutterandscrew":
			return new BoltCutterAndScrew(new Sprite(new Texture(Gdx.files.internal("images/boltesaksMedSkrue.png"))));
		case "candle":
			return new Candle(new Sprite(new Texture(Gdx.files.internal("images/candle_extinguished.png"))));
		case "candlelight":
			return new Candlelight(new Sprite(new Texture(Gdx.files.internal("images/lysIStakeBrenner.png"))));
		case "candlestick":
			return new Candlestick(new Sprite(new Texture(Gdx.files.internal("images/lysestakeUtenLys.png"))));
		case "candlestickandcandle":
			return new CandlestickAndCandle(new Sprite(new Texture(Gdx.files.internal("images/lysIStakeSlukket.png"))));
		case "hand":
			return new Hand(new Sprite(new Texture(Gdx.files.internal("images/Hand.png"))));
		case "key":
			return new Key(new Sprite(new Texture(Gdx.files.internal("images/key.png"))));
		case "knife":
			return new Knife(new Sprite(new Texture(Gdx.files.internal("images/kniv_stor.png"))));
		case "lighter":
			return new Lighter(new Sprite(new Texture(Gdx.files.internal("images/lighter.png"))));
		case "pumpkin":
			return new Pumpkin(new Sprite(new Texture(Gdx.files.internal("images/gresskar.png"))));
		case "pumpkincarvedremote":
			return new PumpkinCarvedRemote(
					new Sprite(new Texture(Gdx.files.internal("images/gresskar_deltiito_fjernkontroll.png"))));
		case "pumpkincarved":
			return new PumpkinCarved(new Sprite(new Texture(Gdx.files.internal("images/gresskar_deltito.png"))));
		case "remotecontrol":
			return new RemoteControl(new Sprite(new Texture(Gdx.files.internal("images/fjernkontroll_inventory.png"))));
		case "screw":
			return new Screw(new Sprite(new Texture(Gdx.files.internal("images/skrue.png"))));
		case "screwdriver":
			return new Screwdriver(new Sprite(new Texture(Gdx.files.internal("images/skruetrekker_inventory.png"))));
		case "flashlight":
			return new Flashlight(new Sprite(new Texture(Gdx.files.internal("images/lommelykt.png"))));
			
			
		// Case for combinations
		case "screw" + "brokenboltcutter":
			return new BoltCutterAndScrew(new Sprite(new Texture(Gdx.files.internal("images/boltesaksMedSkrue.png"))));
		case "brokenboltcutter" + "screw":
			return new BoltCutterAndScrew(new Sprite(new Texture(Gdx.files.internal("images/boltesaksMedSkrue.png"))));

		case "boltcutterandscrew" + "screwdriver":
			Spooks.manager.get("sound/combine2.wav", Sound.class).play();
			return new BoltCutter(new Sprite(new Texture(Gdx.files.internal("images/boltesaks.png"))));
		case "screwdriver" + "boltcutterandscrew":
			Spooks.manager.get("sound/combine2.wav", Sound.class).play();
			return new BoltCutter(new Sprite(new Texture(Gdx.files.internal("images/boltesaks.png"))));

		case "knife" + "pumpkin":
			Spooks.manager.get("sound/cut.wav", Sound.class).play();
			return new PumpkinCarvedRemote(new Sprite(new Texture(Gdx.files.internal("images/gresskar_deltiito_fjernkontroll.png"))));
		case "pumpkin" + "knife":
			Spooks.manager.get("sound/cut.wav", Sound.class).play();
			return new PumpkinCarvedRemote(new Sprite(new Texture(Gdx.files.internal("images/gresskar_deltiito_fjernkontroll.png"))));

		case "candle" + "candlestick":
			return new CandlestickAndCandle(new Sprite(new Texture(Gdx.files.internal("images/lysIStakeSlukket.png"))));
		case "candlestick" + "candle":
			return new CandlestickAndCandle(new Sprite(new Texture(Gdx.files.internal("images/lysIStakeSlukket.png"))));

		case "candlestickandcandle" + "lighter":
			Spooks.manager.get("sound/burn.wav", Sound.class).play();
			return new Candlelight(new Sprite(new Texture(Gdx.files.internal("images/lysIStakeBrenner.png"))));
		case "lighter" + "candlestickandcandle":
			Spooks.manager.get("sound/burn.wav", Sound.class).play();
			return new Candlelight(new Sprite(new Texture(Gdx.files.internal("images/lysIStakeBrenner.png"))));

		default:
			return null;
		}
	}

	@Override
	public ArrayList<Item> combineTwoItems(Item item) {
		ArrayList<Item> ret = new ArrayList<>();
		if (item instanceof KeyItem){
			item.setSprite(((KeyItem) item).getDestroyedSprite());	
			((KeyItem) item).destroyKeyItem();
			SpooksGame.getInstance().getHud().getInventory().updateImage((KeyItem) item);
			ret.add(createItem("candlelight"));
			return ret;
		}
			
		this.used = true;
		item.used = true;
		Item A = createItem(this.itemName + item.getItemName());
		if (A == null)
			return ret;
		ret.add(A);
		
		
		if (A instanceof PumpkinCarved){
			ret.add(createItem("remotecontroller"));
			Collections.swap(ret, 0, 1);
		}
		else if (A instanceof BoltCutter){
			ret.add(createItem("screwdriver"));
			Collections.swap(ret, 0, 1);
		}
		
		
		return ret;
			
	}

	@Override
	public void draw(SpriteBatch spriteBatch) {
		sprite.setX(position.x);
		sprite.setY(position.y);
		sprite.draw(spriteBatch);
	}

	@Override
	public boolean canCombineTwoItems(Item item) {
		if (this.compatibleItems.isEmpty())
			return false;
		return this.compatibleItems.contains(item.getItemName());
	}

	@Override
	public List<String> getCompatibleList(Item item) {
		return compatibleItems;
	}

	protected void setName(String itemName) {
		this.itemName = itemName;
	}

	protected void setCompatibleList(ArrayList compatibleItems) {
		this.compatibleItems = compatibleItems;
	}

	@Override
	public String getItemName() {
		return itemName;
	}

	@Override
	public boolean getUsedStatus() {
		return used;
	}

	@Override
	public void setUsedStatus(boolean e) {
		this.used = e;
	}

	protected void setPickable(boolean e) {
		this.pickable = e;
	}

	@Override
	public boolean getPickableStatus() {
		return pickable;

	}

	@Override
	public void setConsumeable(boolean e) {
		this.consumeable = e;
	}

	@Override
	public boolean getConsumeableStatus() {
		return this.consumeable;
	}

	public void removeInfoWindow() {
		if (window != null)
			window.remove();

	}

	@Override
	public void infoWindow(Hud hud) {
		window = new ItemInfoWindow(this, Spooks.skin, hud, this.position, true);
		hud.stage.addActor(window);

	}
	protected void setKeyItem(boolean e){
		this.isKeyItem = e;
	}
	public boolean getKeyItemStatus(){
		return isKeyItem;
	}

}
