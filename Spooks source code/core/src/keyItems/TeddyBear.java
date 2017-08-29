package keyItems;

import com.badlogic.gdx.graphics.g2d.Sprite;

import logicClasses.KeyItem;

public class TeddyBear extends KeyItem{

	public TeddyBear(String itemName, Sprite retrievedSprite, Sprite destroyedSprite) {
		super(itemName, retrievedSprite, destroyedSprite);
		
	}
	@Override
	public String toString(){
		if (this.isDestroyed())
			return "Burned Teddybear: Remains of the haunted teddybear.\nPurified.";
		return "Haunted Teddybear: Possesed. Destroying it might\nbe the key to leave the house.";
		
	}

}
