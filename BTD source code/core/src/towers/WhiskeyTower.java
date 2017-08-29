package towers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import logicClasses.Tower;

public class WhiskeyTower extends Tower {

	private static int[] upgradeDamage = {12, 15, 20};
	private static int[] upgradeRange = {300, 375, 425};
	private static int[] upgradeCost = {525, 775, 1000};
	private static float[] upgradeCooldown = {6, 4, 4};
	private static int levelOnePrice = 250;
	
	public WhiskeyTower(Vector2 position) {
		super(new Sprite(new Texture("image\\whiskey.png")), 9, 175, levelOnePrice, 7, false, position, "WhiskeyTower");
		sprite.setSize(32, 64);
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