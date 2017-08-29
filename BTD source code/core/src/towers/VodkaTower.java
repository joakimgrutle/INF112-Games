package towers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import logicClasses.Tower;

public class VodkaTower extends Tower {

	private static int[] upgradeDamage = {1, 1, 2};
	private static int[] upgradeRange = {200, 250, 300};
	private static int[] upgradeCost = {300, 500, 850};
	private static float[] upgradeCooldown = {0.7f, 0.5f, 0.3f};
	private static int levelOnePrice = 500;
	
	public VodkaTower(Vector2 position) {
		super(new Sprite(new Texture("image\\vodka.png")), 1, 125, levelOnePrice, 1, false, position, "VodkaTower");
		sprite.setSize(32, 64);
		
		// XDXD
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