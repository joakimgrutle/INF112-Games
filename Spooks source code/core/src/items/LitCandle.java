package items;


	import java.util.ArrayList;
	import com.badlogic.gdx.Gdx;
	import com.badlogic.gdx.graphics.Texture;
	import com.badlogic.gdx.graphics.g2d.Sprite;
	import com.badlogic.gdx.graphics.g2d.SpriteBatch;
	import logicClasses.Item;
	public class LitCandle extends Item {
		String itemName = "litcandle";
		
		public LitCandle() {
			super(new Sprite(new Texture(Gdx.files.internal("images/candle_lit.png"))));
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
			return "Lit Candle: Lights up the room and your soul.";
		}
		
	

}
