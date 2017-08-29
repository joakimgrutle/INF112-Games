package logicClasses;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Gamesprites {

	protected Vector2 position;
	protected Sprite sprite;
	protected boolean active;
	
	public Gamesprites(Sprite sprite){
		this.sprite = sprite;
		this.active = false;
	}
	
	public abstract void draw(SpriteBatch spriteBatch);
	
	public void update(float delta){
		sprite.setX(this.position.x);
		sprite.setY(this.position.y);
	}
	
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public void setPosition(Vector2 position){
		this.position = position;
		sprite.setX(position.x);
		sprite.setY(position.y);
	}
	
	
	public Vector2 getPosition() {
		return position;
	}
}