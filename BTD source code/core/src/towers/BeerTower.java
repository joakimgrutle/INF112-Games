package towers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import logicClasses.Mob;
import logicClasses.TDGame;
import logicClasses.Tower;



public class BeerTower extends Tower {
	
	/*
	 * Denne klassen ka ta hånd om oppgraderinger.
	 */
	private static int[] upgradeDamage = {3, 5, 8};
	private static int[] upgradeRange = {200, 250, 350};
	private static int[] upgradeCost = {100, 350, 600};
	private static float[] upgradeCooldown = {5, 3, 2};
	private static int levelOnePrice = 100;
	
	public BeerTower(Vector2 position) {
		super(new Sprite(new Texture(Gdx.files.internal("image/corona.png"))), 1, 150, levelOnePrice, 7, false, position, "BeerTower");
		
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
