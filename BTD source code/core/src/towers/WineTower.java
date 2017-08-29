package towers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import logicClasses.Tower;

public class WineTower extends Tower {
	
	private static int[] upgradeDamage = {6, 8, 12};
	private static int[] upgradeRange = {250, 325, 600};
	private static int[] upgradeCost = {220, 225, 430};
	private static float[] upgradeCooldown = {8, 6, 5};
	private static int levelOnePrice = 180;

	public WineTower(Vector2 position) {
		super(new Sprite(new Texture("image\\wine.png")), 3, 150, levelOnePrice, 6, false, position, "WineTower");
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
