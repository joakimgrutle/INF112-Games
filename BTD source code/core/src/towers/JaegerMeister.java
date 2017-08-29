package towers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import logicClasses.Mob;
import logicClasses.TDGame;
import logicClasses.Tower;

public class JaegerMeister extends Tower {

	private static int[] upgradeDamage = {1, 1, 5};
	private static int[] upgradeRange = {250, 275, 300};
	private static int[] upgradeCost = {200, 250, 1000};
	private static float[] upgradeCooldown = {2, 2, 1};
	private static int levelOnePrice = 275;
	
	public JaegerMeister(Vector2 position) {
		super(new Sprite(new Texture("image\\jagermeister2.png")), 0, 200, levelOnePrice, 2, true, position, "JaegerMeister");
		sprite.setSize(32, 64);
	}
	
	@Override
	protected Mob getTarget(TDGame game) {
		float maxDist = 0;
		Mob mob = null;
		for (Mob m : game.getWave().getListOfWaveMobs()) {
			if (this.mobIsInRange(m) && !m.isSlowed()) {
				if (m.getDistTravelled() >= maxDist) {
					mob = m;
					maxDist = m.getDistTravelled();
				}
			}
		}
		// if all mobs in range are slowed, targets as normal
		if(mob == null){
			for (Mob m : game.getWave().getListOfWaveMobs()) {
				if (this.mobIsInRange(m)) {
					if (m.getDistTravelled() >= maxDist) {
						mob = m;
						maxDist = m.getDistTravelled();
					}
				}
			}
		}
		return mob;
	}
	
	public static int[] getUpgradeDamge() {
		return upgradeDamage;
	}
	public static int[] getUpgradeRange() {
		return upgradeRange;
	}
	public static int[] getUpgradeCost() {
		return upgradeCost;
	}
	public static float[] getUpgradeCooldown() {
		return upgradeCooldown;
	}
	public static int getTowerPrice() {
		return levelOnePrice;
	}

}
