package items;

import java.util.ArrayList;
import java.util.Arrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import logicClasses.Item;

public class BoltCutterAndScrew extends Item {

	String itemName = "boltcutterandscrew";
	private ArrayList<String> compatibleItems = new ArrayList<String>(Arrays.asList("screwdriver"));
	
	public BoltCutterAndScrew(Sprite sprite) {
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
	return "Boltcutter & screw: Needs a final touch.";
		
	}
	
}
