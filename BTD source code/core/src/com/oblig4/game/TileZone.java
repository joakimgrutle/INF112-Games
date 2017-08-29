package com.oblig4.game;

import com.badlogic.gdx.math.Rectangle;

public class TileZone extends Rectangle{
	private boolean vacantSpot; //Default is true, allow for incoming towers to be placed.

	public TileZone (float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.vacantSpot = true;
	}
	
	
	
}
