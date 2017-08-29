package interfaces;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface IKeyItem {

	/**
	 * 
	 * @return Name of the key item.
	 */
	public String getItemName();

	
	/**
	 * 
	 * @return True if the item has been destroyed, false if it has not been
	 *         destroyed.
	 */

	public boolean isDestroyed();

	

	/**
	 * 
	 * @param spriteBatch
	 *            - the spritebatch that will be drawn
	 */

	public void draw(SpriteBatch spriteBatch);

	/**
	 * 
	 * Method that destroys a key item if you have retrieved it. 
	 */

	public void destroyKeyItem();

}
