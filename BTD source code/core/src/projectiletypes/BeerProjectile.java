package projectiletypes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import logicClasses.Mob;
import logicClasses.Projectile;
import logicClasses.Tower;

public class BeerProjectile extends Projectile {

	public BeerProjectile(Vector2 position, Tower tower, int damage, Mob target, boolean slows) {
		super(new Sprite(new Texture("image\\projectiles\\glassOfBeer.png")), tower, damage, 10f, position, target, slows);
		this.sprite.setSize(6, 6);
	}


}
