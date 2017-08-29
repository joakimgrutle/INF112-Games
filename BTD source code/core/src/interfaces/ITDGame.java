package interfaces;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import logicClasses.Mob;
import logicClasses.Projectile;
import logicClasses.Tower;

/**
 * Kjøre spillet towerdefence
 */
public interface ITDGame {
	
	/**
	 * 
	 *     starter en ny runde av spillet. 
	 *     Lager en ny wave med hensyn til hvilken lvl du er på.
	 *     lager en ny liste med prosjektiler.      
	 * 
	 */

	public void startNewRound();

	/**
	 * 
	 *  setter listen med veipunkt som mobben skal følge.
	 * 
	 * @param list          
	 * 
	 */

	public void setWayPoints(List<Point> list);

	/**
	 * 
	 *  setter tårnet som skal plasseres
	 * 
	 * @param tower
	 * 
	 */

	public void setGhostTower(Tower tower);

	/**
	 * @return returnerer tårnet som skal plasseres
	 * 
	 */

	public Tower getGhostTower();

	/**
	 * 
	 *  spilleren tar skade i forhold til hvor mye damage mobben har.
	 *  
	 * @param mob
	 *           
	 * 
	 */

	public void getDamaged(Mob mob);

	/**
	 * 
	 * antallet penger som skal legges til.
	 * 
	 * @param deltaMoneyAmount,
	 *            
	 */
	public void changeMoneyAmount(int deltaMoneyAmount);

	/**
	 * 
	 *  tower som skal kjoepes
	 * 
	 * @param tower
	 *           
	 */
	public void buyTower(Tower tower);

	/**
	 * 
	 *  tower som skal selges
	 * 
	 * @param tower
	 *            
	 */

	void sellTower(Tower tower);

	/**
	 * 
	 * @return antall penger som spiller besitter.
	 */
	public int getMoneyAmount();

	/**
	 * 
	 * @return levelen til spillet
	 */
	public int getLevel();

	/**
	 * 
	 * setter levelen til spillet.
	 * 
	 * @param level
	 *           
	 */

	public void setLevel(int level);

	/**
	 * 
	 * @return returnerer antall liv som spiller har igjen.
	 * 
	 */

	public int getPlayerLives();

	/**
	 * 
	 * setter antall liv som spiller starter med i spillsesjonen.
	 * 
	 * @param playerLives
	 *            
	 */

	public void setPlayerLives(int playerLives);

	/**
	 * 
	 * @return returnerer antall mobs som har spawnet.
	 * 
	 */

	public int getSpawnedMobs();

	/**
	 * 
	 *    setter antall mobs som har spawnet på kartet.
	 * 
	 * @param spawnedMobs
	 * 
	 */

	public void setSpawnedMobs(int spawnedMobs);

	/**
	 * 
	 * @return returnerer listen med tårn
	 * 
	 */

	public List<Tower> getTowerList();

	/**
	 * 
	 * @return returnerer true om runden har startet
	 *           
	 */

	public boolean roundHasStarted();

	/**
	 * 
	 * @return returnerer hvilken wave vi er på.
	 * 
	 */

	public int getCurrentWaveIndex();

	/**
	 * 
	 * setter nåværende wave.
	 * 
	 * @param currentWaveIndex
	 *            
	 */

	public void setCurrentWaveIndex(int currentWaveIndex);

	/**
	 * 
	 * @return returnerer antall waves
	 */

	public int getWaveCounter();

	/**
	 * 
	 * @return returnerer wave.
	 * 
	 */

	public IWave getWave();

	/**
	 * 
	 * Oppdaterer tårn, prosjektiler og mobs i spillet mens det er igang.
	 * 
	 * @param dt
	 * 
	 */

	public void update(float dt);

	/**
	 * 
	 * gjengir tårnene, prosjektilene og mobsene som er i spillet.
	 * 
	 * @param spriteBatch       
	 * 
	 */

	public void render(SpriteBatch spriteBatch);

	/**
	 * 
	 * spawner mobs frem til spawnedmobs er like stor som antall mobs
	 *            som kan være i waven.
	 * 
	 * @param dt,
	 * 
	 */

	public void checkForEnemies(float dt);

	/**
	 * 
	 * @return returnerer listen med waypoints.
	 * 
	 */

	public List<Point> getWaypoints();

	/**
	 * 
	 * legger til prosjektilet pro i listen med prosjektiler.
	 * 
	 * @param pro
	 * 
	 */

	public void addProjectile(Projectile pro);

	/**
	 * 
	 * @return returnerer listen med projektiler.
	 *            
	 * 
	 */

	public ArrayList<Projectile> getProjectiles();

}