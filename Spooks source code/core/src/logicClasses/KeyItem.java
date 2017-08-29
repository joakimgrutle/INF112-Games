package logicClasses;

import java.util.ArrayList;
import java.util.Arrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oblig4.spooks.Spooks;

import interfaces.IKeyItem;
import keyItems.Earring;
import keyItems.Glove;
import keyItems.Locket;
import keyItems.TeddyBear;

public abstract class KeyItem extends Item implements IKeyItem {

	private ArrayList<String> compatibleItems = new ArrayList<String>(Arrays.asList("candlelight"));
	private boolean destroyed = false;
	private Sprite destroyedSprite;

	public KeyItem(String itemName, Sprite retrievedSprite, Sprite destroyedSprite) {
		super(retrievedSprite);
		this.setName(itemName);
		this.destroyedSprite = destroyedSprite;
		this.setKeyItem(true);
		this.setCompatibleList(compatibleItems);
		
	}

	public static KeyItem createKeyItem(String e) {
		
		String item = e.toLowerCase();

		switch (item) {
		case "teddybear":
			return new TeddyBear("teddybear", new Sprite(new Texture(Gdx.files.internal("images/teddybear.png"))),
					new Sprite(new Texture(Gdx.files.internal("images/omrisse_teddybear.png"))));
		case "glove":
			return new Glove("glove", new Sprite(new Texture(Gdx.files.internal("images/glove.png"))),
					new Sprite(new Texture(Gdx.files.internal("images/omrisse_glove.png"))));
		case "locket":
			return new Locket("locket", new Sprite(new Texture(Gdx.files.internal("images/locket.png"))),
					new Sprite(new Texture(Gdx.files.internal("images/omrisse_locket.png"))));
		case "earring":
			return new Earring("earring", new Sprite(new Texture(Gdx.files.internal("images/earring.png"))),
					new Sprite(new Texture(Gdx.files.internal("images/omrisse_earring.png"))));

		default:
			return null;

		}
	}



	@Override
	public boolean isDestroyed() {
		return destroyed;
	}



	@Override
	public void draw(SpriteBatch spriteBatch) {
		sprite.setX(position.x);
		sprite.setY(position.y);
		sprite.draw(spriteBatch);
	}
	public Sprite getDestroyedSprite(){
		return destroyedSprite;
	}

	@Override
	public void destroyKeyItem() {

		Spooks.manager.get("sound/burn.wav", Sound.class).play();
			this.destroyed = true;
		}
	}



