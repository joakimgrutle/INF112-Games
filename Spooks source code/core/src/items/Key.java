package items;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import logicClasses.Item;

public class Key extends Item {
	String itemName = "key";
	private ArrayList<String> compatibleItems = new ArrayList<String>();
	
	public Key(Sprite sprite) {
		super(sprite);
		this.setName(itemName);
	
	}


	@Override
	public void draw(SpriteBatch spriteBatch) {
		sprite.setX(position.x);
		sprite.setY(position.y);
		sprite.draw(spriteBatch);
		
	}
	
	public String toString(){
		return "Key: Unlocks things, duh.";
	}
	

}
