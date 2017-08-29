package logicClasses;

import java.util.Random;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.oblig4.game.TowerDefence;
import com.oblig4.game.TowerInfoWindow;
import com.oblig4.game.Hud;

import interfaces.ITower;
import towers.BeerTower;
import towers.JaegerMeister;
import towers.VodkaTower;
import towers.WhiskeyTower;
import towers.WineTower;

public abstract class Tower extends Gamesprites implements ITower {

	private int damage, price, range, kills, level;
	private float cooldown;
	private float lastShot;
	private boolean slowsMobs;
	private Mob target;
	private String name;
	public TowerInfoWindow window;
	private boolean justCreated;

	public Tower(Sprite sprite, int damage, int range, int price, float coolDown, boolean slows, Vector2 position,
			String name) {
		super(sprite);
		this.damage = damage;
		this.level = 0;
		this.range = range;
		this.price = price;
		this.cooldown = coolDown;
		this.lastShot = 0;
		this.active = true;
		this.position = position;
		this.slowsMobs = slows;
		this.kills = 0;
		this.name = name;
		this.justCreated = true;
	}

	@Override
	public void draw(SpriteBatch spriteBatch) {
		if (active) {
			sprite.setX(position.x);
			sprite.setY(position.y);
			sprite.draw(spriteBatch);
		}
	}

	@Override
	public int getPrice() {
		return price;
	}

	@Override
	public int getDamage() {
		return this.damage;
	}

	@Override
	public boolean canBuy(int money) {
		return money >= price;
	}

	public String getType() {
		return this.name;
	}

	public int getRange() {
		return this.range;
	}

	@Override
	public void shoot(float delta, TDGame game) {
		lastShot += delta;
		if (target != null) {
			if (lastShot >= cooldown) {
				lastShot = 0f;
				Projectile pro = Projectile.createProjectile(this, target);
				
				switch (this.getType()){
				case ("BeerTower"):
					TowerDefence.manager.get("sound/tower/attack/attack-beer.ogg", Sound.class).play(1.0f, this.getRandomPitch(), 0f);
					break;
					
				case ("JaegerMeister"):
					TowerDefence.manager.get("sound/tower/attack/attack-jager.ogg", Sound.class).play(1.0f, this.getRandomPitch(), 0f);
					break;
					
				case ("WineTower"):
					TowerDefence.manager.get("sound/tower/attack/attack-wine.ogg", Sound.class).play(1.0f, this.getRandomPitch(), 0f);
					break;
					
				case ("VodkaTower"):
					TowerDefence.manager.get("sound/tower/attack/attack-vodka.ogg", Sound.class).play(1.0f, this.getRandomPitch(), 0f);
					break;
					
				case ("WhiskeyTower"):
					TowerDefence.manager.get("sound/tower/attack/attack-whiskey.ogg", Sound.class).play(1.0f, this.getRandomPitch(), 0f);
					break;
				}

				game.addProjectile(pro);
				this.target = null;
			}
		}
	}

	@Override
	public void update(float delta, TDGame game) {
		super.update(delta);
		this.setTarget(this.getTarget(game));
		shoot(0.1f, game);
	}

	@Override
	public void setTarget(Mob mob) {
		this.target = mob;
	}

	@Override
	public boolean doesSlow() {
		return this.slowsMobs;
	}

	public void upgradeTower() {
		if (this instanceof BeerTower) {
			if (canUpgradeTower(this)) {
				this.setCooldown(BeerTower.getUpgradeCooldown()[this.getLevel()]);
				this.setDamage(BeerTower.getUpgradeDamge()[this.getLevel()]);
				this.setRange(BeerTower.getUpgradeRange()[this.getLevel()]);
				TDGame.getInstance().changeMoneyAmount(-BeerTower.getUpgradeCost()[this.getLevel()]);
				this.addPrice(BeerTower.getUpgradeCost()[this.getLevel()]);
				this.incrementLevel();

			}
		} else if (this instanceof WineTower) {
			if (canUpgradeTower(this)) {
				this.setCooldown(WineTower.getUpgradeCooldown()[this.getLevel()]);
				this.setDamage(WineTower.getUpgradeDamge()[this.getLevel()]);
				this.setRange(WineTower.getUpgradeRange()[this.getLevel()]);
				TDGame.getInstance().changeMoneyAmount(-WineTower.getUpgradeCost()[this.getLevel()]);
				this.addPrice(WineTower.getUpgradeCost()[this.getLevel()]);
				this.incrementLevel();

			}
		} else if (this instanceof JaegerMeister) {
			if (canUpgradeTower(this)) {
				this.setCooldown(JaegerMeister.getUpgradeCooldown()[this.getLevel()]);
				this.setDamage(JaegerMeister.getUpgradeDamge()[this.getLevel()]);
				this.setRange(JaegerMeister.getUpgradeRange()[this.getLevel()]);
				TDGame.getInstance().changeMoneyAmount(-JaegerMeister.getUpgradeCost()[this.getLevel()]);
				this.addPrice(JaegerMeister.getUpgradeCost()[this.getLevel()]);
				this.incrementLevel();

			}
		} else if (this instanceof WhiskeyTower) {
			if (canUpgradeTower(this)) {
				this.setCooldown(WhiskeyTower.getUpgradeCooldown()[this.getLevel()]);
				this.setDamage(WhiskeyTower.getUpgradeDamge()[this.getLevel()]);
				this.setRange(WhiskeyTower.getUpgradeRange()[this.getLevel()]);
				TDGame.getInstance().changeMoneyAmount(-WhiskeyTower.getUpgradeCost()[this.getLevel()]);
				this.addPrice(WhiskeyTower.getUpgradeCost()[this.getLevel()]);
				this.incrementLevel();

			}
		} else if (this instanceof VodkaTower) {
			if (canUpgradeTower(this)) {
				this.setCooldown(VodkaTower.getUpgradeCooldown()[this.getLevel()]);
				this.setDamage(VodkaTower.getUpgradeDamge()[this.getLevel()]);
				this.setRange(VodkaTower.getUpgradeRange()[this.getLevel()]);
				TDGame.getInstance().changeMoneyAmount(-VodkaTower.getUpgradeCost()[this.getLevel()]);
				this.addPrice(VodkaTower.getUpgradeCost()[this.getLevel()]);
				this.incrementLevel();

			}
		}

	}

