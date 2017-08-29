package items;

import java.util.ArrayList;
import java.util.Arrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import logicClasses.Item;

public class Hand extends Item{
	String itemName = "hand";
	
	
	public Hand(Sprite sprite) {
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
		return "A hand. Maybe it could be of good use?";
	}
	

}