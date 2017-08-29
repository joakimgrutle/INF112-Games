package com.oblig4.spooks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;

import items.Note;
import logicClasses.Item;

public class ItemInfoWindow extends Window {
	TextButton closeButton;
	public static boolean isOpen = false;
	public static ItemInfoWindow instance;

	public ItemInfoWindow(final Item item, Skin skin, final Hud hud, Vector2 pos, boolean anotherOne) {
		// Setup Window
		super("", skin);
		super.setSize(400, 200);
		if(item instanceof Note)
			super.setSize(600, 200);
		super.setPosition(0, Gdx.graphics.getHeight() - 200);

		// Setup info
		Table table = new Table();
		table.setFillParent(true);
		Label des = new Label(item.toString(), Spooks.skin);
		table.row();
		table.add(des);

		addActor(table);

		ItemInfoWindow.instance = this;
	}

	public static ItemInfoWindow getInstance() {
		return instance;
	}

	public static void removeactor() {
		isOpen = false;
		ItemInfoWindow.getInstance().remove();

	}

}