	public void addPrice(int addition) {
		this.price += addition;
	}

	public boolean canUpgradeTower(Tower tower) {
		if (tower instanceof BeerTower) {
			if (this.getLevel() < BeerTower.getUpgradeCooldown().length) {
				if ((BeerTower.getUpgradeCost()[this.getLevel()] <= TDGame.getInstance().getMoneyAmount())) {
					return true;
				}
			} else
				return false;
		} else if (tower instanceof WineTower) {
			if (this.getLevel() < WineTower.getUpgradeCooldown().length) {
				if ((WineTower.getUpgradeCost()[this.getLevel()] <= TDGame.getInstance().getMoneyAmount())) {
					return true;
				}
			} else
				return false;
		} else if (tower instanceof JaegerMeister) {
			if (this.getLevel() < JaegerMeister.getUpgradeCooldown().length) {
				if ((JaegerMeister.getUpgradeCost()[this.getLevel()] <= TDGame.getInstance().getMoneyAmount())) {
					return true;
				}
			} else
				return false;
		} else if (tower instanceof WhiskeyTower) {
			if (this.getLevel() < WhiskeyTower.getUpgradeCooldown().length) {
				if ((WhiskeyTower.getUpgradeCost()[this.getLevel()] <= TDGame.getInstance().getMoneyAmount())) {
					return true;
				}
			} else
				return false;
		} else if (tower instanceof VodkaTower) {
			if (this.getLevel() < VodkaTower.getUpgradeCooldown().length) {
				if ((VodkaTower.getUpgradeCost()[this.getLevel()] <= TDGame.getInstance().getMoneyAmount())) {
					return true;
				}
			} else
				return false;
		}
		return false;
	}

	public void addKill() {
		this.kills++;
	}

	public int getKills() {
		return this.kills;
	}

	public void setDamage(int dmg) {
		this.damage = dmg;
	}

	public void incrementLevel() {
		this.level++;
	}

	public int getLevel() {
		return this.level;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public void setCooldown(float f) {
		this.cooldown = f;
	}

	// Open tower info tab
	public void infoWindow(Hud hud) {
		if (justCreated) {
			justCreated = false;
			return;
		}
		if (!TowerInfoWindow.isOpen) {
			window = new TowerInfoWindow(this, TowerDefence.uiSkin, hud, this.position, false);
			hud.stage.addActor(window);
			TowerInfoWindow.isOpen = true;
		} else if (TowerInfoWindow.isOpen) {
			for (Tower t : TDGame.getInstance().getTowerList()) {
				if (t.window != null)
					t.window.remove();
			}
			window = new TowerInfoWindow(this, TowerDefence.uiSkin, hud, this.position, true);
			hud.stage.addActor(window);
			TowerInfoWindow.isOpen = true;
			
			
			
		}
	}

	protected Mob getTarget(TDGame game) {
		float maxDist = 0;
		Mob mob = null;
		for (Mob m : game.getWave().getListOfWaveMobs()) {
			if (this.mobIsInRange(m)) {
				if (m.getDistTravelled() >= maxDist) {
					mob = m;
					maxDist = m.getDistTravelled();
				}
			}
		}
		return mob;
	}

	protected boolean mobIsInRange(Mob m) {
		Vector2 vec = new Vector2();
		vec.set(position.x + this.sprite.getWidth() / 2, position.y + this.sprite.getHeight() / 2);
		return vec.dst(m.getPosition()) < range;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + damage;
		result = prime * result + price;
		result = prime * result + (slowsMobs ? 1231 : 1237);
		result = prime * result + level;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tower other = (Tower) obj;
		if (cooldown != other.cooldown)
			return false;
		if (damage != other.damage)
			return false;
		if (price != other.price)
			return false;
		if (slowsMobs != other.slowsMobs)
			return false;
		if (level != other.level)
			return false;
		return true;
	}
	
	public float getRandomPitch() {
		Random rand = new Random();
		float randomNumber = rand.nextFloat() / 2 - 0.25f;

		randomNumber += 1.0f;

		return randomNumber;
	}
	
	public int getSingleUpgradeRange() {
		switch (this.getType()){
		case "BeerTower" :
			if (this.canUpgradeTower(this))
				return (int)BeerTower.getUpgradeRange()[this.getLevel()];
			break;
			
		case "JaegerMeister":
			if (this.canUpgradeTower(this))
				return (int)JaegerMeister.getUpgradeRange()[this.getLevel()];
			break;
			
		case "VodkaTower":
			if (this.canUpgradeTower(this))
				return (int)VodkaTower.getUpgradeRange()[this.getLevel()];
			break;
			
		case "WhiskeyTower":
			if (this.canUpgradeTower(this))
				return (int)WhiskeyTower.getUpgradeRange()[this.getLevel()];
			break;
			
		case "WineTower":
			if (this.canUpgradeTower(this))
				return (int)WineTower.getUpgradeRange()[this.getLevel()];
			break;
		}
		
		return 0;
	}

}
