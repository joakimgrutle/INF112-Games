package logicClasses;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.oblig4.game.TowerDefence;

import interfaces.ITDGame;
import interfaces.IWave;

public class TDGame implements ITDGame {

	public static TDGame tdGameStatic;

	public static TDGame instance;

	private final int STARTING_CAPITAL = 150;

	private final int STARTING_LIVES = 20;

	private int spawnedMobs, score, currentWaveIndex, playerLives, money, level, mobCounter;
	private float spawnDelay = 0;
	private List<Tower> towerList;
	private IWave wave;
	private ArrayList<Projectile> projectiles;
	private List<Point> waypoint = new ArrayList<>();
	private boolean roundHasStarted;
	private Tower ghost;
	private TmxMapLoader maploader = new TmxMapLoader();;
	private TiledMap tiledMap = maploader.load("image/utkast/nightTest2.tmx");

	public MapInterpretation mapping;

	public TDGame() {
		level = 0;
		mobCounter = 0;
		score = 0;
		money = STARTING_CAPITAL;
		playerLives = STARTING_LIVES;
		towerList = new ArrayList<>();
		roundHasStarted = false;
		wave = new Wave();
		projectiles = new ArrayList<>();
		spawnedMobs = 0;
		instance = this;

		// new since 27.04 @nikolai
		mapping = new MapInterpretation("image/utkast/nightTest2.tmx");
	}

	@Override
	public void startNewRound() {
		mobCounter = 0;
		this.setLevel(this.level + 1);
		wave.getListOfWaveMobs().clear();
		wave = new Wave(this.getLevel());
		projectiles = new ArrayList<>();
		spawnedMobs = 0;
		roundHasStarted = true;
		instance = this;
	}

	@Override
	public void setWayPoints(List<Point> list) {
		this.waypoint = list;
	}

	public int getScore() {
		return this.score;
	}

	public void addScore(int score) {
		this.score += score;
	}

	public boolean lostGame() {
		if (this.playerLives <= 0) {
			return true;
		}
		return false;
	}

	@Override
	public void setGhostTower(Tower tower) {
		this.ghost = tower;
	}

	@Override
	public Tower getGhostTower() {
		return this.ghost;
	}

	@Override
	public void getDamaged(Mob mob) {
		playerLives -= mob.getDamage();
	}

	@Override
	public void changeMoneyAmount(int deltaMoneyAmount) {
		this.money += deltaMoneyAmount;
	}

	public boolean canPlace(Tower tower) {
		return false;

	}

	@Override
	public void buyTower(Tower tower) {
		if (tower.canBuy(money)) {
			money -= tower.getPrice();
			towerList.add(tower);
		}
	}

	@Override
	public void sellTower(Tower tower) {



		switch (tower.getType()) {
		case ("BeerTower"):
			TowerDefence.manager.get("sound/tower/sell/sell-beer.ogg", Sound.class).play();
			break;

		case ("JaegerMeister"):
			TowerDefence.manager.get("sound/tower/sell/sell-jager.ogg", Sound.class).play();
			break;

		case ("WineTower"):
			TowerDefence.manager.get("sound/tower/sell/sell-wine.ogg", Sound.class).play();
			break;

		case ("VodkaTower"):
			TowerDefence.manager.get("sound/tower/sell/sell-vodka.ogg", Sound.class).play();
			break;

		case ("WhiskeyTower"):
			TowerDefence.manager.get("sound/tower/sell/sell-whiskey.ogg", Sound.class).play();
			break;
		}

		for (int i = 0; i < towerList.size(); i++) {
			if (towerList.get(i).getPosition() == tower.getPosition()) {
				changeMoneyAmount((int) Math.floor((tower.getPrice() * 0.75)));
				towerList.remove(i);
				mapping.locationsOccupied.remove(i);
			}
		}
	}

	public void upgradeTower(Tower tower) {
		for (int i = 0; i < towerList.size(); i++) {
			if (towerList.get(i).getPosition() == tower.getPosition()) {
				tower.upgradeTower();
			}
		}
	}

	@Override
	public int getMoneyAmount() {
		return money;
	}

	@Override
	public int getLevel() {
		return level;
	}

	@Override
	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public int getPlayerLives() {
		return playerLives;
	}

	@Override
	public void setPlayerLives(int playerLives) {
		this.playerLives = playerLives;
	}

	@Override
	public int getSpawnedMobs() {
		return spawnedMobs;
	}

	@Override
	public void setSpawnedMobs(int spawnedMobs) {
		this.spawnedMobs = spawnedMobs;
	}

	@Override
	public List<Tower> getTowerList() {
		return towerList;
	}

