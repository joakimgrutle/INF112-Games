package com.oblig4.spooks;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import logicClasses.KeyItem;
import logicClasses.SpooksGame;

public class KeyItemListing extends Group {

	private KeyItem item;

	public KeyItemListing(ImageButton imageButton, final KeyItem item) {
		super();
		this.item = item;
		// scale and add to actor
		imageButton.setTransform(true);
		imageButton.setPosition(getX(), getY() + 5);
		imageButton.setScale(0.4f);
		
		if(item.getItemName() == "locket") {
			imageButton.setScale(0.8f);
			imageButton.setPosition(getX(), getY() + 10);
		}
		
		if(item.getItemName() == "teddybear") {
			imageButton.setPosition(getX(), getY());
		}

		addActor(imageButton);
		
		
		imageButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {

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

	public KeyItem getItem() {
		return item;
	}
}
