package items;

import java.util.ArrayList;
import java.util.Arrays;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import logicClasses.Item;

public class Lighter extends Item{
	
	String itemName = "lighter";
	private ArrayList<String> compatibleItems = new ArrayList<>(Arrays.asList("candlestickandcandle"));
	
	public Lighter(Sprite sprite) {
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
		return "Lighter: Lights things on fire.\nAlmost out of fuel.";
	}
	

}
