package com.oblig4.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import logicClasses.TDGame;
import logicClasses.Tower;
import towers.BeerTower;
import towers.JaegerMeister;
import towers.VodkaTower;
import towers.WhiskeyTower;
import towers.WineTower;

public class TowerGUI {
	private ImageButton beerTower;
	private ImageButton jagerTower;
	private ImageButton wineTower;
	private ImageButton vodkaTower;
	private ImageButton whiskeyTower;
	private TDGame gameState;
	private Stage stage;
	private Hud hud;
	private Array<TowerListing> towerList;

	private int manualXOffset;
	private int manualYOffset;

	public TowerGUI(TDGame gameState, Hud hud) {
		this.stage = hud.stage;
		this.gameState = gameState;
		this.hud = hud;
		this.towerList = new Array<>();
	}

	public void createTowerButtons() {
		// Create shop option: beer tower
		Texture beerTexture = new Texture(Gdx.files.internal("image/coronaShop2.png"));
		TextureRegion beerRegion = new TextureRegion(beerTexture);
		TextureRegionDrawable beerRegionDraw = new TextureRegionDrawable(beerRegion);
		beerTower = new ImageButton(beerRegionDraw);
		BeerTower bt = new BeerTower(new Vector2(0, 0));
		TowerListing beerListing = new TowerListing(beerTower, "beer", BeerTower.getTowerPrice(), 1, "damage", hud, bt);

		// Create shop option: jager tower
		Texture jagerTexture = new Texture(Gdx.files.internal("image/jagermeisterShop3.png"));
		TextureRegion jagerRegion = new TextureRegion(jagerTexture);
		TextureRegionDrawable jagerRegionDraw = new TextureRegionDrawable(jagerRegion);
		jagerTower = new ImageButton(jagerRegionDraw);
		JaegerMeister jt = new JaegerMeister(new Vector2(0, 0));
		TowerListing jagerListing = new TowerListing(jagerTower, "jager", JaegerMeister.getTowerPrice(), 1, "damage",
				hud, jt);

		// Create shop option: wine tower
		Texture wineTexture = new Texture(Gdx.files.internal("image/wineShop2.png"));
		TextureRegion wineRegion = new TextureRegion(wineTexture);
		TextureRegionDrawable wineRegionDraw = new TextureRegionDrawable(wineRegion);
		wineTower = new ImageButton(wineRegionDraw);
		WineTower wt = new WineTower(new Vector2(0, 0));
		TowerListing wineListing = new TowerListing(wineTower, "wine", WineTower.getTowerPrice(), 1, "damage", hud, wt);

		// Create shop option: vodka tower
		Texture vodkaTexture = new Texture(Gdx.files.internal("image/vodka.png"));
		TextureRegion vodkaRegion = new TextureRegion(vodkaTexture);
		TextureRegionDrawable vodkaRegionDraw = new TextureRegionDrawable(vodkaRegion);
		vodkaTower = new ImageButton(vodkaRegionDraw);
		VodkaTower vt = new VodkaTower(new Vector2(0, 0));
		TowerListing vodkaListing = new TowerListing(vodkaTower, "vodka", VodkaTower.getTowerPrice(), 1, "damage", hud,
				vt);

		// Create shop option: whiskey tower
		Texture whiskeyTexture = new Texture(Gdx.files.internal("image/whiskeyShop.png"));
		TextureRegion whiskeyRegion = new TextureRegion(whiskeyTexture);
		TextureRegionDrawable whiskeyRegionDraw = new TextureRegionDrawable(whiskeyRegion);
		whiskeyTower = new ImageButton(whiskeyRegionDraw);
		WhiskeyTower wht = new WhiskeyTower(new Vector2(0, 0));
		TowerListing whiskeyListing = new TowerListing(whiskeyTower, "whiskey", WhiskeyTower.getTowerPrice(), 1,
				"damage", hud, wht);

		// Add tower options to list
		towerList.add(beerListing);
		towerList.add(jagerListing);
		towerList.add(wineListing);
		towerList.add(vodkaListing);
		towerList.add(whiskeyListing);

		// Create background and shop title
		Label shopTitle = new Label("Buy Towers", TowerDefence.uiSkin);
		shopTitle.setFontScale(0.7f);
		// Create shop menu
		Table towerTable = new Table(TowerDefence.uiSkin);
		towerTable.setPosition(hud.viewport.getWorldWidth() - 65, hud.viewport.getWorldHeight() - 380);
		towerTable.add(shopTitle).top().align(Align.center).padRight(15f);
		towerTable.row().padTop(35f);

		// Add Towers to shop menu
		for (TowerListing tower : towerList) {
			towerTable.add(tower);
			towerTable.row().padTop(100f);
		}
		stage.addActor(towerTable);
	}

