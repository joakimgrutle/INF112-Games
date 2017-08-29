package mobs;

import java.awt.Point;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;

import logicClasses.Mob;

public class BimboMob extends Mob {

	public BimboMob(int hitpoints, int damage, float speed, int gold) {
		super(new Sprite(), "bimbo", hitpoints, damage, speed, gold);
	}
	
	public BimboMob(int hitpoints, int damage, float speed, int gold, String ekstra) {
		super(new Sprite(), "bimbo", hitpoints, damage, speed, gold, ekstra);
	}

}
