package interfaces;

import java.util.Collection;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import logicClasses.Mob;
import logicClasses.TDGame;
import logicClasses.Tower;

/**
 * Brukere av dette interfacet skal kunne opprette et tårn, som en rektangel.
 * Man skal kunne vite skaden et tårn kan gjøre, om det kan oppgraderes, hvilken
 * "level" tårnet er oppgradert til, hvor mye det koster. Et tårn skal også
 * kunne skyte en gitt Mob.
 *
 */

public interface ITower {

	/**
	 * Metoden opprette et nytt, "oppgradert" tårn, prisen bestemmes ut ifra
	 * hvilket nivå tårnet er i.
	 * 
	 * @return et nytt tårn, som er en oppgradert versjon av samme tårn
	 */
	// public ITower upgradeTower();
	/**
	 * 
	 * @return en liste av oppdateringer en kan gjøre med tårnet
	 */
	// public Collection<Tower> getUpgradeOption();

	/**
	 * Metoden skal returnere nivået et tårn er oppgradert til.
	 * 
	 * @return Hvilket nivå tårnet er oppgradert til.
	 */
	// public int getTowerLevel();

	/**
	 * Metoden skal returnere prisen på tårnet.
	 * 
	 * @return Prisen på dette tårnet
	 */
	public int getPrice();

	/**
	 * Metode som sjekker om man kan kjøpe dette tårnet.
	 * 
	 * @param money
	 *            sjekker om money er større enn getPrice().
	 * @return True dersom money er større enn eller lik getPrice(), false ellers. 
	 */
	public boolean canBuy(int money);

	/**
	 * Denne metoden skal returnere skaden et tårn kan gjøre.
	 * 
	 * @return Skaden tårnet kan gjøre på en IMob.
	 */
	public int getDamage();

	/**
	 * @return posisjonen til tårnet
	 */
	public Vector2 getPosition();

	/**
	 * Oppdater tårnet, skyter en mob om den er i range.
	 * 
	 * @param dt
	 */
	public void update(float dt);

	/**
	 * Metode som tegner tårnet.
	 * 
	 * @param spriteBatch
	 * 
	 */

	public void draw(SpriteBatch spriteBatch);

	/**
	 * 
	 * Oppdaterer tårnets mål og skyter mot det målet.
	 * 
	 * @param delta
	 * 
	 * @param game
	 * 
	 */

	public void update(float delta, TDGame game);

	/**
	 * 
	 * Oppdaterer tårnets skuddcooldown, når cooldown == 0, så lages det et
	 * prosjektil som legges til i game sin liste med prosjektiler.
	 * 
	 * @param delta
	 * 
	 * @param game
	 * 
	 * 
	 */

	public void shoot(float delta, TDGame game);

	/**
	 * 
	 * @return true om det slowes, false ellers.
	 * 
	 */

	public boolean doesSlow();

	/**
	 * 
	 * @param mob
	 *            setter målet som tårnet skal skyte
	 * 
	 */

	public void setTarget(Mob mob);
	
	
	

}
