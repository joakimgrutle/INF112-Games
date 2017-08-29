package interfaces;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import logicClasses.Mob;

public interface IProjectile {

	/**
	 * 
	 * 
	 * @return farten til prosjektilet
	 */

	public float getSpeed();

	/**
	 * Sjekker om prosjektilet kolliderer med enemy.
	 * 
	 * @return true om de kolliderer, false visst ikke.
	 */

	public boolean checkCollision(Mob mob);

	/**
	 * Får prosjektilet til å bevege seg mot fienden som er valgt. 
	 * 
	 * @param enemy 
	 */
	public void moveProjectile(Mob enemy);


	/**
	 * Tegner spriten til prosjektilet
	 * 
	 * @param batch
	 */

	public void draw(SpriteBatch batch);

	/**
	 * Oppdatere prosjektilets oppførsel
	 * 
	 * @param dt
	 */

	public void update(float dt);
}
