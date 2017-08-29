/**
 * 
 */
package logicClasses;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.oblig4.game.TowerDefence;

import interfaces.IMob;
import mobs.BimboMob;
import mobs.BossMob;
import mobs.DudeBroMob;
import mobs.HoboMob;
import mobs.StudentMob;

/**
 * @author Andre Berge
 *
 */
public abstract class Mob extends Gamesprites implements IMob {

	protected enum Dir {
		LEFT, RIGHT, UP, DOWN;
	}

	protected enum EnemyType {
		NORMAL;
	}

	Texture blank;
	private int hitpoints, damage, gold, barSize;
	private float slowSpeed, slowTime, speed, elapsedTime, mobWidth, mobHeight, offsetX, offsetY;
	private boolean isSlowed;
	private String name;
	private float distTravelled = 0;
	private Dir dir;
	private float mobScale = 2;
	private Texture downImg, leftImg, rightImg, upImg;
	private TextureRegion[] downFrames, leftFrames, rightFrames, upFrames;
	private Animation downAnimation, leftAnimation, rightAnimation, upAnimation, animation;
	List<Point> waypoints = new ArrayList<>();

	public static Mob createMob(int num) {
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
			gold = 7 + (num);
			speed = 2 + (num / 5);
			damage = (int) (1 + Math.floor(0.1f * num));
			mob = new BimboMob(hitpoints, damage, speed, gold);
			return mob;
		case 2:
			hitpoints = 1 * num;
			gold = 8 + (num);
			speed = 1.5f + (num / 5);
			damage = (int) (1 + Math.floor(0.1f * num));
			mob = new DudeBroMob(hitpoints, damage, speed, gold);
			return mob;
		case 3:
			hitpoints = 1 * num;
			gold = 9 + (num);
			speed = 2 + (num / 5);
			damage = (int) (1 + Math.floor(0.1f * num));
			mob = new StudentMob(hitpoints, damage, speed, gold);
			return mob;
		case 4:
			hitpoints = 2 * num;
			gold = 10 + (num);
			speed = 2 + (num / 5);
			damage = (int) (1 + Math.floor(0.1f * num));
			mob = new HoboMob(hitpoints, damage, speed, gold);
			return mob;
		case 5:
			hitpoints = 20 * (num+3);
			gold = 50 + (10 * num);
			speed = 1f + (num / 6);
			damage = (int) (10 + Math.floor(0.1f * num));
			mob = new BossMob(hitpoints, damage, speed, gold);
			return mob;
		default:
			return null;

		}
	}

	/**
	 * konstruktør for å slippe å deale med animations i testing.
	 */
	public Mob(Sprite sprite, String name, int hitpoints, int damage, float speed, int gold, String ekstra) {
		super(sprite);
		this.sprite.setSize(32, 32);
		this.mobWidth = sprite.getWidth() * this.mobScale;
		this.mobHeight = sprite.getWidth() * this.mobScale;
		this.offsetX = this.mobWidth / (2 * this.mobScale);
		this.offsetY = this.mobWidth / (2 * this.mobScale);
		this.name = name;
		this.hitpoints = hitpoints;
		this.damage = damage;
		this.gold = gold;
		this.speed = speed;
		this.slowSpeed = speed / 2;
		this.position = super.getPosition();
		this.active = true;
		this.isSlowed = false;
		this.elapsedTime = 0;

		this.waypoints.add(new Point(0, 690));
		this.waypoints.add(new Point(338, 690));
		this.waypoints.add(new Point(338, 340));
		this.waypoints.add(new Point(16, 340));
		this.waypoints.add(new Point(16, 16));
		this.waypoints.add(new Point(402, 16));
		this.waypoints.add(new Point(402, 208));
		this.waypoints.add(new Point(720, 208));
		this.waypoints.add(new Point(720, 690));
		this.waypoints.add(new Point(1072, 690));
		this.waypoints.add(new Point(1072, 16));
		this.waypoints.add(new Point(752, 16));
		this.setPosition(new Vector2(waypoints.get(0).x, waypoints.get(0).y));
	}

	public Mob(Sprite sprite, String name, int hitpoints, int damage, float speed, int gold) {
		super(sprite);
		this.sprite.setSize(32, 32);

		this.mobWidth = sprite.getWidth() * this.mobScale;
		this.mobHeight = sprite.getWidth() * this.mobScale;
		this.offsetX = this.mobWidth / (2 * this.mobScale);
		this.offsetY = this.mobWidth / (2 * this.mobScale);
		this.name = name;
		this.hitpoints = hitpoints;
		this.damage = damage;
		this.gold = gold;
		this.speed = speed;
		this.slowSpeed = speed / 2;
		this.position = super.getPosition();
		this.active = true;
		this.isSlowed = false;
		this.elapsedTime = 0;
		this.barSize = this.hitpoints;

		this.waypoints.add(new Point(0, 690));
		this.waypoints.add(new Point(338, 690));
		this.waypoints.add(new Point(338, 340));
		this.waypoints.add(new Point(16, 340));
		this.waypoints.add(new Point(16, 16));
		this.waypoints.add(new Point(402, 16));
		this.waypoints.add(new Point(402, 208));
		this.waypoints.add(new Point(720, 208));
		this.waypoints.add(new Point(720, 690));
		this.waypoints.add(new Point(1072, 690));
		this.waypoints.add(new Point(1072, 16));
		this.waypoints.add(new Point(752, 16));
		this.setPosition(new Vector2(waypoints.get(0).x, waypoints.get(0).y));

		this.blank = new Texture("whitePixelHP.png");
		downImg = new Texture("image\\src\\" + name + "_down.png");
		leftImg = new Texture("image\\src\\" + name + "_left.png");
		rightImg = new Texture("image\\src\\" + name + "_right.png");
		upImg = new Texture("image\\src\\" + name + "_up.png");
		TextureRegion[][] tempDownFrames = TextureRegion.split(downImg, 32, 32);
		TextureRegion[][] tempLeftFrames = TextureRegion.split(leftImg, 32, 32);
		TextureRegion[][] tempRightFrames = TextureRegion.split(rightImg, 32, 32);
		TextureRegion[][] tempUpFrames = TextureRegion.split(upImg, 32, 32);
		downFrames = new TextureRegion[4];
		leftFrames = new TextureRegion[4];
		rightFrames = new TextureRegion[4];
		upFrames = new TextureRegion[4];
		int index = 0;
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < 4; j++) {
				downFrames[index] = tempDownFrames[i][j];
				leftFrames[index] = tempLeftFrames[i][j];
				rightFrames[index] = tempRightFrames[i][j];
				upFrames[index++] = tempUpFrames[i][j];
			}
		}
		this.speed = this.speed * 5;
		downAnimation = new Animation(1f / this.speed, downFrames);
		leftAnimation = new Animation(1f / this.speed, leftFrames);
		rightAnimation = new Animation(1f / this.speed, rightFrames);
		upAnimation = new Animation(1f / this.speed, upFrames);
		animation = new Animation(1f / this.speed, rightFrames);
		this.speed = this.speed / 5;
		downAnimation.setPlayMode(PlayMode.LOOP);
		leftAnimation.setPlayMode(PlayMode.LOOP);
		rightAnimation.setPlayMode(PlayMode.LOOP);
		upAnimation.setPlayMode(PlayMode.LOOP);
		animation.setPlayMode(PlayMode.LOOP);
	}

	@Override
	public void setDamage(int damage) {
		this.damage = damage;
	}

	@Override
	public int getHitPoints() {
		return this.hitpoints;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public int getDamage() {
		return this.damage;
	}

	public int getSpawnDelay() {
		return (int) (this.speed / 2);
	}

	@Override
	public void slowMob(float time) {
		this.isSlowed = true;
		this.slowTime = time;
	}

	@Override
	public int getGold() {
		return this.gold;
	}

	@Override
	public void update(float dt) {
		super.update(dt);

		float actSpeed = getVelocity(0.1f);
		this.checkIfReachedTheGoal();
		if (!waypoints.isEmpty()) {
			Point waypoint = waypoints.get(0);
			if (position.x > waypoint.x) {
				dir = Dir.LEFT;
				animation = leftAnimation;
			} else if (position.x < waypoint.x) {
				dir = Dir.RIGHT;
				animation = rightAnimation;
			} else if (position.y > waypoint.y) {
				dir = Dir.UP;
				animation = downAnimation;
			} else if (position.y < waypoint.y) {
				dir = Dir.DOWN;
				animation = upAnimation;
			}

			if (dir == Dir.LEFT) {
				position.x -= actSpeed;
				distTravelled += actSpeed;
				if (position.x <= waypoint.x) {
					position.x = waypoint.x;
				}
			} else if (dir == Dir.RIGHT) {
				position.x += actSpeed;
				distTravelled += actSpeed;
				if (position.x >= waypoint.x) {
					position.x = waypoint.x;
				}
			} else if (dir == Dir.UP) {
				position.y -= actSpeed;
				distTravelled += actSpeed;
				if (position.y <= waypoint.y) {
					position.y = waypoint.y;
				}
			} else if (dir == Dir.DOWN) {
				position.y += actSpeed;
				distTravelled += actSpeed;
				if (position.y >= waypoint.y) {
					position.y = waypoint.y;

				}
			}

			if (position.x == waypoint.x && position.y == waypoint.y) {
				waypoints.remove(0);
			}
		}
	}

	@Override
	public void damaged(int damage) {
		this.hitpoints -= damage;
		if (hitpoints <= 0) {
			this.active = false;

			String soundLocation = "sound/mob/puke/puke-";
			switch (this.getName()) {
			case ("student"):
				soundLocation += (("K") + this.getRandom(15)) + ".ogg";
				TowerDefence.manager.get(soundLocation, Sound.class).play(0.9f, this.getRandomPitch(), 0f);
				break;

			case ("homeless"):
				soundLocation += (("H") + this.getRandom(20)) + ".ogg";
				TowerDefence.manager.get(soundLocation, Sound.class).play(0.8f, this.getRandomPitch(), 0f);
				break;

			case ("dudebro"):
				soundLocation += (("A") + this.getRandom(29)) + ".ogg";
				TowerDefence.manager.get(soundLocation, Sound.class).play(0.1f, this.getRandomPitch(), 0f);
				break;

			case ("bimbo"):
				soundLocation += (("S") + this.getRandom(37)) + ".ogg";
				TowerDefence.manager.get(soundLocation, Sound.class).play(0.7f, this.getRandomPitch(), 0f);
				break;

			case ("boss"):
				soundLocation += (("N") + this.getRandom(5)) + ".ogg";
				TowerDefence.manager.get(soundLocation, Sound.class).play(1.2f, this.getRandomPitch(), 0f);
				this.hitpoints += damage;
				
				if (damage > 1)
					this.hitpoints -= (damage - 2);
				
				this.hitpoints -= damage;
				
				break;
			}

			TDGame.getInstance().addScore((int) Math.floor(this.gold + (damage * 2)));
			TDGame.getInstance().changeMoneyAmount(this.getGold());
		}
	}

	@Override
	public float getVelocity(float delta) {
		slowTime -= delta;
		if (slowTime > 0 && this.isSlowed) {
			return slowSpeed;
		} else {
			this.isSlowed = false;
			return speed;
		}
	}

	@Override
	public List<Point> getWaypoints() {
		return this.waypoints;
	}

	@Override
	public void draw(SpriteBatch spriteBatch) {

		if (this.isSlowed) {
			slowSpeed = slowSpeed * 5;
			animation.setFrameDuration(1f / slowSpeed);
			slowSpeed = slowSpeed / 5;
		} else {
			speed = speed * 5;
			animation.setFrameDuration(1f / speed);
			speed = speed / 5;
		}

		if (this.active) {
			elapsedTime += Gdx.graphics.getDeltaTime();
			sprite.setX(this.position.x);
			sprite.setY(this.position.y);
			if (this.name.equals("boss")) {
				spriteBatch.draw(blank, this.getSprite().getX() - 8, this.getSprite().getY() - 4, 5,
						this.getSprite().getHeight() * ((float) this.hitpoints / (float) this.barSize));
			} else {
				spriteBatch.draw(blank, this.getSprite().getX(),
						this.getSprite().getY() + this.getSprite().getHeight() + 10,
						this.getSprite().getWidth() * ((float) this.hitpoints / (float) this.barSize), 4);
			}

			spriteBatch.draw((TextureRegion) animation.getKeyFrame(elapsedTime, true), sprite.getX() - this.offsetX,
					sprite.getY() - this.offsetY, mobWidth, this.mobHeight);
		}
	}

	@Override
	public float getDistTravelled() {
		return this.distTravelled;
	}

	protected void checkIfReachedTheGoal() {
		if (waypoints.size() == 0 && isActive()) {
			setActive(false);
			TDGame.getInstance().getDamaged(this);
			;
		}
	}

	public int getRandom(int range) {
		Random rand = new Random();
		int randomNumber = rand.nextInt(range - 1) + 1;

		return randomNumber;
	}

	public float getRandomPitch() {
		Random rand = new Random();
		float randomNumber = rand.nextFloat() / 2 - 0.25f;

		randomNumber += 1.0f;

		return randomNumber;
	}
	
	public boolean isSlowed(){
		return isSlowed;
	}
}
