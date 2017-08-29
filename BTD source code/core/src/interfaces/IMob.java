package interfaces;

import java.awt.Point;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Brukere av dette interfacet, kan opprette Acors, som rektangler. De vil ha
 * kontroll på hvor mye skade en actor gjør, hvor mye HP en actor har, x/y
 * retningen en actor beveger seg i. Man skal også kunne sette actoren til å
 * bevege seg sakte i et bestemt tidsintervall.
 * 
 */
public interface IMob {

	/**
	 * Denne metoden vil bestemme hvor mye skade en Mob kan gjøre.
	 * 
	 * @param damage
	 *            
	 */
	public void setDamage(int damage);

	/**
	 * Denne metoden returnerer hvor mye HP en mob har.
	 * 
	 * @return HitPoints til en mob
	 */
	public int getHitPoints();

	/**
	 * Denne metoden returnerer hvor mye skade en mob skal gjøre.
	 * 
	 * @return Hvor mye skade en mob skal gjøre
	 */
	public int getDamage();

	/**
	 * Denne metoden vil sette en statuseffekt, slow, i et gitt tidsintervall.
	 * 
	 * @param time
	 *         
	 */
	public void slowMob(float time);


	/**
	 * 
	 * @return liste av waypoints.
	 */
	public List<Point> getWaypoints();

	/**
	 * 
	 * @return spriten til mob
	 */
	public Sprite getSprite();

	/**
	 * 
	 * @return gold som mob dropper
	 */
	public int getGold();

	/**
	 * Denne metoden oppdaterer mob'ens koordinater til neste posisjon
	 * 
	 * @param dt
	 */
	public void update(float dt);

	/**
	 * farten til mobben
	 * 
	 * @param dt
	 * @return returnerer farten til mobben.
	 */

	public float getVelocity(float dt);

	/**
	 * hvor mye skade mobben tar fra et prosjektil. 
	 * om hitpoints er lik null, så vil ikke mobben være active lengre. 
	 * 
	 * @param damage
	 */

	public void damaged(int damage);

	/**
	 * @return returnerer posisjonen til mobben.
	 */

	public Vector2 getPosition();

	/**
	 * @return returnerer hvor langt mobben har beveget seg.
	 */

	public float getDistTravelled();

	/**
	 * tegner spriten til moben.
	 */

	public void draw(SpriteBatch spriteBatch);
}
