package items;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import logicClasses.Item;

public class PumpkinCarvedRemote extends Item{
	String itemName = "pumpkincarvedremote";
	private ArrayList<String> compatibleItems = new ArrayList<>();
	
	public PumpkinCarvedRemote(Sprite sprite) {
		super(sprite);
		this.setName(itemName);
	
	}

	@Override
	public void draw(SpriteBatch spriteBatch) {
		sprite.setX(position.x);
		sprite.setY(position.y);
		sprite.draw(spriteBatch);
		
	}
	
	@Override
	public String toString(){
		return "Carved pumpkin:\nSomething is off about this pumpkin.";
	}
	
}
