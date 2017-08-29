package items;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import logicClasses.Item;

public class BoltCutter extends Item {
	String itemName = "boltcutter";
	private ArrayList<String> compatibleItems = new ArrayList<String>();
	
	public BoltCutter(Sprite sprite) {
		super(sprite);
		this.setName(itemName);
	
	}
	
	@Override
	public String toString(){
		return "Boltcutter: Useful to cut things.";
	}


	
}
