package logicClasses;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import interfaces.IMob;
import interfaces.IProjectile;
import logicClasses.Projectile.ProjectileType;
import projectiletypes.BeerProjectile;
import projectiletypes.JaegerProjectile;
import projectiletypes.VodkaProjectile;
import projectiletypes.WhiskeyProjectile;
import projectiletypes.WineProjectile;
import towers.JaegerMeister;

public abstract class Projectile extends Gamesprites implements IProjectile {

	public enum ProjectileType {

	}

	private int damage;
	private float speed;
	public Mob target;
	private boolean slows;
	private Tower tower;

	public Projectile(Sprite sprite, Tower tower, int damage, float speed, Vector2 position, Mob target,
			boolean slows) {
		super(sprite);
		this.setSlows(slows);
		this.target = target;
		this.setDamage(damage);
		this.speed = speed;
		this.position = position;
		this.setTower(tower);
		active = true;

	}

	@Override
	public float getSpeed() {
		return speed;

	}

	@Override
	public boolean checkCollision(Mob mob) {
		Rectangle rectangle = sprite.getBoundingRectangle();
		Rectangle mobRect = mob.getSprite().getBoundingRectangle();
		return rectangle.overlaps(mobRect);
	}

	@Override
	public void moveProjectile(Mob enemy) {
		// Prøvde å lage et forhold mellom hvor lang avstand det er fra mob til
		// projectile
		// i x, og y retning respektivt. Og ganget da enten x eller y - movement
		// med forholdet
		// Alt etter hvilken retning som hadde lengst vei å bevege seg.
		// For at skuddet skulle gå til moben i en "smooth"-bevegelse,
		// istedenfor hakkete

		Vector2 goal = new Vector2(enemy.getPosition().x + enemy.getSprite().getWidth() / 2,
				enemy.getPosition().y + enemy.getSprite().getHeight() / 2);
		float forholdx = 1;
		float forholdy = 1;
		if (Math.abs(goal.x - position.x) >= 1 && Math.abs(goal.y - position.y) >= 1) {
			if (Math.abs(goal.x - position.x) < Math.abs(goal.y - position.y))
				forholdx = Math.abs(goal.x - position.x) / Math.abs(goal.y - position.y);
			else
				forholdy = Math.abs(goal.y - position.y) / Math.abs(goal.x - position.x);
		}

		if (goal.x > position.x)
			position.x += speed * forholdx;
		else if (goal.x < position.x)
			position.x -= speed * forholdx;
		if (goal.y > position.y)
			position.y += speed * forholdy;
		else if (goal.y < position.y)
			position.y -= speed * forholdy;
	}

	public void draw(SpriteBatch spritebatch) {
		if (active) {
			// deler på 2 for å få prosjektilet til å spawne i midten av tårnet.
			sprite.setX(position.x + (sprite.getHeight() / 2));
			sprite.setY(position.y + (sprite.getWidth() / 2));
			sprite.draw(spritebatch);
		}

	}

	/*
	 *
	 */
	public void update(float delta) {
		if (this.active) {
			super.update(delta);
			if (!target.active) {
				this.active = false;
				return;
			}
			boolean collided = checkCollision(target);
			if (collided && active) {
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

	public static Projectile createProjectile(Tower tower, Mob target2) {
		// Her kan vi legge til prosjektiltyper, som blir brukt alt etter
		// hvilket type tårn
		// Som blir gitt som parameter.
		Projectile pro;
		Vector2 pos = new Vector2(tower.getPosition().x + tower.getSprite().getWidth() / 2,
				tower.getPosition().y + tower.getSprite().getHeight() / 2);
		String type = tower.getType();
		switch (type.toLowerCase()) {
		case "beertower":
			pro = new BeerProjectile(pos, tower, tower.getDamage(), target2, tower.doesSlow());
			return pro;
		case "jaegermeister":
			pro = new JaegerProjectile(pos, tower, tower.getDamage(), target2, tower.doesSlow());
			return pro;
		case "winetower":
			pro = new WineProjectile(pos, tower, tower.getDamage(), target2, tower.doesSlow());
			return pro;
		case "vodkatower":
			pro = new VodkaProjectile(pos, tower, tower.getDamage(), target2, tower.doesSlow());
			return pro;
		case "whiskeytower":
			pro = new WhiskeyProjectile(pos, tower, tower.getDamage(), target2, tower.doesSlow());
			return pro;
		default:
			return null;
		}
	}

	public int getDamage() {
		return this.damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public Tower getTower() {
		return tower;
	}

	public void setTower(Tower tower) {
		this.tower = tower;
	}

	public boolean isSlows() {
		return slows;
	}

	public void setSlows(boolean slows) {
		this.slows = slows;
	}
}
