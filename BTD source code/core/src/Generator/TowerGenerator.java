package Generator;

import java.util.ArrayList;
import java.util.Collection;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import interfaces.ITower;
import logicClasses.Tower;
import towers.BeerTower;

public class TowerGenerator {
	
	public static Tower genTower(String type, Vector2 position) {
		Tower tower = null;
	    Sprite towerImg;
		switch(type){
		case "beertower":
			towerImg = new Sprite(new Texture(Gdx.files.internal("image/corona.png")));
			towerImg.setScale(2f, 2f);
			tower = new BeerTower(position);
			break;
		default:
			break;				
		}
		return tower;
		
	}
	
	/**
	 * Lager tilfeldige tårn på posisjoner gitt
	 * @param positions liste av posisjoner som skal fylles
	 * @return en liste av tårn
	 */
	public static Collection<Tower> randTowers(Vector2[] positions){
		String[] names = {"beertower"};
	    Collection<Tower> towers = new ArrayList<Tower>();
	    for(int i = 0; i < positions.length; i++){
	    	int randChoose = IntGenerator.randValues(0, names.length-1);
	    	towers.add(genTower(names[randChoose],positions[i]));
	    }
	    return towers;
		
	}
}