	@Override
	public boolean roundHasStarted() {
		return roundHasStarted;
	}

	@Override
	public int getCurrentWaveIndex() {
		return currentWaveIndex;
	}

	@Override
	public void setCurrentWaveIndex(int currentWaveIndex) {
		this.currentWaveIndex = currentWaveIndex;
	}

	@Override
	public int getWaveCounter() {
		return this.getWave().getWaveNum();
	}

	@Override
	public IWave getWave() {
		return wave;
	}

	@Override
	public void update(float dt) {
		if (this.roundHasStarted) {
			if (this.getSpawnedMobs() < wave.getNumberOfMobs())
				this.checkForEnemies(0.1f);
			if (!this.wave.getListOfWaveMobs().isEmpty() || (this.spawnedMobs < this.getWave().getNumberOfMobs())) {
				this.getWave().update(dt);
				for (Mob mob : getWave().getListOfWaveMobs())
					mob.update(dt);
			} else {
				this.roundHasStarted = false;
				changeMoneyAmount(20 + (this.getLevel() * 2));
			}
			for (Tower tower : getTowerList())
				tower.update(dt, this);

			for (Projectile projectile : projectiles)
				projectile.update(dt);
		}

	}

	@Override
	public void render(SpriteBatch spriteBatch) {
		displayTowers(spriteBatch);
		displayEnemies(spriteBatch);
		displayProjectiles(spriteBatch);
		dispGhostTower(spriteBatch);
	}

	private void displayProjectiles(SpriteBatch spriteBatch) {
		spriteBatch.begin();
		for (Projectile projectile : projectiles) {
			projectile.draw(spriteBatch);
		}
		spriteBatch.end();

	}

	// For å sortere tower, putter den midlertidig her
	public class TowerComp implements Comparator<Tower> {

		@Override
		public int compare(Tower e1, Tower e2) {
			if (e1.getPosition().y < e2.getPosition().y)
				return 1;
			else if (e1.getPosition().y > e2.getPosition().y)
				return -1;
			return 0;
		}
	}

	private void displayTowers(SpriteBatch spriteBatch) {
		List<Tower> towerRender = new ArrayList<>();
		towerRender.addAll(getTowerList());
		towerRender.sort(new TowerComp());

		spriteBatch.begin();
		for (Tower tower : towerRender) {
			tower.draw(spriteBatch);
		}
		spriteBatch.end();

	}

	private void displayEnemies(SpriteBatch spriteBatch) {
		spriteBatch.begin();
		for (Mob mob : this.getWave().getListOfWaveMobs()) {
			mob.draw(spriteBatch);
		}
		spriteBatch.end();

	}

	private void dispGhostTower(SpriteBatch spriteBatch) {
		spriteBatch.begin();
		if (this.getGhostTower() != null) {

			this.getGhostTower().setPosition(new Vector2(
					Gdx.input.getX() - this.getGhostTower().getSprite().getWidth() / 2,
					(Gdx.graphics.getHeight() - Gdx.input.getY() - this.getGhostTower().getSprite().getHeight() / 2)));
			this.getGhostTower().draw(spriteBatch);
		}
		spriteBatch.end();

	}

	@Override
	public void checkForEnemies(float dt) {
		spawnDelay += dt;
		if (spawnDelay >= 5 && spawnedMobs < wave.getNumberOfMobs() && spawnedMobs < 1) {
			Mob mob = Mob.createMob(this.getLevel());
			this.wave.addMob(mob);
			spawnDelay = 0;
			++spawnedMobs;
		} else if (this.wave.getListOfWaveMobs().size() > 0) {
			if (spawnedMobs > 0
					&& this.wave.getListOfWaveMobs().get(wave.getListOfWaveMobs().size() - 1).getSprite().getX() >= 100
					&& spawnedMobs < wave.getNumberOfMobs()) {
				Mob mob = Mob.createMob(this.getLevel());
				this.wave.addMob(mob);
				spawnDelay = 0;
				++spawnedMobs;
			}
		} else if (spawnDelay >= 5 && spawnedMobs < wave.getNumberOfMobs()) {
			Mob mob = Mob.createMob(this.getLevel());
			this.wave.addMob(mob);
			spawnDelay = 0;
			++spawnedMobs;
		}
	}

	public static TDGame getInstance() {
		return instance;
	}

	@Override
	public List<Point> getWaypoints() {
		return this.waypoint;
	}

	@Override
	public void addProjectile(Projectile pro) {
		this.projectiles.add(pro);
	}

	@Override
	public ArrayList<Projectile> getProjectiles() {
		return this.projectiles;
	}

}
