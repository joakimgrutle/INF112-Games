package mobs;

import java.awt.Point;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;

import logicClasses.Mob;

public class StudentMob extends Mob {

	public StudentMob(int hitpoints, int damage, float speed, int gold) {
		super(new Sprite(), "student", hitpoints, damage, speed, gold);
	}

}
