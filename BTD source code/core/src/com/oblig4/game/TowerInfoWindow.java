package com.oblig4.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import logicClasses.TDGame;
import logicClasses.Tower;
import towers.BeerTower;
import towers.JaegerMeister;
import towers.VodkaTower;
import towers.WhiskeyTower;
import towers.WineTower;

public class TowerInfoWindow extends Window {

	TextButton closeButton;
	public static boolean isOpen = false;
	public static TowerInfoWindow instance;

	public TowerInfoWindow(final Tower tower, Skin skin, final Hud hud, Vector2 pos, boolean anotherOne) {
		// Setup Window
		super("", skin);
		super.setSize(200, 200);
		super.setPosition(445, 350);

		// Add close button
		closeButton = new TextButton("X", skin);
		closeButton.setTransform(true);
		closeButton.setScale(0.7f);
		closeButton.moveBy(150, 135);
		closeButton.addListener(new ClickListener() {
			
			
			@Override
			public void clicked(InputEvent event, float x, float y) {
				TowerInfoWindow.isOpen = false;
				remove();
			}
		});

		// Create sell button
		TextButton sellTowerButton = new TextButton("Sell:", skin);
		sellTowerButton.setTransform(true);
		sellTowerButton.setScale(0.65f);
		sellTowerButton.moveBy(30, 45);
		sellTowerButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				TDGame.getInstance().sellTower(tower);
				TowerInfoWindow.isOpen = false;
				remove();
			}
		});

		TextButton upgradeTowerButton = new TextButton("Upgrade:", skin);
		upgradeTowerButton.setTransform(true);
		upgradeTowerButton.setScale(0.65f);
		upgradeTowerButton.moveBy(30, 7);
		upgradeTowerButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				TDGame.getInstance().upgradeTower(tower);
				TowerInfoWindow.isOpen = false;
				remove();
				
			}
		});
		// Add tower Image
		Image towerImg = new Image(tower.getSprite().getTexture());
		
		
		
		
		
		if (!tower.getType().equals("VodkaTower"))
			towerImg.setScale(2f);
		else
			towerImg.setScale(1f);
		towerImg.moveBy(15, 50);
		// Add tower info
		Label upgrade = null;
		Label sell = null;

		if (tower.getLevel() < BeerTower.getUpgradeCost().length && tower instanceof BeerTower) {

			upgrade = new Label(String.valueOf(BeerTower.getUpgradeCost()[tower.getLevel()]) + "$", skin);
			sell = new Label("75%", skin);
		} else if (tower.getLevel() < WineTower.getUpgradeCost().length && tower instanceof WineTower) {
			upgrade = new Label(String.valueOf(WineTower.getUpgradeCost()[tower.getLevel()]) + "$", skin);
			sell = new Label("75%:", skin);
		} else if (tower.getLevel() < JaegerMeister.getUpgradeCost().length && tower instanceof JaegerMeister) {
			upgrade = new Label(String.valueOf(JaegerMeister.getUpgradeCost()[tower.getLevel()]) + "$", skin);
			sell = new Label("75%", skin);
		} else if (tower.getLevel() < WhiskeyTower.getUpgradeCost().length && tower instanceof WhiskeyTower) {
			upgrade = new Label(String.valueOf(WhiskeyTower.getUpgradeCost()[tower.getLevel()]) + "$", skin);
			sell = new Label("75%:", skin);
		} else if (tower.getLevel() < VodkaTower.getUpgradeCost().length && tower instanceof VodkaTower) {
			upgrade = new Label(String.valueOf(VodkaTower.getUpgradeCost()[tower.getLevel()]) + "$", skin);
			sell = new Label("75%:", skin);
		} else {
			upgrade = new Label("Max", skin);
			sell = new Label("75%", skin);
		}

		upgrade.setFontScale(0.6f);
		sell.setFontScale(0.6f);
		upgrade.moveBy(155, 17);
		sell.moveBy(155, 56);
		Label name = new Label("name: " + tower.getType(), skin);
		Label price = new Label("price: " + tower.getPrice() + "$", skin);
		Label type = new Label("type: " + "damage", skin);
		Label dmg = new Label("damage: " + tower.getDamage(), skin);
		Label kills = new Label("kills: " + tower.getKills(), skin);
		name.setFontScale(0.6f);
		price.setFontScale(0.6f);
		type.setFontScale(0.6f);
		dmg.setFontScale(0.6f);
		kills.setFontScale(0.6f);
		Table labels = new Table();
		labels.add(name);
		labels.row();
		labels.add(price);
		labels.row();
		labels.add(type);
		labels.row();
		labels.add(dmg);
		labels.row();
		labels.add(kills);
		labels.moveBy(110, 115);

		super.addActor(upgradeTowerButton);
		super.addActor(sellTowerButton);
		super.addActor(towerImg);
		super.addActor(labels);
		super.addActor(sell);
		super.addActor(upgrade);
		super.addActor(closeButton);

		TowerInfoWindow.instance = this;
	}

	public static TowerInfoWindow getInstance() {
		return instance;
	}

	public static void removeactor() {
		isOpen = false;
		TowerInfoWindow.getInstance().remove();
		
	}


}
