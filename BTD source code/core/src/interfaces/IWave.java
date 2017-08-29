package interfaces;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Sprite;

import logicClasses.Mob;

/**
 * Brukere av dette interfacet skal kunne opprette en wave. En wave er bare en
 * samling av mobs. Brukeren skal kunne fjerne en mob fra Waven, legge til mob i
 * waven, få antall mobs i waven, og sjekke om waven er ferdig.
 *
 */

public interface IWave {

	/**
	 * 
	 * @return antall mobs i wave
	 */
	public int getNumberOfMobs();

	/**
	 * 
	 * @return True dersom waven ikke har flere mobs.
	 */
	public boolean isFinished();

	/**
	 * 
	 * @return Returnerer en liste over alle mobsene i en wave.
	 */
	public ArrayList<Mob> getListOfWaveMobs();

	/**
	 * Oppdaterer wave, fjerner mobs som har 0 eller mindre hitpoints
	 * 
	 * @param dt
	 */
	public void update(float dt);

	/**
	 *
	 * @return returnerer hvilken wave vi er på.
	 */

	int getWaveNum();

	/**
	 * legger til mobs i listen med mobs.
	 * 
	 * @param mob
	 */

	public void addMob(Mob mob);

}