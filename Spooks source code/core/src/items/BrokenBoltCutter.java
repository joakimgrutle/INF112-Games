package items;

import java.util.ArrayList;
import java.util.Arrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import logicClasses.Item;

public class BrokenBoltCutter extends Item{
	String itemName = "brokenboltcutter";
	private ArrayList<String> compatibleItems = new ArrayList<String>(Arrays.asList("screw"));
	
	public BrokenBoltCutter(Sprite sprite) {
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
		return "Broken Boltcutter: Seems to miss something.";
	}
	

}
