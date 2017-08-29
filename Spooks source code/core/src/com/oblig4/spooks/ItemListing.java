package com.oblig4.spooks;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import items.Candle;
import items.Hand;
import items.Lighter;
import items.PumpkinCarvedRemote;
import items.Screw;
import logicClasses.Item;
import logicClasses.SpooksGame;

public class ItemListing extends Group {

	private Item item;
	private final SpooksGame gameState;

	public ItemListing(ImageButton imageButton, final Item item) {
		super();
		this.item = item;
		this.gameState = SpooksGame.getInstance();
		// scale and add to actor
		imageButton.setTransform(true);
		imageButton.setPosition(getX(), getY());
		imageButton.setScale(0.7f);
		if (item instanceof Screw)
			imageButton.setScale(3);
		if (item instanceof Candle) {
			imageButton.setScale(1.5f);
			item.getSprite().rotate(-90);
		}
		if (item instanceof Hand)
			imageButton.setScale(0.1f);
		if (item instanceof Lighter)
			imageButton.setScale(1.4f);

		addActor(imageButton);

		imageButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (item instanceof PumpkinCarvedRemote) {
					int counter = 0;
					for (ItemListing item : SpooksGame.getInstance().getHud().getInventory().inv) {
						if (item.getItem().getItemName() == "pumpkincarvedremote") {
							break;
						}
						counter++;
					}
					SpooksGame.getInstance().getHud().getInventory().inv.remove(counter);
					SpooksGame.getInstance().getHud().getInventory().addItem(Item.createItem("remotecontrol"));
					SpooksGame.getInstance().getHud().getInventory().addItem(Item.createItem("pumpkincarved"));
				} else {

					if (SpooksGame.getInstance().getCurrentItem() != null) {
						if (SpooksGame.getInstance().getCurrentItem().canCombineTwoItems(item)) {
							SpooksGame.getInstance().combineItem(item);
							SpooksGame.getInstance().setGhostItem(null);
						} else {
							SpooksGame.getInstance().currentItemReset();
							SpooksGame.getInstance().setGhostItem(null);
						}
					} else {
						SpooksGame.getInstance().setCurrentItem(item);

						if (item instanceof Hand)
							item.getSprite().setScale(0.1f, 0.11f);
						SpooksGame.getInstance().setGhostItem(item);
					}
				}
			}

		});

		imageButton.addListener(new InputListener() {
			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
				showItemWindow();
			}

			@Override
			public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
				ItemInfoWindow.removeactor();
			}

			private void showItemWindow() {
				item.infoWindow(SpooksGame.getInstance().getHud());
			}
		});

	}

	public Item getItem() {
		return item;
	}

}
