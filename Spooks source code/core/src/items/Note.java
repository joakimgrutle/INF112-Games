package items;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import logicClasses.Item;

public class Note extends Item {
	String itemName = "note";
	String lore;
	
	public Note(String lore) {
		super(new Sprite(new Texture(Gdx.files.internal("images/oldNote.png"))));
		this.setName(itemName);
		this.lore = lore;
	
	}


	@Override
	public void draw(SpriteBatch spriteBatch) {
		sprite.setX(position.x);
		sprite.setY(position.y);
		sprite.draw(spriteBatch);
		
	}
	@Override
	public String toString(){
		return "Note: Piece of paper with clues.\n"+lore;
		
	}
	public String readNote(){
		return lore;
	}
	
	

}
