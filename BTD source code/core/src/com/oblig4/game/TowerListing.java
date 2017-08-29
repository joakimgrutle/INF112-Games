package com.oblig4.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import logicClasses.TDGame;
import logicClasses.Tower;
import towers.BeerTower;
import towers.JaegerMeister;
import towers.VodkaTower;
import towers.WineTower;
import towers.WhiskeyTower;

public class TowerListing extends Group {

	public TowerListing(final ImageButton towerButton, final String name, int price, int damage, String type, final Hud hud, Tower tower) {

		// scale and add to actor
		towerButton.setPosition(getX() - 70, getY() - 40);
		addActor(towerButton);

		Table labelTable = new Table();
		// item name
		Label labelName = new Label(name, TowerDefence.uiSkin);
		labelName.setAlignment(Align.center);
		//labelName.getStyle().font.getData().setScale(1);
		
		// item price
		Label labelPrice = new Label("Price: " + tower.getPrice() + "$", TowerDefence.uiSkin);
		labelPrice.setAlignment(Align.center);
		
		// Type label
		Label labelType = new Label("Type: " , TowerDefence.uiSkin);
		labelType.setAlignment(Align.center);
		
		// Slow type
		Label labelSlowType = new Label("Slows "  , TowerDefence.uiSkin);
		labelSlowType.setAlignment(Align.center);
		
		// Fast tower
		Label labelFastType = new Label("Fast " , TowerDefence.uiSkin);
		labelFastType.setAlignment(Align.center);
		
		// No Type
		Label labelNoType = new Label("Normal" , TowerDefence.uiSkin);
		labelNoType.setAlignment(Align.center);
		
		// Instakill
		Label labelInstaKill = new Label("5% K.O." , TowerDefence.uiSkin);
		labelInstaKill.setAlignment(Align.center);
		
		// Attribute
		Label labelDamage = new Label("Damage: " + tower.getDamage(), TowerDefence.uiSkin);
		labelDamage.setAlignment(Align.center);

		labelName.setFontScale(0.6f);
		labelPrice.setFontScale(0.6f);
		labelSlowType.setFontScale(0.6f);
		labelFastType.setFontScale(0.6f);
		labelNoType.setFontScale(0.6f);
		labelDamage.setFontScale(0.6f);
		labelInstaKill.setFontScale(0.6f);
		labelType.setFontScale(0.6f);
		
		labelTable.setPosition(getX() + 5, getY());
		labelTable.add(labelName).padTop(10);
		labelTable.row();
		labelTable.add(labelPrice);
		labelTable.row();
		labelTable.add(labelDamage);
		labelTable.row();
		labelTable.add(labelType);
		labelTable.row();
		
		
		// show tower type
		if (tower.doesSlow()) {
			labelTable.add(labelSlowType);
			}
		
		else if(tower.getType().equals("VodkaTower")) {
			labelTable.add(labelFastType);
		}
		
		else if (tower.getType().equals("WineTower")) {
			labelTable.add(labelInstaKill);
		}
		
		else {
			labelTable.add(labelNoType);
		}
	
		
		labelTable.padLeft(5f);
		addActor(labelTable);

		towerButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Sprite towerImg;
				
				if (TDGame.getInstance().getMoneyAmount() >= BeerTower.getTowerPrice() && name == "beer") {
					towerImg = new Sprite(new Texture(Gdx.files.internal("image/corona.png")));
					towerImg.setScale(2f, 2f);
					TDGame.getInstance().setGhostTower(
							new BeerTower(new Vector2((Gdx.input.getX() + towerImg.getWidth()/2), (Gdx.graphics.getHeight() - Gdx.input.getY()) + towerImg.getHeight()/2)));
					hud.buildTower = true;
					hud.towerName = "beer";
				} 
				
				else if (TDGame.getInstance().getMoneyAmount() >= JaegerMeister.getTowerPrice() && name == "jager") {
					towerImg = new Sprite(new Texture(Gdx.files.internal("image/jagermeister.png")));
					towerImg.setScale(2f, 2f);
					TDGame.getInstance().setGhostTower(
							new JaegerMeister(new Vector2((Gdx.input.getX() + towerImg.getWidth()/2), (Gdx.graphics.getHeight() - Gdx.input.getY()) + towerImg.getHeight()/2)));
					hud.buildTower = true;
					hud.towerName = "jager";
				
				}
				
				else if (TDGame.getInstance().getMoneyAmount() >= WineTower.getTowerPrice() && name == "wine") {
					towerImg = new Sprite(new Texture(Gdx.files.internal("image/wine.png")));
					towerImg.setScale(2f, 2f);
					TDGame.getInstance().setGhostTower(
							new WineTower(new Vector2(new Vector2((Gdx.input.getX() + towerImg.getWidth()/2), (Gdx.graphics.getHeight() - Gdx.input.getY()) + towerImg.getHeight()/2))));
					hud.buildTower = true;
					hud.towerName = "wine";
				}
				
				else if (TDGame.getInstance().getMoneyAmount() >= VodkaTower.getTowerPrice() && name == "vodka") {
					towerImg = new Sprite(new Texture(Gdx.files.internal("image/vodka.png")));
					towerImg.setScale(2f, 2f);
					TDGame.getInstance().setGhostTower(
							new VodkaTower(new Vector2(new Vector2((Gdx.input.getX() + towerImg.getWidth()/2), (Gdx.graphics.getHeight() - Gdx.input.getY()) + towerImg.getHeight()/2))));
					hud.buildTower = true;
					hud.towerName = "vodka";
				}
				
				else if (TDGame.getInstance().getMoneyAmount() >= WhiskeyTower.getTowerPrice() && name == "whiskey") {
					towerImg = new Sprite(new Texture(Gdx.files.internal("image/whiskey.png")));
					towerImg.setScale(2f, 2f);
					TDGame.getInstance().setGhostTower(
							new WhiskeyTower(new Vector2(new Vector2((Gdx.input.getX() + towerImg.getWidth()/2), (Gdx.graphics.getHeight() - Gdx.input.getY()) + towerImg.getHeight()/2))));
					hud.buildTower = true;
					hud.towerName = "whiskey";
				}

			}
		});
	}
}
