package mobs;

import java.awt.Point;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;

import logicClasses.Mob;

public class DudeBroMob extends Mob {

	public DudeBroMob(int hitpoints, int damage, float speed, int gold) {
		super(new Sprite(), "dudebro", hitpoints, damage, speed, gold);
	}

}
