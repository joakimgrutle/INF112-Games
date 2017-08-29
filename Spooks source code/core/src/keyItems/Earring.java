package keyItems;

import java.util.ArrayList;
import java.util.Arrays;

import com.badlogic.gdx.graphics.g2d.Sprite;

import logicClasses.KeyItem;

public class Earring extends KeyItem {

	public Earring(String itemName, Sprite retrievedSprite, Sprite destroyedSprite) {
		super(itemName, retrievedSprite, destroyedSprite);
		
	}
	
	@Override
	public String toString(){
		if (this.isDestroyed())
			return "Burned Earring: Remains of the haunted earring.\nPurified.";
		return "Haunted Earring: Possesed. Destroying it might\nbe the key to leave the house.";
		
	}

}
