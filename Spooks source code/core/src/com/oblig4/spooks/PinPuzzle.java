package com.oblig4.spooks;

import java.util.concurrent.TimeUnit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import logicClasses.Pin;

public class PinPuzzle {
	public static Skin uiSkin;
	Pin pin;
	Table pauseTable;
	Label[] label = new Label[3];
	String exitRoom;
	
	PinPuzzle(Viewport viewport, final RoomState roomState) {
		pauseTable = new Table();
		pin = new Pin();
		uiSkin = new Skin(Gdx.files.internal("skin/star-soldier-ui.json"));
		TextButton tall1 = new TextButton("1", uiSkin);
		TextButton tall2 = new TextButton("2", uiSkin);
		TextButton tall3 = new TextButton("3", uiSkin);
		TextButton tall4 = new TextButton("4", uiSkin);
		TextButton tall5 = new TextButton("5", uiSkin);
		TextButton tall6 = new TextButton("6", uiSkin);
		TextButton tall7 = new TextButton("7", uiSkin);
		TextButton tall8 = new TextButton("8", uiSkin);
		TextButton tall9 = new TextButton("9", uiSkin);
		TextButton tallReset = new TextButton("Reset", uiSkin);
		TextButton tallBack = new TextButton("Back", uiSkin);
		TextButton tallGo = new TextButton("Accept", uiSkin);

		// Setup listeners for the buttons
		tall1.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				pin.input(1);
			}
		});
		tall2.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				pin.input(2);
			}
		});
		tall3.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				pin.input(3);
			}
		});
		tall4.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				pin.input(4);
			}
		});
		tall5.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				pin.input(5);
			}
		});
		tall6.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				pin.input(6);
			}
		});
		tall7.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				pin.input(7);
			}
		});
		tall8.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				pin.input(8);
			}
		});
		tall9.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				pin.input(9);
			}
		});
		tallReset.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				pin.reset();
			}
		});
		tallBack.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				roomState.moveToRoom(exitRoom);
			}
		});
		tallGo.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (pin.isFinish()) {
					roomState.moveToRoom(exitRoom);
				} else
					pin.reset();
			}
		});

		pauseTable.setX(viewport.getWorldWidth() / 2);
		pauseTable.setY(viewport.getWorldHeight() / 2);

		label[0] = new Label(String.format("%04d", 0), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		label[1] = new Label(String.format("%04d", 0), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		label[2] = new Label(String.format("%04d", 0), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		
		pauseTable.add(label[0]);
		pauseTable.add(label[1]);
		pauseTable.add(label[2]);
		pauseTable.row();
		// Add buttons to table and the table to the stage
		pauseTable.add(tall1);
		pauseTable.add(tall2);
		pauseTable.add(tall3);
		pauseTable.row();
		pauseTable.add(tall4);
		pauseTable.add(tall5);
		pauseTable.add(tall6);
		pauseTable.row();
		pauseTable.add(tall7);
		pauseTable.add(tall8);
		pauseTable.add(tall9);
		pauseTable.row();
		pauseTable.add(tallReset);
		pauseTable.add(tallBack);
		pauseTable.add(tallGo);
	}

	public Label getLabel(int index){
		return label[index-1];
	}
	public void setExitRoom(String exitRoom){
		this.exitRoom = exitRoom;
	}
	
}
