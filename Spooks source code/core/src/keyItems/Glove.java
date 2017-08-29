package keyItems;

import com.badlogic.gdx.graphics.g2d.Sprite;

import logicClasses.KeyItem;

public class Glove extends KeyItem {

	public Glove(String itemName, Sprite retrievedSprite, Sprite destroyedSprite) {
		super(itemName, retrievedSprite, destroyedSprite);
	}
	
	@Override
	public String toString(){
		if (this.isDestroyed())
			return "Burned Glove: Remains of the haunted glove.\nPurified.";
		return "Haunted Glove: Possesed. Destroying it might\nbe the key to leave the house.";
		
	}

}
