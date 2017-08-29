package items;

import java.util.ArrayList;
import java.util.Arrays;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import logicClasses.Item;

public class Flashlight extends Item{
	
	String itemName = "flashlight";
	
	public Flashlight(Sprite sprite) {
		super(sprite);
		this.setName(itemName);
	
	}

	
	@Override
	public String toString(){
		return "Flashlight: Useful for seeing in the dark.";
	}
}
