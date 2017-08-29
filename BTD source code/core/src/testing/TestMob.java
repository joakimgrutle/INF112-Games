package testing;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import Generator.IntGenerator;
import Generator.MobGenerator;
import logicClasses.Mob;
import logicClasses.Projectile;
import logicClasses.Tower;
import mobs.BimboMob;
import projectiletypes.BeerProjectile;
import towers.BeerTower;

public class TestMob {

	Mob mob;

	final float MOB_SPEED = 1;

	@Before
	public void init() {
		this.mob = new BimboMob(10, 1, MOB_SPEED, 10, "ekstra");
	}

	/*
	 * Tests mob.update(), aka the movement of mobs.
	 */
	@Test
	public void testChangeOfXPositionAfterCallToUpdate() {
		init();

		int numOfUpdates = 35;

		// With a startingpos of 0,y the mob should be at 10,y after ten calls
		// to update()
		Point expectedAfterTen = new Point(10, (int) mob.getPosition().y);
		// after 20 steps
		Point expectedAfterTwenty = new Point(20, (int) mob.getPosition().y);
		// after 30 steps
		Point expectedAfterThirty = new Point(30, (int) mob.getPosition().y);

		for (int i = 0; i <= numOfUpdates; i++) {
			mob.update(i);

			if (i == 10) {
				assertTrue(mob.getPosition().x == expectedAfterTen.x);
			}
			if (i == 20) {
				assertTrue(mob.getPosition().x == expectedAfterTwenty.x);
			}
			if (i == 30) {
				assertTrue(mob.getPosition().x == expectedAfterThirty.x);
			}
		}
	}

	@Test
	public void testChangeOfYAfterChangeOfDirection() {
		init();
		float xPos = -100;
		for (int i = 0; i < 1000; i++) {
			mob.update(i);
			if (xPos == mob.getPosition().x) {
				assertTrue(mob.getPosition().y == 689);
				break;
			}
			xPos = mob.getPosition().x;
		}
	}

	@Test
	public void slowReducesVelocity() {
		init();
		int time = IntGenerator.randValues(0, 10000);
		float preSlow = mob.getVelocity(0);
		float slowedForeverBecauseDeltaTimeIsZero = 0;
		mob.slowMob(time);
		for (int i = 0; i < time; i++) {
			slowedForeverBecauseDeltaTimeIsZero = mob.getVelocity(0);
			assertTrue(slowedForeverBecauseDeltaTimeIsZero < preSlow);

		}

	}

	@Test
	public void slowIsLessThanSpeedUntilSlowTimeIsOver() {
		init();
		float preSlow = mob.getVelocity(0);
		int time = IntGenerator.randValues(0, 10000);
		mob.slowMob(time);
		float postSlow = 0;
		for (int i = 0; i < time - 1; i++) {
			postSlow = mob.getVelocity(1f);
			assertTrue(postSlow < preSlow);
		}
		postSlow = mob.getVelocity(1f);
		assertTrue(postSlow == preSlow);
	}

	@Test
	public void testMobsGettingDamaged() {
		init();
		int total = mob.getHitPoints();
		int dmg = 1;
		mob.damaged(dmg);
		assertTrue(mob.getHitPoints() < total);
	}

	@Test
	public void projectileOverlapsMob() {
		init();
		Tower tower = new BeerTower(new Vector2(0,0));
		/*
		 * Logic is the same for all projectiletypes, so we are just testing with one
		 */
		Projectile pro = new BeerProjectile(tower.getPosition(), tower, 1, mob, false);
		while (pro.isActive()) {
			pro.update(1);
		}
		assertTrue(pro.checkCollision(mob));
	}

	@Test
	public void projectileHurtsMob() {
		init();
		Tower tower = new BeerTower(new Vector2(0,0));
		int dmg = 1;
		int total = mob.getHitPoints();
		Projectile pro = new BeerProjectile(tower.getPosition(), tower, dmg, mob, false);
		while (pro.isActive()) {
			pro.update(1);
			if (pro.checkCollision(mob))
				assertEquals(total, mob.getHitPoints());
		}
		
	}

	@Test
	public void differentMobSpeed() {
		init();
		Mob fasterMob = new BimboMob(10, 1, MOB_SPEED + 2, 10, "ekstra");
		assertEquals(fasterMob.getPosition(), mob.getPosition());
		for (int i = 0; i < 1000; i++) {
			assertTrue(mob.getDistTravelled() <= fasterMob.getDistTravelled());
			mob.update(i);
			fasterMob.update(i);
		}

	}

}
