package items;

import java.util.ArrayList;
import java.util.Arrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import logicClasses.Item;

public class Screw extends Item {
	String itemName = "screw";
	private ArrayList<String> compatibleItems = new ArrayList<String>(Arrays.asList("brokenboltcutter"));
	
	public Screw(Sprite sprite) {
		super(sprite);
		this.setName(itemName);
		this.setCompatibleList(compatibleItems);
		this.setConsumeable(true);
	
	}

	@Override
	public void draw(SpriteBatch spriteBatch) {
		sprite.setX(position.x);
		sprite.setY(position.y);
		sprite.draw(spriteBatch);
		
	}
	@Override
	public String toString(){
		return "Screw: Seems loose. Belongs somewhere.";
	}
	
}
