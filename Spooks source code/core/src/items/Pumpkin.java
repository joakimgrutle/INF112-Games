package items;

import java.util.ArrayList;
import java.util.Arrays;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import logicClasses.Item;

public class Pumpkin extends Item{
	
	String itemName = "pumpkin";
	private ArrayList<String> compatibleItems = new ArrayList<>(Arrays.asList("knife"));
	
	public Pumpkin(Sprite sprite) {
		super(sprite);
		this.setName(itemName);
		this.setCompatibleList(compatibleItems);
	
	}

	@Override
	public void draw(SpriteBatch spriteBatch) {
		sprite.setX(position.x);
		sprite.setY(position.y);
		sprite.draw(spriteBatch);
		
	}
	
	@Override
	public String toString(){
		return "Pumpkin: Just a pumpkin.\nmakes a weird noise when you shake it.";
	}
	

}
