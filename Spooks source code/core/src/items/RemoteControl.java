package items;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import logicClasses.Item;

public class RemoteControl extends Item{
	
	String itemName = "remotecontrol";
	private ArrayList<String> compatibleItems = new ArrayList<String>();
	
	public RemoteControl(Sprite sprite) {
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
		return "Remote Control: Sticky, but it works.";
	}
	
}
