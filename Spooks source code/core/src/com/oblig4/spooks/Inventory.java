package com.oblig4.spooks;

import java.util.ArrayList;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import interfaces.IInventory;
import logicClasses.Item;
import logicClasses.KeyItem;

public class Inventory implements IInventory {

	private ImageButton itemButton;
	private int fstFree;
	public ArrayList<ItemListing> inv;
	public ArrayList<KeyItemListing> keyInv;
	private final int max;
	private final int maxKey;
	private Stage stage;
	private Table table;
	private Table table2;

	public Inventory(Stage s) {
		inv = new ArrayList<>();
		keyInv = new ArrayList<>();
		table = new Table();
		table2 = new Table();
		this.stage = s;
		this.fstFree = 0;
		this.max = 10;
		this.maxKey = 4;
		reDraw();
	}

	@Override
	public boolean hasItem(String itemName) {
		for (ItemListing e : inv) {
			if (e.getItem().getItemName() == itemName){
				return true;
			}
		}
		return false;
	}
	
	public boolean isKeyItemBurned(String itemName) {
		for (KeyItemListing e : keyInv) {
			if (e.getItem().getItemName() == itemName){
				return e.getItem().isDestroyed();
			}
		}
		return false;
	}
	

	@Override
	public void removeUsedItems() {
		ArrayList<ItemListing> item = new ArrayList<>();
		for (ItemListing e : inv)
			if (e.getItem().getUsedStatus()) {
				item.add(e);
				reDraw();
			}
		if (!item.isEmpty())
			for (ItemListing e : item)
				inv.remove(e);
	}

	@Override
	public void addItem(Item item) {
		if (!item.getKeyItemStatus()) {
			if (inv.size() >= max)
				return;
			Texture itemTexture = item.getSprite().getTexture();
			TextureRegion itemRegion = new TextureRegion(itemTexture);
			TextureRegionDrawable itemRegionDraw = new TextureRegionDrawable(itemRegion);
			itemButton = new ImageButton(itemRegionDraw);
			ItemListing itemListing = new ItemListing(itemButton, item);
			inv.add(itemListing);
			Spooks.manager.get("sound/addItemSound.wav", Sound.class).play();

			findFstFree();
			reDraw();
		} else {
			if (keyInv.size() >= maxKey)
				return;

			Texture itemTexture = item.getSprite().getTexture();
			TextureRegion itemRegion = new TextureRegion(itemTexture);
			TextureRegionDrawable itemRegionDraw = new TextureRegionDrawable(itemRegion);
			itemButton = new ImageButton(itemRegionDraw);
			KeyItemListing itemListing = new KeyItemListing(itemButton, (KeyItem) item);
			keyInv.add(itemListing);

			findFstFree();
			reDraw();
		}

	}

	private void findFstFree() {
		int cnt = 0;
		for (ItemListing i : inv) {
			if (i == null) {
				fstFree = cnt;
			}
		}

	}

	public void updateImage(KeyItem item) {
		Texture itemTexture = item.getSprite().getTexture();
		TextureRegion itemRegion = new TextureRegion(itemTexture);
		TextureRegionDrawable itemRegionDraw = new TextureRegionDrawable(itemRegion);
		itemButton = new ImageButton(itemRegionDraw);
		KeyItemListing itemListing = new KeyItemListing(itemButton, item);
		int cnt = 0;
		for (KeyItemListing k : keyInv) {
			if (k.getItem().getItemName() == item.getItemName()) {
				break;
			}
			cnt++;
		}

		keyInv.remove(cnt);
		keyInv.add(itemListing);
		reDraw();
	}

	public void reDraw() {
		table.remove();
		table2.remove();

		table = new Table();
		table.setFillParent(true);
		table.left().bottom();
		table.defaults().space(113).fill();
		table.setPosition(20, 10);
		// Add Items to table
		for (ItemListing item : inv) {
			table.add(item);
		}

		table2 = new Table();
		table2.setFillParent(true);
		table2.right().bottom().right();
		table2.moveBy(-70, 5);

		int cnt = 1;
		for (KeyItemListing item : keyInv) {
			if (cnt == 3)
				table2.row().space(60);

			if (cnt > 2)
				table2.add(item).spaceRight(65);
			else
				table2.add(item).spaceRight(65);
			cnt++;
		}

		stage.addActor(table);
		stage.addActor(table2);

	}

}
