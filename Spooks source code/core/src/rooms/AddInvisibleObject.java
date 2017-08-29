package rooms;

import java.util.concurrent.TimeUnit;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.oblig4.spooks.Hud;
import com.oblig4.spooks.Spooks;

import logicClasses.Item;
import logicClasses.SpooksGame;

public class AddInvisibleObject {
	ImageButton object;
	
	public AddInvisibleObject(Spooks game, Stage stage, final Hud hud, int width, int height, final String itemName){
		Texture texture = new Texture("images/Transparency100.png");
		TextureRegion textRegion = new TextureRegion(texture);
		TextureRegionDrawable drawable = new TextureRegionDrawable(textRegion);

		object = new ImageButton(drawable);
		object.setSize(200, 128);
		object.setPosition(width, height);
		object.getImage().setVisible(false);
		stage.addActor(object);
		
		object.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(!hud.getInventory().hasItem(itemName)){
					Item item = Item.createItem(itemName);
					hud.addItemToHud(item);
					object.remove();
				}
			}
		});
	}
}
