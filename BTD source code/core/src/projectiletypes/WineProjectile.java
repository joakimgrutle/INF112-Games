package projectiletypes;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import logicClasses.Mob;
import logicClasses.Projectile;
import logicClasses.Tower;

public class WineProjectile extends Projectile {

	public WineProjectile(Vector2 position, Tower tower, int damage, Mob target, boolean slows) {
		super(new Sprite(new Texture("image\\projectiles\\glassOfWine.png")), tower, damage, 10, position, target,
				slows);
		sprite.setSize(6, 6);

	}

	@Override
	public void update(float delta) {
		Random ran = new Random();
		
		if (this.active) {
			if (!target.isActive()) {
				this.active = false;
				return;
			}
			boolean collided = checkCollision(target);
			if (collided && active) {
				int kill = ran.nextInt(20);
				
				
				if (kill == 5) {
					target.damaged(1000);
				} else
					target.damaged(this.getDamage());
				if (!target.isActive())
					this.getTower().addKill();
				if (this.isSlows())
					target.slowMob(5); // Changing this value later
				this.active = false;
			}
			if (!collided)
				moveProjectile(target);
		}
	}
}
