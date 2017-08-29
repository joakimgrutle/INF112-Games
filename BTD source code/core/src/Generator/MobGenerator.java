package Generator;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.List;

import interfaces.IMob;
import logicClasses.Mob;
import mobs.BimboMob;
import mobs.BossMob;
import mobs.DudeBroMob;
import mobs.HoboMob;
import mobs.StudentMob;


public class MobGenerator {
	
	public static Mob randMob() {
		// Basically the same as createMob() in Mob.java 
		// Except waypoints are given and type is random
		int num = IntGenerator.randValues(1, 100);
		Mob mob = null;
		int lvl = 0;
		lvl = (num - (5 * ((num - 1) / 5)));

		int hitpoints;
		int gold;
		float speed;
		int damage;
		switch (lvl) {
		case 1:
			hitpoints = 1 * num;
			gold = 7 + (2 * num);
			speed = 2 + (num / 5);
			damage = 1 + num;
			mob = new BimboMob(hitpoints, damage, speed, gold);
			return mob;
		case 2:
			hitpoints = 1 * num;
			gold = 8 + (2 * num);
			speed = 1.5f + (num / 5);
			damage = 1 + num;
			mob = new DudeBroMob(hitpoints, damage, speed, gold);
			return mob;
		case 3:
			hitpoints = 1 * num;
			gold = 9 + (2 * num);
			speed = 2 + (num / 5);
			damage = 1 + (num);
			mob = new StudentMob(hitpoints, damage, speed, gold);
			return mob;
		case 4:
			hitpoints = 2 * num;
			gold = 10 + (2 * num);
			speed = 2 + (num / 5);
			damage = 1 + num;
			mob = new HoboMob(hitpoints, damage, speed, gold);
			return mob;
		case 5:
			hitpoints = 10 * num;
			gold = 50 + (3 * num);
			speed = 1.5f + (num / 5);
			damage = 10 + (num);
			mob = new BossMob(hitpoints, damage, speed, gold);
			return mob;
		default:
			return null;

		}

	}

}
