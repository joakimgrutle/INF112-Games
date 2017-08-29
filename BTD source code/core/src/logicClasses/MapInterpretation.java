package logicClasses;

import java.util.ArrayList;
import java.util.Collection;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.oblig4.game.TowerDefence;

/**
 * This class will handle everything specific to the TMX file created by our
 * TILED-specialists. *
 * 
 * @author Nikolai
 * @since 27.04
 *
 */

public class MapInterpretation {
	// Part of placement
	public ArrayList<Rectangle> locationsOccupied;

	private String mapPath;
	private TmxMapLoader ml = new TmxMapLoader();
	private TiledMap tiledMap;
	private String canPlaceTMXObjectLayer = "canPlace"; // 4 object zones within
														// tmx-file
	private String canNotPlaceTMXObjectLayer = "canNotPlace";
	private Collection<MapObject> mapObjectCollection;
	private ArrayList<Rectangle> canPlaceMapRectangleObjects;
	private ArrayList<Rectangle> canNotPlaceMapRectangleObjects;
	private OrthographicCamera camera;
	private Viewport viewport;
	public ArrayList<Rectangle> placementTiles;

	public MapInterpretation(String mapPath) {
		this.tiledMap = ml.load(mapPath);
		this.viewport = new FillViewport(TowerDefence.width, TowerDefence.height);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, TowerDefence.width, TowerDefence.height);

		MapLayer canPlaceLayer = (MapLayer) tiledMap.getLayers().get(canPlaceTMXObjectLayer);
		canPlaceMapRectangleObjects = this.initialize(canPlaceLayer);


		MapLayer canNotPlaceLayer = (MapLayer) tiledMap.getLayers().get(canNotPlaceTMXObjectLayer);
		canNotPlaceMapRectangleObjects = this.initialize(canNotPlaceLayer);


		placementTiles = finalizedCanPlaceTiles(canPlaceMapRectangleObjects, canNotPlaceMapRectangleObjects);
		locationsOccupied = new ArrayList<Rectangle>();
	}

	public ArrayList<Rectangle> finalizedCanPlaceTiles(ArrayList<Rectangle> cP, ArrayList<Rectangle> cntP) {
		boolean placeHere = true;
		int numberOfUps = 0;
		int numberOfRights = 0;
		int wh = 32;
		ArrayList<Rectangle> finalizedList = new ArrayList<Rectangle>();

		for (Rectangle rect : cP) {


			numberOfUps = (int) (rect.getHeight() / 32);

			numberOfRights = (int) (rect.getWidth() / 32);
			

			for (int i = 0; i < numberOfUps; i++) {
				for (int j = 0; j < numberOfRights; j++) {
					Rectangle x = new Rectangle(rect.getX() + wh * j, rect.getY() + wh * i, wh, wh);
					// x = this.rectangleSelfMultiplicationBy2(x);

					for (Rectangle dontPlaceArea : cntP) {
						Rectangle smallerX = new Rectangle(x.getX() + 8, x.getY() + 8, x.getWidth() - 16,
								x.getHeight() - 16);

						if ((dontPlaceArea.contains(smallerX)))
							placeHere = false;

					}
					if (placeHere)
						finalizedList.add(x);

					placeHere = true;

				}

			}

		}
		return finalizedList;
	}

	public ArrayList<Rectangle> getLocationsOccupied() {
		
		return this.locationsOccupied;
	}
	
public void addRectangleToOccupiedList(Rectangle rect){
		
	try{
		locationsOccupied.add(rect);
	
	}catch(NullPointerException e){
	}
	}

public Rectangle findTileToBeOccupied(Vector2 pos) throws NullPointerException{
	for (Rectangle rect : placementTiles){
		if (rect.contains(pos))
			return rect;
	}
	return null;
}


	public boolean containsSpecificTile(ArrayList<Rectangle> locations, Vector2 towerPos) {
		for (Rectangle rect : locations) {
			if (rect.contains(towerPos))
				return true;
		}
		return false;
	}
	
	public boolean canPlaceAtHere (Vector2 towerPos){
		for (Rectangle rect : locationsOccupied)
			if (rect.contains(towerPos))
				return false;
		return true;
	}

	public Rectangle getDrawableRectangle(ArrayList<Rectangle> allOKTiles, Vector2 pos) {
		Rectangle thisTile = new Rectangle(-900, -900, 2, 2); //VERY UGLY WORKAROUND

		for (Rectangle tiles : allOKTiles)
			if (tiles.contains(pos))
				return tiles;

		return thisTile;

	}

	public ArrayList<Rectangle> getFinalOKPlacementList() {

		return placementTiles;
	}

	public ArrayList<Rectangle> initialize(MapLayer maplayer) {
		ArrayList<Rectangle> objectRectangleList = new ArrayList<Rectangle>();
		for (MapObject object : maplayer.getObjects()) {
			if (object instanceof RectangleMapObject) {
				Rectangle rect = ((RectangleMapObject) object).getRectangle();
				Rectangle fixedRectangle = this.rectangleSelfMultiplicationBy2(rect);

				objectRectangleList.add(fixedRectangle);
			}

		}

		return objectRectangleList;
	}

	public Rectangle getCurrentTile(Vector2 pos) {
		for (Rectangle tiles : placementTiles) {
			if (tiles.contains(pos)) {

				return tiles;
			}
		}
		return null;
	}

	public boolean mouseInputWithinTileObject(Vector2 point) {
		for (Rectangle rect : placementTiles) {
			if (rect.contains(point)) {
				return true;
			}
		}
		return false;
	}

	private Rectangle rectangleSelfMultiplicationBy2(Rectangle rect) {
		rect = rect.set(rect.getX() * 2, rect.getY() * 2, rect.getWidth() * 2, rect.getHeight() * 2);

		return rect;
	}
}
