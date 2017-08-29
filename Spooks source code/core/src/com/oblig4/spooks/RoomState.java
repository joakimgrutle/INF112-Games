package com.oblig4.spooks;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;

import items.*;
import logicClasses.Item;
import logicClasses.PaintedButtonPuzzle;

public class RoomState {

	public ArrayList<Room> roomList = new ArrayList<>();
	String currentRoom = "Kjeller";
	Stage stage;
	private Viewport viewport;
	Hud hud;
	PinPuzzle pin;
	PaintedButtonPuzzleRoom pbpr;
	private static RoomState instance;
	private SpriteBatch batch;

	public RoomState(Stage s, Hud huds) {
		this.stage = s;
		this.viewport = s.getViewport();
		this.hud = huds;
		pin = new PinPuzzle(viewport,this);
		pbpr = new PaintedButtonPuzzleRoom(viewport,this);
		Texture image;
		Room nyttRom;
		TextButton moveButton;
		Texture test;
		instance = this;
		batch = new SpriteBatch();

		TextureRegionDrawable mainBtnUp = new TextureRegionDrawable(Spooks.mainButtonsAtals.findRegion("MenuButton1"));
		TextureRegionDrawable mainBtnDown = new TextureRegionDrawable(
				Spooks.mainButtonsAtals.findRegion("MenuButton2"));
		ButtonOwnStyle btnStyleMenu = new ButtonOwnStyle(mainBtnUp, mainBtnDown);
		btnStyleMenu.font = Spooks.font;

		//////////// Kjeller ///////////
		image = new Texture(Gdx.files.internal("background/sketch_basement.png"));
		nyttRom = new Room("Kjeller", image);
		moveButton = new TextButton("Kjeller", btnStyleMenu);
		test = new Texture("images/kniv_stor.png");

		// Setup listeners for the buttons
		moveButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// Wait for sound to play
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(hud.getInventory().hasItem("boltcutter"))
				moveToRoom("Stue");
			}
		});
		moveButton.setX(viewport.getWorldWidth() - hud.hudArea.getHeight());
		moveButton.setY(viewport.getWorldHeight() - hud.hudArea.getHeight());
		nyttRom.addActor(moveButton);
		roomList.add(nyttRom);
		stage.addActor(moveButton); 



		////////////////// Stue/////////////////

		image = new Texture(Gdx.files.internal("background/sketch_livingroom.png"));
		nyttRom = new Room("Stue", image);
		moveButton = new TextButton("Kjeller", btnStyleMenu);

		
		moveButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// Wait for sound to play
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				moveToRoom("Kjeller");
			}
		});
		moveButton.setX(viewport.getWorldWidth() / 2 - 90);
		moveButton.setY(100);
		nyttRom.addActor(moveButton);

		moveButton = new TextButton("Kjøkken", btnStyleMenu);
		// Setup listeners for the buttons
		moveButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// Wait for sound to play
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				moveToRoom("Kjøkken");
			}
		});

		moveButton.setX(1080);
		moveButton.setY(300);
		nyttRom.addActor(moveButton);

		moveButton = new TextButton("Bad", btnStyleMenu);
		// Setup listeners for the buttons
		moveButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// Wait for sound to play
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				moveToRoom("Bad");
			}
		});

		moveButton.setX(0);
		moveButton.setY(300);
		nyttRom.addActor(moveButton);

		moveButton = new TextButton("Loft", btnStyleMenu);
		// Setup listeners for the buttons
		moveButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// Wait for sound to play
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				moveToRoom("Loft");
			}
		});

		moveButton.setX(300);
		moveButton.setY(600);
		nyttRom.addActor(moveButton);

		roomList.add(nyttRom);

		////////// Loft //////////////

		image = new Texture(Gdx.files.internal("background/sketch_attic.png"));
		nyttRom = new Room("Loft", image);
		moveButton = new TextButton("Stue", btnStyleMenu);

		// Setup listeners for the buttons
		moveButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// Wait for sound to play
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				moveToRoom("Stue");
			}
		});
		moveButton.setX(viewport.getWorldWidth() / 2 - 90);
		moveButton.setY(100);
		nyttRom.addActor(moveButton);

		moveButton = new TextButton("PaintedButtonPuzzle", btnStyleMenu);
		moveButton.setX(viewport.getWorldWidth() / 3);
		moveButton.setY(viewport.getWorldHeight() / 2);
		moveButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// Wait for sound to play
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				moveToRoom("PaintedButtonPuzzle");
			}
		});
		nyttRom.addActor(moveButton);

		moveButton = new TextButton("PinPuzzle", btnStyleMenu);
		// Setup listeners for the buttons
		moveButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// Wait for sound to play
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				moveToRoom("PinPuzzle");
			}
		});
		moveButton.setX(800);
		moveButton.setY(200);
		nyttRom.addActor(moveButton);
		roomList.add(nyttRom);

		///////////////// PinPuzzle///////////

		image = new Texture(Gdx.files.internal("background/sketch_attic.png"));
		nyttRom = new Room("PinPuzzle", image);
		// Legge til pinpuzzle
		pin.setExitRoom("Loft");
		nyttRom.addActor(pin.pauseTable);
		roomList.add(nyttRom);

		///////////////// PaintedButtonPuzzle///////////

		image = new Texture(Gdx.files.internal("background/sketch_attic.png"));
		nyttRom = new Room("PaintedButtonPuzzle", image);
		pbpr.setExitRoom("Loft");
		nyttRom.addActor(pbpr.pauseTable);
		roomList.add(nyttRom);

		/////// Soverom/////////////

		image = new Texture(Gdx.files.internal("background/sketch_bathroom.png"));
		nyttRom = new Room("Bad", image);
		moveButton = new TextButton("Stue", btnStyleMenu);

		// Setup listeners for the buttons
		moveButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// Wait for sound to play
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				moveToRoom("Stue");
			}
		});
		moveButton.setX(viewport.getWorldWidth() / 2 - 90);
		moveButton.setY(100);
		nyttRom.addActor(moveButton);
		roomList.add(nyttRom);

		/////// Kitchen///////////

		image = new Texture(Gdx.files.internal("background/sketch_kitchen.png"));
		nyttRom = new Room("Kjøkken", image);
		moveButton = new TextButton("Stue", btnStyleMenu);

		// Setup listeners for the buttons
		moveButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// Wait for sound to play
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				moveToRoom("Stue");
			}
		});
		moveButton.setX(200);
		moveButton.setY(400);
		nyttRom.addActor(moveButton);
		roomList.add(nyttRom);

		Item item = new BrokenBoltCutter(null);
		item.setPosition(new Vector2(500, 500));
		item.getSprite().setSize(20, 20);
		getCurrentRoom().addItem(item);
		
		item = new Screw(null);
		item.setPosition(new Vector2(300,300));
		getCurrentRoom().addItem(item);
		
		item = new Screwdriver(null);
		item.setPosition(new Vector2(200,290));
		getCurrentRoom().addItem(item);
	}

	public Room getRoom(String room) {
		for (Room e : roomList) {
			if (e.getRoomName() == room)
				return e;
		}
		return null;
	}

	public String getCurrentRoomName() {
		return currentRoom;
	}

	public Room getCurrentRoom() {
		return getRoom(getCurrentRoomName());
	}

	public void setCurrentRoom(String room) {
		currentRoom = room;
	}

	public void moveToRoom(String room) {
		for (Actor e : getRoom(currentRoom).getDirectionList()) {
			e.remove();
		}
		for (Actor e : getRoom(room).getDirectionList()) {
			stage.addActor(e);
		}
		setCurrentRoom(room);
	}

	public void draw(SpriteBatch sb) {
		sb.begin();
		sb.draw(getCurrentRoom().roomImage, 0, hud.hudArea.getHeight(), viewport.getWorldWidth(),
				viewport.getWorldHeight() - hud.hudArea.getHeight());
		for (Item e : getCurrentRoom().itemList) {
			e.draw(sb);
		}
		sb.end();

	}

}
