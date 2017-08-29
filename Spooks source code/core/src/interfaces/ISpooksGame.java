package interfaces;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import logicClasses.Item;

public interface ISpooksGame {
	
	/**
	 * Starts the timer
	 */
	public void startTimer();
	
	/**
	 * Stops the timer
	 */
	public void stopTimer();
	
	/**
	 * 
	 * @return whether or not timer is over
	 */
	public boolean isTimeOver();
	
	/**
	 * Returns the time game has run. Ignores time elapsed while timer is stopped.
	 * 
	 * @return time elapsed
	 */
	public long getElapsedTime();
	
	/**
	 * Adds one minute to the game-duration
	 */
	public void addOneMinute();


	/**
	 * Set ghost item
	 * @param item
	 */
	void setGhostItem(Item item);

	/**
	 * Get ghost item
	 * @return
	 */
	Item getGhostItem();

	void update(float dt);

	void render(SpriteBatch spriteBatch);

	void setCurrentItem(Item item);

	Item getCurrentItem();

	void combineItem(Item item);
	
	
}
