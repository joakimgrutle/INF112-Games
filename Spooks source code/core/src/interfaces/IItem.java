package interfaces;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.oblig4.spooks.Hud;
import com.oblig4.spooks.ItemInfoWindow;
import com.oblig4.spooks.ItemListing;
import com.oblig4.spooks.Spooks;

import items.BoltCutter;
import items.BoltCutterAndScrew;
import items.BrokenBoltCutter;
import items.Candle;
import items.Candlelight;
import items.Candlestick;
import items.CandlestickAndCandle;
import items.Key;
import items.Knife;
import items.Lighter;
import items.Pumpkin;
import items.PumpkinCarvedRemote;
import items.RemoteControl;
import items.Screw;
import items.Screwdriver;
import logicClasses.Item;
import logicClasses.SpooksGame;

public interface IItem {

	/**
	 * @param item
	 *            - et item som kombineres med ett annet item.
	 * 
	 * @return Ett nytt item.
	 */
	public ArrayList<Item> combineTwoItems(Item item);

	/**
	 * @param item
	 *            - et item som skal skjekkes for kompabilitet.
	 * 
	 * @return True dersom items kan kombineres.
	 */
	public boolean canCombineTwoItems(Item item);

	/**
	 * 
	 * @param item
	 *            - et item
	 * 
	 * @return Liste over items som kan kombineres med item.
	 */
	public List<String> getCompatibleList(Item item);

	/**
	 * 
	 * @return Navnet til item.
	 */
	public String getItemName();

	/**
	 * 
	 * @return Tilstanden til item, om den er brukt eller ikke.
	 */
	public boolean getUsedStatus();

	/**
	 * 
	 * @param vec
	 *            - en posisjon
	 * 
	 *            Setter posisjonen til item til vec. 
	 */

	public void setPosition(Vector2 vec);

	/**
	 * 
	 * @param spriteBatch
	 *            - et bilde
	 * 
	 *            Metoden tegner bildet som gis som parameter.
	 */

	public void draw(SpriteBatch spriteBatch);

	/**
	 * 
	 * @param e
	 *            - en boolean verdi
	 * 
	 *            Setter om itemet er brukt eller ikke til e.
	 */

	public void setUsedStatus(boolean e);

	/**
	 * 
	 * 
	 * @return returnerer om itemet kan plukkes opp eller ikke.
	 */

	public boolean getPickableStatus();

	/**
	 * 
	 * @param e
	 *            - boolean verdi
	 * 
	 * @return setter om itemet kan brukes.
	 */

	public void setConsumeable(boolean e);

	/**
	 * 
	 * 
	 * 
	 * @return returnerer om itemet kan brukes eller ikke.
	 */

	public boolean getConsumeableStatus();

	/**
	 * 
	 * @param hud
	 *            - en hud
	 * 
	 * 
	 */

	public void infoWindow(Hud hud);
}
