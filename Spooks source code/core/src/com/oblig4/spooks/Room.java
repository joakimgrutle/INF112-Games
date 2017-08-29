package com.oblig4.spooks;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import logicClasses.Item;

public class Room {

	String room;
	Texture roomImage;
	ArrayList<Actor> roomDirectionlist;
	ArrayList<Item> itemList;

	Room(String room, Texture roomImage) {
		this.room = room;
		this.roomImage = roomImage;
		roomDirectionlist = new ArrayList<Actor>();
		itemList = new ArrayList<Item>();
	}

	public String getRoomName() {
		return room;
	}

	public void addItem(Item item) {
		itemList.add(item);
	}

	public ArrayList<Actor> getDirectionList() {
		return roomDirectionlist;
	}

	public void addActor(Actor actor) {
		roomDirectionlist.add(actor);
	}

	public ArrayList<Item> getItemList() {
		return itemList;
	}

	public void removeItem(Item item) {
		itemList.remove(item);

	}
}
