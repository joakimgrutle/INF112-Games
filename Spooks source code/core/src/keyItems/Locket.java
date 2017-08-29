package keyItems;

import com.badlogic.gdx.graphics.g2d.Sprite;

import logicClasses.KeyItem;

public class Locket extends KeyItem {

	public Locket(String itemName, Sprite retrievedSprite, Sprite destroyedSprite) {
		super(itemName, retrievedSprite, destroyedSprite);
	}
	
	@Override
	public String toString(){
		if (this.isDestroyed())
			return "Burned Locket: Remains of the haunted locket.\nPurified.";
		return "Haunted Locket: Possesed. Destroying it might\nbe the key to leave the house.";
		
	}

}