	public void buyTower() {
		Vector2 pos = new Vector2(Gdx.input.getX(), (Gdx.graphics.getHeight() - Gdx.input.getY()));

		Tower tower = null;

		if (this.gameState.mapping.mouseInputWithinTileObject(pos)) {
			if (gameState.mapping.canPlaceAtHere(pos)) {

				Vector2 pos2 = new Vector2(gameState.mapping.getCurrentTile(pos).getX(),
						gameState.mapping.getCurrentTile(pos).getY());
				if (hud.towerName == "beer") {
					Sprite towerImg = new Sprite(new Texture(Gdx.files.internal("image/corona.png")));
					towerImg.setScale(2f, 2f);
					tower = new BeerTower(pos2);
					TowerDefence.manager.get("sound/tower/buy/buy-beer.ogg", Sound.class).play();

					hud.buildTower = false;
					gameState.mapping.addRectangleToOccupiedList(this.gameState.mapping
							.getDrawableRectangle(this.gameState.mapping.getFinalOKPlacementList(), pos));

				} else if (hud.towerName == "jager") {
					Sprite towerImg = new Sprite(new Texture(Gdx.files.internal("image/jagermeister.png")));
					towerImg.setScale(2f, 2f);
					tower = new JaegerMeister(pos2);

					TowerDefence.manager.get("sound/tower/buy/buy-jager.ogg", Sound.class).play();

					hud.buildTower = false;

					gameState.mapping.addRectangleToOccupiedList(this.gameState.mapping
							.getDrawableRectangle(this.gameState.mapping.getFinalOKPlacementList(), pos));

				} else if (hud.towerName == "wine") {
					Sprite towerImg = new Sprite(new Texture(Gdx.files.internal("image/wine.png")));
					towerImg.setScale(2f, 2f);
					tower = new WineTower(pos2);

					TowerDefence.manager.get("sound/tower/buy/buy-wine.ogg", Sound.class).play();

					hud.buildTower = false;

					gameState.mapping.addRectangleToOccupiedList(this.gameState.mapping
							.getDrawableRectangle(this.gameState.mapping.getFinalOKPlacementList(), pos));
				}

				else if (hud.towerName == "vodka") {
					Sprite towerImg = new Sprite(new Texture(Gdx.files.internal("image/vodka.png")));
					towerImg.setScale(2f, 2f);
					tower = new VodkaTower(pos2);

					TowerDefence.manager.get("sound/tower/buy/buy-vodka.ogg", Sound.class).play();

					hud.buildTower = false;

					gameState.mapping.addRectangleToOccupiedList(this.gameState.mapping
							.getDrawableRectangle(this.gameState.mapping.getFinalOKPlacementList(), pos));

				} else if (hud.towerName == "whiskey") {
					Sprite towerImg = new Sprite(new Texture(Gdx.files.internal("image/whiskey.png")));
					towerImg.setScale(2f, 2f);
					tower = new WhiskeyTower(pos2);

					TowerDefence.manager.get("sound/tower/buy/buy-whiskey.ogg", Sound.class).play();

					hud.buildTower = false;

					gameState.mapping.addRectangleToOccupiedList(this.gameState.mapping
							.getDrawableRectangle(this.gameState.mapping.getFinalOKPlacementList(), pos));
				}
				gameState.buyTower(tower);
			}
		}

	}
}
