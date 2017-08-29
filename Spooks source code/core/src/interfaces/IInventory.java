package interfaces;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.oblig4.spooks.ItemListing;

import logicClasses.Item;

public interface IInventory {
	
	/**
	 * @param item
	 *            - Itemet som skal legges til i inventory
	 * 
	 * 		Legger et item inn i inventory. 
	 */

	public void addItem(Item item);
	
	/**
	 * 
	 * 		Fjerner brukte items fra inventory. 
	 */

	public void removeUsedItems();
	
	/**
	 * @param itemName
	 *            - navnet på itemet som du sjekker om er i inventory. 
	 * 
	 * @return True dersom itemet er i inventory, false ellers. 
	 */

	public boolean hasItem(String itemName);

	

}
